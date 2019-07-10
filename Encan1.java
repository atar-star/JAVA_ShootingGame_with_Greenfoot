import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
public class Encan1 extends Encan{
    boolean pause=true,storing;
    double angle,dam;
    Mychr myc,g;
    GreenfootImage Ai,i,i2;
    int w=15,h=7,speed,count,count2,myang,ax,ay,bx,by,Aw,Ah,x,y,Distx,Disty,shty,spreaddeg=Greenfoot.getRandomNumber(361);
    boolean b;
    List beams,encans;
    Beams beam;
    Encan1 inencan;
    public Encan1(boolean st){
        setImage("Cannon/encan1.png");
        getImage().setTransparency(0);
        storing = st;
        //getImage().scale(15,7);
    }

    public void act(){
        if(!storing&&pause){
            shot();
            if(getX()>575 || getX()<55 || getY()>605 || getY()<25){
                store();
            }else{
                myc = (Mychr)getOneIntersectingObject(Mychr.class);
                if(myc != null){
                    if(touch(myc)){
                        myc.Damage(dam,false);
                        store();
                    }
                }
            }
        }
    }

    public void shot(){
        switch(shty){
            case 1:
            move(speed);
            break;

            case 2:
            move(speed);
            if(count <= 15 && count2 >= 5){
                Distx=getX()-g.getX();
                Disty=getY()-g.getY();
                angle = Math.toDegrees(Math.atan2(Disty,Distx))+180;
                if(angle>360)angle-=360;
                //if(angle<0)angle+=360;
                myang = getRotation();
                if(myang>180){
                    if(myang<angle&&myang+180>angle){
                        if(angle-myang>20)angle=myang+20;
                    }else if(myang-180>angle){
                        if(myang-angle>20)angle=myang+20;
                    }else if(angle==myang){
                    }else{
                        if(myang-angle>20)angle=myang-20;
                    }
                }else{
                    if(myang>angle&&myang-180<angle){
                        if(myang-angle>20)angle=myang-20;
                    }else if(myang+180<angle){
                        if(angle-myang>20)angle=myang-20;
                    }else if(angle==myang){
                    }else{
                        if(angle-myang>20)angle=myang+20;
                    }
                }
                setRotation((int)angle);
                count2 = 0;
                count ++;
            }else{
                count2 ++;
            }
            break;

            case 3:
            move(speed);
            if(speed<4){
                if(getOneIntersectingObject(Beam1.class) != null){
                    for(int j = 0;j < 5;j++){
                        inEncan(getX(),getY(),0.5,1,3,spreaddeg+Greenfoot.getRandomNumber(60));
                        spreaddeg+=72;
                    }
                    spreaddeg=Greenfoot.getRandomNumber(361);
                    store();
                    break;
                }
            }
            break;
        }
    }

    public boolean touch(Actor A){    
        Ai=A.getImage(); 
        bx=getX();
        by=getY();
        x = A.getX();
        y = A.getY();
        Aw = Ai.getWidth();
        Ah = Ai.getHeight();
        i2.clear();
        i2.drawImage(Ai,x-bx-(Aw/2-w/2),y-by-(Ah/2-h/2));
        for(int yi = 0; yi<h; yi++)  
            for(int xi = 0; xi<w; xi++)  
                if(i2.getColorAt(xi,yi).getAlpha()>0 && i.getColorAt(xi,yi).getAlpha()>0)  
                    return b=true;
        return b=false;  
    }

    public void inEncan(int canx, int cany, double damage, int type, int speed, int deg){
        encans = getWorld().getObjects(Encan1.class);
        for(int i=0;i<encans.size();i++){
            inencan = (Encan1)(encans.get(i));
            if(inencan.getStoring()){
                inencan.inmap(canx,cany,damage,type,speed);
                inencan.setRotation(deg);
                break;
            }
        }
    }

    public void inmap(int inx, int iny, double damage, int type, int sped){
        getImage().setTransparency(255);
        storing = false;
        if(shty!=1&&type==1)setImage("Cannon/encan1.png");
        else if(shty!=2&&type==2)setImage("Cannon/encan2.png");
        shty = type;
        if(type==2){
            count = 0;
            count2 = 90;
            g=((A)getWorld()).getMychr();
            Distx=inx-g.getX();
            Disty=iny-g.getY();
            angle = Math.toDegrees(Math.atan2(Disty,Distx))+180;
            setRotation((int)angle);
        }
        speed = sped;
        getImage().scale(15,7);
        i = getImage(); 
        i2 = new GreenfootImage(w,h);
        dam = damage;
        setLocation(inx, iny);
    }

    public void store(){
        getImage().setTransparency(0);
        Ai = null;
        i = null;
        i2 = null;
        myc = null;
        g = null;
        setLocation(330,629);
        //getImage().scale(5,5);
        storing = true;
    }

    public boolean getStoring(){
        return storing;
    }

    public void Pausing(boolean pau){
        pause = pau;
    }
}
