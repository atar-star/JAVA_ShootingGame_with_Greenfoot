import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemys here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemys extends Transparencys
{
    public boolean storing;
    public void store(){
        setLocation(1,1);
        //getImage().scale(1,1);
        storing = true;
    }
    
    double dam;
    public double getDamage(){
        return dam;
    }
    
    public void Damage(double dam){
    
    }
    /**
     * Act - do whatever the Enemys wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    public boolean getStoring(){
        return storing;
    }
}
