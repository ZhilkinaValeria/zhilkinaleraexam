CREATE TABLE IF NOT EXISTS сырые_события_платежей (
    идентификатор UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    фио_плательщика VARCHAR(255) NOT NULL,
    сумма DECIMAL(15,2) NOT NULL,
    валюта VARCHAR(3) NOT NULL,
    способ_оплаты VARCHAR(50) NOT NULL,
    дата_события TIMESTAMP NOT NULL
);