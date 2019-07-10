import greenfoot.*;

public class Hugebeam extends Beam{
    boolean pause = true;
    GreenfootImage image;
    int count = 0;
    int x;
    int y;
    public Hugebeam(int ax, int ay){
        x = ax;
        y = ay;
        setImage("Cannon/beam1.png");
        getImage().scale(x,y);
    }

    public void act(){
        if(pause){
            move(20);

            Enemys enemys = (Enemys)getOneIntersectingObject(Enemys.class);
            Actor encan = getOneIntersectingObject(Encan.class);
            Actor mychr = getOneIntersectingObject(Mychr.class);
            Actor can = getOneIntersectingObject(Can.class);
            if(enemys != null|| encan != null || mychr != null || can != null || getY() > 625){
                if(enemys != null){
                    enemys.Damage(1000);
                }else if(encan != null){
                    getWorld().removeObject(encan);
                }else if(mychr != null) {
                    ((Mychr)mychr).Damage(0.4,false);
                }else if(can != null) {
                    getWorld().removeObject(can);
                }
                if(getY() > 625){
                    getWorld().removeObject(this);
                }
            }
        }
    }  
    
    public void Pausing(boolean pau){
        pause = pau;
    }
}
