package com.everisbootcamp.accountcommission.Interface;

import com.everisbootcamp.accountcommission.Data.Commission;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CommissionRepository
    extends ReactiveMongoRepository<Commission, String> {}
