package model;

import java.util.*;
import static model.Block.setSpeed;

/**
 * Created by Ladislav on 11/29/2016.
 */

public class FallingBlocksGame {

    public ColorTable colorTable = new ColorTable();
    private Random rand = new Random();
    public List<Block> active = new ArrayList<Block>(); // sve blokove koji su aktivni(padaju) stavlja u listu
    public Block[][] grounded = new Block[10][15]; // blokove koji su na podu smiješta u matricu
    private int[] groundHeight = new int[10]; // podešava visinu poda, jednostavni collision
    private long score;
    private boolean scored;
    private boolean blocksRemoved;
    private Queue<Block> q = new LinkedList<Block>();
    private Set<Block> forRemoval = new HashSet<Block>();

    public void startGame() {
        initialiseGroud();
        spawnBlock();
        // draw Block !
        while (!gameOver()) {
            for (int i = 0; i < active.size(); i++) {
                Block block = active.get(i);
                if (isGrounded(block)) {
                    System.out.println("");
                    System.out.println("Grounding block on pos:");
                    setGround(block.getPositionX(), block.getPositionY(), block, -10);  // zaustavlja ga, stavlja u matricu i                                                // podiže tlo
                    active.remove(block); // remove i ?
                    if (block.getID() == Block.getNextID() - 1) {                           //ako je zadnji koji se pojavio
                        spawnBlock();                                                   // pojavi novi
                    }

                    printGroundedArray("Before CheckMatch");

                    System.out.println("Active size: " + active.size());
                    if (active.size() < 2) {
                        checkMatch(block);
                    }
                    printGroundedArray("After CheckMatch");

                } else {
                    block.fall();                                                       // ako nije na tlu, pada
                }
            }
            //for each in grounded draw block !!!
        }
    } // test
    public boolean gameOver() {
        for (int i = 0; i < 10; i++) {
            if (groundHeight[i] == 0) {
                return true;
            }
        }
        return false;
    }

    public void spawnBlock() {
        Block block = new Block(rand.nextInt(10) * 10);
        active.add(block);
    }

    public void initialiseGroud() {
        for (int i = 0; i < groundHeight.length; i++) {
            groundHeight[i] = 140;
        }
    }

    public void setGround(int x, int y, Block block, int hight) {
        int posX = x / 10;
        int posY = y / 10;
        System.out.println("posX and posY: " + posX + " " + posY);
       // block.setBeenGrounded();
        grounded[posX][posY] = block;

        if(grounded[posX][posY]!=null)
        grounded[posX][posY].setBeenGrounded();

        groundHeight[posX] += hight;
        System.out.println(groundHeight[posX]);

    }

    public boolean isGrounded(Block block) {
        if (block.getPositionY() == groundHeight[block.getPositionX() / 10]) {      // ako je pozicija na Y osi jednaka visini tla
            block.setBeenGrounded();
            return true;
        } else {
            return false;
        }
    }

    public void checkQueue() {
        if (q.size() >= 3) {
            System.out.println("adding");
            blocksRemoved = true;
            while (!q.isEmpty()) {

                forRemoval.add(q.remove());
            }
        }
        q.clear();
    }

    // Needs optimisation
    public void checkMatch(Block justGrounded) {

        System.out.println("Check match started !");

        blocksRemoved = false;
        forRemoval = new LinkedHashSet<Block>();
        q = new LinkedList<Block>();
        Block last = null;

        for (int i = 0; i < 10; i++) {
            last = null;
            for (int j = 0; j < 15; j++) {
                if (grounded[i][j] != null) {
                    if (last == null || grounded[i][j].getColor() == last.getColor())
                        q.add(grounded[i][j]);
                    else {
                        checkQueue();
                        q.add(grounded[i][j]);
                    }
                    last = grounded[i][j];
                } else
                    checkQueue();
            }
            checkQueue();
        }
        checkQueue();

        for (int j = 0; j < 15; j++) {
            last = null;
            for (int i = 0; i < 10; i++) {
                if (grounded[i][j] != null) {
                    if (last == null || grounded[i][j].getColor() == last.getColor())
                        q.add(grounded[i][j]);
                    else {
                        checkQueue();
                        q.add(grounded[i][j]);
                    }
                    last = grounded[i][j];
                } else
                    checkQueue();
            }
            checkQueue();
        }
        checkQueue();


        System.out.println("forRemovalSize: " + forRemoval.size());
        while (!forRemoval.isEmpty()) {
            Block b = forRemoval.iterator().next();
            forRemoval.remove(b);

            System.out.println("removing block with id: " + b.getID());
            setGround(b.getPositionX(), b.getPositionY(), null, 10);
            for (int i = b.getPositionY() / 10; i >= 0; i--) {
                if (grounded[b.getPositionX() / 10][i] != null) {
                    Block nad_b = grounded[b.getPositionX() / 10 ][i];
                    forRemoval.remove(nad_b);
                    active.add(nad_b);
                    setGround(nad_b.getPositionX(), nad_b.getPositionY(), null, 10);
                }
            }
        }
    }

    public void setScore(boolean scored) {
        this.scored = scored;
        score += 10;
        setSpeed(score);
        System.out.println("Score: " + score);
    }

    public void printGroundedArray(String s) {
        System.out.println();
        System.out.println(s);
        for (int k = 0; k < 15; k++) {
            for (int j = 0; j < 10; j++) {                if (grounded[j][k] != null)
                System.out.print("B" + " | ");
            else System.out.print(" " + " | ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        FallingBlocksGame game = new FallingBlocksGame();
        game.startGame();
    }
}