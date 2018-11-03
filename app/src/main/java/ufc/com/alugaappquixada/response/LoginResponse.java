package ufc.com.alugaappquixada.response;

public class LoginResponse {
    private String messge;
    private boolean status_login;

    private LoginResponse(String messge){
        this.messge = messge;
    }
    public static LoginResponse create(String messge){
        return new LoginResponse(messge);
    }
    public LoginResponse setLoginAsStatusFailure(){
        this.status_login = false;
        return this;
    }
    public LoginResponse setLoginAsStatusSucess(){
        this.status_login = true;
        return this;
    }

    public String getMessge() {
        return messge;
    }

    public boolean isLogged() {
        return status_login;
    }
}
