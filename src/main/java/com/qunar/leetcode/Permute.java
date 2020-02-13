package com.qunar.leetcode;

import java.util.*;

/**
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Permute {

    public static void main(String[] args) {

        permute(new int[]{1,2,3});
    }

    /**
     * 第一次是参照给出挎号对数生成挎号(GenerateParenthesis)所有组合的回溯算法写出来的，运行时间和内存比较高
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        List<Integer> lives = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            lives.add(nums[i]);
        }
        permute(list,new ArrayList<>(),lives,nums.length);
        return list;
    }

    /**
     *
     * @param list  结果要放到这个list中   判断条件简单：只要放入全部元素就放
     * @param currList 当前组合了那些元素
     * @param lives  还剩下那些元素
     * @param length 本来数组的长度也就是组合达到放入结果list中的条件
     */
    public static void permute(List<List<Integer>> list,List<Integer> currList,List<Integer> lives,int length){

        //想法很简单  就是我放完了就加入到结果list中  然后退出
        if(currList.size() == length){
            list.add(currList);
            return;
        }
        //否则看我还留多少   然后和之前当前的currList能再次组成多少个  然后吧剩下的继续和本次组合的往下递归
        for(int i=0;i<lives.size();i++){
            Integer tmp = lives.get(i);
            //往直前里面加一个  生成本次新的
            ArrayList<Integer> newCurrList = new ArrayList<>(currList);
            newCurrList.add(tmp);
            //如果这个元素已经放入了   那就把这个元素删除调作为下一次的剩下的
            ArrayList<Integer> currLives = new ArrayList<>(lives);
            currLives.remove(i);
            //想法很简单  继续
            permute(list,newCurrList,currLives,length);
        }
    }
}
