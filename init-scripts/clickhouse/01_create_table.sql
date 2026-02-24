CREATE TABLE IF NOT EXISTS агрегаты_событий_паспортов (
    дата_и_время_записи DateTime DEFAULT now(),
    количество_записей UInt64
) ENGINE = MergeTree()
ORDER BY дата_и_время_записи;