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
	
}
