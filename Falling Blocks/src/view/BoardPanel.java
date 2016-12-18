package view;

import controller.GameAdapter;
import model.Block;
import model.FallingBlocksGame;
import javax.swing.*;
import java.awt.*;
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
        // initialiseGame function in controller class?
        board.initialiseGroud();
        board.spawnBlock();
        // everything inside if case in another function in controller class?
        ActionListener timerListener = event -> {
            if (!board.gameOver()) {
                    repaint();
                    for (int i = 0; i < board.active.size(); i++) {
                        Block block = board.active.get(i);
                        if (board.isGrounded(block)) {
                            board.setGround(block.getPositionX(), block.getPositionY(), block, -10);  // zaustavlja ga, stavlja u matricu i                                                // podiže tlo
                            board.active.remove(block); // remove i ?
                            if (block.getID() == Block.getNextID() - 1) {                           //ako je zadnji koji se pojavio
                                board.spawnBlock();                                                   // pojavi novi
                            }
                            if (board.active.size() < 2) {
                                board.checkMatch(block);
                            }
                        }else {
                            block.fall();                                                       // ako nije na tlu, pada
                        }
                    }
                }
        };
        Timer timer = new Timer(20, timerListener);
        timer.setRepeats(true);
        timer.start();
    }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawLine(200,0,200,700);
            g2d.drawLine(600,0,600,700);
            for (Block block : board.active) {
                //g2d.drawRect(block.getPositionX()*4+200, block.getPositionY()*4, 40, 40);
                switch (block.getColor()){
                    case BLANK: g2d.setColor(Color.WHITE);break;
                    case RED:   g2d.setColor(Color.RED);break;
                    case BLUE:  g2d.setColor(Color.BLUE);break;
                    case GREEN: g2d.setColor(Color.GREEN);break;

                }g2d.fillRect(block.getPositionX()*4+200, block.getPositionY()*4, 39, 39);
                g2d.setColor(Color.black);
                 g2d.drawRect(block.getPositionX()*4+200, block.getPositionY()*4, 40, 40);
            }
            for (int k = 0; k < 15; k++) {
                for (int j = 0; j < 10; j++) {
                    if (board.grounded[j][k] != null) {
                        switch (board.grounded[j][k].getColor()){
                            case BLANK: g2d.setColor(Color.WHITE);break;
                            case RED:   g2d.setColor(Color.RED);break;
                            case BLUE:  g2d.setColor(Color.BLUE);break;
                            case GREEN: g2d.setColor(Color.GREEN);break;
                        }
                        g2d.fillRect(board.grounded[j][k].getPositionX()*4+200, board.grounded[j][k].getPositionY()*4, 39, 39);
                        g2d.setColor(Color.black);
                        g2d.drawRect(board.grounded[j][k].getPositionX()*4+200, board.grounded[j][k].getPositionY()*4, 40, 40);
                    }
                }
            }
        }
}
