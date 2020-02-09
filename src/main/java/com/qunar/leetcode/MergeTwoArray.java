package com.qunar.leetcode;


/**
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 *
 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 *
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeTwoArray {

    public static void main(String[] args) {

    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //从尾部开始   谁大把谁放到后面
        if(m == 0){
            for(int t=0;t<n;t++){
                nums1[t] = nums2[t];
            }
            return;
        }
        int j = m-1,last=m+n-1;
        for(int i=n-1;i>=0;){
            //j<0的边界问题一定要注意，
            if(j < 0 || nums2[i] >= nums1[j]){
                nums1[last--] = nums2[i--];
            }else{
                //j<0,当j指向nums1第一个元素的时候发现它比nums2中的要大
                //那么就要j指向的当前元素放到最后，然后j向前移(但是j一定是第一个了)
                nums1[last--] = nums1[j--];
            }
        }
    }
}
