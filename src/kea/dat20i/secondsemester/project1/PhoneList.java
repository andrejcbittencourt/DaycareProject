package kea.dat20i.secondsemester.project1;

import kea.dat20i.libraries.Console;
import kea.dat20i.libraries.FileHandler;
import kea.dat20i.libraries.Handler;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class PhoneList {
    Handler h = new FileHandler();
    String source = "src/kea/dat20i/secondsemester/project1/data/parents.dat";
    ArrayList<Parent> parents = new ArrayList<>();
    int parentsData = h.newFile(source);

    ArrayList<String> columns = new ArrayList<>();
    ArrayList<String> data = new ArrayList<>();
    Document doc = new Document("PhoneList.txt",columns);

    Scanner sc = new Scanner(System.in);
    String w = "";
    boolean add = true;


    public void editPhoneList() throws FileNotFoundException {

        Scanner buffer = new Scanner(h.loadFile(parentsData));
        buffer.useDelimiter(";|\n");

        while(buffer.hasNext()) {
            parents.add(new Parent(buffer.next(), buffer.next(), buffer.next()));
            buffer.nextLine();
        }
        System.out.println(parents.toString());

            int pFinder = 3;
            StringBuilder stringColumns = new StringBuilder();
            String p = "";

            for (Parent pa : parents)
            {


                if (pFinder % 3 == 0) {

                    stringColumns.append(pa);
                    stringColumns.append("\t");
                    data.add("-");

                }
                pFinder ++;

            }
            p = stringColumns.toString();
            columns.add(p);

            /*
            StringBuilder stringData = new StringBuilder();
            for (String s : data)
            {
                stringData.append(s);
                stringData.append("\t");
            }
            h.saveFile(1,stringData.toString());
            */




        /*
        h.newFile("src/kea/dat20i/secondsemester/project1/data/ParentColumns.txt");
        h.newFile("src/kea/dat20i/secondsemester/project1/data/PhoneListData.txt");

        Scanner c = new Scanner(new File("src/kea/dat20i/secondsemester/project1/data/ParentColumns.txt"));
        Scanner d = new Scanner(new File("src/kea/dat20i/secondsemester/project1/data/PhoneListData.txt"));


        if(!( h.fileExists(1))){
            while (c.hasNext()){
                columns.add(c.next());
                data.add("-");
            }
            c.close();

            StringBuilder stringData = new StringBuilder();
            for (String s : data)
            {
                stringData.append(s);
                stringData.append("\t");
            }
            h.saveFile(1,stringData.toString());

        }else{
            while (c.hasNext()){
                columns.add(c.next());
            }
            c.close();

            while (d.hasNext()){
                data.add(d.next());
            }
            d.close();
        }*/
        doc.setData(data);
        doc.display(20, 1);

        int count = data.size() - columns.size();


        while(add) {



            for (int x = count; x <= data.size() -1; x++) {
                data.set(x, "Input ?");

                doc.setData(data);
                doc.display(20, 1);

                System.out.println("");
                System.out.println("");
                System.out.println("");
                System.out.println("Please enter your input");
                System.out.println("Note that when a ''space'' is inserted a next cell will be written");
                System.out.println("");
                System.out.println("");

                w = sc.next();
                data.set(x, w);


                StringBuilder stringData = new StringBuilder();
                for (String s : data) {
                    stringData.append(s);
                    stringData.append("\t");
                }
                h.saveFile(1, stringData.toString());
            }

            doc.setData(data);
            doc.display(20, 1);

            String option = "";
            while(!(option.equals("y") | option.equals("n"))) {
                System.out.println("Would you like to add more phone numbers ?");
                System.out.println("y - yes");
                System.out.println("n - no");
                option = sc.next();
                switch (option) {
                    case "y":
                        for (int x = 0; x <= columns.size() - 1; x++) {
                            data.add("-");
                        }


                        count = count + columns.size();


                        break;

                    case "n":

                        add = false;
                        break;

                    default:
                        System.out.println("Please enter one of the options,capital letters are not included");
                        break;
                }
            }

        }


    }


    /*public void seePhoneList() throws FileNotFoundException {

    }*/
}
class test{
    public static void main(String []args) throws FileNotFoundException {
        PhoneList p = new PhoneList();
        p.editPhoneList();
    }

}