package ru.rksp.zhilkinalera_processor.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.rksp.zhilkinalera_processor.dto.PassportEventDto;
import ru.rksp.zhilkinalera_processor.entity.PassportEntity;
import ru.rksp.zhilkinalera_processor.repository.PassportRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class PassportEventListener {

    private final PassportRepository passportRepository;

    @RabbitListener(queues = "events.raw")
    public void receivePassportEvent(PassportEventDto event) {
        log.info("Получено событие по паспорту: {}", event);

        PassportEntity entity = new PassportEntity();
        entity.setApplicationNumber(event.getApplicationNumber());
        entity.setFullName(event.getFullName());
        entity.setPassportSeriesNumber(event.getPassportSeriesNumber());
        entity.setBirthDate(event.getBirthDate());
        entity.setEventDate(event.getEventDate());

        passportRepository.save(entity);
        log.info("Событие сохранено в PostgreSQL");
    }
}