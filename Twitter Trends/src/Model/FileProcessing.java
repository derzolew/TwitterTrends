package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class FileProcessing {

    private static File mFile;

    public static List<String> readFile(final String fileName) throws FileNotFoundException {
        final List<String> lineList = new ArrayList<>();
        checkIfFileExists(fileName);

        try {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(mFile.getAbsoluteFile()))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    lineList.add(line);
                }

            }

        } catch (final IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return lineList;
    }

    public static String readAllFile(final String filename, final Charset pCharset) throws IOException {
        final byte[] encoded = Files.readAllBytes(Paths.get(filename));
        return new String(encoded, pCharset);
    }

    private static void checkIfFileExists(final String fileName) throws FileNotFoundException {
        mFile = new File(fileName);
        if (!mFile.exists()) {
            throw new FileNotFoundException(mFile.getName());
        }
    }
}