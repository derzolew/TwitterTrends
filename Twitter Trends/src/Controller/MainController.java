package Controller;

import java.util.HashMap;

import Model.SentimentsParsing;
import Model.StateParsing;
import Model.StateSentiment;
import Model.Tweet;
import Model.TweetList;
import Model.TweetParsing;
import Model.TweetSentiment;
import View.MainView;

public class MainController {

    private TweetParsing mTweetParsing;
    private TweetSentiment mTweetSentiment;
    private SentimentsParsing mSentimentsParsing;
    private StateParsing mStateParsing;
    private StateSentiment mStateSentiment;

    private Thread mThreadTweetParsing;
    private Thread mThreadSentimentsParsing;
    private Thread mThreadStatesParsing;
    private Thread mThreadStateSentiment;
    private Thread mThreadUI;

    private MainView mMainView;

    public void threadController() {
        final double time = System.nanoTime();

        //SYNCED TASKS
        parseTweets();
        parseSentiments();
        parseStates();


        try {
            mThreadTweetParsing.join();
            mThreadStatesParsing.join();
            //mThreadUI.join();
            mThreadSentimentsParsing.join();

            makeStateSentiments();
            mThreadStateSentiment.join();
            makeUI();


        } catch (final InterruptedException pE) {
            pE.printStackTrace();
        }

        System.out.println((System.nanoTime() - time) / 1000000000);
    }

    private void makeStateSentiments() {
        mThreadStateSentiment = new Thread(() -> {
            System.out.println("Thread stateSentiments started...");
            mStateSentiment = new StateSentiment();
            mStateSentiment.countStatePositiveness();
            mStateSentiment.countAverageStatePositiveness();
            System.out.println("Thread stateSentiments terminated.");
        });
        mThreadStateSentiment.start();
    }

    public void parseTweets() {
        mThreadTweetParsing = new Thread(() -> {
            System.out.println("Thread parseTweets started...");

            mTweetParsing = new TweetParsing();
            mTweetParsing.makeTweetList();

            mSentimentsParsing = new SentimentsParsing();
            mSentimentsParsing.makeSentiments();

            System.out.println("Thread parseTweets terminated.");
        });
        mThreadTweetParsing.start();
    }

    public void parseSentiments() {
        try {
            mThreadTweetParsing.join();
        } catch (InterruptedException pE) {
            pE.printStackTrace();
        }
        mThreadSentimentsParsing = new Thread(() -> {
            System.out.println("Thread parseSentiments started...");

            mTweetSentiment = new TweetSentiment();
            mTweetSentiment.setTweetPositiveness();

            System.out.println("Thread parseSentiments terminated.");
        });
        mThreadSentimentsParsing.start();
    }

    public void parseStates() {
        mThreadStatesParsing = new Thread(() -> {
            System.out.println("Thread statesParsing started...");

            mStateParsing = new StateParsing();
            mStateParsing.makeStateCoordsClazz();
            mStateParsing.parseFourpleCoordinates(getFourpleCoordinatesHashMap());
            mStateParsing.parseTripleCoordinates(getTripleCoordinatesHashMap());

            System.out.println("Thread stateParsing terminated.");
        });
        mThreadStatesParsing.start();
    }



    public void makeUI() {
        mThreadUI = new Thread(() -> {
            System.out.println("Thread UI started");

            mMainView = new MainView();
            //mMainView.fillColors();
            mMainView.drawMap();

            System.out.println("Thread UI terminated");
        });
        mThreadUI.start();
    }

    public void printTweetMessageAndPositiveness() {
        for (Tweet tweet : TweetList.getInstance().getTweetList()) {
            if (tweet.getPositiveness() != 0) {
                System.out.println(tweet.getTweetText() + "  " + tweet.getPositiveness());
            }
        }


    }



    private HashMap<String, String[][][]> getTripleCoordinatesHashMap() {
        HashMap<String, String[][][]> hashMap = new HashMap<>();
        hashMap.put("AL", mStateParsing.getStateCoords().getAL());
        hashMap.put("AR", mStateParsing.getStateCoords().getAR());
        hashMap.put("AZ", mStateParsing.getStateCoords().getAZ());
        hashMap.put("CA", mStateParsing.getStateCoords().getCA());
        hashMap.put("CO", mStateParsing.getStateCoords().getCO());
        hashMap.put("CT", mStateParsing.getStateCoords().getCT());
        hashMap.put("DC", mStateParsing.getStateCoords().getDC());
        hashMap.put("DE", mStateParsing.getStateCoords().getDE());
        hashMap.put("FL", mStateParsing.getStateCoords().getFL());
        hashMap.put("GA", mStateParsing.getStateCoords().getGA());
        hashMap.put("IA", mStateParsing.getStateCoords().getIA());
        hashMap.put("ID", mStateParsing.getStateCoords().getID());
        hashMap.put("IL", mStateParsing.getStateCoords().getIL());
        hashMap.put("IN", mStateParsing.getStateCoords().getIN());
        hashMap.put("KS", mStateParsing.getStateCoords().getKS());
        hashMap.put("KY", mStateParsing.getStateCoords().getKY());
        hashMap.put("LA", mStateParsing.getStateCoords().getLA());
        hashMap.put("MA", mStateParsing.getStateCoords().getMA());
        hashMap.put("ME", mStateParsing.getStateCoords().getME());
        hashMap.put("MN", mStateParsing.getStateCoords().getMN());
        hashMap.put("MO", mStateParsing.getStateCoords().getMO());
        hashMap.put("MS", mStateParsing.getStateCoords().getMS());
        hashMap.put("MT", mStateParsing.getStateCoords().getMT());
        hashMap.put("NC", mStateParsing.getStateCoords().getNC());
        hashMap.put("ND", mStateParsing.getStateCoords().getND());
        hashMap.put("NE", mStateParsing.getStateCoords().getNE());
        hashMap.put("NH", mStateParsing.getStateCoords().getNH());
        hashMap.put("NJ", mStateParsing.getStateCoords().getNJ());
        hashMap.put("NM", mStateParsing.getStateCoords().getNM());
        hashMap.put("NV", mStateParsing.getStateCoords().getNV());
        hashMap.put("NY", mStateParsing.getStateCoords().getNY());
        hashMap.put("OH", mStateParsing.getStateCoords().getOH());
        hashMap.put("OK", mStateParsing.getStateCoords().getOK());
        hashMap.put("OR", mStateParsing.getStateCoords().getOR());
        hashMap.put("PA", mStateParsing.getStateCoords().getPA());
        hashMap.put("PR", mStateParsing.getStateCoords().getPR());
        hashMap.put("SC", mStateParsing.getStateCoords().getSC());
        hashMap.put("SD", mStateParsing.getStateCoords().getSD());
        hashMap.put("TN", mStateParsing.getStateCoords().getTN());
        hashMap.put("TX", mStateParsing.getStateCoords().getTX());
        hashMap.put("UT", mStateParsing.getStateCoords().getUT());
        hashMap.put("VT", mStateParsing.getStateCoords().getVT());
        hashMap.put("WI", mStateParsing.getStateCoords().getWI());
        hashMap.put("WV", mStateParsing.getStateCoords().getWV());
        hashMap.put("WY", mStateParsing.getStateCoords().getWY());
        return hashMap;
    }

    private HashMap<String, String[][][][]> getFourpleCoordinatesHashMap() {
        HashMap<String, String[][][][]> hashMap = new HashMap<>();
        hashMap.put("AK", mStateParsing.getStateCoords().getAK());
        hashMap.put("HI", mStateParsing.getStateCoords().getHI());
        hashMap.put("MD", mStateParsing.getStateCoords().getMD());
        hashMap.put("MI", mStateParsing.getStateCoords().getMI());
        hashMap.put("RI", mStateParsing.getStateCoords().getRI());
        hashMap.put("VA", mStateParsing.getStateCoords().getVA());
        hashMap.put("WA", mStateParsing.getStateCoords().getWA());
        return hashMap;
    }

}
