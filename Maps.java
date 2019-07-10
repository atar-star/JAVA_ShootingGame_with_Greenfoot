import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class maps here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Maps extends Pause
{
    GreenfootImage Ai,i,i2;
    int Aw,Ah,Ax,Ay,x,y,w,h;
    boolean b;
    /**
     * Act - do whatever the maps wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }  

    public boolean touch(Actor A,int myx,int myy){  
        i=getImage();  
        Ai=A.getImage();  
        Aw=Ai.getWidth();
        Ah=Ai.getHeight();
        Ax=A.getX();
        Ay=A.getY();
        //i2.drawImage(Ai,Ax-x-(Aw/2-w/2),Ay-y-(Ah/2-h/2));
        for(int yi = 0; yi<Ah; yi++){
            for(int xi = 0; xi<Aw; xi++){
                if(570<=Ay-y-(Ah/2-h/2)+yi){
                    break;
                }
                if(Ai.getColorAt(xi,yi).getAlpha()>0 && i.getColorAt(Ax-x-(Aw/2-w/2)+xi,Ay-y-(Ah/2-h/2)+yi).getAlpha()>0){
                    return b=true;
                }
            }
        }
        return b=false;  
    }

    public void bossDestroy(){  
    }
    
    public void enDestroy(boolean esflag){
    }
}
