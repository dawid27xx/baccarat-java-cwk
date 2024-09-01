import java.util.LinkedList;

// TODO: Implement the Shoe class in this file
public class Shoe extends CardCollection {

    public Shoe(int deckN) throws CardException {

        // check for correct number of decks
        if (deckN != 6 && deckN != 8) {
            throw new CardException("Wrong amount of decks.\n");
        }

        //create a list of card from 6 or 8 decks
        cards = new LinkedList<>();

        // fill out the list with cards
        for (int i = 0; i < deckN; i++) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    cards.add(new BaccaratCard(rank, suit));
                }
            }
        }
    }

    // shuffle card using java collection
    public void shuffle() {
        java.util.Collections.shuffle(cards);
    }

    // deal a card then remove it
    public Card deal() {
        if (cards.isEmpty()) {
            throw new CardException("Shoe is empty.\n");
        }
        Card cardDealt = cards.get(0);
        cards.remove(0);
        return cardDealt;
}
}