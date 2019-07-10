import greenfoot.*;

public class Enemy3 extends Enemy{
    boolean pause = true;
    double angle,HP,myy;
    private int shcount = 20;
    private int movecount = 0;
    boolean flag= false;
    Maps map;
    int sizx = 60;
    int sizy = 60;
    public Enemy3(Maps ma, int ang, double hp){
        setImage("Enemy/Ene3/houdai2.png");
        setRotation(ang);
        map = ma;
        HP = hp;
        myy=3;
        getImage().scale(sizx,sizy);
    }  

    public void act(){
        if(pause){
            if(movecount<30){
                movecount++;
            }else{
                angle=Math.toDegrees(Math.atan2(getY()-(((World1)getWorld()).getMychr()).getY(),getX()-(((World1)getWorld()).getMychr()).getX()))+180;
                if(getRotation() > angle - 10&& getRotation() < angle + 10){
                    flag = true;
                }
                if(!flag){
                    if(angle - getRotation() > 2){
                        angle = getRotation() + 2;
                    }else if(angle - getRotation() <  -2){
                        angle = getRotation() - 2;
                    }
                }
                setRotation((int)angle);

                if(shcount > 80){
                    Beam2 beam = new Beam2(6,14,true,1);
                    getWorld().addObject(beam, getX()+(int)Math.cos(Math.toRadians(angle))*12, getY()+(int)Math.sin(Math.toRadians(angle))*12);
                    beam.setRotation((int)angle);
                    Beam2 beam2 = new Beam2(6,14,true,1);
                    getWorld().addObject(beam2, getX()-(int)Math.cos(Math.toRadians(angle))*12, getY()+(int)Math.sin(Math.toRadians(angle))*12);
                    beam2.setRotation((int)angle);
                    shcount = 0;
                }
                shcount ++;
            }
            myy += 1.5;
            setLocation(getX(),(int)myy);
            if(HP <= 0){
                ((World1)getWorld()).getCounter().bumpCount(50);
                map.enDestroy(true);
                getWorld().removeObject(this);
            }else if(getY() > 600){
                map.enDestroy(false); 
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

    public int getSizeX(){
        return sizx;
    }

    public int getSizeY(){
        return sizy;
    }
}
