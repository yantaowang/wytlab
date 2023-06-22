package com.wyt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class IndexController {

    @GetMapping
    public Flux<Integer> index() {
        return Flux.just(1);
    }
    @GetMapping("/{id}")
    public Mono<String> get(@PathVariable(name = "id") Long id) {
        return Mono.just("d");
    }
}