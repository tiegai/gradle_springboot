package org.example.jdk.old_jdk;

public class TreeImpl {

    public static void main(String[] args) {

        TreeInterface tree = new TreeInterface() {
            @Override
            public void add(int data) {
                System.out.println("添加节点：" + data);
            }

            @Override
            public void delete(int data) {
                System.out.println("删除节点：" + data);
            }
        };

        tree.add(1);
        tree.delete(1);
    }
}
