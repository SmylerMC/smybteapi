package fr.thesmyler.smybteapi.projection;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;

import fr.thesmyler.smybteapi.SmyBteApi;
import fr.thesmyler.smybteapi.exception.ApiSpecificationException.InvalidJsonException;
import net.buildtheearth.terraplusplus.projection.GeographicProjection;
import net.buildtheearth.terraplusplus.projection.dymaxion.ConformalDynmaxionProjection;

public class Projections {
	
	public static final GeographicProjection BTE_PROJECTION;
	
	static {
		try(InputStream is = Projections.class.getResourceAsStream("bte_projection.json");
    			BufferedInputStream bs = new BufferedInputStream(is);
    			Scanner sc = new Scanner(bs)){
    			String str = "";
    		while(sc.hasNext()) str += sc.nextLine();	
    		BTE_PROJECTION = parse(str);
    	} catch (IOException e) {
			SmyBteApi.LOGGER.severe("Failed to load bte projection!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static void setup() {
		new ConformalDynmaxionProjection(); // Force loading conformal.txt now
	}
	
	public static GeographicProjection parse(String config) {
	    try {
			return SmyBteApi.JSON_MAPPER.readValue(config, GeographicProjection.class);
		} catch (JsonProcessingException e) {
			throw new InvalidJsonException(config);
		}
    }
}
