package assignment1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class CountryInfo {

//    public static void main(String[] args) {
//        try {
//
//
//            writeWesternCountries(loadInfo("C:\\Users\\tran0885\\Desktop\\COSC190\\a1\\data\\Country.csv"), "C:\\Users\\tran0885\\Desktop\\COSC190\\a1\\data\\think");
//
//
//            int recordSize = writeRAFCountry(loadInfo("C:\\Users\\tran0885\\Desktop\\COSC190\\a1\\data\\Country.csv"), "C:\\Users\\tran0885\\Desktop\\COSC190\\a1\\data\\raftest");
//
//
//            System.out.println(getRAFRec("C:\\Users\\tran0885\\Desktop\\COSC190\\a1\\data\\raftest",
//                    153,
//                    recordSize));
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
        public static void main(String[] args) {
        try {


            int recordSize = writeRAFCountry(loadInfo(args[0]), args[1]);


            //Delete File after each test

            System.out.println(getRAFRec(args[1],
                    -1,
                    recordSize));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static ArrayList<Country> loadInfo(String sFileName) throws FileNotFoundException {
        ArrayList<Country> countryList = new ArrayList<>();
        Scanner scanner = new Scanner(new File(sFileName));

        while (scanner.hasNext()) {
            countryList.add(new Country(scanner.nextLine().split(",")));
        }

        countryList.removeFirst();

        return countryList;
    }

    public static void writeCountryInfo(ArrayList<Country> obList, String sFile) throws IOException {
        ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(sFile));
        for (Country c : obList) {
            objOut.writeObject(c);
        }
        objOut.close();
    }

    public static void writeWesternCountries(ArrayList<Country> obList, String sFile) throws IOException {
        ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(sFile));
        for (Country c : obList) {
            if (!c.getLongitude().isEmpty()) {
                if (Double.parseDouble(c.getLongitude()) < 0) {
                    objOut.writeObject(c);
                }
            }
        }
        objOut.close();
    }

    public static int writeRAFCountry(ArrayList<Country> obList, String sFileName) throws IOException {

        ArrayList<Country> countries = new ArrayList<>();

        for (Country c : obList) {
            String[] temp = {c.getAbbreviation(), c.getLatitude(), c.getLongitude(), c.getName()};
            countries.add(new Country(temp, Country.getRecordMaxSize(obList)));
        }

        RandomAccessFile raf = new RandomAccessFile(sFileName, "rw");

        for (Country c : countries) {
            raf.seek(raf.length());
            raf.write(c.toString().getBytes(StandardCharsets.UTF_16));
        }
        raf.close();

        return (countries.getFirst().toString().length() + 1) * 2;

    }


    public static Country getRAFRec(String sFile, int nPosition, int recordSize) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(new File(sFile), "rw");

        if (nPosition <= 0 || (nPosition > (raf.length() / recordSize))) {
            return null;
        } else {
            raf.seek((long) (nPosition - 1) * recordSize);

            byte[] tempByteArr = new byte[recordSize];

            for (int i = 0; i < tempByteArr.length; i++) {
                tempByteArr[i] = raf.readByte();
            }
            return new Country(new String(tempByteArr, StandardCharsets.UTF_16).split(","));
        }
    }

}
