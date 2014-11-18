/**
   The WarGame class simulates the card game of war and implements the functionality of the Deck class.
   @author Ben Sherr
*/

public class WarGame{
   public static void main(String [] args){
      Deck deck = new Deck(true);                                    // Create the deck
      deck.shuffle();                                                // Shuffle the deck      
      Deck userDeck = allocate(deck, true);                          // Allocate the user's deck
      Deck compDeck = allocate(deck, false);                         // Allocate the computer's deck
      System.out.println("User deck: " + userDeck.size() + 
                         " cards \nComputer deck: " + compDeck.size() + " cards\n");
      
      while(!userDeck.isEmpty() && !compDeck.isEmpty()){
         Deck newCards = play(userDeck, compDeck);
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
      
      System.out.println("\nUser deck: " + userDeck.size() + 
                         " cards \nComputer deck: " + compDeck.size() + " cards");
      winner(userDeck);
   }// main
   
   /**
      The allocate method divides the deck amongst the two players. If player is true then the method creates
      the user's deck, otherwise the method creates the computer's deck.
      @param d The deck of cards.
      @param player The boolean indicating for whom the deck is being apportioned for.
      @return       The divided portion of the deck. 
   */
   public static Deck allocate(Deck d, boolean player){
      Deck d1 = new Deck();
      if(player){                         
         for(int i = 0; i < d.size(); i = i + 2)
            d1.add(d.get(i));
      }// create the user's deck
      else{
         for(int i = 1; i < d.size(); i = i + 2)
            d1.add(d.get(i));
      }// create the computer's deck
         
      return d1;
   }
   
   /**
      The play method simulates the card game war and accumulates the dealt cards with each successive round.
      @param myDeck The user's deck.
      @param compDeck The computer's deck.
      @return         The cards play for the single round or multiple rounds in the event of a tie.
   */
   public static Deck play(Deck myDeck, Deck compDeck){
      Card myCard = myDeck.remove(0);                                   // Remove the user's first card
      Card compCard = compDeck.remove(0);                               // Remove the computer's first card
      roundResults(myCard, compCard);
      Deck newCards = new Deck();                                       // Deck to hold cards played
      newCards.add(myCard);                                             // Accumulate cards
      newCards.add(compCard);                                           // Accumulate cards
      
      while(myCard.equals(compCard) && !myDeck.isEmpty() 
      && !compDeck.isEmpty()){
         Card myCard2 = myDeck.remove(0);                               // User's face down card
         Card compCard2 = compDeck.remove(0);                           // Computer's face down card
         System.out.println("\tFace down cards played.");
         newCards.add(myCard2);                                         // Accumulate cards                             
         newCards.add(compCard2);                                       // Accumulate cards
         if(myDeck.isEmpty() || compDeck.isEmpty())
            break;
            
         myCard = myDeck.remove(0);                                     // User's face up card
         compCard = compDeck.remove(0);                                 // Computer's face up card
         roundResults(myCard, compCard);
         newCards.add(myCard);                                          // Accumulate cards
         newCards.add(compCard);                                        // Accumulate cards
         if(myDeck.isEmpty() || compDeck.isEmpty())
            break;
      }// loop for tie-breaker or war 
      
      return newCards;   
   }
   
   /** 
      The roundResults method prints the result of the round.
      @param myCard The user's card.
      @param compCard The computer's card.
   */
   public static void roundResults(Card myCard, Card compCard){
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
   public static void winner(Deck myDeck){
      if(!myDeck.isEmpty())
         System.out.println("\nThe user wins this game.");
      else
         System.out.println("\nThe computer wins this game.");
   }
}