
import greenfoot.*;
import java.util.List;
public class Mychr extends Transparencys{
    private double HP = 15, clone = 3;
    GreenfootImage Ai,i,i2;
    int w=46,h=34,Aw,Ah,x,y,ax,ay,bx,by,dam,shotf,lopcount=0,time=0,sx=0,sy=0,si=0,sh3=10,sh5=3,descount=0,trans=255,death = 0;
    boolean b,fl=true,pause=true,mapmove=true,press=false, deathcoll = false;
    map coll;
    List cans,bursts;
    Can incan,beam;
    Actor enes;
    Counter counter;
    Burst inburst;
    public Mychr(int shty){
        getImage().scale(46,34);
        i=getImage();  
        w=i.getWidth();
        h=i.getHeight();
        shotf=shty;
        i2 = new GreenfootImage(w,h);
    }

    public void act(){
        if(pause){
            if(HP>0&&death==0){
                Move();
                if(mapmove)shot();
                HitEnemys();
                time ++;
            }else{
                if(death==0){
                    clone--;
                    descount = 0;
                    death = 1;
                }else if(death==1){
                    if((descount%6)==0){
                        Burst(Greenfoot.getRandomNumber(30)-15,Greenfoot.getRandomNumber(30)-15,Greenfoot.getRandomNumber(20)+20,2);
                    }

                    if(trans>10){
                        trans-=10;
                    }else{
                        trans = 0;
                        descount = 0;
                        death = 2;
                        HP = 15;
                        Damage(-20,false);
                        deathcoll = true;
                    }
                    super.OrderTransparence(trans);
                }else if(clone == 0){
                    ((A)getWorld()).Gameover();
                }else if(deathcoll){
                    if(descount<30){
                    }else if(descount == 30){
                        super.OrderTransparence(90);
                    }else{
                        coll = (map)getOneIntersectingObject(map.class);
                        if(coll!=null){
                            if(touch(coll,true,true)){
                                DeathMove();
                            }else{
                                deathcoll = false;
                                descount = 0;
                            }
                        }else{
                            deathcoll = false;
                            descount = 0;
                        }
                    }
                }else{
                    Move();
                    if(descount>40){
                        trans = 255;
                        super.OrderTransparence(255);
                        death = 0;
                    }
                }
                descount++;
            }
        }
    }

    private void Move(){

        if(Greenfoot.isKeyDown("i")){
            sy-=5;
        }

        if(Greenfoot.isKeyDown("k")){
            sy+=5;
        }

        if(Greenfoot.isKeyDown("j")){
            sx-=5;
        }

        if(Greenfoot.isKeyDown("l")){
            sx+=5;
        }

        coll = (map)getOneIntersectingObject(map.class);
        if(sx!=0||sy!=0){
            bx+=sx;
            by+=sy;
            setLocation(bx, by);
            if(coll!=null){
                if(touch(coll,true,true)){
                    bx-=sx;
                    by-=sy;
                    setLocation(bx, by);
                }
            }
            //setLocation(bx, by);
            sx = 0;
            sy = 0;
        }
        if(coll!=null){ 
            if(touch(coll,false,true)){
                by+=2;
                setLocation(bx, by);
                if(by>585){
                    Damage(200,false);
                }
            }
        }

        bx=getX();
        by=getY();

        if(by > 585){
            by=585;
            setLocation(bx, 585);
        }
        if(by < 45){
            by=45;
            setLocation(bx, 45);
        }
        if(bx < 82){
            bx=82;
            setLocation(82,by);
        }
        if(bx > 578){
            bx=578;
            setLocation(578,by);
        }
    }

    private void DeathMove(){

        if(Greenfoot.isKeyDown("i")){
            sy-=5;
        }
        if(Greenfoot.isKeyDown("k")){
            sy+=5;
        }
        if(Greenfoot.isKeyDown("j")){
            sx-=5;
        }
        if(Greenfoot.isKeyDown("l")){
            sx+=5;
        }

        if(sx!=0||sy!=0){
            bx+=sx;
            by+=sy;
            setLocation(bx, by);
            sx = 0;
            sy = 0;
        }

        bx=getX();
        by=getY();
        if(by > 585){
            by=585;
            setLocation(bx, 585);
        }
        if(by < 45){
            by=45;
            setLocation(bx, 45);
        }
        if(bx < 82){
            bx=82;
            setLocation(82,by);
        }
        if(bx > 578){
            bx=578;
            setLocation(578,by);
        }
    }

    public void shot(){
        switch(shotf){
            case 1:
            if((time%13)==0 && Greenfoot.isKeyDown("z")){
                inCan(bx+10,by-10,-90,1,15,7,1);
                inCan(bx-10,by-10,-90,1,15,7,1);
                //shmusic();
            }
            break;

            case 2:
            if(Greenfoot.isKeyDown("z")){
                if((time%13)==0){
                    inCan(bx+10,by-10,-90,1,15,7,1);
                    inCan(bx-10,by-10,-90,1,15,7,1);
                    //shmusic();
                }
                if((time%26)==0){
                    inCan(bx-25,by-10,-130,0.5,15,7,3);
                    inCan(bx+25,by-10,-50,0.5,15,7,3);
                }
            }
            break;

            case 3:
            if(Greenfoot.isKeyDown("z")){
                if((time%13)==0){
                    inCan(bx+10,by-10,-90,1,15,7,1);
                    inCan(bx-10,by-10,-90,1,15,7,1);
                    //shmusic();
                }
                if((time%26)==0){
                    inCan(bx-25,by-10,-130,0.5,15,7,3);
                    inCan(bx+25,by-10,-50,0.5,15,7,3);
                }
                if((time%45)==0){
                    inCan(bx-40,by-40,-110,1,15,6,8);
                    inCan(bx-40,by-40,-70,1,15,6,8);
                }
            }
            break;

            case 4:
            if(Greenfoot.isKeyDown("z")){
                if(!press){
                    press = true;
                    beam = inCan(bx,by-10,-90,0.1,20,8,7);
                }
            }else{
                if(press){
                    press = false;
                    beam.store();
                }
            }
            break;

            case 6:
            if ((time%15) == 0 && Greenfoot.isKeyDown("z")) {
                si += sh3;
                inCan(bx+15,by-20,-90,1,30,7,2);
                inCan(bx-15,by-20,-90,1,30,7,2);
                inCan(bx+30,by-15,-80+si,1,30,7,2);
                inCan(bx-30,by-15,-100-si,1,30,7,2);
                if(si >= 60 || si <= 0){
                    sh3 *= -1;
                }
                //shmusic();
            }
            break;

            case 7:
            if((time%15) == 0 && Greenfoot.isKeyDown("z")){
                inCan(bx-40,by,-140,0.6,15,7,3);
                inCan(bx+40,by,-40,0.6,15,7,3);
                inCan(bx,by,-90,0.6,15,7,3);
                //shmusic();
            }
            break;

            case 8:
            if((time%15) == 0 && Greenfoot.isKeyDown("z")){
                inCan(bx-40,by,-110,1,15,6,4);
                inCan(bx+40,by,-70,1,15,6,4);
                //shmusic();
            }
            break;

            case 9:
            if ((time%6) == 0 && Greenfoot.isKeyDown("z")) {
                si += sh5;
                inCan(bx,by,-90+si,0.4,17,7,5);
                inCan(bx,by,-90-si,0.4,17,7,5);
                if(si >= 70 || si <= -70){
                    sh5 *= -1;
                }
                //shmusic();
            }
            break;

            case 10:
            if(Greenfoot.isKeyDown("z")){
                if(!press){
                    press = true;
                    beam = inCan(bx,by-10,-90,0.1,20,8,6);
                }
            }else{
                if(press){
                    press = false;
                    beam.store();
                }
            }
            break;
        }
    }

    public void Damage(double dam,boolean damflag){
        HP -= dam;
        if(HP > 15){
            HP = 15;
        }
        (((A)getWorld()).getHPCounter()).MyHP(HP);
        if(damflag){
            counter = ((A)getWorld()).getCounter();
            (((A)getWorld()).getCounter()).bumpCount(5);
        }
    }

    public boolean touch(Actor A,boolean st,boolean map){    
        Ai=A.getImage(); 
        if(st){
            bx=getX();
            by=getY();
        }  
        i2.clear();
        if(!map){
            x = A.getX();
            y = A.getY();
            Aw = Ai.getWidth();
            Ah = Ai.getHeight();
            i2.drawImage(Ai,x-bx-(Aw/2-w/2),y-by-(Ah/2-h/2));
        }else{
            Aw=540;
            Ah=570;
            i2.drawImage(Ai,330-bx-(Aw/2-w/2),315-by-(Ah/2-h/2));
        }
        for(int yi = 0; yi<h; yi++)  
            for(int xi = 0; xi<w; xi++)  
                if(i2.getColorAt(xi,yi).getAlpha()==255 && i.getColorAt(xi,yi).getAlpha()>0)  
                    return b=true;
        return b=false;  
    }  

    public Can inCan(int canx, int cany, int deg, double damage, int six, int siy, int type){
        cans = getWorld().getObjects(Can.class);
        for(int i=0;i<cans.size();i++){
            incan = (Can)(cans.get(i));
            if(incan.getStoring()){
                incan.inmap(canx,cany,damage,six,siy,type);
                incan.setRotation(deg);
                return incan;
            }
        }
        return (Can)(cans.get(0));
    }

    public void HitEnemys(){
        enes = getOneIntersectingObject(Enemys.class);
        if(enes != null){
            if(touch(enes,true,false)){
                Damage(((Enemys)enes).getDamage(),true);
                ((Enemys)enes).Damage(3);
            }
        }
    }

    public void Burst(int burstx, int bursty, int brsize, int anispeed){
        bursts = getWorld().getObjects(Burst.class);
        for(int i=0;i<bursts.size();i++){
            inburst = (Burst)(bursts.get(i));
            if(inburst.getStoring()){
                inburst.inmap(getX()+burstx,getY()+bursty,burstx,bursty,brsize,anispeed);
                break;
            }
        }
    }

    public void Pausing(boolean pau){
        pause = pau;
    }

    public void setshtype(int shof){
        shotf = shof;
    }

    public void mapmoving(boolean mapmf){
        mapmove = mapmf;
    }

    public void Imagescale(int x, int y){
        setImage("Mychr.png");
        getImage().scale(x,y);
    }

    public void Image(){
        setImage("Mychr.png");
        getImage().scale(46,34);
        Transparence();
    }
}
