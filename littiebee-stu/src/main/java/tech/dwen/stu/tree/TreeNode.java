package tech.dwen.stu.tree;

public class TreeNode {
    /**
     * 节点值
     */
    int val;

    /**
     * 左子树
     */
    TreeNode left;

    /**
     * 右子树
     */
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
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
