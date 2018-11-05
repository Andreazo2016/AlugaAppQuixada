package ufc.com.alugaappquixada.presenter;

import ufc.com.alugaappquixada.Model.User;
import ufc.com.alugaappquixada.service.UserService;
import ufc.com.alugaappquixada.view.SignUpView;

public class SignUpPresenterImpl implements SignUpPresenter {

    private static SignUpPresenterImpl instance;
    private UserService userService;
    private SignUpView view;
    private SignUpPresenterImpl(SignUpView view){
        this.view = view;
        this.userService = UserService.getInstance();
    }
    public static SignUpPresenterImpl create(SignUpView view){
        if(instance == null){
            instance = new SignUpPresenterImpl(view);
        }
        return instance;
    }
    @Override
    public void saveUser(String name, String email, String phone, String password) {
        this.userService.salvarUser(User.create(email,name,phone,password));
        view.onSignUpSucess();
    }
}
