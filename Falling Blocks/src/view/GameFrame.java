package view;

import model.FallingBlocksGame;

import javax.swing.*;

/**
 * Created by Ladislav on 12/15/2016.
 */
public class GameFrame extends JFrame {

    private BoardPanel boardPanel;

    public GameFrame() {
        super("Falling Blox"); // blocks? or feature
        boardPanel = new BoardPanel(new FallingBlocksGame());
        this.setContentPane(boardPanel);
        this.setSize(800, 630);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // consider about using Gradle build plugin instead of IDEA module file .iml
    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new GameFrame());
        SwingUtilities.invokeLater(GameFrame::new);
    }
}
