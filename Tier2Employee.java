public class Tier2Employee extends Employee {
    private String certification;

    public Tier2Employee(String firstName, String lastName, String address, String phoneNumber, 
                         String email, String employeeld, String clockedin, String hiredDate, 
                         String certification) {
        super(firstName, lastName, address, phoneNumber, email, employeeld, clockedin, hiredDate);
        this.certification = certification;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    @Override
    public String getFileData() {
        return super.getFileData() + "," + getCertification();
    }
}
