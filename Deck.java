/**
   The Deck class represents an array of Card objects and provides the ability to create a 52 card deck. In
   addition, the class provides the functionality to shuffle and remove cards.
   @author Ben Sherr 
*/

import java.util.ArrayList;                                                // Needed for the ArrayList class
import java.util.Random;                                                   // Needed for the Random class

public class Deck{
   private ArrayList<Card> deck;
   private static final int NUM_CARDS = 52;
   
   /**
      Default Constructor, constructs a full deck of Card objects.
   */
   public Deck(){
      newDeck();
   }
   
   /**
      The newDeck method creates a standard 52 card deck of Card objects.
   */
   public void newDeck(){     
      deck = new ArrayList<Card> (NUM_CARDS);
      for(int suit = Card.SPADES; suit <= Card.DIAMONDS; suit++){
         for(int rank = 2; rank <= Card.ACE; rank++){
            deck.add(new Card(suit, rank));
         }// rank loop
      }// suit loop
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
      creation and successful shuffle.
   */
   public void displayAll(){
      for(int index = 0; index < deck.size(); index++)
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
      The remove method removes a Card at the top of the deck.
      @return      The card at the top of the deck.
   */
   public Card remove(){
      return deck.remove(0);
   }
   
   /**
      The emptyDeck method clears all cards from the deck.
   */
   public void emptyDeck(){
      deck.clear();
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
      return (deck.size() == 0);
   }
   
   // Tester
   public static void main(String [] args){
      Deck d1 = new Deck();
      d1.displayAll();
      d1.emptyDeck();
      System.out.println("------");
      d1.displayAll();
   }
}