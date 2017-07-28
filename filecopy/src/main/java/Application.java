import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class Application {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please enter source and target file");
            return;
        }

        copyFileWithMetrics(args[0], args[1]);
    }

    /**
     * Используем Java 7 Path, я посмотрел бенчмарки, это один из самых
     * быстрых способов копирования, смотрел тут
     *
     * <a href="https://baptiste-wicht.com/posts/2010/08/file-copy-in-java-benchmark.html">
     *     File copy benchmark
     * </a>
     *
     * @param source Исходный файл
     * @param target Новый файл
     */
    private static void copyFileWithMetrics(String source, String target) {
        try {
            final long startCopyTime = System.nanoTime();
            copyFile(source, target);
            final long elapsedTime = System.nanoTime() - startCopyTime;
            final long copyTime = TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);

            System.out.printf("Время копирования: %dмс%n", copyTime);
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    private static void copyFile(String source, String target) throws IOException {
        /*
        Копировал с помощью буферов/каналов и Java 7 Files. На моей машине
        MacBook Pro 2015 быстрее всего работает Files.copy, копировал файл
        размером 1ГБ, заняло это все 2181мс, с помощью каналов это заняло
        2567мс, с помощью файл стримов 3321мс
         */
        Files.copy(Paths.get(source), Paths.get(target));
    }
}
