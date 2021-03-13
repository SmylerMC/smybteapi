package fr.thesmyler.smybteapi.util;

import java.util.function.Predicate;

public final class ValueChecks {
	
	private ValueChecks() {} // Static class
	
	/**
	 * Checks if a string is null or empty
	 * @param str
	 * @return true if the string is null or empty, false otherwise
	 */
	public static boolean stringIsNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}
	
	public static boolean isValidLatitudeLongitudePair(double[] lalo) {
		if(lalo == null) return false;
		if(lalo.length != 2) return false;
		return Double.isFinite(lalo[0]) && Double.isFinite(lalo[1])
				&& isInInclusiveRange(lalo[0], -90d, 90d) && isInInclusiveRange(lalo[1], -180d, 180d);
	}
	
	public static boolean isValidLatitudeLongitudePair(Double[] lalo) {
		return isValidLatitudeLongitudePair(new double[] {lalo[0], lalo[1]});
	}
	
	public static boolean isFinitePair(double[] pair) {
		return pair != null && pair.length == 2 && Double.isFinite(pair[0]) && Double.isFinite(pair [1]);
	}
	
	public static boolean isFinitePair(float[] pair) {
		return pair != null && pair.length == 2 && Float.isFinite(pair[0]) && Float.isFinite(pair [1]);
	}
	
	public static boolean isFinitePair(Double[] pair) {
		return pair != null && pair.length == 2 && Double.isFinite(pair[0]) && Double.isFinite(pair [1]);
	}
	
	public static boolean isFinitePair(Float[] pair) {
		return pair != null && pair.length == 2 && Float.isFinite(pair[0]) && Float.isFinite(pair [1]);
	}
	
	public static boolean isInInclusiveRange(double x, double min, double max) {
		return x >= min && x <= max;
	}
	
	public static boolean isInInclusiveRange(long x, long min, long max) {
		return x >= min && x <= max;
	}
	
	public static boolean isInExclusiveRange(double x, double min, double max) {
		return x > min && x < max;
	}
	
	public static boolean isInExclusiveRange(long x, long min, long max) {
		return x > min && x < max;
	}
	
	public static Predicate<Long> inclusiveRangerChecker(long min, long max) {
		return x -> isInInclusiveRange(x, min, max);
	}
	
	public static Predicate<Double> inInclusiveRangerChecker(double min, double max) {
		return x -> isInInclusiveRange(x, min, max);
	}
	
	public static Predicate<Long> exclusiveRangerChecker(long min, long max) {
		return x -> isInInclusiveRange(x, min, max);
	}
	
	public static Predicate<Double> exclusiveRangerChecker(double min, double max) {
		return x -> isInInclusiveRange(x, min, max);
	}

}
