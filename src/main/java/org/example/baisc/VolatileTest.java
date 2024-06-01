package org.example.baisc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileTest {

    // 只能保证可见行
    //public volatile int inc = 0;

    // 不能保证原子性
    /*public void increase() {
        inc++;
    }*/

    // synchronized 保证原子性
    /*public synchronized void increase() {
        inc++;
    }*/

    // Lock 保证原子性
    /*Lock lock = new ReentrantLock();
    public void increase(){
        lock.lock();
        try{
            inc++;
        } finally {
            lock.unlock();
        }
    }*/

    // AtomicInteger 保证原子性
    public AtomicInteger inc = new AtomicInteger();

    public void increase(){
        inc.getAndIncrement();
    }


    public static void main(String[] args) {
        final VolatileTest volatileTest = new VolatileTest();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        volatileTest.increase();
                };
            }.start();
        }

        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(volatileTest.inc);
    }
}
