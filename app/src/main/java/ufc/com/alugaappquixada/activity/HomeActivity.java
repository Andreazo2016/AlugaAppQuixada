package ufc.com.alugaappquixada.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

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
    private final int max_size_zoom = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_sheet_layout);
        bottomSheet = findViewById( R.id.bottom_sheet );
        mapPresenter = new MapPresenterImpl(this,this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);
        setupBottomSheet();




    }

    private void setupBottomSheet() {
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setPeekHeight(0);
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
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentUser.getMyPosition(),max_size_zoom));

        /*When a marker was clicked*/
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick( Marker marker ) {
                Integer tagOfMarker = ( Integer ) marker.getTag();
                mapPresenter.onMakerClick( tagOfMarker );
                return false;
            }
        });
        mapPresenter.seachAvailableApsNearByMe();
    }

    @Override
    public void addAvailableApsOnMap(List<PointMaker> availablesAps) {
        Bitmap bm = Util.getBitmap(this,R.layout.custom_layout);
        BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(bm);
        for (PointMaker aps : availablesAps){
            mMap.addMarker(new MarkerOptions()
                    .position(aps.getMyPosition())
                    .title(aps.getTitle())
                    .icon(icon))
                    .setTag(aps.getTag());
        }
    }


}
