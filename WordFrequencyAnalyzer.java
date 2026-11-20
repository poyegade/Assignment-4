import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class WordFrequencyAnalyzer {


    /**
     * Description: Process file is responsible for reading words from the file and inserting them into the AVL tree.
     * After reading from the file this method normalizes the words before inserting them into the AVL tree.
     * @param fileName
     * @param tree
     * @throws FileNotFoundException
     */
    public static void processFile(String fileName, AVLTree tree) throws FileNotFoundException {
        Scanner input = new Scanner(new File(fileName));

        while (input.hasNext()) {
            String word = input.next();

            // Convert text to lowercase
            word = word.toLowerCase();

            // Remove punctuation
            word = word.replaceAll("[^a-zA-z]","");

            if (!word.isEmpty()) {
                tree.insert(word);
            }
        }
        input.close();
    }
}