package com.qunar.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subsets {

    public static void main(String[] args) {
        subsets(new int[]{1,2,3});
    }

    public static List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> list = new ArrayList<List<Integer>>();
        List<List<Integer>> listTmpAdd = new ArrayList<List<Integer>>();
//        list.add(new ArrayList<Integer>());  不能放着   下面第二个循环不为空的时候回添加
        for(int i =0;i<nums.length;i++){

            for(List listTmp : list){
                ArrayList<Integer> newList = new ArrayList<Integer>(listTmp);
                newList.add(nums[i]);
                listTmpAdd.add(newList);
            }
            list.addAll(listTmpAdd);
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(nums[i]);
            list.add(integers);
            listTmpAdd = new ArrayList<List<Integer>>();
        }
        list.add(new ArrayList<Integer>());
        return list;
    }
}
