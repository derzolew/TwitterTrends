package View;

import java.awt.BasicStroke;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import Model.ColorProvider;
import Model.StateList;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.KEY_STROKE_CONTROL;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static java.awt.RenderingHints.VALUE_STROKE_PURE;

public class MainView {

    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;

    private static Graphics2D g2;


    private final JFrame mJFrame = new JFrame();

    public MainView() {
        mJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mJFrame.setSize(WIDTH, HEIGHT);

    }

    public void drawMap() {
        mJFrame.setContentPane(new Container() {
            @Override
            public void paint(final Graphics graphics) {
                super.paint(graphics);
                g2 = (Graphics2D) graphics.create();

                g2.setStroke(new BasicStroke(1));

                g2.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(KEY_STROKE_CONTROL, VALUE_STROKE_PURE);

                for (int i = 0; i < StateList.getInstance().getStateList().size(); i++) {
                    g2.setColor(ColorProvider.getColorByValue(
                            StateList.getInstance().getStateList().get(i).getStateSentiment()));
                    for (int j = 0; j < StateList.getInstance().getStateList().get(i).getPath().size(); j++) {
                        //g2.draw(StateList.getInstance().getStateList().get(i).getPath().get(j));
                        g2.fill(StateList.getInstance().getStateList().get(i).getPath().get(j));
                    }
                }
            }
        });
        mJFrame.setVisible(true);
    }

    public void fillColors() {
        if (g2 != null) {
            for (int i = 0; i < StateList.getInstance().getStateList().size(); i++) {
                for (int j = 0; j < StateList.getInstance().getStateList().get(i).getPath().size(); j++) {

                }
            }
        }
    }


}
