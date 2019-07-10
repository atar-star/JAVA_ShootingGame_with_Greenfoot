import greenfoot.*;
import java.awt.Color;

public class HPCounter extends Counters{   
    private double HP;
        
    public HPCounter(){
        setImage(new GreenfootImage("HP", 20, Color.WHITE, Color.BLACK));
    }

    public void MyHP(double hp){
        ((World1)getWorld()).removeObjects(getWorld().getObjects(HPs.class));
        HP = Math.ceil(hp);
        for(int i = 0; i < (int)HP; i++){
            getWorld().addObject(new HPs(),470 + i * 8, 615);
        }
    }
}
