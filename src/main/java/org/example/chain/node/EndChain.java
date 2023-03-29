package org.example.chain.node;


import org.example.chain.common.CheckChain;
import org.example.chain.entity.MessageEntity;


import java.util.List;

public class EndChain extends CheckChain {

    @Override
    public boolean proceed(List<MessageEntity> entityList) {
        System.out.println("结束");
        return true;
    }
}
