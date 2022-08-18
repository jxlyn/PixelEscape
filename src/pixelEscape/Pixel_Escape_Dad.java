package pixelEscape;
//1. An ArrayList
//2. A 2-dimensional array
//3. Inheritance
//4. At least one timer
//5. An end
//6. Scorekeeping
//7. User interaction (i.e., KeyListener, MouseListener, etc)

public class Pixel_Escape_Dad {
  //---------Big Checks---------//
  public static boolean pause;  //
  public static boolean crash;  //
  public static boolean start;  //
  public static boolean played; //
  public static boolean boost;  //
  //=========Big Checks=========//
  
  //------Up By User------//
  public boolean goingUp; //
  private double upCount; //
  //======Up By User======//
  
  //---------How Far Gose---------//
  public static int distance;     //
  public static int maxDistance;  //
  //=========How Far Gose=========//
   
  //-----------Pixel_Escape-----------//
  public Pixel_Escape_Dad() {         //
    this.played = false;              //
    this.maxDistance = 0;             //
  }                                   //
  //===========Pixel_Escape===========//
  
  //-----------Try To Load File-----------//
  public void load() {                    //
  }
  //===========Try To Load File===========//
}