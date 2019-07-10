import greenfoot.*;
import java.awt.*;
public class Boss2x1 extends Bosss{
    boolean pause = true;
    private int movecount = 0;
    private int shcount = 0;
    public double HP = 120;
    Maps map;
    public int trans = 255;
    public int descount = 0;
    private int burstx,bursty,brsize;
    double angle;
    int sizx = 125;
    int sizy = 120;
    public Boss2x1(Maps ma){
        setImage("Boss/boss2-1.png");
        getImage().scale(sizx,sizy);
        map = ma;
    }

    public void act(){
        if(pause){
            //updateImage();
            if(movecount < 1){
                getWorld().addObject(new Boss2x1Option(this,70,30), getX(), getY());
                getWorld().addObject(new Boss2x1Option(this,-70,-30), getX(), getY());
                movecount ++;
            }else if(movecount < 40){
                setLocation(getX(), getY() + 2);
                movecount ++;
            }
            if(movecount >= 40){
                if(HP <= 0){
                    getWorld().removeObjects(getWorld().getObjects(Enattacks.class));
                    if(trans>20){
                        super.OrderTransparence(trans);
                        trans-=3;
                        if((descount%13)==0){
                            burstx = Greenfoot.getRandomNumber(161)-80;
                            bursty = Greenfoot.getRandomNumber(141)-70;
                            brsize = Greenfoot.getRandomNumber(41)+40;
                            getWorld().addObject(new Burst1(this, burstx, bursty, brsize, Greenfoot.getRandomNumber(3)+1),getX()+burstx,getY()+bursty);
                        }
                        descount++;
                    }else{
                        ((World1)getWorld()).getCounter().bumpCount(1000);
                        map.bossDestroy();
                        (((World1)getWorld()).getMychr()).setshflag(3);
                        getWorld().removeObject(this);
                    }
                }else{
                    shot();
                }
            }
        }
    }    

    public void shot(){
        if(HP > 0){
            if((shcount%200)==0){
                angle=Math.toDegrees(Math.atan2(getY()-(((World1)getWorld()).getMychr()).getY(),getX()-(((World1)getWorld()).getMychr()).getX()))+160;
                for(int i=0; i<3; i++){
                    Beam2 beam = new Beam2(6,74,true,1);
                    getWorld().addObject(beam, getX(), getY()+30);
                    beam.setRotation((int)angle + 20 * i);
                }
            }
            shcount ++;  
        }
    }

    public void Damage(double dam){
        HP -= dam;
    }

    private void updateImage(){  
        GreenfootImage image = new GreenfootImage(360, 60);
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
