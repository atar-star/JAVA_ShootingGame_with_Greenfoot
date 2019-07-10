import greenfoot.*;
import java.util.List;
public class HugeBeam extends Beams{
    boolean pause = true,storing;
    int count = 0,mx,x,y,myx,myy,enx,eny;
    List enemyss,encans,cans;
    Enemys enemys;
    Encan encan;
    Mychr mychr;
    Can can;
    public HugeBeam(boolean st){
        storing = st;
        setImage("Cannon/beam2.png");
        getImage().setTransparency(0);
    }

    public void act(){
        if(pause&&!storing){
            if(getY() < 315){
                x+=30;
                move(15);
                setImage("Cannon/beam2.png");
                getImage().scale(x,y);
            }else if(mx==x){
                store();
            }else if(count<=40){
                count++;
            }else{
                x-=30;
                move(15);
                setImage("Cannon/beam2.png");
                getImage().scale(x,y);
            }

            myx=getX();
            myy=getY();
            mychr = ((A)getWorld()).getMychr();
            enemyss = getWorld().getObjects(Enemys.class);
            encans = getWorld().getObjects(Encan.class);
            cans = getWorld().getObjects(Can.class);
            for(int i = 0;i < enemyss.size();i++){
                enemys = (Enemys)enemyss.get(i);
                if(!enemys.getStoring()){
                    enx = enemys.getImage().getWidth()/2;
                    eny = enemys.getImage().getHeight()/2;
                    if(myy+x/2>enemys.getY()-eny&&myx-y/2<enemys.getX()+enx&&myx+y/2>enemys.getX()-enx)
                        enemys.Damage(1000);
                }
            }
            for(int i = 0;i < encans.size();i++){
                encan = (Encan)encans.get(i);
                if(!encan.getStoring()){
                    enx = encan.getImage().getWidth()/2;
                    eny = encan.getImage().getHeight()/2;
                    if(myy+x/2>encan.getY()-eny&&myx-y/2<encan.getX()+enx&&myx+y/2>encan.getX()-enx)
                        ((Encan)encans.get(i)).store();
                }
            }
            for(int i = 0;i < cans.size();i++){
                can = (Can)cans.get(i);
                if(!can.getStoring()){
                    enx = can.getImage().getWidth()/2;
                    eny = can.getImage().getHeight()/2;
                    if(myy+x/2>can.getY()-enx && myx-y/2<can.getX()+eny && myx+y/2>can.getX()-eny)
                        ((Can)cans.get(i)).store();
                }
            }
            if(mychr != null){
                if(myy+x/2>mychr.getY()-17&&myx-y/2<mychr.getX()+23&&getX()+y/2>mychr.getX()-23)
                    mychr.Damage(0.2,false);
            }
        }
    }  

    public void inmap(int inx, int iny, int six, int siy){
        getImage().setTransparency(255);
        storing = false;
        setImage("Cannon/beam2.png");
        getImage().scale(six,siy);
        x=six;
        mx=six;
        y=siy;
        count=0;
        setLocation(inx, iny);
        mychr = ((A)getWorld()).getMychr();
    }

    public void store(){
        getImage().setTransparency(0);
        enemys = null;
        encan = null;
        mychr = null;
        can = null;
        setLocation(330,629);
        storing = true;
    }

    public void Pausing(boolean pau){
        pause = pau;
    }

    public boolean getStoring(){
        return storing;
    }
}
