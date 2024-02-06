package assignment1;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Payroll {
    public static void main(String[] args) {
        try {

            URL url = new URL("https://liveexample.pearsoncmg.com/data/Salary.txt");

            Scanner readPayRoll = new Scanner(new DataInputStream(url.openStream()));

            ArrayList<FacultyMember> memberList = new ArrayList<>();

            while (readPayRoll.hasNext()) {
                memberList.add(new FacultyMember(readPayRoll.nextLine().split(" ")));
            }

//            for (FacultyMember m : memberList){
//                System.out.println(m);
//            }
//
//            summary(memberList);
//            summary(memberList, FacultyPosition.ASSISTANT);
//            summary(memberList, FacultyPosition.ASSOCIATE);
//            summary(memberList, FacultyPosition.FULL);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int numOfMember(ArrayList<FacultyMember> list) {
        return list.size();
    }

    private static int numOfMember(ArrayList<FacultyMember> list, FacultyPosition position) {
        int countAssistant = 0;
        int countAssociate = 0;
        int countFull = 0;

        for (FacultyMember m : list) {
            if (m.getPosition().equals("assistant")) {
                countAssistant++;
            }
            if (m.getPosition().equals("associate")) {
                countAssociate++;
            }
            if (m.getPosition().equals("full")) {
                countFull++;
            }

        }
        return switch (position) {
            case FacultyPosition.ASSISTANT -> countAssistant;
            case FacultyPosition.ASSOCIATE -> countAssociate;
            case FacultyPosition.FULL -> countFull;
        };
    }

    private static double getHighSalary(ArrayList<FacultyMember> list) {
        double high = 0;
        for (FacultyMember m : list) {
            if (Double.parseDouble(m.getAnnualSalary()) > high) {
                high = Double.parseDouble(m.getAnnualSalary());
            }
        }
        return high;
    }

    private static double getHighSalary(ArrayList<FacultyMember> list, FacultyPosition position) {
        double high = 0;
        switch (position) {
            case FacultyPosition.ASSISTANT:
                for (FacultyMember m : list) {
                    if (m.getPosition().equals("assistant") && Double.parseDouble(m.getAnnualSalary()) > high) {
                        high = Double.parseDouble(m.getAnnualSalary());
                    }
                }
                break;
            case FacultyPosition.ASSOCIATE:
                for (FacultyMember m : list) {
                    if (m.getPosition().equals("associate") && Double.parseDouble(m.getAnnualSalary()) > high) {
                        high = Double.parseDouble(m.getAnnualSalary());
                    }
                }
                break;
            case FacultyPosition.FULL:
                for (FacultyMember m : list) {
                    if (m.getPosition().equals("full") && Double.parseDouble(m.getAnnualSalary()) > high) {
                        high = Double.parseDouble(m.getAnnualSalary());
                    }
                }
                break;
        }

        return high;
    }

    private static double getLowSalary(ArrayList<FacultyMember> list) {
        double low = getHighSalary(list);
        for (FacultyMember m : list) {
            if (Double.parseDouble(m.getAnnualSalary()) < low) {
                low = Double.parseDouble(m.getAnnualSalary());
            }
        }

        return low;
    }

    private static double getLowSalary(ArrayList<FacultyMember> list, FacultyPosition position) {
        double low = getHighSalary(list, position);
        switch (position) {
            case FacultyPosition.ASSISTANT:
                for (FacultyMember m : list) {
                    if (m.getPosition().equals("assistant") && Double.parseDouble(m.getAnnualSalary()) < low) {
                        low = Double.parseDouble(m.getAnnualSalary());
                    }
                }
                break;
            case FacultyPosition.ASSOCIATE:
                for (FacultyMember m : list) {
                    if (m.getPosition().equals("associate") && Double.parseDouble(m.getAnnualSalary()) < low) {
                        low = Double.parseDouble(m.getAnnualSalary());
                    }
                }
                break;
            case FacultyPosition.FULL:
                for (FacultyMember m : list) {
                    if (m.getPosition().equals("full") && Double.parseDouble(m.getAnnualSalary()) < low) {
                        low = Double.parseDouble(m.getAnnualSalary());
                    }
                }
                break;
        }

        return low;
    }

    private static double getAverageSalary(ArrayList<FacultyMember> list) {
        double average = 0;
        double total = 0;
        for (FacultyMember m : list) {
            total += Double.parseDouble(m.getAnnualSalary());
            average = total / numOfMember(list);
        }


        return Math.round(average * 100.0) / 100.0;
    }

    private static double getAverageSalary(ArrayList<FacultyMember> list, FacultyPosition position) {
        double average = 0;
        double total = 0;
        switch (position) {
            case FacultyPosition.ASSISTANT:
                for (FacultyMember m : list) {
                    if (m.getPosition().equals("assistant")) {
                        total += Double.parseDouble(m.getAnnualSalary());
                        average = total / numOfMember(list, position);
                    }
                }
                break;
            case FacultyPosition.ASSOCIATE:
                for (FacultyMember m : list) {
                    if (m.getPosition().equals("associate")) {
                        total += Double.parseDouble(m.getAnnualSalary());
                        average = total / numOfMember(list, position);
                    }
                }
                break;
            case FacultyPosition.FULL:
                for (FacultyMember m : list) {
                    if (m.getPosition().equals("full")) {
                        total += Double.parseDouble(m.getAnnualSalary());
                        average = total / numOfMember(list, position);
                    }
                }
                break;
        }

        return Math.round(average * 100.0) / 100.0;
    }

//    private static void summary(ArrayList<FacultyMember> list) {
//        System.out.println("FACULTY:");
//        System.out.println("Num: " + numOfMember(list));
//        System.out.println("High: " + getHighSalary(list));
//        System.out.println("Low: " + getLowSalary(list));
//        System.out.println("Average: " + getAverageSalary(list));
//    }
//
//    private static void summary(ArrayList<FacultyMember> list, FacultyPosition position) {
//        System.out.println(position.toString());
//        System.out.println("Num: " + numOfMember(list, position));
//        System.out.println("High: " + getHighSalary(list, position));
//        System.out.println("Low: " + getLowSalary(list, position));
//        System.out.println("Average: " + getAverageSalary(list, position));
//    }


}
