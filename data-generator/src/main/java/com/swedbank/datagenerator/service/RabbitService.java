package com.swedbank.datagenerator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swedbank.datagenerator.model.BitcoinPriceIndex;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class RabbitService {
    public static final String EXCHANGE_NAME = "datagenerator.exchange";
    public static final String ROUTING_KEY = "datagenerator.bitcoin";


    public final RabbitTemplate rabbitTemplate;
    public final BitcoinPriceIndexGeneratorService service;
    public final ObjectMapper objectMapper;

    @Scheduled(fixedDelay = 60000)
    public void sendBitcoinPriceIndex() throws IOException {
        BitcoinPriceIndex responseMetric = service.getBitcoinPriceIndex();
        String responseMetricJson = objectMapper.writeValueAsString(responseMetric);
        Message message = MessageBuilder
                .withBody(responseMetricJson.getBytes(StandardCharsets.UTF_8))
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();

        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);
    }
}
