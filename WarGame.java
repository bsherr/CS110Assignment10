/**
   The WarGame class simulates the card game of war and implements the functionality of the Deck class.
   @author Ben Sherr
*/

public class WarGame{
   private Deck userDeck;                                            // The user's deck
   private Deck compDeck;                                            // The computer's deck
   private Deck newCards;                                            // The cards played each round
   private Card userCard;                                            // The user's played card
   private Card compCard;                                            // The computer's played card
   private boolean war;                                              // Presence of war
   
   /**
      Constructor, constructs and shuffles the starting deck and allocates the desired amounts of cards to both 
      the user and the computer.
   */
   public WarGame(){
      userDeck = new CardPile();                                     // Allocate the user's deck
      compDeck = new CardPile();                                     // Allocate the computer's deck
   }
   
   /**
      The play method simulates the card game war and accumulates the dealt cards with each successive round.
   */
   public void play(){
      war = false;
      userCard = userDeck.remove();                               // Remove the user's first card
      compCard = compDeck.remove();                               // Remove the computer's first card
      newCards = new Deck();                                      // Deck to hold cards played
      newCards.emptyDeck();                                       // Clear the deck of all cards
      newCards.add(userCard);                                     // Accumulate cards
      newCards.add(compCard);                                     // Accumulate cards
      
      if(userCard.equals(compCard))                               // Set war flag, if necessary
         isWar();
      
      addToWinner(newCards);    
   }
   
   /**
      The isWar method simulates the special conditions for play in the event of a tie or war.
   */
   public void isWar(){
      while(userCard.equals(compCard)){
         Card userCard2 = userDeck.remove();                         // User's face down card
         Card compCard2 = compDeck.remove();                         // Computer's face down card
         newCards.add(userCard2);                                    // Accumulate cards                             
         newCards.add(compCard2);                                    // Accumulate cards
         if(userDeck.isEmpty() || compDeck.isEmpty())
            break;
            
         userCard = userDeck.remove();                               // User's face up card
         compCard = compDeck.remove();                               // Computer's face up card
         newCards.add(userCard);                                     // Accumulate cards
         newCards.add(compCard);                                     // Accumulate cards
         if(userDeck.isEmpty() || compDeck.isEmpty())
            break;
      }// loop for tie-breaker or war
   }
   
   /**
      The addTo Winner method adds the cards from the round to the winner's deck.
      @param d1 The deck of cards accumulated during the round.
   */
   public void addToWinner(Deck d1){
      userCard = newCards.get(newCards.size() - 2);                  // Retrieve user's last card
      compCard = newCards.get(newCards.size() - 1);                  // Retrieve computer's last card
      if(userCard.isGreater(compCard)){                              // User wins the round
         for(int i = 0; i < d1.size(); i++)
            userDeck.add(d1.get(i));
      }// user wins
      else{                                                          // Computer wins the round
         for(int i = 0; i < d1.size(); i++)
            compDeck.add(d1.get(i));
      }// computer wins
   }
   
   /** 
      The getUserDeck method returns the user's deck.
      @return The user's deck.
   */
   public Deck getUserDeck(){
      return userDeck;
   }
   
   /** 
      The getCompDeck method returns the computer's deck.
      @return The computer's deck.
   */
   public Deck getCompDeck(){
      return compDeck;
   }
   
   /** 
      The getUserCard method returns the user's current played card.
      @return The user's current played card.
   */
   public Card getUserCard(){
      return userCard;
   }
   
   /** 
      The getCompCard method returns the computer's current played card.
      @return The computer's current played card.
   */
   public Card getCompCard(){
      return compCard;
   }
   
   /**
      The getWar method returns the boolean war, relating to whether or not the war condition is satisfied.
      @return The boolean for the war flag.
   */
   public boolean getWar(){
      return war;
   }
   
   /** 
      The roundResults method prints the result of the round.
   */
   public String roundResults(){
      if(userDeck.isEmpty())
         return "Game over, the computer wins!";
      else if(!userDeck.isEmpty() && !compDeck.isEmpty()){
         if(userCard.isGreater(compCard))
            return "The user wins!";
         else if(!userCard.isGreater(compCard))
            return "The computer wins!";
         else
            return "War!";
      }
      else
         return "Game over, the user wins!";
   }
}