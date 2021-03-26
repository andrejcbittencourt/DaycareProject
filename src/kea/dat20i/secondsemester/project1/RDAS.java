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
      // load groups & ACL
      buffer = new Scanner(dataHandler.loadFile(groupsData));
      buffer.useDelimiter("\n");
      int index = 0;
      while(buffer.hasNext()) {
        groups.add(new Group(buffer.nextLine()));
        // add documents & permissions
        if(buffer.hasNextLine()) {
          Scanner subBuffer = new Scanner(buffer.nextLine());
          subBuffer.useDelimiter(";");
          while (subBuffer.hasNext()) {
            groups.get(index).addAccessControl(subBuffer.next(), Permission.valueOf(subBuffer.next()));
          }
        }
        // add employees
        if(buffer.hasNextLine()) {
          Scanner subBuffer = new Scanner(buffer.nextLine());
          subBuffer.useDelimiter(";");
          while (subBuffer.hasNext()) {
            groups.get(index).addEmployee(subBuffer.next());
          }
        }
        index++;
      }
      // load parents & children
      buffer = new Scanner(dataHandler.loadFile(parentsData));
      buffer.useDelimiter(";|\n");
      index = 0;
      while(buffer.hasNext()) {
        parents.add(new Parent(buffer.next(),buffer.next(),buffer.next()));
        buffer.nextLine();
        // add children
        if(buffer.hasNextLine()) {
          Scanner subBuffer = new Scanner(buffer.nextLine());
          subBuffer.useDelimiter(";");
          while (subBuffer.hasNext()) {
            parents.get(index).addChild(subBuffer.next(),subBuffer.nextInt());
          }
        }
        index++;
      }
      // load documents
      buffer = new Scanner(dataHandler.loadFile(documentsData));
      buffer.useDelimiter("\n");
      index = 0;
      while(buffer.hasNext()) {
        String docName = buffer.nextLine();
        // columns
        if(buffer.hasNextLine()) {
          Scanner subBuffer = new Scanner(buffer.nextLine());
          subBuffer.useDelimiter(";");
          ArrayList<String> docColumns = new ArrayList<>();
          while (subBuffer.hasNext()) {
            docColumns.add(subBuffer.next());
          }
          documents.add(new Document(docName, docColumns));
        }
        // data
        if(buffer.hasNextLine()) {
          Scanner subBuffer = new Scanner(buffer.nextLine());
          subBuffer.useDelimiter(";");
          ArrayList<String> docData = new ArrayList<>();
          while (subBuffer.hasNext()) {
            docData.add(subBuffer.next());
          }
          documents.get(index).setData(docData);
        }
        index++;
      }
    } catch (Exception e) { // catch file errors
      console.println("Error loading data.");
      if(employees.size()<1) {
        e.printStackTrace();
        System.exit(1);
      }
    }

    // main loop
    while(true) {
      // RDAS
      console.println(name);

      // login input
      String inputUsername = console.getInput("Please Login;username", "", "");
      if(inputUsername.equals("exit;")) // if "exit;" then quit
        break;
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
        menuLoop: while(true) {
          // menu
          String menuOptions = "Menu:;1- Work Schedule;2- Telephone List;3- Parents Information;4- Waiting List;5- Log out";
          String validMenuInput = "[12345]{1}";
          String menuChoice = console.getInput(menuOptions, validMenuInput, "");
          switch (Integer.parseInt(menuChoice)) {
            case 1:
              // TODO: UC1
              break;
            case 2:
              // TODO: UC2
              break;
            case 3:
              // TODO: UC3
              break;
            case 4:
              // TODO: UC4
              break;
            case 5:
              break menuLoop;
          }
        }
        // Store all the data back
        StringBuilder dataToStore = new StringBuilder();
        console.println("Saving data...");
        // parents
        for(Parent parent: parents) {
          dataToStore.append(parent.getName()).append(";");
          dataToStore.append(parent.getPhoneNumber()).append(";");
          dataToStore.append(parent.getAddress()).append("\n");
          ArrayList<String> children = parent.getChildrenName();
          for(String child: children) {
            dataToStore.append(child).append(";");
            dataToStore.append(parent.getChildAge(child)).append(";");
          }
          dataToStore.delete(dataToStore.length()-1,dataToStore.length()).append("\n");
        }
        dataToStore.delete(dataToStore.length()-1,dataToStore.length());
        try {
          dataHandler.saveFile(parentsData, dataToStore.toString());
        } catch(Exception e) {
          console.println("Error saving parents.");
          console.println(dataToStore.toString());
        }
        // documents
        dataToStore = new StringBuilder();
        for(Document doc: documents) {
          dataToStore.append(doc.getName()).append("\n");
          ArrayList<String> columns = doc.getColumns();
          for(String column: columns) {
            dataToStore.append(column).append(";");
          }
          dataToStore.delete(dataToStore.length()-1,dataToStore.length()).append("\n");
          ArrayList<String> docData = doc.getData();
          for(String data: docData) {
            dataToStore.append(data).append(";");
          }
          dataToStore.delete(dataToStore.length()-1,dataToStore.length()).append("\n");
        }
        dataToStore.delete(dataToStore.length()-1,dataToStore.length());
        try {
          dataHandler.saveFile(documentsData, dataToStore.toString());
        } catch(Exception e) {
          console.println("Error saving documents.");
          console.println(dataToStore.toString());
        }
        console.println("done.");
      } else
        console.println("Invalid username/password.");
    }
  }
}
