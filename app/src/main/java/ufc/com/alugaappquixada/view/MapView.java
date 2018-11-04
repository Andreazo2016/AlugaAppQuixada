package ufc.com.alugaappquixada.view;

import java.util.List;

import ufc.com.alugaappquixada.Model.MarkerInformation;
import ufc.com.alugaappquixada.Model.PointMaker;

public interface MapView {
    void showInformationAboutMarkerClicked(MarkerInformation markerInformation);
     void addAvailableApsOnMap( List<PointMaker> availablesAps );
}
