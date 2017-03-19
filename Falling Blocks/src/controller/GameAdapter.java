package controller;

import model.Block;
import model.FallingBlocksGame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static view.BoardPanel.*;

/**
 * Created by Ladislav on 12/15/2016.
 * Implements mouse listener and makes possible to paint block on click
 */
public class GameAdapter extends MouseAdapter {

    private final FallingBlocksGame board;
    private final Container container;

    public GameAdapter(FallingBlocksGame board, Container container) {
        this.board = board;
        this.container = container;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (Block block : board.getActive()) {
            if (e.getButton() == MouseEvent.BUTTON1 &&
                    e.getX() >= block.getPositionX() * getBlockPosCorrection() + getFirstLineIndent() &&
                    e.getX() <= block.getPositionX() * getBlockPosCorrection() + getFirstLineIndent() + getBlockSize() &&
                    e.getY() >= block.getPositionY() * getBlockPosCorrection() &&
                    e.getY() <= block.getPositionY() * getBlockPosCorrection() + getBlockSize() &&
                    !block.beenGrounded()) {
                if (!block.isColored()) {
                    block.setColor(board.getColorTable().pollLeftColor());
                }
            }
            if (e.getButton() == MouseEvent.BUTTON3 &&
                    e.getX() >= block.getPositionX() * getBlockPosCorrection() + getFirstLineIndent() &&
                    e.getX() <= block.getPositionX() * getBlockPosCorrection() + getFirstLineIndent() + getBlockSize() &&
                    e.getY() >= block.getPositionY() * getBlockPosCorrection() &&
                    e.getY() <= block.getPositionY() * getBlockPosCorrection() + getBlockSize() &&
                    !block.beenGrounded()) {
                if (!block.isColored()) {
                    block.setColor(board.getColorTable().pollRightColor());
                }
            }
        }
    }

    public void gameFlow() {
        board.makeTurn();
    }
}


