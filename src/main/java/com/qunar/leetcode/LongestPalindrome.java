package com.qunar.leetcode;

import java.util.HashMap;

/**
 * 根据回文串的定义，正着和反着读一样
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *于中间字符对称的文法，即“aba”(单核)、“cabbac”(双核)等
 * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-bao-gu/
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(longestPalindrome3("cccc"));
    }
    /**  cabbac  babad
     *解法 1: 暴力破解,时间复杂度O(n³）空间复杂度：O(1），常数个变量
     *两个for循环分别有i,j两个数组下标指针，通过i,j不断移动(i指向c第一个,然后j往后移动一次判断一次是否满足回文字串)
     *满足回文字串的判断条件：两个指针m,n;m从第一个开始，n从最后一个开始分别往中间移动，然后判断是否相等
     *
     **/
    public static String longestPalindrome1(String s) {
        return "";
    }

    /**
     * 动态规划-最长公共字串和子序列问题
     * A = "HelloWorld"
     * B = "loop"
     * A和B的最长公共子串是：lo,最长公共子序列是：loo
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        return "";
    }

    /**
     * 解法 4: 扩展中心
     * 我们知道回文串一定是对称的，所以我们可以每次循环选择一个中心，进行左右扩展，判断左右字符是否相等即可。
     * acac->cac  caac->caac  bbbba->bbbb
     * 两种：奇数或者偶数
     * @param s
     * @return
     */
    public static String longestPalindrome3(String s) {
        if(s == null || s == "" || s.length() == 0){
            return "";
        }
        int i = 0,maxLength=0;
        String maxStr = "";
        while(i < s.length()){
            int tmpJishu = 1,tmpOushu = 0,tmpLengthJishu=0,tmpLengthOushu=0;//cab1bac  ccc  aaaa
            String tmpStrJishu="",tmpStrOushu="";
            while(i - tmpJishu >= 0 && i + tmpJishu < s.length()){
                if(s.charAt(i - tmpJishu) == s.charAt(i + tmpJishu)){
                    tmpLengthJishu = 2*tmpJishu + 1;
                    tmpStrJishu = s.substring(i - tmpJishu,i + tmpJishu + 1);
                    tmpJishu++;
                }else{
                    break;
                }
            }
            if(tmpLengthJishu > maxLength){
                maxStr = tmpStrJishu;
                maxLength = tmpLengthJishu;
            }
            while(i - tmpOushu >= 0 && i + (tmpOushu + 1) < s.length()){//cabbac  ccc  aaaa
                if(s.charAt(i - tmpOushu) == s.charAt(i + (tmpOushu + 1))){
                    tmpLengthOushu = 2*tmpOushu + 2;
                    tmpStrOushu = s.substring(i - tmpOushu,i + (tmpOushu + 1) + 1);
                    tmpOushu++;
                }else{
                    break;
                }
            }
            if(tmpLengthOushu > maxLength){
                maxStr = tmpStrOushu;
                maxLength = tmpLengthOushu;
            }
            i++;
        }
        return maxLength == 0 ? s.charAt(0)+"" : maxStr;
    }

}
