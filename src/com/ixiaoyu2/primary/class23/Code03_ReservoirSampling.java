package com.ixiaoyu2.primary.class23;

import java.util.Arrays;

/**
 * @author :Administrator
 * @date :2022/5/17 0017
 */
public class Code03_ReservoirSampling {

    // 假设有一个源源吐出不同球的机器，
    //只有装下10个球的袋子，每一个吐出的球，要么放入袋子，要么永远扔掉
    //如何做到机器吐出每一个球之后，所有吐出的球都等概率被放进袋子里


    private static class RandomBox {
        private int[] bag;
        private int n;
        private int count;

        public RandomBox(int capacity) {
            bag = new int[capacity];
            n = capacity;
            count = 0;
        }


        public int rand(int max) {
            //生成1~max的随机数
            return (int) (Math.random() * max) + 1;
        }

        public void add(int num) {
            count++;
            // 袋子美满 直接进袋子
            if (count <= n) {
                bag[count - 1] = num;
            } else {
                // 袋子满了，n/count的概率进袋子
                if (rand(count) <= n) {
                    // 1/n的概率 一个位置被替换
                    bag[rand(n) - 1] = num;
                }
            }
        }

        public int[] choices() {
            return Arrays.copyOf(bag, n);
        }
    }

    public static int random(int i) {
        // 等概率返回1~i的一个数
        return (int) (Math.random() * i) + 1;
    }

    public static void main(String[] args) {
        System.out.println("hello");
        int test = 10000;
        int ballNum = 17;
        int[] count = new int[ballNum + 1];
        for (int i = 0; i < test; i++) {
            int[] bag = new int[10];
            int bagi = 0;
            for (int num = 1; num <= ballNum; num++) {
                if (num <= 10) {
                    bag[bagi++] = num;
                } else { // num > 10
                    // 一定要把num球入袋子 10/num 的概率进带子
                    if (random(num) <= 10) {
                        bagi = (int) (Math.random() * 10);
                        bag[bagi] = num;
                    }
                }
            }
            for (int num : bag) {
                count[num]++;
            }
        }
        for (int i = 0; i <= ballNum; i++) {
            System.out.println(count[i]);
        }

        System.out.println("hello");
        int all = 100;
        int choose = 10;
        int testTimes = 50000;
        int[] counts = new int[all + 1];
        for (int i = 0; i < testTimes; i++) {
            RandomBox box = new RandomBox(choose);
            for (int num = 1; num <= all; num++) {
                box.add(num);
            }
            int[] ans = box.choices();
            for (int j = 0; j < ans.length; j++) {
                counts[ans[j]]++;
            }
        }

        for (int i = 0; i < counts.length; i++) {
            System.out.println(i + " times : " + counts[i]);
        }

    }
}
