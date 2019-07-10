import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Encan1 extends Encan{
    boolean pause = true;
    double dam;
    public Encan1(double damage){
        setImage("Cannon/encan1.png");
        getImage().scale(15,7);
        dam = damage;
    }

    public void act(){
        if(pause){
            move(6);
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
