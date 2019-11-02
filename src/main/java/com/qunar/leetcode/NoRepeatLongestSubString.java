package com.qunar.leetcode;

import java.util.HashSet;

/**
 *给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/submissions/
 */
public class NoRepeatLongestSubString {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("abcabcbb"));
    }

    /**
     * a b c
     * b c a
     * c a b
     * a b c
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring1(String s) {
        if(s == null || s.trim() == ""){
            return 0;
        }
        char[] chars = s.toCharArray();
        HashSet set = null;
        int max = 0;
        if(chars.length == 1){
            return 1;
        }
        for(int i=0;i<chars.length;i++){
            set = new HashSet();
            set.add(chars[i]);
            for(int j=i+1;j<chars.length;j++){
                if(set.contains(chars[j])){
                    max = set.size() > max ? set.size() : max;
                    break;
                }else{
                    set.add(chars[j]);
                }
                if(j == chars.length - 1){
                    max = set.size() > max ? set.size() : max;
                }
            }
        }
        return max;
    }

    /**
     * 滑动窗口的思想
     *     p   w   w   k   e   w      初始化的时候窗口的两个指针都是指向开始位置
     * \\
     *    p   w   w   k   e   w      第一步由于窗口大小为0，不包含任何元素，右边窗向向右移动，第一个元素到窗口内
     * \    \
     *    p   w   w   k   e   w      第二步右边的窗口边上的元素是否包含在窗口内，不包含所以右边窗再次移动，将第二个元素包含
     * \        \
     *      w   w   k   e   w       第三步再将判断右边窗口的元素是否包含在窗口内，在内所以第一个元素开始的子串失效，删除左边窗口开头元素，左边窗口右移
     *   \    \
     *          w   k   e   w       第四步再将判断右边窗口的元素是否包含在窗口内，还在内所以第二个元素开始的子串失效，删除左边窗口开头元素，左边窗口右移
     *       \\
     *          w   k   e   w       第五步其实窗口内已经没元素了，不包含，所以右窗右移
     *       \    \
     *          w   k   e   w       第六步任然不包含，所以右窗右移
     *       \        \
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        HashSet<Character> characters = new HashSet<>();
        int i=0,j=0,maxL=0;
        while (i < s.length() && j < s.length()){
            if(!characters.contains(s.charAt(j))){
                characters.add(s.charAt(j));
                j++;
                maxL = characters.size() > maxL ? characters.size() : maxL;
            }else{
                characters.remove(s.charAt(i++));
            }
        }
        return maxL;
    }
}
