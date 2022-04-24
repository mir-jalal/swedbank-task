package com.swedbank.datareader.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Document(indexName = "bitcoin_price_list")
public class BitcoinPriceIndex {
    @Id
    private UUID id = UUID.randomUUID();

    @Field(type= FieldType.Date)
    private Date timestamp;

    @Field(type = FieldType.Double)
    private BigDecimal usd;

    @Field(type = FieldType.Double)
    private BigDecimal eur;

    @Field(type = FieldType.Double)
    private BigDecimal gbp;

    @Override
    public String toString() {
        return "BitcoinPriceIndex{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", usd=" + usd +
                ", eur=" + eur +
                ", gbp=" + gbp +
                '}';
    }
}
