package ufc.com.alugaappquixada;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
>>>>>>> ac659fc1e27c58bfc75ceecdc3bb2d3a7d8bdcd8

import ufc.com.alugaappquixada.activity.DetailsApartmentActivity;
import ufc.com.alugaappquixada.activity.LoginActivity;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this,DetailsApartmentActivity.class));
    }

}
