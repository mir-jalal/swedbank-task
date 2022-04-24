package com.swedbank.datagenerator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class BitcoinPriceIndex implements Serializable {
    private Date timestamp;

    private BigDecimal usd;
    private BigDecimal gbp;
    private BigDecimal eur;

    @JsonProperty("time")
    public void getFromTime(Map<String, String> time){
        String timeString = time.get("updatedISO");
        this.timestamp = Date.from(Instant.parse(timeString));
    }

    @JsonProperty("bpi")
    public void getFromBpi(Map<String, Map<String, String>> bpi){
        String usdString = bpi.get("USD").get("rate_float");
        String eurString = bpi.get("EUR").get("rate_float");
        String gbpString = bpi.get("GBP").get("rate_float");

        this.usd = new BigDecimal(usdString);
        this.eur = new BigDecimal(eurString);
        this.gbp = new BigDecimal(gbpString);
    }

    @Override
    public String toString(){
        return "BitcoinPriceIndex{" +
                "timestamp=" + timestamp +
                ", usd=" + usd +
                ", gbp=" + gbp +
                ", eur=" + eur +
                '}';
    }
}
