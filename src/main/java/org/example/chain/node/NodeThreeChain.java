package org.example.chain.node;

import org.example.chain.common.CheckChain;
import org.example.chain.entity.MessageEntity;

import java.util.List;

public class NodeThreeChain extends CheckChain {

    @Override
    public boolean proceed(List<MessageEntity> entityList) {
        for (MessageEntity entity : entityList) {
            if (entity.getVip().equals("1")) {
                System.out.println(entity.getName()+"-"+"节点3-执行VIP用户的需求...");
                // TODO
            } else {
                System.out.println(entity.getName()+"-"+"节点3-执行非VIP用户的需求...");
                // TODO
            }
        }
        return checker.proceed(entityList);
    }
}
