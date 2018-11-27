package ufc.com.alugaappquixada.presenter;

import android.content.Context;

import ufc.com.alugaappquixada.Async.UserLoginAsync;
import ufc.com.alugaappquixada.response.LoginResponse;
import ufc.com.alugaappquixada.service.LoginService;
import ufc.com.alugaappquixada.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView loginView;
    private LoginService loginService;
    private Context ctx;

    private LoginPresenterImpl(){}
    private LoginPresenterImpl(Context ctx,LoginView loginView){
        this.ctx = ctx;
        this.loginView = loginView;
        this.loginService = LoginService.getInstance(ctx);
    }
    public static LoginPresenterImpl createWithContext(Context ctx,LoginView loginView){
        return new LoginPresenterImpl(ctx,loginView);
    }


    @Override
    public void validateCredential(String username, String password) {
        /*
        LoginResponse userRequestForLogin =  this.loginService.checkCredentials(username,password);
        if(userRequestForLogin.isLogged()){
           this.loginView.onLoginSucess();
        }else {
            this.loginView.onLoginFailure(userRequestForLogin.getMessge());
        }
        */
        new UserLoginAsync(ctx,loginView).execute(username,password);

    }
}
