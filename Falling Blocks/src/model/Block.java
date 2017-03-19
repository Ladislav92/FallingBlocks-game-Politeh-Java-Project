package model;

/**
 * Created by Ladislav on 11/20/2016.
 */
public class Block {

    private static double speed;
    private static long nextID = 0;

    private final long blockID;
    private int positionX, positionY;
    /* better for reading
    private int positionX;
    private int positionY;
     */
    private Color color;
    private boolean isColored = false;
    private boolean beenGrounded = false;

    // maybe model doesn't want to know about GUI (positions)
    // maybe positionY and speed are params ?
    public Block(int positionX) {
        this.blockID = nextID++;
        this.positionX = positionX; //prihvata random pozicij
        this.positionY = -50; // stvara blok izvan frejma
        this.color = Color.BLANK;
        speed = 1;
    }

    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }

    public Color getColor() {
        return this.color;
    }

    public long getID() {
        return blockID;
    }

    public boolean beenGrounded() {
        return beenGrounded;
    }

    public void setColor(Color color) {
        // why not just assignments?
        if (!isColored) {
            this.isColored = true;
            this.color = color;
        }
    }

    public boolean isColored() {
        return isColored;
    }

    void setBeenGrounded() {
        // why not just (1)
        if (!beenGrounded) {
            beenGrounded = true;
        }
    }

    // (1)
    public void setBeenGrounded(boolean beenGrounded) {
        this.beenGrounded = beenGrounded;
    }

    public void fall() {
        positionY += speed;
    }

    public static long getNextID() {
        return nextID;
    }
    // remove if not needed
    //static void setSpeed(long score){speed+=((double)score/1000);}
}
