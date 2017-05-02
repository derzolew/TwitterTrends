package Model;

public class Tweet {

    private String mDate;
    private String mTweetText;
    private Coordinates mCoordinates;
    private double mPositiveness;



    public double getPositiveness() {
        return mPositiveness;
    }

    public void setPositiveness(double pPositiveness) {
        mPositiveness = pPositiveness;
    }


    Tweet(final String pDate, final String pTweetText, final Coordinates pCoordinates) {
        mDate = pDate;
        mTweetText = pTweetText;
        mCoordinates = pCoordinates;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String pDate) {
        mDate = pDate;
    }

    public String getTweetText() {
        return mTweetText;
    }

    public void setTweetText(String pTweetText) {
        mTweetText = pTweetText;
    }

    public Coordinates getCoordinates() {
        return mCoordinates;
    }

    public void setCoordinates(Coordinates pCoordinates) {
        mCoordinates = pCoordinates;
    }

    public static class Coordinates {
        private double xAxis;
        private double yAxis;

        Coordinates(final double pXAxis, final double pYAxis) {
            xAxis = pXAxis;
            yAxis = pYAxis;
        }

        public double getxAxis() {
            return xAxis;
        }

        public void setxAxis(final double pXAxis) {
            xAxis = pXAxis;
        }

        public double getyAxis() {
            return yAxis;
        }

        public void setyAxis(final double pYAxis) {
            yAxis = pYAxis;
        }
    }
}
