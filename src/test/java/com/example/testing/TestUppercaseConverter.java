package com.example.testing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import java.time.Duration;

@SpringBootTest
public class TestUppercaseConverter {

    final TestPublisher<String> testPublisher = TestPublisher.create();

    @Test
    void testUpperCase() {

        UppercaseConverter uppercaseConverter = new UppercaseConverter(testPublisher.flux());

        StepVerifier.create(uppercaseConverter.getUpperCase())
                .then(() -> testPublisher.emit("dAtos", "GeNeRaDoS", "Sofka"))
                .expectNext("DATOS", "GENERADOS", "SOFKA")
                .verifyComplete();
    }
    @Test
    void testUpperCaseError() {

        TestPublisher<String> testPublishr = TestPublisher
                .createNoncompliant(TestPublisher.Violation.ALLOW_NULL);

        StepVerifier.create( testPublishr
                        .emit("1", "2", null, "3"))
                .thenAwait(Duration.ofSeconds(10))
                .expectComplete()
                .verify();
    }
}
