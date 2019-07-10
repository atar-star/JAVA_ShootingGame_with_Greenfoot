import greenfoot.*;
import java.awt.Color;
public class Boss2x1Option extends Bosss{
    boolean pause = true;
    World1 spaceworld = (World1) getWorld();
    private int shcount = 0;
    int myx,opx,angle,shang,deg;
    double HP = 30;
    int shc = Greenfoot.getRandomNumber(30)+50;
    Bosss boss;
    public Boss2x1Option(Bosss bos, int bossx, int opmx){
        setImage("Boss/boss2-1Option.png");
        getImage().scale(35,35);
        if(bossx>0){
            setRotation(135);
            angle = 135;
            deg = -3;
        }else{
            setRotation(45);
            angle = 45;
            deg = 3;
        }
        myx = bossx;
        boss = bos;
        opx = opmx;
    }

    public void act(){
        if(pause){
        //updateImage();
        setLocation(boss.getX() + myx,boss.getY() + 60);
        if(shcount >= shc){
            for(int i=0;i<=1;i++){
                Encan3 encan3 = new Encan3(17, 6, 0.2, 1);
                getWorld().addObject(encan3, getX(), getY());
                encan3.setRotation(getRotation());
            }
            shc = Greenfoot.getRandomNumber(30)+60;
            shcount = 0;
        }else{
            angle += deg;
            setRotation(angle);
            if(angle>=135||angle<=45){
                deg*=-1;
            }
        }
        shcount++;

        if(HP <= 0){
            ((World1)getWorld()).getCounter().bumpCount(200);
            getWorld().removeObject(this);
        }
    }
    }

    public void Damage(double dam){
        HP -= dam;
    }

    private void updateImage(){  
        GreenfootImage image = new GreenfootImage(50, 50);
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
}