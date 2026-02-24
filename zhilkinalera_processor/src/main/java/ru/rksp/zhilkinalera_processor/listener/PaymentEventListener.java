package ru.rksp.zhilkinalera_processor.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.rksp.zhilkinalera_processor.dto.PaymentEventDto;
import ru.rksp.zhilkinalera_processor.entity.PaymentEntity;
import ru.rksp.zhilkinalera_processor.repository.PaymentRepository;

@Component
public class PaymentEventListener {

    private static final Logger log = LoggerFactory.getLogger(PaymentEventListener.class);
    private final PaymentRepository paymentRepository;

    public PaymentEventListener(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @RabbitListener(queues = "events.raw")
    public void receivePaymentEvent(PaymentEventDto event) {
        log.info("Получено событие: {}", event);

        PaymentEntity entity = new PaymentEntity();
        entity.setФио_плательщика(event.getФио_плательщика());
        entity.setСумма(event.getСумма());
        entity.setВалюта(event.getВалюта());
        entity.setСпособ_оплаты(event.getСпособ_оплаты());
        entity.setДата_события(event.getДата_события());

        paymentRepository.save(entity);
        log.info("Событие сохранено в БД");
    }
}