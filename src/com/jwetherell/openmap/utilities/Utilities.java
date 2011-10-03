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

    /**
     * Convert a double point to an E6 point.
     * @param loc Double point to convert.
     * @return Integer point using E6 notation.
     */
	public static int convertPointToE6(double loc){
		return (int)(loc * 1.0E6);
	}

	/**
	 * Convert an integer E6 point to a double point.
	 * @param loc Integer point using E6 notation.
	 * @return Double point.
	 */
    public static double convertPointFromE6(int loc){
        return (loc / 1.0E6);
    }
}
