import greenfoot.*;


public class Enemys extends Transparencys{
    
    double HP;
    int sizx,sizy;
    public void Damage(double dam){
        HP -= dam;
    }
    
    public int getSizeX(){
        return sizx;
    }
    
    public int getSizeY(){
        return sizy;
    }
}
