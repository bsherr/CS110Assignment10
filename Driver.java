/**
   The Driver class drives the War card game GUI.
   @author Ben Sherr
*/

import javax.swing.*;                                                         // Needed for the Swing classes

public class Driver{
   private static final int WINDOW_WIDTH = 600;
   private static final int WINDOW_HEIGHT = 600;
   
   public static void main(String [] args){
      JFrame warGUI = new WarGUI();
      warGUI.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      warGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      warGUI.setVisible(true);
   }
}