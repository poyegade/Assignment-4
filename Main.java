import java.util.Scanner;


/*
Author: Patric Oyegade
Date: 11/18/2026
Class: Data Structures and Algorithms
Assignment: Assignment 4
File Name: Assignment 4 - Tree

Description:
The goal of this project is to create an AVL tree that stores and tracks word frequencies. It receives a file to
access outside the file directory. Then it lowercases and removes punctuation and adds each word to the AVL tree. To
track how often a word appears in the AVL tree, the frequency counter is incremented by 1. The tree must be balanced
after deletions and insertions to keep the program efficient. The program shows the file's words using in-order and
level-order traversals after processing them.

References: https://learn.zybooks.com/zybook/CSPCSS405DuerreFall2026/chapter/10/section/4
*/


public class Main {

    /**
     * Descrpition: The main method does several actions including, prompt the user for a file,
     * Read text from file, Store words in AVL tree, Store words in AVL tree,
     * Display in-order traversal, Display level-order traversal.
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter file name: ");

        String fileName = keyboard.nextLine();

        AVLTree tree = new AVLTree();

        WordFrequencyAnalyzer.processFile(fileName,tree);

        System.out.println("\n=== In-Order Traversal (Alphabetical) ===");

        tree.inorder();

        System.out.println("\n=== Level-Order Traversal (Tree Structure) ===");

        tree.levelOrder();

        keyboard.close();

    }
}