package com.qunar.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pascals-triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Generate {

    public static void main(String[] args) {

        List<List<Integer>> generate = generate(5);
        for(int i=0;i<generate.size();i++){
            System.out.println(generate.get(i).toString());
        }
    }

    public static List<List<Integer>> generate(int numRows) {

        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if(numRows <= 0){
            return list;
        }

        for(int i=0;i<numRows;i++){
            List<Integer> tmp = new ArrayList<Integer>();
            for(int j=0;j<=i;j++){
                List<Integer> pre = null;
                if(j - 1 < 0 || i-1 < 0 || ((pre = list.get(i-1)) != null && j > pre.size()-1)){
                    tmp.add(1);
                }else{
                    tmp.add(pre.get(j-1) + pre.get(j));
                }
            }
            list.add(tmp);
        }
        return list;
    }
}
