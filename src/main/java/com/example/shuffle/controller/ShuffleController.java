package com.example.shuffle.controller;

import com.example.shuffle.model.ShuffleRequest;
import com.example.shuffle.service.ShuffleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/shuffle")

public class ShuffleController {
    @Autowired
    private ShuffleService shuffleService;

    @PostMapping
    public ResponseEntity<List<Integer>> shuffle(@RequestBody ShuffleRequest request) {
        List<Integer> shuffled = shuffleService.shuffleNumbers(request.getNumber());
        shuffleService.sendLogAsync(request);
        return ResponseEntity.ok(shuffled);
    }
}
