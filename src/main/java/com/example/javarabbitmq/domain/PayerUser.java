package com.example.javarabbitmq.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * idx,
 * 유저 식별자 번호,
 * 카드 빌링키,
 *
 */
@Getter
@Builder
public class PayerUser {
    private Long idx;
}
