package ufc.com.alugaappquixada.Model;

public class Owner {
    private String name;
    private String email;
    private Adress adress;
    private PhoneNumber phoneNumber;

    public Owner(String name, String email, PhoneNumber phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }
}
