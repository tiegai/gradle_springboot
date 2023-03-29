package org.example.chain.Util;

import org.example.chain.Execute;
import org.example.chain.entity.MessageEntity;

import java.util.List;
import java.util.concurrent.*;

public class ThreadUtil {

    public void doProcessList(List<List<MessageEntity>> list, List<Integer> nodeList){

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 50, 4, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());

        threadPoolExecutor.execute(() -> {
            for (int i = 0; i < list.size(); i++) {
                System.out.println("提交任务" + i);
                try {
                    Execute execute = new Execute();
                    execute.execute(list.get(i),nodeList);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        threadPoolExecutor.shutdown();

    }

    private void doProcessSubList(List<MessageEntity> subList){

        //List<T> updateList = new ArrayList<>();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 50, 4, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());

        threadPoolExecutor.execute(() -> {
            for (int i = 0; i < subList.size(); i++) {
                System.out.println("提交任务" + i);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        threadPoolExecutor.shutdown();

    }

}
