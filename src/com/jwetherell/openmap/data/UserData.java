package com.jwetherell.openmap.data;

import com.jwetherell.openmap.common.MGRSPoint;
import com.jwetherell.openmap.utilities.Utilities;
import android.location.Location;

public abstract class UserData {
    private static Location currentLocation = null;
    private static double initialLatitude = 39.931261;
    private static double initialLongitude = -75.051267;
    
    private static COORDINATES coord = COORDINATES.MGRS;
    private static ACCURACY accuracy = ACCURACY.ACCURACY_1_METER;
    private static DISTANCE distance = DISTANCE.MILES;
    private static AZIMUTH azimuth = AZIMUTH.DEGREES_360;

    public static enum COORDINATES { LAT_LON, UTM, MGRS };
    public static int coordinatesToInt(COORDINATES coordinates) {
        return coordinates.ordinal();
    }
    public static COORDINATES intToCoordinates(int coordinates) {
        return COORDINATES.class.getEnumConstants()[coordinates];
    }
    public static String coordinatesToString(COORDINATES coordinates) {
        if (coordinates == COORDINATES.LAT_LON) return "Latitude / Longitude";
        if (coordinates == COORDINATES.UTM) return "UTM";
        if (coordinates == COORDINATES.MGRS) return "MGRS";
        return "Unknown";
    }
    
    public static enum ACCURACY { ACCURACY_1_METER, ACCURACY_10_METER, ACCURACY_100_METER, ACCURACY_1000_METER, ACCURACY_10000_METER};
    public static int accuracyToInt(ACCURACY accuracy) {
        return accuracy.ordinal();
    }
    public static ACCURACY intToAccuracy(int accuracy) {
        return ACCURACY.class.getEnumConstants()[accuracy];
    }
    public static int accuracyToMgrsAccuracy(ACCURACY idx) {
        if (idx==ACCURACY.ACCURACY_1_METER) return MGRSPoint.ACCURACY_1_METER;
        if (idx==ACCURACY.ACCURACY_10_METER) return MGRSPoint.ACCURACY_10_METER;
        if (idx==ACCURACY.ACCURACY_100_METER) return MGRSPoint.ACCURACY_100_METER;
        if (idx==ACCURACY.ACCURACY_1000_METER) return MGRSPoint.ACCURACY_1000_METER;
        if (idx==ACCURACY.ACCURACY_10000_METER) return MGRSPoint.ACCURACY_10000_METER;
        return MGRSPoint.ACCURACY_1_METER;
    }
    public static ACCURACY mgrsAccuracyToAccuracy(int acc) {
        if (acc==MGRSPoint.ACCURACY_1_METER) return ACCURACY.ACCURACY_1_METER;
        if (acc==MGRSPoint.ACCURACY_10_METER) return ACCURACY.ACCURACY_10_METER;
        if (acc==MGRSPoint.ACCURACY_100_METER) return ACCURACY.ACCURACY_100_METER;
        if (acc==MGRSPoint.ACCURACY_1000_METER) return ACCURACY.ACCURACY_1000_METER;
        if (acc==MGRSPoint.ACCURACY_10000_METER) return ACCURACY.ACCURACY_10000_METER;
        return ACCURACY.ACCURACY_1_METER;
    }
    public static String accuracyToString() {
        if (accuracy.equals(ACCURACY.ACCURACY_1_METER)) return "1 meter";
        if (accuracy.equals(ACCURACY.ACCURACY_10_METER)) return "10 meter";
        if (accuracy.equals(ACCURACY.ACCURACY_100_METER)) return "100 meter";
        if (accuracy.equals(ACCURACY.ACCURACY_1000_METER)) return "1000 meter";
        if (accuracy.equals(ACCURACY.ACCURACY_1000_METER)) return "10000 meter";
        return "Unknown";
    }
    
    public static enum DISTANCE { FEET, METERS, MILES, KM };
    public static int distanceToInt(DISTANCE distance) {
        return distance.ordinal();
    }
    public static DISTANCE intToDistance(int distance) {
        return DISTANCE.class.getEnumConstants()[distance];
    }
    public static String distanceToString(DISTANCE distance) {
        if (distance == DISTANCE.FEET) return "feet";
        if (distance == DISTANCE.METERS) return "meters";
        if (distance == DISTANCE.MILES) return "miles";
        if (distance == DISTANCE.KM) return "km";
        return "Unknown";
    }
    
    public static enum AZIMUTH { DEGREES_360, DEGREES_180, DEGREES_PI, DEGREES_MILS };
    public static int azimuthToInt(AZIMUTH azimuth) {
        return azimuth.ordinal();
    }
    public static AZIMUTH intToAzimuth(int azimuth) {
        return AZIMUTH.class.getEnumConstants()[azimuth];
    }
    public static String azimuthToString(AZIMUTH azimuth) {
        if (azimuth == AZIMUTH.DEGREES_360) return "0 to 360 degrees";
        if (azimuth == AZIMUTH.DEGREES_180) return "-180 to 180 degrees";
        if (azimuth == AZIMUTH.DEGREES_PI) return "-PI to PI degrees";
        if (azimuth == AZIMUTH.DEGREES_MILS) return "0 to 6400 mils";
        return "Unknown";
    }
    
    public static Location getLocation(){
        if (currentLocation == null) {
            currentLocation = new Location("default");
            currentLocation.setLatitude(initialLatitude);
            currentLocation.setLongitude(initialLongitude);
        }
        return currentLocation;
    }
    
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
       
    public static double getLatitude(){
        if(currentLocation != null) return (currentLocation.getLatitude());
        return (initialLatitude);
    }
    
    public static double getLongitude(){
        if(currentLocation != null) return (currentLocation.getLongitude());
        return (initialLongitude);
    }
    
    public static int getLatitudeE6(){
        return Utilities.convertPointToE6(getLatitude());
    }
    
    public static int getLongitudeE6(){
        return Utilities.convertPointToE6(getLongitude());
    }
    
    public static void setCoordinates(COORDINATES coord) {
        UserData.coord = coord;
    }
    public static COORDINATES getCoordinates() {
        return coord;
    }

    public static void setAccuracy(ACCURACY accuracy) {
        UserData.accuracy = accuracy;
    }
    public static ACCURACY getAccuracy() {
        return accuracy;
    }

    public static void setDistance(DISTANCE distance) {
        UserData.distance = distance;
    }
    public static DISTANCE getDistance() {
        return distance;
    }

    public static void setAzimuth(AZIMUTH azimuth) {
        UserData.azimuth = azimuth;
    }
    public static AZIMUTH getAzimuth() {
        return azimuth;
    }
}
