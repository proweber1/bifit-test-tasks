import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

class WordCounter {
    private final String fileName;
    private final String wordForCount;

    WordCounter(String fileName, String wordForCount) {
        this.fileName = Objects.requireNonNull(fileName, "file name must be not null");
        this.wordForCount = Objects.requireNonNull(wordForCount, "word for count must be not null");
    }

    long countWords() throws IOException {
        /*
        Хотел сделать кеш к этому методу, чтобы при каждом вызове этого метода
        файл не читался заново, но если бы я так сделал, то изменения в файле не
        подхватывались бы, можно было придумать всякие механизмы которые бы
        инвалидировали бы кеш, но я не стал придумывать их
         */
        try (Stream<String> line = Files.lines(Paths.get(fileName))) {
            return countWordsInLineStream(line);
        }
    }

    private long countWordsInLineStream(Stream<String> lines) {
        return lines
                .flatMap(this::getWordsFromString)
                .filter(str -> str.equalsIgnoreCase(wordForCount))
                .count();
    }

    private Stream<String> getWordsFromString(String input) {
        return Arrays.stream(input.split("\\W+"));
    }
}
