package com.jwetherell.openmap.overlay;

import java.util.HashMap;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class HashMapItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	private HashMap<String,OverlayItem> mapOverlays = new HashMap<String,OverlayItem>();
	
	public HashMapItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}
    
    public void addOverlay(OverlayItem item) {
        mapOverlays.put(getKey(item),item);
        populate();
    }
    
    public void addOverlay(OverlayItem item, Drawable marker) {
        item.setMarker(boundCenterBottom(marker));
        mapOverlays.put(getKey(item),item);
        populate();
    }
    
    public boolean containsOverlay(OverlayItem item) {
        String key = getKey(item);
        return mapOverlays.containsKey(key);
    }
    
    public void removeOverlay(OverlayItem item) {
        if (containsOverlay(item)) {
            mapOverlays.remove(getKey(item));
            populate();
        }
    }
    
    public void clearOverlays() {
        mapOverlays.clear();
        populate();
    }
    
	private static String getKey(OverlayItem item){
		return item.getTitle()+item.getSnippet();
	}
	
	@Override
	protected OverlayItem createItem(int i) {
		Object[] array = mapOverlays.values().toArray();
		OverlayItem item = (OverlayItem)array[i];
		return item;
	}

	@Override
	public int size() {
		return mapOverlays.size();
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		// draw without a shadow
		super.draw(canvas, mapView, false);
	}
}
