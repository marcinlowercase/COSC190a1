package assignment1;

public class FacultyMember {

    private final int FI = 0;
    private final int LA = 1;
    private final int PO = 2;
    private final int SA = 3;

    private String firstName;
    private String lastName;
    private String position;
    private String annualSalary;

    public FacultyMember(String[] s){
        this.firstName = s[FI];
        this.lastName = s[LA];
        this.position = s[PO];
        this.annualSalary = s[SA];
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public String getAnnualSalary() {
        return annualSalary;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + position + " " + annualSalary;
    }
}
