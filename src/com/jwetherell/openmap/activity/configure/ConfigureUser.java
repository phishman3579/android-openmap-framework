package com.jwetherell.openmap.activity.configure;

import com.jwetherell.openmap.R;
import com.jwetherell.openmap.data.UserData;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


/**
 * This class extends Activity and allows the user to set the default options for the map.
 * 
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public class ConfigureUser extends Activity {

    /**
     * {@inheritDoc}
     */
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//No title bar and set the content from the xml
		setContentView(R.layout.configure_user);

		Spinner coords = (Spinner) findViewById(R.id.input_coords_spinner);
		ArrayAdapter<CharSequence> coordsAdapter = 
			ArrayAdapter.createFromResource(this, R.array.coords, android.R.layout.simple_spinner_item);
		coordsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		coords.setOnItemSelectedListener(coordsListener);
		coords.setAdapter(coordsAdapter);
		int unit = UserData.coordinatesToInt(UserData.getCoordinates());
		coords.setSelection(unit);
	      
        Spinner accuracyMode = (Spinner) findViewById(R.id.input_accuracy_spinner);
        ArrayAdapter<CharSequence> modesAdapter = 
            ArrayAdapter.createFromResource(this, R.array.accuracy, android.R.layout.simple_spinner_item);
        modesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accuracyMode.setOnItemSelectedListener(accuracyListener);
        accuracyMode.setAdapter(modesAdapter);
        int accuracy = UserData.accuracyToInt(UserData.getAccuracy());
        accuracyMode.setSelection(accuracy);
        
        Spinner distance = (Spinner) findViewById(R.id.input_distance_spinner);
        ArrayAdapter<CharSequence> distanceAdapter = 
            ArrayAdapter.createFromResource(this, R.array.distance, android.R.layout.simple_spinner_item);
        distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distance.setOnItemSelectedListener(distanceListener);
        distance.setAdapter(distanceAdapter);
        int dist = UserData.distanceToInt(UserData.getDistance());
        distance.setSelection(dist);
        
        Spinner azimuth = (Spinner) findViewById(R.id.input_azimuth_spinner);
        ArrayAdapter<CharSequence> azimuthAdapter = 
            ArrayAdapter.createFromResource(this, R.array.azimuth, android.R.layout.simple_spinner_item);
        azimuthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        azimuth.setOnItemSelectedListener(azimuthListener);
        azimuth.setAdapter(azimuthAdapter);
        int azi = UserData.azimuthToInt(UserData.getAzimuth());
        azimuth.setSelection(azi);
        
		Button done = (Button) findViewById(R.id.configure_user_done);
		done.setOnClickListener(doneListener);
	}

    private OnItemSelectedListener azimuthListener = new OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            UserData.setAzimuth(UserData.intToAzimuth(arg2));
        }
        public void onNothingSelected(AdapterView<?> arg0) {
            //Ignore
        }
    };
    
    private OnItemSelectedListener distanceListener = new OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            UserData.setDistance(UserData.intToDistance(arg2));
        }
        public void onNothingSelected(AdapterView<?> arg0) {
            //Ignore
        }
    };
    
	private OnItemSelectedListener accuracyListener = new OnItemSelectedListener() {
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			UserData.setAccuracy(UserData.intToAccuracy(arg2));
		}
		public void onNothingSelected(AdapterView<?> arg0) {
			//Ignore
		}
	};
	
	private OnItemSelectedListener coordsListener = new OnItemSelectedListener() {
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			UserData.setCoordinates(UserData.intToCoordinates(arg2));
		}
		public void onNothingSelected(AdapterView<?> arg0) {
			//Ignore
		}
	};

	private OnClickListener doneListener = new OnClickListener() {
		public void onClick(View v) {
			finish();
		}
	};
}
