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
    columns.add("Column 1");
    columns.add("Column 2");
    columns.add("Column 3");
    columns.add("Column 4");
    columns.add("Column 5");
    columns.add("Column 6");
    columns.add("Column 7");
    columns.add("Column 8");
    Document doc = new Document("workSchedule.txt", columns);
    ArrayList<String> data = new ArrayList<>();
    data.add("08:00");
    data.add("Software Design");
    data.add("Programming");
    data.add("ITO");
    data.add("Technology");
    data.add("Programming - this class is canceled on week 17");
    data.add("Free");
    data.add("Free");
    data.add("09:00");
    data.add("Software Design");
    data.add("Programming");
    data.add("ITO");
    data.add("Technology");
    data.add("Programming");
    data.add("Free");
    data.add("Free");
    doc.setData(data);
    doc.display(15, 5);
  }
}
