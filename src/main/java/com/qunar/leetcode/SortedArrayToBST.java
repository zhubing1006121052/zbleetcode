package com.qunar.leetcode;

import com.qunar.common.TreeNode;

/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点
 * 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortedArrayToBST {

    public static void main(String[] args) {

        System.out.println(new SortedArrayToBST().sortedArrayToBST(new int[]{1,2,3,4,5}));
        System.out.println(new SortedArrayToBST().sortedArrayToBST(new int[]{1,2,3,4,5,6,7}));
    }

    /**
     * 解题思路：
     * 1：既然是高度平衡二叉树，那么根节点就是数组的中间数
     * 2：递归下去不断取中间节点为左右子树
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {

        if(nums.length > 0){
            return sortedArrayToBST(nums,0,nums.length - 1);
        }
        return null;
    }

    /**
     * 1,2,3,4,5
     * @param nums
     * @param begin
     * @param end
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums,int begin,int end) {
        TreeNode root = null;
        if(end - begin >= 0){
            root = new TreeNode(nums[(end + begin)/2]);
            TreeNode left = sortedArrayToBST(nums,begin,(end + begin)/2 -1);
            root.setLeft(left);
            TreeNode right = sortedArrayToBST(nums,(end + begin)/2 +1,end);
            root.setRight(right);
        }
        //第一次进来是返回树的根节点，所以最终返回的肯定是树的根节点
        //最后一次是返回树的叶子节点(null),
        return root;
    }
}
