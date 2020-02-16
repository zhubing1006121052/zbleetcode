package com.qunar.leetcode;

import com.qunar.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 */
public class BuildTreeBy2SortArray {

    public static void main(String[] args) {

    }

    /**
     * 先序遍历的顺序是根节点，左子树，右子树。中序遍历的顺序是左子树，根节点，右子树。
     *
     * 所以我们只需要根据先序遍历得到根节点，然后在中序遍历中找到根节点的位置，它的左边就是左子树的节点，右边就是右子树的节点。
     *
     * 生成左子树和右子树就可以递归的进行了。
     *
     * 比如上图的例子，我们来分析一下。
     *
     * preorder = [3,9,20,15,7]
     * inorder = [9,3,15,20,7]
     * 首先根据 preorder 找到根节点是 3
     *
     * 然后根据根节点将 inorder 分成左子树和右子树
     * 左子树
     * inorder [9]
     *
     * 右子树
     * inorder [15,20,7]
     *
     * 把相应的前序遍历的数组也加进来
     * 左子树
     * preorder[9]
     * inorder [9]
     *
     * 右子树
     * preorder[20 15 7]
     * inorder [15,20,7]
     *
     * 现在我们只需要构造左子树和右子树即可，成功把大问题化成了小问题
     * 然后重复上边的步骤继续划分，直到 preorder 和 inorder 都为空，返回 null 即可
     * 事实上，我们不需要真的把 preorder 和 inorder 切分了，只需要用分别用两个指针指向开头和结束位置即可。注意下边的两个指针指向的数组范围是包括左边界，不包括右边界。
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {

        Map<Integer,Integer> valIndex = new HashMap();
        for(int i=0;i<inorder.length;i++){
            valIndex.put(inorder[i],i);
        }
        return buildTree(preorder,0,preorder.length,inorder,0,inorder.length,valIndex);
    }

    /**
     *
     * @param preorder  前序遍历  找到根节点(第一个就是)
     * @param pstart    前序数组开始位置
     * @param pend      前序数组结束位置(到这个位置的时候说明已经没有元素了)
     * @param inorder  中序遍历  为的是根据从前序遍历中找到的根节点然后在中序遍历中找到然后以此为界左边是左子树，右边是右子树
     * @param istart   中序数组中开始位置
     * @param iend     中序数组中结束位置
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int pstart, int pend,
                                     int[] inorder, int istart, int iend,Map<Integer,Integer> valIndex) {
        if(pstart == pend){
            //没有了
            return null;
        }
        int rootVal = preorder[pstart];
        int rootValInOrderIndex = valIndex.get(rootVal);
//        for(;rootValInOrderIndex<iend;rootValInOrderIndex++){
//            if(inorder[rootValInOrderIndex] == rootVal){
//                break;
//            }
//        }
        TreeNode root = new TreeNode(rootVal);
        int leftNums = rootValInOrderIndex-istart;//左子树有几个元素
        root.left = buildTree(preorder,pstart+1,pstart+1+leftNums,inorder,istart,rootValInOrderIndex,valIndex);
        root.right = buildTree(preorder,pstart + leftNums +1,pend,inorder,rootValInOrderIndex+1,iend,valIndex);
        return root;
    }
}
