import greenfoot.*;

public class map extends Stage2{
    boolean pause = true;
    private static final GreenfootImage bgImage = new GreenfootImage("World/map3.png");
    private double scrollSpeed = 1.5,scrollPosition = 570,trans = 0;;
    private GreenfootImage scrollingImage,image = new GreenfootImage(540, 570),background = new GreenfootImage(540, 570);
    public map(){
        InitImage(570);
        getImage().setTransparency(0);
    }

    public void act(){
        if(pause){
            painting();
            if(trans!=255){
                trans+=10;
                if(trans>255){
                    trans=255;
                }
                getImage().setTransparency((int)Math.ceil(trans));
            }
        }    
    }

    public void InitImage(int scroll){
        scrollingImage = getScrollingImage(bgImage.getWidth(), bgImage.getHeight());
        background.drawImage(scrollingImage, 0, scroll);  
        setImage(background);
    }

    private GreenfootImage getScrollingImage(int width, int height){
        image.scale(width,height);
        for(int x = 0; x < width; x += bgImage.getWidth()) {
            for(int y = 0; y < height; y += bgImage.getHeight()){
                image.drawImage(bgImage, x, y);
            }
        }
        return image;
    }

    public void painting(){
        if(scrollPosition >= bgImage.getHeight()){
            getWorld().removeObject(this);
        }
        scrollPosition += scrollSpeed;
        background = getImage();
        background.clear();
        background.drawImage(scrollingImage, 0, (int)scrollPosition);
        background.drawImage(scrollingImage, 0, (int)scrollPosition - scrollingImage.getHeight());
    }

    public void Pausing(boolean pau){
        pause = pau;
    }
}
