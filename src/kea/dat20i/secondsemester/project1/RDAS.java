package kea.dat20i.secondsemester.project1;

import kea.dat20i.libraries.*;


import java.util.ArrayList;
import java.util.Scanner;

public class RDAS {

  public static void main(String[] args){
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
        Scanner subBuffer = new Scanner(buffer.nextLine());
        subBuffer.useDelimiter(";");
        ArrayList<String> docColumns = new ArrayList<>();
        while (subBuffer.hasNext()) {
          docColumns.add(subBuffer.next());
        }
        documents.add(new Document(docName, docColumns));
        // data
        if(buffer.hasNextLine()) {
          subBuffer = new Scanner(buffer.nextLine());
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
      String inputUsername = console.getInput("Please Login;Username", "", "");
      if(inputUsername.equals("exit;")) // if "exit;" then quit
        break;
      String inputPassword = console.getInput("Password", "", "");

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
          String menuOptions = "Menu:;1- Work Schedule;2- Telephone List;3- Waiting List;4- Parents Information;5- Log out";
          String validMenuInput = "[12345]{1}";
          String menuChoice = console.getInput(menuOptions, validMenuInput, "");
          switch (menuChoice) {
            case "1":
              // UC1 - work schedule
              int scheduleListIndex = 0;
              for (Document document: documents){
                if (document.getName().equals("workSchedule")){
                  break;
                }
                scheduleListIndex++;
              }
              // check if employee has permission to view the document
              Permission wSPermission = Permission.NONE;
              for(Group group: groups) {
                wSPermission = group.getPermission(documents.get(scheduleListIndex).getName(),loginEmployee.getUsername());
                if(wSPermission==Permission.EDITOR||wSPermission==Permission.VIEWER)
                  break;
              }
              if(wSPermission==Permission.EDITOR||wSPermission==Permission.VIEWER) {
                documents.get(scheduleListIndex).display(20, 1);

                // check if employee has permission to edit the document
                for(Group group: groups) {
                  wSPermission = group.getPermission(documents.get(scheduleListIndex).getName(),loginEmployee.getUsername());
                  if(wSPermission==Permission.EDITOR)
                    break;
                }
                if(wSPermission==Permission.EDITOR) { // EDITOR

                  // select day and hour
                  String scheduleMenuChoice = console.getInput("Choose an action;1- Edit;2- Go back", "[12]{1}", "");
                  if (scheduleMenuChoice.equals("1")) {

                    scheduleLoop:
                    while (true) {
                      String scheuleValidInput = "[";
                      for (int i = 0; i < documents.size(); i++)
                        scheuleValidInput += i + 1;
                      scheuleValidInput += "]{1}";
                      String selectedDay = console.getInput("Which column", scheuleValidInput, "");
                      String selectedHour = console.getInput("Which row", scheuleValidInput, "");


                      if (!selectedDay.isEmpty() & !selectedHour.isEmpty()) {
                        int selectedCell = (Integer.parseInt(selectedHour) - 1) * documents.get(scheduleListIndex).getColumns().size() + (Integer.parseInt(selectedDay) - 1);
                        console.println("cell: " + documents.get(scheduleListIndex).getData().get(selectedCell));

                        scheduleMenuChoice = console.getInput("Choose an action;1- Confirm;2- Go back", "[1234]{1}", "");
                        switch (scheduleMenuChoice) {
                          case "1":
                            // edit schedule

                            String newSchedule = console.getInput("Insert new activity", "", "");

                            ArrayList<String> cellData = documents.get(scheduleListIndex).getData();
                            cellData.set(selectedCell, newSchedule);

                            documents.get(scheduleListIndex).setData(cellData);
                            break scheduleLoop;

                          case "2": // go back
                            break scheduleLoop;
                        }
                      }
                    }
                  } else if (scheduleMenuChoice.equals("2"))
                    break;
                } else {
                  console.println("You don't have permission to edit this document.");
                  break;
                }
              } else
                console.println("You don't have permission to access this document.");
              break;

            case "2":
              // UC2 - phone list

              // show phone list
              ArrayList<String> phoneTmp = new ArrayList<>();
              phoneTmp.add("Name");
              phoneTmp.add("Phone Number");

              Document phoneList = new Document("phoneList", phoneTmp);
              phoneTmp = new ArrayList<>();
              for(Parent parent: parents) {
                phoneTmp.add(parent.getName());
                phoneTmp.add(parent.getPhoneNumber());

              }
              phoneList.setData(phoneTmp);

              // check if employee has permission to view the document
              Permission pLPermission = Permission.NONE;
              for(Group group: groups) {
                pLPermission = group.getPermission(phoneList.getName(),loginEmployee.getUsername());
                if(pLPermission==Permission.EDITOR||pLPermission==Permission.VIEWER)
                  break;
              }
              if(pLPermission==Permission.EDITOR||pLPermission==Permission.VIEWER) {
                phoneList.display(20, 1);

                // check if employee has permission to edit the document
                for(Group group: groups) {
                  pLPermission = group.getPermission(phoneList.getName(),loginEmployee.getUsername());
                  if(pLPermission==Permission.EDITOR)
                    break;
                }
                if(pLPermission==Permission.EDITOR) { // EDITOR
                  // select number
                  String phoneMenuChoice = console.getInput("Choose an action;1- Edit;2- Go back", "[12]{1}", "");
                  if (phoneMenuChoice.equals("1")) {
                    phoneLoop:
                    while (true) {
                      String phoneValidInput = "[";
                      for (int i = 0; i < parents.size(); i++)
                        phoneValidInput += i + 1;
                      phoneValidInput += "]{1}";
                      String selectedParent = console.getInput("Which parent", phoneValidInput, "");
                      console.println("Phone number: " + parents.get(Integer.parseInt(selectedParent) - 1).getPhoneNumber());

                      if (!selectedParent.isEmpty()) {
                        phoneMenuChoice = console.getInput("Choose an action;1- Confirm;2- Go back", "[1234]{1}", "");
                        switch (phoneMenuChoice) {
                          case "1":
                            // edit phone number
                            String newPhoneNumber = console.getInput("Insert new phone number", "", "");

                            parents.get(Integer.parseInt(selectedParent) - 1).setPhoneNumber(newPhoneNumber);
                            break phoneLoop;

                          case "2": // go back
                            break phoneLoop;
                        }
                      }
                    }
                  } else if (phoneMenuChoice.equals("2"))
                    break;
                } else {
                  console.println("You don't have permission to edit this document.");
                  break;
                }
              } else
                console.println("You don't have permission to access this document.");
              break;

            case "3":
              // UC3 - Waiting List
              int index = 0;
              for(Document document: documents) {
                if(document.getName().equals("waitingList"))
                  break;
                index++;
              }
              // check if employee has permission to view the document
              Permission wLPermission = Permission.NONE;
              for(Group group: groups) {
                wLPermission = group.getPermission(documents.get(index).getName(),loginEmployee.getUsername());
                if(wLPermission==Permission.EDITOR||wLPermission==Permission.VIEWER)
                  break;
              }
              if(wLPermission==Permission.EDITOR||wLPermission==Permission.VIEWER) { // VIEWER or EDITOR
                while (true) {
                  documents.get(index).display(20, 1); // display the document
                  // check if employee has permission to edit the document
                  for(Group group: groups) {
                    wLPermission = group.getPermission(documents.get(index).getName(),loginEmployee.getUsername());
                    if(wLPermission==Permission.EDITOR)
                      break;
                  }
                  if(wLPermission==Permission.EDITOR) { // EDITOR
                    String wLMenuChoice = console.getInput("Choose an action;1- Add;2- Remove;3- Go Back", "[123]{1}", "");
                    if (wLMenuChoice.equals("1")) { // add to list
                      String inputName = console.getInput("Insert the parent's full name", "", "");
                      String inputPhoneNumber = console.getInput("Insert the parent's phone number", "", "");
                      String inputAddress = console.getInput("Insert the parent's address", "", "");
                      String inputChildName = console.getInput("Insert the child's name", "", "");
                      String inputChildAge = console.getInput("Insert the child's age", "", "");
                      ArrayList<String> tmp = documents.get(index).getData();
                      tmp.add(inputName);
                      tmp.add(inputPhoneNumber);
                      tmp.add(inputAddress);
                      tmp.add(inputChildName);
                      tmp.add(inputChildAge);
                      documents.get(index).setData(tmp);
                    } else if (wLMenuChoice.equals("2")) { // remove from list
                      if (documents.get(index).getData().size() > 0) {
                        while (true) {
                          String validInput = "[";
                          for (int i = 0; i < documents.get(index).getData().size() / documents.get(index).getColumns().size(); i++) {
                            validInput += i + 1;
                          }
                          validInput += "]{1}";
                          String inputToRemove = console.getInput("Insert the row # to delete", validInput, "");
                          if (!inputToRemove.isEmpty()) {
                            ArrayList<String> tmp = documents.get(index).getData();
                            for (int i = 0; i < documents.get(index).getColumns().size(); i++) {
                              tmp.remove((Integer.parseInt(inputToRemove) - 1) * documents.get(index).getColumns().size());
                            }
                            documents.get(index).setData(tmp);
                            break;
                          }
                        }
                      } else
                        console.println("No data to remove.");
                    } else if (wLMenuChoice.equals("3")) // go back
                      break;
                  } else {
                    console.println("You don't have permission to edit this document.");
                    break;
                  }
                }
              } else
                console.println("You don't have permission to access this document.");
              break;
            case "4":
              // UC4 - parents information
              if(loginEmployee.is_admin()) { // only admin
                while(true) {
                  // show parents list
                  ArrayList<String> parentsTmp = new ArrayList<>();
                  parentsTmp.add("Name");
                  parentsTmp.add("Phone Number");
                  parentsTmp.add("Address");
                  parentsTmp.add("# of children");
                  Document parentsList = new Document("list", parentsTmp);
                  parentsTmp = new ArrayList<>();
                  for(Parent parent: parents) {
                    parentsTmp.add(parent.getName());
                    parentsTmp.add(parent.getPhoneNumber());
                    parentsTmp.add(parent.getAddress());
                    ArrayList<String> nChildren = parent.getChildrenName();
                    parentsTmp.add(String.valueOf(nChildren.size()));
                  }
                  parentsList.setData(parentsTmp);
                  parentsList.display(20, 1);
                  // select parent
                  String pMenuChoice = console.getInput("Choose an action;1- Edit;2- Go back","[12]{1}","");
                  if(pMenuChoice.equals("1")) {
                    parentsLoop: while(true) {
                      String parentsValidInput = "[";
                      for(int i = 0; i < parents.size(); i++)
                        parentsValidInput += i+1;
                      parentsValidInput += "]{1}";
                      String selectedParent = console.getInput("Which parent", parentsValidInput,"");
                      if(!selectedParent.isEmpty()) {
                        pMenuChoice = console.getInput("Choose an action;1- Edit parent;2- Add child;3- Edit child;4- Go back", "[1234]{1}", "");
                        switch (pMenuChoice) {
                          case "1":
                            // edit parent info
                            console.println("Name: "+parents.get(Integer.parseInt(selectedParent)-1).getName());
                            String newName = console.getInput("Insert new name","","");
                            console.println("Phone number: "+parents.get(Integer.parseInt(selectedParent)-1).getPhoneNumber());
                            String newPhoneNumber = console.getInput("Insert new phone number","","");
                            console.println("Address: "+parents.get(Integer.parseInt(selectedParent)-1).getAddress());
                            String newAddress = console.getInput("Insert new address","","");
                            parents.get(Integer.parseInt(selectedParent)-1).setName(newName);
                            parents.get(Integer.parseInt(selectedParent)-1).setPhoneNumber(newPhoneNumber);
                            parents.get(Integer.parseInt(selectedParent)-1).setAddress(newAddress);
                            break parentsLoop;
                          case "2":
                            // show children and add a new one
                            parentsTmp = new ArrayList<>();
                            parentsTmp.add("Name");
                            parentsTmp.add("Age");
                            parentsList = new Document("list", parentsTmp);
                            parentsTmp = new ArrayList<>();
                            for(String child: parents.get(Integer.parseInt(selectedParent)-1).getChildrenName()) {
                              parentsTmp.add(child);
                              parentsTmp.add(String.valueOf(parents.get(Integer.parseInt(selectedParent)-1).getChildAge(child)));
                            }
                            parentsList.setData(parentsTmp);
                            parentsList.display(20, 1);
                            String childName = console.getInput("Insert name of the new child","","");
                            String childAge = console.getInput("Insert the age of the new child","[1234567890]+","");
                            if(!childAge.isEmpty())
                              parents.get(Integer.parseInt(selectedParent)-1).addChild(childName, Integer.parseInt(childAge));
                            else
                              console.println("Error adding new child.");
                            break parentsLoop;
                          case "3":
                            // show and edit children
                            parentsTmp = new ArrayList<>();
                            parentsTmp.add("Name");
                            parentsTmp.add("Age");
                            parentsList = new Document("list", parentsTmp);
                            parentsTmp = new ArrayList<>();
                            int childIndex = 0;
                            for(String child: parents.get(Integer.parseInt(selectedParent)-1).getChildrenName()) {
                              parentsTmp.add(child);
                              parentsTmp.add(String.valueOf(parents.get(Integer.parseInt(selectedParent)-1).getChildAge(child)));
                              childIndex++;
                            }
                            parentsList.setData(parentsTmp);
                            parentsList.display(20, 1);

                            while(true) { // edit child
                              String validChildInput = "[";
                              for(int i=0; i < childIndex; i++) {
                                validChildInput += i+1;
                              }
                              validChildInput += "]{1}";
                              String selectedChild = console.getInput("Which child", validChildInput, "");
                              if(!selectedChild.isEmpty()) { // valid choice
                                String childOldName = parents.get(Integer.parseInt(selectedParent)-1).getChildrenName().get(Integer.parseInt(selectedChild)-1);
                                console.println("Name: "+childOldName);
                                String childNewName = console.getInput("Insert new name","","");
                                console.println("Age: "+parents.get(Integer.parseInt(selectedParent)-1).getChildAge(childOldName));
                                String childNewAge = console.getInput("Insert new age","[1234567890]+","");
                                parents.get(Integer.parseInt(selectedParent)-1).setChildName(Integer.parseInt(selectedChild)-1, childNewName);
                                parents.get(Integer.parseInt(selectedParent)-1).setChildAge(Integer.parseInt(selectedChild)-1, Integer.parseInt(childNewAge));
                                break;
                              }
                            }
                            break parentsLoop;
                          case "4": // go back
                            break parentsLoop;
                        }
                      }
                    }
                  } else if(pMenuChoice.equals("2"))
                    break;
                }
              } else
                console.println("Access denied: not admin.");
              break;
            case "5":
              break menuLoop; // log out
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
