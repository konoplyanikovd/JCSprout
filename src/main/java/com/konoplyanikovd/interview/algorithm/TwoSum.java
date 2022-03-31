package com.konoplyanikovd.interview.algorithm;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public int[] getTwo1(int[] nums,int target){
        int[] result = null;

        for (int i= 0 ;i<nums.length ;i++){
            int a = nums[i] ;
            for (int j = nums.length -1 ;j >=0 ;j--){
                int b = nums[j] ;

                if (i != j && (a + b) == target) {
                    result = new int[]{i,j} ;
                }
            }
        }
        return result ;
    }

    public int[] getTwo2(int[] nums,int target){
        int[] result = new int[2] ;
        Map<Integer,Integer> map = new HashMap<>(2) ;
        for (int i=0 ;i<nums.length;i++){

            if (map.containsKey(nums[i])){
                result = new int[]{map.get(nums[i]),i} ;
            }
            map.put(target - nums[i],i) ;
        }
        return result ;
    }
}
