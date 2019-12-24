package tech.dwen.stu.sword;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by MrCai on 2019/12/23.
 */
public class T1 {

    public static void main(String[] args) {
        int k = 6;
        TreeNode root = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node3 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        root.setLeft(node3);
        root.setRight(node6);

        node3.setLeft(node2);
        node3.setRight(node4);

        node2.setLeft(node1);

        node6.setRight(node7);

        TreeNode n = get(root, k);
        if (n != null) {
            System.out.println(n.getValue());
        } else {
            System.out.println("k 超出树节点数");
        }

    }

    public static TreeNode get(TreeNode root,Integer k) {
        // 中序遍历二叉搜索树，放入一个List
        List<TreeNode> list = traversal(root);

        // k 小于等于树节点的个数
        if(k > list.size()) return null;
        else return list.get(k - 1);
    }

    public static List<TreeNode> traversal(TreeNode root){

        ArrayList<TreeNode> list = new ArrayList<TreeNode>();

        Stack<TreeNode> stack = new Stack<TreeNode>();

        TreeNode p = root;

        while (p != null || !stack.isEmpty()) {

            while (p != null) {
                stack.push(p);
                p = p.getLeft();
            }

            if (!stack.isEmpty()) {
                p = stack.pop();
                list.add(p);
                p = p.getRight();
            }
        }
        return list;
    }
}



class TreeNode{
    private Integer value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
