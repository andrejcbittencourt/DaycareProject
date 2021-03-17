package kea.dat20i.secondsemester.project1;

import kea.dat20i.libraries.*;

public class RDAS {
  public static void main(String[] args) {
    Console console = new Console();
    String username = console.getInput("Please login;Insert username","","");
    System.out.println(username);
  }
}
