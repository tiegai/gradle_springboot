package org.example.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class React {

    public static void main(String[] args) {

        System.out.println("Hello, World!");

        Flux.just("可以指定序列中包含的全部元素。创建出来的 Flux 序列在发布这些元素之后会自动结束").subscribe(System.out::println);

        Flux.fromArray(new String[] {"可以从一个数组","Iterable 对象", "Stream 对象中创建 Flux 对象"}).subscribe(System.out::println);

        Flux.empty().subscribe(System.out::println);

        Flux.range(1, 10).subscribe(System.out::println);

        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);

        Flux.generate(synchronousSink -> {
            synchronousSink.next("test");
            synchronousSink.complete();
                }
        ).subscribe(System.out::println);

        final Random random = new Random();
        Flux.generate(ArrayList::new, (list, synchronousSink) -> {
            int value = random.nextInt(100);
            list.add(value);
            synchronousSink.next(value);
            if (list.size() == 10) {
                synchronousSink.complete();
            }
            return list;
        }).subscribe(System.out::println);

        Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);


        Mono.justOrEmpty(Optional.of("Hello Mono justOrEmpty")).subscribe(System.out::println);

        Mono.create(sink -> sink.success("Hello Mono create")).subscribe(System.out::println);

        Flux.range(1, 100).buffer(20).subscribe(System.out::println);
        Flux.range(1,10).bufferUntil(integer -> integer % 2 == 0).subscribe(System.out::println);
        Flux.range(1,10).bufferWhile(integer -> integer % 2 == 0).subscribe(System.out::println);

        Flux.range(1, 10).filter(integer -> integer % 2 == 0).subscribe(System.out::println);

        Flux.range(1,10).window(5).subscribe(System.out::println);
        Flux.range(1,10).windowUntil(integer -> integer % 2 == 0).subscribe(System.out::println);

        Flux.just("a", "b")
                .zipWith(Flux.just("c", "d"))
                .subscribe(System.out::println);
        Flux.just("a", "b")
                .zipWith(Flux.just("c", "d"), (s1, s2) -> String.format("%s: %s", s1, s2))
                .subscribe(System.out::println);

        Flux.range(1, 1000).take(10).subscribe(System.out::println);
        Flux.range(1, 1000).takeLast(10).subscribe(System.out::println);
        Flux.range(1, 1000).takeWhile(i -> i < 10).subscribe(System.out::println);
        Flux.range(1, 1000).takeUntil(i -> i == 10).subscribe(System.out::println);

        // 累计操作
        Flux.range(1, 100).reduce((x, y) -> x + y).subscribe(System.out::println);
        Flux.range(1, 100).reduceWith(() -> 100, (x, y) -> x + y).subscribe(System.out::println);

       /* Flux.merge(Flux.interval(Duration.of(10, ChronoUnit.SECONDS)), Flux.interval(Duration.of(1, ChronoUnit.SECONDS)))
                .toStream()
                .forEach(System.out::println);
        Flux.mergeSequential(Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).take(2), Flux.interval(Duration.of(1, ChronoUnit.SECONDS)).take(2))
                .toStream()
                .forEach(System.out::println);

        */

        Flux.just(5, 10)
                .flatMap(x -> Flux.just(x * 2)).subscribe(System.out::println);

        Flux.just(6, 12)
                .concatMap(x -> Flux.just(x * 2)).subscribe(System.out::println);


        /*Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .subscribe(System.out::println, System.err::println);*/

        /*Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .onErrorReturn(0)
                .subscribe(System.out::println);*/

        /*Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .retry(1)
                .subscribe(System.out::println);*/
        // reactor
        Flux<String> flux = Flux.just("go")
                .delayElements(Duration.ofSeconds(10)).map(String::toUpperCase);
        // reactor非阻塞订阅
        flux.subscribe(System.out::println);

    }

}