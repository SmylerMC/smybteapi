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
	
}
