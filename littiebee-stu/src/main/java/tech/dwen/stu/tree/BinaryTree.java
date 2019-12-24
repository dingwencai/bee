package tech.dwen.stu.tree;

public class BinaryTree {

    private TreeNode root;

    /*
     *          1
     *        2   3
     *      4       5
     *        6
     *      7   8
     *
     * */

    public BinaryTree(TreeNode root){
        this.root = root;
    }

    public BinaryTree initTree(TreeNode root){
        return new BinaryTree(root);
    }

    public BinaryTree createTree(){
        return null;
    }

    public int treeDeap(BinaryTree root){
        return 0;
    }

    public BinaryTree parent(){
        return null;
    }

}
