package ru.rksp.zhilkinalera_processor.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "raw_passport_events")
public class PassportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "application_number")
    private String applicationNumber;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "passport_series_number")
    private String passportSeriesNumber;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "event_date")
    private LocalDateTime eventDate;
}