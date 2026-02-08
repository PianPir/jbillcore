package io.github.pianpir.jbillcore.usage;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usage")
@RequiredArgsConstructor
public class UsageEventController {

    private final UsageEventService usageEventService;

    @PostMapping
    public ResponseEntity<UsageEvent> saveUsageEvent(@RequestHeader(name = "Idempotency-Key") String idempotencyKey,
                                         @Valid @RequestBody UsageEventRequest request) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException("Idempotency-Key header must not be blank");
        }
        UsageEvent saved = usageEventService.recordUsageEvent(idempotencyKey, request);
        return ResponseEntity.ok().body(saved);
    }
}
