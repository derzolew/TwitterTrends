package Model;

import java.awt.Color;

public class ColorProvider {

    public static double normalizeNumber(double value) {
        return value / Math.sqrt(1 + value * value);
    }

    public static Color getColorByValue(double value) {
        //value = Math.max(0, Math.min(1, value));
        int color = (int)(value * 255);

        if (value < 0) {
            return new Color(Math.abs(color), 0, 0);
        } else {
            return new Color(0, color, 0);
        }
    }
}
