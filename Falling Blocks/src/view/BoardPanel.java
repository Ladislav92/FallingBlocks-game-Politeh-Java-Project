package view;

import controller.GameAdapter;
import model.Block;
import model.FallingBlocksGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Created by Ladislav on 12/15/2016.
 */
public class BoardPanel extends JPanel {

    private final FallingBlocksGame board;
    private final GameAdapter adapter;

    BoardPanel(FallingBlocksGame board){
        super();
        this.board = board;
        adapter = new GameAdapter(board, this);
        this.addMouseListener(adapter);
        this.setVisible(true);
        ActionListener timerListener = event -> {
                repaint();
        };
        Timer timer = new Timer(20, timerListener);
        timer.setRepeats(true);
        timer.start();
    }

        //timer test
        int rectX1 = 0, rectY1 = 40;

        public void moveBlock(){
            rectX1 += 1;
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawLine(200,0,200,600);
            g2d.drawLine(600,0,600,600);
            // Timer test
            g2d.drawRect(rectX1,rectY1,40,40);

            // Where to put startGame() function here in
            // BoardPanel in paint ? or somehow in controller?

            for (Block block : board.active) {
                g2d.drawRect(block.getPositionX(), block.getPositionY(), 40, 40);
            }
            for (int k = 0; k < 15; k++) {
                for (int j = 0; j < 10; j++) {
                    if (board.grounded[j][k] != null) {
                        g2d.drawRect(board.grounded[j][k].getPositionX() + 200, board.grounded[j][k].getPositionY(), 40, 40);
                    }
                }
            }

            //for each in grounded draw block !!!


        }

        //paint game -> override paint method from JPanel
    // paint 2 strokes to put game into it
    //on the right side put score on top
    //on the right side under score paint ColorBoard
    //paint all active blocks and all grounded blocks
}
