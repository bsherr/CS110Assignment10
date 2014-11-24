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
   private JComponent titlePanel, infoPanel, gamePanel;                       // All JComponents
   private JLabel title, info, game, outcome, userNum, compNum,               // All JLabels
                  uNum, cNum, userCard, userDeck, compCard, compDeck;
   private JButton play;                                                      // The buttons
   
   private final static int FRST = 0;                                         // First row/column 
   private final static int SEC = 1;                                          // Second row/column
   private final static int THRD = 2;                                         // Third row/column
   private final static int FRTH = 3;                                         // Fourth row/column
   private final static int FTH = 4;                                          // Fifth row
   private final static int SXTH = 5;                                         // Sixth row
   private final static int WIDTH = 4;                                        // Frame is 4 columns wide   
   
   /**
      Constructor, constructs the GUI for the card game War.
   */
   public WarGUI(){
      setLayout(new GridBagLayout());                                         
      warGame = new WarGame();                                                
      //GridBagConstraints c = new GridBagConstraints();
      
      // Title
      titlePanel = new JPanel();
      title = new JLabel("A Simulated Card Game of War");
      title.setFont(new Font("Times New Roman", Font.BOLD, 18));
      titlePanel.setBackground(Color.WHITE);
      titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      titlePanel.add(title);
      addComp(titlePanel, FRST, FRST, WIDTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0);
            
      // Directions
      infoPanel = new JPanel();
      info = new JLabel("Directions: Press the 'Play' button to simulate a single round of the card game.");
      info.setFont(new Font("Times New Roman", Font.ITALIC, 16));
      infoPanel.add(info);
      addComp(infoPanel, FRST, SEC, WIDTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 10, 0), 0);                      
      
      // Labels - Respective decks and cards
      String[] deckLbls = {"User's deck", "User's card", "Computer's card", "Computer's deck"};
      for(int i = 0; i <= FRTH; i++){
         JComponent label = new JLabel(deckLbls[i]);
         label.setFont(new Font("Times New Roman", Font.BOLD, 14));
         addComp(label, i, THRD, WIDTH/4, GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0);
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
   }// Constructor   
   
   /**
      The addComp method sets the grid bag constraints given the parameters and adds the component to the frame.
   */
   public void addComp(Component Comp, int x, int y, int width, int fillType, Insets i, int yPad){
      GridBagConstraints c = new GridBagConstraints(x, y, width, 1, 0, 0, GridBagConstraints.CENTER, fillType, i, 0, yPad);
      add(Comp, c);
   }
   
   /**
      The PlayButtonListener is an action listener class for the play button.
   */
   private class PlayButtonListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         warGame.play();                                                   // Play a round
         Card user = warGame.getUserCard();                                // Get the user's card
         Card comp = warGame.getCompCard();                                // Get the computer's card
         
         resetAll();                                                       // Remove previous text and images

         userCard.setIcon(new ImageIcon(user.addressString()));            // Reset user card image
         uNum.setText("" + warGame.getUserDeck().size());                  // Reset user deck text
         compCard.setIcon(new ImageIcon(comp.addressString()));            // Reset computer card image
         cNum.setText("" + warGame.getCompDeck().size());                  // Reset computer deck text
         outcome.setText(warGame.roundResults());                          // Reset outcome text
         pack();
         /*
         if(warGame.getWar()){
            resetAll();
            warGame.isWar();
            userCard.setIcon(new ImageIcon(user.addressString()));         // Reset user card image
            uNum.setText("" + warGame.getUserDeck().size());               // Reset user deck text
            compCard.setIcon(new ImageIcon(comp.addressString()));         // Reset computer card image
            cNum.setText("" + warGame.getCompDeck().size());               // Reset computer deck text
            outcome.setText(warGame.roundResults());                       // Reset outcome text
            pack();
         }*/
         
         if(warGame.getUserDeck().isEmpty() || warGame.getCompDeck().isEmpty())
            play.setEnabled(false);         
      }
      
      /**
      */
      private void setAll(){
      }
      
      /**
         The resetAll method removes the card images, card counts, and outcome text for the previous round of play.
      */
      private void resetAll(){
         userCard.setIcon(null);                                           // Remove previous user card image
         uNum.setText(null);                                               // Remove previous user deck text
         compCard.setIcon(null);                                           // Remove previous computer card image
         cNum.setText(null);                                               // Remove computer deck previous text
         outcome.setText(null);                                            // Remove outcome text   
      }
   }// PlayButtonListener class
}// WarGUI class