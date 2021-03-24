package kea.dat20i.secondsemester.project1;

import kea.dat20i.libraries.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RDAS {

  public static void main(String[] args) {
    // manage console input/output
    Console console = new Console();

    // data handler
    FileHandler dataHandler = new FileHandler();
    String dataBasePath = "src/kea/dat20i/secondsemester/project1/data/";
    String dataExtension = ".dat";

    // initialize data
    String name = "Roskilde Daycare Administrative System"; // program name
    // load employees
    ArrayList<Employee> employees = new ArrayList<>();
    try {
      int employeesData = dataHandler.newFile(dataBasePath + "employees" + dataExtension);
      Scanner buffer = new Scanner(dataHandler.loadFile(employeesData));
      buffer.useDelimiter(";|\n");
      while(buffer.hasNext()) {
        employees.add(new Employee(buffer.next(), buffer.next(), buffer.nextBoolean(), buffer.next()));
      }
    } catch (Exception e) {
      console.println("Error loading data.");
      e.printStackTrace();
      System.exit(1);
    }

    while(true) {
      // print name
      console.println(name);

      // login
      String inputUsername = console.getInput("Please Login;username", "", "");
      String inputPassword = console.getInput("password", "", "");

      Employee loginEmployee = null;
      for(Employee employee: employees) {
        if(employee.getUsername().equals(inputUsername))
          if(employee.authenticate(inputUsername, inputPassword)) {
            console.println("Welcome " + employee.getName() + (employee.is_admin()?" (admin)":""));
            loginEmployee = employee;
          }
      }
      if(loginEmployee != null) {
        while(true) {
          // TODO: implement all the UC here
          break;
        }
      } else
        console.println("Invalid username/password.");
    }
  }
}
