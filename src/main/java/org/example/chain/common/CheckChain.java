package org.example.chain.common;

import org.example.chain.entity.MessageEntity;

import java.util.List;

public abstract class CheckChain {

    // 当前处理节点
    protected CheckChain checker;

    // 设置下一个处理者
    public void setNextChecker(CheckChain checker) {
        this.checker = checker;
    }

    // 处理方法，每一个处理者要实现该方法
    public abstract boolean proceed(List<MessageEntity> entityList);

    // 使用构造者模式创建
    public static class Builder {
        // 分别记录第一个处理者和下一个处理者，类似于链表结构
        private CheckChain head;
        private CheckChain tail;

        // 添加处理者
        public Builder addChecker(CheckChain chain) {
            if (this.head == null) {
                this.head = this.tail = chain;
                return this;
            }
            // 设置下一个处理者
            this.tail.setNextChecker(chain);
            this.tail = chain;
            return this;
        }

        public CheckChain build() {
            return this.head;
        }
    }
}
