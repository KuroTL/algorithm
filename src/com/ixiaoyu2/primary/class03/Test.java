package com.ixiaoyu2.primary.class03;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author :Administrator
 * @Date :2022/3/5
 * @Description :com.msb.primary.class03
 * @Version: 1.0
 */
public class Test {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();

        list.add(5);
        list.add(5);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        list.add(5);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println("\n-------------");

//        System.out.println(list.remove(5));
//
//        for (int i = 0; i < list.size(); i++) {
//            System.out.print(list.get(i) + " ");
//        }
//        System.out.println("\n-------------");
        Integer a = 5;
////        System.out.println(list.remove(a));
////        System.out.println(list.remove(a));
////        System.out.println(list.remove(a));
////        System.out.println(list.remove(a));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)==a){
                list.remove(a);
                System.out.println(list.size());
            }
            System.out.print(list.get(i) + " ");
        }


    }
}
