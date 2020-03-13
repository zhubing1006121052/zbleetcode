package com.qunar.leetcode;

import java.util.Stack;

/**
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 *
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 *
 * 示例 1:
 *
 * 输入: "3+2*2"
 * 输出: 7
 * 示例 2:
 *
 * 输入: " 3/2 "
 * 输出: 1
 * 示例 3:
 *
 * 输入: " 3+5 / 2 "
 * 输出: 5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/basic-calculator-ii
 * @Author bing.zhu
 * @Date 2020/3/10
 **/
public class Calculate {

    public final static String JIANJIAN_EXP = "[+-]";
    public final static String CHENGCHU_EXP = "[*/]";
    public final static char JIA = '+';
    public final static char JIAN = '-';
    public final static char CHENG = '*';


    public static void main(String[] args) {

        String str = "3 + 5/ 2 +21 -1/1 +2*3";
        calculateByStack(str);
    }

    /**
     * 计算器的实现，第一应该就能想到使用栈
     * @param s
     * @return
     */
    public static int calculateByStack(String s) {

        char chrs[] = s.toCharArray();
        char lastOp = '+';
        Stack<Integer> stack = new Stack<Integer>();
        for(int i=0;i<chrs.length;i++){
            if(chrs[i] == ' '){
                continue;
            }
            //第一次肯定是数字，后面才有运算符，所以先第一个数放进去
            //然后记录第一个数字后面的操作符
            //在继续找下一个数字，如果上一个操作符号为加号直接入栈
            //如果上一个操作符号是负号那么直接将该数字置为负数入栈
            //如果上一个操作符号是乘号那么将上一个数字弹出乘了之后再入栈
            //如果上一个操作符号是除号那么上一个数字弹出除了之后再入栈
            if(Character.isDigit(chrs[i])){
                int tmp = chrs[i] - '0';
                while(++i<chrs.length && Character.isDigit(chrs[i])){
                    //下一位还是数字
                    tmp = tmp*10 + (chrs[i] - '0');
                }
                //不是数字了 回到该位置
                i--;
                if(lastOp == '+'){
                    //第一次进来肯定是+
                    stack.push(tmp);
                }else if(lastOp == '-'){
                    stack.push(-1*tmp);
                }else if(lastOp == '*'){
                    stack.push(stack.pop() * tmp);
                }else{
                    stack.push(stack.pop() / tmp);
                }

            }else{
                //操作符号
                lastOp = chrs[i];
            }
        }
        int result = 0;
        for(Integer i : stack){
            result += i;
        }
        return result;
    }
    /**
     * 思想：分组
     * 1，先按照加减号拆分，分成一组组的，这一组组计算完之后我们再做相加和相减
     * 2，对于上面分好的组，这个组内不是乘就是除
     * @param s
     * @return
     */
    public static int calculate(String s) {

        String groups[] = s.split(JIANJIAN_EXP);
        int total = 0;
        int groupVal = 0;
        int jjcharindex = 0;
        int chchcharindex = 0;
        char jjchr = JIA;
        for(int i=0;i<groups.length;i++){

            String nums[] = groups[i].split(CHENGCHU_EXP);
            for(int j=0;j<nums.length;j++){
                int tmp = Integer.parseInt(nums[j].trim());
                if(j == 0){
                    groupVal = tmp;
                    chchcharindex = nums[j].length();
                    continue;
                }
                char chr = groups[i].charAt(chchcharindex);
                if(chr == CHENG){
                    groupVal = groupVal * tmp;
                }else{
                    groupVal = groupVal / tmp;
                }
                chchcharindex = chchcharindex + nums[j].length() + 1;
            }
            if(i == 0){
                total = groupVal;
            }else if(jjchr == JIA){
                total = total + groupVal;
            }else if(jjchr == JIAN){
                total = total - groupVal;
            }
            if(i<groups.length-1){
                //按照加减分隔，这个为第一个当前数字计算后的一个加减号位置
                jjcharindex = jjcharindex + groups[i].length() + (i==0?0:1);
                jjchr = s.charAt(jjcharindex);
            }
        }
        return total;
    }


}
