package view;

import controller.GameAdapter;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Created by Ladislav on 12/15/2016.
 */

public class BoardPanel extends JPanel {

    private final FallingBlocksGame board;
    private final GameAdapter adapter;
    
    private final static int BLOCK_SIZE = 40;
    private final static int FILL_AREA_SIZE = 39;
    private final static int FIRST_LINE_INDENT = 200;
    private final static int SECOND_LINE_INDENT = 600;

    private final static int FIRST_COLOR_COL= 660;
    private final static int SECOND_COLOR_COL= 700;
    private final static int FIRST_COLOR_ROW= 100;
    private final static int SECOND_COLOR_ROW= 140;

    private final static int SCORE_TEXT_POS_X = 660;
    private final static int SCORE_POS_X = 740;
    private final static int SCORE_POS_Y =  280;

    private final static int BLOCK_POS_CORRECTION = 4;

    private final static int TIMER_DELAY = 20;

    public static int getFirstLineIndent(){return FIRST_LINE_INDENT;}
    public static int getBlockSize(){return BLOCK_SIZE;}
    public static int getBlockPosCorrection(){return BLOCK_POS_CORRECTION;}

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
        Timer timer = new Timer(TIMER_DELAY, timerListener);
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
            g2d.drawLine(FIRST_LINE_INDENT, 0, FIRST_LINE_INDENT, this.getHeight());
            g2d.drawLine(SECOND_LINE_INDENT, 0, SECOND_LINE_INDENT, this.getHeight());

            // Draws Color table on the right side
            g2d.setColor(Color.black);
            g2d.drawRect(FIRST_COLOR_COL, FIRST_COLOR_ROW, BLOCK_SIZE, BLOCK_SIZE);
            g2d.setColor(transmuteColor(board.getColorTable().peekLeftAvailable()));
            g2d.fillRect(FIRST_COLOR_COL, FIRST_COLOR_ROW, FILL_AREA_SIZE, FILL_AREA_SIZE);

            g2d.setColor(Color.black);
            g2d.drawRect(SECOND_COLOR_COL, FIRST_COLOR_ROW, BLOCK_SIZE, BLOCK_SIZE);
            g2d.setColor(transmuteColor(board.getColorTable().peekRightAvailable()));
            g2d.fillRect(SECOND_COLOR_COL, FIRST_COLOR_ROW, FILL_AREA_SIZE, FILL_AREA_SIZE);

            g2d.setColor(Color.black);
            g2d.drawRect(FIRST_COLOR_COL, SECOND_COLOR_ROW, BLOCK_SIZE, BLOCK_SIZE);
            g2d.setColor(transmuteColor(board.getColorTable().peekLeftIncoming()));
            g2d.fillRect(FIRST_COLOR_COL, SECOND_COLOR_ROW, FILL_AREA_SIZE, FILL_AREA_SIZE);

            g2d.setColor(Color.black);
            g2d.drawRect(SECOND_COLOR_COL, SECOND_COLOR_ROW, BLOCK_SIZE, BLOCK_SIZE);
            g2d.setColor(transmuteColor(board.getColorTable().peekRightIncoming()));
            g2d.fillRect(SECOND_COLOR_COL, SECOND_COLOR_ROW, FILL_AREA_SIZE, FILL_AREA_SIZE);

            g2d.setColor(Color.black);
            g2d.drawString("SCORE:", SCORE_TEXT_POS_X, SCORE_POS_Y);
            g2d.drawString( Objects.toString(board.getScore()), SCORE_POS_X, SCORE_POS_Y);
            // Draws all blocks

            for (Block block : board.getActive()) {
                 g2d.setColor(transmuteColor(block.getColor()));
                 g2d.fillRect(block.getPositionX()* BLOCK_POS_CORRECTION + FIRST_LINE_INDENT,
                         block.getPositionY()* BLOCK_POS_CORRECTION, FILL_AREA_SIZE, FILL_AREA_SIZE);
                
                 g2d.setColor(Color.black);
                 g2d.drawRect(block.getPositionX()* BLOCK_POS_CORRECTION + FIRST_LINE_INDENT,
                         block.getPositionY()* BLOCK_POS_CORRECTION, BLOCK_SIZE, BLOCK_SIZE);
            }
            for (int row = 0; row < 15; row++) {
                for (int column = 0; column < 10; column++) {
                    if (board.getGrounded(column, row) != null) {

                        g2d.setColor(transmuteColor(board.getGrounded(column,row).getColor()));
                        g2d.fillRect(column*BLOCK_SIZE + FIRST_LINE_INDENT,
                                row*BLOCK_SIZE, FILL_AREA_SIZE, FILL_AREA_SIZE);

                        g2d.setColor(Color.black);
                        g2d.drawRect(column*BLOCK_SIZE + FIRST_LINE_INDENT,
                                row*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                    }
                }
            }
        }
}
