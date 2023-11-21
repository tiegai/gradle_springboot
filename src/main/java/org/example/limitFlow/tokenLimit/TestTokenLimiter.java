package org.example.limitFlow.tokenLimit;

public class TestTokenLimiter {
    final static Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        int period = 500;
        // 创建令牌桶
        TokenLimiter limiter = new TokenLimiter(2, period, 2);
        // 生产令牌
        limiter.start(LOCK);

        // 这里主要是保证让线程池初始先产生2个令牌，
        // 其实init方法里已经加了2个令牌了，而队列长度限定为2，
        // 所以初始时addToken()并不会成功。
        // 如果这里不使用wait和notify配合，可能客户端获取令牌会先执行，打破节奏了
        synchronized (LOCK) {
            // 该线程被阻塞挂起，直到其他线程调用了该共享对象的notify()或者notifyAll()方法，才返回
            LOCK.wait();
        }

        // 启动4个线程，模拟4个客户端各自每隔500ms获取令牌
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                while (true) {
                    String name = Thread.currentThread().getName();
                    if (limiter.tryAcquire()) {
                        System.out.println(name + ":拿到令牌");
                    } else {
                        System.out.println(name + ":没有令牌");
                    }

                    // 如果不sleep  线程没法切到生产的去执行
                    try {
                        Thread.sleep(period);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

