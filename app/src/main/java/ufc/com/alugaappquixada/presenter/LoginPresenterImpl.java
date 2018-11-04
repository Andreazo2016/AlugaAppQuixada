package ufc.com.alugaappquixada.presenter;

import ufc.com.alugaappquixada.response.LoginResponse;
import ufc.com.alugaappquixada.service.LoginService;
import ufc.com.alugaappquixada.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView loginView;
    private LoginService loginService;

    private LoginPresenterImpl(){}
    private LoginPresenterImpl(LoginView loginView){
        this.loginView = loginView;
        this.loginService = LoginService.getInstance();
    }
    public static LoginPresenterImpl create(LoginView loginView){
        return new LoginPresenterImpl(loginView);
    }


    @Override
    public void validateCredential(String username, String password) {
        LoginResponse userRequestForLogin =  this.loginService.checkCredentials(username,password);
        if(userRequestForLogin.isLogged()){
           this.loginView.onLoginSucess();
        }else {
            this.loginView.onLoginFailure(userRequestForLogin.getMessge());
        }

    }
}
