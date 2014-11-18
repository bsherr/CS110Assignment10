/**
   The Card class provides the means to create a Card object, which represents a card from a standard 52 
   card deck. After its creatiom, the properties of the Card - rank and suit, may not be altered. For 
   our purposes, two card are equal if they are of the same rank.
   @author Ben Sherr
*/

public class Card{
   public static final int SPADES = 1;                               // the first set of 13 cards
   public static final int CLUBS = 2;                                // the second set of 13 cards                            
   public static final int HEARTS = 3;                               // the third set of 13 cards
   public static final int DIAMONDS = 4;                             // the fourth set of 13 cards
   public static final int KING = 13;
   public static final int QUEEN = 12;
   public static final int JACK = 11;
   public static final int ACE = 1;
   private int suit;
   private int rank;
      
   /**
      The constructor creates a Card object with a supplied suit and rank.
      @param mySuit The suit of the card.
      @param myRank The rank of the card.
   */
   public Card(int mySuit, int myRank){
      suit = mySuit;                                                 // Initialize the rank
      rank = myRank;                                                 // Initialize the suit
   }
   
   /**
      The getSuit method returns the suit of the Card object.
      @return The suit of the card.
   */
   public int getSuit(){
      return suit;
   }
   
   /**
      The getRank method returns the rank of the Card object.
      @return The rank of the card.
   */
   public int getRank(){
      return rank;
   }
   
   /**
      The toString method transforms the properties of the card object to the respective Strings.
      @return A string representing the rank and suit of the card.
   */
   public String toString(){
      String result = "";
      String[] ranks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven",   // Rank string array 
                        "Eight", "Nine", "Ten", "Jack", "Queen", "King"};                                  
      String [] suits = {"Spades", "Clubs", "Hearts", "Diamonds"};               // Suit string array
      
      for(int index = 0; index < ranks.length; index++){                         // Rank test
         if(rank == (index + 1))
            result = ranks[index] + " of ";
      }     
      for(int index = 0; index < suits.length; index++){                         // Suit test
         if(suit == (index + 1))
            result += suits[index];
      }
                     
      return result;
   }
   
   /**
      The equals method compares two cards to determine if the cards are of equal rank.
      @param other The other card.
      @return      A boolean corresponding to whether the two cards are of equal rank (true) or are of 
                   different rank (false).
   */
   public boolean equals(Card other){
      return this.getRank() == other.getRank();
   }
   
   /**
      The isGreater method compares two cards to determine if this card possesses a rank greater than that
      of the other card.
      @param other The other card. 
      @return      A boolean indicating whether or not this card's rank is greater than the other card's rank.
   */
   public boolean isGreater(Card other){
      return this.getRank() > other.getRank();
   }
}