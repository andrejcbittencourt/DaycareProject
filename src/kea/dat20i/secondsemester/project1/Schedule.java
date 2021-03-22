package kea.dat20i.secondsemester.project1;

import kea.dat20i.libraries.FileHandler;
import kea.dat20i.libraries.Handler;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Schedule {
    ArrayList<String> columns = new ArrayList<>();
    ArrayList<String> data = new ArrayList<>();
    Document doc = new Document("Schedule.txt",columns);
    Scanner sc = new Scanner(System.in);
    String w = "";
    Handler h = new FileHandler();


    public void editSchedule() throws FileNotFoundException {
        h.newFile("Columns");
        h.newFile("Data");


        if(!(h.fileExists(0) & h.fileExists(1))){


        columns.add("Time(h)");
        columns.add("Monday");
        columns.add("Tuesday");
        columns.add("Wednesday");
        columns.add("Thursday");
        columns.add("Friday");
        columns.add("Saturday");
        columns.add("Sunday");


        data.add("07:00");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");
        data.add("08:00");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");
        data.add("09:00");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");
        data.add("10:00");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");
        data.add("11:00");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");
        data.add("12:00");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");
        data.add("13:00");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");
        data.add("14:00");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");data.add("-");




            StringBuilder stringColumns = new StringBuilder();
            for (String s : columns)
            {
                stringColumns.append(s);
                stringColumns.append("\t");
            }
            h.saveFile(0,stringColumns.toString());


            StringBuilder stringData = new StringBuilder();
            for (String s : data)
            {
                stringData.append(s);
                stringData.append("\t");
            }
            h.saveFile(1,stringData.toString());


        }else{
            Scanner c = new Scanner(new File("columns"));
            Scanner d = new Scanner(new File("data"));

            while (c.hasNext()){
                columns.add(c.next());
            }
            c.close();

            while (d.hasNext()){
                data.add(d.next());
            }
            d.close();
        }

            doc.setData(data);
            doc.display(20, 1);





        for (int x = 0; x <= 64; x++){
            if (!(x == 0 | x == 8 | x == 16 | x == 24 | x == 32 | x == 40 | x == 48 | x == 56)){
                data.set(x,"?");

                doc.setData(data);
                doc.display(20, 1);

                w = sc.next();
                data.set(x,w);



                StringBuilder stringData = new StringBuilder();
                for (String s : data)
                {
                    stringData.append(s);
                    stringData.append("\t");
                }
                h.saveFile(1,stringData.toString());
            }
        }



}
public void seeSchedule() throws FileNotFoundException {
    Scanner c = new Scanner(new File("columns"));
    Scanner d = new Scanner(new File("data"));

    while (c.hasNext()){
        columns.add(c.next());
    }
    c.close();

    while (d.hasNext()){
        data.add(d.next());
    }
    d.close();


            doc.setData(data);
            doc.display(20, 1);
}
}
class Test {
    public static void main(String[] args) throws FileNotFoundException {
        Schedule s = new Schedule();
        //s.editSchedule();
        s.seeSchedule();
    }

}
