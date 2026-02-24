package ru.rksp.zhilkinalera_ingest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rksp.zhilkinalera_ingest.dto.PassportEventDto;

@RestController
@RequestMapping("/api/v1/events")
@Tag(name = "События паспортов", description = "API для приёма событий по паспортам")
public class EventController {

    private final RabbitTemplate rabbitTemplate;

    public EventController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    @Operation(summary = "Отправить событие по паспорту в очередь")
    public ResponseEntity<String> sendEvent(@RequestBody PassportEventDto event) {
        rabbitTemplate.convertAndSend("events.raw", event);
        return ResponseEntity.ok("Событие успешно отправлено в очередь");
    }
}
