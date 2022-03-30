package com.konoplyanikovd.algorithm;

public class BloomFilters {

    private int arraySize;

    private int[] array;

    public BloomFilters(int arraySize) {
        this.arraySize = arraySize;
        array = new int[arraySize];
    }

    public void add(String key) {
        int first = hashcode_1(key);
        int second = hashcode_2(key);
        int third = hashcode_3(key);

        array[first % arraySize] = 1;
        array[second % arraySize] = 1;
        array[third % arraySize] = 1;

    }

    public boolean check(String key) {
        int first = hashcode_1(key);
        int second = hashcode_2(key);
        int third = hashcode_3(key);

        int firstIndex = array[first % arraySize];
        if (firstIndex == 0) {
            return false;
        }

        int secondIndex = array[second % arraySize];
        if (secondIndex == 0) {
            return false;
        }

        int thirdIndex = array[third % arraySize];
        if (thirdIndex == 0) {
            return false;
        }

        return true;

    }


    private int hashcode_1(String key) {
        int hash = 0;
        int i;
        for (i = 0; i < key.length(); ++i) {
            hash = 33 * hash + key.charAt(i);
        }
        return Math.abs(hash);
    }


    private int hashcode_2(String data) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < data.length(); i++) {
            hash = (hash ^ data.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return Math.abs(hash);
    }


    private int hashcode_3(String key) {
        int hash, i;
        for (hash = 0, i = 0; i < key.length(); ++i) {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
        return Math.abs(hash);
    }


    public static void main(String[] args) {
        BloomFilters bloomFilter = new BloomFilters(10000);
        long hashcode = bloomFilter.hashcode_1("1");
        long hashcode2 = bloomFilter.hashcode_1("2");
        System.out.println(hashcode);
        System.out.println(hashcode2);
        System.out.println("=========");
        long hashcode3 = bloomFilter.hashcode_2("1");
        long hashcode4 = bloomFilter.hashcode_2("100");
        System.out.println(hashcode3);
        System.out.println(hashcode4);
        System.out.println("=========");

        long hashcode5 = bloomFilter.hashcode_3("1");
        long hashcode6 = bloomFilter.hashcode_3("100");
        System.out.println(hashcode5);
        System.out.println(hashcode6);
        System.out.println("=========");


        bloomFilter.add("12345");
        bloomFilter.add("100");
        bloomFilter.add("1000");


        boolean check = bloomFilter.check("9000");
        System.out.println("check=" + check);

        boolean check1 = bloomFilter.check("12345");
        System.out.println("check1=" + check1);
    }
}
