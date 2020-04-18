package com.qunar.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GenerateParenthesis {

    public static List<String> aa(int n){
        List<String> result = new ArrayList<>();
        bb("(",1,0,n,result);
        return result;
    }
    public static void bb(String curr, int left, int right, int n, List<String> result){

        if(left + right == n){
            result.add(curr);
            return ;
        }
        //其实进来的时候你考虑  是添加左挎号还是右挎号   只要左挎号的个数小于一半  就可以添加左挎号
        //那右挎号什么什么时候可以添加呢？只要左边挎号个数大于右边的时候就可以添加又挎号，右边挎号个数都大于等于左边的时候就不能在添加左挎号了
        if(left < n/2){
            bb(curr+"(",left + 1,right,n,result);
        }
        //
        if(right < left){
            bb(curr+")",left,right + 1,n,result);
        }

    }

    public static void main(String[] args) {

        System.out.println(aa(6));
        System.out.println(generateParenthesis(3));
    }


    /**
     *回溯法 思路和算法
     *
     * 只有在我们知道序列仍然保持有效时才添加 '(' or ')'，
     * 而不是像 方法一 那样每次添加。我们可以通过跟踪到目前为止放置的左括号和右括号的数目来做到这一点，
     *如果我们还剩一个位置，我们可以开始放一个左括号。 如果它不超过左括号的数量，我们可以放一个右括号。
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        //想法：一次次的尝试，比如n=5,第一个肯定先放"(",第二个可以放"(",")"都可以，只要左挎号不超过5个就行
        //如果第二个放了"(" 那么第三个也可以随便放
        //如果第二个放了")"那么第一个和第二个挎号已经凑成一对了(左右个数相等)，那么第三个只能放"("
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    /**
     *
     * @param ans 存放结果的列表
     * @param currStr 当前的存放的有哪些挎号
     * @param leftNums  存放左挎号的数量
     * @param rightNums 存放了右挎号的数量
     * @param maxNums 允许存放左右挎号的最大数量
     */
    public static void backtrack(List<String> ans, String currStr, int leftNums, int rightNums,int maxNums){

        //如果所有挎号都放完了，结束再放到数组中
        if(currStr.length() == maxNums*2){
            ans.add(currStr);
            return;
        }
        //如果挎号还没拼接完  就还需要放
        //怎么放？放左挎号还是右挎号
        //大方向就是先放左挎号  再放右挎号
        if(leftNums < maxNums){
            //当左挎号还没超过限制的时候肯定是先放左边的，
            backtrack(ans,currStr+"(",leftNums+1,rightNums,maxNums);
        }
        //上面执行的结果对下面没有影响 只要知道当前这个挎号currStr是什么，有多少左挎号，有多少右挎号
        //那右挎号有什么限制呢？也是不能超过最大限制？这个当然也是，但是还得必须满足小于之前已经出现的左挎号数量
        //要不然不能成对rightNums < maxNums && rightNums < leftNums
        if(rightNums < leftNums){
            //n=3   currStr = "()" 左右都等于1 不能放右挎号了
            backtrack(ans,currStr+")",leftNums,rightNums+1,maxNums);
        }

    }
}
