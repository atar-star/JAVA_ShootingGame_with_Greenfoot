import greenfoot.*;
import java.util.List;
public class Boss2option extends Boss{
    boolean pause = true,shflag=false;
    private int count,type,myx;
    double HP = 30,angle,speed=5.0,myang;
    List encans;
    Boss boss;
    Encan1 inencan;
    public Boss2option(Boss bos, int bossx, int opmx, int typ){
        setImage("Boss/boss2Option.png");
        getImage().scale(35,35);
        if(bossx>0){
            setRotation(135);
        }else{
            setRotation(45);
        }
        myx = bossx;
        boss = bos;
        type = typ;
    }

    public void act(){
        if(pause){
            setLocation(boss.getX() + myx,boss.getY() + 60);
            if(!shflag){
                angle=Math.toDegrees(Math.atan2(getY()-(((A)getWorld()).getMychr()).getY(),getX()-(((A)getWorld()).getMychr()).getX()))+180;
                myang = getRotation();
                if (myang - 180 > angle) {
                    angle += 360;
                }
                if (myang + 180 < angle) {
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
                count++;
                if(count>=85){
                    shflag=true;
                    count=0;
                }
            }else{
                shot();
            }
            if(HP <= 0){
                ((A)getWorld()).getCounter().bumpCount(200);
                getWorld().removeObject(this);
            }
        }
    }

    public void Damage(double dam){
        HP -= dam;
    }

    public void Pausing(boolean pau){
        pause = pau;
    }

    public void shot(){
        if(count<=10){
            inEncan(getX(),getY(),getRotation(),(int)Math.ceil(speed));
            if(type==1)turn(8);
            else turn(-8);
            speed-=0.4;
            count++;
        }else{
            count=0;
            speed=6.0;
            shflag=false;
        }
    }

    public void inEncan(int canx, int cany, int deg, int speed){
        encans = getWorld().getObjects(Encan1.class);
        for(int i=0;i<encans.size();i++){
            inencan = (Encan1)(encans.get(i));
            if(inencan.getStoring()){
                inencan.inmap(canx,cany,1,3,speed);
                inencan.setRotation(deg);
                break;
            }
        }
    }
}
