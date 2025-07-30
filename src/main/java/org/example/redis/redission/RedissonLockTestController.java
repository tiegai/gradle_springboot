package org.example.redis.redission;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RScheduledExecutorService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redisson")
@Slf4j
public class RedissonLockTestController {

    @Autowired
    private RedissonClient redissonClient;

    @RequestMapping("/lock")
    public void lock() {

        String lockKey = "kingmouse-001";
        // 获取锁对象
        RLock lock = redissonClient.getLock(lockKey);
        // 加锁,尝试获取锁，5秒后获取不到则直接放弃
        lock.lock(5, TimeUnit.SECONDS);

        // 业务处理
        log.info("实际业务处理");

        try {
            System.out.println("阻塞式--加锁成功，线程 ID：" + Thread.currentThread().getId());
        } catch (Exception e) {
            log.error("业务处理异常：{}", e.getMessage());
        } finally {
            // 解锁
            lock.unlock();
            System.out.println("阻塞式--解锁成功，线程 ID：" + Thread.currentThread().getId());
        }

    }

    @RequestMapping("/tryLock")
    public void tryLock() throws InterruptedException {

        // 设置锁的名字，同一个名字为同一把锁,不同名字之间互不干扰
        String lockKey = "kingmouse-002";
        // 获取锁对象
        RLock lock = redissonClient.getLock(lockKey);

        // 尝试获取锁，最长等待5秒，持有锁最长30秒
        boolean isLock = lock.tryLock(5, 30, TimeUnit.SECONDS);

        if (isLock) {
            log.info("业务处理....");
            lock.lock(); // 默认是30秒
            lock.lock(50, TimeUnit.SECONDS); // 50秒后自动解锁
            try {
                System.out.println("非阻塞式--获取锁成功，线程 ID：" + Thread.currentThread().getId());
            } catch (Exception e) {
                log.error("业务处理异常：{}", e.getMessage());
            } finally {
                // 解锁
                lock.unlock();
                System.out.println("非阻塞式--解锁成功，线程 ID：" + Thread.currentThread().getId());
            }
        }
    }

    @RequestMapping("/scheduleTask")
    public void scheduleTask() {
        // 新建一个调度任务
        RScheduledExecutorService executorService = redissonClient.getExecutorService("myExecutorService");

        // Schedule a task to run after 10 seconds
        executorService.schedule(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("Scheduled task executed");
                return "Task completed";
            }
        }, 10, TimeUnit.SECONDS);
    }

}
