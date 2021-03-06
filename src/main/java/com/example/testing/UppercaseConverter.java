package com.example.testing;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

//@Service
public class UppercaseConverter {

    private final Flux<String> source;

    UppercaseConverter(Flux<String> source) {
        this.source = source;
    }

    Flux<String> getUpperCase() {
        return source.map(String::toUpperCase);
    }
}
