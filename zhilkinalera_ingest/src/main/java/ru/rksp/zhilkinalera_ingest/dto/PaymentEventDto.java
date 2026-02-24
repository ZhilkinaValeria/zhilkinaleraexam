package ru.rksp.zhilkinalera_ingest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentEventDto {
    private String фио_плательщика;
    private BigDecimal сумма;
    private String валюта;
    private String способ_оплаты;
    private LocalDateTime дата_события;

    // Конструктор по умолчанию (нужен для Spring)
    public PaymentEventDto() {
    }

    // Конструктор со всеми полями
    public PaymentEventDto(String фио_плательщика, BigDecimal сумма, String валюта,
                           String способ_оплаты, LocalDateTime дата_события) {
        this.фио_плательщика = фио_плательщика;
        this.сумма = сумма;
        this.валюта = валюта;
        this.способ_оплаты = способ_оплаты;
        this.дата_события = дата_события;
    }

    // Геттеры и сеттеры (нажми Alt+Insert → Getter and Setter → выбери все поля)
    public String getФио_плательщика() {
        return фио_плательщика;
    }

    public void setФио_плательщика(String фио_плательщика) {
        this.фио_плательщика = фио_плательщика;
    }

    public BigDecimal getСумма() {
        return сумма;
    }

    public void setСумма(BigDecimal сумма) {
        this.сумма = сумма;
    }

    public String getВалюта() {
        return валюта;
    }

    public void setВалюта(String валюта) {
        this.валюта = валюта;
    }

    public String getСпособ_оплаты() {
        return способ_оплаты;
    }

    public void setСпособ_оплаты(String способ_оплаты) {
        this.способ_оплаты = способ_оплаты;
    }

    public LocalDateTime getДата_события() {
        return дата_события;
    }

    public void setДата_события(LocalDateTime дата_события) {
        this.дата_события = дата_события;
    }
}