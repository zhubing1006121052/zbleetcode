package com.qunar.leetcode;

/**
 *给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 * 示例 1:
 *
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 *
 * 不能更改原数组（假设数组是只读的）。   如果能改，可以循环每个元素的值作为下标，然后将其置为-1,继续下一个，遇到-1返回下标就是重复的值
 * 只能使用额外的 O(1) 的空间。   如果可以使用额外空间   hash表可以解决
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 */
public class FindDuplicate {


    public static void main(String[] args) {
        System.out.println(findDuplicate(new int[]{1,2,3,2,4}));
    }

    public static int findDuplicate(int nums[]){

        //二分法  n+1的数组,值都在1到n之间，首先找到中间的那个数(1+n)/2  看左边的多还是右边多，哪边多肯定就是那边有重复的
        //但是我们要统计全量的数组数据，策略明确之后我们就要知道什么时候推出  什么时候找到
        int start = 1,end = nums.length-1;
        int midlle = 0,cnt=0;
        while(start < end){

            midlle = (start + end)/2;
            for(int i=0;i<nums.length;i++){
                //2,3,1,4   middle=2(小于等于midlle有middle个)  2,3,1,4,5  middle=3(小于等于midlle有middle个)
                if(nums[i] <= midlle){
                    cnt++;
                }
            }
            //
            if(cnt > midlle){
                //左边的有重复  也就是比middle小的有重复
                end = midlle;
            }else{
                start = midlle+1;
            }
            cnt = 0;
        }

        return start;
    }


    public int findDuplicate2(int nums[]){

        for(int i=0;i<nums.length;i++){
            if(nums[i] > 0){
                nums[nums[i]] = -1;
            }else{
                return nums[i];
            }
        }
        return 0;
    }
}
