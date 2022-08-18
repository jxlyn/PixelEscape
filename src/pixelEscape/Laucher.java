
package pixelEscape;

import javax.swing.*;

public class Laucher {
  
  public static void main(String[] args) {     
    try {
      UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
    } catch (Exception e) {
      e.printStackTrace();
    }
    new Pixel_Escape();
  }
  
}
