package ufc.com.alugaappquixada.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import ufc.com.alugaappquixada.Async.UserDetailsAsync;
import ufc.com.alugaappquixada.Model.User;
import ufc.com.alugaappquixada.R;
import ufc.com.alugaappquixada.util.Util;
import ufc.com.alugaappquixada.view.UserDetailsView;

public class UserDetailsActivity extends Activity implements UserDetailsView {

    private EditText name;
    private EditText email;
    private EditText cellPhone;
    private EditText password;
    private CircleImageView circleImageView;

    private UserDetailsView userDetailsView = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        name = findViewById(R.id.nameUserDetails);
        email = findViewById(R.id.emailUserDetails);
        cellPhone = findViewById(R.id.phoneUserDetails);
        password = findViewById(R.id.passwordUserDetails);
        circleImageView = findViewById(R.id.profileImage);


        User user = Util.getUserLogged(this);
        circleImageView.setImageBitmap(Util.base64ToBitMap(user.getFaceImage()));
        name.setText(user.getName());
        email.setText(user.getEmail());
        cellPhone.setText(user.getPhoneNumber());
        password.setText("");


    }

    @Override
    public void onSucess() {
        finish();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG);
    }
}
