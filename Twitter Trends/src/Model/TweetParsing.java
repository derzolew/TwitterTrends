package Model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TweetParsing {

    private List<String> mLineList = new ArrayList<>();

    public TweetParsing() {
        try {

            mLineList.addAll(FileProcessing.readFile(Constants.FILE_SANDWICH));
            mLineList.addAll(FileProcessing.readFile(Constants.FILE_TEXAS));
            mLineList.addAll(FileProcessing.readFile(Constants.FILE_OBAMA));
            mLineList.addAll(FileProcessing.readFile(Constants.FILE_JOB));
            mLineList.addAll(FileProcessing.readFile(Constants.FILE_LIFE));

        } catch (final FileNotFoundException pE) {
            pE.getMessage();
        }
    }

    public void makeTweetList(){
        for (final String tweet : mLineList) {
            try {
                TweetList.getInstance().getTweetList().add(parseTweetLine(tweet));
            } catch (ArrayIndexOutOfBoundsException pE) {
                pE.printStackTrace();
            }
        }
    }


    private Tweet parseTweetLine(final String line) {
        final String []lineParts = line.split("\t");
        return new Tweet(parseDate(lineParts[2]), parseText(lineParts[3].toLowerCase()),
                parseCoordinates(lineParts[0]));

    }

    private Tweet.Coordinates parseCoordinates(String coordinates) {
        coordinates = coordinates.substring(1, coordinates.length() - 1);
        final String []splittedCoordinates = coordinates.split(", ");
        return new Tweet.Coordinates(Double.parseDouble(splittedCoordinates[0]),
                Double.parseDouble(splittedCoordinates[1]));
    }

    private String parseDate(final String date) {
        return date;
    }

    private String parseText(final String tweetText) {
        return tweetText;
    }


}
