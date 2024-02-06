package assignment1;

import java.io.Serializable;
import java.util.ArrayList;

public class Country implements Serializable {

    private final int AB = 0;
    private final int LA = 1;
    private final int LO = 2;
    private final int NA = 3;


    private char[] abbreviation;
    private char[] latitude;
    private char[] longitude;
    private char[] name;


    public Country(String[] sArr) {
        this.abbreviation = sArr[AB].toCharArray();
        this.latitude = sArr[LA].toCharArray();
        this.longitude = sArr[LO].toCharArray();
        this.name = sArr[NA].toCharArray();

    }

    public Country(String[] sArr, int[] maxSize) {
        this.abbreviation = addSpace(sArr[AB], maxSize[AB]).toCharArray();
        this.latitude = addSpace(sArr[LA], maxSize[LA]).toCharArray();
        this.longitude = addSpace(sArr[LO], maxSize[LO]).toCharArray();
        this.name = addSpace(sArr[NA], maxSize[NA]).toCharArray();
    }

    public static int[] getRecordMaxSize(ArrayList<Country> list) {

        int[] maxSize = new int[4];

        for (Country c : list) {
            if (c.getAbbreviation().length() > maxSize[c.AB]) {
                maxSize[c.AB] = c.getAbbreviation().length();
            }
            if (c.getLatitude().length() > maxSize[c.LA]) {
                maxSize[c.LA] = c.getLatitude().length();
            }
            if (c.getLongitude().length() > maxSize[c.LO]) {
                maxSize[c.LO] = c.getLongitude().length();
            }
            if (c.getName().length() > maxSize[c.NA]) {
                maxSize[c.NA] = c.getName().length();
            }
        }


        return maxSize;
    }

    public String getAbbreviation() {
        return String.valueOf(abbreviation);
    }

    public String getLatitude() {
        return String.valueOf(latitude);
    }

    public String getLongitude() {
        return String.valueOf(longitude);
    }

    public String getName() {
        return String.valueOf(name);
    }

    @Override
    public String toString() {
        return String.valueOf(abbreviation) + ","
                + String.valueOf(latitude) + ","
                + String.valueOf(longitude) + ","
                + String.valueOf(name);
    }

    public String addSpace(String c, int maxLength) {

        String temp = "a";

        for (int i = 0; i < maxLength - c.length(); i++) {
            temp += ' ';
        }
        temp += c;
        return temp.substring(1);
    }


}
