package com.everisbootcamp.accountcommission.Services;

import com.everisbootcamp.accountcommission.Constants.Exxecutors; 
import com.everisbootcamp.accountcommission.Model.AccountModel;
import com.everisbootcamp.accountcommission.Web.Consumer;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors; 
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j; 
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class CommissionService {

    //@Autowired
    //private CommissionRepository repository; 

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
                log.info(" -numero: " + number + " -fecha de creación: " + date.getDayOfMonth() +" -dia de hoy:"+ date2.getDate() );
                //repository.save(null).subscribe();
            }
        };
 
        Exxecutors.executor = Executors.newScheduledThreadPool(1);
        Exxecutors.executor.scheduleAtFixedRate(timertask, 1, 1, TimeUnit.SECONDS); 
    }

    public void ChargeCommision() {
        log.info(findAllAccount()
        .toStream()
        .filter(ac -> ac.getRules().isCommissionMaintenance())
        .filter(acc -> acc.getAmount() >= 1)
        .map(ac -> {
            registerCommission(ac.getIdaccount(), ac.getDatecreated());
            return ac;
        }).count()+"");
    }
}
