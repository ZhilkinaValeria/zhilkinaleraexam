package ru.rksp.zhilkinalera_processor.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "сырые_события_платежей")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "идентификатор")
    private UUID идентификатор;

    @Column(name = "фио_плательщика", nullable = false)
    private String фио_плательщика;

    @Column(name = "сумма", nullable = false)
    private BigDecimal сумма;

    @Column(name = "валюта", nullable = false, length = 3)
    private String валюта;

    @Column(name = "способ_оплаты", nullable = false)
    private String способ_оплаты;

    @Column(name = "дата_события", nullable = false)
    private LocalDateTime дата_события;

    public PaymentEntity() {}

    public UUID getИдентификатор() { return идентификатор; }
    public void setИдентификатор(UUID идентификатор) { this.идентификатор = идентификатор; }

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