package com.example.shuffle.service;

import com.example.shuffle.model.ShuffleRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ShuffleService {

    @Value("${log.service.url}")
    private String logServiceUrl;

    private final WebClient webClient = WebClient.create();

    public List<Integer> shuffleNumbers(int n) {
        List<Integer> list = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList());
        Collections.shuffle(list);
        return list;
    }

    @Async
    public void sendLogAsync(ShuffleRequest request) {
        webClient.post()
                .uri(logServiceUrl)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
    }
}
