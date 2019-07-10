import greenfoot.*;
import java.util.List;
public class Stage3 extends Maps{
    private static final GreenfootImage bgImage = new GreenfootImage("World/world3.bmp");
    GreenfootImage background=new GreenfootImage(540, 570),scrollingImage,image=new GreenfootImage(540, 570),bg;
    int scrollSpeed=2,scrollPosition=570,scfl=0,scposi=bgImage.getHeight()/3*2,scretupos=570,encount=0,count=0,endes=0,bossflag=0,enfig=0,
    beamcount=70,beamcount2=0,beamX,beamX2,beamx,beamx2,rambeam=Greenfoot.getRandomNumber(200)+120,myx,myy;
    private double trans=0,mytrans=255,imx=46,imy=34,myang,vx,vy;
    public boolean pause = true,beam=true,mpmvanime=false;
    Text txt;
    List cans,ene1s,ene2s,hugebeams;
    Mychr myc;
    Ene1 inene1;
    Ene2 inene2;
    HugeBeam inhugebeam;
    public Stage3(){
        InitImage(scrollPosition);
        getImage().setTransparency(0);
    }

    public void act(){
        if(pause){
            painting();
            if(bossflag==0){
                (((A)getWorld()).getMychr()).mapmoving(true);
                trans+=10;
                if(trans>255){
                    trans=255;
                }
                (((A)getWorld()).getMychr()).OrderTransparence((int)Math.ceil(trans));
                cans = getWorld().getObjects(Can.class);
                for(int i = 0;i < cans.size();i++){
                    if(!((Can)cans.get(i)).getStoring())((Can)cans.get(i)).OrderTransparence((int)Math.ceil(trans));
                }
                getImage().setTransparency((int)Math.ceil(trans));
                if(trans==255){
                    bossflag++;
                }
            }else if(bossflag==1){
                if(encount>=90){
                    bossflag++;
                    encount=0;
                }else{encount++;}
            }else if(bossflag==2){
                HugeBeams();
                if(endes<60){
                    if (encount > 40 && enfig < 8){
                        if(Greenfoot.getRandomNumber(11) >= 3){
                            for(int i=0; i<Greenfoot.getRandomNumber(3) + 1; i++){
                                inEnemy1();
                                enfig ++;
                                if(enfig >= 8){
                                    break;
                                }
                            }
                        }else{
                            for(int i=0; i<Greenfoot.getRandomNumber(2) + 1; i++){
                                inEnemy2();
                                enfig ++;
                                if(enfig >= 8){
                                    break;
                                }
                            }
                        }
                        encount = 0;
                    }else{
                        encount ++;
                    }
                }else{
                    if(beamcount2 == 0 && beamcount == 0){
                        bossflag++;
                        count = 0;
                    }
                }
            }else if(bossflag==3){
                if(enfig != 0){
                }else if(count > 80){
                    getWorld().addObject(new Boss3(this), getWorld().getWidth()/2, 30);
                    bossflag++;
                    count = 0;
                }else{
                    count ++;
                }
            }else if(bossflag==4){
            }else if(bossflag==5){
                (((A)getWorld()).getMychr()).Pausing(false);
                if(encount>=120){
                    myx = ((A)getWorld()).getMychr().getX();
                    myy = ((A)getWorld()).getMychr().getY();
                    if(myx<=335 && myx>=325 && myy<=515 && myy>=495){
                        (((A)getWorld()).getMychr()).setLocation(330,500);
                        scposi = bgImage.getHeight();
                        scretupos = 1710;
                        mpmvanime = true;
                        encount=0;
                        count=0;
                    }else{
                        myang = Math.toDegrees(Math.atan2(myy-500,myx-330))+180;
                        vx = Math.cos(Math.toRadians(myang)) * 4;
                        vy = Math.sin(Math.toRadians(myang)) * 4;
                        (((A)getWorld()).getMychr()).setLocation(myx+(int)vx,myy+(int)vy);
                    }
                }else{encount++;}
            }else if(bossflag==6){
                if(encount>=140){
                    myy = (((A)getWorld()).getMychr()).getY();
                    if(count==60){
                        (((A)getWorld()).getMychr()).setLocation(330,320);
                        bossflag++;
                        encount=0;
                        count=0;
                    }else{
                        (((A)getWorld()).getMychr()).setLocation(330,myy-3);
                        imx -= 0.43;
                        imy -= 0.23;
                        (((A)getWorld()).getMychr()).Imagescale((int)Math.ceil(imx),(int)Math.ceil(imy));
                        mytrans -= 4.1;
                        if(mytrans < 0)mytrans=0;
                        (((A)getWorld()).getMychr()).OrderTransparence((int)Math.ceil(mytrans));
                        count++;
                    }
                }else{encount++;}
            }else if(bossflag==7){
                Clear();
            }else if(bossflag==8){
                if(encount>=200){
                    bossflag++;
                }else{encount++;}
            }else if(bossflag==9){
                trans-=6;
                if(trans<0)trans=0;
                txt.OrderTransparence((int)Math.ceil(trans));
                getImage().setTransparency((int)Math.ceil(trans));
                if(trans==0)bossflag++;
            }else if(bossflag==10){
                getWorld().removeObject(txt);
                Greenfoot.delay(60);
                (((A)getWorld()).getMychr()).setLocation(getWorld().getWidth()/2,getWorld().getHeight()/2+130);
                (((A)getWorld()).getMychr()).Image();
                ((A)getWorld()).allflag();
                getWorld().removeObject(this);
            }
        }
    }    

    public void HugeBeams(){
        beamcount ++;
        if(beamcount == rambeam){
            beamX = Greenfoot.getRandomNumber(530)+ 70;
            getWorld().addObject(new Beamsign(), beamX, 45);
            beamx = Greenfoot.getRandomNumber(30) + 60;
            while(true){
                beamX2 = Greenfoot.getRandomNumber(530)+ 70;
                if(beamX2 > beamX + beamx||beamX2 < beamX - beamx){
                    break;
                }
            }
            beamx2 = Greenfoot.getRandomNumber(30) + 60;
            getWorld().addObject(new Beamsign(), beamX2, 45);
        }else if(beamcount >= rambeam + 120 && beam){
            getWorld().removeObjects(getWorld().getObjects(Beamsign.class));
            beam = false;
        }else if(!beam&&beamcount2==0){
            inHugeBeam(beamX,0,20,beamx,90);
            inHugeBeam(beamX2,0,20,beamx2,90);
            beamcount2 = 120;
        }else if(beamcount >= rambeam + 120 && !beam){
            beamcount2--;
            if(beamcount2 == 0){
                rambeam = Greenfoot.getRandomNumber(200) + 120;
                beamcount = 0;
                beamcount2 = 0;
                beam = true;
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
        if(scrollPosition >= scposi){
            scrollPosition = scretupos; 
            if(mpmvanime){
                bossflag++;
                mpmvanime=false;
            }
        }
        scrollPosition += scrollSpeed;
        bg = getImage();
        bg.drawImage(scrollingImage, 0, scrollPosition - scrollingImage.getHeight());
        bg.drawImage(scrollingImage, 0, scrollPosition);
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
        Greenfoot.setSpeed(50);
        txt = new Text("STAGE3 CLEAR!",false);
        getWorld().addObject(txt, getWorld().getWidth()/2, getWorld().getHeight()/2);
        bossflag++;
    }

    public void inEnemy1(){
        ene1s = getWorld().getObjects(Ene1.class);
        for(int i=0;i<ene1s.size();i++){
            inene1 = (Ene1)(ene1s.get(i));
            if(inene1.getStoring()){
                inene1.inmap(0.5,this,Greenfoot.getRandomNumber(460)+85,3,-1,Greenfoot.getRandomNumber(4)+1,3,360,4);
                break;
            }
        }
    }

    public void inEnemy2(){
        ene2s = getWorld().getObjects(Ene2.class);
        for(int i=0;i<ene2s.size();i++){
            inene2 = (Ene2)ene2s.get(i);
            if(inene2.getStoring()){
                inene2.inmap(3.6,this,Greenfoot.getRandomNumber(5) * 15 + 60,Greenfoot.getRandomNumber(460) + 85, 5);
                break;
            }
        }
    }

    public void inHugeBeam(int inx, int iny, int sizex, int sizey, int deg){
        hugebeams = getWorld().getObjects(HugeBeam.class);
        for(int i=0;i<hugebeams.size();i++){
            inhugebeam = (HugeBeam)(hugebeams.get(i));
            if(inhugebeam.getStoring()){
                inhugebeam.inmap(inx,iny,sizex,sizey);
                inhugebeam.setRotation(deg);
                break;
            }
        }
    }

    public void Pausing(boolean pau){
        pause = pau;
    }
}
