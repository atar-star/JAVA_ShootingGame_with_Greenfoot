import greenfoot.*;

public class Stage2x1 extends Maps{
    boolean pause = true;
    private static final GreenfootImage bgImage = new GreenfootImage("World/world2x1.jpg");
    GreenfootImage background = new GreenfootImage(540, 570);
    private double scrollSpeed = 1.5;
    private GreenfootImage scrollingImage;
    private double scrollPosition = 570;
    GreenfootImage image = new GreenfootImage(540, 570);
    GreenfootImage bg;
    Text txt;
    public int encount = 0;
    public int encount2 = 60;
    public int count = 0;
    public int endes = 0;
    public int bossflag = -1;
    public int enfig = 0;
    private double trans = 0;
    public Stage2x1(){
        InitImage(570);
        getImage().setTransparency(0);
    }

    public void act(){
        if(pause){
            painting();
            if(bossflag==-1){ 
                (((World1)getWorld()).getMychr()).getImage().scale(54,36);
                (((World1)getWorld()).getMychr()).mapmove(false);
                (((World1)getWorld()).getMychr()).setshab(true);
                trans+=10;
                if(trans>255){
                    trans=255;
                }
                (((World1)getWorld()).getMychr()).OrderTransparence((int)Math.ceil(trans));
                getImage().setTransparency((int)Math.ceil(trans));
                if(trans==255){
                    bossflag++;
                }
            }else if(bossflag == 0){
                if(encount > 50){
                    getWorld().addObject(new Stagetitle("StageTitle/STAGE2.png", 3, this),getWorld().getWidth()/2,getWorld().getHeight()/2);
                    encount = -1000;
                }else{
                    encount++;
                }

            }else if(bossflag == 1){
                if(scrollPosition>=8000){
                    bossflag++;
                }else if(scrollPosition>=2300){
                    encount2=0;
                }else if(scrollPosition>=2000){
                    if(encount2>80){
                        getWorld().addObject(new Enemy3(this,180,12), 520, 3);
                        encount2=0;
                    }
                    encount2++;
                }else if(scrollPosition>=1600){
                    encount2=0;
                }else if(scrollPosition>=1000){
                    if(encount2>120){
                        getWorld().addObject(new Enemy3(this,0,12), 100, 3);
                        encount2=0;
                    }
                    encount2++;
                }
            }else if(bossflag == 2){
                if(count > 20){
                    getWorld().addObject(new Boss2x1(this), getWorld().getWidth()/2, 0);
                    bossflag++;
                    count = 0;
                }else{
                    count ++;
                }
            }else if(bossflag == 3){
            }else if(bossflag == 4){
                (((World1)getWorld()).getMychr()).setshab(false);
                if(encount>=60){
                    bossflag++;
                    encount=0;
                }else{encount++;}
            }else if(bossflag == 5){
                Clear();
            }else if(bossflag == 6){
                trans-=10;
                if(trans>0){
                    trans=0;
                }
                (((World1)getWorld()).getMychr()).OrderTransparence((int)Math.ceil(trans));
                getImage().setTransparency((int)Math.ceil(trans));
                if(trans==0){
                    bossflag++;
                }
            }else if(bossflag==7){
                getWorld().removeObject(txt);
                Greenfoot.delay(30);
                (((World1)getWorld()).getMychr()).setLocation(getWorld().getWidth()/2,500);
                ((World1)getWorld()).allflag();
                getWorld().removeObject(this);
            }
        }
    }    

    public void InitImage(int scroll){
        scrollingImage = getScrollingImage(bgImage.getWidth(), bgImage.getHeight());
        background.drawImage(scrollingImage, 0, scroll);  
        setImage(background);
    }

    private void paint(int position){
        bg = getImage();
        bg.drawImage(scrollingImage, 0, position);
        bg.drawImage(scrollingImage, 0, position - scrollingImage.getHeight());
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
            scrollPosition = bgImage.getHeight();
        }
        scrollPosition += scrollSpeed;
        bg = getImage();
        bg.drawImage(scrollingImage, 0, (int)scrollPosition);
        bg.drawImage(scrollingImage, 0, (int)scrollPosition - scrollingImage.getHeight());
    }

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

    public void Clear(){
        (((World1)getWorld()).getMychr()).mapmove(true);
        getWorld().removeObjects(getWorld().getObjects(Can.class));
        if((((World1)getWorld()).getMychr()).getY()<=20){
            bossflag++;
        }else{
            (((World1)getWorld()).getMychr()).setLocation((((World1)getWorld()).getMychr()).getX(),(((World1)getWorld()).getMychr()).getY()-6);
        }
    }

    public void Pausing(boolean pau){
        pause = pau;
    }
}
