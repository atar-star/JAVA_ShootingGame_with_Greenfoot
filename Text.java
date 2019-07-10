import greenfoot.*;  
import java.awt.Color;  

public class Text extends Counters{  
    String msgTxt;  
    World world;  
    int trans=0;
    int count=0;
    int i = 5;
    boolean gmov;
    public Text(String txt, World w, boolean gameover){  
        msgTxt = txt;  
        world = w;  
        gmov = gameover;
        updateImage();  
    }  

    private void updateImage(){  
        GreenfootImage image = new GreenfootImage(world.getWidth(), world.getHeight());
        if(gmov){
            image.setColor(Color.black);
            image.setTransparency(200);
            image.fill();
        }
        GreenfootImage txtImg = new GreenfootImage(msgTxt, 46, Color.white, new Color(0, 0, 0, 0)); 
        image.drawImage(txtImg, (image.getWidth() - txtImg.getWidth())/2, (image.getHeight()-txtImg.getHeight())/2);  
        setImage(image);
    }
}  