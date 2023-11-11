package com.example.javarabbitmq.service;

import com.example.javarabbitmq.domain.PayerUser;
import com.example.javarabbitmq.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
public class MessageService {
    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    List<PayerUser> payerUsers = new ArrayList<>();

    public MessageService(@Value("${spring.rabbitmq.routing.key}") String exchangeName, @Value("${spring.rabbitmq.exchange.name}") String routingKey, RabbitTemplate rabbitTemplate) {
        for (int i = 0; i < 1000000; i++) {
            payerUsers.add(
                    PayerUser.builder()
                            .idx((long) i)
                            .build()
            );
        }

        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void test() {
        System.out.println(payerUsers.size());
    }

    /**
     * 메세지를 발행
     */
    public void sendMessage() {
        log.info("payer send");
        List<List<PayerUser>> lists = IntStream.range(0, payerUsers.size())
                .boxed()
                .collect(Collectors.groupingBy(i -> i / 100000, Collectors.mapping(payerUsers::get, Collectors.toList()))).values().stream().toList();
        for (int i = 0; i < lists.size(); i++) {
            rabbitTemplate.convertAndSend(exchangeName, routingKey, lists.get(i));
        }
    }
}
