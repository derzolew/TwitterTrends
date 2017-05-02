package Model;

import java.io.FileNotFoundException;
import java.util.List;

public class SentimentsParsing {
    private List<String> mUnParsedLines;

    public SentimentsParsing() {
        try {
            mUnParsedLines = FileProcessing.readFile(Constants.FILE_SENTIMENTS);
        } catch (final FileNotFoundException pE) {
            pE.printStackTrace();
        }
    }

    public void makeSentiments() {
        for (String line : mUnParsedLines) {
            SentimentsList.getInstance().getSentimentList().add(parseLines(line));
        }
    }

    private Sentiment parseLines(String line) {
        final String []parts = line.split(",");
        return new Sentiment(parts[0], Float.parseFloat(parts[1]));
    }
}
