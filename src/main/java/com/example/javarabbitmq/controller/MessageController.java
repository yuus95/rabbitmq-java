package com.example.javarabbitmq.controller;

import com.example.javarabbitmq.dto.MessageDto;
import com.example.javarabbitmq.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MessageController {

    private final MessageService messageService;

    /**
     * 메시지 발행
     */
    @PostMapping("/send/message")
    public String sendMessage(@RequestBody MessageDto messageDto) {
        messageService.sendMessage();
        return "send success!";
    }
}
