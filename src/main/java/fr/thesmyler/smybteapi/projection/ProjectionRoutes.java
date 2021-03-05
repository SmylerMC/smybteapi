package fr.thesmyler.smybteapi.projection;

import static fr.thesmyler.smybteapi.SmyBteApiUtil.parseFiniteDoublePointSafe;
import static fr.thesmyler.smybteapi.SmyBteApiUtil.touchJsonResponse;

import fr.thesmyler.smybteapi.SmyBteApi;
import fr.thesmyler.smybteapi.exception.Precondition;
import net.buildtheearth.terraplusplus.projection.GeographicProjection;
import net.buildtheearth.terraplusplus.projection.OutOfProjectionBoundsException;
import spark.Request;
import spark.Response;

public final class ProjectionRoutes {

	private ProjectionRoutes() {} // static class

	public static String toGeo(Request request, Response response) {
		touchJsonResponse(response);
		Precondition.hasGetParam("mcpos", request);
		String[] strs = request.queryParamsValues("mcpos");
		Precondition.isArraySmaller(strs, 1000, "points");
		GeographicProjection projection = Projections.BTE_PROJECTION;
		ToGeoResponse resp = new ToGeoResponse();
		resp.total = strs.length;
		resp.geo_positions = new double[strs.length][2];
		for(int i=0; i<strs.length; i++) {
			try {
				double[] xz = parseFiniteDoublePointSafe(strs[i]);
				double[] lola = projection.toGeo(xz[0], xz[1]);
				resp.geo_positions[i][0] = lola[1];
				resp.geo_positions[i][1] = lola[0];
				resp.success += 1;
			} catch (OutOfProjectionBoundsException e) {
				resp.geo_positions[i] = null;
				resp.out_of_bounds += 1;
			}
		}
		return SmyBteApi.GSON.toJson(resp);
	}
	
	public static String fromGeo(Request request, Response response) {
		touchJsonResponse(response);
		Precondition.hasGetParam("geopos", request);
		String[] strs = request.queryParamsValues("geopos");
		Precondition.isArraySmaller(strs, 1000, "points");
		GeographicProjection projection = Projections.BTE_PROJECTION;
		FromGeoResponse resp = new FromGeoResponse();
		resp.total = strs.length;
		resp.mc_positions = new double[strs.length][2];
		for(int i=0; i<strs.length; i++) {
			try {
				double[] lalo = parseFiniteDoublePointSafe(strs[i]);
				resp.mc_positions[i] = projection.fromGeo(lalo[1], lalo[0]);
				resp.success += 1;
			} catch (OutOfProjectionBoundsException e) {
				resp.mc_positions[i] = null;
				resp.out_of_bounds += 1;
			}
		}
		return SmyBteApi.GSON.toJson(resp);
	}
	
	static class ToGeoResponse {
		double[][] geo_positions;
		int total;
		int success;
		int out_of_bounds;
	}
	
	static class FromGeoResponse {
		double[][] mc_positions;
		int total;
		int success;
		int out_of_bounds;
	}

}
