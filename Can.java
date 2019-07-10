import greenfoot.*;
import java.util.List;
public class Can extends Transparencys{
    double dam=1,angle;
    GreenfootImage Ai,i,i2=new GreenfootImage(15,7);
    int w=15,h=7,Aw,Ah,x,y,type,count,count2,Distx,Disty,anime=0,anicount=1,ax,ay,bx,by,enx;
    boolean b,fl=true,pause=true,flag=true,bomf=false;
    Actor encan,enemys;
    Can incan;
    Enemys ene,misene;
    List enes,cans;
    public boolean storing;
    public Can(boolean st){
        storing = st;
        getImage().setTransparency(0);
    }

    public void act(){
        if(!storing&&pause){
            shtype();
        }
    }

    public void shtype(){
        switch(type){
            case 1:// Normal
            move(13); 
            hittest(false);
            break;

            case 2:// Reflect
            move(13);
            Reflecter();      
            hittest(false);
            break;

            case 3:// Missile
            move(7);
            if(count <= 25 && count2 >= 1){
                enes = getObjectsInRange(240,Enemys.class); 
                if (!enes.isEmpty()){
                    for(int i=0;i<enes.size();i++){
                        ene = (Enemys)enes.get(i); 
                        if(!ene.getStoring()){
                            Distx=getX()-ene.getX();
                            Disty=getY()-ene.getY();
                            angle = Math.toDegrees(Math.atan2(Disty,Distx))+180;
                            if(angle - getRotation() > 10){
                                angle = getRotation() + 10;
                            }else if(angle - getRotation() < -10){
                                angle = getRotation() - 10;
                            }
                            setRotation((int)angle);
                            break;
                        }
                    }
                }
                count2 = 0;
                count ++;
            }else{
                count2 ++;
            }
            hittest(false);
            break;

            case 4:// Shotgun
            move(13);
            if(count > 2){
                w -= 6;
                //sy -= 4;
                if(w > 0 && h > 0){
                    for(int i=0;i < 6;i++){
                        inCan(getX(),getY(),getRotation()+Greenfoot.getRandomNumber(210)-155,0.2,w,h,4);
                    }
                }
                flag = false;
                store();
            }
            count++;
            hittest(false);
            break;

            case 5:// Burst
            Reflecter();
            if(bomf){
                if((anime%2) == 0){
                    setImage("Burst1/burst" + anicount +".png");
                    getImage().scale(w,h);
                    if(anicount == 10){
                        flag = false;
                        store();
                    }else{
                        anicount ++;
                    }   
                    if(flag){
                        i = getImage();
                        hittest(true);
                    }
                }
                anime++;
            }else{
                move(7);
                if(getX() > 605 || getX() < 55 || getY() > 625 || getY() < 5){
                    store();
                }
                encan = getOneObjectAtOffset(0,0,Encan.class);
                enemys = getOneIntersectingObject(Enemys.class);                   
                if(encan != null || enemys != null){
                    if(encan != null){
                        ((Encan)encan).store();
                        store();
                    }else{
                        bomf = true;
                        w=100;
                        h=100;
                        i2=new GreenfootImage(100,100);
                    }
                }
            }
            break;

            case 6:
            //getImage().setTransparency(150);
            if(count<30){
                while(getImage().getTransparency()!=150){
                    getImage().setTransparency(150);
                }
            }
            setLocation((((A)getWorld()).getMychr()).getX(),(((A)getWorld()).getMychr()).getY()/2);
            if(flag){
                getImage().scale((((A)getWorld()).getMychr()).getY(),20);
                enes=getWorld().getObjects(Enemys.class);
                for(int i=0;i<enes.size();i++){
                    ene = (Enemys)enes.get(i);
                    enx = ene.getImage().getWidth()/2;
                    bx = (((A)getWorld()).getMychr()).getX();
                    if(bx+7>ene.getX()-enx&&bx-7<ene.getX()+enx&&(((A)getWorld()).getMychr()).getY()>ene.getY()){
                        ene.Damage(dam);
                    }
                }
            }
            count++;
            break;

            case 7:
            //getImage().setTransparency(150);
            if(count<30){
                while(getImage().getTransparency()!=150){
                    getImage().setTransparency(150);
                }
            }
            setLocation((((A)getWorld()).getMychr()).getX(),(((A)getWorld()).getMychr()).getY()/2);
            if(flag){
                getImage().scale((((A)getWorld()).getMychr()).getY(),20);
                enes=getWorld().getObjects(Enemys.class);
                for(int i=0;i<enes.size();i++){
                    ene = (Enemys)enes.get(i);
                    enx = ene.getImage().getWidth()/2;
                    bx = (((A)getWorld()).getMychr()).getX();
                    if(bx+10>ene.getX()-enx&&bx-10<ene.getX()+enx&&(((A)getWorld()).getMychr()).getY()>ene.getY()){
                        inCan(ene.getX(),ene.getY()-10,90,0.4,17,7,5);
                    }
                }
            }
            count++;
            break;

            case 8:
            move(9);
            if(count > 2){
                if(w > 0 && h > 0){
                    for(int i=0;i < 7;i++){
                        inCan(getX(),getY(),getRotation()+Greenfoot.getRandomNumber(90)-45,0.04,4,4,1);
                    }
                }
                flag = false;
                store();
            }
            count++;
            hittest(false);
            break;
        }
    }

    private void Reflecter(){
        if(getX() > 595 || getX() < 65){
            if(getX() > 595){
                setLocation(595,getY());
            }else if(getX() < 65){
                setLocation(65,getY());
            }
            setRotation(-getRotation() + 180);
        }
    }

    public void hittest(boolean bom){
        if(getX() > 605 || getX() < 55 || getY() > 625 || getY() < 5){
            store();
        }else{
            encan = getOneIntersectingObject(Encan.class);
            enemys = getOneIntersectingObject(Enemys.class);
            if(encan != null){
                ((Encan)encan).store();
                if(!bom)store();
            }
            if(enemys != null){
                if(touch(enemys)){
                    ((Enemys)enemys).Damage(dam);
                    if(!bom)store();
                }
            }
        }
    }

    public void inCan(int canx, int cany, int deg, double damage, int six, int siy, int type){
        cans = getWorld().getObjects(Can.class);
        for(int i=0;i<cans.size();i++){
            incan = (Can)(cans.get(i));
            if(incan.getStoring()){
                incan.inmap(canx,cany,damage,six,siy,type);
                incan.setRotation(deg);
                break;
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

    public void inmap(int inx, int iny, double damage,int six, int siy, int shty){
        storing = false;
        bomf = false;
        anime = 0;
        anicount = 1;
        w = six;
        h = siy;
        dam = damage;
        type = shty;
        getImage().setTransparency(255);
        if(type==6||type==7){
            setImage("Cannon/mybeam1.png");
            while(getImage().getTransparency()!=150){
                getImage().setTransparency(150);
            }
        }else{
            setImage("Cannon/mycan1.png");
        }
        getImage().scale(six,siy);
        setLocation(inx, iny);
        i = getImage();
        i2=new GreenfootImage(six,siy);
    }

    public void store(){
        flag=true;
        Ai = null;
        i = null;
        i2 = null;
        encan = null;
        enemys = null;
        count=0;
        count2=0;
        setLocation(330,629);
        getImage().clear();
        storing = true;
        getImage().setTransparency(0);
    }

    public boolean getStoring(){
        return storing;
    }

    public void Pausing(boolean pau){
        pause = pau;
    }
}
