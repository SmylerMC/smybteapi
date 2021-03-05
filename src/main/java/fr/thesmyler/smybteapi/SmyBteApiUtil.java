package fr.thesmyler.smybteapi;

import fr.thesmyler.smybteapi.exception.ApiSpecificationException;
import fr.thesmyler.smybteapi.exception.ApiSpecificationException.InvalidDoubleException;
import fr.thesmyler.smybteapi.exception.ApiSpecificationException.InvalidFloatException;
import fr.thesmyler.smybteapi.exception.ApiSpecificationException.InvalidIntException;
import fr.thesmyler.smybteapi.exception.ApiSpecificationException.InvalidLongException;
import spark.Response;

/**
 * Collections of useful static methods commonly used a	across the application
 * 
 * @author SmylerMC
 *
 */
public final class SmyBteApiUtil {

    /**
     * Earth's circumference around the equator, in meters.
     */
    public static final double EARTH_CIRCUMFERENCE = 40075017;

    /**
     * Earth's circumference around the poles, in meters.
     */
    public static final double EARTH_POLAR_CIRCUMFERENCE = 40008000;
    
	private SmyBteApiUtil() {} // static class
	
	/**
	 * Adds the necessary HTTP headers for a JSON response
	 * 
	 * @param response
	 */
	public static void touchJsonResponse(Response response) {
		response.type("application/json");
	}
	
	/**
	 * Gets the specified system property.
	 * <p>
	 * If this property is not available, tries to return the environment variable with the same name,
	 * capitalized and with dots '.' replaced by '_'
	 * 
	 * @param key
	 * @return a system property or environment variable, or null if none exist for the specified key
	 */
	public static String getPropertyOrEnv(String key) {
		String value = System.getProperty(key);
		value = System.getenv(key.replace(".", "_").toUpperCase());
		if(stringIsNullOrEmpty(value)) value = null;
		return value;
	}
	
	/**
	 * Checks if a string is null or empty
	 * @param str
	 * @return true if the string is null or empty, false otherwise
	 */
	public static boolean stringIsNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}
	
	public static int parseIntSafe(String str) {
		try {
			return Integer.parseInt(str);
		} catch(NumberFormatException e) {
			throw new InvalidIntException(str);
		}
	}
	
	public static float parseFloatSafe(String str) {
		try {
			return Float.parseFloat(str);
		} catch(NumberFormatException e) {
			throw new InvalidFloatException(str);
		}
	}
	
	public static long parseLongSafe(String str) {
		try {
			return Long.parseLong(str);
		} catch(NumberFormatException e) {
			throw new InvalidLongException(str);
		}
	}
	
	public static double parseDoubleSafe(String str) {
		try {
			return Double.parseDouble(str);
		} catch(NumberFormatException e) {
			throw new InvalidDoubleException(str);
		}
	}
	
	public static float parseFiniteFloatSafe(String str) {
		float f = parseFloatSafe(str);
		if(!Float.isFinite(f)) throw new InvalidFloatException(str);
		return f;
	}
	
	public static double parseFiniteDoubleSafe(String str) {
		double d = parseDoubleSafe(str);
		if(!Double.isFinite(d)) throw new InvalidDoubleException(str);
		return d;
	}
	
	public static double[] parseFiniteDoublePointSafe(String str) {
		String[] strs = str.split("\\,");
		if(strs.length != 2) throw new ApiSpecificationException("Failed to parse point", str + " is not a valid couple of finite doubles.");
		return new double[] {parseFiniteDoubleSafe(strs[0]), parseFiniteDoubleSafe(strs[1])};
	}
	
}
