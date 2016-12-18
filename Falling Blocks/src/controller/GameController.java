package controller;

import model.Block;
import model.FallingBlocksGame;

/**
 * Created by Ladislav on 12/15/2016.
 * Implements game flow itself and makes connection between GUI and MODEL
 */

public class GameController {
    FallingBlocksGame game = new FallingBlocksGame();

    GameController(FallingBlocksGame game){
        this.game = game;
    }

    public void startGame() {
        game.initialiseGroud();
        game.spawnBlock();

        while (!game.gameOver()) {
            for (int i = 0; i < game.active.size(); i++) {
                Block block = game.active.get(i);
                if (game.isGrounded(block)) {
                    System.out.println("");
                    System.out.println("Grounding block on pos:");
                    game.setGround(block.getPositionX(), block.getPositionY(), block, -10);  // zaustavlja ga, stavlja u matricu i                                                // podiže tlo
                    game.active.remove(block); // remove i ?
                    if (block.getID() == Block.getNextID() - 1) {                           //ako je zadnji koji se pojavio
                        game.spawnBlock();                                                   // pojavi novi
                    }

                    game.printGroundedArray("Before CheckMatch");

                    System.out.println("Active size: " + game.active.size());
                    if (game.active.size() < 2) {
                        game.checkMatch(block);
                    }
                    game.printGroundedArray("After CheckMatch");

                } else {
                    block.fall();                                                       // ako nije na tlu, pada
                }
            }
        //for each in grounded draw block !!!
        }
    }
}
