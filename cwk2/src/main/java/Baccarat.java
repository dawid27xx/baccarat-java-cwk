import java.util.Objects;
import java.util.Scanner;

public class Baccarat {
  public static void main(String[] args) {

      if (args.length == 0) {
          playNormal();
      }
      if (args.length == 1) {
          playInteractive();
      }
  }
  public static void playNormal() {
      // round counter and play variable
      int round = 0;
      int playerWon = 0;
      int bankerWon = 0;
      int tie = 0;

      // create a shoe and shuffle it
      Shoe shoe = new Shoe(6);
      shoe.shuffle();

      // create hands for banker and player
      BaccaratHand banker = new BaccaratHand();
      BaccaratHand player = new BaccaratHand();

      // game loop
      // check if continue playing, and shoe size larger than 6
      while (shoe.size() > 6) {

          // draw cards
          banker.add(shoe.deal());
          player.add(shoe.deal());
          banker.add(shoe.deal());
          player.add(shoe.deal());

          // display round number
          System.out.println("Round " + (round+1));

          // show card values
          System.out.println("Player: " + player + " = " + player.value());
          System.out.println("Banker: " + banker + " = " + banker.value());

          // players rule
          // draw another if value < 6
          if (player.value() < 6) {
              player.add(shoe.deal());
              System.out.println("Dealing third card to player...");
              System.out.println("Player: " + player + " = " + player.value());
              System.out.println("Banker: " + banker + " = " + banker.value());
          }

          // bankers rule

          boolean drawThird = bankerRule(banker, player);
          if (drawThird) {
              banker.add(shoe.deal());
              System.out.println("Dealing third card to banker...");
              System.out.println("Player: " + player + " = " + player.value());
              System.out.println("Banker: " + banker + " = " + banker.value());
          }

          // check win
          int won = checkWin(banker, player);
          switch (won) {
              case 1 -> playerWon += 1;
              case 2 -> bankerWon += 1;
              case 3 -> tie += 1;
          }

          // clear hands
          player.discard();
          banker.discard();

          // add one to counter
          round += 1;
      }

      // exit stats
      System.out.println("End of game stats:");
      System.out.println(round + " rounds played");
      System.out.println(playerWon + " player wins");
      System.out.println(bankerWon + " banker wins");
      System.out.println(tie + " ties");
  }

  public static void playInteractive() {

      // round counter and play variable
      int round = 0;
      int playerWon = 0;
      int bankerWon = 0;
      int tie = 0;
      boolean play = true;

      // create a shoe and shuffle it
      Shoe shoe = new Shoe(6);
      shoe.shuffle();

      // create hands for banker and player
      BaccaratHand banker = new BaccaratHand();
      BaccaratHand player = new BaccaratHand();

      // game loop
      // check if continue playing, and shoe size larger than 6
      while (play && shoe.size() > 6) {

          // draw cards
          banker.add(shoe.deal());
          player.add(shoe.deal());
          banker.add(shoe.deal());
          player.add(shoe.deal());

          // display round number
          System.out.println("Round " + (round+1));

          // show card values
          System.out.println("Player: " + player + " = " + player.value());
          System.out.println("Banker: " + banker + " = " + banker.value());

          // players rule
          // draw another if value < 6
          if (player.value() < 6) {
              player.add(shoe.deal());
              System.out.println("Dealing third card to player...");
              System.out.println("Player: " + player + " = " + player.value());
              System.out.println("Banker: " + banker + " = " + banker.value());
          }

          // bankers rule

          boolean drawThird = bankerRule(banker, player);
          if (drawThird) {
              banker.add(shoe.deal());
              System.out.println("Dealing third card to banker...");
              System.out.println("Player: " + player + " = " + player.value());
              System.out.println("Banker: " + banker + " = " + banker.value());
          }

          // check win
          int won = checkWin(banker, player);
          switch (won) {
              case 1 -> playerWon += 1;
              case 2 -> bankerWon += 1;
              case 3 -> tie += 1;
          }

          // clear hands
          player.discard();
          banker.discard();

          // add one to counter
          round += 1;

          // ask player if to continue
          Scanner scanner = new Scanner(System.in);
          System.out.println("Another round? (y/n): ");
          String continueGame = scanner.nextLine();
          char first_letter = continueGame.charAt(0);
          char lower_case_letter = Character.toLowerCase(first_letter);

          if (!Objects.equals(lower_case_letter, 'y')) {
              play = false;
          }
      }

      // exit stats
      System.out.println(round + " rounds played");
      System.out.println(playerWon + " player wins");
      System.out.println(bankerWon + " banker wins");
      System.out.println(tie + " ties");
  }
  public static boolean bankerRule (BaccaratHand banker, BaccaratHand player) {
      boolean draw = banker.value() < 6 && player.size() == 2;

      // bankers rule if player has 3 cards
      if (player.size() == 3) {
          if (banker.value() < 3 || banker.value() == 3 && player.cards.get(2).value() != 8 ||
              banker.value() == 4 && player.cards.get(2).value() > 1
              && player.cards.get(2).value() < 8 ||
              banker.value() == 5 && player.cards.get(2).value() > 3
              && player.cards.get(2).value() < 8 ||
              banker.value() == 6 && player.cards.get(2).value() > 5
              && player.cards.get(2).value() < 8) {
              draw = true;
          }
      }
      return draw;
  }
  public static int checkWin (BaccaratHand banker, BaccaratHand player) {
      if (player.value() > banker.value()) {
          System.out.println("Player win!");
          return 1;
      }

      if (banker.value() > player.value()) {
          System.out.println("Banker win!");
          return 2;
      }

      if (banker.value() == player.value()) {
          System.out.println("Tie");
          return 3;
      }
      return 0;
  }
}
