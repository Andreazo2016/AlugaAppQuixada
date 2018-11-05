package ufc.com.alugaappquixada;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import ufc.com.alugaappquixada.activity.DetailsApartmentActivity;
import ufc.com.alugaappquixada.activity.LoginActivity;

public class MainActivity extends Activity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this,DetailsApartmentActivity.class));
    }

}
