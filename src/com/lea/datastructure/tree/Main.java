package com.lea.datastructure.tree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author lzc
 * @create 2020-10-20 21:28
 * 树（tree）: 是n（n >= 0）个节点的有限集，当n=0时，称为空树。在任意一个分空树中，有如下特点
 *  1.有且仅有一个特定的称为根的节点
 *  2.当n > 1时，其余节点可分为m( m > 0) 个互不相交的由县级，每个结合本身又称为是一个树，并称为根的子树
 *
 *                  1
 *               /      \
 *              2       3
 *           /    \      \
 *          4     5      6
 *        / | \
 *       7  8 9
 *
 *
 *  上面数字为节点编号，
 *      节点 1 是根节点（root）
 *      5,6,7,8是树的末端，没有孩子节点，称为叶子节点（leaf）
 *      节点2以下是根节点的其中一个 子树
 *      节点4的上一级 2节点 是节点4的父节点（parent），从节点四衍生出两个节点，是节点4的孩子节点（child）
 *      和节点4统计，由父节点衍生出的节点5是节点4的 兄弟节点（sibling）
 *
 *  由树引出典型树结构  二叉树      见BinaryTree.java
 *
 */
public class Main {

    public static void main(String[] args) {
        Integer[] tree = new Integer[]{3,2,9,null,null,10,null,null,8,null,4};
        LinkedList<Integer> inputList = new LinkedList<>(Arrays.asList(tree));
        BinaryTree.TreeNode treeNode = BinaryTree.createBinaryTree(inputList);
        System.out.println("前序遍历");
        BinaryTree.preOrderTraveral(treeNode);
        System.out.println("中序遍历");
        BinaryTree.inOrderTraneral(treeNode);
        System.out.println("后序遍历");
        BinaryTree.postOrderTraneral(treeNode);
    }
}
