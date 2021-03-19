package kea.dat20i.secondsemester.project1;

import kea.dat20i.libraries.*;

import java.util.ArrayList;

public class RDAS {

  public static void main(String[] args) {
    /*Console console = new Console();
    String username = console.getInput("Please Login;username","","");
    System.out.println(username);
    String password = console.getInput("password","","");
    System.out.println(password);*/

    // test area
    ArrayList<String> columns = new ArrayList<>();
    columns.add("Time");
    columns.add("Monday");
    columns.add("Tuesday");
    columns.add("Wednesday");
    columns.add("Thursday");
    columns.add("Friday");
    columns.add("Saturday");
    columns.add("Sunday");
    Document doc = new Document("workSchedule.txt", columns);
    ArrayList<String> data = new ArrayList<>();
    data.add("08:00");
    data.add("Software Design");
    data.add("Programming");
    data.add("ITO");
    data.add("Technology");
    data.add("Programming");
    data.add("Free");
    data.add("Free");
    doc.setData(data);
    doc.display();
  }
}
