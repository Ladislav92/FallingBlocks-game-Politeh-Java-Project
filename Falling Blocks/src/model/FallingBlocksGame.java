package model;

import java.util.*;

/**
 * Created by Ladislav on 11/29/2016.
 */

public class FallingBlocksGame {

    private static final int ROW = 15;
    private static final int COLUMN = 10;
    private static final int ADJUST_POSTION = 10; // POSTION -> POSITION

    private ColorTable colorTable = new ColorTable();
    private Block[][] grounded = new Block[COLUMN][ROW]; // use collections framework instead
    private List<Block> active = new ArrayList<Block>(); // just '= new ArrayList<>();'

    private long score;
    private int[] groundHeight = new int[COLUMN];
    private Random rand = new Random();
    private boolean blocksRemoved; // never accessed
    private Queue<Block> q = new LinkedList<Block>(); // bad name
    private Set<Block> forRemoval = new HashSet<Block>();

    public ColorTable getColorTable() {
        return colorTable;
    }

    public Block getGrounded(int x, int y) {
        return grounded[x][y];
    }

    public List<Block> getActive() {
        return active;
    }

    public long getScore() {
        return score;
    }

    public FallingBlocksGame() {
        spawnBlock();
        initialiseGroud();
    }

    public void spawnBlock() {
        Block block = new Block(rand.nextInt(COLUMN) * ADJUST_POSTION);
        active.add(block);
    }

    // param hight -> height
    public void setGround(int x, int y, Block block, int hight) {
        int posX = x / ADJUST_POSTION;
        int posY = y / ADJUST_POSTION;
        System.out.println("posX and posY: " + posX + " " + posY);
        grounded[posX][posY] = block;
        if (grounded[posX][posY] != null) {
            grounded[posX][posY].setBeenGrounded();
        }
        groundHeight[posX] += hight;
        System.out.println(groundHeight[posX]); // printlns in model is bad, use loggers

    }

    public boolean isGrounded(Block block) {
        if (block.getPositionY() >= groundHeight[block.getPositionX() / ADJUST_POSTION]) {      // ako je pozicija na Y osi jednaka visini tla
            block.setBeenGrounded();
            return true;
        } else {
            return false;
        }
    }

    public void checkMatch(Block justGrounded) {

        System.out.println("Check match started !");

        blocksRemoved = false;
        forRemoval = new LinkedHashSet<Block>();
        q = new LinkedList<Block>(); // just = new LinkedList<>();
        Block last;


        // at least extract these two block into method checkAllCells(int firstDim, int secondDim);
        checkAllCells(COLUMN, ROW);
//        for (int i = 0; i < COLUMN; i++) {
//            last = null;
//            for (int j = 0; j < ROW; j++) {
//                if (grounded[i][j] != null) {
//                    if (last == null || grounded[i][j].getColor() == last.getColor()
//                            && grounded[i][j].getColor() != Color.BLANK) { // always use { and } with if
//                        q.add(grounded[i][j]);
//                    } else {
//                        checkQueue();
//                        q.add(grounded[i][j]);
//                    }
//                    last = grounded[i][j];
//                } else
//                    checkQueue();
//            }
//            checkQueue();
//        }
//        checkQueue();

        checkAllCells(ROW, COLUMN);
//        for (int j = 0; j < ROW; j++) {
//            last = null;
//            for (int i = 0; i < COLUMN; i++) {
//                if (grounded[i][j] != null) {
//                    if (last == null || grounded[i][j].getColor() == last.getColor()
//                            && grounded[i][j].getColor() != Color.BLANK) {
//                        q.add(grounded[i][j]);
//                    } else {
//                        checkQueue();
//                        q.add(grounded[i][j]);
//                    }
//                    last = grounded[i][j];
//                } else
//                    checkQueue();
//            }
//            checkQueue();
//        }
        checkQueue();


        System.out.println("forRemovalSize: " + forRemoval.size()); // use loggers
        while (!forRemoval.isEmpty()) {
//            Block b;
//            b = forRemoval.iterator().next();
            Block b = forRemoval.iterator().next();
            forRemoval.remove(b);
            setScore();
            System.out.println("removing block with id: " + b.getID());
            setGround(b.getPositionX(), b.getPositionY(), null, ADJUST_POSTION);
            for (int i = b.getPositionY() / ADJUST_POSTION; i >= 0; i--) {
                if (grounded[b.getPositionX() / ADJUST_POSTION][i] != null) {
                    Block nad_b = grounded[b.getPositionX() / ADJUST_POSTION][i];
                    forRemoval.remove(nad_b);
                    active.add(nad_b);
                    setGround(nad_b.getPositionX(), nad_b.getPositionY(), null, ADJUST_POSTION);
                }
            }
        }
    }

    private void checkAllCells(int firstDim, int secondDim) {
        Block last;
        for (int j = 0; j < firstDim; j++) {
            last = null;
            for (int i = 0; i < secondDim; i++) {
                if (grounded[i][j] != null) {
                    if (last == null || grounded[i][j].getColor() == last.getColor()
                            && grounded[i][j].getColor() != Color.BLANK) {
                        q.add(grounded[i][j]);
                    } else {
                        checkQueue();
                        q.add(grounded[i][j]);
                    }
                    last = grounded[i][j];
                } else
                    checkQueue();
            }
            checkQueue();
        }
    }

    public boolean gameOver() {
        for (int i = 0; i < COLUMN; i++) {
            if (groundHeight[i] == 0) {
                return true;
            }
        }
        return false;
    }

    private void initialiseGroud() {
        for (int i = 0; i < groundHeight.length; i++) {
            groundHeight[i] = 140; // magic const
        }
    }

    private void checkQueue() {
        if (q.size() >= 3) {
            System.out.println("adding");
            blocksRemoved = true;
            while (!q.isEmpty()) {
                forRemoval.add(q.remove());
            }
        }
        q.clear();
    }

    private void setScore() {
        score += 10;
        System.out.println("Score: " + score);
    }

    public void makeTurn() {
        for (int i = 0; i < active.size(); i++) {
            Block block = active.get(i);
            if (isGrounded(block)) {
                System.out.println("");
                System.out.println("Grounding block on pos:");
                setGround(block.getPositionX(), block.getPositionY(), block, -ADJUST_POSTION);  // zaustavlja ga, stavlja u matricu i podiže tlo
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
    }

    // param s is never used
    public void printGroundedArray(String s) {
        System.out.println();
        for (int k = 0; k < 15; k++) {
            for (int j = 0; j < 10; j++) {
                if (grounded[j][k] != null)
                    System.out.print("B" + " | ");
                else System.out.print(" " + " | ");
            }
            System.out.println("");
        }
    }

    // remove if not needed
    public static void main(String[] args) {
        FallingBlocksGame game = new FallingBlocksGame();
        // game.startGame();
    }
}