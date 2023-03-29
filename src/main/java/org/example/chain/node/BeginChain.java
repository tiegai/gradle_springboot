package org.example.chain.node;

import org.example.chain.common.CheckChain;
import org.example.chain.entity.MessageEntity;

import java.util.List;

public class BeginChain extends CheckChain {

    @Override
    public boolean proceed(List<MessageEntity> entityList) {
        System.out.println("开始");
        return checker.proceed(entityList);
    }
}

