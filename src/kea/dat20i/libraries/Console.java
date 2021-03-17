package kea.dat20i.libraries;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {

  // helper function to display menu and handle user input
  public String getInput(String menuItems, String validInput, String errorMessage) {
    Scanner scan = new Scanner(System.in);
    // build the string
    StringBuilder menu = new StringBuilder();
    Scanner buffer = new Scanner(menuItems);
    buffer.useDelimiter(";"); // delimited by ;
    while(buffer.hasNext()) {
      menu.append(buffer.next()).append('\n');
    }
    menu.append(": ");
    // handles the user input
    System.out.print(menu.toString());
    String input = "";
    try {
      input = scan.next();
      // validate the regex if not empty
      if(!validInput.isEmpty()&&!input.matches(validInput))
        throw new InputMismatchException();
    } catch (InputMismatchException e) {
      //this.clear();
      //this.cursorPosition(0,0);
      System.out.println("Error: "+((errorMessage.isEmpty())?"Invalid input.":errorMessage));
      scan.nextLine();
      input = "";
    }
    return input;
  }

  // ANSI ESCAPE CODES

  // set cursor position to (row, col)
  public void cursorPosition(int row, int col) {
    System.out.print("\033["+row+";"+col+"f");
  }

  // move cursor n rows up
  public void cursorUp(int n) {
    System.out.print("\033["+n+"A");
  }

  // move cursor n rows down
  public void cursorDown(int n) {
    System.out.print("\033["+n+"B");
  }

  // move cursor n columns forward
  public void cursorForward(int n) {
    System.out.print("\033["+n+"C");
  }

  // move cursor n columns backwards
  public void cursorBackwards(int n) {
    System.out.print("\033["+n+"D");
  }

  // clear the console
  public void clear() {
    System.out.print("\033[2J");
  }

  // erase the end of the current row
  public void erase() {
    System.out.print("\033[K");
  }

}
