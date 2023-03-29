package org.example.chain.node;

import org.example.chain.common.CheckChain;
import org.example.chain.entity.MessageEntity;

import java.util.List;

public class NodeTwoChain extends CheckChain {

    @Override
    public boolean proceed(List<MessageEntity> entityList) {
        for (MessageEntity entity : entityList) {
            if (entity.getAge() > 30) {
                System.out.println(entity.getName()+"-"+"节点2-执行年龄在30岁以上的用户的需求...");
                //TODO
            } else {
                System.out.println(entity.getName()+"-"+"节点2-执行年龄在30岁以下包含30岁的用户的需求...");
                //TODO
            }
        }
        return checker.proceed(entityList);
    }
}
