package ufc.com.alugaappquixada.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import ufc.com.alugaappquixada.BuildConfig;
import ufc.com.alugaappquixada.R;
import ufc.com.alugaappquixada.presenter.LoginPresenter;
import ufc.com.alugaappquixada.presenter.SignUpPresenter;
import ufc.com.alugaappquixada.presenter.SignUpPresenterImpl;
import ufc.com.alugaappquixada.util.Util;
import ufc.com.alugaappquixada.view.SignUpView;

public class SignUpActivity extends Activity implements SignUpView {

    private EditText nameUser;
    private EditText emailUser;
    private EditText phoneUser;
    private EditText passwordUser;
    private Button btnSignUp;
    private String mCurrentPhotoPath;
    private String faceImage;
    private CircleImageView circleImageView;
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
        circleImageView =  findViewById(R.id.userdp);

        signUpPresenter = SignUpPresenterImpl.createWithView(this,this);

         btnSignUp.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                signUp();
             }
         });
         circleImageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 getPermissions();
             }
         });
         
    }

    private void getPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        else
            dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                photoFile = File.createTempFile("PHOTOAPP", ".jpg", storageDir);
                mCurrentPhotoPath = "file:" + photoFile.getAbsolutePath();
            }
            catch(IOException ex){
                Toast.makeText(getApplicationContext(), "Erro ao tirar a foto", Toast.LENGTH_SHORT).show();
            }

            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this, "com.example.asd.fileprovider",photoFile));
                startActivityForResult(takePictureIntent, 0);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            try {
                Bitmap bm1 = BitmapFactory.decodeStream(getContentResolver().openInputStream(Uri.parse(mCurrentPhotoPath)));
                circleImageView.setImageBitmap(bm1);
                bm1 = Bitmap.createScaledBitmap(bm1, 626, 626, true);
                faceImage = Util.convertBitMapToBase64(bm1);
            }catch(FileNotFoundException fnex){
                Toast.makeText(getApplicationContext(), "Foto não encontrada!", Toast.LENGTH_LONG).show();
            }

    }
    @Override
    public void signUp() {
        String name = nameUser.getText().toString();
        String email = emailUser.getText().toString();
        String phone = phoneUser.getText().toString();
        String password = passwordUser.getText().toString();
        signUpPresenter.saveUser(name,email,phone,password,faceImage);
    }

    @Override
    public void onSignUpSucess() {
        startActivity(new Intent(this,LoginActivity.class));
    }

    @Override
    public void onSignUpFailure() {

    }
}
