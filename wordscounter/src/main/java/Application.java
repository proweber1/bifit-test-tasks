import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please, enter filename and word for count");
            return;
        }
        tryCountWordsInFile(args[0], args[1]);
    }

    private static void tryCountWordsInFile(String fileName, String wordForCount) {
        try {
            final long countWordsInFile = new WordCounter(fileName, wordForCount).countWords();

            System.out.println(countWordsInFile);
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }
}
