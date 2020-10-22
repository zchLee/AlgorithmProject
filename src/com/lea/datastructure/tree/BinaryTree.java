package com.lea.datastructure.tree;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author lzc
 * @create 2020-10-20 21:39
 *
 * 二叉树（binary tree） 是一种特殊形式，这种树结构**每个节点最多有两个孩子节点**
 * 它的两个孩子节点，一个称为**左子节点（left child）**，一个被称为**右子节点（right child）** 顺序是固定的，和人的左右手一致
 * 此外二叉树还有两种特殊形式，一个叫满二叉树，一个叫完全二叉树
 *
 * 满二叉树：
 *   一个二叉树所有节点都存在左右孩子，且所有的叶子节点都在同一层级，满足这两个条件就称为**满二叉树**
 * 完全二叉树：
 *      对一个有n个节点的二叉树，按层级顺序编号，则所有节点的编号为从1,到n。如果这个数所有节点和同样深度的 -满二叉树-的编号从1到n
 *      的节点位置相同，则这个二叉树为完全二叉树
 *
 *      可以这么理解，二叉树从n到m个节点，和满二叉树从n到m的节点位置相同，那么这个二叉树就是完全二叉树
 *
 *  用物理存储结构怎么来表达呢？
 *  1.链式存储结构
 *      实现比较简单，也挺直观，在链表的基础上，节点留两个指针，分别指向左孩子、右孩子
 *  2.数组
 *      使用数组存储时，按照层级顺序把二叉树的节点放到数组中对应的关系上，如果某个节点的孩子空缺，则数组相应的位置也空出来。
 *          据说这样设计方便在数组中定位二叉树的孩子节点和父节点
 *     表达式
 *      父节点下标 = 左孩子节点下标 * 2 + 1 = 2 * 右孩子节点小标 + 2；
 *      由此也可以通过 下标是否能被2整除 判断出 是 左孩子还是右孩子
 *
 *  二叉树有什么用呢？
 *      二叉树包含许多特殊形式，每种形式都有自己的作用，但最主要的应用还在于进行查找操作和维持相对顺序这两个方面，
 *
 *  以此引出 二叉查找树（binary search tree） 也叫 二叉排序树（binary sort tree）
 *      二叉查找树在二叉树基础上增加了以下几个条件
 *          * 如果左子树不为空，则左子树上所有节点的值均小于根节点的值
 *          * 如果右子树不为空，则右子树上所有节点的值均大于根节点的值
 *          * 左、右子树也都是二叉查找树
 *      对于一个**节点分布相对均衡**的二叉查找树来说，如果节点总数是n，那么搜索节点的复杂度就是O(logn)
 *
 *      缺点：
 *          很容易“偏食”，添加数据递增，会使右孩子无限延长，是二叉树变得不平衡，查找的时间复杂度也变成了O(n), 解决这个问题需要
 *          引入 **自平衡**， 红黑树、AVL树、树堆等
 *  遍历：
 *      从节点之间的位置关系来看，二叉树遍历分为4中
 *          1.前序遍历
 *          2.中序遍历
 *          3.后序遍历
 *          4.层序遍历
 *      从宏观角度，二叉树的遍历可付姐为两大来
 *          1.深度优先遍历（前、中、后序遍历）
 *              顾名思义，偏深度，一头插到底的访问方式
 *          2.广度优先（层序遍历）
 */
public class BinaryTree {


    /*
        二叉树前序遍历
        输出顺序是根节点，左子节点，右子节点, 从根结点出发，找到左子节点就输出，继续找左子节点，直到叶子节点，再找叶子节点的父节点的右叶子节点
     */
    public static void preOrderTraversal(TreeNode node) {
        if (node == null)
            return;
        System.out.print(node.data + "\t");
        preOrderTraversal(node.leftChild);
        preOrderTraversal(node.rightChild);
    }

    /*
    中序遍历
        输出顺序是 左子树、根节点、右子树
        找到最左孩子输出，再输出最左孩子的父节点，然后输出右节点
     */
    public static void inOrderTraversal(TreeNode node) {
        if (node == null)
            return;
        inOrderTraversal(node.leftChild);
        System.out.print(node.data + "\t");
        inOrderTraversal(node.rightChild);
    }


    /*
    后序遍历
        输出顺序是 左子树、右子树、根节点
        找到最左孩子输出，再输出最左孩子的父节点的右节点，最后输出父节点
     */
    public static void postOrderTraversal(TreeNode node) {
        if (node == null)
            return;
        postOrderTraversal(node.leftChild);
        postOrderTraversal(node.rightChild);
        System.out.print(node.data + "\t");
    }

    // 利用栈后入先出的特点 实现前序遍历二叉树
    public static void preOrderTraversalWithStack(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        // 出栈操作
        while (treeNode != null || !stack.isEmpty()) {
            // 一直输出左子树，并把节点放入stack中
            while (treeNode != null) {
                System.out.print(treeNode.data + "\t");
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            // 节点遍历完了左孩子，从栈中取出数据回溯右孩子
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                // 开始一直遍历右孩子
//                System.out.println(treeNode.data);  此处打印是答应重复的数据，错了
                treeNode = treeNode.rightChild;
            }

        }
    }


    public static void postOrderTraversalWithStack(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        // 出栈操作
        while (treeNode != null || !stack.isEmpty()) {
            // 一直找左子树，并把节点放入stack中
            while (treeNode != null) {
//                System.out.print(treeNode.data + "\t");
                stack.push(treeNode);
                treeNode = treeNode.leftChild;

            }
            // 节点遍历完了左孩子，从栈中取出数据回溯右孩子
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                System.out.print(treeNode.data + "\t");
                treeNode = treeNode.rightChild;
            }

        }
    }

    /*
    层序遍历
     */
    public static void levelOrderTraversal(TreeNode treeNode) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(treeNode);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.data + "\t");
            if (null != node.leftChild) {
                queue.add(node.leftChild);
            }
            if (null != node.rightChild) {
                queue.add(node.rightChild);
            }
        }
        System.out.println();
    }

    // 递归实现层序遍历
    public static void levelOrderTraversalRecursion(Queue<TreeNode> queue) {
        if (queue.isEmpty())
            return;
        for (int i = 0; i < queue.size(); i++) {
            TreeNode treeNode = queue.poll();
            System.out.print(treeNode.data + "\t");
            if (treeNode.leftChild != null) {
                queue.add(treeNode.leftChild);
            }
            if (treeNode.rightChild != null) {
                queue.add(treeNode.rightChild);
            }
        }
        levelOrderTraversalRecursion(queue);
//        if (null != treeNode.leftChild) {
//            levelOrderTraversalRecursion(treeNode.leftChild);
//        }
//        if (null != treeNode.rightChild) {
//            levelOrderTraversalRecursion(treeNode.rightChild);
//        }
//        System.out.print(treeNode.data + "\t");
    }



    // 构建二叉树
    public static TreeNode createBinaryTree(LinkedList<Integer> inputList) {
        if (inputList == null || inputList.isEmpty())
            return null;
        TreeNode node = null;
        Integer data = inputList.removeFirst();
        // 如果链表空了，就不递归
        if (data != null) {
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(inputList);
            node.rightChild = createBinaryTree(inputList);
        }
        return node;
    }

    // 节点
    protected static class TreeNode {
        int data;
        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode(int data) {
            this.data = data;
        }
    }
}
