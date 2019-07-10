import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Encan4 extends Encan{
    boolean pause = true;
    double dam;
    double a=1;
    double count = 0;
    double b=10;
    public Encan4(){
        setImage("Cannon/encan1.png");
        getImage().scale(15,7);
        //b = damage;
    }

    public void act(){
        if(pause){
            for(int i=0;i<=2;i++){
                move((int)(5));
                turn((int)(b));
                count++;
                if(count>17){
                    b*=-1;
                    count=0;
                    a=1;
                }
                a++;
            }
        }
    }

    public void Pausing(boolean pau){
        pause = pau;
    }
}
