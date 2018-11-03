package ufc.com.alugaappquixada.presenter;

import ufc.com.alugaappquixada.Model.PointMaker;

public interface MapPresenter {
 public void onMarkerClick(Integer tagMarker);
 public void seachAvailableApsNearByMe();
 public PointMaker getMyLocation();
}
