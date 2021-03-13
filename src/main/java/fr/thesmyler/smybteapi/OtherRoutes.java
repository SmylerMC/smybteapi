package fr.thesmyler.smybteapi;

import static fr.thesmyler.smybteapi.util.SmyBteApiUtil.EARTH_CIRCUMFERENCE;
import static fr.thesmyler.smybteapi.util.SmyBteApiUtil.touchJsonResponse;
import static java.lang.Math.cos;
import static java.lang.Math.toRadians;

import fr.thesmyler.smybteapi.exception.GetParameterHelper;
import fr.thesmyler.smybteapi.util.ValueChecks;
import spark.Request;
import spark.Response;

public final class OtherRoutes {
	
	private OtherRoutes() {} // Static class
	
	public static String mercatorRes(Request request, Response response) {
		double lat = GetParameterHelper.getSingleDoubleParam("latitude", request, ValueChecks.inInclusiveRangerChecker(-85, 85));
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
