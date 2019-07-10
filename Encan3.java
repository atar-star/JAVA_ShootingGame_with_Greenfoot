import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Encan3 extends Encan{
    boolean pause = true;
    double dam;
    int count=0;
    int sx,sy,shcount;
    boolean flag=true;
    public Encan3(int scx, int scy, double damage, int flco){
        setImage("Cannon/encan1.png");
        sx=scx;
        sy=scy;
        shcount=flco;
        getImage().scale(sx,sy);
        dam = damage;
    }

    public void act(){
        if(pause){
            move(7);
            if(count > 1&& shcount < 3){
                if(shcount==1)sx -= 6;
                if(sx > 0 && sy > 0){
                    for(int i=0;i < Greenfoot.getRandomNumber(5)+2;i++){
                        Encan3 encan = new Encan3(sx, sy, 0.2, shcount+1);
                        getWorld().addObject(encan, getX(), getY());
                        encan.setRotation(getRotation() + Greenfoot.getRandomNumber(60) - 30);
                    }
                }
                flag=false;
                getWorld().removeObject(this);
            }
            count++;
            if(flag){
                Actor mychr = getOneIntersectingObject(Mychr.class);
                if(mychr != null||getX() > 575 || getX() < 55 || getY() > 605 || getY() < 25){
                    if(mychr != null){
                        ((Mychr)mychr).Damage(dam,false);
                    }
                    getWorld().removeObject(this);
                }
            }
        }
    }

    public void Pausing(boolean pau){
        pause = pau;
    }
}
