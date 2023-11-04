package com.example.javarabbitmq.service;

import com.example.javarabbitmq.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageService {
    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    /**
     * 메세지를 발행
     */
    public void sendMessage(MessageDto messageDto) {
        log.info("message sent: {}", messageDto.toString());
        rabbitTemplate.convertAndSend(exchangeName, routingKey, messageDto);
    }


    /**
     * 메시지 수신
     */
    @RabbitListener(queues = "${spring.rabbitmq.queue.name}")
    public void reciveMessage(MessageDto messageDto) {
        log.info("Received message: {}", messageDto.toString());
    }

}
