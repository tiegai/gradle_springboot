package org.example.reactor;

import reactor.core.publisher.Mono;

public class MonoSubscribeDemo {

    public static void main(String[] args) {
        // Create a Mono that emits a single value
        Mono<String> mono = Mono.just("Hello, Mono!");

        // Subscribe to the Mono and print the emitted value
        mono.subscribe(value -> System.out.println("Received: " + value));

        // Create a Mono that emits an error
        Mono<String> errorMono = Mono.error(new RuntimeException("An error occurred"));

        // Subscribe to the Mono and handle the error
        errorMono.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error.getMessage())
        );

        // Create a Mono that completes without emitting any value
        Mono<Void> emptyMono = Mono.empty();

        // Subscribe to the Mono and print a message when it completes
        emptyMono.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error.getMessage()),
                () -> System.out.println("Completed without value")
        );
    }
}