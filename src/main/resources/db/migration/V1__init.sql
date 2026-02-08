CREATE TABLE usage_events (
                 idempotency_key TEXT NOT NULL PRIMARY KEY,
                 customer_id TEXT NOT NULL,
                 feature_key TEXT NOT NULL,
                 amount BIGINT NOT NULL CHECK (amount >= 1),
                 occurred_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
)