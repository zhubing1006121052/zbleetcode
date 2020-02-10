package com.qunar.leetcode;

import java.util.*;

/**
 * 数组交集分两版，
 * 简单版：
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 * 示例 2:
 *
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 * 说明:
 *
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
 *
 * 进阶版：
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 示例 2:
 *
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * 说明：
 *
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IntersectionOfArray {

    public static void main(String[] args) {

        System.out.println(intersection2(new int[]{9,2,4,8,4},new int[]{4,9,5}));
    }

    /**
     * 简单版
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        //两个数组中同时都出现的元素
        if(nums1.length == 0 || nums2.length == 0){
            return new int[]{};
        }
        Set<Integer> set = new HashSet<Integer>();
        Set<Integer> intersection = new HashSet<Integer>();
        int[] setArray = nums2;
        int[] forArray = nums1;
        if(nums1.length > nums2.length){
            setArray = nums1;
            forArray = nums2;
        }
        for(int i=0;i<forArray.length;i++){
            set.add(forArray[i]);
        }
        for(int j=0;j<setArray.length;j++){
            if(set.contains(setArray[j])){
                intersection.add(setArray[j]);
            }
        }
        int[] reVal = new int[intersection.size()];
        Iterator<Integer> iterator = intersection.iterator();
        int i = 0;
        while(iterator.hasNext()){
            reVal[i++] = iterator.next();
        }
        return reVal;
    }


    /**
     *进阶版   复杂度较高
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersection2(int[] nums1, int[] nums2) {
        //两个数组中同时都出现的元素
        if(nums1.length == 0 || nums2.length == 0){
            return new int[]{};
        }
        Map<Integer,Integer> set = new HashMap<Integer,Integer>();
        List<Integer> intersection = new ArrayList<>();
        int[] setArray = nums2;
        int[] forArray = nums1;
        if(nums1.length > nums2.length){
            setArray = nums1;
            forArray = nums2;
        }
        for(int i=0;i<forArray.length;i++){
            Integer times = set.get(forArray[i]);
            if(times == null){
                set.put(forArray[i],1);
            }else{
                set.put(forArray[i],times + 1);
            }
        }
        for(int j=0;j<setArray.length;j++){
            Integer times = set.get(setArray[j]);
            if( times != null && times > 0){
                intersection.add(setArray[j]);
                set.put(setArray[j],times - 1);
            }
        }
        int[] reVal = new int[intersection.size()];
        Iterator<Integer> iterator = intersection.iterator();
        int i = 0;
        while(iterator.hasNext()){
            reVal[i++] = iterator.next();
        }
        return reVal;
    }

    /**
     *进阶版   复杂度优化
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersection3(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersection3(nums2, nums1);
        }
        HashMap<Integer, Integer> m = new HashMap<>();
        for (int n : nums1) {
            m.put(n, m.getOrDefault(n, 0) + 1);
        }
        int k = 0;
        for (int n : nums2) {
            int cnt = m.getOrDefault(n, 0);
            if (cnt > 0) {
                //相对上面的算法这里的好处就是利用nums1从0开始存放交集元素   避免了空间浪费
                nums1[k++] = n;
                m.put(n, cnt - 1);
            }
        }
        //这里的好处就是避免了for循环赋值元素   记住！
        return Arrays.copyOfRange(nums1, 0, k);
    }
}
