package io.github.pianpir.jbillcore.usage;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UsageEventRequest (
        @NotBlank String customerId,
        @NotBlank String featureKey,
        @Min(1) long amount
){}
