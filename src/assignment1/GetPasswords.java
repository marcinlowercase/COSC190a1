package assignment1;

import java.util.Scanner;

public class GetPasswords {

    public static void main(String[] args) throws BadPass {


        try {
            if (!valid(capturePassword())) {
                throw new BadPass("Invalid Password!");
            } else {
                System.out.println("Good Password");
            }
        } catch (BadPass ex) {
            System.out.println(ex.getMessage());
        }


    }

    private static String capturePassword() {

        System.out.print("Enter a password (8-10 characters): ");
        Scanner inputLine = new Scanner(System.in);
        return inputLine.nextLine();


    }

    private static boolean valid(String password) {
        return includeSpecialChar(password, 2) && rangeOfLength(password, 8, 12);
    }

    private static boolean rangeOfLength(String password, int minLength, int maxLength) {
        return password.length() >= minLength && password.length() <= maxLength;
    }

    private static boolean includeSpecialChar(String password, int quantityOfSpecialChar) {

        int countSpecialChar = 0;
        String specialChar = "%&^!@";

        for (int i = 0; i < specialChar.length(); i++) {
            for (int j = 0; j < password.length(); j++) {
                if (specialChar.charAt(i) == password.charAt(j)) {
                    countSpecialChar++;
                    break;
                }
            }
            if (countSpecialChar >= quantityOfSpecialChar) {
                return true;
            }
        }

        return false;
    }
}
