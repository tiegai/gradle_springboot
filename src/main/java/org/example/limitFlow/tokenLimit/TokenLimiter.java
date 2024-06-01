package org.example.limitFlow.tokenLimit;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TokenLimiter {

    /**
     * 令牌
     */
    private static final String TOKEN = "token";

    /**
     * 阻塞队列，线程安全，非公平容量有限
     */
    private ArrayBlockingQueue<String> arrayBlockingQueue;

    /**
     * 令牌桶容量
     */
    private int limit;

    /**
     * 令牌每次生成的数量
     */
    private int amount;

    /**
     * 令牌桶间隔时间——单位时间
     */
    private int period;

    public TokenLimiter(int limit, int period, int amount) {
        this.limit = limit;
        this.amount = amount;
        this.period = period;
        /**
         * 理解ReentrantLock
         * AQS CAS
         */
        arrayBlockingQueue = new ArrayBlockingQueue<>(limit);
        init();
    }

    /**
     * 初始化生成令牌，按桶容量生成
     */
    private void init() {
        for (int i = 0; i < limit; i++) {
            arrayBlockingQueue.offer(TOKEN);
        }
    }

    /**
     * 生成令牌，周期性线程池，启动后延迟500毫秒，每500毫秒执行一次
     * （可以理解为启动后每隔500毫秒执行一次）
     *
     * @param lock
     */
    public void start(Object lock) {
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            synchronized (lock) {
                // 往令牌桶添加令牌
                addToken();
                lock.notifyAll();
            }
        }, 500, this.period, TimeUnit.MILLISECONDS);
    }

    /**
     * 添加令牌
     */
    private void addToken() {
        for (int i = 0; i < this.amount; i++) {
            // 溢出返回false
            arrayBlockingQueue.offer(TOKEN);
        }
    }

    public boolean tryAcquire() {
        // 队首元素出队
        return arrayBlockingQueue.poll() != null;
    }

}
