package net.buildtheearth.terraplusplus.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Hacky stuff
 * 
 * @author SmylerMC
 *
 * @param <T>
 * @param <K>
 */
public class HackBiMap<T, K> {
	
	private Map<T, K> directe = new HashMap<>();
	private Map<K, T> inverse = new HashMap<>();
	
	public void put(T t, K k) {
		directe.put(t, k);
		inverse.put(k, t);
	}
	
	public K get(T t) {
		return directe.get(t);
	}
	
	public T getInverse(K k) {
		return inverse.get(k);
	}

	/**
	 * @return the directe
	 */
	public Map<T, K> direct() {
		return directe;
	}

	/**
	 * @return the inverse
	 */
	public Map<K, T> inverse() {
		return inverse;
	}
	
	

}
