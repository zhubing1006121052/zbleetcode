package com.qunar.leetcode;

/**
 * 位1的个数   
 */
public class HammingWeight {

    public static void main(String[] args) {
        System.out.println(1<<1);//2
        System.out.println(1<<2);//4
        System.out.println(1<<30);//1073741824
        System.out.println(1<<31);//-2147483648
//        System.out.println(hammingWeight(3));
    }

    public static int hammingWeight(int n) {
        int cnt = 0;
        //1 2 4 8 16 32 64 ... 2的31次方
        int flag = 1;
        for(int i=0;i<32;i++){
            if((n & flag) != 0){
                cnt++;
            }
            //向左移动一位
            flag = flag << 1;
        }
        return cnt;
    }
}
