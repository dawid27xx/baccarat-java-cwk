// TODO: Implement the BaccaratHand class in the file
public class BaccaratHand extends CardCollection {
    public BaccaratHand(){}

    // check for natural
    public boolean isNatural() {
        boolean natural = false;
        int value = value();
        if ((value == 8 || value == 9) && cards.size() == 2) {
            natural = true;
        }
    return natural;
    }

    // toString method
    public String toString() {
        if (cards.isEmpty()) {
            return "";
        }

        String string = "";
        for (Card card: cards) {
            string += card.getRank().getSymbol();
            string += card.getSuit().getSymbol();
            string += " ";

        }
        string = string.substring(0, string.length()-1);

        return string;
    }

    // correct for the value to not allow values larger than 10
    @Override public int value() {
        int sum = 0;
        for (Card card: cards) {
            sum += card.value();
        }
        while (sum > 9) {
            sum -= 10;
        }
        return sum;
    }
}