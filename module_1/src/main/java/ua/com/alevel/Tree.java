package ua.com.alevel;

import java.util.Stack;

public class Tree {

    private TreeNode rootTreeNode;

    public Tree() {
        rootTreeNode = null;
    }

    public void insertNode(int value) {
        TreeNode newTreeNode = new TreeNode();
        newTreeNode.setValue(value);
        if (rootTreeNode == null) {
            rootTreeNode = newTreeNode;
        } else {
            TreeNode currentTreeNode = rootTreeNode;
            TreeNode parentTreeNode;
            while (true) {
                parentTreeNode = currentTreeNode;
                if (value == currentTreeNode.getValue()) {
                    return;
                } else if (value < currentTreeNode.getValue()) {
                    currentTreeNode = currentTreeNode.getLeftChild();
                    if (currentTreeNode == null) {
                        parentTreeNode.setLeftChild(newTreeNode);
                        return;
                    }
                } else {
                    currentTreeNode = currentTreeNode.getRightChild();
                    if (currentTreeNode == null) {
                        parentTreeNode.setRightChild(newTreeNode);
                        return;
                    }
                }
            }
        }
    }

    public void printTree() {
        Stack globalStack = new Stack();
        globalStack.push(rootTreeNode);
        int gaps = 64;
        boolean isRowEmpty = false;
        int treeDeep = 0;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);
        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (globalStack.isEmpty() == false) {
                TreeNode temp = (TreeNode) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.getValue());
                    localStack.push(temp.getLeftChild());
                    localStack.push(temp.getRightChild());
                    if (temp.getLeftChild() != null ||
                            temp.getRightChild() != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("__");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            gaps /= 2;
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop());
            if (!globalStack.isEmpty()) {
                treeDeep++;
            }
        }
        System.out.println(separator);
        System.out.println("Глубина дерева = " + treeDeep);
    }
}