package kea.dat20i.secondsemester.project1;

import kea.dat20i.libraries.*;

import java.util.ArrayList;

public class RDAS {

  public static void main(String[] args) {
    // manage console input/output
    Console console = new Console();
    // file handler
    FileHandler fileHandler = new FileHandler();

    while(true) {
      // welcome
      console.println("Roskilde Daycare Administrative System\n");

      // login
      String username = console.getInput("Please Login;username", "", "");
      String password = console.getInput("password", "", "");

      // test area
      ArrayList<String> columns = new ArrayList<>();
      columns.add("Time [hh:mm]");
      columns.add("Monday");
      columns.add("Tuesday");
      columns.add("Wednesday");
      columns.add("Thursday");
      columns.add("Friday");
      columns.add("Saturday");
      columns.add("Sunday");
      Document doc = new Document("Work Schedule", columns);
      ArrayList<String> data = new ArrayList<>();
      data.add("08:00");
      data.add("Software Design");
      data.add("Programming");
      data.add("ITO");
      data.add("Technology");
      data.add("Programming");
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
      doc.display(15, 1);
    }
  }
}
