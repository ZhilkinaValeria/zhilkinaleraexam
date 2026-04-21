CREATE TABLE IF NOT EXISTS raw_parking_events (
    идентификатор UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    госномер VARCHAR(255) NOT NULL,
    номер_парковки VARCHAR(6) NOT NULL,
    стоимость DECIMAL(10,2) NOT NULL,
    способ_оплаты VARCHAR(20) NOT NULL,
    дата_события TIMESTAMP NOT NULL
);