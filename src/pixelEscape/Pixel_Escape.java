package pixelEscape;
  //---------Import---------//
  import javax.swing.*;     //
  import java.util.*;       //
  import java.awt.*;        //
  import java.awt.event.*;  //
  import java.io.*;         //
  //=========Import=========//
  
public class Pixel_Escape extends Pixel_Escape_Dad implements MouseListener {  
  //---------GUI Members---------//
  private JFrame background;     //
  private ImagePanel back;       //
  private MovingImage pixel;     //
  //=========GUI Members=========//
  
  //---------Big Checks---------//
  public static boolean pause;  //
  public static boolean crash;  //
  public static boolean start;  //
  public static boolean played; //
  public static boolean boost;  //
  public static boolean boostComing;
  //=========Big Checks=========//
  
  //------Up By User------//
  public boolean goingUp; //
  private double upCount; //
  //======Up By User======//
  
  //---------How Far Gose---------//
  public static int distance;     //
  public static int maxDistance;  //
  //=========How Far Gose=========//
  
  //------------Big Datas------------//
  public final int XPOSITION;        //
  public final int NUMRECTANGLES;    //
  public final int RECTANGLEHEIGHT;  //
  public final int RECTANGLEWIDTH;   //
  //============Big Datas============//
  
  //-------Pixel Facts-------//
  private int moveIncrement; //
  private int shadowN;       //
  private int boostC;        //
  private int pixelBoost;    //
  private int shadowBoost;   //
  private int scoreBoost;    //
  private int backBoost;     //
  private int boostComingC;  //
  //=======Pixel Facts=======//
  
  //---------------------Arrays---------------------//
  private ArrayList<MovingImage> topRectangles;     //
  private ArrayList<MovingImage> bottomRectangles;  //
  private ArrayList<MovingImage> middleRectangles;  //
  private ArrayList<MovingImage> rectangles;        //
  private ArrayList<MovingImage> shadow;            //
  private int[][] boostPos;                         //
  File currentD;
  //=====================Arrays=====================//
  
  //-----------Pixel_Escape-----------//
  public Pixel_Escape() {             //
    currentD = new File(System.getProperty("user.dir"));
    //System.out.println("MEOW: " + currentD);
    NUMRECTANGLES = 28;               //
    RECTANGLEHEIGHT = 93;             //
    RECTANGLEWIDTH = 29;              //
    XPOSITION = 200;                  //
    boostPos = new int[100][100];
    played = false;                   //
    maxDistance = 0;                  //
    pixelBoost = 11;                  //11
    shadowBoost = 75;
    scoreBoost = 2900;
    boostComingC = 0;
    backBoost = 200;                  //
    load(new File("./src/Assets/HighScore.txt"));  //
    initiate();                       //
  }                                   //
  //===========Pixel_Escape===========//
  
  //-----------Try To Load File-----------//
  public void load(File file) {           //
    try {                                 //
      Scanner reader = new Scanner(file); //
      while(reader.hasNext()) {           //
        int value = reader.nextInt();     //
        if(value > maxDistance) {         //
          maxDistance = value;            //
        }                                 //
      }                                   //
    } catch(IOException e ) {             //
      System.out.println("Error: " + e);  //
    }                                     //
  }                                       //
  //===========Try To Load File===========//
  
  //-----------------Try To Save File-----------------//
  public void save() {                                //
    FileWriter writer;                                //
    try {                                             //
      writer = new FileWriter("./src/Assets/HighScore.txt");       //
      writer.write("" + maxDistance);                 //
      writer.close();                                 //
    } catch(IOException e) {                          //
      System.out.println("Error: " + e.getMessage()); //
    }                                                 //
  }                                                   //
  //=================Try To Save File=================//
 
  //--------------------------------------Initiate--------------------------------------//
  public void initiate() {                                                              //
    //------------------------If Not Played Once------------------------//              //
    if (!played) {                                                      //              //
      //-------------------Background Settings-------------------//     //              //
      background = new JFrame("Pixel_Escape");                   //     //              //
      background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //     //              //Closes the program when the window is closed.
      background.setResizable(false);                            //     //              //Don't allow the user to resize the window.
      background.setSize(new Dimension(818,568));                //     //              //
      background.setVisible(true);                               //     //              //
      //===================Background Settings===================//     //              //
                                                                        //              //
      //----------Add Things To Background----------//                  //              //
      back = new ImagePanel("./src/Assets/back.GIF");            //                  //              //
      background.add(back);                         //                  //              //
      back.addMouseListener(this);                  //                  //              //
      background.addKeyListener(new KeyAdapter() {  //                  //              //
        //------------Key Pressed------------//     //                  //              //
        public void keyPressed(KeyEvent e){  //     //                  //              //
          int key = e.getKeyCode();          //     //                  //              //
          System.out.println(key);           //     //                  //              //
          if (key == 66) {
            key = 0;
            boost = true;
          }
          if (!start) {                      //     //                  //              //
            start = true;                    //     //                  //              //
          }                                  //     //                  //              //
          goingUp = true;                    //     //                  //              //
          upCount = 0;                       //     //                  //              //
        }                                    //     //                  //              //
        //============Key Pressed============//     //                  //              //
                                                    //                  //              //
        //------------Key Released------------//    //                  //              //
        public void keyReleased(KeyEvent e){  //    //                  //              //
          goingUp = false;                    //    //                  //              //
          upCount = 0;                        //    //                  //              //
          if (pause) {                        //    //                  //              //
            pause = false;                    //    //                  //              //
          }                                   //    //                  //              //
        }                                     //    //                  //              //
        //============Key Released============//    //                  //              //
      });                                           //                  //              //
      //==========Add Things To Background==========//                  //              //
    }                                                                   //              //
    //========================If Not Played Once========================//              //
                                                                                        //
    //---Set Variables---//                                                             //
    played = true;       //                                                             //
    goingUp = false;     //                                                             //
    pause = false;       //                                                             //
    crash = false;       //                                                             //
    start = false;       //                                                             //
                         //                                                             //
    distance = 0;        //                                                             //
    upCount = 0;         //                                                             //
    moveIncrement = 15;  //                                                             //
    shadowN = 15;        //                                                             //
    //===Set Variables===//                                                             //
                                                                                        //
    //-----------------Set ArrayLists-----------------//                                //
    shadow = new ArrayList<MovingImage>();            //                                //
    rectangles = new ArrayList<MovingImage>();        //                                //
    topRectangles = new ArrayList<MovingImage>();     //                                //
    middleRectangles = new ArrayList<MovingImage>();  //                                //
    bottomRectangles = new ArrayList<MovingImage>();  //                                //
    //=================Set ArrayLists=================//                                //
                                                                                        //
    pixel = new MovingImage("./src/Assets/pixel.GIF",XPOSITION,270);                                 //
                                                                                        //
    //------------------------Add Top & Bottom Rectangles------------------------//     //
    for(int x = 0; x < NUMRECTANGLES; x++) {                                     //     //
      topRectangles.add(new MovingImage("./src/Assets/middle.JPG",RECTANGLEWIDTH*x,0));         //     //
    }                                                                            //     //
    for(int x = 0; x < NUMRECTANGLES; x++) {                                     //     //
       bottomRectangles.add(new MovingImage("./src/Assets/middle.JPG",RECTANGLEWIDTH*x,410));   //     //
    }                                                                            //     //
    //========================Add Top & Bottom Rectangles========================//     //
                                                                                        //
    //--------------------------Add Middle Rectangles--------------------------//       //
    middleRectangles.add(new MovingImage("./src/Assets/middle.JPG",1392,randomMidHeight()));  //       //
    middleRectangles.add(new MovingImage("./src/Assets/middle.JPG",1972,randomMidHeight()));  //       //
    //==========================Add Middle Rectangles==========================//       //
    
    game();                                                                             //
  }                                                                                     //
  //======================================Initiate======================================//
  
  public void game() {
    //-----------------------Set Timing-----------------------//
    long last = System.currentTimeMillis();                   //
    long lastPixel = System.currentTimeMillis();              //
    long lastShadow = System.currentTimeMillis();             //
    int firstUpdates = 0;                                     //
    double lastDistance = (double)System.currentTimeMillis(); //
    //=======================Set Timing=======================//
    
    //-----------------------------------------------------Gaming-----------------------------------------------------//
    while(true) {                                                                                                     //
      if (boost) {
        updateNullMiddle();
        scoreBoost = 500;
        backBoost = 1;
        pixelBoost = 14;
        shadowBoost = 10;
        if (boostC > 150) {
          
          boost = false;
          boostC = 0;
          scoreBoost = 2900;
          backBoost = 200;
          pixelBoost = 11;
          shadowBoost = 75;
        }
      }
      //-----------------------------------------------Score Speed-----------------------------------------------//   //
      if(!pause && !crash && start && (double)System.currentTimeMillis() - (double)(scoreBoost / 40) > lastDistance) { //   //(2900 / 40)
        lastDistance = System.currentTimeMillis();                                                               //   //
        //System.out.println(lastDistance);                                                                      //   //
        distance++;                                                                                              //   //
      }                                                                                                          //   //
      //===============================================Score Speed===============================================//   //
                                                                                                                      //
      //----------------------------------Pixel Speed----------------------------------//                             //
      if(!pause && !crash && start && System.currentTimeMillis() - pixelBoost > lastPixel) {   //                             //15
        lastPixel = System.currentTimeMillis();                                        //                             //
        updatePixel();                                                                 //                             //
        if (!boost) {
          if (boostComingC == 0) {
            if ((int)(Math.random() * 500) == 8) {
              updateBoostMiddle();
              boostComing = true;
              boostComingC++;
            } else {
              updateMiddle();
            }
          } else if (boostComingC > 120) {
            boostComing = false;
            boostComingC = 0;
            updateMiddle();
          } else {
            updateBoostMiddle();
            //System.out.println(boostComingC);
            boostComingC++;
          }
        } else {
          boostC++;
        }
      }                                                                                //                             //
      //==================================Pixel Speed==================================//                             //
                                                                                                                      //
      //------------------------------Rectangles Speed------------------------------//                                //
      if(!pause && !crash && start && System.currentTimeMillis() - backBoost > last) {    //                                //200
        last = System.currentTimeMillis();                                          //                                //
        updateRectangles();                                                         //                                //
      }                                                                             //                                //
      //==============================Rectangles Speed==============================//                                //
                                                                                                                      //
      //-------------------------------------------Last Shadow-------------------------------------------//           //
      if(!pause && !crash && start && System.currentTimeMillis() - shadowBoost > lastShadow) {                    //           //75
        lastShadow = System.currentTimeMillis();                                                         //           //
        if (firstUpdates < shadowN) {                                                                    //           //
          firstUpdates++;                                                                                //           //
          shadow.add(new MovingImage("./src/Assets/shadow.GIF",187,pixel.getY()));                                    //           //
          for(int x = 0; x < firstUpdates; x++)                                                          //           //
            shadow.set(x,new MovingImage("./src/Assets/shadow.GIF",shadow.get(x).getX() - 30, shadow.get(x).getY())); //           //
        } else {                                                                                         //           //
          for(int x = 0; x < shadowN - 1; x++)                                                           //           //
            shadow.get(x).setY(shadow.get(x+1).getY());                                                  //           //
          shadow.set(shadowN - 1,new MovingImage("./src/Assets/shadow.GIF",167,pixel.getY()));                        //           //
        }                                                                                                //           //
      }                                                                                                  //           //
      //===========================================Last Shadow===========================================//           //
      back.updateImages(topRectangles,middleRectangles,bottomRectangles,pixel,shadow);                                //
    }                                                                                                                 //
    //=====================================================Gaming=====================================================//
  }

  //--------------------------------------------------Update Rectangles--------------------------------------------------//
  public void updateRectangles() {                                                                                       //
    for(int x = 0; x < (NUMRECTANGLES - 1); x++) {                                                                       //Move all but the last rectangle 1 spot to the left.
      topRectangles.set(x,new MovingImage("./src/Assets/middle.JPG",RECTANGLEWIDTH * x,topRectangles.get(x + 1).getY()));               //
      //System.out.println(x+",new MovingImage('middle.JPG'"+","+RECTANGLEWIDTH*x+","+topRectangles.get(x+1).getY()+")");  //
      bottomRectangles.set(x,new MovingImage("./src/Assets/middle.JPG",RECTANGLEWIDTH * x,bottomRectangles.get(x + 1).getY()));         //
    }                                                                                                                    //
    lastRectangle();                                                                                                     //
  }                                                                                                                      //
  //==================================================Upadte Rectangles==================================================//
  
  //------------------------Last Rectangle------------------------//
  public void lastRectangle() {                                   //
    if(distance % 400 == 0) {                                     //
      moveIncrement++;                                            //
    }                                                             //
    //-------------------Move Rectangle-------------------//      //
    if(topRectangles.get(26).getY() < 2) {                //      //If too high, move down.
      moveDown();                                         //      //
    } else if (bottomRectangles.get(26).getY() > 463) {   //      //Else if too low, move up.
      moveUp();                                           //      //
    } else {                                              //      //Else move randomly.
      if((int)(Math.random() * 60) == 49) {               //      //50
        randomDrop();                                     //      //
      } else {                                            //      //
        if((int)(Math.random() * 2) == 1) {               //      //
          moveUp();                                       //      //
        } else {                                          //      //
          moveDown();                                     //      //
        }                                                 //      //
      }                                                   //      //
    }                                                     //      //
    //===================Move Rectangle===================//      //
  }                                                               //
  //========================Last Rectangle========================//
  
  //------------------------------------------Random Drop Obsticle------------------------------------------//
  public void randomDrop() {                                                                                //
    topRectangles.get(26).setY(topRectangles.get(26).getY() + (463 - bottomRectangles.get(26).getY()));     //
    bottomRectangles.get(26).setY(463);                                                                     //
  }                                                                                                         //
  //==========================================Random Drop Obsticle==========================================//
  
  //--------------------------------------------------------------------Move Rectangle Down--------------------------------------------------------------------//
  public void moveDown() {                                                                                                                                     //
    topRectangles.set((NUMRECTANGLES - 1),new MovingImage("./src/Assets/middle.JPG",RECTANGLEWIDTH*(NUMRECTANGLES - 1),topRectangles.get(26).getY() + moveIncrement));        //
    bottomRectangles.set((NUMRECTANGLES - 1),new MovingImage("./src/Assets/middle.JPG",RECTANGLEWIDTH*(NUMRECTANGLES - 1),bottomRectangles.get(26).getY() + moveIncrement));  //
  }                                                                                                                                                            //
  //====================================================================Move Rectangle Down====================================================================//
  
  //---------------------------------------------------------------------Move Rectangle Up---------------------------------------------------------------------//
  public void moveUp() {                                                                                                                                       //
    bottomRectangles.set((NUMRECTANGLES - 1),new MovingImage("./src/Assets/middle.JPG",RECTANGLEWIDTH*(NUMRECTANGLES - 1),bottomRectangles.get(26).getY() - moveIncrement));  //
    topRectangles.set((NUMRECTANGLES - 1),new MovingImage("./src/Assets/middle.JPG",RECTANGLEWIDTH*(NUMRECTANGLES - 1),topRectangles.get(26).getY() - moveIncrement));        //
  }                                                                                                                                                            //
  //=====================================================================Move Rectangle Up=====================================================================//
  
  //--Random Determine How High The Obsticle Gonna Be--//
  public int randomMidHeight() {                       //
    int max = 10000;                                   //
    int min = 0;                                       //
                                                       //
    for(int x = 0; x < NUMRECTANGLES; x++) {           //
      if(topRectangles.get(x).getY() > min) {          //
        min = (int)topRectangles.get(x).getY();        //
      }                                                //
      if(bottomRectangles.get(x).getY() < max) {       //
        max = (int)bottomRectangles.get(x).getY();     //
      }                                                //
    }                                                  //
    min += RECTANGLEHEIGHT;                            //
    max -= (RECTANGLEHEIGHT + min);                    //
    return min + (int)(Math.random() * max);           //
  }                                                    //
  //--Random Determine How High The Obsticle Gonna Be--//
  
  //------------------------------------------------Moves The Randomly Generated Middle Rectangles------------------------------------------------//
  public void updateMiddle() {                                                                                                                    //
    if(middleRectangles.get(0).getX() > -1 * RECTANGLEWIDTH) {                                                                                    //
      middleRectangles.set(0,new MovingImage("./src/Assets/middle.JPG",middleRectangles.get(0).getX() - (RECTANGLEWIDTH/5), middleRectangles.get(0).getY()));    //
      middleRectangles.set(1,new MovingImage("./src/Assets/middle.JPG",middleRectangles.get(1).getX() - (RECTANGLEWIDTH/5), middleRectangles.get(1).getY()));    //
    } else {                                                                                                                                      //
      middleRectangles.set(0,new MovingImage("./src/Assets/middle.JPG",middleRectangles.get(1).getX() - (RECTANGLEWIDTH/5), middleRectangles.get(1).getY()));    //
      middleRectangles.set(1,new MovingImage("./src/Assets/middle.JPG",middleRectangles.get(0).getX() + 580,randomMidHeight()));                                 //
    }                                                                                                                                             //
  }                                                                                                                                               //
  //================================================Moves The Randomly Generated Middle Rectangles================================================//
  
  //------------------------------------------------Moves The Randomly Generated Middle Rectangles------------------------------------------------//
  public void updateBoostMiddle() {                                                                                                                    //
    if(middleRectangles.get(0).getX() > -1 * RECTANGLEWIDTH) {                                                                                    //
      middleRectangles.set(0,new MovingImage("./src/Assets/boost.JPG",middleRectangles.get(0).getX() - (RECTANGLEWIDTH/5), middleRectangles.get(0).getY()));    //
      middleRectangles.set(1,new MovingImage("./src/Assets/boost.JPG",middleRectangles.get(1).getX() - (RECTANGLEWIDTH/5), middleRectangles.get(1).getY()));    //
    } else {                                                                                                                                      //
      middleRectangles.set(0,new MovingImage("./src/Assets/boost.JPG",middleRectangles.get(1).getX() - (RECTANGLEWIDTH/5), middleRectangles.get(1).getY()));    //
      middleRectangles.set(1,new MovingImage("./src/Assets/boost.JPG",middleRectangles.get(0).getX() + 580,randomMidHeight()));                                 //
    }                                                                                                                                             //
  }                                                                                                                                               //
  //================================================Moves The Randomly Generated Middle Rectangles================================================//
  
  //------------------------------------------------Moves The Randomly Generated Middle Rectangles------------------------------------------------//
  public void updateNullMiddle() {                                                                                                                    //
    if(middleRectangles.get(0).getX() > -1 * RECTANGLEWIDTH) {                                                                                    //
      middleRectangles.set(0,new MovingImage("",middleRectangles.get(0).getX() - (RECTANGLEWIDTH/5), middleRectangles.get(0).getY()));    //
      middleRectangles.set(1,new MovingImage("",middleRectangles.get(1).getX() - (RECTANGLEWIDTH/5), middleRectangles.get(1).getY()));    //
    } else {                                                                                                                                      //
      middleRectangles.set(0,new MovingImage("",middleRectangles.get(1).getX() - (RECTANGLEWIDTH/5), middleRectangles.get(1).getY()));    //
      middleRectangles.set(1,new MovingImage("",middleRectangles.get(0).getX() + 580,randomMidHeight()));                                 //
    }                                                                                                                                             //
  }                                                                                                                                               //
  //================================================Moves The Randomly Generated Middle Rectangles================================================//
  
  //--------------------------Check Is Hit Or Not--------------------------//
  public boolean isHit() {                                                 //
    for(int x = 3; x <= 7; x++) {                                          //
      if(pixel.getY() + 48 >= bottomRectangles.get(x).getY()) {            //
        return true;                                                       //
      }                                                                    //
    }                                                                      //
                                                                           //
    for(int y = 3; y <= 7; y++) {                                          //
      if(pixel.getY() <= topRectangles.get(y).getY() + RECTANGLEHEIGHT) {  //
        return true;                                                       //
      }                                                                    //
    }                                                                      //
    if (!boost) {
      for(int z = 0; z <= 1; z++) {                                          //
        if(isInMidRange(z)) {                                                //
          if (boostComing) {
            boostComing = false;
            boostComingC = 0;
            boost = true;
          } else {
            return true;
          }
        }                                                                    //
      }                                                                      //
    }
    return false;                                                          //
  }                                                                        //
  //==========================Check Is Hit Or Not==========================//
  
  //---------------------------------------------------------------------Is Hit Middle---------------------------------------------------------------------//
  public boolean isInMidRange(int num) {                                                                                                                   //
    Rectangle middlecheck = new Rectangle((int)middleRectangles.get(num).getX(),(int)middleRectangles.get(num).getY(),RECTANGLEWIDTH,RECTANGLEHEIGHT);     //
    Rectangle pixelcheck = new Rectangle((int)pixel.getX(),(int)pixel.getY(),20,20);                                                                       //
    return middlecheck.intersects(pixelcheck);                                                                                                             //
  }                                                                                                                                                        //
  //=====================================================================Is Hit Middle=====================================================================//
  
  //---------------------------------------------------------------------Is Hit Boost---------------------------------------------------------------------//
  public boolean isInBoostRange(int num) {                                                                                                                  //
    Rectangle boostcheck = new Rectangle((int)middleRectangles.get(num).getX(),(int)middleRectangles.get(num).getY(),RECTANGLEWIDTH,RECTANGLEHEIGHT);    //
    Rectangle pixelcheck = new Rectangle((int)pixel.getX(),(int)pixel.getY(),20,20);                                                                       //
    return boostcheck.intersects(pixelcheck);                                                                                                             //
  }                                                                                                                                                        //
  //=====================================================================Is Hit Boost=====================================================================//
  
  //-----Check If The Pixel Crashs-----//
  public void crash() {                //
    crash = true;                      //
    if(distance > maxDistance) {       //
      maxDistance = distance;          //
      save();                          //
    }                                  //
    initiate();                        //
  }                                    //
  //=====Check If The Pixel Crashs=====//
    
  //-----------------------------------Move The Pixel-----------------------------------//
  public void updatePixel() {                                                           //
    upCount += .08;                                                                     //
    if(goingUp) {                                                                       //
      //-----------------------------Move Pixel Up-----------------------------//       //
      if(upCount < 3.5) {                                                      //       //
        pixel.setPosition(XPOSITION,(double)(pixel.getY() - (.3 + upCount)));  //       //
      } else {                                                                 //       //
        pixel.setPosition(XPOSITION,(double)(pixel.getY() - (1.2 + upCount))); //       //
        pixel.setImage("./src/Assets/pixelUp.GIF");                                         //       //
      }                                                                        //       //
      //=============================Move Pixel Up=============================//       //
    } else {                                                                            //
      //----------------------------Move Pixel Down----------------------------//       //
      if(upCount < 1) {                                                        //       //
        pixel.setPosition(XPOSITION,(double)(pixel.getY() + upCount));         //       //
      } else {                                                                 //       //
        pixel.setPosition(XPOSITION,(double)(pixel.getY() + (1.2 + upCount))); //       //
        pixel.setImage("./src/Assets/pixel.GIF");                                           //       //
      }                                                                        //       //
      //============================Move Pixel Down============================//       //
    }                                                                                   //
    if(isHit()) {                                                                       //
      crash();                                                                          //
    }                                                                                   //
  }                                                                                     //
  //===================================Move The Pixel===================================//
    
  //--Called when the mouse exits the game window--//
  public void mouseExited(MouseEvent e) {          //
    if(start) {                                    //
      pause = true;                                //
    }                                              //
  }                                                //
  //==Called when the mouse exits the game window==//
  
  //--Called when the mouse enters the game window--//
  public void mouseEntered(MouseEvent e){
  }
  //==Called when the mouse enters the game window==//
  
  //-----Called when the mouse is released-----//
  public void mouseReleased(MouseEvent e) {    //
    goingUp = false;                           //
    upCount = 0;                               //
    if(pause) {                                //
      pause = false;                           //
    }                                          //
  }                                            //
  //=====Called when the mouse is released=====//
  
  //-----Called when the mouse is pressed-----//
  public void mousePressed(MouseEvent e) {    //
    if (!start) {                             //
      start = true;                           //
    }                                         //
    goingUp = true;                           //
    upCount = 0;                              //
  }                                           //
  //=====Called when the mouse is pressed=====//
  
  //----Called when the mouse is released----//
  public void mouseClicked(MouseEvent e) {   //
  }                                          //
  //====Called when the mouse is released====//
  
  //---Called when the key is pressed---//
  public void keyPressed(KeyEvent e) {  //
  }                                     //
  //===Called when the key is pressed===//
  
  //---Called when the key is released---//
  public void keyReleased(KeyEvent e) {  //
  }                                      //
  //===Called when the key is released===//
  
  //-----Check what type the key is-----//
  public void keyTyped(KeyEvent e) {    //
  }                                     //
  //=====Check what type the key is=====//
}
