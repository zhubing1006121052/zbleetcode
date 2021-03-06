package com.qunar.leetcode;

/**
 *
 * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
 *
 * 示例 1:
 *
 * 输入: a = 1, b = 2
 * 输出: 3
 * 示例 2:
 *
 * 输入: a = -2, b = 3
 * 输出: 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-two-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 那么如何求一个负数的补码呢？例如：求-1的补码。
 * 第一步：先写出1的原码（以8位为例）：0000 0001
 * 第二步：求这个原码的反码：1111 1110
 * 第三步：补码就是给反码加1。即：1111 1111 。
 * 由补码求源码就是三二一
 */
public class SumByBits {

    public static void main(String[] args) {
        //-2147483648=10000000000000000000000000000000
        System.out.println(((int)Math.pow(2,30)*2)+"="+Integer.toBinaryString((int)Math.pow(2,30)*2));
        //-2147483648=10000000000000000000000000000000
        System.out.println((1<<31)+"="+Integer.toBinaryString(1<<31));
    }

    /**
     * a    = 01000000000000000000000000000101
     * b    = 01000000000000000000000000000101
     * a+b  = 10000000000000000000000000001010  相加起来的就是一个负数  溢出了
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        //就是位运算 a=5,b=3,sum=8
        //需要考虑的问题：同一位相同为0(都是1则进位)，不同为1
        //a = 00000000000000000000000000000101
        //b = 00000000000000000000000000000011
        //sum 00000000000000000000000000001000
        // ^= 00000000000000000000000000000110
        // &= 00000000000000000000000000000001

        //第一步：第一位相加然后进一位
        //a =   00000000000000000000000000000101
        //b =   00000000000000000000000000000011
        //a^b=  00000000000000000000000000000110
        //a&b=  00000000000000000000000000000001  需要进一位(需要左移以为继续加)
        while(b != 0){
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }


}
