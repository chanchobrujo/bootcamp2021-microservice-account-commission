package com.everisbootcamp.accountcommission.Config;

import com.everisbootcamp.accountcommission.Services.CommissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InitCommissión implements CommandLineRunner {

    @Autowired
    private CommissionService service;

    @Override
    public void run(String... args) throws Exception {
        log.info("SERVICE CHARGE COMMISSION BY ACCOUNT ENABLED");
        service.ChargeCommision();
    }
}
