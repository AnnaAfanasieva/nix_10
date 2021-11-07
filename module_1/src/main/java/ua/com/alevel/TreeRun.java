package ua.com.alevel;

public class TreeRun {

    public static void main(String[] args) {
        Tree tree = new Tree();
        double doubleNodeValue;

        for (int i = 0; i < 25; i++) {
            doubleNodeValue = 1 + Math.random() * 39;
            tree.insertNode((int) doubleNodeValue);
        }

        tree.printTree();
    }
}