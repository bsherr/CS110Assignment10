/**
   The Deck class represents an array of Card objects and provides the ability to create a 52 card deck. In
   addition, the class provides the functionality to shuffle and remove cards.
   @author Ben Sherr 
*/

import java.util.ArrayList;                                                // Needed for the ArrayList class
import java.util.Collections;
import java.util.Random;                                                   // Needed for the Random class

public class Deck extends ArrayList{
   private ArrayList<Card> deck;
   private final int NUM_CARDS = 52;
   
   /**
      Default Constructor, creates an empty card deck of Card objects.
   */
   public Deck(){
      deck = new ArrayList<Card> ();
   }
   
   /**
      Alternate Constructor, creates a standard 52 card deck of Card objects if the boolean argument is true.
   */
   public Deck(boolean fill){
      if(fill){      
         deck = new ArrayList<Card> (NUM_CARDS);
         for(int index = 0; index < NUM_CARDS; index++){
            for(int suit = Card.SPADES; suit <= Card.DIAMONDS; suit++){
               for(int rank = Card.ACE; rank <= Card.KING; rank++){
                  deck.add(new Card(suit, rank));
                  index++;
               }// rank loop
            }// suit loop
         }// deck loop
      }// if loop
   }
   
   /**
      The shuffle method shuffles the contents of this card deck.
   */
   public void shuffle(){
      Random rand = new Random();
      for(int index = 0; index < deck.size(); index++){
         int randIndex = rand.nextInt(deck.size());                        // Random location within the deck
         Card randCard = deck.get(randIndex);                              // Card at random location
         deck.set(randIndex, deck.get(index));                             // Set card at random to card at index
         deck.set(index, randCard);                                        // Set card at index to original random
      }
   }
   
   /**
      The displayAll method prints each card in the deck, provides ability to validate successful deck
      creation and succeesful shuffle.
   */
   public void displayAll(){
      for(int index = 1; index < deck.size(); index++)
         System.out.println(deck.get(index));
   }
   
   /**
      The get method returns the card at the specified index.
      @param index An integer location within the list.
      @return      The card at the specified location.
   */
   public Card get(int index){
      return deck.get(index);
   }
   
   /**
      The add method adds a Card to the end of the deck.
      @param c The card to be added to the end of the deck.
   */
   public void add(Card c){
      deck.add(c);
   }
     
   /**
      The remove method removes a Card at the specified index.
      @param index The index within the deck.
      @return      The card at index.
   */
   public Card remove(int index){
      return deck.remove(index);
   }
   
   /**
      The size method returns the number of cards in the deck.
      @return The number of cards in the deck.
   */
   public int size(){
      return deck.size();
   }
   
   /**
      The isEmpty method returns a boolean indicating whether or not the deck is empty.
      @return A boolean indicating if the deck is empty (false) or not (true).
   */
   public boolean isEmpty(){
      return deck.isEmpty();
   }
}