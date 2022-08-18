package pixelEscape;
//---------Import---------//
  import javax.swing.*;     //
  import java.util.*;       //
  import java.awt.*;        //
  //=========Import=========//

public class ImagePanel extends JPanel {
  //---------------Set Images---------------//
  private Image background;                 //The background image
  private MovingImage pixel;                //
  private ArrayList<MovingImage> top;       //An array list of foreground images
  private ArrayList<MovingImage> bottom;    //
  private ArrayList<MovingImage> middle;    //
  private ArrayList<MovingImage> shadow;    //
  //===============Set Images===============//
  
  //--Constructs a new ImagePanel with the background image specified by the file path given--//
  public ImagePanel(String img)  {                                                            //
    this(new ImageIcon(img).getImage());                                                      //
  }                                                                                           //
  //==Constructs a new ImagePanel with the background image specified by the file path given==//
  
  //--------Constructs a new ImagePanel with the background image given--------//
  public ImagePanel(Image img) {                                               //
    background = img;                                                          //
    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));   //Get the size of the image
                                                                               //
    //----Panel Settings----//                                                 //Make the size of the panel equal to the size of the image
    setPreferredSize(size); //                                                 //
    setMinimumSize(size);   //                                                 //
    setMaximumSize(size);   //                                                 //
    setSize(size);          //                                                 //
    //====Panel Settings====//                                                 //
                                                                               //
    top = new ArrayList<MovingImage>();                                        //
    middle = new ArrayList<MovingImage>();                                     //
    bottom = new ArrayList<MovingImage>();                                     //
    shadow = new ArrayList<MovingImage>();                                     //
  }                                                                            //
  //========Constructs a new ImagePanel with the background image given========//
  
  //---------------Called when the computer want to repaint the window---------------//
  public void paintComponent(Graphics g) {                                           //
    g.drawImage(background, 0, 0, null);                                             //Paint the background with its upper left corner at the upper left corner of the panel
                                                                                     //Paint each image in the foreground where it should go
    for(MovingImage img : top) {                                                     //
      g.drawImage(img.getImage(), (int)(img.getX()), (int)(img.getY()), null);       //
    }                                                                                //
    for(MovingImage img : middle) {                                                  //
      g.drawImage(img.getImage(), (int)(img.getX()), (int)(img.getY()), null);       //
    }                                                                                //
    for(MovingImage img : bottom) {                                                  //
      g.drawImage(img.getImage(), (int)(img.getX()), (int)(img.getY()), null);       //
    }                                                                                //
    for(MovingImage img : shadow) {                                                  //
      g.drawImage(img.getImage(), (int)(img.getX()), (int)(img.getY()), null);       //
    }                                                                                //
    if(pixel != null) {                                                              //
      g.drawImage(pixel.getImage(), (int)(pixel.getX()), (int)(pixel.getY()), null); //
    }                                                                                //
    drawStrings(g);                                                                  //
  }                                                                                  //
  //===============Called when the computer want to repaint the window===============//
  
  //----------------------Draw Words To The Screen----------------------//
  public void drawStrings(Graphics g) {                                 //
    g.setColor(Color.WHITE);                                            //
    g.setFont(new Font("Arial",Font.BOLD,20));                          //
    g.drawString("Distance: " + Pixel_Escape.distance,30,500);          //
    g.setFont(new Font("Arial",Font.BOLD,20));                          //
                                                                        //
    if (Pixel_Escape.distance > Pixel_Escape.maxDistance) {             //
      g.drawString("HighScore: " + Pixel_Escape.distance,600,500);      //
    } else {                                                            //
      g.drawString("HighScore: " + Pixel_Escape.maxDistance,600,500);   //
    }                                                                   //
                                                                        //
    if(Pixel_Escape.pause) {                                            //
      g.setColor(Color.RED);                                            //
      g.setFont(new Font("Chiller",Font.BOLD,72));                      //
      g.drawString("Paused",280,290);                                   //
      g.setFont(new Font("Chiller",Font.BOLD,30));                      //
      g.drawString("Click to unpause",275,340);                         //
    }                                                                   //
  }                                                                     //
  //======================Draw Words To The Screen======================//
  
  //-------------------------------------------------Replaces the list of foreground images with the one given, and repaints the panel-------------------------------------------------//
  public void updateImages(ArrayList<MovingImage> newTop,ArrayList<MovingImage> newMiddle,ArrayList<MovingImage> newBottom,MovingImage newPixel,ArrayList<MovingImage> newShadow) {    //
    top = newTop;                                                                                                                                                                      //
    pixel = newPixel;                                                                                                                                                                  //
    middle = newMiddle;                                                                                                                                                                //
    bottom = newBottom;                                                                                                                                                                //
    shadow = newShadow;                                                                                                                                                                //
    repaint();                                                                                                                                                                         //Repaints stuff
    }                                                                                                                                                                                  //
  //=================================================Replaces the list of foreground images with the one given, and repaints the panel=================================================//
}