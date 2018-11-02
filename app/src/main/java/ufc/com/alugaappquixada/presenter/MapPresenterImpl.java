package ufc.com.alugaappquixada.presenter;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ufc.com.alugaappquixada.Model.PointMaker;
import ufc.com.alugaappquixada.service.LocationService;
import ufc.com.alugaappquixada.view.MapView;

public class MapPresenterImpl implements MapPresenter {
    private MapView mapView;
    private Context ctx;
    private LocationService gpsLocation;
    private final String TAG_CLASS = "MapPresenterImpl_CLASS";
    public MapPresenterImpl(Context ctx,MapView mapView){
        this.mapView = mapView;
        this.ctx = ctx;
        this.gpsLocation = new LocationService(ctx);
    }

    @Override
    public void onMakerClick(Integer tagMarker) {

    }

    @Override
    public void seachAvailableApsNearByMe() {
        List<PointMaker> listMockPointMaker = new ArrayList<>();
        listMockPointMaker.add(PointMaker.create(-5.7323739,-39.0102391,"José Oster Machado",1));
        listMockPointMaker.add(PointMaker.create(-5.7320133,-39.010271,"Mr Dog",2));
        listMockPointMaker.add(PointMaker.create(-5.732563,-39.0107431,"Bar da Telma",3));
        mapView.addAvailableApsOnMap(listMockPointMaker);
    }

    @Override
    public PointMaker getMyLocation() {
        if(!gpsLocation.canGetLocation()){
            gpsLocation.showSettingsAlert();
            Log.d(TAG_CLASS,"Não foi possível pegar sua possição gps!");
        }
        return PointMaker
                .create(gpsLocation.getLatitude(),gpsLocation.getLongitude(),"me",0);
    }
}
