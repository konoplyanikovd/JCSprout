package com.konoplyanikovd.interview.red;

import java.util.LinkedList;
import java.util.List;

public class RedPacket {

    private static final int MIN_MONEY = 1;

    private static final int MAX_MONEY = 200 * 100;

    private static final int LESS = -1;

    private static final int MORE = -2;

    private static final int OK = 1;

    private static final double TIMES = 2.1F;

    private int recursiveCount = 0;

    public List<Integer> splitRedPacket(int money, int count) {
        List<Integer> moneys = new LinkedList<>();

        if (MAX_MONEY * count <= money) {
            System.err.println("请调大最小红包金额 MAX_MONEY=[" + MAX_MONEY + "]");
            return moneys ;
        }

        int max = (int) ((money / count) * TIMES);
        max = max > MAX_MONEY ? MAX_MONEY : max;

        for (int i = 0; i < count; i++) {
            int redPacket = randomRedPacket(money, MIN_MONEY, max, count - i);
            moneys.add(redPacket);
            money -= redPacket;
        }

        return moneys;
    }

    private int randomRedPacket(int totalMoney, int minMoney, int maxMoney, int count) {
        if (count == 1) {
            return totalMoney;
        }

        if (minMoney == maxMoney) {
            return minMoney;
        }

        maxMoney = maxMoney > totalMoney ? totalMoney : maxMoney;

        int redPacket = (int) (Math.random() * (maxMoney - minMoney) + minMoney);

        int lastMoney = totalMoney - redPacket;

        int status = checkMoney(lastMoney, count - 1);

        if (OK == status) {
            return redPacket;
        }

        if (LESS == status) {
            recursiveCount++;
            System.out.println("recursiveCount==" + recursiveCount);
            return randomRedPacket(totalMoney, minMoney, redPacket, count);
        }

        if (MORE == status) {
            recursiveCount++;
            System.out.println("recursiveCount===" + recursiveCount);
            return randomRedPacket(totalMoney, redPacket, maxMoney, count);
        }

        return redPacket;
    }

    private int checkMoney(int lastMoney, int count) {
        double avg = lastMoney / count;
        if (avg < MIN_MONEY) {
            return LESS;
        }

        if (avg > MAX_MONEY) {
            return MORE;
        }

        return OK;
    }


    public static void main(String[] args) {
        RedPacket redPacket = new RedPacket();
        List<Integer> redPackets = redPacket.splitRedPacket(20000, 100);
        System.out.println(redPackets);

        int sum = 0;
        for (Integer red : redPackets) {
            sum += red;
        }
        System.out.println(sum);
    }

}
