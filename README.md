# JBillCore

> **Self-hosted usage-based billing engine for Spring Boot apps**  
> No cloud. No payments. Just events and invoices.

Простой, надёжный движок учёта использования для indie-hacker’ов и small SaaS.  
Шлёте события → получаете расчёт за период. Всё работает локально — данные остаются у вас.

---

##  Возможности

- Приём событий использования через REST API
- Гарантированная **идемпотентность** (повторный запрос = тот же результат)
- Агрегация по месяцам (UTC)
- Self-hosted: запускается рядом с вашим приложением
- Нет зависимости от Stripe, Lago или облака

---

##  Быстрый старт

1. Запустите сервис:
   ```bash
   docker-compose up -d
   ```

2. Отправьте событие:
   ```bash
   curl -X POST http://localhost:8080/usage \
     -H "Idempotency-Key: req_123" \
     -H "Content-Type: application/json" \
     -d '{
       "customer_id": "acme_corp",
       "feature_key": "pdf_generation",
       "amount": 1
     }'
   ```

3. Получите баланс за текущий месяц:
   ```bash
   curl "http://localhost:8080/balance?customerId=acme_corp"
   ```

4. История по месяцам:
   ```bash
   curl "http://localhost:8080/invoices?customerId=acme_corp"
   ```

---

## Технологии

- Java 21
- Spring Boot 3
- PostgreSQL
- Flyway
- Docker + Docker Compose
- REST API + OpenAPI

---

##  Документация API

Swagger UI (будет) доступен по адресу:  
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## Лицензия

MIT - используйте, модифицируйте, внедряйте свободно.
