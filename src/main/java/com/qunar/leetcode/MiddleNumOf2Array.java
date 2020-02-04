package com.qunar.leetcode;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 *
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/submissions/
 */
public class MiddleNumOf2Array {

    public static void main(String[] args) {

        System.out.println(findMedianSortedArrays_v1(new int[]{1,2},new int[]{3,4}));
        System.out.println(findMedianSortedArrays_v2(new int[]{1,2},new int[]{3,4}));
    }

    /**
     * 这个做法是将两个数组排序，然后求出中位数
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays_v1(int[] nums1, int[] nums2) {
        int merge[] = new int[nums1.length + nums2.length];
        int i=0,j=0,index=0;
        while(i < nums1.length || j < nums2.length){
            if(i < nums1.length && j < nums2.length){
                if(nums1[i] < nums2[j]){
                    merge[index] = nums1[i++];
                }else{
                    merge[index] = nums2[j++];
                }
            }else if(i < nums1.length && !(j < nums2.length)){
                merge[index] = nums1[i++];
            }else if(!(i < nums1.length) && j < nums2.length){
                merge[index] = nums2[j++];
            }
            index++;
        }
        return merge.length % 2 == 0 ? 1.0d*(merge[merge.length/2] + merge[merge.length/2 - 1])/2 : merge[merge.length/2];
    }

    /**
     * 感觉没有必要将两个数组合并完
     * 比如数组1：[1,3,5,6],数组2：[2,4,8.9]  总长度为8 那么我们合并到地5个就行了，然后第四和第五个元素相加除2
     * 如果是数组1：[1,3,5,6],数组2：[2,4,8]  总长度为7 那么找到第4个就是中位数
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays_v2(int[] nums1, int[] nums2) {

        int middle = (nums1.length + nums2.length) / 2;
        boolean lengthOushu = (nums1.length + nums2.length) % 2 == 0;
        int middleIndex = lengthOushu ? middle - 1 : middle;
        int i=0,j=0,index=0;
        double sum = 0;
        int tmp = 0;
        while(i < nums1.length || j < nums2.length){
            if(i < nums1.length && j < nums2.length){
                if(nums1[i] < nums2[j]){
                    tmp = nums1[i++];
                }else{
                    tmp = nums2[j++];
                }
            }else if(i < nums1.length && !(j < nums2.length)){
                tmp = nums1[i++];
            }else if(!(i < nums1.length) && j < nums2.length){
                tmp = nums2[j++];
            }
            if(sum > 0){
                sum += tmp;
                break;
            }
            if(index == middleIndex){
                if(lengthOushu){
                    //长度为偶数
                    sum = tmp;
                }else{
                    return tmp*1.0d;
                }
            }
            index++;
        }
        return sum/2;
    }
}
