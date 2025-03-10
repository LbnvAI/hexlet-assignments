package exercise;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.io.File;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String path1, String path2, String resultPath) {
        CompletableFuture<String> readFile1 = CompletableFuture.supplyAsync(() -> {
            String file1 = "";
            try {
                file1 = String.join("", Files.readAllLines(Path.of(path1)));
            } catch (Exception e) {
                System.out.println("NoSuchFileException");
            }
            return file1;
        });
        CompletableFuture<String> readFile2 = CompletableFuture.supplyAsync(() -> {
            String file2 = "";
            try {
                file2 = String.join("", Files.readAllLines(Path.of(path2)));
            } catch (Exception e) {
                System.out.println("NoSuchFileException");
            }
            return file2;
        });
        return readFile1.thenCombine(readFile2, (cont1, cont2) -> {
            String result = String.join("\n", cont1, cont2);
            try {
                Files.writeString(Path.of(resultPath), result);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return result;
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN

        // END
    }
}

