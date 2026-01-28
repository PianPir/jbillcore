package io.github.pianpir.jbillcore.usage;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsageEventRepository extends JpaRepository<UsageEvent, String> {

    @Query("SELECT ue.idempotencyKey FROM UsageEvent ue")
    List<String> findAllIdempotencyKeys();

    UsageEvent findByIdempotencyKey(String idempotencyKey);
}
