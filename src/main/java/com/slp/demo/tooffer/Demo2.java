package com.slp.demo.tooffer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author sanglp
 * @create 2018-12-27 14:14
 * @desc 给定一个长度为 n+1 的数组 nums，数组中所有的数均在 1∼n 的范围内，其中 n≥1。  请找出数组中任意一个重复的数，但不能修改输入的数组。
 **/
public class Demo2 {
    static Log logger = LogFactory.getLog(Demo2.class);
    public static void main(String[] args) {

        int [] a = new int[]{1,2,5,1,2,7};
        solution1(a);
        solution2(a);
    }

    /**
     *创建长度为 n+1 的辅助数组，把原数组的元素复制到辅助数组中。如果原数组被复制的数是 m，则放到辅助数组第 m 个位置。这样很容易找出重复元素。空间复杂度为 O(n)。
     * @param nums
     * @return
     */
    private static int solution1(int [] nums){
        int n = nums.length;
        int [] nums2 = new int[n+1];
        for(int i=0;i<nums.length;i++){
            int a = nums[i];
            if(nums2[a]+"" != ""){
                logger.info("重复的元素是："+a);
               return a;
            }else{
                nums2[a] =a;
            }

        }
        return -1;
    }

    /**
     * 数组元素的取值范围是 [1, n]，对该范围对半划分，分成 [1, middle], [middle+1, n]。计算数组中有多少个(count)元素落在 [1, middle] 区间内，如果 count 大于 middle-1+1，那么说明这个范围内有重复元素，否则在另一个范围内。继续对这个范围对半划分，继续统计区间内元素数量。
     *
     * 时间复杂度 O(n * log n)，空间复杂度 O(1)。
     * @param nums
     * @return
     */
    private static int solution2(int [] nums){

        if (nums == null || nums.length < 2) {
            return 0;
        }
        int start = 1;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            int cnt = getCountRange(nums, start, mid);
            if (start == end) {
                if (cnt > 1) {
                    // 找到重复的数字
                    logger.info("重复数字是："+start);
                    return start;
                }
                break;
            }
            if (cnt > mid - start + 1) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return 0;
    }

    /**
     * 计算整个数组中有多少个数的取值在[from, to] 之间
     *
     * @param nums 数组
     * @param from 左边界
     * @param to 右边界
     * @return 数量
     */
    private  static int getCountRange(int[] nums, int from, int to) {
        int cnt = 0;
        for (int e : nums) {
            if (e >= from && e <= to) {
                ++cnt;
            }
        }
        return cnt;
    }
}
