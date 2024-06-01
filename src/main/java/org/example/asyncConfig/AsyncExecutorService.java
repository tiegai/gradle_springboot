package org.example.asyncConfig;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@Service
public class AsyncExecutorService {

    public static Random random = new Random();

    @Autowired
    private AsyncTask asyncTask;

    public void doShop() {
        try {
            createOrder();
            // 调用有结果返回的异步任务
            Future<String> pay = asyncTask.pay();
            // 判断线程是否完成,需要等待返回结果
//            if (pay.isDone()) {
                try {
                    String result = pay.get();
                    System.out.println("异步任务返回结果:"+result);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                asyncTask.vip();
                asyncTask.sendSms();
//            }
        } catch (InterruptedException e) {
            System.out.println("异常:" + e);
        }
    }

    public void createOrder() {
        System.out.println("开始做任务1：下单成功");
    }
}
