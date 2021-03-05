package fr.thesmyler.smybteapi.exception;

import com.google.gson.JsonObject;

import fr.thesmyler.smybteapi.SmyBteApi;

public class ApiSpecificationException extends RuntimeException {

	private String error;
	private String details;
	
	public ApiSpecificationException(String error, String details) {
		this.error = error;
		this.details = details;
	}
	
	public String getErrorMessage() {
		return this.error;
	}
	
	public String getErrorDetails() {
		return this.details;
	}
	
	@Override
	public String toString() {
		JsonObject json = new JsonObject();
		json.addProperty("error", this.error);
		json.addProperty("details", this.details);
		return SmyBteApi.GSON.toJson(json);
	}
	
	public static class TooMuchDataException extends ApiSpecificationException {

		public TooMuchDataException(int howMany, int max, String unit) {
			super("Too much data requested at once", "Requested " + howMany + " " + unit + " maximum authorized is " + max + ".");
		}
		
	}
	
	public static class MissingGetParameterException extends ApiSpecificationException {

		public MissingGetParameterException(String parameterName) {
			super("Missing get parameter", "The '" + parameterName + "' get parameter is required for this endpoint.");
		}
		
	}
	
	public static class InvalidIntException extends ApiSpecificationException {

		public InvalidIntException(String str) {
			super("Failed to parse a double.", "'" + str + "' is not a valid int.");
		}
		
	}
	
	public static class InvalidFloatException extends ApiSpecificationException {

		public InvalidFloatException(String str) {
			super("Failed to parse a double.", "'" + str + "' is not a valid float.");
		}
		
	}
	
	public static class InvalidLongException extends ApiSpecificationException {

		public InvalidLongException(String str) {
			super("Failed to parse a long.", "'" + str + "' is not a valid long.");
		}
		
	}
	
	public static class InvalidDoubleException extends ApiSpecificationException {

		public InvalidDoubleException(String str) {
			super("Failed to parse a double.", "'" + str + "' is not a valid double.");
		}
		
	}
	
	public static class InvalidJsonException extends ApiSpecificationException {

		public InvalidJsonException(String str) {
			super("Failed to parse a json string.", "'" + str + "' is not a valid json object for this request.");
		}
		
	}
	
}
