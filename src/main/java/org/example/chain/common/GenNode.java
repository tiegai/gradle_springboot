package org.example.chain.common;

import org.example.chain.node.NodeOneChain;
import org.example.chain.node.NodeThreeChain;
import org.example.chain.node.NodeTwoChain;

/**
 *  生成节点类，每增加一个节点，需要添加一个case
 */
public class GenNode {

    public static CheckChain getNode(Integer nodeNum){

        switch(nodeNum){
            case 1:
                return new NodeOneChain();

            case 2:
                return new NodeTwoChain();

            case 3:
                return new NodeThreeChain();

            default:
                return null;
        }
    }
}
