package ru.rksp.zhilkinalera_ingest.dto;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PassportEventDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String applicationNumber;      // номер_заявления
    private String fullName;               // фио
    private String passportSeriesNumber;   // серия_номер_паспорта
    private LocalDate birthDate;            // дата_рождения
    private LocalDateTime eventDate;        // дата_события

    public PassportEventDto() {}

    public String getApplicationNumber() { return applicationNumber; }
    public void setApplicationNumber(String applicationNumber) { this.applicationNumber = applicationNumber; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPassportSeriesNumber() { return passportSeriesNumber; }
    public void setPassportSeriesNumber(String passportSeriesNumber) { this.passportSeriesNumber = passportSeriesNumber; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public LocalDateTime getEventDate() { return eventDate; }
    public void setEventDate(LocalDateTime eventDate) { this.eventDate = eventDate; }

    @Override
    public String toString() {
        return "PassportEventDto{" +
                "applicationNumber='" + applicationNumber + '\'' +
                ", fullName='" + fullName + '\'' +
                ", passportSeriesNumber='" + passportSeriesNumber + '\'' +
                ", birthDate=" + birthDate +
                ", eventDate=" + eventDate +
                '}';
    }
}