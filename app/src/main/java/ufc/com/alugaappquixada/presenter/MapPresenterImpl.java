package ufc.com.alugaappquixada.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ufc.com.alugaappquixada.Async.EnterpriseAsync;
import ufc.com.alugaappquixada.Async.EnterprisebyMeAsync;
import ufc.com.alugaappquixada.Config.ConfigRetrofit;
import ufc.com.alugaappquixada.Model.Enterprise;
import ufc.com.alugaappquixada.Model.MarkerInformation;
import ufc.com.alugaappquixada.Model.Owner;
import ufc.com.alugaappquixada.Model.PointMaker;
import ufc.com.alugaappquixada.Model.User;
import ufc.com.alugaappquixada.repository.EnterpriseRepository;
import ufc.com.alugaappquixada.service.EnterpriseService;
import ufc.com.alugaappquixada.service.LocationService;
import ufc.com.alugaappquixada.service.UserService;
import ufc.com.alugaappquixada.view.MapView;

public class MapPresenterImpl implements MapPresenter {
    private MapView mapView;
    private Context ctx;
    private LocationService gpsLocation;
    private final String TAG_CLASS = "MapPresenterImpl_CLASS";
    private EnterpriseService enterpriseService;
    private UserService userService;
    public MapPresenterImpl(Context ctx,MapView mapView){
        this.mapView = mapView;
        this.ctx = ctx;
        this.gpsLocation = new LocationService(ctx);
        this.enterpriseService = new EnterpriseService();
        this.userService = UserService.getInstance();
    }

    @Override
    public void onMarkerClick(Integer tagMarker) {
        /*
        if(tagMarker != null) {
           Enterprise enterpriseCliked =  this.enterpriseService.findEnterpriseById(tagMarker);
            Owner owner = enterpriseCliked.getOwner();
           mapView.showInformationAboutMarkerClicked(MarkerInformation
                   .create(owner.getEmail(),owner.getName(),enterpriseCliked.getDescription(),owner.getPhoneNumber().getNumber()));
        }
        */

        new EnterpriseAsync(mapView).execute(tagMarker);
    }
    @Override
    public void seachAvailableApsNearByMe() {
        /*
       List<PointMaker> listMockPointMaker = new ArrayList<>();
        List<Enterprise> avaliableEnterpriseNaerMe = this.enterpriseService.findAllEnterprise();
        for(Enterprise enterprise : avaliableEnterpriseNaerMe){
            listMockPointMaker.add(PointMaker.create(
                    enterprise.getLatitute(),enterprise.getLongitute()
                    ,enterprise.getDescription()
                    ,enterprise.getId()));
        }
        mapView.addAvailableApsOnMap(listMockPointMaker);
        */
       new EnterprisebyMeAsync(mapView).execute();
    }

    @Override
    public PointMaker getMyLocation() {
        if(!gpsLocation.canGetLocation()){
            gpsLocation.showSettingsAlert();
            Log.d(TAG_CLASS,"Não foi possível pegar sua possição gps!");
        }
        User userLogged = userService.getUserLogged();
        return PointMaker.create(gpsLocation.getLatitude(),gpsLocation.getLongitude(),userLogged.getName(),0);
    }
}
