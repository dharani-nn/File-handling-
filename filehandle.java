import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileHandlingUtility {

    private static final String FILE_NAME = System.getProperty("user.home") + "/sample.txt";


    public static void main(String[] args) {
        try {
            // 1. Write to file
            writeToFile("This is the first line.\nThis is the second line.");

            // 2. Read from file
            System.out.println("Reading file contents:");
            readFromFile();

            // 3. Modify file (replace a specific word)
            modifyFile("second", "modified");

            // 4. Read again to show changes
            System.out.println("\nAfter modification:");
            readFromFile();

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Writes the provided content to the file. Overwrites if file exists.
     *
     * @param content Text to be written to the file
     * @throws IOException If an I/O error occurs
     */
    public static void writeToFile(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(content);
        }
    }

    /**
     * Reads and prints the content of the file line by line.
     *
     * @throws IOException If an I/O error occurs
     */
    public static void readFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    /**
     * Modifies the file by replacing all occurrences of oldWord with newWord.
     *
     * @param oldWord The word to be replaced
     * @param newWord The word to replace with
     * @throws IOException If an I/O error occurs
     */
    public static void modifyFile(String oldWord, String newWord) throws IOException {
        Path path = Paths.get(FILE_NAME);
        List<String> lines = Files.readAllLines(path);
        List<String> modifiedLines = new ArrayList<>();

        for (String line : lines) {
            modifiedLines.add(line.replace(oldWord, newWord));
        }

        Files.write(path, modifiedLines);
    }
}
