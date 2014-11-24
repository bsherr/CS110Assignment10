/**
   The WarGUI class implements a GUI for the WarGame class. The WarGame class simulates the card game between a user 
   and the computer.
   @author Ben Sherr
*/

import javax.swing.*;                                                         // Needed for the Swing classes
import java.awt.*;                                                            // Needed for BorderLayout class
import java.awt.event.*;                                                      // Neeeded for ActionEvent class

public class WarGUI extends JFrame{   
   private WarGame warGame;                                                   // The actual game
   private JComponent titlePanel;                                             // All JComponents
   private JLabel title, game, outcome, userNum, compNum,                     // All JLabels
                  uNum, cNum, userCard, userDeck, compCard, compDeck;
   private JButton play, war;                                                 // The buttons
   private int countWar;                                                      // Number of war button presses
   
   private final static int FRST = 0;                                         // First row/column 
   private final static int SEC = 1;                                          // Second row/column
   private final static int THRD = 2;                                         // Third row/column
   private final static int FRTH = 3;                                         // Fourth row/column
   private final static int FTH = 4;                                          // Fifth row
   private final static int SXTH = 5;                                         // Sixth row
   private final static int SVTH = 6;                                         // Seventh row
   private final static int WIDTH = 4;                                        // Frame is 4 columns wide   
   
   /**
      Constructor, constructs the GUI for the card game War.
   */
   public WarGUI(){
      setLayout(new GridBagLayout());                                         
      warGame = new WarGame();                                                
      
      // Title
      titlePanel = new JPanel();
      title = new JLabel("A Simulated Card Game of War");
      title.setFont(new Font("Times New Roman", Font.BOLD, 20));
      titlePanel.setBackground(Color.WHITE);
      titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      titlePanel.add(title);
      addComp(titlePanel, FRST, FRST, WIDTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0);                     
      
      // Labels - Respective decks and cards
      String[] deckLbls = {"User's hand", "User's card", "Computer's card", "Computer's hand"};
      for(int i = 0; i <= FRTH; i++){
         JComponent label = new JLabel(deckLbls[i]);
         label.setFont(new Font("Times New Roman", Font.ITALIC, 18));
         addComp(label, i, SEC, WIDTH/4, GridBagConstraints.CENTER, new Insets(20, 0, 0, 0), 0);
      }
      
      // Game - user deck (face down)
      userDeck = new JLabel(new ImageIcon("CardPics\\back.jpg"));
      addComp(userDeck, FRST, FRTH, WIDTH/4, GridBagConstraints.CENTER, new Insets(0, 5, 5, 5), WIDTH*9);
      
      // The user's card image (place holder)
      userCard = new JLabel(new ImageIcon("CardPics\\placeHold.jpg"));
      addComp(userCard, SEC, FRTH, WIDTH/4, GridBagConstraints.CENTER, new Insets(0, 5, 5, 25), WIDTH*9);
      
      // The computer's card image (place holder)
      compCard = new JLabel(new ImageIcon("CardPics\\placeHold.jpg"));
      addComp(compCard, THRD, FRTH, WIDTH/4, GridBagConstraints.CENTER, new Insets(0, 25, 5, 5), WIDTH*9);
      
      // Game - computer deck (face down)
      compDeck = new JLabel(new ImageIcon("CardPics\\back.jpg"));
      addComp(compDeck, FRTH, FRTH, WIDTH/4, GridBagConstraints.CENTER, new Insets(0, 5, 5, 5), WIDTH*9); 
      
      // Label - number of user's cards
      userNum = new JLabel("Number of cards:");
      userNum.setFont(new Font("Times New Roman", Font.BOLD, 14));
      addComp(userNum, FRST, FTH, WIDTH/4, GridBagConstraints.CENTER, new Insets(0, 5, 5, 5), 0);
      
      // Outcome of the game as text
      outcome = new JLabel("Press 'Play' to begin the game");
      outcome.setFont(new Font("Times New Roman", Font.BOLD, 14));
      addComp(outcome, SEC, FTH, WIDTH/2, GridBagConstraints.CENTER, new Insets(0, 5, 5, 5), 0);
      
      // Label - number of computer's cards
      compNum = new JLabel("Number of cards:");
      compNum.setFont(new Font("Times New Roman", Font.BOLD, 14));
      addComp(compNum, FRTH, FTH, WIDTH/4, GridBagConstraints.CENTER, new Insets(0, 5, 5, 5), 0);
      
      // Inital number of user's cards
      uNum = new JLabel("" + warGame.getUserDeck().size());
      uNum.setFont(new Font("Times New Roman", Font.BOLD, 14));
      addComp(uNum, FRST, SXTH, WIDTH/4, GridBagConstraints.CENTER, new Insets(0, 5, 5, 5), 0);
      
      // Button - play
      play = new JButton("Play");
      play.addActionListener(new PlayButtonListener());
      play.setFont(new Font("Times New Roman", Font.BOLD, 14));
      addComp(play, SEC, SXTH, WIDTH/2, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0);
         
      // Initial number of computer's cards
      cNum = new JLabel("" + warGame.getCompDeck().size());
      cNum.setFont(new Font("Times New Roman", Font.BOLD, 14));
      addComp(cNum, FRTH, SXTH, WIDTH/4, GridBagConstraints.CENTER, new Insets(0, 5, 5, 5), 0);
      
      // Button - war
      war = new JButton("War");                                      
      war.addActionListener(new WarButtonListener());
      war.setFont(new Font("Times New Roman", Font.BOLD, 14));
      addComp(war, SEC, SVTH, WIDTH/2, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0);
      war.setVisible(false);    
   }// Constructor   
   
   /**
      The addComp method sets the grid bag constraints given the parameters and adds the component to the frame.
   */
   public void addComp(Component Comp, int x, int y, int width, int fillType, Insets i, int yPad){
      GridBagConstraints c = new GridBagConstraints(x, y, width, 1, 0, 0, GridBagConstraints.CENTER, fillType, i, 0, yPad);
      add(Comp, c);
   }
   
   /**
      The setAll method sets the card images, card counts, and the outcome of the game after each round.
   */
   public void setAll(){
      Card user = warGame.getUserCard();                                   // Get the user's card
      Card comp = warGame.getCompCard();                                   // Get the computer's card
      userCard.setIcon(new ImageIcon(user.addressString()));               // Reset user card image
      uNum.setText("" + warGame.getUserDeck().size());                     // Reset user deck text
      compCard.setIcon(new ImageIcon(comp.addressString()));               // Reset computer card image
      cNum.setText("" + warGame.getCompDeck().size());                     // Reset computer deck text
      outcome.setText(warGame.roundResults());                             // Reset outcome text
   }
   
   /**
      The setAllWar method sets the card images, card counts, and the outcome of the game after the face down 
      cards are dealt after the start of a war round.
   */
   public void setAllWar(){
      userCard.setIcon(new ImageIcon("CardPics\\back.jpg"));               // Reset user card image
      uNum.setText("" + (warGame.getUserDeck().size() - 1));               // Reset user deck text
      compCard.setIcon(new ImageIcon("CardPics\\back.jpg"));               // Reset computer card image
      cNum.setText("" + (warGame.getCompDeck().size() - 1));               // Reset computer deck text
      outcome.setText("Face down cards are played.");                      // Reset outcome text
   }
   
   /**
      The gameOver method disables the play button if either of the player's hands are empty.
   */
   public void gameOver(){
      if(warGame.getUserDeck().isEmpty() || warGame.getCompDeck().isEmpty()){
         play.setEnabled(false);                                           // Disable "Play" button
         war.setEnabled(false);                                            // Disable "War" button
      }
   }
   
   /**
      The PlayButtonListener is an action listener class for the play button.
   */
   private class PlayButtonListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         warGame.play();                                                   // Play a round
         setAll();                                                         // Set text and images
         pack();                                                           // Pack the frame
         if(warGame.getWar()){
            war.setVisible(true);                                          // Show the "War" button
            pack();                                                        // Pack the frame   
            countWar = 0;                                                  // Initialize counter for presses
            play.setEnabled(false);                                        // disable "Play" button
         }
         gameOver();                                                       // Check for empty hands        
      }// actionPerformed method
   }// PlayButtonListener class
   
   /**
      The WarButtonListener is an action listener class for the war button.
   */
   private class WarButtonListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         if(countWar == 0){
            setAllWar();                                                   // Set text and images
            play.setEnabled(false);                                        // Disable "Play" button
            gameOver();                                                    // Check for empty hands
            countWar++;                                                    // Increment
         }// face down cards played
         else if(countWar == 1){
            warGame.isWar();                                               // Play war round
            setAll();                                                      // Set text images
            countWar = 0;                                                  // Increment
            gameOver();                                                    // Check for empty hands
            war.setVisible(false);                                         // Hide "War" button
            play.setEnabled(true);                                         // Enable "Play" button
         }// war cards played
         pack();
      }// actionPerformed method
   }// WarButtonListener class
}// WarGUI class