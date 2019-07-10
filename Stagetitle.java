import greenfoot.*;
import java.awt.Color;

public class Stagetitle extends Counters{
    boolean pause = true;
    int trans = 0;
    int i;
    int count = 0;
    Maps map;
    public Stagetitle(String filename, int transag,Maps ma){
        setImage(filename);
        GreenfootImage image = getImage();
        image.scale(256,40);
        image.setTransparency(0);
        setImage(image);
        i = transag;
        map = ma;
    }

    public void act(){
        if(pause){
            super.OrderTransparence(trans);
            if(trans == 255){
                if(count >= 20){
                    i *= -1;
                    trans += i;
                }           
                count++;
            }else{
                trans += i;
            }

            if(trans == 0){
                map.bossDestroy();
                getWorld().removeObject(this);
            }else if(trans>255){
                trans = 255;
            }
        }
    }    
    
    public void Pausing(boolean pau){
        pause = pau;
    }
}
