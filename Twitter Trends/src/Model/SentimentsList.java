package Model;

import java.util.ArrayList;
import java.util.List;

public final class SentimentsList {

    private static SentimentsList mInstance;
    private final List<Sentiment> mSentimentList = new ArrayList<>();

    private SentimentsList() {}

    public static SentimentsList getInstance() {
        if (mInstance == null) {
            mInstance = new SentimentsList();
        }
        return mInstance;
    }

    public List<Sentiment> getSentimentList() {
        return mSentimentList;
    }


}
