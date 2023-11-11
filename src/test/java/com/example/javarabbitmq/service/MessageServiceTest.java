package com.example.javarabbitmq.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    void name() {
        messageService.test();
    }

    @Test
    void subListTest() {
        List<Integer> collect = IntStream.rangeClosed(0, 1000).boxed()
                .collect(Collectors.toList());

        List<List<Integer>> lists = IntStream.range(0, collect.size())
                .boxed()
                .collect(Collectors.groupingBy(i -> i / 100, Collectors.mapping(collect::get, Collectors.toList()))).values().stream().toList();
        System.out.println(lists.size());
    }
}
