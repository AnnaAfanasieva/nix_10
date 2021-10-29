package ua.com.alevel;

public class TreeNode {
    private int value;
    private TreeNode leftChild;
    private TreeNode rightChild;

    public int getValue() {
        return this.value;
    }

    public void setValue(final int value) {
        this.value = value;
    }

    public TreeNode getLeftChild() {
        return this.leftChild;
    }

    public void setLeftChild(final TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode getRightChild() {
        return this.rightChild;
    }

    public void setRightChild(final TreeNode rightChild) {
        this.rightChild = rightChild;
    }
}