package org.example.asyncDirectly;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncTestService {

    @Async
    public void test(){
        try{
            System.out.println(Thread.currentThread().getName() + "in test , before sleep." );
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName() + "in test , after sleep." );
        } catch (InterruptedException e) {
            System.out.println("sleep error.");
        }
    }
}
