package tech.dwen.stu.sword;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class T32 {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        // TODO ?生成二叉树
        root.setLeft(node2);
        root.setRight(node3);

        node2.setLeft(node4);
        node2.setRight(node5);

        node3.setLeft(node6);

        List<TreeNode> list = levelTraversal(root);

        list.forEach(e ->{
            Integer value = e.getValue();
            if (value == null) {
                System.out.println("");
            } else {
                System.out.print(value);
            }
        });

    }

    public static List<TreeNode> levelTraversal(TreeNode root){
        List<TreeNode> list = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        Queue<TreeNode> queueNowRow = new LinkedList<>();
        queueNowRow.add(root);

        while (!queueNowRow.isEmpty()) {
            while (!queueNowRow.isEmpty()) {
                TreeNode p = queueNowRow.poll();
                list.add(p);
                TreeNode left = p.getLeft();
                TreeNode right = p.getRight();
                if (left != null) {
                    queue.add(left);
                }
                if (right != null) {
                    queue.add(right);
                }
            }
            list.add(new TreeNode(null));
            while (!queue.isEmpty()) {
                queueNowRow.add(queue.poll());
            }
        }
        return list;
    }
}
