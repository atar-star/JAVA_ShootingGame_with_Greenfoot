import greenfoot.*;

public class Transparencys extends Pause{
    
    public void Transparence(){
        getImage().setTransparency(0);
    }  
    
    public void AnTransparence(){
        getImage().setTransparency(255);
    } 
    
    public void OrderTransparence(int order){
        getImage().setTransparency(order);
    }
}
