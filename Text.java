import greenfoot.*;  
import java.awt.Color;  

public class Text extends Counters{  
    String msgTxt; 
    boolean gmov;
    public Text(String txt,boolean gameover){  
        msgTxt = txt;  
        gmov = gameover;
        updateImage();  
    }  

    private void updateImage(){  
        GreenfootImage image = new GreenfootImage(660,630);
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