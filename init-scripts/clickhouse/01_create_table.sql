CREATE TABLE IF NOT EXISTS parking_event_aggregates (
    record_timestamp DateTime DEFAULT now(),
    record_count UInt64
) ENGINE = MergeTree()
ORDER BY record_timestamp;