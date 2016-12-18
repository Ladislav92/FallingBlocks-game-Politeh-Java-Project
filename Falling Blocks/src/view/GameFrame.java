package view;

import model.FallingBlocksGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;

/**
 * Created by Ladislav on 12/15/2016.
 */
public class GameFrame extends JFrame {
    BoardPanel boardPanel;
    public GameFrame(){
        super("Falling Blox");
        boardPanel = new BoardPanel(new FallingBlocksGame());
        this.setContentPane(boardPanel);
        this.setSize(800,600);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);

    }
    // Timer test !
    KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyPressed(KeyEvent e) {
            if((e.getKeyCode() == KeyEvent.VK_RIGHT)){
                boardPanel.moveBlock();
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
            if((e.getKeyCode() == KeyEvent.VK_RIGHT)) {
                boardPanel.rectX1 = boardPanel.rectX1;
            }
        }
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameFrame());
     }
}
