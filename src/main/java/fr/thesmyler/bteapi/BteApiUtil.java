package fr.thesmyler.bteapi;

import spark.Response;

/**
 * Collections of useful static methods commonly used a	across the application
 * 
 * @author SmylerMC
 *
 */
public final class BteApiUtil {

	private BteApiUtil() {} // static class
	
	/**
	 * Adds the necessary HTTP headers for a JSON response
	 * 
	 * @param response
	 */
	public static void touchJsonResponse(Response response) {
		response.header("content-type", "application/json");
	}
}
