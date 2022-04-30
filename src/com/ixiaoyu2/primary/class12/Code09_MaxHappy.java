package com.ixiaoyu2.primary.class12;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :Administrator
 * @date :2022/4/8 0008
 */
public class Code09_MaxHappy {
    private static class Employee {
        int happy;
        List<Employee> subordinates;

        public Employee(int happy) {
            this.happy = happy;
            subordinates = new ArrayList<>();
        }
    }

    public static int maxHappy(Employee boss) {
        if (boss == null) {
            return 0;
        }
        Info bossInfo = process(boss);
        return Math.max(bossInfo.noAttendMaxHappy, bossInfo.attendMaxHappy);
    }

    private static class Info {
        int attendMaxHappy;
        int noAttendMaxHappy;

        public Info(int attendMaxHappy, int noAttendMaxHappy) {
            this.attendMaxHappy = attendMaxHappy;
            this.noAttendMaxHappy = noAttendMaxHappy;
        }
    }

    private static Info process(Employee employee) {
        if (employee == null) {
            return new Info(0, 0);
        }
        int attendMaxHappy = employee.happy;
        int noAttendMaxHappy = 0;

        for (Employee subordinate : employee.subordinates) {
            Info info = process(subordinate);
            attendMaxHappy += info.noAttendMaxHappy;
            noAttendMaxHappy += Math.max(info.attendMaxHappy, info.noAttendMaxHappy);
        }
        return new Info(attendMaxHappy, noAttendMaxHappy);
    }


    public static int maxHappy2(Employee boss) {
        if (boss == null) {
            return 0;
        }
        return process2(boss, false);
    }

    private static int process2(Employee cur, boolean attend) {
        if (attend) {
            int ans = 0;
            for (Employee c : cur.subordinates) {
                ans += process2(c, false);
            }
            return ans;
        } else {
            int p1 = cur.happy;
            int p2 = 0;
            for (Employee c : cur.subordinates) {
                p1 += process2(c, true);
                p2 += process2(c, false);
            }
            return Math.max(p1, p2);
        }
    }

    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.subordinates = new ArrayList<>();
            e.subordinates.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy(boss) != maxHappy2(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
