package org.example.webflux.concurrency;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/concurrent")
public class ConcurrencyController {

    private final ConcurrencyService service;

    public ConcurrencyController(ConcurrencyService service) {
        this.service = service;
    }

    @GetMapping("/run")
    public Flux<String> runTasks(@RequestParam(defaultValue = "1000") int count) {
        return service.runConcurrentTasks(count);
    }
}

