package pixelEscape;
//---------Import---------//
  import javax.swing.*;     //
  import java.awt.*;        //
  //=========Import=========//
  
public class MovingImage {
  //-----Set Stuffs-----//
  private Image image;  //The picture
  private double x;     //X position
  private double y;     //Y position
  //=====Set Stuffs=====//
  
  //--Construct a new Moving Image with image, x position, and y position given--//
  public MovingImage(Image img, double xPos, double yPos) {                      //
    image = img;                                                                 //
    x = xPos;                                                                    //
    y = yPos;                                                                    //
  }                                                                              //
  //==Construct a new Moving Image with image, x position, and y position given==//
  
  //--Construct a new Moving Image with image, x position, and y position given--//
  public MovingImage(String path, double xPos, double yPos) {                    //From the path
    this(new ImageIcon(path).getImage(), xPos, yPos);                            //
  }                                                                              //
  //==Construct a new Moving Image with image, x position, and y position given==//
  
  //---------------------Set Methods---------------------//
  public void setPosition(double xPos, double yPos) {    //
    x = xPos;                                            //
    y = yPos;                                            //
  }                                                      //
                                                         //
  public void setImage(String path) {                    //
    image = new ImageIcon(path).getImage();              //
  }                                                      //
                                                         //
  public void setY(double newY) {                        //
    y = newY;                                            //
  }                                                      //
                                                         //
  public void setX(double newX) {                        //
    x = newX;                                            //
  }                                                      //
  //=====================Set Methods=====================//

  //--------Get Methods--------//
  public double getX() {       //
    return x;                  //
  }                            //
                               //
  public double getY() {       //
    return y;                  //
  }                            //
                               //
  public Image getImage() {    //
    return image;              //
  }                            //
  //========Get Methods========//
}