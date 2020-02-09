package com.qunar.leetcode;

/**
 *缺失数字
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 *
 * 示例 1:
 *
 * 输入: [3,0,1]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [9,6,4,2,3,5,7,0,1]
 * 输出: 8
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/missing-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MissingNumber {

    public static void main(String[] args) {

        System.out.println(missingNumber2(new int[]{0,1,2}));
    }

    public static int missingNumber2(int[] nums) {
        int sum = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum += i;
            sum -= nums[i - 1];
        }
        return sum;
    }
    /**
     *注意 此处这样计算也是不行的，因为累加可能会导致溢出Integer最大值
     * 为什么 [0] 返回的是1 ???
     * @param nums
     * @return
     */
    public static int missingNumber(int[] nums) {

        if(nums.length == 0){
            return 0;
        }
        int max = nums[0],sum=nums[0];
        for(int i=1;i<nums.length;i++){
            if(nums[i] > max){
                max = nums[i];
            }
            sum += nums[i];
        }
        int m=0,n=max,rsum=0;
        while(m < n){
            rsum += m++ + n--;
        }
        if(m == n){
            rsum += n;
        }
        return rsum - sum;
    }
}
