package TreeTraversal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Node structure for the Binary Search Tree
class BSTNode {
    BSTNode left, right;
    int data;

    // Constructor to initialize the node with data
    public BSTNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

// Binary Search Tree class
class BinarySearchTree {
    private BSTNode root;

    // Constructor to initialize an empty tree
    public BinarySearchTree() {
        root = null;
    }

    // Method to insert a new value into the BST
    public void insert(int data) {
        if (!contains(data)) { // Check if value is not already in the tree
            root = insert(root, data);
        }
    }

    // Recursive helper method to insert a new value into the BST
    private BSTNode insert(BSTNode node, int data) {
        if (node == null)
            return new BSTNode(data);
        if (data <= node.data)
            node.left = insert(node.left, data);
        else
            node.right = insert(node.right, data);
        return node;
    }

    // Method to delete a value from the BST
    public void delete(int key) {
        root = delete(root, key);
    }

    // Recursive helper method to delete a value from the BST
    private BSTNode delete(BSTNode root, int key) {
        if (root == null) return root;
        if (key < root.data)
            root.left = delete(root.left, key);
        else if (key > root.data)
            root.right = delete(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.data = minValue(root.right);
            root.right = delete(root.right, root.data);
        }
        return root;
    }

    // Method to find the minimum value in a subtree
    private int minValue(BSTNode root) {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    // Method to check if a value is present in the BST
    public boolean contains(int key) {
        return contains(root, key);
    }

    // Recursive helper method to check if a value is present in the BST
    private boolean contains(BSTNode root, int key) {
        if (root == null) return false;
        if (key == root.data) return true;
        if (key < root.data)
            return contains(root.left, key);
        else
            return contains(root.right, key);
    }

    // Method to perform inorder traversal of the BST
    public void inorder() {
        inorder(root);
    }

    // Recursive helper method to perform inorder traversal of the BST
    private void inorder(BSTNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }
}

// Main class containing the program entry point
public class TreeTraversal {
    public static void main(String[] args) {
        try {
            // Read input from file
            Scanner scanner = new Scanner(new File("input.txt"));
            BinarySearchTree bst = new BinarySearchTree();
            while (scanner.hasNextLine()) {
                // Read each line from input file
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    // Split line by commas to get individual operations
                    String[] commands = line.split(",");
                    for (String command : commands) {
                        String trimmedCommand = command.trim();
                        if (!trimmedCommand.isEmpty()) {
                            // Extract operation and value
                            char op = trimmedCommand.charAt(0);
                            try {
                                int val = Integer.parseInt(trimmedCommand.substring(1).trim());
                                // Perform operation based on the command
                                if (op == '+') {
                                    bst.insert(val);
                                } else if (op == '-') {
                                    bst.delete(val);
                                }
                            } catch (NumberFormatException e) {
                                // Ignore invalid input
                            }
                        }
                    }
                }
            }
            scanner.close();

            // Print the inorder traversal of the BST
            System.out.println("Inorder Traversal:");
            bst.inorder();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}