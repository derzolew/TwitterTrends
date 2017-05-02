package Model;

import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class State {
    private String mName;
    private List<Path2D.Double> mPath = new ArrayList<>();
    private List<Path2D.Double> mOriginalPath = new ArrayList<>();
    private List<List<Point2D.Double>> mPointList;
    private int mTweetCounter = 0;


    public void setStateSentiment(double pStateSentiment) {
        stateSentiment += pStateSentiment;
        mTweetCounter++;
    }

    public void countAverageStateSentiment() {
        if (mTweetCounter != 0) {
            stateSentiment /= mTweetCounter;
            stateSentiment *= 10;
            stateSentiment = ColorProvider.normalizeNumber(stateSentiment);
        }
    }

    private double stateSentiment = 0;

    public double getStateSentiment() {
        return stateSentiment;
    }

    public List<List<Point2D.Double>> getPointList() {
        return mPointList;
    }

    public List<Path2D.Double> getOriginalPath() {
        return mOriginalPath;
    }

    public void setPointList(final List<List<Point2D.Double>> pPointList) {
        mPointList = pPointList;
    }

    public State() {}

    public State(final String pName, final List<Path2D.Double> pPath, final List<List<Point2D.Double>> pPoint) {
        mName = pName;
        mPath = pPath;
        mPointList = pPoint;
    }

    public State(String pName, List<List<Point2D.Double>> pPointList) {
        mName = pName;
        mPointList = pPointList;
        makePolygons(pPointList, mPath);
    }



    private void makePolygons(List<List<Point2D.Double>> pPointList, List<Path2D.Double> pPath) {
        for (int i = 0; i < pPointList.size(); i++) {
            Path2D.Double polygon = new Path2D.Double();
            Path2D.Double originalPolygon = new Path2D.Double();

            originalPolygon.moveTo(pPointList.get(i).get(0).getX(), pPointList.get(i).get(0).getY());

            polygon.moveTo((180 - Math.abs(pPointList.get(i).get(0).getX()))*13,
                    ((90 - pPointList.get(i).get(0).getY())*13));
            for (int j = 0; j < pPointList.get(i).size(); j++) {
                originalPolygon.lineTo(pPointList.get(i).get(j).getX(), pPointList.get(i).get(j).getY());
                polygon.lineTo((180 - Math.abs(pPointList.get(i).get(j).getX()))*13,
                        ((90 - pPointList.get(i).get(j).getY())*13));
            }
            originalPolygon.closePath();
            polygon.closePath();
            mPath.add(polygon);
            mOriginalPath.add(originalPolygon);
        }   
    }


    public String getName() {
        return mName;
    }

    public void setName(final String pName) {
        mName = pName;
    }

    public List<Path2D.Double> getPath() {
        return mPath;
    }

    public void setPath(final List<Path2D.Double> pPath) {
        mPath = pPath;
    }

}
