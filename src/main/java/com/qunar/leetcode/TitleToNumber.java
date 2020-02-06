package com.qunar.leetcode;

public class TitleToNumber {

    public static void main(String[] args) {

        System.out.println((int)'A');//65
        System.out.println((int)'B');//66
        System.out.println((int)'Z');//90
        System.out.println(titleToNumber("AA"));
        System.out.println(titleToNumber("AB"));
        System.out.println(titleToNumber("ZY"));
        System.out.println(titleToNumber("AAA"));
    }

    public static int titleToNumber(String s) {

        if(s == null || s == ""){
            return 0;
        }
        int val = 0;
        char[] chars = s.toCharArray();
        for(int i=chars.length-1;i>=0;i--){
            int tmp = ((int)chars[i] - 64);
            for(int j=0;j<chars.length-i-1;j++){
                tmp = tmp * 26;
            }
            val = val + tmp;
        }
        return val;
    }
}
