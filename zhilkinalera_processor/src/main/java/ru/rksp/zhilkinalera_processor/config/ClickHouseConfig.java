package ru.rksp.zhilkinalera_processor.config;

import com.clickhouse.jdbc.ClickHouseDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class ClickHouseConfig {

    private static final Logger log = LoggerFactory.getLogger(ClickHouseConfig.class);

    @Value("${clickhouse.url}")
    private String url;

    @Bean(name = "clickhouseDataSource")
    public DataSource clickhouseDataSource() {
        try {
            log.info("Creating ClickHouse DataSource with URL: {}", url);

            ClickHouseDataSource dataSource = new ClickHouseDataSource(url);

            // Тестовое подключение при старте
            try (var conn = dataSource.getConnection()) {
                if (conn.isValid(5)) {
                    log.info("ClickHouse connection test: SUCCESS");
                } else {
                    log.warn("ClickHouse connection test: FAILED (isValid=false)");
                }
            }

            return dataSource;
        } catch (SQLException e) {
            log.error(" Failed to create ClickHouse DataSource: {} | URL was: {}", e.getMessage(), url, e);
            throw new RuntimeException("Failed to create ClickHouse DataSource: " + e.getMessage(), e);
        }
    }

    @Bean(name = "clickhouseJdbcTemplate")
    public JdbcTemplate clickhouseJdbcTemplate(@Qualifier("clickhouseDataSource") DataSource dataSource) {
        log.info("Creating JdbcTemplate for ClickHouse");
        return new JdbcTemplate(dataSource);
    }
}