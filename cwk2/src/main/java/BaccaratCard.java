// TODO: Implement the BaccaratCard class in this file
public class BaccaratCard extends Card{
    public BaccaratCard(Card.Rank r, Card.Suit s) {
        super(r, s);
    }

    // change the value method so face cards are worth 0
    @Override
    public int value() {
       if (super.value() == 10) {
           return 0;
       }
       else {
           return super.value();
       }
    }
}
