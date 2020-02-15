package com.qunar.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回 s 所有可能的分割方案。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AllSubStrHuiwenStrOfStr {


    public static void main(String[] args) {

        List<List<String>> aab = partition("aab");
    }

    public static List<List<String>> partition(String s) {
        //回文子串定义：正反读都是一样(中心扩展法)
        //难点是怎么获取所有的切分子串
        List<List<String>> listListStr = new ArrayList<List<String>>();
        List<String> okStr = new ArrayList();
        partition(listListStr,okStr,s);
        return listListStr;
    }
    /**
     * 之前挎号问题  子集问题    思想都是搞好了放进去对应的剩下多少   最后搞完了就放到结果中
     * @param listListStr  装准备好的子回文子串
     * @param okStr  一个个分隔好的
     * @param leftStr  还有多少没分隔的
     */
    public static void partition(List<List<String>> listListStr,List<String> okStr,String leftStr){

        //回溯算法  只剩下一个字符了  一个字符肯定是回文串   okStr 都是判断了是回文子串的才放进去的
        if(leftStr.equals("")){
            listListStr.add(okStr);
            return;
        }

        //一个个切分   ababd  首次切分成  a是回文,留下babd  ab不是,不用执行   aba是,留下bd   abab不是,不用执行  ababd不是,不用执行
        //记住这里是小于等于，因为"abcde".substring(0,2)方法结果是ab,而不是abc!!!
        List<String> tmp = null;
        String start = null;
        for(int i=1;i<=leftStr.length();i++){
            start = leftStr.substring(0,i);
            if(isHuiwenSubString(start)){
                tmp = new ArrayList(okStr);
                tmp.add(start);
                //"1".substring(1,1) 等于空字符串""
                partition(listListStr,tmp,leftStr.substring(i,leftStr.length()));
            }
        }
    }
    public static boolean isHuiwenSubString(String subStr){
        int i=0,j=subStr.length()-1;
        while(i <= j){
            if(subStr.charAt(i) != subStr.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
