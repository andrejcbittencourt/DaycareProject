package kea.dat20i.secondsemester.project1;

import kea.dat20i.libraries.*;

import java.util.ArrayList;

public class RDAS {

  public static void main(String[] args) {
    // manage console input/output
    Console console = new Console();
    // file handler
    FileHandler fileHandler = new FileHandler();
    String fileBasePath = "src/kea/dat20i/secondsemester/project1/data/";
    String fileExtension = ".txt";

    while(true) {
      // welcome
      console.println("Roskilde Daycare Administrative System\n");

      // login
      String inputUsername = console.getInput("Please Login;username", "", "");
      String inputPassword = console.getInput("password", "", "");

      // employee file
      int employeeFile = fileHandler.newFile(fileBasePath + "employees/" + inputUsername + fileExtension); // data/employees/username.txt

      if (fileHandler.fileExists(employeeFile)) { // if file doesn't exist, then the login is invalid
        console.println("one step closer.");
      } else
        console.println("Invalid username and/or password.");
    }
  }
}
