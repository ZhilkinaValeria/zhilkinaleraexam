package ru.rksp.zhilkinalera_processor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingEventDto {
    private String carNumber;
    private String parkingNumber;
    private BigDecimal parkingCost;
    private String paymentType;
    private LocalDateTime eventDate;
}