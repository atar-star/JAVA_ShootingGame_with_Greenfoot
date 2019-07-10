import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
public class Mychr extends Transparencys{ 
    boolean pause = true;
    private int time;
    private double HP = 15;
    private int dam;
    private int si = 0;
    private double mo;
    private int shotf;
    int sh3 = 10;
    int sh5 = 4;
    int sx = 0;
    int sy = 0;
    boolean sh = true;
    boolean def = true;
    int fl;
    boolean mpmv = false;
    public Mychr(int shot, World wl){
        World spaceworld = wl;
        shotf = shot;
        GreenfootImage image = getImage();
        image.scale(54,36);
        setImage(image);
    }

    public void act(){
        if(pause){
            //updateImage();
            if(!mpmv){
                shot();
                Move();
            }
            Actor enemy1 = getOneIntersectingObject(Enemy1.class);
            if(enemy1 != null){
                Damage(2, true);
                getWorld().removeObject(enemy1);
            }
            Actor enemy2 = getOneIntersectingObject(Enemy2.class);
            if(enemy2 != null){
                Damage(2, true);
                getWorld().removeObject(enemy2);
            }

            if(HP <= 0){
                death();
            }
        }
    }

    private void Move(){
        if(Greenfoot.isKeyDown("i")){
            sy -= 5;
        }

        if(Greenfoot.isKeyDown("k")){
            sy += 5;
        }

        if(Greenfoot.isKeyDown("j")){
            sx -= 5;
        }

        if(Greenfoot.isKeyDown("l")){
            sx += 5;
        }

        setLocation(getX() + sx, getY() + sy);
        sx = 0;
        sy = 0;

        if(getY() > 585){
            setLocation(getX(), 585);
        }
        if(getY() < 45){
            setLocation(getX(), 45);
        }
        if(getX() < 82){
            setLocation(82,getY());
        }
        if(getX() > 578){
            setLocation(578,getY());
        }
    }

    public void shot(){
        if(sh){
            switch(shotf){
                case 1:
                if((time%13)==0 && Greenfoot.isKeyDown("z")){
                    Can can1 = new Can(1, 27, 7, 1);
                    getWorld().addObject(can1, getX()+10,getY()-10);
                    can1.setRotation(-90);
                    Can can2 = new Can(1, 27, 7, 1);
                    getWorld().addObject(can2, getX()-10,getY()-10);
                    can2.setRotation(-90);
                    shmusic();
                }
                time ++;
                break;

                case 2:
                if(Greenfoot.isKeyDown("z")){
                    if((time%13)==0){
                        Can can1 = new Can(1, 27, 7, 1);
                        getWorld().addObject(can1, getX()+10,getY()-10);
                        can1.setRotation(-90);
                        Can can2 = new Can(1, 27, 7, 1);
                        getWorld().addObject(can2, getX()-10,getY()-10);
                        can2.setRotation(-90);
                        shmusic();
                    }
                    if((time%26)==0){
                        Can can3 = new Can(3, 15, 7, 0.5);
                        getWorld().addObject(can3, getX()-25,getY()-10);
                        can3.setRotation(-130);
                        Can can4 = new Can(3, 15, 7, 0.5);
                        getWorld().addObject(can4, getX()+25,getY()-10);
                        can4.setRotation(-50);
                    }
                }
                time ++;
                break;

                case 3:
                if(Greenfoot.isKeyDown("z")){
                    if((time%13)==0){
                        Can can1 = new Can(1, 27, 7, 1);
                        getWorld().addObject(can1, getX()+10,getY()-10);
                        can1.setRotation(-90);
                        Can can2 = new Can(1, 27, 7, 1);
                        getWorld().addObject(can2, getX()-10,getY()-10);
                        can2.setRotation(-90);
                        shmusic();
                    }
                    if((time%26)==0){
                        Can can3 = new Can(3, 15, 7, 0.5);
                        getWorld().addObject(can3, getX()-25,getY()-10);
                        can3.setRotation(-130);
                        Can can4 = new Can(3, 15, 7, 0.5);
                        getWorld().addObject(can4, getX()+25,getY()-10);
                        can4.setRotation(-50);
                    }
                    if((time%45)==0){
                        Can can1 = new Can(4, 15, 6, 1);
                        getWorld().addObject(can1, getX() - 40, getY() - 40);
                        can1.setRotation(-110);
                        Can can2 = new Can(4, 15, 6, 1);
                        getWorld().addObject(can2, getX() + 40, getY() - 40);
                        can2.setRotation(-70);
                    }
                }
                time ++;
                break;

                case 6:
                if ((time%15) == 0 && Greenfoot.isKeyDown("z")) {
                    si += sh3;
                    Can can1 = new Can(2, 30, 7, 1);
                    getWorld().addObject(can1, getX() + 15,getY() - 20);
                    can1.setRotation(-90);
                    Can can2 = new Can(2, 30, 7, 1);
                    getWorld().addObject(can2, getX() - 15,getY() - 20);
                    can2.setRotation(-90);
                    Can can3 = new Can(2, 30, 7, 1);
                    getWorld().addObject(can3, getX() + 30,getY() - 15);
                    can3.setRotation(-80 + si);
                    Can can4 = new Can(2, 30, 7, 1);
                    getWorld().addObject(can4, getX() - 30,getY() - 15);
                    can4.setRotation(-100 - si);
                    if(si >= 60 || si <= 0){
                        sh3 *= -1;
                    }
                    shmusic();
                }
                time ++;
                break;

                case 7:
                if((time%15) == 0 && Greenfoot.isKeyDown("z")){
                    Can can1 = new Can(3, 15, 7, 0.6);
                    getWorld().addObject(can1, getX() - 40, getY());
                    can1.setRotation(-140);
                    Can can2 = new Can(3, 15, 7, 0.6);
                    getWorld().addObject(can2, getX(), getY());
                    can2.setRotation(-90);
                    Can can3 = new Can(3, 15, 7, 0.6);
                    getWorld().addObject(can3, getX() + 40, getY());
                    can3.setRotation(-40);
                    shmusic();
                }
                time ++;
                break;

                case 8:
                if((time%15) == 0 && Greenfoot.isKeyDown("z")){
                    Can can1 = new Can(4, 15, 6, 1);
                    getWorld().addObject(can1, getX() - 40, getY() - 40);
                    can1.setRotation(-110);
                    Can can2 = new Can(4, 15, 6, 1);
                    getWorld().addObject(can2, getX() + 40, getY() - 40);
                    can2.setRotation(-70);
                    shmusic();
                }
                time ++;
                break;

                case 9:
                if ((time%5) == 0 && Greenfoot.isKeyDown("z")) {
                    si += sh5;
                    Can can3 = new Can(5, 17, 7, 0.4);
                    getWorld().addObject(can3, getX(),getY());
                    can3.setRotation(-90 + si);
                    Can can4 = new Can(5, 17, 7, 0.4);
                    getWorld().addObject(can4, getX(),getY());
                    can4.setRotation(-90 - si);
                    if(si >= 70 || si <= -70){
                        sh5 *= -1;
                    }
                    shmusic();
                }
                time ++;
                break;

                case 10:
                if((time%2)==0&&Greenfoot.isKeyDown("z")) {
                    Can can = new Can(6, 8, 8, 0.2);
                    getWorld().addObject(can,getX(),getY()-10);
                    can.setRotation(-90);
                }
                time++;
                break;
            }
        }
    }

    public void Damage(double dam, boolean damflag){
        HP -= dam;
        if(HP > 15){
            HP = 15;
        }
        (((World1)getWorld()).getHPCounter()).MyHP(HP);
        if(damflag){
            Counter counter = ((World1)getWorld()).getCounter();
            (((World1)getWorld()).getCounter()).bumpCount(5);
            (((World1)getWorld()).getStage()).enDestroy(true);
        }
    }

    public void death(){
        //getWorld().removeObjects(getWorld().getObjects(Options.class));
        ((World1)getWorld()).Gameover();
    }

    private void shmusic(){
        /*if(!mysh.isPlaying()){
        mysh.play();
        }*/
    }

    public double getHP(){
        return HP;
    }

    public void setshab(boolean shf){
        sh = shf;
    }

    public void mapmove(boolean maf){
        mpmv = maf;
    }

    public void setshflag(int shof){
        shotf = shof;
    }

    private void updateImage(){  
        /*fl = ((World1)getWorld()).getflag();
        GreenfootImage image = new GreenfootImage(30, 30);
        image.setColor(Color.black);
        image.setTransparency(255);
        image.fill();
        GreenfootImage txtImg = new GreenfootImage("a"+fl, 46, Color.white, new Color(0, 0, 0, 0)); 
        image.drawImage(txtImg, (image.getWidth() - txtImg.getWidth())/2, (image.getHeight()-txtImg.getHeight())/2);  
        setImage(image);*/
    }

    public void Pausing(boolean pau){
        pause = pau;
    }
}
