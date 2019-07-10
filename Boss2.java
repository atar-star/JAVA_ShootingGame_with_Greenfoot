import greenfoot.*;
import java.util.List;
public class Boss2 extends Boss{
    boolean pause = true;
    private int movecount = 0,shcount = 0,burstx,bursty,brsize;
    public double HP = 120,angle,x,y,deg=-90,ad;
    public int trans = 255,descount = 0,sizx = 125,sizy = 120;
    Maps map;
    Boss2option boop21 = new Boss2option(this,70,30,1),boop22=new Boss2option(this,-70,-30,2);
    List bursts,beams;
    Burst inburst;
    Beam1 inbeam;
    public Boss2(Maps ma){
        setImage("Boss/boss2.png");
        getImage().scale(sizx,sizy);
        if(Greenfoot.getRandomNumber(1)==0)ad=3;
        else ad=-3;
        map = ma;
    }

    public void act(){
        if(pause){
            if(movecount < 1){
                getWorld().addObject(boop21, getX(), getY());
                getWorld().addObject(boop22, getX(), getY());
                movecount ++;
            }else if(movecount < 30){
                setLocation(getX(), getY() + 2);
                x=getX();
                y=getY();
                movecount ++;
            }
            if(movecount >= 30){
                if(HP <= 0){
                    if(trans>20){
                        boop21.OrderTransparence(trans);
                        boop22.OrderTransparence(trans);
                        super.OrderTransparence(trans);
                        trans-=3;
                        if((descount%13)==0){
                            burstx = Greenfoot.getRandomNumber(161) - 80;
                            bursty = Greenfoot.getRandomNumber(141) - 70;
                            brsize = Greenfoot.getRandomNumber(41) + 40;
                            Burst();
                        }
                        descount++;
                    }else{
                        ((A)getWorld()).getCounter().bumpCount(1000);
                        map.bossDestroy();
                        (((A)getWorld()).getMychr()).setshtype(3);
                        getWorld().removeObject(boop21);
                        getWorld().removeObject(boop22);
                        getWorld().removeObject(this);
                    }
                }else{
                    move();
                    shot();
                }
            }
        }
    }    

    public void shot(){
        if(HP > 0){
            if(!getWorld().getObjects(Boss2option.class).isEmpty()){
                if(((deg+90)%360)==0||(shcount%200)==0){
                    angle=Math.toDegrees(Math.atan2(getY()-(((A)getWorld()).getMychr()).getY(),getX()-(((A)getWorld()).getMychr()).getX()))+180;
                    for(int i=0; i<5; i++){
                        inBeam((int)angle - 40 + 20 * i, 56, 6);
                    }
                }
            }else{
                if((deg%39)==0){
                    angle=Math.toDegrees(Math.atan2(getY()-(((A)getWorld()).getMychr()).getY(),getX()-(((A)getWorld()).getMychr()).getX()))+180;
                    for(int i=0; i<5; i++){
                        inBeam((int)angle - 40 + 20 * i, 28, 6);
                    }
                }
            }
            shcount ++;  
        }
    }

    public void move(){
        x=Math.cos(Math.toRadians(deg))*180+330;
        y=Math.sin(Math.toRadians(deg))*70+120;
        deg+=ad;
        setLocation((int)Math.round(x),(int)Math.round(y));
    }

    public void Damage(double dam){
        HP -= dam;
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
