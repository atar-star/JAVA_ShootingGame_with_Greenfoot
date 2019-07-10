import greenfoot.*;

public class Transparencys extends Pause{
    
    public void Transparence(){
        GreenfootImage t = getImage();
        t.setTransparency(0);
        setImage(t);
    }  
    public void AnTransparence(){
        GreenfootImage t = getImage();
        t.setTransparency(255);
        setImage(t);
    } 
    public void OrderTransparence(int order){
        GreenfootImage t = getImage();
        t.setTransparency(order);
        setImage(t);
    }
}
