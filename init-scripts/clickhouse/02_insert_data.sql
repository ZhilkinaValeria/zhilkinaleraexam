-- init-scripts/clickhouse/02_insert_data.sql
TRUNCATE TABLE IF EXISTS parking_event_aggregates;

INSERT INTO parking_event_aggregates (record_count, record_timestamp) VALUES (5, now() - toIntervalDay(7));
INSERT INTO parking_event_aggregates (record_count, record_timestamp) VALUES (3, now() - toIntervalDay(6));
INSERT INTO parking_event_aggregates (record_count, record_timestamp) VALUES (8, now() - toIntervalDay(5));
INSERT INTO parking_event_aggregates (record_count, record_timestamp) VALUES (2, now() - toIntervalDay(4));
INSERT INTO parking_event_aggregates (record_count, record_timestamp) VALUES (10, now() - toIntervalDay(3));
INSERT INTO parking_event_aggregates (record_count, record_timestamp) VALUES (7, now() - toIntervalDay(2));
INSERT INTO parking_event_aggregates (record_count, record_timestamp) VALUES (4, now() - toIntervalDay(1));
INSERT INTO parking_event_aggregates (record_count, record_timestamp) VALUES (6, now() - toIntervalHour(12));
INSERT INTO parking_event_aggregates (record_count, record_timestamp) VALUES (9, now() - toIntervalHour(6));
INSERT INTO parking_event_aggregates (record_count, record_timestamp) VALUES (12, now() - toIntervalHour(1));