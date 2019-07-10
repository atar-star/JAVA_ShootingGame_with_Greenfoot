import greenfoot.*;

public class Beam1 extends Beam{
    boolean pause = true;
    GreenfootImage image;
    int count = 0;
    private boolean flag = true;
    int x,y;
    boolean shfl;
    double dam;
    public Beam1(int ax, int ay, boolean sh, double damage){
        shfl = sh;
        x = ax;
        y = ay;
        dam = damage;
        setImage("Cannon/beam2.png");
        image = getImage();
        image.scale(y,x);
        setImage(image);
    }

    public void act(){
        if(pause){
            if(shfl){
                move(7);
            }else{
                setLocation(getX(), getY() + 10);
            }
            if(flag){
                Actor mychr = getOneIntersectingObject(Mychr.class);
                if (mychr != null) {
                    ((Mychr)mychr).Damage(dam, false);
                    flag = false;
                }
            }

            if(getX() > 585 || getX() < 5 || getY() > 605 || getY() < 5){
                getWorld().removeObject(this);
            }
        }
    }  
    
    public void Pausing(boolean pau){
        pause = pau;
    }
}
