/**
   The WarGame class simulates the card game of war and implements the functionality of the Deck class.
   @author Ben Sherr
*/

public class WarGame{
   private Deck deck;                                                // The main deck
   private Deck userDeck;                                            // The user's deck
   private Deck compDeck;                                            // The computer's deck
   private static final int NUM_CARDS = 5;                           // Desired number of cards in each deck
   
   /**
      Constructor, constructs and shuffles the starting deck and allocates the desired amounts of cards to both 
      the user and the computer.
   */
   public WarGame(){
      deck = new Deck();                                             // Create the deck
      deck.shuffle();                                                // Shuffle the deck      
      userDeck = deck.divide(deck, true, NUM_CARDS);                 // Allocate the user's deck
      compDeck = deck.divide(deck, false, NUM_CARDS);                // Allocate the computer's deck
   }
   
   /**
      The play method simulates the card game war and accumulates the dealt cards with each successive round.
      @return         The cards played for the single round or multiple rounds in the event of a tie.
   */
   public Deck play(){
      Card myCard = userDeck.remove(0);                                 // Remove the user's first card
      Card compCard = compDeck.remove(0);                               // Remove the computer's first card
      roundResults(myCard, compCard);
      Deck newCards = new Deck(true);                                   // Deck to hold cards played
      newCards.add(myCard);                                             // Accumulate cards
      newCards.add(compCard);                                           // Accumulate cards
      
      while(myCard.equals(compCard) && !userDeck.isEmpty() 
      && !compDeck.isEmpty()){
         Card myCard2 = userDeck.remove(0);                             // User's face down card
         Card compCard2 = compDeck.remove(0);                           // Computer's face down card
         System.out.println("\tFace down cards played.");
         newCards.add(myCard2);                                         // Accumulate cards                             
         newCards.add(compCard2);                                       // Accumulate cards
         if(userDeck.isEmpty() || compDeck.isEmpty())
            break;
            
         myCard = userDeck.remove(0);                                   // User's face up card
         compCard = compDeck.remove(0);                                 // Computer's face up card
         roundResults(myCard, compCard);
         newCards.add(myCard);                                          // Accumulate cards
         newCards.add(compCard);                                        // Accumulate cards
         if(userDeck.isEmpty() || compDeck.isEmpty())
            break;
      }// loop for tie-breaker or war 
      
      return newCards;   
   }
   
   /**
      The playGame method continuously calls the play method until one of the players runs out of the cards.
   */
   public void playGame(){
      while(!userDeck.isEmpty() && !compDeck.isEmpty()){
         Deck newCards = play();
         Card userLastCard = newCards.get(newCards.size() - 2);      // Retrieve user's last card
         Card compLastCard = newCards.get(newCards.size() - 1);      // Retrieve computer's last card
         if(userLastCard.isGreater(compLastCard)){                   // User wins the round
            for(int i = 0; i < newCards.size(); i++)
               userDeck.add(newCards.get(i));
         }// user wins
         else{                                                       // Computer wins the round
            for(int i = 0; i < newCards.size(); i++)
               compDeck.add(newCards.get(i));
         }// computer wins    
      }// play the game
   }
   
   /** 
      The roundResults method prints the result of the round.
      @param myCard The user's card.
      @param compCard The computer's card.
   */
   public void roundResults(Card myCard, Card compCard){
      if(!myCard.equals(compCard)){
         if(myCard.isGreater(compCard))
            System.out.println("User: " + myCard + "\t\tComputer: " + compCard + "\n\tThe user wins!");
         else
            System.out.println("User: " + myCard + "\t\tComputer: " + compCard + "\n\tThe computer wins!");
      }
      else
         System.out.println("User: " + myCard + "\t\tComputer: " + compCard + "\n\tWar!");
   }
   
   /**
      The winner method prints the result of the game.
      @param myDeck The user's deck.
   */
   public void winner(Deck myDeck){
      if(!myDeck.isEmpty())
         System.out.println("\nThe user wins this game.");
      else
         System.out.println("\nThe computer wins this game.");
   }
   
   // Tester
   public static void main(String [] args){
      WarGame game1 = new WarGame();
      game1.playGame();
   }
}