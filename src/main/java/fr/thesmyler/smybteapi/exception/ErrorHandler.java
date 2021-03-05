package fr.thesmyler.smybteapi.exception;

import static fr.thesmyler.smybteapi.SmyBteApiUtil.touchJsonResponse;

import com.google.gson.JsonObject;

import fr.thesmyler.smybteapi.SmyBteApi;
import spark.Request;
import spark.Response;

public final class ErrorHandler {
	
	private ErrorHandler() {} // static class
	
	/**
	 * Handles ApiSpecificationException
	 * 
	 * @param exception
	 * @param request
	 * @param response
	 */
	public static void handleUserGeneratedException(Exception exception, Request request, Response response) {
		touchJsonResponse(response);
		response.status(400);
		response.body(exception.toString());
	}
	
	/**
	 * Handles all exceptions except ApiSpecificationException
	 * 
	 * @param exception
	 * @param request
	 * @param response
	 */
	public static String handleServerError(Request request, Response response) {
		touchJsonResponse(response);
		response.status(500);
		JsonObject json = new JsonObject();
		json.addProperty("internal error", "An internal error happened with your request. We are sorry for the inconvenience. Please contact the instance administrator if the problem persists.");
		json.addProperty("instance-administrator", SmyBteApi.INSTANCE_ADMIN);
		json.addProperty("url", request.url());
		return SmyBteApi.GSON.toJson(json);
	}
	
	/**
	 * Handles requests that do not map any route
	 * 
	 * @param request
	 * @param response
	 */
	public static String handleNotFound(Request request, Response response) {
		touchJsonResponse(response);
		response.status(404);
		JsonObject json = new JsonObject();
		json.addProperty("error", "404: invalid endpoint");
		json.addProperty("url", request.url());
		return SmyBteApi.GSON.toJson(json);
	}

}
