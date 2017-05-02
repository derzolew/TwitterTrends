package Model;

import com.google.gson.Gson;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StateParsing {
    private State mState;
    private StateCoords mStateCoords;

    public StateParsing() {
    }

    public void parseTripleCoordinates(HashMap<String, String[][][]> pStringHashMap) {
        Set set = pStringHashMap.entrySet();
        Iterator iterator= set.iterator();

        while(iterator.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) iterator.next();

            String[][][] stateCoords = (String[][][]) mapEntry.getValue();

            List<List<Point2D.Double>> stateBlockList = new ArrayList<>();

            for (int i = 0; i < stateCoords.length; i++) {

                List<Point2D.Double> blockPointsList = new ArrayList<>();
                for (int j = 0; j < stateCoords[i].length; j++) {
                    blockPointsList.add(new Point2D.Double(Double.parseDouble(stateCoords[i][j][0]),
                                        Double.parseDouble(stateCoords[i][j][1])));
                }
                stateBlockList.add(blockPointsList);
            }

            StateList.getInstance().getStateList().add(new State((String) mapEntry.getKey(), stateBlockList));
        }
    }


    public void parseFourpleCoordinates(HashMap<String, String[][][][]> pStringHashMap) {
        Set set = pStringHashMap.entrySet();
        Iterator iterator = set.iterator();

        while(iterator.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) iterator.next();

            String[][][][] stateCoords = (String[][][][]) mapEntry.getValue();

            List<List<Point2D.Double>> stateBlockList = new ArrayList<>();

            for (int i = 0; i < stateCoords.length; i++) {

                List<Point2D.Double> blockPointsList = new ArrayList<>();

                for (int j = 0; j < stateCoords[i].length; j++) {

                    for (int k = 0; k < stateCoords[i][j].length; k++) {
                        blockPointsList.add(new Point2D.Double(Double.parseDouble(stateCoords[i][j][k][0]),
                                Double.parseDouble(stateCoords[i][j][k][1])));
                    }
                }
                stateBlockList.add(blockPointsList);
            }

            StateList.getInstance().getStateList().add(new State((String) mapEntry.getKey(), stateBlockList));
        }
    }


    public void makeStateCoordsClazz() {
        final Gson gson = new Gson();
        mStateCoords = gson.fromJson(getJson(Constants.FILE_JSON), StateCoords.class);
    }

    public StateCoords getStateCoords() {
        return mStateCoords;
    }


    private String getJson(final String filePath) {
        String json = null;
        try {
            json = FileProcessing.readAllFile(filePath, StandardCharsets.UTF_8);
        } catch (final IOException pE) {
            pE.printStackTrace();
        }
        return json;
    }
}
