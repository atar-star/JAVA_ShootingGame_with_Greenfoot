import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.awt.*;
public class Enemy1 extends Enemy{   
    private boolean deadflag = true;
    private int pat,shpat; 
    private double moving;
    private int intime = Greenfoot.getRandomNumber(9) * 10;
    private int count = 0;
    private int shcount = 100;
    private int rad = 0;
    private int anime = 0;
    private int anicount = 1;
    double HP;
    double ag = 90;
    double vx,vy,mx,my,si;
    double i = 0;
    int a = 0;
    int x;
    double moag;
    double carvmo;
    Maps map;
    int sizx = 20;
    int sizy = 20;
    public Enemy1(int mo, int sh, double move, double carve, double caag, Maps ma, double hp){
        if(mo == -1){
            pat = Greenfoot.getRandomNumber(11);
        }else{
            pat = mo;
        }
        if(sh == -1){
            shpat = Greenfoot.getRandomNumber(5); 
        }else{
            shpat = sh;
        }
        moving = move;
        carvmo = carve;
        moag = caag;
        vx = move;
        vy = move;
        mx = 0;
        my = move;
        map = ma;
        HP = hp;
        intime = Greenfoot.getRandomNumber(4) * 10 + 50;
        if(pat == 7){
            setRotation(60);
        }else if(pat == 8){
            setRotation(-60);
        }
        getImage().scale(sizx,sizy);
    }

    public void act(){
        if(pause){
            if((anime%11) == 0){
                setImage("Enemy/Ene1/ene1-" + anicount +".png");
                if(anicount == 13){
                    anicount = 1;
                }else{
                    anicount ++;
                }
            }
            anime ++;
            //updateImage();
            if (!getWorld().getObjects(Mychr.class).isEmpty()){
                switch(pat){
                    case 0:
                    setLocation(getX(), (int)(getY() + moving));
                    break;

                    case 1:
                    if(intime > count){
                        setLocation(getX(), getY() + 4);
                    }

                    break;
                    case 2:
                    if(intime > count){
                        setLocation(getX(), getY() + 3);
                    }else{
                        setLocation(getX(), getY() - 3);
                    }

                    break;

                    case 3:
                    if(intime > count){
                        setLocation(getX(), getY() + 2);
                        if(count >= 15){
                            setLocation(getX() - 2, getY());
                        }
                    }
                    break;

                    case 4:
                    if(intime > count){
                        setLocation(getX(), getY() + 2);
                        if(count >= 15){
                            setLocation(getX() + 2, getY());
                        }
                    }
                    break; 

                    case 5:
                    if(a==0){
                        x = getX();
                    }
                    si = Math.toDegrees(Math.sin(i)) * 0.7;
                    setLocation(x + (int)si, getY() + 3);
                    if((a%1)==0){
                        i+= 0.1;
                    }
                    a++;
                    break;

                    case 6:
                    if(a==0){
                        x = getX();
                    }
                    si = Math.toDegrees(Math.cos(i)) * 0.8;
                    setLocation(x + (int)si, getY() + 3);
                    if((a%1)==0){
                        i+= 0.1;
                    }
                    a++;
                    break;

                    case 7:
                    if(count < 120){
                        turn(Greenfoot.getRandomNumber(2) + 1);
                    }
                    move(4);
                    break;

                    case 8:
                    if(count < 120){
                        turn(-(Greenfoot.getRandomNumber(2) + 1));
                    }
                    move(4);
                    break;

                    case 9://Left turn
                    if(count >= 50 && count <= carvmo/moag+50){
                        mx = Math.cos(Math.toRadians(ag)) * moving * 1.3;
                        my = Math.sin(Math.toRadians(ag)) * moving * 1.3;
                        ag += moag;
                    }else if(count > carvmo/moag+50){
                        mx = Math.cos(Math.toRadians(ag)) * moving * 1.3;
                        my = Math.sin(Math.toRadians(ag)) * moving * 1.3;
                    }
                    setLocation(getX() + (int)(mx), getY() + (int)(my));
                    break;

                    case 10://Right turn
                    if(count >= 50 && count <= carvmo/moag+50){
                        mx = Math.cos(Math.toRadians(ag)) * moving * 1.3;
                        my = Math.sin(Math.toRadians(ag)) * moving * 1.3;
                        ag -= moag;
                    }else if(count > carvmo/moag+50){
                        mx = Math.cos(Math.toRadians(ag)) * moving * 1.3;
                        my = Math.sin(Math.toRadians(ag)) * moving * 1.3;
                    }
                    setLocation(getX() + (int)(mx), getY() + (int)(my));
                    break;
                }
                count ++;       
            }

            switch(shpat){
                case 0:
                break;
                case 1:
                if(shcount > 110){
                    List b=getWorld().getObjects(Mychr.class);
                    int Distx, Disty;
                    double angle;
                    Mychr g=(Mychr)b.get(0);
                    Distx=getX()-g.getX();
                    Disty=getY()-g.getY();
                    angle=Math.toDegrees(Math.atan2(Disty,Distx))+180;
                    Encan1 encan = new Encan1(1);
                    getWorld().addObject(encan, getX(), getY());
                    encan.setRotation((int)angle);
                    shcount = 0;
                }
                break;

                case 2:
                if(shcount > 130){
                    List b=getWorld().getObjects(Mychr.class);
                    int Distx, Disty;
                    double angle;
                    Mychr g=(Mychr)b.get(0);
                    Distx=getX()-g.getX();
                    Disty=getY()-g.getY();
                    angle=Math.toDegrees(Math.atan2(Disty,Distx))+180;
                    shcount = 0;
                    Encan1 encan1 = new Encan1(1);
                    getWorld().addObject(encan1, getX(), getY());
                    encan1.setRotation((int)angle);
                    Encan1 encan2 = new Encan1(1);
                    getWorld().addObject(encan2, getX(), getY());
                    encan2.setRotation((int)angle + 20);
                    Encan1 encan3 = new Encan1(1);
                    getWorld().addObject(encan3, getX(), getY());
                    encan3.setRotation((int)angle - 20);
                }
                break;

                case 3:
                if(shcount > 10){
                    shcount = 0;
                    Encan1 encan13 = new Encan1(1);
                    getWorld().addObject(encan13, getX(), getY());
                    encan13.setRotation(90 + rad * 30);
                    rad ++;
                }
                break;

                case 4:
                if(shcount > 90){
                    shcount = 0;
                    for(int i=0; i<9; i++){
                        Encan1 encan14 = new Encan1(1);
                        getWorld().addObject(encan14, getX(), getY());
                        encan14.setRotation(90 + rad * 40);
                        rad ++;
                    }
                }
                break;
            }
            shcount ++;

            if(HP <= 0){
                ((World1)getWorld()).getCounter().bumpCount(30);
                map.enDestroy(true);
                getWorld().removeObject(this);
            }else{
                if(getX() > 596 || getX() < 64 || getY() > 600 || getY() < 2){
                    map.enDestroy(false);
                    getWorld().removeObject(this);
                }
            }
        }
    }

    public void Damage(double dam){
        HP -= dam;
    }

    private void updateImage(){  
        /*GreenfootImage image = new GreenfootImage(30, 30);
        image.setColor(Color.black);
        image.setTransparency(50);
        image.fill();
        GreenfootImage txtImg = new GreenfootImage("a"+(int)HP, 46, Color.white, new Color(0, 0, 0, 0)); 
        image.drawImage(txtImg, (image.getWidth() - txtImg.getWidth())/2, (image.getHeight()-txtImg.getHeight())/2);  
        setImage(image);*/
    }

    public int getSizeX(){
        return sizx;
    }

    public int getSizeY(){
        return sizy;
    }
}
