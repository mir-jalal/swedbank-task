package com.swedbank.datagenerator.service;

import com.swedbank.datagenerator.model.ResponseMetric;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ResponseMetricGeneratorService {

    private static final int MAX_GENERATED_RESPONSE_TIME = 1000;

    private final ThreadLocalRandom randomWithThreadLocalRandomInARange = ThreadLocalRandom.current();

    public ResponseMetric generateRequestMetrics() {
        int randomNumber = randomWithThreadLocalRandomInARange.nextInt(1, 100);
        int totalResponseTime=0;
        int minResponseTime=MAX_GENERATED_RESPONSE_TIME;
        int maxResponseTime=0;

        for (int i = 0; i < randomNumber; i++) {
            int responseTime = randomWithThreadLocalRandomInARange.nextInt(1, MAX_GENERATED_RESPONSE_TIME);
            if (responseTime < minResponseTime) {
                minResponseTime = responseTime;
            }
            if (responseTime > maxResponseTime) {
                maxResponseTime = responseTime;
            }
            totalResponseTime += responseTime;
        }

        ResponseMetric responseMetric = new ResponseMetric(new Date(System.currentTimeMillis()), randomNumber,
                ((double) totalResponseTime / (double) randomNumber),
                maxResponseTime, minResponseTime, totalResponseTime);

        System.out.println(responseMetric);

        return responseMetric;
    }
}
