package io.github.pianpir.jbillcore.usage;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "usage_events")
@Getter
@Setter
public class UsageEvent {

    @Id
    @Column(name = "idempotency_key", nullable = false, unique = true)
    private String idempotencyKey;

    @Column(nullable = false)
    private String customerId;

    @Column(nullable = false)
    private String featureKey;

    @Column(nullable = false)
    private long amount;

    @Column(nullable = false)
    private Instant occurredAt = Instant.now();
}
