import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Runner {
    private static final String INPUT_FILE = "input.txt";

    public static void main(String[] args) {
        FirstPrinter first = new FirstPrinter("Start");
        SecondPrinter second = new SecondPrinter("Start");
        List<String> msg = readListOfStrings(INPUT_FILE);
        List<String> uniqueList = new LinkedList<>();
        first.start();
        second.start();
        boolean uniqueStart = false;
        for (int i = 0; i < msg.size(); i++) {
            String currentMessage = msg.get(i);
            boolean notDoneYet = true;
            while (notDoneYet) {
                if (!first.isAlive()) {
                    FirstPrinter nextFirstThread = new FirstPrinter(currentMessage);
                    nextFirstThread.start();
                    first = nextFirstThread;
                    notDoneYet = false;
                } else {
                    if (!second.isAlive()) {
                        SecondPrinter nextSecondThread = new SecondPrinter(currentMessage);
                        nextSecondThread.start();
                        second = nextSecondThread;
                        notDoneYet = false;
                    } else
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.getLocalizedMessage();
                        }
                }

            }
            if (i != msg.size() - 1 && !uniqueStart) {
                List<String> newList = readListOfStrings(INPUT_FILE);
                uniqueList = formNewList(msg, readListOfStrings(INPUT_FILE), uniqueList);
                msg = newList;
            } else {
                if (!uniqueStart) {
                    uniqueStart = true;
                    msg = uniqueList;
                    i = -1;
                }
            }

        }
    }


    private static List<String> formNewList(List<String> oldList, List<String> newList, List<String> uniqueStart) {
        List<String> uniqueList = uniqueStart;
        for (int i = 0; i < newList.size(); i++) {
            boolean isUnique = true;
            for (int j = 0; j < oldList.size(); j++)
                if (newList.get(i).compareTo(oldList.get(j)) == 0) {
                    j = newList.size();
                    isUnique = false;
                }
            if (isUnique) {
                uniqueList.add(newList.get(i));
            }
        }
        //uniqueList.forEach(System.out::println);
        return uniqueList;
    }


    private static List<String> readListOfStrings(String path) {
        List<String> listOfStrings = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String tempString = br.readLine();
            while (tempString != null) {
                listOfStrings.add(tempString);
                tempString = br.readLine();
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return listOfStrings;
    }
}
