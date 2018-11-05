package ufc.com.alugaappquixada.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ufc.com.alugaappquixada.R;
import ufc.com.alugaappquixada.presenter.LoginPresenter;
import ufc.com.alugaappquixada.presenter.SignUpPresenter;
import ufc.com.alugaappquixada.presenter.SignUpPresenterImpl;
import ufc.com.alugaappquixada.view.SignUpView;

public class SignUpActivity extends Activity implements SignUpView {

    private EditText nameUser;
    private EditText emailUser;
    private EditText phoneUser;
    private EditText passwordUser;
    private Button btnSignUp;
    private SignUpPresenter signUpPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameUser = findViewById(R.id.nameUser);
        emailUser = findViewById(R.id.emailUser);
        phoneUser = findViewById(R.id.phoneUser);
        passwordUser = findViewById(R.id.passwordUser);
        btnSignUp = findViewById(R.id.btnSignUp);

        signUpPresenter = SignUpPresenterImpl.createWithView(this,this);

         btnSignUp.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                signUp();
             }
         });
    }

    @Override
    public void signUp() {
        String name = nameUser.getText().toString();
        String email = emailUser.getText().toString();
        String phone = phoneUser.getText().toString();
        String password = passwordUser.getText().toString();
        signUpPresenter.saveUser(name,email,phone,password);
    }

    @Override
    public void onSignUpSucess() {
        startActivity(new Intent(this,LoginActivity.class));
    }

    @Override
    public void onSignUpFailure() {

    }
}
