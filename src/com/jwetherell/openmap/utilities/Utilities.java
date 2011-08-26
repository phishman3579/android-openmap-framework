package com.jwetherell.openmap.utilities;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * This abstract class provides many static methods to convert between some primitive types.
 * 
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public abstract class Utilities {
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    public static DecimalFormat NUMBER_FORMAT = new DecimalFormat("0.00");
    public static DecimalFormat NO_DECIMAL_NUMBER_FORMAT = new DecimalFormat("0");
    public static DecimalFormat INTEGER_FORMART = new DecimalFormat("##00"); 

	public static int convertPointToE6(double loc){
		return (int)(loc * 1.0E6);
	}

    public static double convertPointFromE6(int loc){
        return (loc / 1.0E6);
    }
    
    public static int boolToInt(boolean bool){
        if (bool) return 1;
        else return 0;
    }
    
    public static boolean intToBool(int integer){
        if (integer==1) return true;
        else return false;
    }
}
