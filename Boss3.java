import greenfoot.*;
import java.awt.Color;
import java.util.List;
public class Boss3 extends Boss{
    private boolean pause=true,firstmove2=true,bossflag=true,flag=true;
    public double HP = 100;
    private int movecount=0,movecount2=0,rot=-70,shcount=0,sh2count=0,
    rot2,beamcount=0,beamcount2=0,beamX,beamrot=50,bretime=0;
    public int trans=255,descount=0,burstx,bursty,brsize;
    Maps map;
    List enattacks,bursts,beams;
    Burst inburst;
    Beam1 inbeam;
    public Boss3(Maps ma){
        setImage("Boss/boss3.png");
        getImage().scale(360,140);
        map = ma;
    }

    public void act(){
        if(pause){
            //updateImage();
            if(movecount < 40){
                setLocation(getX(), getY() + 2);
                movecount ++;
                if(HP != 100){
                    HP = 100;
                }
            }
            if(movecount >= 40){
                if(HP <= 0){
                    enattacks = getWorld().getObjects(EneAttack.class);
                    for(int i=0;i<enattacks.size();i++){
                        ((EneAttack)enattacks.get(i)).store();
                    }
                    
                    if(trans>20){
                        super.OrderTransparence(trans);
                        trans-=3;
                        if((descount%13)==0){
                            burstx = Greenfoot.getRandomNumber(161)-80;
                            bursty = Greenfoot.getRandomNumber(141)-70;
                            brsize = Greenfoot.getRandomNumber(41)+40;
                            Burst();
                        }
                        descount++;
                    }else{
                        ((A)getWorld()).getCounter().bumpCount(2000);
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
        if(HP >= 60){
            if(shcount >= 50){
                shcount = 0;
                /*for(int i=0; i<15; i++){
                    Beam2 encan = new Beam2(3,25,true,1);
                    getWorld().addObject(encan, getX() + 80, getY() + 60);
                    encan.setRotation(rot + i * 33 * 5);
                }
                for(int i=0; i<15; i++){
                    Beam2 encan2 = new Beam2(3,25,true,1);
                    getWorld().addObject(encan2, getX() - 80, getY() + 60);
                    rot2 = 180 - rot;
                    encan2.setRotation(rot2 - i * 33 * 5);
                }*/
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
                //inBeam(10, beamrot, false,0.1)
                //getWorld().addObject(new Beam1(10, beamrot, false,0.1), getX() + 97, getY() + 60);
                //getWorld().addObject(new Beam1(10, beamrot, false,0.1), getX() - 94, getY() + 60);
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
    
    public void inBeam(int deg, int six, int siy){
        beams = getWorld().getObjects(Beam1.class);
        for(int i=0;i<beams.size();i++){
            inbeam = (Beam1)(beams.get(i));
            if(inbeam.getStoring()){
                inbeam.inmap(getX(),getY()+26,six,siy,7,1);
                inbeam.setRotation(deg);
                break;
            }
        }
    }
    
    public void Burst(){
        bursts = getWorld().getObjects(Burst.class);
        for(int i=0;i<bursts.size();i++){
            inburst = (Burst)(bursts.get(i));
            if(inburst.getStoring()){
                inburst.inmap(getX()+burstx,getY()+bursty,burstx,bursty,brsize,Greenfoot.getRandomNumber(3)+1);
                break;
            }
        }
    }
}
