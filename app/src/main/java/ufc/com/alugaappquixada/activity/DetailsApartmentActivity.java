package ufc.com.alugaappquixada.activity;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.synnapps.carouselview.CarouselView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import ufc.com.alugaappquixada.R;

public class DetailsApartmentActivity extends Activity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    Button buttonVisit;
    CarouselView carouselView;

    int[] sampleImages = {R.drawable.apart1, R.drawable.apart2, R.drawable.apart3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_apartment);

        carouselView = (CarouselView) findViewById(R.id.caroselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener((position, imageView) -> {
            imageView.setImageResource(sampleImages[position]);
        });

        buttonVisit = (Button) findViewById(R.id.buttonVisit);
        buttonVisit.setOnClickListener((view) -> {
            Calendar date = Calendar.getInstance();

            DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(DetailsApartmentActivity.this
                ,date.get(Calendar.YEAR)
                ,date.get(Calendar.MONTH)
                ,date.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.setTitle("Data da visita");
            datePickerDialog.show(getFragmentManager(), "DataPicker");
            datePickerDialog.setOnDismissListener( (dialogInterface) -> {
                TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(DetailsApartmentActivity.this
                        ,date.get(Calendar.HOUR_OF_DAY)
                        ,date.get(Calendar.MINUTE)
                        ,true);
                timePickerDialog.setTitle("Horário da visita");

                timePickerDialog.show(getFragmentManager(), "TimePicker");
            });

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_details_apartment, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {

        switch(item.getItemId())
        {
            case R.id.actionLogout:
                startActivity(new Intent(this,LoginActivity.class));
                break;

        }
        return true;
    }



    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

    }


}
