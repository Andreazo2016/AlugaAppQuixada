package ufc.com.alugaappquixada.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import ufc.com.alugaappquixada.Model.User;
import ufc.com.alugaappquixada.R;

public class Util {
    private static  User user;
    private static String KEY_USER_LOGGED = "USER_LOGGED";
    public static Bitmap createCustomMarker(Context context, @DrawableRes int resource) {


        View marker = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_user, null);

        CircleImageView markerImage = (CircleImageView) marker.findViewById(R.id.user_dp);
        markerImage.setImageResource(resource);

        /*
        TextView txt_name = (TextView)marker.findViewById(R.id.name);
        txt_name.setText(_name);
           */
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        marker.setLayoutParams(new ViewGroup.LayoutParams(52, ViewGroup.LayoutParams.WRAP_CONTENT));
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(marker.getMeasuredWidth(), marker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        marker.draw(canvas);

        return bitmap;
    }
    public static Bitmap createCustomMarkerUser(Context context, @DrawableRes int resource) {
        User user = getUserLogged(context);

        View marker = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_user, null);

        CircleImageView markerImage = (CircleImageView) marker.findViewById(R.id.user_dp);
        //markerImage.setImageResource(resource);
        markerImage.setImageBitmap(base64ToBitMap(user.getFaceImage()));
        /*
        TextView txt_name = (TextView)marker.findViewById(R.id.name);
        txt_name.setText(_name);
           */
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        marker.setLayoutParams(new ViewGroup.LayoutParams(52, ViewGroup.LayoutParams.WRAP_CONTENT));
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(marker.getMeasuredWidth(), marker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        marker.draw(canvas);

        return bitmap;
    }
    public static Bitmap getBitmap(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
            return BitmapFactory.decodeResource(context.getResources(), drawableId);

    }
    public static void saveUserLoged(Context ctx,User user){
        SharedPreferences  mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String userSaved = gson.toJson(user);
        int i = 0;
        prefsEditor.putString(user.getEmail(), userSaved);
        prefsEditor.commit();
    }
    public static User getUserByUserName(Context ctx,String username){
        SharedPreferences  mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        Gson gson = new Gson();
        String json = mPrefs.getString(username, null);
         return gson.fromJson(json, User.class);
    }
    public static User getUserLogged(Context ctx){
        return getUserByUserName(ctx,KEY_USER_LOGGED);
    }
    public static void saveUserOnSesion(Context ctx,User user){
        SharedPreferences  mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String userSaved = gson.toJson(user);
        prefsEditor.putString(KEY_USER_LOGGED, userSaved);
        prefsEditor.commit();
    }
    public static String convertBitMapToBase64(Bitmap image){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
    public static Bitmap base64ToBitMap(String image){
        byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
       return  BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
