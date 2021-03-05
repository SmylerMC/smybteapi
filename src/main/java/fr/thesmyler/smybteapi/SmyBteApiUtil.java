package fr.thesmyler.smybteapi;

import spark.Response;

/**
 * Collections of useful static methods commonly used a	across the application
 * 
 * @author SmylerMC
 *
 */
public final class SmyBteApiUtil {

	private SmyBteApiUtil() {} // static class
	
	/**
	 * Adds the necessary HTTP headers for a JSON response
	 * 
	 * @param response
	 */
	public static void touchJsonResponse(Response response) {
		response.header("content-type", "application/json");
	}
	
	/**
	 * Gets the specified system property.
	 * <p>
	 * If this property is not available, tries to return the environment variable with the same name.
	 * 
	 * @param key
	 * @return a system property or environment variable, or null if none exist for the specified key
	 */
	public static String getPropertyOrEnv(String key) {
		String value = System.getProperty(key);
		value = System.getenv(key);
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
	
}
