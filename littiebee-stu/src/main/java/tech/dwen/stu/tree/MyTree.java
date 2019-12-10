package tech.dwen.stu.tree;

import java.util.Stack;

public class MyTree {

    /**
     * 中序遍历
     * @param root
     */
    public static void midTraversal(TreeNode root){
        // 栈
        Stack<TreeNode> stack = new Stack<TreeNode>();
        // 游标节点指向根节点
        TreeNode node = root;
        // 最后一个节点满足的条件为: node的左右子树都为空并且栈为空
        // 不同时满足这两点，则进入循环
        while (node != null || !stack.isEmpty()) {
            // 若当前考查节点非空，则输出该节点的值
            // 由考查顺序得知，需要一直往左走
            while (node != null) {
                // 为了之后能找到该节点的右子树，暂存该节点
                stack.push(node);
                node = node.getLeft();
            }
            // 一直到左子树为空，则开始考虑右子树
            // 如果栈已空，就不需要再考虑
            // 弹出栈顶元素，将游标等于该节点的右子树
            if (!stack.isEmpty()) {
                node = stack.pop();
                System.out.print(node.getVal() + " ");
                node = node.getRight();
            }

        }
        System.out.println("");
    }

    /**
     * 后序遍历
     * @param root
     */
    public static void postorderTraversal(TreeNode root){
        // 栈
        Stack<TreeNode> stack = new Stack<TreeNode>();
        // 游标节点指向根节点
        TreeNode node = root;

        TreeNode lastVis = root;
        // 最后一个节点满足的条件为: node的左右子树都为空并且栈为空
        // 不同时满足这两点，则进入循环
        while (node != null || !stack.isEmpty()) {
            // 若当前考查节点非空，则输出该节点的值
            // 由考查顺序得知，需要一直往左走
            while (node != null) {
                // 为了之后能找到该节点的右子树，暂存该节点
                stack.push(node);
                node = node.getLeft();
            }
            //查看当前栈顶元素
            node = stack.peek();
            //1.右子树也为空 2.右子树已经访问
            //则可以直接输出当前节点的值
            if (node.getRight() == null || node.getRight().getVal() == lastVis.getVal()) {
                System.out.print(node.getVal() + " ");
                lastVis = node;
                stack.pop();
                // 节点node设置成null，下一轮循环的时候会考查栈中的节点
                node = null;
            } else {
                //否则，继续遍历右子树
                node = node.getRight();
            }
        }
        System.out.println("");
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

        root.setLeft(node2);
        root.setRight(node3);

        node2.setLeft(node4);

        node4.setRight(node6);

        node6.setLeft(node7);
        node6.setRight(node8);

        node3.setRight(node5);

        midTraversal(root);
        postorderTraversal(root);
    }


}
