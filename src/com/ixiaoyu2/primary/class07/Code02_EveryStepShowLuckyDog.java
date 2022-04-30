package com.ixiaoyu2.primary.class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @author :Administrator
 * @date :2022/3/16 0016
 * @description :com.msb.primary.class07
 * @version: 1.0
 */
public class Code02_EveryStepShowLuckyDog {

    public static List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        LuckyDogs luckyDogs = new LuckyDogs(k);
        for (int i = 0; i < arr.length; i++) {
            luckyDogs.operate(i, arr[i], op[i]);
            List<Integer> luckDogsNum = luckyDogs.getLuckDogsNum();
            ans.add(luckDogsNum);
        }
        return ans;
    }

    private static class LuckyDogs {
        int limit;
        HeapGreater<Customer> luckDogArea;
        HeapGreater<Customer> candidateArea;
        HashMap<Integer, Customer> customers;

        public LuckyDogs(int limit) {
            this.limit = limit;
            luckDogArea = new HeapGreater<>(new LuckyDogComparator());
            candidateArea = new HeapGreater<>(new CandidateComparator());
            customers = new HashMap<>(16);
        }

        public void operate(int time, int id, boolean op) {
            if (!op && !customers.containsKey(id)) {
                return;
            }
            if (!customers.containsKey(id)) {
                customers.put(id, new Customer(id, 0, 0));
            }
            Customer customer = customers.get(id);
            if (op) {
                customer.buy++;
            } else {
                customer.buy--;
            }

            if (!candidateArea.contains(customer) && !luckDogArea.contains(customer)) {
                customer.enterTime = time;
                if (luckDogArea.size() < limit) {
                    luckDogArea.push(customer);
                } else {
                    candidateArea.push(customer);
                }
            } else if (luckDogArea.contains(customer)) {
                if (customer.buy == 0) {
                    luckDogArea.remove(customer);
                    customers.remove(id);
                } else {
                    luckDogArea.resign(customer);
                }
            } else {
                if (customer.buy == 0) {
                    candidateArea.remove(customer);
                    customers.remove(id);
                } else {
                    candidateArea.resign(customer);
                }
            }
            luckDogCheck(time);
        }

        private void luckDogCheck(int time) {
            if (candidateArea.isEmpty()) {
                return;
            }
            if (luckDogArea.size() < limit) {
                Customer c = candidateArea.pop();
                c.enterTime = time;
                luckDogArea.push(c);
            } else {
                if (candidateArea.peek().buy > luckDogArea.peek().buy) {
                    Customer oldLuckDog = luckDogArea.pop();
                    Customer newLuckDog = candidateArea.pop();
                    oldLuckDog.enterTime = time;
                    newLuckDog.enterTime = time;
                    luckDogArea.push(newLuckDog);
                    candidateArea.push(oldLuckDog);
                }
            }


        }

        public List<Integer> getLuckDogsNum() {
            List<Customer> luckDogs = luckDogArea.getAllElements();
            List<Integer> ans = new ArrayList<>(luckDogs.size());
            luckDogs.forEach((o) -> ans.add(o.id));
            return ans;
        }
    }

    private static class Customer {
        int id;
        int buy;
        int enterTime;

        public Customer(int id, int buy, int enterTime) {
            this.id = id;
            this.buy = buy;
            this.enterTime = enterTime;
        }
    }

    /**
     * 候选去比较规则，购买数量多的排前面，数量相等，先进入候选区的排前面（排前面的先进入获奖区）
     */
    private static class CandidateComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buy != o2.buy ? (o2.buy - o1.buy) : (o1.enterTime - o2.enterTime);
        }
    }

    /**
     * 获奖区规则，购买数量少的排前面，数量相等，先进入候选区的排前面（排前面的先离开获奖区）
     */
    private static class LuckyDogComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buy != o2.buy ? (o1.buy - o2.buy) : (o1.enterTime - o2.enterTime);
        }
    }

    /*以下代码为对数器，用于测试*/
    public static List<List<Integer>> comparator(int[] arr, boolean[] op, int k) {
        HashMap<Integer, Customer> customers = new HashMap<>(16);
        List<Customer> luckDogArea = new ArrayList<>();
        List<Customer> candidateArea = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int id = arr[i];
            boolean buy = op[i];
            if (!buy && !customers.containsKey(id)) {
                ArrayList<Integer> list = new ArrayList<>();
                luckDogArea.forEach((o) -> list.add(o.id));
                ans.add(list);
                continue;
            }
            if (!customers.containsKey(id)) {
                customers.put(id, new Customer(id, 0, 0));
            }
            Customer customer = customers.get(id);
            if (buy) {
                customer.buy++;
            } else {
                customer.buy--;
            }
            if (!luckDogArea.contains(customer) && !candidateArea.contains(customer)) {
                customer.enterTime = i;
                if (luckDogArea.size() < k) {
                    luckDogArea.add(customer);
                } else {
                    candidateArea.add(customer);
                }
            } else if (luckDogArea.contains(customer)) {
                if (customer.buy == 0) {
                    luckDogArea.remove(customer);
                    customers.remove(id);
                }
                luckDogArea.sort(new LuckyDogComparator());
            } else {
                if (customer.buy == 0) {
                    candidateArea.remove(customer);
                    customers.remove(id);
                }
                candidateArea.sort(new CandidateComparator());
            }


            checkLuckDog(candidateArea, luckDogArea, k, i);

            List<Integer> list = new ArrayList<>();
            luckDogArea.forEach((o) -> list.add(o.id));
            ans.add(list);
        }
        return ans;
    }

    public static void checkLuckDog(List<Customer> candidateArea, List<Customer> luckDogArea, int k, int time) {
        if (candidateArea.isEmpty()) {
            return;
        }
        // 候选区不为空
        if (luckDogArea.size() < k) {
            Customer c = candidateArea.get(0);
            c.enterTime = time;
            luckDogArea.add(c);
            candidateArea.remove(0);
        } else { // 等奖区满了，候选区有东西
            if (candidateArea.get(0).buy > luckDogArea.get(0).buy) {
                Customer oldDaddy = luckDogArea.get(0);
                luckDogArea.remove(0);
                Customer newDaddy = candidateArea.get(0);
                candidateArea.remove(0);
                newDaddy.enterTime = time;
                oldDaddy.enterTime = time;
                luckDogArea.add(newDaddy);
                candidateArea.add(oldDaddy);
            }
        }
    }

    public static Data randomData(int maxLength, int maxNum) {
        int[] arr = new int[(int) (Math.random() * maxLength) + 1];
        boolean[] op = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxNum);
            op[i] = Math.random() < 0.5;
        }
        return new Data(arr, op);
    }


    private static boolean sameAnswer(List<List<Integer>> list1, List<List<Integer>> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            List<Integer> cur1 = list1.get(i);
            List<Integer> cur2 = list2.get(i);
            if (cur1.size() != cur2.size()) {
                return false;
            }
            cur1.sort((o1, o2) -> o1 - o2);
            cur2.sort((o1, o2) -> o1 - o2);

            for (int i1 = 0; i1 < cur1.size(); i1++) {
                if (!cur1.get(i1).equals(cur2.get(i1))) {
                    return false;
                }
            }
        }
        return true;
    }


    private static class Data {
        public int[] arr;
        public boolean[] op;

        public Data(int[] arr, boolean[] op) {
            this.arr = arr;
            this.op = op;
        }
    }

    public static void main(String[] args) {
        int maxValue = 10;
        int maxLen = 100;
        int maxK = 6;
        int testTimes = 100000;
        System.out.println("测试开始");

        for (int i = 0; i < testTimes; i++) {
            Data testData = randomData(maxValue, maxLen);
            int k = (int) (Math.random() * maxK) + 1;
            int[] arr = testData.arr;
            boolean[] op = testData.op;
            List<List<Integer>> ans1 = topK(arr, op, k);
            List<List<Integer>> ans2 = comparator(arr, op, k);
            try {
                if (!sameAnswer(ans1, ans2)) {
                    for (int j = 0; j < arr.length; j++) {
                        System.out.println(arr[j] + " , " + op[j]);
                    }
                    System.out.println(k);
                    System.out.println(ans1);
                    System.out.println(ans2);
                    System.out.println("出错了！");
                    break;
                }
            } catch (Exception e) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.println(arr[j] + " , " + op[j]);
                }
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了！");
                e.printStackTrace();
                break;
            }
        }
        System.out.println("测试结束");
    }
}
