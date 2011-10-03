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

	/**
	 * Add OverlayItem to Overlay.
	 * @param item OverlayItem to add.
	 * @throws NullPointerException if item is NULL.
	 */
    public void addOverlay(OverlayItem item) {
    	if (item==null) throw new NullPointerException();
    	
        mapOverlays.put(getKey(item),item);
        populate();
    }

	/**
	 * Add OverlayItem with marker as it's default Drawable to Overlay.
	 * @param item OverlayItem to add.
	 * @throws NullPointerException if item is NULL.
	 */
    public void addOverlay(OverlayItem item, Drawable marker) {
    	if (item==null || marker==null) throw new NullPointerException();
    	
        item.setMarker(boundCenterBottom(marker));
        mapOverlays.put(getKey(item),item);
        populate();
    }
    
    /**
     * Does the Overlay contain the given OverlayItem.
     * @param item OverlayItem to check for.
     * @return True of the OverlayItem is in this Overlay.
     */
    public boolean containsOverlay(OverlayItem item) {
    	if (item==null) return false;
    	
        String key = getKey(item);
        return mapOverlays.containsKey(key);
    }
    
    /**
     * Remove the given OverlayItem from the Overlay.
     * @param item OverlayItem to remove.
     */
    public void removeOverlay(OverlayItem item) {
    	if (item==null) return;
    	
        if (containsOverlay(item)) {
            mapOverlays.remove(getKey(item));
            populate();
        }
    }
    
    /**
     * Remove all OverlayItems from the Overlay.
     */
    public void clearOverlays() {
        mapOverlays.clear();
        populate();
    }
    
	private static String getKey(OverlayItem item){
		if (item==null) throw new NullPointerException();
		
		return item.getTitle()+item.getSnippet();
	}
    
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected OverlayItem createItem(int i) {
		Object[] array = mapOverlays.values().toArray();
		OverlayItem item = (OverlayItem)array[i];
		return item;
	}
    
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return mapOverlays.size();
	}
    
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		if (canvas==null || mapView==null) throw new NullPointerException();
		
		// draw without a shadow
		super.draw(canvas, mapView, false);
	}
}
