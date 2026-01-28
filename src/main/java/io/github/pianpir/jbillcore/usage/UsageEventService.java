package io.github.pianpir.jbillcore.usage;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UsageEventService {

    private final UsageEventRepository usageEventRepository;

    @Transactional
    public UsageEvent saveUsageEvent(String idempotencyKey, UsageEventRequest request) {
        try {
            UsageEvent event = new UsageEvent();
            event.setIdempotencyKey(idempotencyKey);
            event.setCustomerId(request.customerId());
            event.setFeatureKey(request.featureKey());
            event.setAmount(request.amount());
            event.setOccurredAt(Instant.now());

            return usageEventRepository.save(event);

        } catch (DataIntegrityViolationException ex) {
            return usageEventRepository.findById(idempotencyKey)
                    .orElseThrow(() -> new IllegalStateException("Unexpected: key not found after constraint violation"));
        }
    }


}
