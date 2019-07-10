import greenfoot.*;
import java.awt.Color;
public class si extends Pause{
    GreenfootImage image;
    Mychr ene;
    public si(Mychr en){
        getImage().scale(400,118);
        getImage().setTransparency(20);
        ene = en;
    }
    
    public void act(){
        setLocation(ene.getX(), ene.getY());
        setRotation(ene.getRotation());
        move(200);
    }    
}
