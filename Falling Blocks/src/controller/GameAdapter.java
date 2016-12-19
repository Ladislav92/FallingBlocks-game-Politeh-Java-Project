package controller;

import model.Block;
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
    public void mousePressed(MouseEvent e){
        for(Block block:board.active){
            if(e.getButton() == MouseEvent.BUTTON1 &&
                e.getX() >= block.getPositionX()*4+200 &&
                e.getX() <= block.getPositionX()*4 + 240 &&
                e.getY() >= block.getPositionY()*4 &&
                e.getY() <= block.getPositionY()*4 + 40 &&
                    !block.beenGrounded()) {
                if (!block.isColored()) {
                    block.setColor(board.colorTable.getColor(0));
                }
            }
            if(e.getButton() == MouseEvent.BUTTON3 &&
                    e.getX() >= block.getPositionX()*4+200 &&
                    e.getX() <= block.getPositionX()*4 + 240 &&
                    e.getY() >= block.getPositionY()*4 &&
                    e.getY() <= block.getPositionY()*4 + 40 &&
                    !block.beenGrounded()) {
                if (!block.isColored()) {
                    block.setColor(board.colorTable.getColor(1));
                }
            }
        }

    }
    public void gameFlow(){
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
}

