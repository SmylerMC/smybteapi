package fr.thesmyler.smybteapi.exception;

import java.util.function.Function;
import java.util.function.Predicate;

import spark.Request;

/**
 * Everything needed to deal with get parameters
 * 
 * TODO extensive testing
 * 
 * @author SmylerMC
 *
 */
public final class GetParameterHelper {
	
	private GetParameterHelper() {} // static class
	
	public static String[] getOptionalMultiStringParam(String key, Request request, String[] defaultValue, Predicate<String> condition) {
		String[] values = request.queryParamsValues(key);
		if(values == null) return defaultValue;
		parseParamsWithValueCheck(key, values, values, Function.identity(), condition);
		return values;
	}
	
	public static String[] getMultiStringParam(String key, Request request, Predicate<String> condition) {
		return checkNotNull(key, getOptionalMultiStringParam(key, request, null, condition));
	}
	
	public static String[] getOptionalMultiStringParam(String key, Request request, String[] defaultValue, int maxCount, Predicate<String> condition) {
		return checkGetParamArrayMaxCount(key, getOptionalMultiStringParam(key, request, defaultValue, condition), maxCount);
	}
	
	public static String[] getMultiStringParam(String key, Request request, int maxCount, Predicate<String> condition) {
		return checkNotNull(key, getOptionalMultiStringParam(key, request, null, maxCount, condition));
	}
	
	public static String[] getOptionalMultiStringParam(String key, Request request, String[] defaultValue, int minCount, int maxCount, Predicate<String> condition) {
		return checkGetParamArrayMinCount(key, getOptionalMultiStringParam(key, request, defaultValue, maxCount, condition), minCount);
	}
	
	public static String[] getMultiStringParam(String key, Request request, int minCount, int maxCount, Predicate<String> condition) {
		return checkNotNull(key, getOptionalMultiStringParam(key, request, null, minCount, maxCount, condition));
	}
	
	public static String getOptionalSingleStringParam(String key, Request request, String defaultValue, Predicate<String> condition) {
		return getParamAsUnique(key, getOptionalMultiStringParam(key, request, new String[] {defaultValue}, condition));
	}
	
	public static String getSingleStringParam(String key, Request request, Predicate<String> condition) {
		return checkNotNull(key, getOptionalSingleStringParam(key, request, null, condition));
	}
	
	public static String[] getOptionalMultiStringParam(String key, Request request, String[] defaultValue) {
		return getOptionalMultiStringParam(key, request, defaultValue, null);
	}
	
	public static String[] getMultiStringParam(String key, Request request) {
		return getMultiStringParam(key, request, null);
	}
	
	public static String[] getOptionalMultiStringParam(String key, Request request, String[] defaultValue, int maxCount) {
		return getOptionalMultiStringParam(key, request, defaultValue, maxCount, null);
	}
	
	public static String[] getMultiStringParam(String key, Request request, int maxCount) {
		return getMultiStringParam(key, request, maxCount, null);
	}
	
	public static String[] getOptionalMultiStringParam(String key, Request request, String[] defaultValue, int minCount, int maxCount) {
		return getOptionalMultiStringParam(key, request, defaultValue, minCount, maxCount, null);
	}
	
	public static String[] getMultiStringParam(String key, Request request, int minCount, int maxCount) {
		return getMultiStringParam(key, request, minCount, maxCount, null);
	}
	
	public static String getOptionalSingleStringParam(String key, Request request, String defaultValue) {
		return getOptionalSingleStringParam(key, request, null, null);
	}
	
	public static String getSingleStringParam(String key, Request request) {
		return getSingleStringParam(key, request, null);
	}
	
	public static Integer[] getOptionalMultiIntParam(String key, Request request, Integer[] defaultValue, Predicate<Integer> condition) {
		String[] strs = getOptionalMultiStringParam(key, request, null);
		if(strs == null) return defaultValue;
		Integer[] values = new Integer[strs.length];
		return parseParamsWithValueCheck(key, strs, values, GetParameterHelper::parseInt, condition);
	}
	
	public static Integer[] getMultiIntParam(String key, Request request, Predicate<Integer> condition) {
		return checkNotNull(key, getOptionalMultiIntParam(key, request, null, condition));
	}
	
	public static Integer[] getOptionalMultiIntParam(String key, Request request, Integer[] defaultValue, int maxCount, Predicate<Integer> condition) {
		return checkGetParamArrayMaxCount(key, getOptionalMultiIntParam(key, request, defaultValue, condition), maxCount);
	}
	
	public static Integer[] getMultiIntParam(String key, Request request, int maxCount, Predicate<Integer> condition) {
		return checkNotNull(key, getOptionalMultiIntParam(key, request, null, maxCount, condition));
	}
	
	public static Integer[] getOptionalMultiIntParam(String key, Request request, Integer[] defaultValue, int minCount, int maxCount, Predicate<Integer> condition) {
		return checkGetParamArrayMinCount(key, getOptionalMultiIntParam(key, request, defaultValue, maxCount, condition), minCount);
	}
	
	public static Integer[] getMultiIntParam(String key, Request request, int minCount,  int maxCount, Predicate<Integer> condition) {
		return checkNotNull(key, getOptionalMultiIntParam(key, request, null, minCount, maxCount, condition));
	}
	
	public static Integer getOptionalSingleIntParam(String key, Request request, Integer defaultValue, Predicate<Integer> condition) {
		return getParamAsUnique(key, getOptionalMultiIntParam(key, request, new Integer[] {defaultValue}, condition));
	}
	
	public static Integer getSingleIntParam(String key, Request request, Predicate<Integer> condition) {
		return checkNotNull(key, getOptionalSingleIntParam(key, request, null, condition));
	}
	
	public static Integer[] getOptionalMultiIntParam(String key, Request request, Integer[] defaultValue) {
		return getOptionalMultiIntParam(key, request, defaultValue, null);
	}
	
	public static Integer[] getMultiIntParam(String key, Request request) {
		return getMultiIntParam(key, request, null);
	}
	
	public static Integer[] getOptionalMultiIntParam(String key, Request request, Integer[] defaultValue, int maxCount) {
		return getOptionalMultiIntParam(key, request, defaultValue, maxCount, null);
	}
	
	public static Integer[] getMultiIntParam(String key, Request request, int maxCount) {
		return getMultiIntParam(key, request, maxCount, null);
	}
	
	public static Integer[] getOptionalMultiIntParam(String key, Request request, Integer[] defaultValue, int minCount, int maxCount) {
		return getOptionalMultiIntParam(key, request, defaultValue, minCount, maxCount, null);
	}
	
	public static Integer[] getMultiIntParam(String key, Request request, int minCount, int maxCount) {
		return getMultiIntParam(key, request, minCount, maxCount, null);
	}
	
	public static Integer getOptionalSingleIntParam(String key, Request request, Integer defaultValue) {
		return getOptionalSingleIntParam(key, request, defaultValue, null);
	}
	
	public static Integer getSingleIntParam(String key, Request request) {
		return getSingleIntParam(key, request, null);
	}
	
	public static Float[] getOptionalMultiFloatParam(String key, Request request, Float[] defaultValue, Predicate<Float> condition) {
		String[] strs = getOptionalMultiStringParam(key, request, null);
		if(strs == null) return defaultValue;
		Float[] floats = new Float[strs.length];
		return parseParamsWithValueCheck(key, strs, floats, GetParameterHelper::parseFloat, condition);
	}
	
	public static Float[] getMultiFloatParam(String key, Request request, Predicate<Float> condition) {
		return checkNotNull(key, getOptionalMultiFloatParam(key, request, null, condition));
	}
	
	public static Float[] getOptionalMultiFloatParam(String key, Request request, Float[] defaultValue, int maxCount, Predicate<Float> condition) {
		return checkGetParamArrayMaxCount(key, getOptionalMultiFloatParam(key, request, defaultValue, condition), maxCount);
	}
	
	public static Float[] getMultiFloatParam(String key, Request request, int maxCount, Predicate<Float> condition) {
		return checkNotNull(key, getOptionalMultiFloatParam(key, request, null, maxCount, condition));
	}
	
	public static Float[] getOptionalMultiFloatParam(String key, Request request, Float[] defaultValue, int minCount, int maxCount, Predicate<Float> condition) {
		return checkGetParamArrayMinCount(key, getOptionalMultiFloatParam(key, request, defaultValue, maxCount, condition), minCount);
	}
	
	public static Float[] getMultiFloatParam(String key, Request request, int minCount,  int maxCount, Predicate<Float> condition) {
		return checkNotNull(key, getOptionalMultiFloatParam(key, request, null, minCount, maxCount, condition));
	}
	
	public static Float getOptionalSingleFloatParam(String key, Request request, Float[] defaultValue, Predicate<Float> condition) {
		return getParamAsUnique(key, getOptionalMultiFloatParam(key, request, defaultValue, condition));
	}
	
	public static Float getSingleFloatParam(String key, Request request, Predicate<Float> condition) {
		return checkNotNull(key, getOptionalSingleFloatParam(key, request, null, condition));
	}
	
	public static Float[] getOptionalMultiFloatParam(String key, Request request, Float[] defaultValue) {
		return getOptionalMultiFloatParam(key, request, defaultValue, null);
	}
	
	public static Float[] getMultiFloatParam(String key, Request request) {
		return getMultiFloatParam(key, request, null);
	}
	
	public static Float[] getOptionalMultiFloatParam(String key, Request request, Float[] defaultValue, int maxCount) {
		return getOptionalMultiFloatParam(key, request, defaultValue, maxCount, null);
	}
	
	public static Float[] getMultiFloatParam(String key, Request request, int maxCount) {
		return getMultiFloatParam(key, request, maxCount, null);
	}
	
	public static Float[] getOptionalMultiFloatParam(String key, Request request, Float[] defaultValue, int minCount, int maxCount) {
		return getOptionalMultiFloatParam(key, request, defaultValue, minCount, maxCount, null);
	}
	
	public static Float[] getMultiFloatParam(String key, Request request, int minCount,  int maxCount) {
		return getMultiFloatParam(key, request, minCount, maxCount, null);
	}
	
	public static Float getOptionalSingleFloatParam(String key, Request request, Float defaultValue) {
		return getOptionalSingleFloatParam(key, request, new Float[] {defaultValue}, null);
	}
	
	public static Float getSingleFloatParam(String key, Request request) {
		return getSingleFloatParam(key, request, null);
	}
	
	public static Long[] getOptionalMultiLongParam(String key, Request request, Long[] defaultValue, Predicate<Long> condition) {
		String[] strs = getOptionalMultiStringParam(key, request, null);
		if(strs == null) return defaultValue;
		Long[] longs = new Long[strs.length];
		return parseParamsWithValueCheck(key, strs, longs, GetParameterHelper::parseLong, condition);
	}
	
	public static Long[] getMultiLongParam(String key, Request request, Predicate<Long> condition) {
		return checkNotNull(key, getOptionalMultiLongParam(key, request, null, condition));
	}
	
	public static Long[] getOptionalMultiLongParam(String key, Request request, Long[] defaultValue, int maxCount, Predicate<Long> condition) {
		return checkGetParamArrayMaxCount(key, getOptionalMultiLongParam(key, request, defaultValue, condition), maxCount);
	}
	
	public static Long[] getMultiLongParam(String key, Request request, int maxCount, Predicate<Long> condition) {
		return checkNotNull(key, getOptionalMultiLongParam(key, request, null, maxCount, condition));
	}
	
	public static Long[] getOptionalMultiLongParam(String key, Request request, Long[] defaultValue, int minCount, int maxCount, Predicate<Long> condition) {
		return checkGetParamArrayMinCount(key, getOptionalMultiLongParam(key, request, defaultValue, maxCount, condition), minCount);
	}
	
	public static Long[] getMultiLongParam(String key, Request request, int minCount, int maxCount, Predicate<Long> condition) {
		return checkNotNull(key, getOptionalMultiLongParam(key, request, null, minCount, maxCount, condition));
	}
	
	public static Long getOptionalSingleLongParam(String key, Request request, Long defaultValue, Predicate<Long> condition) {
		return getParamAsUnique(key, getOptionalMultiLongParam(key, request, new Long[] {defaultValue}, condition));
	}
	
	public static Long getSingleLongParam(String key, Request request, Predicate<Long> condition) {
		return checkNotNull(key, getOptionalSingleLongParam(key, request, null, condition));
	}
	
	public static Long[] getOptionalMultiLongParam(String key, Request request, Long[] defaultValue) {
		return getOptionalMultiLongParam(key, request, defaultValue, null);
	}
	
	public static Long[] getMultiLongParam(String key, Request request) {
		return getMultiLongParam(key, request, null);
	}
	
	public static Long[] getOptionalMultiLongParam(String key, Request request, Long[] defaultValue, int maxCount) {
		return getOptionalMultiLongParam(key, request, defaultValue, maxCount, null);
	}
	
	public static Long[] getMultiLongParam(String key, Request request, int maxCount) {
		return getMultiLongParam(key, request, maxCount, null);
	}
	
	public static Long[] getOptionalMultiLongParam(String key, Request request, Long[] defaultValue, int minCount, int maxCount) {
		return  getOptionalMultiLongParam(key, request, defaultValue, minCount, maxCount, null);
	}
	
	public static Long[] getMultiLongParam(String key, Request request, int minCount,  int maxCount) {
		return  getMultiLongParam(key, request, minCount, maxCount, null);
	}
	
	public static Long getOptionalSingleLongParam(String key, Request request, Long defaultValue) {
		return getOptionalSingleLongParam(key, request, defaultValue, null);
	}
	
	public static Long getSingleLongParam(String key, Request request) {
		return getSingleLongParam(key, request, null);
	}
	
	public static Double[] getOptionalMultiDoubleParam(String key, Request request, Double[] defaultValue, Predicate<Double> condition) {
		String[] strs = getOptionalMultiStringParam(key, request, null);
		if(strs == null) return defaultValue;
		Double[] doubles = new Double[strs.length];
		return parseParamsWithValueCheck(key, strs, doubles, GetParameterHelper::parseDouble, condition);
	}
	
	public static Double[] getMultiDoubleParam(String key, Request request, Predicate<Double> condition) {
		return checkNotNull(key, getOptionalMultiDoubleParam(key, request, null, condition));
	}
	
	public static Double[] getOptionalMultiDoubleParam(String key, Request request, Double[] defaultValue, int maxCount, Predicate<Double> condition) {
		return checkGetParamArrayMaxCount(key, getOptionalMultiDoubleParam(key, request, defaultValue, condition), maxCount);
	}
	
	public static Double[] getMultiDoubleParam(String key, Request request, int maxCount, Predicate<Double> condition) {
		return checkNotNull(key, getOptionalMultiDoubleParam(key, request, null, maxCount, condition));
	}
	
	public static Double[] getOptionalMultiDoubleParam(String key, Request request, Double[] defaultValue, int minCount, int maxCount, Predicate<Double> condition) {
		return checkGetParamArrayMinCount(key, getOptionalMultiDoubleParam(key, request, defaultValue, maxCount, condition), minCount);
	}
	
	public static Double[] getMultiDoubleParam(String key, Request request, int minCount,  int maxCount, Predicate<Double> condition) {
		return checkNotNull(key, getOptionalMultiDoubleParam(key, request, null, minCount, maxCount, condition));
	}
	
	public static Double getOptionalSingleDoubleParam(String key, Request request, Double defaultValue, Predicate<Double> condition) {
		return getParamAsUnique(key, getOptionalMultiDoubleParam(key, request, new Double[] {defaultValue}, condition));
	}
	
	public static double getSingleDoubleParam(String key, Request request, Predicate<Double> condition) {
		return checkNotNull(key, getOptionalSingleDoubleParam(key, request, null, condition));
	}
	
	public static Double[] getOptionalMultiDoubleParam(String key, Request request, Double[] defaultValue) {
		return getOptionalMultiDoubleParam(key, request, defaultValue, null);
	}
	
	public static Double[] getMultiDoubleParam(String key, Request request) {
		return getMultiDoubleParam(key, request, null);
	}
	
	public static Double[] getOptionalMultiDoubleParam(String key, Request request, Double[] defaultValue, int maxCount) {
		return getOptionalMultiDoubleParam(key, request, defaultValue, maxCount, null);
	}
	
	public static Double[] getMultiDoubleParam(String key, Request request, int maxCount) {
		return getMultiDoubleParam(key, request, maxCount, null);
	}
	
	public static Double[] getOptionalMultiDoubleParam(String key, Request request, Double[] defaultValue, int minCount, int maxCount) {
		return getOptionalMultiDoubleParam(key, request, defaultValue, minCount, maxCount, null);
	}
	
	public static Double[] getMultiDoubleParam(String key, Request request, int minCount,  int maxCount) {
		return getMultiDoubleParam(key, request, minCount, maxCount, null);
	}
	
	public static Double getOptionalSingleDoubleParam(String key, Request request, Double defaultValue) {
		return getOptionalSingleDoubleParam(key, request, defaultValue, null);
	}
	
	public static Double getSingleDoubleParam(String key, Request request) {
		return getSingleDoubleParam(key, request, null);
	}
	
	public static Double[][] getOptionalMultiDoublePairParam(String key, Request request, Double[][] defaultValue, Predicate<Double[]> condition) {
		String[] strs = getMultiStringParam(key, request, null);
		if(strs == null) return defaultValue;
		Double[][] doubles = new Double[strs.length][2];
		return parseParamsWithValueCheck(key, strs, doubles, GetParameterHelper::parseDoublePair, condition);
	}
	
	public static Double[][] getMultiDoublePairParam(String key, Request request,Predicate<Double[]> condition) {
		return checkNotNull(key, getOptionalMultiDoublePairParam(key, request, null, condition));
	}
	
	public static Double[][] getOptionalMultiDoublePairParam(String key, Request request, Double[][] defaultValue, int maxCount, Predicate<Double[]> condition) {
		return checkGetParamArrayMaxCount(key, getOptionalMultiDoublePairParam(key, request, defaultValue, condition), maxCount);
	}
	
	public static Double[][] getMultiDoublePairParam(String key, Request request, int maxCount, Predicate<Double[]> condition) {
		return checkNotNull(key, getOptionalMultiDoublePairParam(key, request, null, maxCount, condition));
	}
	
	public static Double[][] getOptionalMultiDoublePairParam(String key, Request request, Double[][] defaultValue, int minCount, int maxCount, Predicate<Double[]> condition) {
		return checkGetParamArrayMinCount(key, getOptionalMultiDoublePairParam(key, request, defaultValue, maxCount, condition), minCount);
	}
	
	public static Double[][] getMultiDoublePairParam(String key, Request request, int minCount,  int maxCount, Predicate<Double[]> condition) {
		return checkNotNull(key, getOptionalMultiDoublePairParam(key, request, null, minCount, maxCount, condition));
	}
	
	public static Double[] getOptionalSingleDoublePairParam(String key, Request request, Double[] defaultValue, Predicate<Double[]> condition) {
		return getParamAsUnique(key, getOptionalMultiDoublePairParam(key, request, new Double[][] {defaultValue}, condition));
	}
	
	public static Double[] getSingleDoublePairParam(String key, Request request, Predicate<Double[]> condition) {
		return checkNotNull(key, getOptionalSingleDoublePairParam(key, request, null, condition));
	}
	
	public static Double[][] getOptionalMultiDoublePairParam(String key, Request request) {
		return getOptionalMultiDoublePairParam(key, request);
	}
	
	public static Double[][] getMultiDoublePairParam(String key, Request request) {
		return getMultiDoublePairParam(key, request, null);
	}
	
	public static Double[][] getOptionalMultiDoublePairParam(String key, Request request, Double[][] defaultValue, int maxCount) {
		return getOptionalMultiDoublePairParam(key, request, defaultValue, maxCount, null);
	}
	
	public static Double[][] getMultiDoublePairParam(String key, Request request, int maxCount) {
		return getMultiDoublePairParam(key, request, maxCount, null);
	}
	
	public static Double[][] getOptionalMultiDoublePairParam(String key, Request request, Double[][] defaultValue, int minCount, int maxCount) {
		return getOptionalMultiDoublePairParam(key, request, defaultValue, minCount, maxCount, null);
	}
	
	public static Double[][] getMultiDoublePairParam(String key, Request request, int minCount,  int maxCount) {
		return getMultiDoublePairParam(key, request, minCount, maxCount, null);
	}
	
	public static Double[] getOptionalSingleDoublePairParam(String key, Request request, Double[] defaultValue) {
		return getOptionalSingleDoublePairParam(key, request, defaultValue, null);
	}
	
	public static Double[] getSingleDoublePairParam(String key, Request request) {
		return getSingleDoublePairParam(key, request, null);
	}
	
	public static Integer parseInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch(NumberFormatException e) {
			throw new ApiSpecificationException("Failed to parse an int.", "'" + str + "' is not a valid int.");
		}
	}
	
	public static Float parseFloat(String str) {
		try {
			return Float.parseFloat(str);
		} catch(NumberFormatException e) {
			throw new ApiSpecificationException("Failed to parse a float.", "'" + str + "' is not a valid float.");
		}
	}
	
	public static Long parseLong(String str) {
		try {
			return Long.parseLong(str);
		} catch(NumberFormatException e) {
			throw new ApiSpecificationException("Failed to parse a long.", "'" + str + "' is not a valid long.");
		}
	}
	
	public static Double parseDouble(String str) {
		try {
			return Double.parseDouble(str);
		} catch(NumberFormatException e) {
			throw new ApiSpecificationException("Failed to parse a double.", "'" + str + "' is not a valid double.");
		}
	}
	
	public static Double[] parseDoublePair(String str) {
		String[] strs = str.split("\\,");
		if(strs.length != 2) throw new ApiSpecificationException("Failed to parse point", str + " is not a valid couple of finite doubles.");
		return new Double[] {parseDouble(strs[0]), parseDouble(strs[1])};
	}
	
	private static <T> T[] checkGetParamArrayMinCount(String key, T[] arr, int minCount) {
		if(arr != null && arr.length < minCount) throw new ApiSpecificationException("Too few get parameters", "The '" + key + "' get parameter needs to be specified at least" + minCount + " times.");
		return arr;
	}
	
	private static <T> T[] checkGetParamArrayMaxCount(String key, T[] arr, int maxCount) {
		if(arr != null && arr.length > maxCount) throw new ApiSpecificationException("Too many get parameters",  "The '" + key + "' get parameter cannot be specified more than " + maxCount + " times.");
		return arr;
	}
	
	private static <T> T getParamAsUnique(String key, T[] arr) {
		if(arr != null && arr.length == 1) throw new ApiSpecificationException("Single parameter required", "The '" + key + "' is needed exactly once.");
		return arr != null ? arr[0] : null;
	}
	
	private static <T> T checkNotNull(String key, T arr) {
		if(arr == null) throw new ApiSpecificationException("Missing get parameter", "The '" + key + "' get parameter is required.");
		return arr;
	}
	
	private static <T> T[] parseParamsWithValueCheck(String key, String[] toParse, T[] result, Function<String, T> parser, Predicate<T> condition) {
		if(toParse == null) return null;
		for(int i=0; i<toParse.length; i++) {
			T value = parser.apply(toParse[i]);
			if(condition != null && !condition.test(value)) throw new ApiSpecificationException("Invalid value.", value + " is not a valid value for get parameter " + key +".");
			result[i] = value;
		}
		return result;
	}

}
