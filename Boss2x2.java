import greenfoot.*;
import java.awt.*;
public class Boss2x2 extends Bosss{
    boolean pause = true;
    private int movecount = 0;
    private int movecount2 = 0;
    private boolean firstmove2 = true;
    private int shcount = 0;
    private int sh2count = 0;
    public double HP = 260;
    private boolean bossflag = true;
    private boolean flag = true;
    private int rot = -70;
    private int rot2;
    private int beamcount = 0;
    private int beamcount2 = 0;
    private int beamX;
    int beamrot = 50;
    int bretime = 0;
    Maps map;
    public int trans = 255;
    public int descount = 0;
    private int burstx,bursty,brsize;
    public Boss2x2(Maps ma){
        setImage("Boss/boss2-2.png");
        GreenfootImage image = getImage();
        image.scale(360,140);
        setImage(image);
        map = ma;
    }

    public void act(){
        if(pause){
            //updateImage();
            if(movecount < 40){
                setLocation(getX(), getY() + 2);
                movecount ++;
                if(HP != 260){
                    HP = 260;
                }
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
                        ((World1)getWorld()).getCounter().bumpCount(2000);
                        map.bossDestroy();
                        getWorld().removeObject(this);
                    }
                }else{
                    shot();
                }
            }
        }
    }    

    public void shot(){
        if(HP >= 160){
            if(shcount >= 50){
                shcount = 0;
                for(int i=0; i<15; i++){
                    Beam2 encan = new Beam2(3,25,true,1);
                    getWorld().addObject(encan, getX() + 80, getY() + 60);
                    encan.setRotation(rot + i * 33 * 5);
                }
                for(int i=0; i<15; i++){
                    Beam2 encan2 = new Beam2(3,25,true,1);
                    getWorld().addObject(encan2, getX() - 80, getY() + 60);
                    rot2 = 180 - rot;
                    encan2.setRotation(rot2 - i * 33 * 5);
                }
                sh2count ++;
                if(sh2count <= 5){
                    rot += 32;
                }else if(sh2count == 5){
                    sh2count = 0;
                }else{
                    rot -= 24;
                }
            }else{
                shcount ++;
            }
        }else if(HP > 0){
            if(firstmove2){
                if(movecount2 < 45){
                    setLocation(getX() - 5, getY());
                    movecount2 ++;
                }else{
                    firstmove2 = false;
                    movecount2 = 0;
                }
            }else if(movecount2 < 90){
                setLocation(getX() + 5, getY());
                movecount2 ++;
            }else if(movecount2 < 180){
                setLocation(getX() - 5, getY());
                movecount2 ++;
            }else{
                movecount2 = 0;
            }
            beamcount ++;
            if(beamcount >= 45){
                getWorld().addObject(new Beam1(10, beamrot, false,0.1), getX() + 97, getY() + 60);
                getWorld().addObject(new Beam1(10, beamrot, false,0.1), getX() - 94, getY() + 60);
                beamcount2 ++;
                if(beamcount2 >= 54){
                    beamrot -= 6;
                }
            }
            if(beamcount2 >= 60){
                beamcount = 0;
                beamcount2 = 0;
                beamrot = 50;
            }
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
}
