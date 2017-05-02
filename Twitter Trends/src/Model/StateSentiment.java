package Model;

import java.awt.geom.Point2D;
import java.util.List;

public class StateSentiment {

    private List<State> mStateList = StateList.getInstance().getStateList();
    private List<Tweet> mTweetList = TweetList.getInstance().getTweetList();

    public void countStatePositiveness() {
        for (Tweet tweet : mTweetList) {
            Point2D.Double point = new Point2D.Double(tweet.getCoordinates().getyAxis(),
                                                      tweet.getCoordinates().getxAxis());


            for (State state : mStateList) {
                for (int i = 0; i < state.getOriginalPath().size(); i++) {

                    if (state.getOriginalPath().get(i).contains(point)) {
                        state.setStateSentiment(tweet.getPositiveness());
                    }
                }
            }
        }
    }

    public void countAverageStatePositiveness() {
        for(State state : mStateList) {
            state.countAverageStateSentiment();
        }
    }
}
