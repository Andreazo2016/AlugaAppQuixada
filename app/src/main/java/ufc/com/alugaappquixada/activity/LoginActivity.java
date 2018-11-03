package ufc.com.alugaappquixada.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ufc.com.alugaappquixada.R;
import ufc.com.alugaappquixada.presenter.LoginPresenter;
import ufc.com.alugaappquixada.presenter.LoginPresenterImpl;
import ufc.com.alugaappquixada.view.LoginView;

public class LoginActivity extends Activity implements LoginView {

    private EditText emailText;
    private EditText passwordText;
    private Button  loginBtnConfirm;
    private LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        loginBtnConfirm = findViewById(R.id.loginBtn);

        loginPresenter = LoginPresenterImpl.create(this);

        loginBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = emailText.getText().toString();
                String password = passwordText.getText().toString();
                loginPresenter.validateCredential(username,password);
            }
        });
    }

    @Override
    public void onLoginSucess() {
        //todo pass user loged to homeactivity
        startActivity(new Intent(this,HomeActivity.class));
    }

    @Override
    public void onLoginFailure(String erro) {
        Toast.makeText(LoginActivity.this, erro, Toast.LENGTH_SHORT).show();
    }
}
