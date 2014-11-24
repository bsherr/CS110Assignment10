/**
   The Driver class drives the War card game GUI.
   @author Ben Sherr
*/

import javax.swing.*;                                                         // Needed for the Swing classes

public class Driver{
   private final static int WINDOW_WIDTH = 700;
   private final static int WINDOW_HEIGHT = 475;

   public static void main(String [] args){
      JFrame warGUI = new WarGUI();
      warGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      warGUI.setVisible(true);
      warGUI.pack();
   }
}