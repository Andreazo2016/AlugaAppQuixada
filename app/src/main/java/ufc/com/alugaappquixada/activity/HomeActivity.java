package ufc.com.alugaappquixada.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import ufc.com.alugaappquixada.Model.MarkerInformation;
import ufc.com.alugaappquixada.Model.PointMaker;
import ufc.com.alugaappquixada.R;
import ufc.com.alugaappquixada.presenter.MapPresenter;
import ufc.com.alugaappquixada.presenter.MapPresenterImpl;
import ufc.com.alugaappquixada.util.Util;
import ufc.com.alugaappquixada.view.MapView;


public class HomeActivity extends FragmentActivity
        implements OnMapReadyCallback,MapView {
    private BottomSheetBehavior mBottomSheetBehavior;
    private View bottomSheet;
    private GoogleMap mMap;
    private MapPresenter mapPresenter;
    private final int MAX_SIZE_ZOOM = 15;
    private final int CLOSE_BOTTOM_SHEET = 0;
    private final int HEIGHT_OF_SHOWED_BOTTOM_SHEET = 200;
    private TextView nameText;
    private TextView emailText;
    private TextView adressText;
    private TextView phoneNumberText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_sheet_layout);


        bottomSheet = findViewById( R.id.bottom_sheet );

        mapPresenter = new MapPresenterImpl(this,this);

        this.nameText = findViewById(R.id.textNameBasic);
        this.emailText = findViewById(R.id.textEmailBasic);
        this.phoneNumberText = findViewById(R.id.textPhoneBasic);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);

    }

    private void setupBottomSheet() {
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        mBottomSheetBehavior.setPeekHeight(CLOSE_BOTTOM_SHEET);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setPeekHeight(CLOSE_BOTTOM_SHEET);
                }

            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {

            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        PointMaker currentUser = mapPresenter.getMyLocation();
        mMap.addMarker(new MarkerOptions()
                .position(currentUser.getMyPosition())
                .title(currentUser.getTitle()));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentUser.getMyPosition(),MAX_SIZE_ZOOM));

        /*When a marker was clicked*/
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick( Marker marker ) {
                Integer tagOfMarker = ( Integer ) marker.getTag();
                mapPresenter.onMarkerClick( tagOfMarker );
                return false;
            }
        });
        mapPresenter.seachAvailableApsNearByMe();
        setupBottomSheet();

    }

    @Override
    public void showInformationAboutMarkerClicked(MarkerInformation markerInformation) {
        mBottomSheetBehavior.setPeekHeight(HEIGHT_OF_SHOWED_BOTTOM_SHEET);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        this.nameText.setText(markerInformation.getName());
        this.emailText.setText(markerInformation.getEmail());
        this.phoneNumberText.setText(markerInformation.getPhoneNumber());

    }

    @Override
    public void addAvailableApsOnMap(List<PointMaker> availablesAps) {
        for (PointMaker aps : availablesAps){
            mMap.addMarker(new MarkerOptions()
                    .position(aps.getMyPosition())
                    .title(aps.getTitle()))
                    .setTag(aps.getTag());
        }
    }


}
