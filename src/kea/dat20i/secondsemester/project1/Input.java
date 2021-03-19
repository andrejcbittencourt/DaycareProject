package kea.dat20i.secondsemester.project1;


import kea.dat20i.libraries.FileHandler;
import kea.dat20i.libraries.Handler;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {


    ArrayList<String> data = new ArrayList<String>();

    public void seeInput(){


        data.add("|Time(h)|");
        data.add("|  Monday   |");
        data.add("|  Tuesday  |");
        data.add("| Wednesday |");
        data.add("| Thursday  |");
        data.add("|  Friday   |");
        data.add("| Saturday  |");
        data.add("|  Sunday   |");
        int z = 7;
        int q = 10;
        int g = 1;
        int j = 10;

        for (int w = 0; w <= 63; w++) {

            if (w == 0 | w == 1 | w == 2 | w == 3 | w == 4 | w == 5 | w == 6 | w == 7 | w == 8 | w == 9 | w == 10 | w ==16) {
                if (w == 0 | w == 8 | w == 16) {

                    data.add("\n| 0" + z + ":00 |");
                    z = z + 1;

                } else{
                  data.add("|Input nr 0" + g + "|");
                  g = g + 1;
                }
            } else {
                if (w == 24 | w == 32 | w == 40 | w == 48 | w == 56) {

                    data.add("\n| " + q + ":00 |");
                    q = q + 1;
                }else {

                    data.add("|Input nr " + j + "|");
                    j = j + 1;
                }
            }
        }
        for (int y = 0; y <= 71; y++) {

            System.out.print(data.get(y));
        }

    }


}

class Edit{
    Scanner e_cells = new Scanner(System.in);
    Handler h = new FileHandler();
    int x = 1;

    public void editInput() throws FileNotFoundException {


        Input i = new Input();
        i.seeInput();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");


        int g = 1;
        boolean nInt = true ;
        boolean nO = true;

        for (int w = 8; w <= 71; w++){
            if (!(w == 8 | w == 16 | w == 24 | w == 32 | w == 40 | w == 48 | w == 56 | w == 64)){
                i.data.set(w,"|           |");
            }
        }


        for (int w = 8; w <= 71; w++) {

            if (!(w == 8 | w == 16 | w == 24 | w == 32 | w == 40 | w == 48 | w == 56 | w == 64)) {
                if (w == 9 | w == 10 | w == 11 | w == 12 | w == 13 | w == 14 | w == 15 | w == 17 | w == 18) {

                i.data.set(w, "|Input nr 0" + g + "|");
                g = g + 1;
                for (int y = 0; y <= 71; y++) {

                    System.out.print(i.data.get(y));
                }

                    do{
                        System.out.println("");
                        System.out.println("");
                        System.out.println("Please enter one of the options below:");
                        System.out.println("");
                        System.out.println("");
                        System.out.println("1 - Brake");
                        System.out.println("2 - Writing    - Brock");
                        System.out.println("3 - Listening  - Jeff");
                        System.out.println("4 - Reading    - Drake");
                        System.out.println("5 - Running    - Peter");
                        System.out.println("6 - Jumping    - Mark");
                        System.out.println("7 - Sleeping   - Phineas");
                        System.out.println("8 - Painting   - Ferb");
                        System.out.println("9 - Falling    - Mark");

                        try {
                            x = e_cells.nextInt();
                            if((x >= 1) & (x <= 9)){




                                i.data.set(w, "|     " + x + "     |");
                                System.out.println("");
                                System.out.println("");
                                System.out.println("");
                                System.out.println("");

                                nO = false;

                            }else{


                                System.out.println("Please enter one of the options");
                                nO = true;
                                e_cells.nextLine();


                            }

                            nInt = false;
                        }
                        catch(Exception e) {


                            System.out.println("Please enter a Integer");
                            nInt = true;
                            e_cells.nextLine();


                        }
                    }while((nInt) | (nO));

            }else{

                    i.data.set(w, "|Input nr " + g + "|");
                    g = g + 1;
                    for (int y = 0; y <= 71; y++) {

                        System.out.print(i.data.get(y));
                    }

                    do{
                        System.out.println("");
                        System.out.println("");
                        System.out.println("Please enter one of the options below:");
                        System.out.println("");
                        System.out.println("");
                        System.out.println("1 - Brake");
                        System.out.println("2 - Writing    - Borck");
                        System.out.println("3 - Listening  - Jeff");
                        System.out.println("4 - Reading    - Drake");
                        System.out.println("5 - Running    - Peter");
                        System.out.println("6 - Jumping    - Mark");
                        System.out.println("7 - Sleeping   - Phineies");
                        System.out.println("8 - Painting   - Ferb");
                        System.out.println("9 - Falling    - Mark");

                        try {

                            x = e_cells.nextInt();
                            if((x >= 1) & (x <= 9)){




                                i.data.set(w, "|     " + x + "     |");
                                System.out.println("");
                                System.out.println("");
                                System.out.println("");
                                System.out.println("");

                                nO = false;

                            }else{


                                System.out.println("Please enter one of the options");
                                nO = true;
                                e_cells.nextLine();


                            }

                            nInt = false;
                        }
                        catch(Exception e) {


                            System.out.println("Please enter a Integer");
                            nInt = true;
                            e_cells.nextLine();


                        }
                    }while((nInt | nO));

                }

            }
            }
        for (int y = 0; y <= 71; y++) {

            System.out.print(i.data.get(y));
        }
        }


    }



class MainTest{

    public static void main(String[] args) throws FileNotFoundException {

        Input i = new Input();
        //i.seeInput();
        Edit e = new Edit();
        e.editInput();

    }

}


