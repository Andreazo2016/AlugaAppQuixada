package ufc.com.alugaappquixada.Model;

public class User {
    private Integer id;
    private String email;
    private String name;
    private String phoneNumber;
    private String password;
    private String faceImage;

    private User(){}
    private User(String email, String name, String phoneNumber,String password,String faceImage) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.faceImage = faceImage;
    }
    private User(String email, String name, String phoneNumber,String password) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
    public static User create(){return new User();}
    public static User create(String email,String name,String phoneNumber, String password ){
        return new User(email,name,phoneNumber,password);
    }
    public static User create(String email,String name,String phoneNumber, String password,String faceImage ){
        return new User(email,name,phoneNumber,password,faceImage);
    }

    public Integer getId() {
        return id;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }
}
