package com.slp.demo.tooffer;

import org.apache.commons.logging.LogFactory;

import org.apache.commons.logging.Log;

import java.util.Arrays;


/**
 * @author sanglp
 * @create 2018-12-27 13:48
 * @desc 给定一个长度为 n 的整数数组 nums，数组中所有的数字都在 0∼n−1 的范围内。
 *
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 *
 * 请找出数组中任意一个重复的数字。
 *
 * 注意：如果某些数字不在 0∼n−1 的范围内，或数组中不包含重复数字，则返回 -1；
 * 样例
 *
 * 给定 nums = [2, 3, 5, 4, 3, 2, 6, 7]。
 *
 * 返回 2 或 3。
 **/
public class Demo1 {

    static Log logger = LogFactory.getLog(Demo1.class);
    public static void main(String[] args) {
       int[] a = new int[]{2,3,1,4,2,1};
       solution3(a);
       solution1(a);
    }

    /**
     * 排序后，顺序扫描，判断是否有重复，时间复杂度为O(n2)
     */
    public static int solution1(int [] nums){
        if(nums == null || nums.length < 2){
            logger.info("数组为空或数组长度小于2");
            return -1;
        }
        Arrays.sort(nums);
        for (int i=0;i<nums.length;i++){
            logger.info("i="+nums[i]);
            if(nums[i]==nums[i+1]){
                logger.info("重复元素为"+nums[i]);
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * 利用哈希表，遍历数组，如果哈希表中没有该元素，则存入哈希表中，否则返回重复的元素。时间复杂度为 O(n)，空间复杂度为 O(n)。
     */
    public static void solution2(int [] nums){

    }

    /**
     * 长度为 n，元素的数值范围也为 n，如果没有重复元素，那么数组每个下标对应的值与下标相等。
     *
     * 从头到尾遍历数组，当扫描到下标 i 的数字 nums[i]：
     *
     * 如果等于 i，继续向下扫描；
     * 如果不等于 i，拿它与第 nums[i] 个数进行比较，如果相等，说明有重复值，返回 nums[i]。如果不相等，就把第 i 个数 和第 nums[i] 个数交换。重复这个比较交换的过程。
     * 此算法时间复杂度为 O(n)，因为每个元素最多只要两次交换，就能确定位置（比如把 2 跟 5 交换，此时 2 在正确的位置，而 5 需要再交换一次就能跑到正确的位置）。空间复杂度为 O(1)。
     */
    public static int solution3(int [] nums){

        if(nums == null || nums.length < 2){
            logger.info("数组为空或数组长度小于2");
            return -1;
        }

        int n = nums.length;
        for (int e: nums) {
            if(e<0 || e>n-1){
                logger.info("元素小于0或者大于数组长度");
                return -1;
            }
        }
        for (int i = 0; i < n; ++i) {
            while (nums[i] != i) {
                int val = nums[nums[i]];
                if (nums[i] == val) {
                    logger.info("重复元素为"+val);
                    return val;
                }
                swap(nums, i, nums[i]);
            }
        }
        return -1;
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
