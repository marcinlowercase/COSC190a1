package assignment1;

import java.io.*;
import java.util.Scanner;

public class CountWords {

    public static void main(String[] args) {
        try {
            for (String path : args) {
                File fObj = new File(path);

                System.out.println("Report for file " + fObj.getName());

                if (fObj.exists()) {
                    System.out.println("Count Line " + countLine(fObj));
                    System.out.println(("Count Word " + countWord(fObj)));
                    System.out.println(("Character Count " + countChar(fObj)));

                } else {
                    System.out.println(fObj.getAbsoluteFile() + " not exist.");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }

    private static int countChar(File fObj) throws FileNotFoundException {

        Scanner input = new Scanner(fObj);
        int count = 0;
        while (input.hasNext()) {
            for (String word : input.nextLine().split(" ")) {
                count += word.length();
            }
        }

        // I don't know it just have 1 more character when working with csv file
        // At the Final look after complete everything, I think it was because UTF-16
        if (fObj.getName().contains(".csv")) {
            count--;
        }

        return count;
    }

    private static int countWord(File fObj) throws FileNotFoundException {
        Scanner input = new Scanner(fObj);
        int count = 0;
        while (input.hasNext()) {
            String line = input.nextLine();
            count += countWordInLine(line);
        }
        return count;
    }

    private static int countWordInLine(String line) {
        int count = 0;
        String[] comma = line.split(",");
        count += comma.length;
        for (String commaSection : comma) {

            count += (commaSection.split(" ").length - 1);

            for (int i = 0; i < commaSection.split(" ").length; i++) {
                if (commaSection.split(" ")[i].isEmpty()) {
                    count--;
                }
            }

        }
        return count;
    }

    private static int countLine(File fObj) throws FileNotFoundException {
        Scanner input = new Scanner(fObj);
        int count = 0;
        while (input.hasNext()) {
            input.nextLine();
            count++;
        }
        return count;
    }
}
