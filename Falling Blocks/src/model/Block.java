package model;

/**
 * Created by Ladislav on 11/20/2016.
 */
public class Block {
    //U GUI IDE
/*    private static final int width = 20;
    private static final int height = 20;*/

    private static double speed;
    private int positionX, positionY;
    private final long blockID;
    private static long nextID = 0;
    private Color color;

    public Block(int positionX){
        this.blockID = nextID++;
        this.positionX = positionX; //prihvata random pozicij
        this.positionY = -100; // stvara blok izvan frejma
        this.speed = 1;
        this.color = Color.BLANK;
    }

    public Block getBlock(){return this;};
    public int getPositionX(){return this.positionX;}
    public int getPositionY(){return this.positionY;}
    public Color getColor(){return this.color;};
    public static void setSpeed(long score){speed+=((double)score/1000);};
    public long getID(){return blockID;}
    public static long getNextID(){return nextID;}

    public void fall(){positionY+=speed;}

}
