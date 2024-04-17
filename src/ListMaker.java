import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class ListMaker {
    public static ArrayList<String> list = new ArrayList<String>();
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws IOException {

        boolean done = false;
        boolean loaded = false;
        boolean edited = false;
        boolean saved = false;

        String fileName = null;

        while (!done) {
            String input = InputHelper.getRegExString(scan, "Options: \nA - Add an item to the list\nD - delete item from the list\nO – Open a list file from disk \nS – Save the current list file to disk \nC – Clear removes all the elements from the current list \nV - View  \nQ - quit the program", "[AaDdPpQqOoSsCcVv]");
            if (input.equalsIgnoreCase("a")) {
                add();
                if (loaded ) {
                    edited = true;
                    saved = false;
                }
            }
            else if (input.equalsIgnoreCase("d")) {
                delete();
            }
            else if (input.equalsIgnoreCase("v")) {
                viewList();
            }
            else if (input.equalsIgnoreCase("q")) {
                quit();
            }
            else if (input.equalsIgnoreCase("o")) {
                if (edited) {
                    if (loaded) {
                        IOHelper.writeFile(list, fileName);
                    } else {
                        if (InputHelper.getYNConfirm(scan, "Do you want to save the current list before opening it?")) {
                            IOHelper.writeFile(list, InputHelper.getNonZeroLenString(scan, ""));
                        }
                    }
                }
                list.clear();
                fileName = IOHelper.openFile(list);

                loaded = true;
                edited = false;
                saved = false;
            }
            else if (input.equalsIgnoreCase("s")) {
                if (loaded) {
                    IOHelper.writeFile(list, fileName);
                } else {
                    IOHelper.writeFile(list, InputHelper.getNonZeroLenString(scan, "what would you like to name the file?"));
                }
            }
            else if (input.equalsIgnoreCase("c")) {
            list.clear();
                loaded = true;
                edited = false;
                saved = false;
            }
        }

    }
    public static void add(){
        String word = InputHelper.getNonZeroLenString(scan, "Please enter a string to insert into the list");
        list.add(word);
    }
    public static void delete(){
        int index = InputHelper.getRangedInt(scan, "Please enter the index at which you would like the remove the String", 0, list.size()-1);
        list.remove(index);
    }
    public static void viewList(){
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + " - " + list.get(i));
        }
    }
    public static void quit(){
        if (InputHelper.getYNConfirm(scan, "Please enter Y to continue or N to quit")) {
            System.exit(0);
        }
    }


}