package com.swedbank.datagenerator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponseMetric implements Serializable {
    @JsonProperty("timestamp")
    private long timestamp;
    @JsonProperty("requestCount")
    private int requestCount;
    @JsonProperty("averageResponseTime")
    private double averageResponseTime;
    @JsonProperty("maxResponseTime")
    private long maxResponseTime;
    @JsonProperty("minResponseTime")
    private long minResponseTime;
    @JsonProperty("totalResponseTime")
    private int totalResponseTime;

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
