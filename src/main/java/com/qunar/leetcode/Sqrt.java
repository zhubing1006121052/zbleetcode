package com.qunar.leetcode;

/**
 * 求一个数的平方根
 * @Author bing.zhu
 * @Date 2020/4/8
 **/
public class Sqrt {

    public static void main(String[] args) {
        for(int i=0;i<200;i++){
            System.out.println(i+" sqrt is "+sqrt(i));
        }
        System.out.println();
    }

    /**
     * 求一个数的平方根，二分法
     *          12
     *        (0,12)
     *     (0,6)     (6,12)
     *  (0,3)  (3,6)
     *     (3,4)  (4,6)
     *  (3,3) (3,4)
     * @param n
     * @return
     */
    public static int sqrt(int n){
        return sqrt2(0,n,n);
    }

    /**
     *这个有问题，边界值没考虑全
     * @param left
     * @param right
     * @param target
     * @return
     */
    @Deprecated
    public static int sqrt(int left, int right,int target){
        if(left == right){
            return left;
        }else if(left+1 == right){
            if(target - left*left < right*right - target){
                return left;
            }else{
                return right;
            }
        }
        int middle = (left + right)/2;
        if(middle*middle == target){
            return middle;
        }
        if(middle*middle < target){
            return sqrt(middle,right,target);
        }else{
            return sqrt(left,middle,target);
        }
    }

    /**
     * 向上取整
     * @param left
     * @param right
     * @param target
     * @return
     */
    public static int sqrt2(double left, double right,int target){

        double middle = (left + right)/2;
        if(Math.round(middle)*Math.round(middle) == target){
            return (int)Math.round(middle);
        }else if(Math.round(middle*middle) == target){
            return (int)Math.floor(middle);
        }else if(Math.round(middle*middle) < target){
            return sqrt2(middle,right,target);
        }else{
            return sqrt2(left,middle,target);
        }
    }
}
