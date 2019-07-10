import greenfoot.*;

public class Burst1 extends Transparencys{
    boolean pause = true;
    Bosss bos;
    int posx,posy,siz,ansped;
    int anime = 0;
    int anicount = 2;
    public Burst1(Bosss boss, int pox, int poy, int size,int anispeed){
        bos = boss;
        posx = pox;
        posy = poy;
        siz = size;
        ansped = anispeed;
        getImage().scale(siz,siz);
        setImage("Burst1/burst1.png");
    }

    public void act(){
        if(pause){
            //setLocation(bos.getX()+posx,bos.getY()+posy);
            setLocation(getX(),getY());
            if((anime%ansped) == 0){
                setImage("Burst1/burst" + anicount +".png");
                GreenfootImage image = getImage();
                image.scale(siz,siz);
                setImage(image);
                if(anicount == 10){
                    getWorld().removeObject(this);
                }else{
                    anicount ++;
                }   
            }
            anime++;
        }
    }    
    
    public void Pausing(boolean pau){
        pause = pau;
    }
}
