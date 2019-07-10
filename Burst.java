import greenfoot.*;

public class Burst extends Transparencys{
    boolean pause=true,storing=true;
    int posx,posy,siz,ansped=1,anime=0,anicount=2;
    public Burst(boolean st){
        storing = st;
        getImage().setTransparency(0);
    }

    public void act(){
        if(pause&&!storing){
            //setLocation(bos.getX()+posx,bos.getY()+posy);
            setLocation(getX(),getY());
            if((anime%ansped) == 0){
                setImage("Burst1/burst" + anicount +".png");
                getImage().scale(siz,siz);
                if(anicount == 10){
                    store();
                }else{
                    anicount ++;
                }   
            }
            anime++;
        }
    }    
    
    public void inmap(int inx, int iny, int pox, int poy, int size,int anispeed){
        posx = pox;
        posy = poy;
        siz = size;
        ansped = anispeed;
        setLocation(inx,iny);
        getImage().scale(siz,siz);
        setImage("Burst1/burst1.png");
        storing=false;
    }
    
    public void store(){
        getImage().setTransparency(0);
        setLocation(330,629);
        anime=0;
        anicount=2;
        //getImage().scale(40,40);
        storing = true;
    }
    
    public void Pausing(boolean pau){
        pause = pau;
    }
    
    public boolean getStoring(){
        return storing;
    }
}
