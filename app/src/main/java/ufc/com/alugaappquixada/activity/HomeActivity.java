package ufc.com.alugaappquixada.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ufc.com.alugaappquixada.Config.ConfigRetrofit;
import ufc.com.alugaappquixada.Model.Enterprise;
import ufc.com.alugaappquixada.Model.MarkerInformation;
import ufc.com.alugaappquixada.Model.PointMaker;
import ufc.com.alugaappquixada.Model.User;
import ufc.com.alugaappquixada.R;
import ufc.com.alugaappquixada.presenter.MapPresenter;
import ufc.com.alugaappquixada.presenter.MapPresenterImpl;
import ufc.com.alugaappquixada.repository.EnterpriseRepository;
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
    private TextView phoneNumberText;
    private CircleImageView userOwenerImage;
    private Button buttonViewDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_sheet_layout);
        buttonViewDetails = findViewById(R.id.buttonViewMore);
        bottomSheet = findViewById( R.id.bottom_sheet );
        userOwenerImage = findViewById(R.id.user_image);

        mapPresenter = new MapPresenterImpl(this,this);

        this.nameText = findViewById(R.id.textNameBasic);
        this.emailText = findViewById(R.id.textEmailBasic);
        this.phoneNumberText = findViewById(R.id.textPhoneBasic);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);

        buttonViewDetails.setOnClickListener( (view) -> {
            startActivity(new Intent(this,DetailsApartmentActivity.class));
        });

    }

    private void setupBottomSheet() {
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setHideable(true);
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
        Bitmap customIconForCurrentUserLoged = Util.createCustomMarkerUser(this,R.drawable.user_image_min);

        mMap.addMarker(new MarkerOptions()
                .position(currentUser.getMyPosition())
                        .title(currentUser.getTitle())).setIcon(BitmapDescriptorFactory.fromBitmap(customIconForCurrentUserLoged));

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
        userOwenerImage.setImageResource(R.drawable.user_image_min);

    }

    @Override
    public void addAvailableApsOnMap(List<PointMaker> availablesAps) {
        Bitmap customIconavailablesAps = Util.createCustomMarker(this,R.drawable.building_icon_min);
        for (PointMaker aps : availablesAps){
            Marker pointCreated = mMap.addMarker(new MarkerOptions().position(aps.getMyPosition()).title(aps.getTitle()));
            pointCreated.setTag(aps.getTag());
            pointCreated.setIcon(BitmapDescriptorFactory.fromBitmap(customIconavailablesAps));
        }
    }


}
