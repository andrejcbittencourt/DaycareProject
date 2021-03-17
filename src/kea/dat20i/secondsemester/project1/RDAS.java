package kea.dat20i.secondsemester.project1;

import kea.dat20i.libraries.*;

import java.util.ArrayList;

public class RDAS {

  public static void loadEmployees() {

  }

  public static void main(String[] args) {
    Console console = new Console();
    String username = console.getInput("Please Login;username","","");
    System.out.println(username);
    String password = console.getInput("password","","");
    System.out.println(password);
  }
}
