package ufc.com.alugaappquixada.Model;

public class MarkerInformation {
    private String email;
    private String name;
    private String adress;
    private String phoneNumber;


    private MarkerInformation(String email, String name, String adress, String phoneNumber) {
        this.email = email;
        this.name = name;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
    }
    public static MarkerInformation create(String email, String name, String adress, String phoneNumber){
        return new MarkerInformation(email,name,adress,phoneNumber);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
