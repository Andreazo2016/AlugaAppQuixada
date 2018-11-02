package ufc.com.alugaappquixada.presenter;

import ufc.com.alugaappquixada.Model.PointMaker;

public interface MapPresenter {
 public void onMakerClick(Integer tagMarker);
 public void seachAvailableApsNearByMe();
 public PointMaker getMyLocation();
}
