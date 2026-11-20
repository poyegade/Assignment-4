// Node class is for the AVL Tree, it stores (String Key, int count) as pairs in the tree.

public class Node {
    // Stores the word from the file
    String key;

    // Store the frequency of the word
    int count;

    // Tracking height for balancing
    int height;

    Node left;
    Node right;


    /**
     * Description: Creates a new node containing a word. Also stores string key and frequency count.
     *
     * @param key
     */
    public Node(String key) {
        this.key = key;
        count = 1;
        height = 1;
    }
}
