import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
public class Boss1 extends Bosss{
    boolean pause = true;
    private int movecount = 0;
    private int movecount2 = 0;
    private boolean firstmove2 = true;
    private int shcount = 35;
    private int sh2count = 0;
    public double HP = 70;
    private boolean bossflag = true;
    private int rot = -70;
    private int rot2;
    Maps map;
    public int trans = 255;
    public int descount = 0;
    private int burstx,bursty,brsize;
    int moves;
    public Boss1(Maps ma){
        setImage("Boss/boss1-1.png");
        map = ma;
        if(Greenfoot.getRandomNumber(2)==1){
            moves = 8;
        }else{
            moves = -8;
        }
    }

    public void act(){
        if(pause){
            //updateImage();
            if(movecount < 1){
                //getWorld().addObject(new Boss1option(70, 30), getX(), getY());
                //getWorld().addObject(new Boss1option(-70, -30), getX(), getY());
                movecount ++;
            }else  if(movecount < 40){
                setLocation(getX(), getY() + 2);
                movecount ++;
            }
            if(movecount >= 40){
                if(HP>0){
                    move();
                    shot();
                    /*if(!getWorld().getObjects(Boss1option.class).isEmpty()&&HP<1){
                    HP = 1;
                    }*/
                }else{
                    //getWorld().removeObjects(getWorld().getObjects(Boss1option.class));
                    if(trans>20){
                        super.OrderTransparence(trans);
                        trans-=3;
                        if((descount%13)==0){
                            burstx = Greenfoot.getRandomNumber(51)-25;
                            bursty = Greenfoot.getRandomNumber(51)-25;
                            brsize = Greenfoot.getRandomNumber(41)+40;
                            getWorld().addObject(new Burst1(this, burstx, bursty, brsize, Greenfoot.getRandomNumber(3)+1),getX()+burstx,getY()+bursty);
                        }
                        descount++;
                    }else{
                        getWorld().addObject(new Cure(3), getX(), getY());
                        ((World1)getWorld()).getCounter().bumpCount(500);
                        map.bossDestroy();
                        (((World1)getWorld()).getMychr()).setshflag(2);
                        getWorld().removeObject(this);
                    }
                }
            }
        }
    }

    public void Damage(double dam){
        HP -= dam;
    }

    private void updateImage(){  
        GreenfootImage image = new GreenfootImage(120, 90);
        image.setColor(Color.black);
        image.setTransparency(50);
        image.fill();
        GreenfootImage txtImg = new GreenfootImage("a"+HP, 46, Color.white, new Color(0, 0, 0, 0)); 
        image.drawImage(txtImg, (image.getWidth() - txtImg.getWidth())/2, (image.getHeight()-txtImg.getHeight())/2);  
        setImage(image);
    }

    private void move(){
        setLocation(getX() + moves, getY());
        if(getX()>=550||getX()<=110){
            moves*=-1;
        }
    }

    private void shot(){
        if((shcount%40)==0){
            for(int i=0; i<6; i++){
                Encan1 encan = new Encan1(1);
                getWorld().addObject(encan, getX() + 15, getY());
                encan.setRotation(rot + i * 50);
            }
            for(int i=0; i<6; i++){
                Encan1 encan2 = new Encan1(1);
                getWorld().addObject(encan2, getX() - 15, getY());
                rot2 = 180 - rot;
                encan2.setRotation(rot2 - i * 50);
            }
            sh2count ++;
            if(sh2count <= 6){
                rot += 20;
            }else if(sh2count == 12){
                sh2count = 0;
            }else{
                rot -= 20;
            }
        }
        if((shcount%60)==0){
            getWorld().addObject(new Encan2(1), getX()+30, getY()-15);
            getWorld().addObject(new Encan2(1), getX()-30, getY()-15);
        }
        shcount ++;
    }

    public void Pausing(boolean pau){
        pause = pau;
    }
}

   