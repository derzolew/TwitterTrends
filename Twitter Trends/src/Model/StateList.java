package Model;

import java.util.ArrayList;
import java.util.List;

public class StateList {
    private static StateList instance;
    private List<State> mStateList = new ArrayList<>();

    public List<State> getStateList() {
        return mStateList;
    }

    private StateList() {}

    public static StateList getInstance() {
        if (instance == null) {
            instance = new StateList();
        }
        return instance;
    }
}
