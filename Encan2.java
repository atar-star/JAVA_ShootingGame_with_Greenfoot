import greenfoot.*;
import java.util.List;

public class Encan2 extends Encan{
    boolean pause = true;
    int count = 0;
    int count2 = 90;
    int Distx, Disty;
    double angle;
    double dam;
    public Encan2(double damage){
        setImage("Cannon/encan2.png");
        GreenfootImage image = getImage();
        image.scale(15,7);
        setImage(image);
        dam = damage;
    }

    public void act(){
        if(pause){
            move(5);
            if(count <= 15 && count2 >= 4){
                List b=getWorld().getObjects(Mychr.class);
                Mychr g=(Mychr)b.get(0);
                Distx=getX()-g.getX();
                Disty=getY()-g.getY();
                angle = Math.toDegrees(Math.atan2(Disty,Distx))+180;
                setRotation((int)angle);
                count2 = 0;
                count ++;
            }else{
                count2 ++;
            }

            Actor mychr = getOneIntersectingObject(Mychr.class);
            if(mychr != null||getX() > 575 || getX() < 55 || getY() > 605 || getY() < 25){
                if(mychr != null){
                    ((Mychr)mychr).Damage(dam,false);
                }
                getWorld().removeObject(this);
            }
        }
    }       

    public void Pausing(boolean pau){
        pause = pau;
    }
}
