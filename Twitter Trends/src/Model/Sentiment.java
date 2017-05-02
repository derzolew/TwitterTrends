package Model;

public class Sentiment {
    private String mWord;
    private float mPositiveness;

    public Sentiment(final String pWord, final float pPositiveness) {
        mWord = pWord;
        mPositiveness = pPositiveness;
    }

    public String getWord() {
        return mWord;
    }

    public void setWord(final String pWord) {
        mWord = pWord;
    }

    public float getPositiveness() {
        return mPositiveness;
    }

    public void setPositiveness(final float pPositiveness) {
        mPositiveness = pPositiveness;
    }
}
