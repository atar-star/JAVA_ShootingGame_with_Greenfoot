import greenfoot.*;
import java.awt.Color;
import java.util.List;
public class A extends World{
    GreenfootImage background;
    HPCounter hpcount = new HPCounter();
    Counter count = new Counter(0,20);
    Maps map,maps;
    Mychr myc;
    GreenfootSound s = new GreenfootSound("no name.wav");
    boolean pause = true,paupress = false,nowpau = false,FPSfl = true;
    List pauss,cans;
    Text pautxt;
    int allflag = 0;

    private long basetime;
    private int count2;
    private float framerate;
    long now;
    int speed=50;

    public A(){    
        super(660, 630, 1); 
        setBackground("StageTitle/Title.jpg");
        Greenfoot.setSpeed(speed);
        //Greenfoot.start();
        basetime = System.currentTimeMillis();
    }

    public void act(){
        if(allflag==0){
            if(Greenfoot.isKeyDown("enter")){
                background = new GreenfootImage(getWidth(), getHeight());
                background.setColor(Color.black);
                background.fill();
                setBackground(background);
                Greenfoot.delay(80);
                InitGame();
                allflag=1;
            }
        }else if(allflag==2){

            for(int i=0;i<=6;i++){
                addObject(new Ene3(true),1,629);
            }

            for(int i=0;i<=15;i++){
                addObject(new Beam1(true),330,629);
            }
            cans = getObjects(Can.class);
            for(int i=0;i<cans.size();i++){
                ((Can)cans.get(i)).store();
            }
            map = new map2();
            addObject(map, getWidth()/2,getHeight()/2);
            maps = new map();
            addObject(maps, getWidth()/2,getHeight()/2);
            allflag=3;
            FPSfl=false;
            speed=50;
            Greenfoot.setSpeed(50);
        }else if(allflag==4){
            //maps.getImage().clear();
            //removeObject(maps);
            //removeObjects(getObjects(Ene3.class));
            for(int i=0;i<=2;i++){
                addObject(new HugeBeam(true),330,629);
            }
            map = new Stage3();
            addObject(map,getWidth()/2,getHeight()/2);
            allflag=0;
            FPSfl=true;
        }else{
        }

        if(allflag!=0&&Greenfoot.isKeyDown("x")){
            if(!paupress){
                paupress = true;
                pause = !pause;
                pauss = getObjects(Pause.class);
                for(int i = 0;i < pauss.size();i++){
                    ((Pause)pauss.get(i)).Pausing(pause);
                }
                if(!nowpau){
                    pautxt = new Text("PAUSE",false);
                    addObject(pautxt,getWidth()/2,getHeight()/2);
                }else{
                    removeObject(pautxt);
                }
                nowpau = !nowpau;
            }
        }else{
            paupress = false;
        }

        //if(FPSfl)count();
    }

    private void InitGame(){
        setPaintOrder(Counters.class,Margin.class,Transparencys.class,Mychr.class,Can.class,Enemys.class,HugeBeam.class,
            Encan.class,Beams.class,Cure.class,map.class,map2.class,Maps.class);
        myc = new Mychr(1);//Greenfoot.getRandomNumber(10)+1);
        addObject(myc,getWidth()/2,getHeight()/2+130);
        map = new Stage1();
        //map = new map2();
        addObject(map, getWidth()/2,getHeight()/2);
        //addObject(new map(), getWidth()/2,getHeight()/2);
        addObject(new Margin(getWidth(),30), getWidth()/2, 615);
        addObject(new Margin(60, getHeight()), 630, getHeight()/2);
        addObject(new Margin(60, getHeight()), 30, getHeight()/2);
        addObject(new Margin(getWidth(),30), getWidth()/2, 15);
        addObject(count, 80, 615);
        addObject(hpcount, 450, 615);
        for(int i = 0; i < 15; i++){
            addObject(new HPs(),470 + i * 8, 615);
        }

        for(int i=0;i<=7;i++){
            addObject(new Ene1(true),1,629);
        }

        for(int i=0;i<=6;i++){
            addObject(new Ene2(true),1,629);
        }

        for(int i=0;i<=65;i++){
            addObject(new Can(true),1,629);
        }

        for(int i=0;i<=80;i++){
            addObject(new Encan1(true),1,629);
        }

        for(int i=0;i<=6;i++){
            addObject(new Cure(true),1,629);
        }

        for(int i=0;i<=6;i++){
            addObject(new Burst(true),1,629);
        }
        s.setVolume(30);
        s.playLoop();
    }

    public void allflag(){
        allflag++;
    }

    public void Gameover(){
        Text txt = new Text("GAME OVER",true);
        addObject(txt, getWidth()/2, getHeight()/2);
        removeObjects(getObjects(Encan.class));
        Greenfoot.delay(160);
        removeObjects(getObjects(Enemys.class));
        Greenfoot.delay(2);
        removeObjects(getObjects(Mychr.class));
        Greenfoot.delay(2);
        removeObjects(getObjects(Actor.class));
        Greenfoot.delay(60);
        allflag=0;
        setBackground("StageTitle/Title.jpg");
    }

    public map getnowmap(){
        return (map)map;
    }

    public HPCounter getHPCounter(){
        return hpcount;
    }

    public Counter getCounter(){
        return count;
    }

    public Mychr getMychr(){
        return myc;
    }

    public Maps getNowMap(){
        return map;
    }

    public float getFrameRate() {
        return framerate;
    }

    public void count() {
        count2++;
        now = System.currentTimeMillis();
        if(now - basetime >= 1000){
            framerate = (float)(count2 * 1000) / (float)(now - basetime);
            basetime = now;
            count2 = 0;
            if(framerate>55){
                speed--;
                if(speed<45)speed=45;
                Greenfoot.setSpeed(speed);
            }else if(framerate<60){
                speed++;
                if(speed>60)speed=60;
                Greenfoot.setSpeed(speed);
            }
        }
    }
}

