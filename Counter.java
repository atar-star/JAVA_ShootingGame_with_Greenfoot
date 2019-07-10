import greenfoot.*;
import java.awt.Color;

public class Counter extends Counters{
    private int score;
    private int size;
    
    public Counter(int scores, int sizes){
        score = scores;
        size = sizes;
        setImage(new GreenfootImage("Score " + score, size, Color.WHITE, null));
    }
    
    public void bumpCount(int amount){
        score += amount;
        setImage(new GreenfootImage("Score " + score, 20, Color.WHITE, null));
    }
    
    public int returnScore(){
        return score;
    }
    
    public void setSize(int size2){
        setImage(new GreenfootImage("Score " + score, size2, Color.WHITE, null));
    }
}
