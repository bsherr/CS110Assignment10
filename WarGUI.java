/**
   The WarGUI class implements a GUI for the WarGame class. The WarGame class simulates the card game between a user 
   and the computer.
   @author Ben Sherr
*/

import javax.swing.*;                                                         // Needed for the Swing classes
import java.awt.*;                                                            // Needed for BorderLayout class
import java.awt.event.*;                                                      // Neeeded for ActionEvent class

public class WarGUI extends JFrame{   
   private WarGame warGame;
   private JPanel titlePanel, gamePanel, outcomePanel, buttonPanel;
   private JLabel title, game, outcome;
   private JButton addUser, play, addComp;    
   
   /**
      Constructor, constructs the GUI for the card game War.
   */
   public WarGUI(){
      setLayout(new GridLayout(4, 1));                                        // Create new 4 x 1 grid
      warGame = new WarGame();                                                // Construct new game of War
      
      titlePanel = new JPanel();
      gamePanel = new JPanel(new GridLayout(2, 4));
      outcomePanel = new JPanel();
      buttonPanel = new JPanel(new GridLayout(1, 3));
      
      title = new JLabel("A Simulated Card Game of War");
      title.setFont(new Font("Times New Roman", Font.BOLD, 18));
      titlePanel.add(title);
      
      //gamePanel, TBD
      
      outcome = new JLabel("In Progress");
      outcome.setFont(new Font("Times New Roman", Font.BOLD, 14));
      outcomePanel.add(outcome);
      
      addUser = new JButton("Add to user's deck");
      play = new JButton("Play");
      addComp = new JButton("Add to computer's deck");
      
      buttonPanel.add(addUser);
      buttonPanel.add(play);
      buttonPanel.add(addComp);
      
      add(titlePanel);
      add(gamePanel);
      add(outcomePanel);
      add(buttonPanel);
   }   
   
   // Tester
   public static void main(String [] args){
      JFrame warGUI = new WarGUI();
      warGUI.setSize(600, 600);
      warGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      warGUI.setVisible(true);
   }
}