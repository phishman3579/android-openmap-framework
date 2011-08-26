package com.jwetherell.openmap.activity;

import java.util.List;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.jwetherell.openmap.R;
import com.jwetherell.openmap.data.UserData;
import com.jwetherell.openmap.overlay.HashMapItemizedOverlay;

/**
 * This class extends CustomMapActivity to keep the current location centered on the screen.
 * 
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public abstract class CenteredMapActivity extends CustomMapActivity {
	protected static List<Overlay> mapOverlays = null;

	protected static HashMapItemizedOverlay gpsOverlay = null;
	private static OverlayItem overlayItem = null;
	private static Drawable icon = null;

	protected static LocationManager locationManager = null;
	protected static LocationListener locationListener = null;
	protected static int minMeters = 10;
	protected static int minTime = 10;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Get the location manager
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		UserData.setLocation(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
		locationListener = new MapLocationListener();

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minMeters, locationListener);

		// Find the most recent lat/lon or if none use the default
		GeoPoint point = new GeoPoint(UserData.getLatitudeE6(), UserData.getLongitudeE6());

		// Move the map view to the point
		mapController.animateTo(point);

		// Get the overlays and add our GPS point
		mapOverlays = mapView.getOverlays();
		icon = getResources().getDrawable(R.drawable.icon);
		gpsOverlay = new HashMapItemizedOverlay(icon);
		overlayItem = new OverlayItem(point, "MyGps", "The current position of my GPS");
		gpsOverlay.addOverlay(overlayItem);
		mapOverlays.add(gpsOverlay);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		locationManager.removeUpdates(locationListener);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		setCenterOnGps();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minMeters,locationListener);
	}

	@Override
	public void onStop() {
		super.onStop();
		locationManager.removeUpdates(locationListener);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		setCenterOnGps();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minMeters,locationListener);
	}

	@Override
	public void onPause() {
		super.onPause();
		locationManager.removeUpdates(locationListener);
	}

	protected void setCenterOnGps() {
		Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		updateLocation(loc);
	}

	protected void updateLocation(Location loc) {
		UserData.setLocation(loc);
		GeoPoint point = new GeoPoint(UserData.getLatitudeE6(), UserData.getLongitudeE6());
		
		gpsOverlay.removeOverlay(overlayItem);
		gpsOverlay.addOverlay(new OverlayItem(point, "MyGps", "The current position of my GPS"));
		mapView.invalidate();

		mapController.animateTo(point);
	}

	private class MapLocationListener implements LocationListener {
		public void onLocationChanged(Location loc) {
			if (loc == null) return;
			
			UserData.setLocation(loc);
			updateLocation(loc);
		}

		public void onProviderDisabled(String provider) {
			// Ignore
		}

		public void onProviderEnabled(String provider) {
			// Ignore
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// Ignore
		}
	}
}
