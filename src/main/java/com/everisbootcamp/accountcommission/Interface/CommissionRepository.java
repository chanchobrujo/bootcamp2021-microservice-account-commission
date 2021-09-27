package com.everisbootcamp.accountcommission.Interface;

import com.everisbootcamp.accountcommission.Data.Commission;
import java.time.LocalDateTime;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CommissionRepository extends ReactiveMongoRepository<Commission, String> {
    public Boolean existsByDatecreated(LocalDateTime date);

    public Mono<Commission> findByNumberaccount(String numberaccount);
}
