package assignment1;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Encyrpt {

    public static void main(String[] args) {


        try {
            if (args[0].contains(".txt")) {

                String keyPad = genKeypad();

                File plainFile = new File(args[0]);
                Scanner readPlainFile = new Scanner(plainFile);


                // Take the directory the original file in.
                String path = plainFile.getParent();

                // Write Key Pad to enc.txt file
                PrintWriter writeKeyPad = new PrintWriter(path + File.separator + "enc.txt");
                writeKeyPad.println(keyPad);
                writeKeyPad.close();

                // Encrypt file to temp file
                PrintWriter writeToTemp = new PrintWriter(path + File.separator + "temp.txt");
                while (readPlainFile.hasNext()) {
                    writeToTemp.println(encryptLine(readPlainFile.nextLine(), keyPad));
                }
                readPlainFile.close();
                writeToTemp.close();


                // Overwrite the original file
                overwriteFile(path + File.separator + "temp.txt", plainFile.getAbsolutePath());


            } else {
                System.out.println("This program only run with text file");
                System.exit(1);
            }

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }

    public static String genKeypad() {
        String string = "#";
        while (string.substring(1).length() < 26) {
            char randChar = (char) (new Random().nextInt(26) + 97);
            if (!alreadyHave(randChar, string)) {
                string += randChar;
            }
        }
        return string.substring(1);
    }

    public static String encryptLine(String sPlainText, String sKeypad) {

        StringBuilder encryptedString = new StringBuilder();

        for (int i = 0; i < sPlainText.length(); i++) {
            char currentChar = sPlainText.charAt(i);
            if (Character.isAlphabetic(currentChar)) {
                currentChar = turnToLowercaseIfUpper(currentChar);
                int index = ((int) currentChar) - 97;
                encryptedString.append(sKeypad.charAt(index));
            } else {
                encryptedString.append(currentChar);
            }

        }
        return encryptedString.toString();

    }

    private static char turnToLowercaseIfUpper(char c) {
        if (Character.isUpperCase(c)) {
            c = Character.toLowerCase(c);
        }
        return c;
    }

    private static boolean alreadyHave(char c, String string) {
        for (int j = 0; j < string.length(); j++) {
            if (string.charAt(j) == c) {
                return true;
            }
        }
        return false;
    }

    private static void overwriteFile(String srcFile, String targetFile) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(srcFile));
        PrintWriter writer = new PrintWriter(targetFile);

        while (scanner.hasNext()) {
            writer.println(scanner.nextLine());
        }
        writer.close();
        scanner.close();

        new File(srcFile).delete();
    }
}
