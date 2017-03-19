package model;

import java.util.Random;

/**
 * Created by Ladislav on 12/17/2016.
 */
public class ColorTable {

    // maybe using collections
    private Color[] leftColorQue = new Color[2];
    private Color[] rightColorQue = new Color[2];

    ColorTable() {
        populateColorTable();
    }

    public Color peekLeftIncoming() {
        return leftColorQue[1];
    }

    public Color peekLeftAvailable() {
        return leftColorQue[0];
    }

    public Color peekRightIncoming() {
        return rightColorQue[1];
    }

    public Color peekRightAvailable() {
        return rightColorQue[0];
    }

    public Color pollLeftColor() {
        Color temp = leftColorQue[0];
        leftColorQue[0] = leftColorQue[1];
        leftColorQue[1] = generateColor();
        return temp;
    }

    public Color pollRightColor() {
        Color temp = rightColorQue[0];
        rightColorQue[0] = rightColorQue[1];
        rightColorQue[1] = generateColor();
        return temp;
    }

    private void populateColorTable() {
        for (int i = 0; i < 2; i++) {
            leftColorQue[i] = generateColor();
            rightColorQue[i] = generateColor();
        }
    }

    private Color generateColor() {
        Random rand = new Random();
        int random = rand.nextInt(3);
        switch (random) {
            default:
            case 0:
                return Color.RED;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.GREEN;
        }
    }

}
