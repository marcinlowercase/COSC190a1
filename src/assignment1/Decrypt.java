package assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Decrypt {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Use Decrypt File_To_Decrypt Key_Pad_File");
        }

        String encryptedFilePath = args[0];
        String keyPadPath = args[1];

        try {
            File encryptedFile = new File(encryptedFilePath);
            Scanner scanner = new Scanner(encryptedFile);

            String path = encryptedFile.getParent();

            // Read the key pad
            Scanner readKeyPad = new Scanner(new File(keyPadPath));
            String keyPad = readKeyPad.nextLine();

            //Write Decrypt to temp File
            PrintWriter writeToTemp = new PrintWriter(path + File.separator + "temp.txt");
            while (scanner.hasNext()) {
                writeToTemp.println(decryptLine(scanner.nextLine(), keyPad));
            }
            writeToTemp.close();
            scanner.close();

            // Overwrite the original file
            overwriteFile(path + File.separator + "temp.txt", encryptedFile.getAbsolutePath());


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());

        }


    }

    private static String decryptLine(String encryptedLine, String keyPadPath) {

        StringBuilder decryptedString = new StringBuilder();

        for (int i = 0; i < encryptedLine.length(); i++) {
            char currentChar = encryptedLine.charAt(i);
            if (Character.isAlphabetic(currentChar)) {
                currentChar = turnToLowercaseIfUpper(currentChar);

                int thing = keyPadPath.indexOf((currentChar));
                int origin = thing + 97;

                decryptedString.append((char) origin);

            } else {
                decryptedString.append(currentChar);
            }

        }

        return decryptedString.toString();
    }

    private static char turnToLowercaseIfUpper(char c) {
        if (Character.isUpperCase(c)) {
            c = Character.toLowerCase(c);
        }
        return c;
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
