package com.jwetherell.openmap.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.jwetherell.openmap.R;


/**
 * This abstract class extends Activity to handle drawing the map and setting
 * the default options.
 * 
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public abstract class CustomMapActivity extends MapActivity {

    protected static MapView mapView = null;
    protected static MapController mapController = null;

    private static int zoomLevel = 18;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Create the MapView using my API key
        mapView = new MapView(this, getResources().getString(R.string.mapKey));
        mapView.setSatellite(true);
        mapView.setKeepScreenOn(true);

        // Add the map view to the map frame
        FrameLayout mapFrame = (FrameLayout) this.findViewById(R.id.MapFrame);
        mapFrame.addView(mapView);

        // Set the map controller
        mapController = mapView.getController();

        // Move the map view to the point
        mapController.setZoom(zoomLevel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
