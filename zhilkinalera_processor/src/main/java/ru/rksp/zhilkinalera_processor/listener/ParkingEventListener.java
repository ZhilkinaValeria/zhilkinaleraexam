package ru.rksp.zhilkinalera_processor.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.rksp.zhilkinalera_processor.dto.ParkingEventDto;
import ru.rksp.zhilkinalera_processor.entity.ParkingEntity;
import ru.rksp.zhilkinalera_processor.repository.ParkingRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class ParkingEventListener {

    private final ParkingRepository parkingRepository;

    @RabbitListener(queues = "events.raw")
    public void receiveParkingEvent(ParkingEventDto event) {
        log.info("Получено событие по парковке: {}", event);

        ParkingEntity entity = new ParkingEntity();
        entity.setCarNumber(event.getCarNumber());
        entity.setParkingNumber(event.getParkingNumber());
        entity.setParkingCost(event.getParkingCost());
        entity.setPaymentType(event.getPaymentType());
        entity.setEventDate(event.getEventDate());

        parkingRepository.save(entity);
        log.info("Событие сохранено в PostgreSQL");
    }
}