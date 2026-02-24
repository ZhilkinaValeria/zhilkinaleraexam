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
import ru.rksp.zhilkinalera_processor.repository.PassportRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/events")
@Tag(name = "Статистика паспортов", description = "API для получения статистики по паспортам")
public class StatisticsController {

    private static final Logger log = LoggerFactory.getLogger(StatisticsController.class);

    private final PassportRepository passportRepository;
    private final JdbcTemplate clickhouseJdbcTemplate;

    @Autowired
    public StatisticsController(PassportRepository passportRepository,
                                @Qualifier("clickhouseJdbcTemplate") JdbcTemplate clickhouseJdbcTemplate) {
        this.passportRepository = passportRepository;
        this.clickhouseJdbcTemplate = clickhouseJdbcTemplate;
    }

    @PostMapping("/count")
    @Operation(summary = "Получить количество записей и сохранить в ClickHouse")
    public ResponseEntity<Map<String, Object>> getCount() {
        Map<String, Object> response = new HashMap<>();

        try {
            long count = passportRepository.count();
            log.info("Количество записей в PostgreSQL: {}", count);
            response.put("count", count);

            try {
                String sql = "INSERT INTO агрегаты_событий_паспортов (количество_записей) VALUES (?)";
                int updated = clickhouseJdbcTemplate.update(sql, count);
                log.info("Данные сохранены в ClickHouse, обновлено строк: {}", updated);
                response.put("clickhouse", "saved");
            } catch (Exception e) {
                log.error("Ошибка при сохранении в ClickHouse: {}", e.getMessage());
                response.put("clickhouse", "error: " + e.getMessage());
            }

            response.put("timestamp", LocalDateTime.now().toString());
            response.put("status", "success");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Ошибка: {}", e.getMessage(), e);

            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            errorResponse.put("status", "error");
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
}