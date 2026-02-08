package io.github.pianpir.jbillcore.usage;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsageEventService {

    private final UsageEventRepository usageEventRepository;

    @Transactional
    public UsageEvent recordUsageEvent(String idempotencyKey, UsageEventRequest request) {
        Optional<UsageEvent> existing = usageEventRepository.findById(idempotencyKey);
        if (existing.isPresent()) {
            return existing.get();
        }

        UsageEvent event = new UsageEvent();
        event.setIdempotencyKey(idempotencyKey);
        event.setCustomerId(request.customerId());
        event.setFeatureKey(request.featureKey());
        event.setAmount(request.amount());
        event.setOccurredAt(Instant.now());

        return usageEventRepository.save(event);
    }


}
