package org.example.asyncDirectly;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Component
@Slf4j
public class AsyncTestTask {
    public static Random random = new Random();

    // 返回结果的异步调用
    @Async
    public Future<String> pay() throws InterruptedException {
        log.info("开始做异步返回结果任务：支付");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(5000));
        long end = System.currentTimeMillis();
        log.info("支付，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("会员服务完成");
    }

    @Async
    public CompletableFuture<String> paySuccess() throws InterruptedException {
        log.info("开始做异步返回结果任务：支付");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(5000));
        long end = System.currentTimeMillis();
        log.info("支付，耗时：" + (end - start) + "毫秒");

        return new CompletableFuture<>();
    }

    @Async
    public void sendSms() throws InterruptedException {
        log.info("开始做异步返回结果任务：发送短信");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("发送短信，耗时：" + (end - start) + "毫秒");
    }

    /**
     * 会员积分任务
     * @throws InterruptedException
     */
    @Async
    public void vip() throws InterruptedException {
        log.info("开始做异步返回结果任务：会员积分");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(20000));
        long end = System.currentTimeMillis();
        log.info("会员积分，耗时：" + (end - start) + "毫秒");
    }
}

