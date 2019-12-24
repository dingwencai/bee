package tech.dwen.stu.tree;

public class TreeNode {
    /**
     * 节点值
     */
    private int val;

    /**
     * 左子树
     */
    private TreeNode left;

    /**
     * 右子树
     */
    private TreeNode right;

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
