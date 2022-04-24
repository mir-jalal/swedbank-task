package com.swedbank.datareader.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swedbank.datareader.model.ResponseMetric;
import com.swedbank.datareader.repository.ResponseMetricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RabbitListenerService {
    public static final String EXCHANGE_NAME = "datagenerator.exchange";
    public static final String QUEUE_NAME = "datagenerator.queue";
    public static final String ROUTING_KEY = "datagenerator.response";

    public final ObjectMapper objectMapper;
    public final ResponseMetricRepository repository;

    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @RabbitListener(queues = QUEUE_NAME)
    public void receiveMessage(Message message) throws IOException {
        ResponseMetric responseMetric = objectMapper.readValue(message.getBody(), ResponseMetric.class);

        repository.save(responseMetric);

        System.out.println(responseMetric);
    }
}
