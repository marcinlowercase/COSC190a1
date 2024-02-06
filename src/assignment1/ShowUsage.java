package assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ShowUsage {

    public static void main(String[] args) {

        try {

            ArrayList<String> strWordList = new ArrayList<>();
            ArrayList<String> strWordListNoDup;
            ArrayList<Word> wordList = new ArrayList<>();

            for (String path : args) {
                File fObj = new File(path);
                Scanner scanner = new Scanner(fObj);
                while (scanner.hasNext()) {
                    StringBuilder word = new StringBuilder();
                    String line = scanner.nextLine();
                    for (int i = 0; i < line.length(); i++) {
                        if (Character.isAlphabetic(line.charAt(i))) {
                            word.append(line.charAt(i));
                        } else {
                            if (word.length() > 3) {
                                strWordList.add(word.toString().toLowerCase());
                                word = new StringBuilder();
                            } else {
                                word = new StringBuilder();
                            }

                        }
                        if (i == line.length() - 1) {
                            if (word.length() > 3) {
                                strWordList.add(word.toString().toLowerCase());
                            }
                        }
                    }
                }


                Collections.sort(strWordList);


                strWordListNoDup = new ArrayList<>(strWordList);


                // Create No Dup String List
                for (int i = 0; i < strWordListNoDup.size(); i++) {
                    if (i != strWordListNoDup.size() - 1) {
                        if (strWordListNoDup.get(i).equals(strWordListNoDup.get(i + 1))) {
                            strWordListNoDup.remove(i + 1);
                            i--;
                        }
                    }

                }

                // Use No Dup String List to create Word ArrList
                for (String string : strWordListNoDup) {
                    wordList.add(new Word(string));
                }

                // Count usage time of words
                for (Word wObj : wordList) {
                    for (String s : strWordList) {
                        if (wObj.getWord().equals(s)) {
                            wObj.useOneMore();
                        }
//                        if ((wObj.getUsageTime() != 0) && (wObj.getWord() != s)){
//                            break;
//                        }
                    }
                }

                wordList.sort(Comparator.comparing(Word::getWord));
                Collections.reverse(wordList);
                wordList.sort(Comparator.comparing(Word::getUsageTime));
                Collections.reverse(wordList);

            }

            for (Word w : wordList) {
                System.out.println(w);
            }
            System.out.println(wordList.size());


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
