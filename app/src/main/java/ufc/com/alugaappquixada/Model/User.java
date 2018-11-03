package ufc.com.alugaappquixada.Model;

public class User {
    private String email;
    private String name;
    private String phoneNumber;
    private String password;

    private User(){}
    private User(String email, String name, String phoneNumber,String password) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
    public static User create(String email,String name,String phoneNumber, String password ){
        return new User(email,name,phoneNumber,password);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }
}
