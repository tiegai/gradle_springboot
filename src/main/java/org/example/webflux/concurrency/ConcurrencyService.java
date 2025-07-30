package org.example.webflux.concurrency;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ConcurrencyService {

    /**
     * 使用 Flux.range() 生成大量任务。
     * 使用 flatMap() 实现并发执行（非阻塞）。
     * Mono.delay() 模拟异步耗时任务。
     * flatMap(..., concurrency) 控制最大并发数，防止资源耗尽。
     */
    public Flux<String> runConcurrentTasks(int count) {
        return Flux.range(1, count)
                .flatMap(this::simulateAsyncTask, 100) // 控制并发度为100
                .doOnNext(result -> System.out.println("Processed: " + result));
    }

    private Mono<String> simulateAsyncTask(int id) {
        return Mono.delay(Duration.ofMillis(ThreadLocalRandom.current().nextInt(10, 100)))
                .map(delay -> "Task " + id + " completed on thread " + Thread.currentThread().getName());
    }
}

