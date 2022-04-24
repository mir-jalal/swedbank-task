package com.swedbank.datagenerator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swedbank.datagenerator.model.ResponseMetric;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class RabbitService {
    public static final String EXCHANGE_NAME = "datagenerator.exchange";
    public static final String ROUTING_KEY = "datagenerator.response";


    public final RabbitTemplate rabbitTemplate;
    public final ResponseMetricGeneratorService responseMetricGeneratorService;
    public final ObjectMapper objectMapper;

    @Scheduled(fixedDelay = 1000)
    public void sendResponseMetrics() throws JsonProcessingException {
        ResponseMetric responseMetric = responseMetricGeneratorService.generateRequestMetrics();
        String responseMetricJson = objectMapper.writeValueAsString(responseMetric);
        Message message = MessageBuilder
                .withBody(responseMetricJson.getBytes(StandardCharsets.UTF_8))
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();

        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);
    }
}
