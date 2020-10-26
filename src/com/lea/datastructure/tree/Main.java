package com.lea.datastructure.tree;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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
 *  二叉堆：一种完全二叉树分两个类型
 *      最大堆
 *          最大堆的任何一个父节点都大于或等于它的左、右孩子节点的值
 *      最小堆
 *          最小堆的任何一个父节点都小于或等于它的左、右孩子节点的值
 *
 *   二叉堆的根节点叫做 堆顶
 *   二叉堆插入、删除、构建 操作的 自我调整（以最小堆为例）
 *   插入：当二叉堆插入节点时，插入位置是完全二叉树的最后一个位置，和父节点比较，如果比父节点小，不符合最小堆的性质，
 *      让新节点 “上浮”， 就和父节点交换位置，依次拿新节点和父节点比较，不符合二叉堆的性质就上浮，并交换位置
 *
 *   删除：删除的过程与插入的过程正好相反。删除某个节点时，拿二叉堆最后一个节点的值来占位，通过占位的节点值和左、右节点比较
 *      如果左、右孩子节点中最小的一个 比 占位节点 小，那么占位节点 “下沉”，和最小的节点交换位置，依次比较左右节点，直到占位节点最小或
 *      没有孩子节点
 *
 *   构建二叉堆：
 *      构建二叉树，也就是把一个无序的完全二叉树调整为二叉堆；本质是 让所有非叶子节点依次下沉
 *      从最后一个非叶子节点开始，比较节点是否比孩子节点是否小，小就“下沉”，和最小孩子节点交换位置，然后是第二个非叶子节点做比较.... 直到堆顶
 *
 *   把二叉堆。完全二叉树存储方式是顺序存储。也就是二叉堆的所有节点都存储在数组中
 *   假设一个父节点下标是 parent
 *   那么 左孩子节点的下标 = parent * 2 + 1;
 *       右孩子节点的下标 = parent * 2 + 2;
 *
 */
public class Main {

    private static final Integer[] tree = new Integer[]{3,2,9,null,null,10,null,null,8,null,4};
    private static final LinkedList<Integer> inputList = new LinkedList<>(Arrays.asList(tree));

    public static void main(String[] args) {
        BinaryTree.TreeNode treeNode = BinaryTree.createBinaryTree(inputList);
        System.out.print("\n前序遍历 : ");                  // 根--》 左--》 右
        BinaryTree.preOrderTraversal(treeNode);
        System.out.print("\n利用栈实现前序遍历 : ");
        BinaryTree.preOrderTraversalWithStack(treeNode);
        System.out.print("\n中序遍历 : ");                  // 左--》根 --》 右
        BinaryTree.inOrderTraversal(treeNode);
        System.out.print("\n利用栈实现中序遍历 : ");
        BinaryTree.postOrderTraversalWithStack(treeNode);

        System.out.print("\n后序遍历 : ");                  // 左--》右 --》 根
        BinaryTree.postOrderTraversal(treeNode);
    }

    @Test
    public void test() {
        BinaryTree.TreeNode treeNode = BinaryTree.createBinaryTree(inputList);
        BinaryTree.levelOrderTraversal(treeNode);

        Queue<BinaryTree.TreeNode> queue = new LinkedList<>();
        queue.offer(treeNode);
        BinaryTree.levelOrderTraversalRecursion(queue);
    }
}
