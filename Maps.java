import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Maps extends Pause{
    
    int bossflag;
    int encount;
    int enfig;
    int endes;
    public void bossDestroy(){   
        bossflag ++;
        encount = 0;
        enfig = 0;
    }
    
    public void enDestroy(boolean esflag){
        if(esflag){
            endes ++;
        }
        enfig --;
    }
}
