import greenfoot.*;

public class Cure extends Transparencys{
    boolean pause = true;
    private int cure;

    public Cure(int cu){
        cure = cu;
        GreenfootImage image = getImage();
        image.scale(17,17);
        setImage(image);
    }

    public void act(){
        if(pause){
            setLocation(getX(), getY() + 3);
            Actor mychr = getOneIntersectingObject(Mychr.class);
            if(mychr != null || getY() > 605){
                if(mychr != null){
                    ((World1)getWorld()).getMychr().Damage(-cure,false);
                }
                getWorld().removeObject(this);
            }
        }
    }    
    
    public void Pausing(boolean pau){
        pause = pau;
    }
}
