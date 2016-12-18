package view;

import model.FallingBlocksGame;

import javax.swing.*;
/**
 * Created by Ladislav on 12/15/2016.
 */
public class GameFrame extends JFrame {
    BoardPanel boardPanel;
    public GameFrame(){
        super("Falling Blox");
        boardPanel = new BoardPanel(new FallingBlocksGame());
        this.setContentPane(boardPanel);
        this.setSize(800,630);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameFrame());
     }
}
