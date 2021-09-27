package com.everisbootcamp.accountcommission;

import javax.annotation.PreDestroy;

import com.everisbootcamp.accountcommission.Constants.Exxecutors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class AccountCommissionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountCommissionApplication.class, args);    
    }

    @PreDestroy
    public void onExit() { 
        Exxecutors.executor.shutdown();  
        log.info(" ## SERVICE CHARGE COMMISSION BY ACCOUNT DISABLED"); 
    }
}
