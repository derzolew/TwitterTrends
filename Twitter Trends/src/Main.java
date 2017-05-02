import Controller.MainController;
import Model.State;
import Model.StateList;

public final class Main {

    public static void main(final String []args) {

        final MainController mainController = new MainController();

        mainController.threadController();

        //TODO REMAKE
        for (State state : StateList.getInstance().getStateList()) {
            System.out.println(state.getName() + "  " + state.getStateSentiment());
        }


    }
}
