
import java.util.LinkedList;
import java.util.Queue;


// This class implements an AVL Tree for storing word frequencies.

public class AVLTree {

    private Node root; // Root node of the tree.

    /**
     * Method: insert
     *
     * Description: inserts a word into the AVL tree if the word already exists, its frequency count is incremented.
     *
     * @param key Word to insert.
     */
    public void insert(String key) {
        root = insert(root, key);
    }

    /**
     * Method: insert
     *
     * Description: Executes BST insertion, updates node heights, and rebalances tree if needed.
     *
     * @param node Current subtree root.
     * @param key Word being inserted.
     * @return Updated subtree root.
     */
    private Node insert(Node node, String key) {

        if (node == null) {
            return new Node(key); // Create a new word entry.
        }

        int comparison = key.compareTo(node.key);

        if (comparison < 0) {

            node.left = insert(node.left, key); // Insert on the left.

        } else if (comparison > 0) {

            node.right = insert(node.right, key); // Insert on the right.

        } else {

            // Increment count when word already exists.
            node.count++;

            return node;
        }

        // Update the height after insertion.
        node.height = 1 + Math.max(height(node.left), height(node.right));

        return rebalance(node); // Restore AVL balance.
    }

    /**
     * Method: search
     * Description: Searches for a word in the AVL tree.
     *
     * @param key Word being searched.
     * @return Frequency count or 0 if not found.
     */
    public int search(String key) {

        Node current = root;

        while (current != null) {

            int comparison = key.compareTo(current.key);

            if (comparison == 0) {
                return current.count; // Return the word's frequency.
            }

            if (comparison < 0) {
                current = current.left; // Search the left subtree.
            } else {
                current = current.right; // Search the right subtree.
            }
        }

        return 0; // Word was not found.
    }

    /**
     * Method: delete
     * Description: Removes a word from the AVL tree.
     *
     * @param key Word to remove.
     */
    public void delete(String key) {
        root = delete(root, key);
    }

    /**
     * Method: delete
     * Description: Recursive helper method for deletion.
     *
     * @param node Current subtree root.
     * @param key Word being removed.
     * @return Updated subtree root.
     */
    private Node delete(Node node, String key) {

        if (node == null) {
            return null; // Word was not found.
        }

        if (key.compareTo(node.key) < 0) {

            node.left = delete(node.left, key); // Continue searching left.

        } else if (key.compareTo(node.key) > 0) {

            node.right = delete(node.right, key); // Continue searching right.

        } else {

            // Replace node if it has only one child or no children.
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            // Find the smallest node in the right subtree.
            Node successor = findMinimum(node.right);

            // Copy the successor's word and frequency.
            node.key = successor.key;
            node.count = successor.count;

            // Remove the original successor node.
            node.right = delete(node.right, successor.key);
        }

        // Update height after deletion.
        node.height = 1 + Math.max(height(node.left), height(node.right));

        return rebalance(node); // Restore AVL balance.
    }

    /**
     * Method: findMinimum
     *
     * Description: Finds the smallest node in a subtree.
     *
     * @param node Starting node.
     * @return Smallest node.
     */
    private Node findMinimum(Node node) {

        // Keep moving left to find the smallest value.
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    /**
     * Method: height
     * Description: The height calculates the balance factor and decided if rotations are necessary.
     *
     * @param node Node being examined.
     * @return Height or 0 if null.
     */
    private int height(Node node) {

        if (node == null) {
            return 0; // Empty nodes have a height of 0.
        }

        return node.height;
    }

    /**
     * Method: getBalance
     *
     * Description: This method calculates the balance factor of a node.
     * This is because AVL trees must be rebalanced after inserting or deleting.
     *
     * @param node The node being checked.
     * @return Balance factor.
     */
    private int getBalance(Node node) {

        if (node == null) {
            return 0;
        }

        // Compare the heights of the left and right subtrees.
        return height(node.left) - height(node.right);
    }

    /**
     * Method: rebalance
     *
     * Description: Restores AVL balance after insertions
     * and deletions.
     *
     * @param node Node being checked.
     * @return Balanced subtree root.
     */
    private Node rebalance(Node node) {

        int balance = getBalance(node);

        // Left-heavy subtree.
        if (balance > 1) {

            // Check for a left-right imbalance.
            if (getBalance(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }

            return rotateRight(node); // Fix left-heavy imbalance.
        }

        // Right-heavy subtree.
        if (balance < -1) {

            // Check for a right-left imbalance.
            if (getBalance(node.right) > 0) {
                node.right = rotateRight(node.right);
            }

            return rotateLeft(node); // Fix right-heavy imbalance.
        }

        return node; // No rotation needed.
    }

    /**
     * Method: rotateLeft
     * Description: Performs a left rotation, only when the right side of the tree is heavier.
     *
     * @param x Unbalanced node.
     * @return New subtree root.
     */
    private Node rotateLeft(Node x) {

        Node y = x.right;
        Node t2 = y.left;

        // Move nodes to perform the left rotation.
        y.left = x;
        x.right = t2;

        // Update heights after rotation.
        x.height = 1 + Math.max(height(x.left), height(x.right));

        y.height = 1 + Math.max(height(y.left), height(y.right));

        return y;
    }

    /**
     * Method: rotateRight
     * Description: Performs a right rotation, rebalances the tree if the left subtree has more nodes.
     *
     * @param y Unbalanced node.
     * @return New subtree root.
     */
    private Node rotateRight(Node y) {

        Node x = y.left;
        Node t2 = x.right;

        // Move nodes to perform the right rotation.
        x.right = y;
        y.left = t2;

        // Update heights after rotation.
        y.height = 1 + Math.max(height(y.left), height(y.right));

        x.height = 1 + Math.max(height(x.left), height(x.right));

        return x;
    }

    /**
     * Method: inorder
     * Description: This method outputs word using inorder traversal.
     * Words are outputted in alphabetical order
     *
     */
    public void inorder() {
        inorder(root);
    }

    /**
     * Method: inorder
     *
     * Description: Recursive helper method for in-order traversal.
     *
     * @param node Current node.
     */
    private void inorder(Node node) {

        if (node != null) {

            inorder(node.left); // Visit left subtree first.

            System.out.println(
                    node.key + ": " + node.count); // Print word and frequency.

            inorder(node.right); // Visit right subtree.
        }
    }

    /**
     * Method: levelOrder
     * Description: Displays the tree level-by-level using a queue. This supports level order traversal because
     * queue and level order process items the same way.
     */
    public void levelOrder() {

        if (root == null) {
            return; // Nothing to display.
        }

        Queue<Node> queue = new LinkedList<>();

        queue.add(root); // Add the root node first.

        while (!queue.isEmpty()) {

            Node current = queue.poll(); // Get the next node.

            System.out.println(
                    current.key + ": " + current.count);

            // Add left child to the queue if it exists.
            if (current.left != null) {
                queue.add(current.left);
            }

            // Add right child to the queue if it exists.
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }
}
