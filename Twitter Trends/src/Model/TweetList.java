package Model;

import java.util.ArrayList;
import java.util.List;

public final class TweetList {

    private static TweetList mInstance;
    private final List<Tweet> mTweetList = new ArrayList<>();

    private TweetList () {}

    public static TweetList getInstance() {

        if (mInstance == null) {
            mInstance = new TweetList();
        }

        return mInstance;
    }

    public List<Tweet> getTweetList() {
        return mTweetList;
    }


}
