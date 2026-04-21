# Экзаменационный проект

**Автор:** Жилкина Валерия Дмитриевна  
**Стек:** Java 17, Spring Boot 3.4, Maven, PostgreSQL 15, ClickHouse 23.8, RabbitMQ 3.x, Docker Compose

Ответ на вопросы в [answerQuestions.md](https://github.com/ZhilkinaValeria/zhilkinaleraexam/blob/main/answerQuestions.md)

---

## Требования к окружению
- JDK 17 (проверка: `java -version`)
- Docker & Docker Compose
- Apache Maven 3.8+
- Утилита `curl` (или Postman/Insomnia)
- Доступ к портам: `5432`, `8123`, `9000`, `5672`, `15672`, `8081`, `8082`

---

## Структура проекта

    ```agsl
    zhilkinaleraexam/
    ├── docker-compose.yml          # Инфраструктура (PG, CH, RMQ)
    ├── init-scripts/
    │   ├── postgres/               # 01_create_table.sql, 02_insert_data.sql
    │   └── clickhouse/             # 01_create_table.sql, 02_insert_data.sql
    ├── zhilkinalera_ingest/        # Сервис №1 (port 8082)
    │   └── src/main/java/ru/rksp/...
    ├── zhilkinalera_processor/     # Сервис №2 (port 8081)
    │   └── src/main/java/ru/rksp/...
    ├── pom.xml                     # Родительский POM
    └── README.md
    ```

## Примечание по реализации

**PostgreSQL:** Таблица и все колонки используют кириллические идентификаторы, инициализация выполняется через init-scripts/postgres/.
**ClickHouse:** Таблица parking_event_aggregates и её колонки используют латинские идентификаторы. Это техническое решение обусловлено ограничением парсера JDBC-драйвера ClickHouse 0.4.6, который не поддерживает кириллицу в DML-запросах (bad SQL grammar). Функционально схема полностью соответствует требованию: хранит агрегированные данные с временной меткой и счётчиком записей.
**Аутентификация ClickHouse:** Пользователь default настроен с пустым паролем. При ручных HTTP-запросах необходимо явно указывать &user=default или заголовки X-ClickHouse-User: default.
**Сборка:** Проект использует Maven Multi-Module. Корневой pom.xml задает groupId: ru.rksp и artifactId: zhilkina.

## Быстрый запуск

1. **Скачайте/распакуйте проект** и перейдите в корневую папку:
   ```bash
   
   git clone https://github.com/ZhilkinaValeria/zhilkinaleraexam.git
   
   cd zhilkinaleraexam
   ```
2. Поднимите инфраструктуру (PostgreSQL, ClickHouse, RabbitMQ):

    ```bash
   docker-compose up -d
   sleep 25
    ```
   
3. Соберите проект:

    ```bash
    mvn clean compile
    ```

4. Запустите сервисы в двух отдельных терминалах:

    ```bash
    # Терминал 1: Ingest-Service
    mvn spring-boot:run -pl zhilkinalera_ingest
    
    # Терминал 2: Processor-Service
    mvn spring-boot:run -pl zhilkinalera_processor
    ```
   
## Функционал

1. Swagger

В браузере для доступа к api в адресную строку вставить:
- Ingest-Service: http://localhost:8082/swagger-ui.html
- Processor-Service: http://localhost:8081/swagger-ui.html

2. Отправка событий через Ingest

    ```bash
    curl -X POST http://localhost:8082/api/v1/events \
    -H "Content-Type: application/json" \
    -d '{
   "carNumber": "А778АА96",
   "parkingNumber": "P22",
   "parkingCost": 800,
   "paymentType": "СБП",
   "eventDate": "2026-04-21T13:25:19.657Z"
   }'
    ```
**Ожидается:** 200 OK и сообщение об успешной отправке.

3. Проверка, что Processor обработал сообщение

    В логах processor-service должны появиться строки:
    ```bash
    Получено событие по парковке: ParkingEventDto(...)
    Событие сохранено в PostgreSQL
    ```

4. Проверка очереди RabbitMQ

    ```bash
   docker exec -it exam_rabbitmq rabbitmqctl list_queues name messages consumers 
   ```
   Ожидается: events.raw с 0 messages (сообщение успешно потреблено) и 1 consumer.

5. endpoint статистики

    ```bash
    curl -X POST http://localhost:8081/api/v1/events/count
    ```
   Ожидается: JSON с текущим количеством записей, "clickhouse": "saved" и статусом success.

## Прямая проверка баз данных

**RabbitMQ (Требование 3)**

UI доступен по адресу: http://localhost:15672 (guest / guest)
Вкладка Queues -> должна присутствовать очередь events.raw.

**PostgreSQL (Требование 4)**

    ```bash
    docker exec -it exam_postgres psql -U user -d parking_db -c "\d raw_parking_events"
    docker exec -it exam_postgres psql -U user -d parking_db -c "SELECT * FROM raw_parking_events LIMIT 2;"
    ```

Структура должна содержать: госномер, номер_парковки, стоимость, способ_оплаты, дата_события.

**ClickHouse (Требование 5)**

    ```bash
    # Просмотр структуры
    curl "http://localhost:8123/?query=DESCRIBE+parking_event_aggregates&user=default"
    
    # Просмотр данных
    curl "http://localhost:8123/?query=SELECT+*+FROM+parking_event_aggregates+ORDER+BY+record_timestamp+DESC+LIMIT+5&user=default"
    ```

Структура должна содержать: record_timestamp (DateTime), record_count (UInt64).


