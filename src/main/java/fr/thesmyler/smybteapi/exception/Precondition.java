package fr.thesmyler.smybteapi.exception;

import fr.thesmyler.smybteapi.exception.ApiSpecificationException.InvalidValueException;
import fr.thesmyler.smybteapi.exception.ApiSpecificationException.MissingGetParameterException;
import fr.thesmyler.smybteapi.exception.ApiSpecificationException.MultipleGetParameterException;
import fr.thesmyler.smybteapi.exception.ApiSpecificationException.TooMuchDataException;
import spark.Request;

public final class Precondition {
	
	private Precondition() {} // static class
	
	public static void hasGetParam(String key, Request request) {
		if(request.queryParamsValues(key) == null) throw new MissingGetParameterException(key);
	}
	
	public static void hasGetParamOnce(String key, Request request) {
		hasGetParam(key, request);
		if(request.queryParamsValues(key).length != 1) throw new MultipleGetParameterException(key);
	}
	
	public static void isArraySmaller(Object[] array, int max, String unit) {
		if(array.length > max) throw new TooMuchDataException(array.length, max, unit);
	}
	
	public static void inRange(long x, long min, long max, String name) {
		if(x < min || x >= max) throw new InvalidValueException(x, name);
	}
	
	public static void inRange(double x, double min, double max, String name) {
		if(x < min || x >= max) throw new InvalidValueException(x, name);
	}

}
