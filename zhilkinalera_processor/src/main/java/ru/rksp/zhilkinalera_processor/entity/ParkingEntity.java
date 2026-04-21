package ru.rksp.zhilkinalera_processor.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "raw_parking_events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingEntity {

    @Id
    @Column(name = "идентификатор")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "госномер")
    private String carNumber;

    @Column(name = "номер_парковки")
    private String parkingNumber;

    @Column(name = "стоимость")
    private BigDecimal parkingCost;

    @Column(name = "способ_оплаты")
    private String paymentType;

    @Column(name = "дата_события")
    private LocalDateTime eventDate;
}