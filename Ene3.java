import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
public class Ene3 extends Enemy{   
    private boolean deadflag=true,storing;
    private int pat,shpat,count = 0,shcount = 30,shcount2 = 0,rad = 0,x,sizx=51,sizy=41,Distx,Disty,myx,myy;
    double dam=3,moving,HP,ag=90,i=0,angle,y,myang;
    double[] shagx=new double[2],shagy=new double[2];
    Maps map;
    List b,encans,cures;
    Mychr g;
    Encan1 inencan;
    Cure incure;
    public Ene3(boolean st){
        storing = st;
        setImage("Enemy/Ene3/ene32.png");
        getImage().setTransparency(0);
    }

    public void act(){
        if(!storing&&pause){
            move();
            shot();
            if(HP <= 0){
                if(Greenfoot.getRandomNumber(11) <= 3){
                    inCure(getX(),getY());
                }
                ((A)getWorld()).getCounter().bumpCount(50);
                map.enDestroy(true);
                store();
            }else{
                if(getX() > 596 || getX() < 64 || getY() > 600 || getY() < 2){
                    map.enDestroy(false);
                    store();
                }
            }
        }
    }

    public void move(){
        y += 1.5;
        setLocation(x,(int)y);
        g=((A)getWorld()).getMychr();
        myx=getX();
        myy=getY();
        Distx=myx-g.getX();
        Disty=myy-g.getY();
        angle=Math.toDegrees(Math.atan2(Disty,Distx))+180;
        myang = getRotation();
        if (myang - 180 > angle) {
            angle += 360;
        }else if (myang + 180 < angle) {
            angle -= 360;
        }
        if (myang - angle > 5) {
            myang -= 5;
        } else if (myang - angle < -5) {
            myang += 5;
        } else {
            myang = angle;
        }  
        setRotation((int)myang);
    }

    public void shot(){
        if(shcount > 70){
            for(int i2=0;i2<2;i2++){
                shagx[i2] = myx + Math.cos(Math.toRadians(myang + 90 - 60 * (i2+1)))*20;
                shagy[i2] = myy + Math.sin(Math.toRadians(myang + 90 - 60 * (i2+1)))*20;
                inEncan((int)shagx[i2],(int)shagy[i2],(int)myang, 8);
            }
            shcount -= 20;
            shcount2++;
            if(shcount2==3){
                shcount=0;
                shcount2=0;
            }
        }
        shcount ++;
    }

    public void Damage(double dam){
        HP -= dam;
    }

    public void inmap(double hp, Maps m, int inx, int iny, int ang){
        getImage().setTransparency(255);
        map = m;
        HP = hp;
        count=0;
        shcount = 20;
        shcount2 = 0;
        getImage().scale(sizx,sizy);
        storing = false;
        x = inx;
        y = iny;
        setLocation(inx, iny);
        setRotation(ang);
    }

    public void store(){
        getImage().setTransparency(0);
        setLocation(1,629);
        //getImage().scale(40,40);
        storing = true;
    }

    public boolean getStoring(){
        return storing;
    }

    public void inEncan(int canx, int cany, int deg, int speed){
        encans = getWorld().getObjects(Encan1.class);
        for(int i=0;i<encans.size();i++){
            inencan = (Encan1)(encans.get(i));
            if(inencan.getStoring()){
                inencan.inmap(canx,cany,1,1,speed);
                inencan.setRotation(deg);
                break;
            }
        }
    }
    
    public void inCure(int canx, int cany){
        cures = getWorld().getObjects(Cure.class);
        for(int i=0;i<cures.size();i++){
            incure = (Cure)(cures.get(i));
            if(incure.getStoring()){
                incure.inmap(canx,cany,1);
                break;
            }
        }
    }

    public double getDamage(){
        return dam;
    }
}
