package com.qunar.leetcode;

/**
 *假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ClimbStairs {


    public static void main(String[] args) {


    }

    /**
     * leetcode上运行时间超过限制   递归
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        //n递减到0就是到达了   n = n-2或者n = n-1
        //n=2*x + 1*y  就是求xy有多少种组合,但是这个组合还涉及顺序
        //如5=2*x + 1*y;有(0,5),(1,3),(2,1)三种组合
        //(0,5):5个1所以不涉及顺序
        //(1,3):1个2,3个1有4种顺序组合
        //(2,1):2个2,1个1有3种顺序组合   没想到组合的种类怎么计算

        //3个的时候,要么爬1一个要么爬2个,爬1剩下2,爬2剩下1,就是算剩下的有几种爬法
        //climbStairs(3) = climbStairs(2) + climbStairs(1);
        //climbStairs(4) = climbStairs(3) + climbStairs(2)
        //可以吧climbStairs(2) 和 climbStairs(1) 看成是原子的 爬到这就知道剩下的了
        //和斐波拉西数列看起来有点像
        if(n == 1){
            return 1;
        }else if(n == 2){
            return 2;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    /**
     * 动态规划
     * 不难发现，这个问题可以被分解为一些包含最优子结构的子问题，
     * 即它的最优解可以从其子问题的最优解来有效地构建，我们可以使用动态规划来解决这一问题。
     * 第 i阶可以由以下两种方法得到：
     * 1，在第 (i-1)(i−1) 阶后向上爬一阶。
     * 2，在第 (i-2)(i−2) 阶后向上爬 22 阶。
     * 所以到达第 i阶的方法总数就是到第 (i-1) 阶和第 (i−2) 阶的方法数之和。
     *
     * 令 dp[i]表示能到达第 i阶的方法总数(也就是数组的下标为楼梯,数组值为爬到该阶的方法总数)：
     * dp[i]=dp[i−1]+dp[i−2]
     * 枚举：
     * 爬到N层       方法总数
     *     1           1
     *     2           2
     *     3           3        到3有两种方式分别为1层和2层
     *     4           6        到3有两种方式分别为2层和3层
     * @param n
     * @return
     */
    public static int climbStairs2(int n) {
        //n为正整数
        if(n <= 2){
            // 1返回1  2返回2
            return n;
        }
        //dp[i]=dp[i−1]+dp[i−2]  下标就是楼梯阶数   从1开始到n结束，所以数组长度为n+1
        int dp[] = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3;i<=n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

}
