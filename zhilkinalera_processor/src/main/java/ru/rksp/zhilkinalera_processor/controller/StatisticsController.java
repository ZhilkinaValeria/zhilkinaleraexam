package ru.rksp.zhilkinalera_processor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rksp.zhilkinalera_processor.repository.PaymentRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/events")
@Tag(name = "Статистика платежей", description = "API для получения статистики")
public class StatisticsController {

    private static final Logger log = LoggerFactory.getLogger(StatisticsController.class);

    private final PaymentRepository paymentRepository;
    private final JdbcTemplate clickhouseJdbcTemplate;

    @Autowired
    public StatisticsController(PaymentRepository paymentRepository,
                                @Qualifier("clickhouseJdbcTemplate") JdbcTemplate clickhouseJdbcTemplate) {
        this.paymentRepository = paymentRepository;
        this.clickhouseJdbcTemplate = clickhouseJdbcTemplate;
    }

    @PostMapping("/count")
    @Operation(summary = "Получить количество записей и сохранить в ClickHouse")
    public ResponseEntity<Map<String, Object>> getCount() {
        Map<String, Object> response = new HashMap<>();

        try {
            // Получаем количество записей в PostgreSQL
            long count = paymentRepository.count();
            log.info("Количество записей в PostgreSQL: {}", count);

            // Сохраняем в ClickHouse
            try {
                String sql = "INSERT INTO агрегаты_событий_платежей (количество_записей) VALUES (?)";
                clickhouseJdbcTemplate.update(sql, count);
                log.info("Данные сохранены в ClickHouse");
                response.put("clickhouse", "saved");
            } catch (Exception e) {
                log.error("Ошибка при сохранении в ClickHouse: {}", e.getMessage());
                response.put("clickhouse", "error: " + e.getMessage());
            }

            response.put("count", count);
            response.put("timestamp", java.time.LocalDateTime.now().toString());
            response.put("status", "success");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Ошибка: {}", e.getMessage(), e);
            response.put("error", e.getMessage());
            response.put("status", "error");
            return ResponseEntity.internalServerError().body(response);
        }
    }
}