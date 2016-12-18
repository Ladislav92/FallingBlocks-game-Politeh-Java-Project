package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ladislav on 12/17/2016.
 */
public class ColorTableView extends JComponent{

    //Delegation ? -> paintColorTable(Graphics g){}
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d =(Graphics2D)g;
    }
}
