import greenfoot.*;
import java.util.List;

public class Encan2 extends Encan{
    int count,count2,Distx,Disty,ax,ay,bx,by,Aw,Ah,x,y;
    double angle,dam;
    GreenfootImage Ai,i,i2;
    int w=15,h=7;
    boolean b;
    boolean pause = true;
    List c;
    Mychr g;
    public boolean storing;
    int myang;
    public Encan2(boolean st){
        setImage("encan2.png");
        getImage().setTransparency(0);
        storing = st;
        //getImage().scale(15,7);
    }

    public Encan2(double damage){
        setImage("encan2.png");
        getImage().scale(15,7);
        dam = damage;
        i=getImage(); 
        i2 = new GreenfootImage(w,h);
    }

    public void act(){
        if(!storing&&pause){
            move(5);
            if(count <= 15 && count2 >= 5){
                c=getWorld().getObjects(Mychr.class);
                g=(Mychr)c.get(0);
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
            
            if(getX() > 575 || getX() < 55 || getY() > 605 || getY() < 25){
                store();
            }else{
                g = (Mychr)getOneIntersectingObject(Mychr.class);
                if(g != null){
                    if(touch(g)){
                        g.Damage(dam,false);
                        store();
                    }
                }
            }
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

    public void delete(){
        Ai = null;
        i = null;
        i2 = null;
        c = null;
        g = null;
        getWorld().removeObject(this);
    }

    public void inmap(int inx, int iny, double damage){
        getImage().setTransparency(255);
        storing = false;
        getImage().scale(15,7);
        i = getImage(); 
        i2 = new GreenfootImage(w,h);
        dam = damage;
        count = 0;
        count2 = 90;
        setLocation(inx, iny);
        g=((A)getWorld()).getMychr();
        Distx=getX()-g.getX();
        Disty=getY()-g.getY();
        angle = Math.toDegrees(Math.atan2(Disty,Distx))+180;
        setRotation((int)angle);
    }

    public void store(){
        getImage().setTransparency(0);
        Ai = null;
        i = null;
        i2 = null;
        c = null;
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
