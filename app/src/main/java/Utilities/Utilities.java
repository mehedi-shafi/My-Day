package Utilities;

/**
 * Created by Shafi on 12/2/2017.
 */

public class Utilities {

    public static boolean getBool(String val){
        if(val.toLowerCase().compareTo("true") == 0){
            return true;
        }
        return false;
    }

    public static String getBoolString(boolean val){
        if (val){
            return "true";
        }
        return "false";
    }

}
