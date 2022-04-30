package com.ixiaoyu2.rookie.class02;

/**
 * @Author :Administrator
 * @Date :2022/2/18
 * @Description :com.msb.rookie.class02
 * @Version: 1.0
 */
public class RandToRand {
    public static void main(String[] args) {


        int testTimes = 1000000;
//        double x = 0.7;
//
//        int count1 = 0;
//        for (int i = 0; i < testTimes; i++) {
//            if (Math.random() < x) {
//                count1++;
//            }
//        }
//        System.out.println((double) count1 / testTimes);
//        System.out.println("--------------------------------------------------");
//        //通过以上验证，Math.random()等概率返回[0，1)区间上的小数
//        //那么返回在[0，x)区间（x<=1)里的数的概率为x
//        int count2 = 0;
//        for (int i = 0; i < testTimes; i++) {
//            if (xToPower2() < x) {
//                count2++;
//            }
//        }
//        System.out.println((double) count2 / testTimes);
//
//        System.out.println("--------------------------------------------------");
//        int count3 = 0;
//        for (int i = 0; i < testTimes; i++) {
//            if (xToPowerX() < x) {
//                count3++;
//            }
//        }
//        System.out.println((double) count3 / testTimes);
//        System.out.println((double) 1 - Math.pow(1 - x, 2));
//        System.out.println("--------------------------------------------------");
        int[] counts = new int[8];
        for (int i = 0; i < testTimes; i++) {
            int r = random7();
            for (int i1 = 0; i1 < counts.length; i1++) {
                if (r == i1) {
                    counts[i1] = 1 + counts[i1];
                }
            }
        }
        System.out.println(counts[0]);
        System.out.println(counts[1]);
        System.out.println(counts[2]);
        System.out.println(counts[3]);
        System.out.println(counts[4]);
        System.out.println(counts[5]);
        System.out.println(counts[6]);
        System.out.println(counts[7]);

    }

    /**
     * 以x^2概率等概率返回[0,x)上的数
     *
     * @return double
     */
    public static double xToPower2() {
        return Math.max(Math.random(), Math.random());
    }

    public static double xToPowerX() {
        return Math.min(Math.random(), Math.random());
    }

    /**
     * 一个随机数发生器random5()，等概率生成1-5之间的数，仅适用random5(),生成一个等概率生成1-7之间数的随机数生成器random7()
     */
    public static int random7() {
        int num;
        do {
            num = (randomToRandom() << 2) + (randomToRandom() << 1) + randomToRandom();
        } while (num == 0);
        return num;
    }

    /**
     * 根据random5()随机生成器生成等概率的0,1生成器
     *
     * @return 等概率返回0和1
     */
    private static int randomToRandom() {
        int i = 0;
        do {
            i = random5();
        } while (i == 3);
        return i < 3 ? 0 : 1;
    }


    /**
     * 随机数发生器random5()，等概率生成1-5之间的数
     *
     * @return 等概率返回1-5之间的数
     */
    private static int random5() {
        return (int) (Math.random() * 5) + 1;
    }
}
