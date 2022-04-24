package com.swedbank.datareader.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Document(indexName = "response_list")
public class ResponseMetric {
    @Id
    private UUID id = UUID.randomUUID();

    @Field(type= FieldType.Date)
    private Date timestamp;

    @Field(type = FieldType.Long)
    private long requestCount;

    @Field(type = FieldType.Double)
    private double averageResponseTime;

    @Field(type = FieldType.Long)
    private long maxResponseTime;

    @Field(type = FieldType.Long)
    private long minResponseTime;

    @Field(type = FieldType.Long)
    private long totalResponseTime;

    @Override
    public String toString() {
        return "ResponseMetric{" +
                "timestamp=" + timestamp +
                ", requestCount=" + requestCount +
                ", averageResponseTime=" + averageResponseTime +
                ", maxResponseTime=" + maxResponseTime +
                ", minResponseTime=" + minResponseTime +
                ", totalResponseTime=" + totalResponseTime +
                '}';
    }
}
