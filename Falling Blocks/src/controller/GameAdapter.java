package controller;

import model.FallingBlocksGame;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Ladislav on 12/15/2016.
 * Implements mouse listener and makes posible to paint block on click
 */
public class GameAdapter extends MouseAdapter{

    private final FallingBlocksGame board;
    private final Container container;

    public GameAdapter(FallingBlocksGame board, Container container){
        this.board = board;
        this.container = container;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // for all blocks in board.active
        // if mouse clicked on block position (full square)
        // activeColor = game.ColorTable.getColor()
        // block.setColor(activeColor)
        // paintBlock(activeColor) -> GUI ! ! !
        //all blocks in active and in grounded repaint?
    }
}
