package controller;

import model.Block;
import model.FallingBlocksGame;

import javax.swing.*;
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

        for(Block block:board.active){
          /*  if (e.getButton() == MouseEvent.BUTTON1 && blockClicked(e, block) && !block.beenGrounded()){
                block.setColor(board.colorTable.getColor(0));
            }
            if (e.getButton() == MouseEvent.BUTTON2 && blockClicked(e, block) && !block.beenGrounded()){
                block.setColor(board.colorTable.getColor(1));
            }*/
          if(e.getButton() == MouseEvent.BUTTON1)
            block.setColor(board.colorTable.getColor(0));
            if(e.getButton() == MouseEvent.BUTTON2)
                block.setColor(board.colorTable.getColor(1));
        }
    }
    public boolean blockClicked(MouseEvent e, Block block){

        if (e.getX() >= block.getPositionX()*4+200 && e.getX() <= block.getPositionX()*4+240
                && e.getY() >= block.getPositionY()*4 && e.getY() <= block.getPositionY()*4+40){
            return true;
        }
        return false;
    }
}
