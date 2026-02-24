-- Очистка таблицы
TRUNCATE TABLE IF EXISTS payment_aggregates;

-- Вставка тестовых данных
INSERT INTO payment_aggregates (records_count, created_at) VALUES
(5, now() - INTERVAL 7 DAY),
(3, now() - INTERVAL 6 DAY),
(8, now() - INTERVAL 5 DAY),
(2, now() - INTERVAL 4 DAY),
(10, now() - INTERVAL 3 DAY),
(7, now() - INTERVAL 2 DAY),
(4, now() - INTERVAL 1 DAY),
(6, now() - INTERVAL 12 HOUR),
(9, now() - INTERVAL 6 HOUR),
(12, now() - INTERVAL 1 HOUR);