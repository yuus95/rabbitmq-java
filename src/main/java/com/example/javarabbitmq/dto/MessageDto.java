package com.example.javarabbitmq.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageDto {
    private String title;
    private String content;
}
