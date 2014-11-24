/**
   The CardPile class represents the cards each player holds during the game. The CardPile class extends the Deck class and
   allows for the creation of each player to hold a reasonable amount of cards at the beginning of the game. 
   @author Ben Sherr
*/

import java.util.Random;

public class CardPile extends Deck{
   private final static int SIZE = 5;                                      // The number of cards for each player at start
   
   /**
      Default Constructor, removes cards from the standard deck until the there are SIZE cards remaining.
   */
   public CardPile(){
      super();                                                             // Create standard deck
      super.shuffle();                                                     // Shuffle the deck
      while(super.size() > SIZE){
         super.remove();                                 
      }     
   }
}