package com.jwetherell.openmap.overlay;

import java.util.HashMap;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

/**
 * This class extends ItemizedOverlay to handle the OverlayItems using a HashMap.
 * 
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public class HashMapItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	private HashMap<String,OverlayItem> mapOverlays = new HashMap<String,OverlayItem>();
	
	public HashMapItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}
    
    public void addOverlay(OverlayItem item) {
    	if (item==null) return;
    	
        mapOverlays.put(getKey(item),item);
        populate();
    }
    
    public void addOverlay(OverlayItem item, Drawable marker) {
    	if (item==null || marker==null) return;
    	
        item.setMarker(boundCenterBottom(marker));
        mapOverlays.put(getKey(item),item);
        populate();
    }
    
    public boolean containsOverlay(OverlayItem item) {
    	if (item==null) return false;
    	
        String key = getKey(item);
        return mapOverlays.containsKey(key);
    }
    
    public void removeOverlay(OverlayItem item) {
    	if (item==null) return;
    	
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
		if (item==null) return null;
		
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
		if (canvas==null || mapView==null) return;
		
		// draw without a shadow
		super.draw(canvas, mapView, false);
	}
}
