package fr.thesmyler.smybteapi.util;

import static fr.thesmyler.smybteapi.util.ValueChecks.stringIsNullOrEmpty;
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
    
	private SmyBteApiUtil() {} // static class
	
	/**
	 * Adds the necessary HTTP headers for a JSON response
	 * 
	 * @param response the response to modify
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
	 * @param key the key to lookup
	 * @return a system property or environment variable, or null if none exist for the specified key
	 */
	public static String getPropertyOrEnv(String key) {
		String value = System.getProperty(key);
		if (value != null) return value;
		value = System.getenv(key.replace(".", "_").toUpperCase());
		if(stringIsNullOrEmpty(value)) value = null;
		return value;
	}
	
}
