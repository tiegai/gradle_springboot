package org.example.chain.Util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

/*    public static void main(String args[]){

        List<String> listTest = new ArrayList<>() ;
        listTest.add("a");
        listTest.add("b");
        listTest.add("c");
        listTest.add("d");
        listTest.add("e");
        listTest.add("f");
        listTest.add("g");

        List<List<String>> listTestArray = splitList(listTest,3);

        System.out.println(listTestArray);

    }*/

    public static <T> List<List<T>> splitList(List<T> list, int size){
        List<List<T>> listArray = new ArrayList<List<T>>();
        List<T> subList = null;
        for(int i=0;i<list.size();i++){
            //每次达到边界大小就重新申请一个sublist
            if(i%size == 0){
                subList = new ArrayList<T>();
                listArray.add(subList);
            }
            subList.add(list.get(i));
        }
        return listArray;
    }
}
