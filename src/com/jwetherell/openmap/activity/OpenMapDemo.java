package com.jwetherell.openmap.activity;

import com.google.android.maps.GeoPoint;
import com.jwetherell.openmap.R;
import com.jwetherell.openmap.activity.configure.ConfigureUser;
import com.jwetherell.openmap.common.LatLonPoint;
import com.jwetherell.openmap.common.Length;
import com.jwetherell.openmap.common.MGRSPoint;
import com.jwetherell.openmap.common.ProjMath;
import com.jwetherell.openmap.common.UTMPoint;
import com.jwetherell.openmap.data.MessageUtilities;
import com.jwetherell.openmap.data.UserData;
import com.jwetherell.openmap.utilities.Utilities;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


/**
 * This class extends CenteredMapActivity to handle the user interaction with
 * the map.
 * 
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public class OpenMapDemo extends CenteredMapActivity {

    private static final String htmlSpace = "&nbsp; ";
    private static LatLonPoint lastLocation = null;
    private static LatLonPoint lastClickLocation = null;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mapView.setClickable(true);
        mapView.setOnTouchListener(onTouchMapListener);

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        updateLocation(location);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLocation(Location loc) {
        super.updateLocation(loc);

        double myLat = Utilities.convertPointFromE6(UserData.getLatitudeE6());
        double myLon = Utilities.convertPointFromE6(UserData.getLongitudeE6());
        lastLocation = new LatLonPoint(myLat, myLon);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.configure:
                intent = new Intent(getApplicationContext(), ConfigureUser.class);
                startActivity(intent);
                return true;
            case R.id.exit:
                finish();
                return true;
            default:
                return false;
        }
    }

    private boolean handleClick(int latE6, int lonE6) {
        if (lastLocation == null) return false;

        double lastClickLat = Utilities.convertPointFromE6(latE6);
        double lastClickLon = Utilities.convertPointFromE6(lonE6);
        lastClickLocation = new LatLonPoint(lastClickLat, lastClickLon);

        String location = "";
        if (UserData.getCoordinates() == UserData.COORDINATES.LAT_LON) {
            location = lastClickLocation.toString();
        } else if (UserData.getCoordinates() == UserData.COORDINATES.UTM) {
            UTMPoint utmPoint = new UTMPoint(lastClickLocation);
            location = utmPoint.toString();
        } else {
            MGRSPoint mgrsPoint = MGRSPoint.LLtoMGRS(lastClickLocation);
            mgrsPoint.resolve(UserData.accuracyToMgrsAccuracy(UserData.getAccuracy()));
            location = mgrsPoint.toString();
        }

        Length lengthInRadians = null;
        if (UserData.getDistance() == UserData.DISTANCE.MILES) {
            lengthInRadians = Length.get("mile");
        } else if (UserData.getDistance() == UserData.DISTANCE.KM) {
            lengthInRadians = Length.get("km");
        } else if (UserData.getDistance() == UserData.DISTANCE.METERS) {
            lengthInRadians = Length.get("m");
        } else {
            lengthInRadians = Length.get("feet");
        }
        double length = lengthInRadians.fromRadians(lastLocation.distance(lastClickLocation));

        String units = "radians";
        double azimuth = lastLocation.azimuth(lastClickLocation);
        if ((UserData.getAzimuth() == UserData.AZIMUTH.DEGREES_180) || (UserData.getAzimuth() == UserData.AZIMUTH.DEGREES_360)) {
            units = "degrees";
            azimuth = ProjMath.radToDeg(azimuth);
            if (UserData.getAzimuth() == UserData.AZIMUTH.DEGREES_360) azimuth = (azimuth < 0) ? 360 + azimuth : azimuth;
        } else if (UserData.getAzimuth() == UserData.AZIMUTH.DEGREES_MILS) {
            units = "mils";
            azimuth = ProjMath.radToMils(azimuth);
        }

        String html = location + "<br>" + "Range:" + htmlSpace + Utilities.NUMBER_FORMAT.format(length) + htmlSpace + lengthInRadians.getAbbr() + "<br>"
                + "Azimuth:" + htmlSpace + Utilities.NUMBER_FORMAT.format(azimuth) + htmlSpace + units;

        MessageUtilities.alertUser(getApplicationContext(), Html.fromHtml(html).toString());

        return true;
    }

    private OnTouchListener onTouchMapListener = new OnTouchListener() {

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int x = Math.round(event.getX());
            int y = Math.round(event.getY());
            GeoPoint point = mapView.getProjection().fromPixels(x, y);

            if (event.getAction() == MotionEvent.ACTION_UP) {
                handleClick(point.getLatitudeE6(), point.getLongitudeE6());
            }
            return false;
        }
    };
}
