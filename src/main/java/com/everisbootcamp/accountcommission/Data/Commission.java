package com.everisbootcamp.accountcommission.Data;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "commission")
public class Commission {

    @Id
    private String idcommission;

    private String numberaccount;
    private Integer amount;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime datecreated = LocalDateTime.now(ZoneId.of("America/Lima"));

    public Commission(String numberaccount, Integer amount) {
        this.numberaccount = numberaccount;
        this.amount = amount;
    }
}
