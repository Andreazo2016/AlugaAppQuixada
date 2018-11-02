package ufc.com.alugaappquixada.Model;

import com.google.android.gms.maps.model.LatLng;

public class PointMaker {
    private double latitute;
    private double longitude;
    private String title;
    private Integer tag;

    private PointMaker(){}
    private PointMaker(double latitute, double longitude){
        this.latitute = latitute;
        this.longitude = longitude;
    }

    private PointMaker(double latitute, double longitude, String title,Integer tag) {
        this.latitute = latitute;
        this.longitude = longitude;
        this.title = title;
        this.tag = tag;
    }
    public static PointMaker createPointMakerFromLatLong(double latitute, double longitude){
        return new PointMaker(latitute,longitude);
    }

    public static PointMaker create(double latitute, double longitude, String title,Integer tag){
        return new PointMaker(latitute,longitude,title,tag);
    }

    public double getLatitute() {
        return latitute;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTitle() {
        return title;
    }

    public Integer getTag() {
        return tag;
    }
    public LatLng getMyPosition(){
        return new LatLng(getLatitute(),getLongitude());
    }


}
