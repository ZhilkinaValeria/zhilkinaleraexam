package ru.rksp.zhilkinalera_processor.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PassportEventDto {
    private String applicationNumber;
    private String fullName;
    private String passportSeriesNumber;
    private LocalDate birthDate;
    private LocalDateTime eventDate;
}