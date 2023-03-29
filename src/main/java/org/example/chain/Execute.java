package org.example.chain;

import org.example.chain.common.CheckChain;
import org.example.chain.common.GenNode;
import org.example.chain.entity.MessageEntity;
import org.example.chain.node.*;

import java.util.List;

public class Execute {

    public void execute(List<MessageEntity> entityList,List nodeList){

        // 默认创建开始和结束节点
        BeginChain beginChain = new BeginChain();
        EndChain endChain = new EndChain();

        //根据模板节点list组装责任链
        CheckChain.Builder builder = new CheckChain.Builder()
                .addChecker(beginChain);

        for(int i =0;i<nodeList.size();i++){
            builder.addChecker(GenNode.getNode((Integer) nodeList.get(i)));
        }

        builder.addChecker(endChain).build();

        /*new CheckChain.Builder()
                .addChecker(beginChain)
                .addChecker(new NodeOneChain())
                .addChecker(new NodeTwoChain())
                .addChecker(new NodeOneChain())
                .addChecker(new NodeTwoChain())
                .addChecker(endChain)
                .build();*/

        // 发起开始执行操作
        boolean result = beginChain.proceed(entityList);
        System.out.println("=======是否成功：" + result + "=======");
    }
}
