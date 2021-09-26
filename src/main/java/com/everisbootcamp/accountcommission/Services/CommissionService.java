package com.everisbootcamp.accountcommission.Services;

import com.everisbootcamp.accountcommission.Interface.CommissionRepository;
import com.everisbootcamp.accountcommission.Model.AccountModel;
import com.everisbootcamp.accountcommission.Web.Consumer;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class CommissionService {

    //@Autowired
    //private CommissionRepository repository;

    private static ScheduledExecutorService executor = null;

    private Flux<AccountModel> findAllAccount() {
        return Consumer.webClientAccount
            .get()
            .uri("/")
            .retrieve()
            .bodyToFlux(AccountModel.class);
    }

    private void registerCommission(String number, LocalDateTime date) {
        Date date2 = new Date();

        TimerTask timertask = new TimerTask() {
            @Override
            public void run() {
                log.info(" -" + number + date.getDayOfMonth() + date2.getDay());
                //repository.save(null).subscribe();
            }
        };

        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(timertask, 1, 1, TimeUnit.SECONDS);
        //executor.shutdown();
    }

    public void ChargeCommision() {
        findAllAccount()
            .toStream()
            .filter(ac -> ac.getRules().isCommissionMaintenance())
            .filter(acc -> acc.getAmount() >= 1)
            .map(ac -> {
                registerCommission(ac.getIdaccount(), ac.getDatecreated());
                return ac;
            });
    }
}
