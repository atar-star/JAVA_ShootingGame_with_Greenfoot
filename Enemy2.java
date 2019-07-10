import greenfoot.*;
import java.util.List;
import java.awt.*;
public class Enemy2 extends Enemy{
    boolean pause = true;
    private int anime = 0;
    private int anicount = 1;
    private int a = -1;
    private int intime = Greenfoot.getRandomNumber(7) * 10;
    private int shcount = 150;
    private int count = 0;
    double HP;
    Maps map;
    int sizx = 30;
    int sizy = 30;
    public Enemy2(Maps ma, double hp){
        while(intime < 40){
            intime = Greenfoot.getRandomNumber(7) * 10;
        }
        map = ma;
        HP = hp;
        getImage().scale(sizx,sizy);
    }

    public void act(){
        if(pause){
            if((anime%7) == 0){
                setImage("Enemy/Ene2/ene2-" + anicount +".png");
                if(anicount == 7|anicount == 1){
                    a *= -1;
                }
                anicount += a ;
            }
            anime ++;
            //updateImage();
            if(intime > count){
                setLocation(getX(), getY() + 3);
            }
            count ++;

            if(shcount > 200){
                List b=getWorld().getObjects(Mychr.class);
                int Distx, Disty;
                double angle;
                Mychr g=(Mychr)b.get(0);
                Distx=getX()-g.getX();
                Disty=getY()-g.getY();
                angle=Math.toDegrees(Math.atan2(Disty,Distx))+180;
                Encan2 encan2 = new Encan2(1);
                getWorld().addObject(encan2, getX(), getY());
                encan2.setRotation((int)angle);
                shcount = 0;
            }
            shcount ++;

            if(HP <= 0){
                if(Greenfoot.getRandomNumber(11) <= 3){
                    getWorld().addObject(new Cure(1), getX(), getY());
                }
                ((World1)getWorld()).getCounter().bumpCount(50);
                map.enDestroy(true);
                getWorld().removeObject(this);
            }else if(getY() < 0){
                map.enDestroy(false); 
                getWorld().removeObject(this);
            }
        }
    }    

    public void Damage(double dam){
        HP -= dam;
    }

    private void updateImage(){  
        GreenfootImage image = new GreenfootImage(40, 40);
        image.setColor(Color.black);
        image.setTransparency(50);
        image.fill();
        GreenfootImage txtImg = new GreenfootImage("a"+(int)HP, 46, Color.white, new Color(0, 0, 0, 0)); 
        image.drawImage(txtImg, (image.getWidth() - txtImg.getWidth())/2, (image.getHeight()-txtImg.getHeight())/2);  
        setImage(image);
    }
    
    public void Pausing(boolean pau){
        pause = pau;
    }
    
    public int getSizeX(){
        return sizx;
    }
    
    public int getSizeY(){
        return sizy;
    }
}