package ufc.com.alugaappquixada;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import ufc.com.alugaappquixada.activity.HomeActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this,HomeActivity.class));
    }
}
