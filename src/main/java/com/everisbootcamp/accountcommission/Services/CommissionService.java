package com.everisbootcamp.accountcommission.Services;

import com.everisbootcamp.accountcommission.Constants.Exxecutors;
import com.everisbootcamp.accountcommission.Data.Commission;
import com.everisbootcamp.accountcommission.Interface.CommissionRepository;
import com.everisbootcamp.accountcommission.Model.AccountModel;
import com.everisbootcamp.accountcommission.Web.Consumer;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CommissionService {

    @Autowired
    private CommissionRepository repository;

    private Flux<AccountModel> findAllAccount() {
        return Consumer.webClientAccount.get().uri("/").retrieve().bodyToFlux(AccountModel.class);
    }

    private void registerCommission(String numberaccount, LocalDateTime date) {
        LocalDateTime dateNow = LocalDateTime.now(ZoneId.of("America/Lima"));

        TimerTask timertask = new TimerTask() {
            @Override
            public void run() {
                if (dateNow.getDayOfMonth() == 15) {
                    Mono<Commission> commission = repository.findByNumberaccount(numberaccount);
                    if (commission.block() != null) {
                        if (
                            commission.block().getDatecreated().getMonth().getValue() !=
                            dateNow.getMonthValue()
                        ) repository.save(new Commission(numberaccount, 1)).subscribe();
                    } else {
                        repository.save(new Commission(numberaccount, 1)).subscribe();
                    }
                }
            }
        };

        Exxecutors.executor = Executors.newScheduledThreadPool(1);
        Exxecutors.executor.scheduleAtFixedRate(timertask, 0, 1, TimeUnit.DAYS);
    }

    public void ChargeCommision() {
        log.info(
            findAllAccount()
                .toStream()
                .filter(ac -> ac.getRules().isCommissionMaintenance())
                .filter(acc -> acc.getAmount() >= 1)
                .map(
                    ac -> {
                        registerCommission(ac.getNumberaccount(), ac.getDatecreated());
                        return ac;
                    }
                )
                .count() +
            ""
        );
    }
}
