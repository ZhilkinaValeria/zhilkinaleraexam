package ru.rksp.zhilkinalera_ingest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rksp.zhilkinalera_ingest.dto.PaymentEventDto;

@RestController
@RequestMapping("/api/v1/events")
@Tag(name = "Платёжные события", description = "API для приёма платежных событий")
public class EventController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public EventController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    @Operation(summary = "Отправить событие платежа в очередь")
    public ResponseEntity<String> sendEvent(@RequestBody PaymentEventDto event) {
        try {
            // Отправляем сообщение в очередь "events.raw"
            rabbitTemplate.convertAndSend("events.raw", event);

            return ResponseEntity.ok("Событие успешно отправлено в очередь");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Ошибка при отправке события: " + e.getMessage());
        }
    }
}