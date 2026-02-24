CREATE TABLE IF NOT EXISTS сырые_события_паспортов (
    идентификатор UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    номер_заявления VARCHAR(50) NOT NULL,
    фио VARCHAR(255) NOT NULL,
    серия_номер_паспорта VARCHAR(20) NOT NULL,
    дата_рождения DATE NOT NULL,
    дата_события TIMESTAMP NOT NULL
);