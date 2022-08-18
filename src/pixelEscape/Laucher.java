package pixelEscape;

import javax.swing.*;

/**
 * This is the driver class contain main method
 *
 */
public class Laucher {
    public static void main(String[] args) {
        try {
          UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        Frame_Main frame = new Frame_Main();
        Controller_Main controller = new Controller_Main(frame);
    }
}