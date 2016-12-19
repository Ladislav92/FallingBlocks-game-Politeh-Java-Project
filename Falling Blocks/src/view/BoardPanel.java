package view;

import controller.GameAdapter;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionListener;

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
            if (!board.gameOver()) {
                    adapter.gameFlow();
                    repaint();

            }
        };
        Timer timer = new Timer(20, timerListener);
        timer.setRepeats(true);
        timer.start();
    }
        // Transmutes enum Color from Block to AWT color for GUI
        private Color transmuteColor(model.Color color){
            switch (color){
                default:
                case BLANK: return Color.WHITE;
                case RED:   return Color.RED;
                case BLUE:  return Color.BLUE;
                case GREEN: return Color.GREEN;
            }
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawLine(200,0,200,700);
            g2d.drawLine(600,0,600,700);

            // Draws Color table on the right side
            g2d.setColor(Color.black);
            g2d.drawRect(660, 100, 40, 40);
            g2d.setColor(transmuteColor(board.colorTable.colors[0]));
            g2d.fillRect(660, 100, 39, 39);

            g2d.setColor(Color.black);
            g2d.drawRect(700, 100, 40, 40);
            g2d.setColor(transmuteColor(board.colorTable.colors[1]));
            g2d.fillRect(700, 100, 39, 39);

            g2d.setColor(Color.black);
            g2d.drawRect(660, 140, 40, 40);
            g2d.setColor(transmuteColor(board.colorTable.colors[2]));
            g2d.fillRect(660, 140, 39, 39);

            g2d.setColor(Color.black);
            g2d.drawRect(700, 140, 40, 40);
            g2d.setColor(transmuteColor(board.colorTable.colors[3]));
            g2d.fillRect(700, 140, 39, 39);

            // Draws all blocks
            for (Block block : board.active) {
                 g2d.setColor(transmuteColor(block.getColor()));
                 g2d.fillRect(block.getPositionX()*4+200, block.getPositionY()*4, 39, 39);
                 g2d.setColor(Color.black);
                 g2d.drawRect(block.getPositionX()*4+200, block.getPositionY()*4, 40, 40);
            }
            for (int k = 0; k < 15; k++) {
                for (int j = 0; j < 10; j++) {
                    if (board.grounded[j][k] != null) {
                        g2d.setColor(transmuteColor(board.grounded[j][k].getColor()));
                        g2d.fillRect(board.grounded[j][k].getPositionX()*4+200, board.grounded[j][k].getPositionY()*4, 39, 39);
                        g2d.setColor(Color.black);
                        g2d.drawRect(board.grounded[j][k].getPositionX()*4+200, board.grounded[j][k].getPositionY()*4, 40, 40);
                    }
                }
            }
        }
}
