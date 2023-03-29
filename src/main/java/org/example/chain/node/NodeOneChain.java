package org.example.chain.node;

import org.example.chain.common.CheckChain;
import org.example.chain.entity.MessageEntity;

import java.util.List;

public class NodeOneChain extends CheckChain {

    @Override
    public boolean proceed(List<MessageEntity> entityList) {
        // TODO 单独起一个微服务进行集群处理
        for (MessageEntity entity : entityList) {
            switch (entity.getGender()){
                case "0":
                    System.out.println(entity.getName()+"-"+"节点1-执行男性用户的需求...");
                    // TODO
                    break;
                case "1":
                    System.out.println(entity.getName()+"-"+"节点1-执行女性用户的需求...");
                    // TODO
                    break;
                default:
                    System.out.println(entity.getName()+"-"+"节点1-跳过执行...");
                    break;
            }
        }
        return checker.proceed(entityList);
    }
}
