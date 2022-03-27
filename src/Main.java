import java.text.ParseException;

import Console.Console;

public class Main {
  public static void main(String[] args) throws ParseException {

    Console console = new Console("listings_review_date.csv");
    console.menu();

  }
}
