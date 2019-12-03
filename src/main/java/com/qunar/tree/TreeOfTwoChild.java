package com.qunar.tree;

/**
 * 二叉树相关
 * 1，遍历
 */
public class TreeOfTwoChild {


    public static void main(String[] args) {

        /**
         *               1
         *           /     \
         *        2        3
         *      /   \     /   \
         *     4    5    6    7
         */
        TreeNode root = initTree();
        //先序遍历 1 2 4 5 3 6 7   (就是优先遍历左子树，没有左子树再遍历右子树)
        preOrderTraverse(root); System.out.println();
        //中序遍历 4 2 5 1 6 3 7  (优先访问左子树，知道没有左节点，然后访问更节点，在访问有子树节点)
        inOrderTraverse(root);System.out.println();
        //后序遍历 4 5 2 6 7 3 1
        postOrderTraverse(root);
        //层次遍历
        int nums[] = new int[]{1,2,3,4,5,6};
        sortedArrayToBST(nums);
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = null;
        sortedArrayToBST_digui(nums,0,nums.length,root);
        return root;
    }
    public static void sortedArrayToBST_digui(int[] nums,int start,int end,TreeNode root) {

        if(start == end){
            return;
        }
        int middle = (end - start) / 2;
        System.err.println(end+"-"+start+"="+middle);
        root = new TreeNode(nums[middle]);
        sortedArrayToBST_digui(nums,start,middle,root.left);
        sortedArrayToBST_digui(nums,middle+1,end,root.right);
    }

    public static TreeNode initTree(){
        TreeNode root = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);
        TreeNode left11 = new TreeNode(4);
        TreeNode right12 = new TreeNode(5);
        TreeNode left21 = new TreeNode(6);
        TreeNode left22 = new TreeNode(7);
        root.left = left1;
        root.right = right1;
        left1.left = left11;
        left1.right = right12;
        right1.left = left21;
        right1.right = left22;
        return root;
    }

    /**
     * 二叉树先序遍历的实现思想是：
     *
     * 访问根节点；
     * 访问当前节点的左子树；
     * 若当前节点无左子树，则访问当前节点的右子树；
     * @param treeNode
     */
    public static void preOrderTraverse(TreeNode treeNode){

        if(treeNode != null){
            System.out.print(treeNode.val + " ");
            preOrderTraverse(treeNode.left);
            preOrderTraverse(treeNode.right);
        }
    }

    /**
     * 二叉树中序遍历
     * 二叉树中序遍历的实现思想是：
     *
     * 访问当前节点的左子树；
     * 访问根节点；
     * 访问当前节点的右子树；
     * @param treeNode
     */
    public static void inOrderTraverse(TreeNode treeNode){

        if(treeNode != null){
            inOrderTraverse(treeNode.left);
            System.out.print(treeNode.val + " ");
            inOrderTraverse(treeNode.right);

        }
    }

    /**
     * 二叉树后序遍历
     * 二叉树后序遍历的实现思想是：从根节点出发，依次遍历各节点的左右子树，
     * 直到当前节点左右子树遍历完成后，才访问该节点元素。
     * @param treeNode
     */
    public static void postOrderTraverse(TreeNode treeNode){
        if(treeNode != null){
            postOrderTraverse(treeNode.left);
            postOrderTraverse(treeNode.right);
            System.out.print(treeNode.val + " ");
        }
    }

    /**
     * 二叉树层次遍历
     * 层次遍历方式：按照二叉树中的层次从左到右依次遍历每层中的结点。具体的实现思路是：
     * 通过使用队列的数据结构，从树的根结点开始，依次将其左孩子和右孩子入队。而后每次队列中一个结点出队，
     * 都将其左孩子和右孩子入队，直到树中所有结点都出队，出队结点的先后顺序就是层次遍历的最终结果。
     * @param treeNode
     */
    public static void levelOrderTraverse(TreeNode treeNode){

    }
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
