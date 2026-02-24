package ru.rksp.zhilkinalera_processor.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentEventDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String фио_плательщика;
    private BigDecimal сумма;
    private String валюта;
    private String способ_оплаты;
    private LocalDateTime дата_события;

    public PaymentEventDto() {}

    public String getФио_плательщика() { return фио_плательщика; }
    public void setФио_плательщика(String фио_плательщика) { this.фио_плательщика = фио_плательщика; }

    public BigDecimal getСумма() { return сумма; }
    public void setСумма(BigDecimal сумма) { this.сумма = сумма; }

    public String getВалюта() { return валюта; }
    public void setВалюта(String валюта) { this.валюта = валюта; }

    public String getСпособ_оплаты() { return способ_оплаты; }
    public void setСпособ_оплаты(String способ_оплаты) { this.способ_оплаты = способ_оплаты; }

    public LocalDateTime getДата_события() { return дата_события; }
    public void setДата_события(LocalDateTime дата_события) { this.дата_события = дата_события; }
}