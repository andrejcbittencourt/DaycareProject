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
    ArrayList<Employee> employees = new ArrayList<>();
    int employeesData = dataHandler.newFile(dataBasePath + "employees" + dataExtension); // employee data file
    ArrayList<Group> groups = new ArrayList<>();
    int groupsData = dataHandler.newFile(dataBasePath + "groups" + dataExtension); // groups data file
    ArrayList<Document> documents = new ArrayList<>();
    int documentsData = dataHandler.newFile(dataBasePath + "documents" + dataExtension); // documents data file
    ArrayList<Parent> parents = new ArrayList<>();
    int parentsData = dataHandler.newFile(dataBasePath + "parents" + dataExtension); // parents data file
    try {
      // load employees
      Scanner buffer = new Scanner(dataHandler.loadFile(employeesData));
      buffer.useDelimiter(";|\n");
      while(buffer.hasNext()) {
        employees.add(new Employee(buffer.next(), buffer.next(), buffer.nextBoolean(), buffer.next()));
      }
      // load groups
      // load parents
    } catch (Exception e) { // catch file errors
      console.println("Error loading data.");
      if(employees.size()<1) {
        e.printStackTrace();
        System.exit(1);
      }
    }

    while(true) {
      // RDAS
      console.println(name);

      // login input
      String inputUsername = console.getInput("Please Login;username", "", "");
      String inputPassword = console.getInput("password", "", "");

      // authenticate
      Employee loginEmployee = null;
      for(Employee employee: employees) {
        if(employee.getUsername().equals(inputUsername))
          if(employee.authenticate(inputUsername, inputPassword)) {
            console.println("Welcome " + employee.getName() + (employee.is_admin()?" (admin)":""));
            loginEmployee = employee;
          }
      }
      if(loginEmployee != null) { // if login was successful
        while(true) {
          // TODO: implement all the UC here

          break;
        }
      } else
        console.println("Invalid username/password.");
    }
  }
}
