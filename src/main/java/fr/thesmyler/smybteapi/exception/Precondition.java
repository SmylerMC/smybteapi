package fr.thesmyler.smybteapi.exception;

import fr.thesmyler.smybteapi.exception.ApiSpecificationException.MissingGetParameterException;
import fr.thesmyler.smybteapi.exception.ApiSpecificationException.TooMuchDataException;
import spark.Request;

public final class Precondition {
	
	private Precondition() {} // static class
	
	public static void hasGetParam(String key, Request request) {
		if(request.queryParamsValues(key) == null) throw new MissingGetParameterException(key);
	}
	
	public static void isArraySmaller(Object[] array, int max, String unit) {
		if(array.length > max) throw new TooMuchDataException(array.length, max, unit);
	}

}
