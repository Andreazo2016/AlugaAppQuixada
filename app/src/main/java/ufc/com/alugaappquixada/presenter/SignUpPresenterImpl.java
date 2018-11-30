package ufc.com.alugaappquixada.presenter;

import android.content.Context;

import ufc.com.alugaappquixada.Model.User;
import ufc.com.alugaappquixada.service.UserService;
import ufc.com.alugaappquixada.view.SignUpView;

public class SignUpPresenterImpl implements SignUpPresenter {

    private static SignUpPresenterImpl instance;
    private UserService userService;
    private SignUpView view;
    private Context ctx;
    private SignUpPresenterImpl(Context ctx,SignUpView view){
        this.view = view;
        this.userService = UserService.createWithContext(ctx);
    }
    public static SignUpPresenterImpl createWithView(Context ctx,SignUpView view){
        if(instance == null){
            instance = new SignUpPresenterImpl(ctx,view);
        }
        return instance;
    }
    @Override
    public void saveUser(String name, String email, String phone, String password,String faceImage) {
        this.userService.salvarUser(User.create(email,name,phone,password,faceImage));
        view.onSignUpSucess();
    }
}
