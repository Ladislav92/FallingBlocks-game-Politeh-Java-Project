package model;

import java.util.Random;
/**
 * Created by Ladislav on 12/17/2016.
 */
public class ColorTable {

    public Color[] colors = new Color[4];

    ColorTable(){
        for (int i = 0; i < 4; i++) {
            colors[i] = generateColor();
        }
    }
    // 0 for left side, 1 for right side
    public Color getColor(int i){
        Color color = colors[i];
        colors[i] = colors[i+2];
        colors[2] = generateColor();
        return color;
    }

    private Color generateColor(){
        Random rand = new Random();
        int random = rand.nextInt(3);
        switch (random){
            default:
            case 0: return Color.RED;
            case 1: return Color.BLUE;
            case 2: return Color.GREEN;
        }
    }

}
