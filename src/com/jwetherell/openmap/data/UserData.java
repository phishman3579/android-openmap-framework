package com.jwetherell.openmap.data;

import com.jwetherell.openmap.common.MGRSPoint;
import com.jwetherell.openmap.utilities.Utilities;
import android.location.Location;


/**
 * This abstract class is used to save globally accessible data.
 * 
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public abstract class UserData {
    private static Location currentLocation = null;
    private static double initialLatitude = 39.931261;
    private static double initialLongitude = -75.051267;
    
    private static COORDINATES coord = COORDINATES.MGRS;
    private static ACCURACY accuracy = ACCURACY.ACCURACY_1_METER;
    private static DISTANCE distance = DISTANCE.MILES;
    private static AZIMUTH azimuth = AZIMUTH.DEGREES_360;

    /** Available coordinate systems */
    public static enum COORDINATES { LAT_LON, UTM, MGRS };
    /**
     * Convert the Coordinates Enum to it's integer value.
     * @param coordinates Coordinates Enum to convert.
     * @return Integer representation of the Coordinates Enum param.
     */
    public static int coordinatesToInt(COORDINATES coordinate) {
        return coordinate.ordinal();
    }
    /**
     * Convert the integer to the Coordinates Enum.
     * @param coordinate Integer to convert.
     * @return COORDINATES value of the integer param.
     */
    public static COORDINATES intToCoordinates(int coordinate) {
        return COORDINATES.class.getEnumConstants()[coordinate];
    }
    /**
     * Get string value of the Coordinates Enum.
     * @param coordinates Enum to get String value of.
     * @return String representing the given Enum.
     */
    public static String coordinatesToString(COORDINATES coordinate) {
        if (coordinate == COORDINATES.LAT_LON) return "Latitude / Longitude";
        if (coordinate == COORDINATES.UTM) return "UTM";
        if (coordinate == COORDINATES.MGRS) return "MGRS";
        return "Unknown";
    }
    
    /** Available MGRS accuracy */
    public static enum ACCURACY { ACCURACY_1_METER, ACCURACY_10_METER, ACCURACY_100_METER, ACCURACY_1000_METER, ACCURACY_10000_METER};
    /**
     * Convert the Accuracy Enum to it's integer value.
     * @param accuracy Accuracy Enum to convert.
     * @return Integer representation of the Accuracy Enum param.
     */
    public static int accuracyToInt(ACCURACY accuracy) {
        return accuracy.ordinal();
    }
    /**
     * Convert the integer to the Accuracy Enum.
     * @param accuracy Integer to convert.
     * @return Accuracy Enum represented by the integer param. 
     */
    public static ACCURACY intToAccuracy(int accuracy) {
        return ACCURACY.class.getEnumConstants()[accuracy];
    }
    /**
     * Convert the given Accuracy Enum to a MGRS accuracy.
     * @param idx Accuracy Enum to convert.
     * @return MGRS accuracy.
     */
    public static int accuracyToMgrsAccuracy(ACCURACY idx) {
        if (idx==ACCURACY.ACCURACY_1_METER) return MGRSPoint.ACCURACY_1_METER;
        if (idx==ACCURACY.ACCURACY_10_METER) return MGRSPoint.ACCURACY_10_METER;
        if (idx==ACCURACY.ACCURACY_100_METER) return MGRSPoint.ACCURACY_100_METER;
        if (idx==ACCURACY.ACCURACY_1000_METER) return MGRSPoint.ACCURACY_1000_METER;
        if (idx==ACCURACY.ACCURACY_10000_METER) return MGRSPoint.ACCURACY_10000_METER;
        return MGRSPoint.ACCURACY_1_METER;
    }
    /**
     * Convert the given MGRS accuracy to Accuracy Enum.
     * @param acc MGRS accuracy.
     * @return Accuracy Enum accuracy.
     */
    public static ACCURACY mgrsAccuracyToAccuracy(int acc) {
        if (acc==MGRSPoint.ACCURACY_1_METER) return ACCURACY.ACCURACY_1_METER;
        if (acc==MGRSPoint.ACCURACY_10_METER) return ACCURACY.ACCURACY_10_METER;
        if (acc==MGRSPoint.ACCURACY_100_METER) return ACCURACY.ACCURACY_100_METER;
        if (acc==MGRSPoint.ACCURACY_1000_METER) return ACCURACY.ACCURACY_1000_METER;
        if (acc==MGRSPoint.ACCURACY_10000_METER) return ACCURACY.ACCURACY_10000_METER;
        return ACCURACY.ACCURACY_1_METER;
    }
    /**
     * Get string value of the Accuracy Enum.
     * @param coordinates Enum to get String value of.
     * @return String representing the given Enum.
     */
    public static String accuracyToString(ACCURACY acc) {
        if (acc.equals(ACCURACY.ACCURACY_1_METER)) return "1 meter";
        if (acc.equals(ACCURACY.ACCURACY_10_METER)) return "10 meter";
        if (acc.equals(ACCURACY.ACCURACY_100_METER)) return "100 meter";
        if (acc.equals(ACCURACY.ACCURACY_1000_METER)) return "1000 meter";
        if (acc.equals(ACCURACY.ACCURACY_1000_METER)) return "10000 meter";
        return "Unknown";
    }
    
    /** Available distance formats */
    public static enum DISTANCE { FEET, METERS, MILES, KM };
    /**
     * Convert the integer value of the distance Distance Enum.
     * @param distance Distance Enum to convert.
     * @return Integer value of the Enum.
     */
    public static int distanceToInt(DISTANCE distance) {
        return distance.ordinal();
    }
    /**
     * Convert the Distance Enum given an integer value.
     * @param distance Integer to convert.
     * @return Distance Enum of the integer param.
     */
    public static DISTANCE intToDistance(int distance) {
        return DISTANCE.class.getEnumConstants()[distance];
    }
    /**
     * Get string value of the Distance Enum.
     * @param distance Enum to get String value of.
     * @return String representing the given Enum.
     */
    public static String distanceToString(DISTANCE distance) {
        if (distance == DISTANCE.FEET) return "feet";
        if (distance == DISTANCE.METERS) return "meters";
        if (distance == DISTANCE.MILES) return "miles";
        if (distance == DISTANCE.KM) return "km";
        return "Unknown";
    }
    
    /** Available azimuth formats */
    public static enum AZIMUTH { DEGREES_360, DEGREES_180, DEGREES_PI, DEGREES_MILS };
    /**
     * Convert the integer value of the distance Azimuth Enum.
     * @param azimuth Azimuth Enum to convert.
     * @return Integer value of the Enum.
     */
    public static int azimuthToInt(AZIMUTH azimuth) {
        return azimuth.ordinal();
    }
    /**
     * Convert the Azimuth Enum given an integer value.
     * @param azimuth Integer to convert.
     * @return Azimuth Enum of the integer param.
     */
    public static AZIMUTH intToAzimuth(int azimuth) {
        return AZIMUTH.class.getEnumConstants()[azimuth];
    }
    /**
     * Get string value of the Azimuth Enum.
     * @param azimuth Enum to get String value of.
     * @return String representing the given Enum.
     */
    public static String azimuthToString(AZIMUTH azimuth) {
        if (azimuth == AZIMUTH.DEGREES_360) return "0 to 360 degrees";
        if (azimuth == AZIMUTH.DEGREES_180) return "-180 to 180 degrees";
        if (azimuth == AZIMUTH.DEGREES_PI) return "-PI to PI degrees";
        if (azimuth == AZIMUTH.DEGREES_MILS) return "0 to 6400 mils";
        return "Unknown";
    }
    
    /**
     * Get the current location.
     * Note: Defaults to initialLatitude and initialLongitude if no known location.
     * @return Location representing current location.
     */
    public static Location getLocation(){
        if (currentLocation == null) {
            currentLocation = new Location("default");
            currentLocation.setLatitude(initialLatitude);
            currentLocation.setLongitude(initialLongitude);
        }
        return currentLocation;
    }
    
    /**
     * Set the current location.
     * Note: Defaults to initialLatitude and initialLongitude if Location param is NULL.
     * @param loc Location to set as current.
     */
    public static void setLocation(Location loc){
        if (loc != null) {
            currentLocation=loc;
        } else {
            loc = new Location("default");
            loc.setLatitude(initialLatitude);
            loc.setLongitude(initialLongitude);
            currentLocation = loc;
        }
    }

    /**
     * Get current latitude.
     * Note: returns initialLatitude if currentLocation is NULL.
     * @return Double representation of the latitude.
     */
    public static double getLatitude(){
        if(currentLocation != null) return (currentLocation.getLatitude());
        return (initialLatitude);
    }

    /**
     * Get current longitude.
     * Note: returns initialLongitude if currentLocation is NULL.
     * @return Double representation of the longitude.
     */
    public static double getLongitude(){
        if(currentLocation != null) return (currentLocation.getLongitude());
        return (initialLongitude);
    }

    /**
     * Get current latitude in integer E6 format.
     * @return Double representation of the latitude.
     */
    public static int getLatitudeE6(){
        return Utilities.convertPointToE6(getLatitude());
    }

    /**
     * Get current longitude in integer E6 format.
     * @return Double representation of the longitude.
     */
    public static int getLongitudeE6(){
        return Utilities.convertPointToE6(getLongitude());
    }
    
    /**
     * Set the coordinate system to use.
     * @param coord Coordinte system to use.
     */
    public static void setCoordinates(COORDINATES coord) {
        UserData.coord = coord;
    }
    /**
     * Get the coordinate system in use.
     * @return Coordinate system in use.
     */
    public static COORDINATES getCoordinates() {
        return coord;
    }

    /**
     * Set the accuracy.
     * @param accuracy Accuracy to set.
     */
    public static void setAccuracy(ACCURACY accuracy) {
        UserData.accuracy = accuracy;
    }
    /**
     * Get the accuracy.
     * @return Accuracy in use.
     */
    public static ACCURACY getAccuracy() {
        return accuracy;
    }

    /**
     * Set the distance to use.
     * @param distance Distance to use.
     */
    public static void setDistance(DISTANCE distance) {
        UserData.distance = distance;
    }
    /**
     * Get the current distance.
     * @return Distance in use.
     */
    public static DISTANCE getDistance() {
        return distance;
    }

    /**
     * Set the Azimuth to use.
     * @param azimuth Azimuth to use.
     */
    public static void setAzimuth(AZIMUTH azimuth) {
        UserData.azimuth = azimuth;
    }
    /**
     * Get the Azimuth in use.
     * @return Azimuth in use.
     */
    public static AZIMUTH getAzimuth() {
        return azimuth;
    }
}
