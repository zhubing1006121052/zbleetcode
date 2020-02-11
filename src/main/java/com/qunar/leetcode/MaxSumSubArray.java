package com.qunar.leetcode;

/**
 *给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxSumSubArray {

    public static void main(String[] args) {

    }


    /**
     * [-2,1,-3,4,-1,2,1,-5,4]
     * 分析：  首先我们在循环中肯定要记录最大值,然后我们还要知道从哪开始累加到
     * 第一步：我们从1加上之前的,也就是-2,-2+1<1,那我们不能加上之前的，会拖累最大和,我们必须从1开始累加之后的
     *         而且我们暂定1就是我们的最大和,其实我们可以记录下此时的下标为1,可以理解我子序列的开始和结束都是这
     *         但是后面还会加,所以结束下标还会移动
     * 第二步：我们再次从-3加上之前的,之前的已经从1开始了,1-3>-3,加起来比我当前这个-3大,那就需要之前的累加结果帮忙
     *         但是累加的1-3<1小于之前的最大和1,所以我们不该变最大和，累加值我们是改变的,因为加上之前的使我现在的结果变大了
     *         如果后面还有累加,那么可能还会增大
     * 第三步：我们再次从4加上之前的,1-3+4<4之前的累加结果帮助不了我们,丢弃掉,那我们现在从4开始累加了
     *         而且这个4也是大于之前的最大和1的，所以最大和也要变化了
     *第四步：我们再次从-1加上之前的,4-1<4当前元素-1使我们的累加结果变小了，累加结果还是要的，
     *         都使最大和变小了，肯定不比最大和4大，不用比较了
     *第五步：我们再次从2加上之前的4-1+2>4,这次的2加上之前的累计结果更大了，所以最大和变成了5,累加值也是5
     *第六步：我们再次从1加上之前的4-1+2+1>5,这次的1加上之前的累计结果更大了，所以最大和变成了6,累加值也是6
     *第七步：我们再次从-5加上之前的4-1+2+1-5<6,是结果变小了,最大和不变还是6,但是累加值变了6-5=1,因为后边可能有大元素
     *第八步：我们再次从4加上之前的4-1+2+1-5+4<6,是结果是比之前变大了，但是还是小于最大和6,累加值确实变大为5了
     * .......没有了，所以最大和就是6
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        //[-2,1,-3,4,-1,2,1,-5,4]   [-1,-2]
        if(nums.length == 0){
            return 0;
        }
        int maxSum = nums[0];//最大和
        int total = nums[0];//累计
        for(int i=1;i<nums.length;i++){
            if(nums[i] >= total + nums[i]){
                //之前的累加结果拖后腿了(但是可能)，累加值从现在这个元素开始
                //=也从新累计说明之前的结果为0，帮不了什么
                total = nums[i];
//                if(total > maxSum){
//                    maxSum = total;
//                }
            }else{
                //之前的结果使累加值变大了，就是加上当前元素越来越大了
                total += nums[i];
//                if(total > maxSum){
//                    //虽然之前的结果使累加值变大了，但是如果累加值大于之前的最大和，就替换一下，否则还是使用之前的
//                    maxSum = total;
//                }
            }
            //累加值都变化了  看是否大于之前的最大和
            if(total > maxSum){
               maxSum = total;
            }
        }
        return maxSum;
    }
}