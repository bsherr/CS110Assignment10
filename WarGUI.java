/**
   The WarGUI class implements a GUI for the WarGame class. The WarGame class simulates the card game between a user 
   and the computer.
   @author Ben Sherr
*/

import javax.swing.*;                                                         // Needed for the Swing classes
import java.awt.*;                                                            // Needed for BorderLayout class

public class WarGUI extends JFrame{
   private final int WINDOW_WIDTH = 500;
   private final int WINDOW_HEIGHT = 400;
   
   /**
      Constructor
   */
   public WarGUI(){
      setTitle("A Simulated Card Game of War");
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new BorderLayout());
      
      JPanel top = new JPanel();
      JPanel left = new JPanel();
      JPanel right = new JPanel();      
      JPanel bottom = new JPanel();      
      
      JLabel title = new JLabel("War");
      JLabel userCard = new JLabel("User's card");
      JLabel compCard = new JLabel("Computer's card");
      JLabel nums = new JLabel("Number of cards");
      
      top.setBorder(BorderFactory.createLineBorder(Color.black));
      left.setBorder(BorderFactory.createLineBorder(Color.black));
      right.setBorder(BorderFactory.createLineBorder(Color.black));
      bottom.setBorder(BorderFactory.createLineBorder(Color.black));
      
      top.add(title);
      left.add(userCard);
      right.add(compCard);
      bottom.add(nums);      
      
      add(top, BorderLayout.NORTH);
      add(left, BorderLayout.EAST);
      add(right, BorderLayout.WEST);
      add(bottom, BorderLayout.SOUTH);      
      
      setVisible(true);
   }
   
   public static void main(String [] args){
      new WarGUI();
   }
}