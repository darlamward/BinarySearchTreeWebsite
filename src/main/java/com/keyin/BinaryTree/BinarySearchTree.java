package com.keyin.BinaryTree;

public class BinarySearchTree {
    private Node root;

    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
        public Node getLeft() {
            return left;
        }
        public void setLeft(Node left) {
            this.left = left;
        }
        public Node getRight() {
            return right;
        }
        public void setRight(Node right) {
            this.right = right;
        }
    }

    public BinarySearchTree() {
        root = null;
    }

    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private Node insertRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = insertRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = insertRecursive(current.right, value);
        }

        return current;
    }

    public Node getRoot() {
        return root;
    }

    public String toJson() {
        if (root == null) {
            return "{}";
        }

        return "{\n" + toJsonRecursive(root, 1) + "\n}";
    }

    private String toJsonRecursive(Node current, int level) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("\n");

        // Add indentation based on the current level
        String indentation = "  ".repeat(level);

        // Add value of the current node
        jsonBuilder.append(indentation).append("\"value\":").append(current.getValue());

        // Add left child
        jsonBuilder.append(",\n").append(indentation).append("\"left\":");
        if (current.getLeft() != null) {
            jsonBuilder.append(toJsonRecursive(current.getLeft(), level + 1));
        } else {
            jsonBuilder.append("null");
        }

        // Add right child
        jsonBuilder.append(",\n").append(indentation).append("\"right\":");
        if (current.getRight() != null) {
            jsonBuilder.append(toJsonRecursive(current.getRight(), level + 1));
        } else {
            jsonBuilder.append("null");
        }

        return jsonBuilder.toString();
    }
}
