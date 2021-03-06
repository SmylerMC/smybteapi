package fr.thesmyler.smybteapi;

import static fr.thesmyler.smybteapi.SmyBteApiUtil.*;
import static java.lang.Math.*;

import fr.thesmyler.smybteapi.exception.Precondition;
import spark.Request;
import spark.Response;

public final class OtherRoutes {
	
	private OtherRoutes() {} // static class
	
	public static String mercatorRes(Request request, Response response) {
		Precondition.hasGetParamOnce("latitude", request);
		double lat = SmyBteApiUtil.parseFiniteDoubleSafe(request.queryParams("latitude"));
		Precondition.inRange(lat, -90, 90, "latitude");
		MercatorResRes resp = new MercatorResRes();
		for(int z=0; z<resp.resolution.length; z++) {
			resp.resolution[z] = EARTH_CIRCUMFERENCE*cos(toRadians(lat)) / (double)((1<<z) * 256);
		}
		touchJsonResponse(response);
		return SmyBteApi.GSON.toJson(resp);
	}
	
	private static class MercatorResRes {
		double[] resolution = new double[21];
	}

}
