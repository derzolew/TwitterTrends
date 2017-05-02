package Model;

import java.util.List;
import java.util.Objects;

public class TweetSentiment {

    private double mFinalPositiveness = 0;

    public void setTweetPositiveness() {
        final List<Tweet> tweetList = TweetList.getInstance().getTweetList();


        for (final Tweet aTweetList : tweetList) {
            final String tweetText = aTweetList.getTweetText();
            final String[] parts = tweetText.split("[^a-zA-z']+");
            double positiveness = 0;
            int countEntities = 0;
            for (final String part : parts) {
                for (int j = 0; j < SentimentsList.getInstance().getSentimentList().size(); j++) {
                    final String buf = SentimentsList.getInstance().getSentimentList().get(j).getWord();

                    if (Objects.equals(part, buf)) {
                        positiveness += SentimentsList.getInstance().getSentimentList().get(j).getPositiveness();
                        countEntities++;
                    }

                }
                if (countEntities != 0) {
                    mFinalPositiveness = positiveness / countEntities;
                } else {
                    mFinalPositiveness = 0;
                }

            }
            aTweetList.setPositiveness(mFinalPositiveness);
        }



    }

}
