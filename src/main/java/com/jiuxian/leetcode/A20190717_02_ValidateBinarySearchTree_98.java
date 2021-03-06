package com.jiuxian.leetcode;

import com.jiuxian.leetcode.util.TreeNode;
import com.jiuxian.leetcode.util.TreeUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-07-17 21:35:51
 * *
 * @comment: 98. 验证二叉搜索树
 * <p>
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A20190717_02_ValidateBinarySearchTree_98 {

    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(list, root);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private void inorder(List<Integer> list, TreeNode root) {
        if (root == null) return;
        inorder(list, root.left);
        list.add(root.val);
        inorder(list, root.right);
    }


    public boolean isValidBST1(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        return helper(list, root);
    }

    private boolean helper(LinkedList<Integer> list, TreeNode root) {
        if (root == null) return true;
        if (!helper(list, root.left)) return false;

        if (list.size() > 0 && list.getLast() >= root.val) return false;
        list.addLast(root.val);
        return helper(list, root.right);
    }


    public boolean isValidBST2(TreeNode root) {
        Integer min = Integer.MIN_VALUE;
        Integer max = Integer.MAX_VALUE;
        return isValid(root, min, max);
    }

    private boolean isValid(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (min != null && root.val <= min) return false;
        if (max != null && root.val >= max) return false;
        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }


    public static void main(String[] args) {

        TreeNode root = TreeUtil.getTree("[5,1,4,null,null,3,6]");
        boolean validBST = new A20190717_02_ValidateBinarySearchTree_98().isValidBST1(root);
        System.out.println(validBST);
    }
}
