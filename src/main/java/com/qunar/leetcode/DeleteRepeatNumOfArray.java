package com.qunar.leetcode;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 *
 * 给定数组 nums = [1,1,2],
 *
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 *
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 *
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DeleteRepeatNumOfArray {


    public static void main(String[] args) {

    }

    /**
     * [0,0,1,1,1,2,2,3,3,4]
     * 思想：使用三个指针
     * 指针i:指向要比较的元素
     * 指针j:指向i的下一个位置,总是等于i+1
     * 指针k:不断迭代往下走，然后把值付给j，知道j的元素不等于i
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        int i=0,j=1,finalLength=nums.length;
        for(int k=1;k<nums.length;k++){
            nums[j] = nums[k];
            if(nums[j] == nums[i]){
                finalLength--;
                if(j == nums.length - 1){
                    //最后一个
                    continue;
                }
                //nums[j] = nums[++k];
            }else{
                i++;
                j++;
                //nums[j] = nums[++k];
            }
        }
        return finalLength;
    }
}
