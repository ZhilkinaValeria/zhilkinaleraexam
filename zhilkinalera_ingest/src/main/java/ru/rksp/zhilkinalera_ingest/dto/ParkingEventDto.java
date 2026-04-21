package ru.rksp.zhilkinalera_ingest.dto;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

public class ParkingEventDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String carNumber;      // госномер
    private String parkingNumber;               // номер парковки
    private BigDecimal parkingCost;   // стоимость
    private String paymentType;            // способ_оплаты
    private LocalDateTime eventDate;        // дата_события

    public ParkingEventDto() {}

    public String getCarNumber() { return carNumber; }
    public void setCarNumber(String carNumber) { this.carNumber = carNumber; }

    public String getParkingNumber() { return parkingNumber; }
    public void setParkingNumber(String parkingNumber) { this.parkingNumber = parkingNumber; }

    public BigDecimal getParkingCost() { return parkingCost; }
    public void setParkingCost(BigDecimal parkingCost) { this.parkingCost = parkingCost; }

    public String getPaymentType() { return paymentType; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }

    public LocalDateTime getEventDate() { return eventDate; }
    public void setEventDate(LocalDateTime eventDate) { this.eventDate = eventDate; }

    @Override
    public String toString() {
        return "ParkingEventDto{" +
                "carNumber='" + carNumber + '\'' +
                ", parkingNumber='" + parkingNumber + '\'' +
                ", parkingCost='" + parkingCost + '\'' +
                ", paymentType=" + paymentType +
                ", eventDate=" + eventDate +
                '}';
    }
}