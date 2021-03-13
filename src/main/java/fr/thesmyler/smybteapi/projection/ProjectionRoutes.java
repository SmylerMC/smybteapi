package fr.thesmyler.smybteapi.projection;

import static fr.thesmyler.smybteapi.util.SmyBteApiUtil.touchJsonResponse;

import fr.thesmyler.smybteapi.SmyBteApi;
import fr.thesmyler.smybteapi.exception.GetParameterHelper;
import fr.thesmyler.smybteapi.util.ValueChecks;
import net.buildtheearth.terraplusplus.projection.GeographicProjection;
import net.buildtheearth.terraplusplus.projection.OutOfProjectionBoundsException;
import spark.Request;
import spark.Response;

public final class ProjectionRoutes {

	private ProjectionRoutes() {} // static class
	
	public static final int MAX_CONVERSIONS_PER_REQUEST = 1000;

	public static String toGeo(Request request, Response response) {
		touchJsonResponse(response);
		GetParameterHelper.getMultiStringParam("mcpos", request);
		Double[][] mcposs = GetParameterHelper.getMultiDoublePairParam("mcpos", request, MAX_CONVERSIONS_PER_REQUEST, ValueChecks::isFinitePair);
		GeographicProjection projection = Projections.BTE_PROJECTION;
		ToGeoResponse resp = new ToGeoResponse();
		resp.total = mcposs.length;
		resp.geo_positions = new double[mcposs.length][2];
		for(int i=0; i<mcposs.length; i++) {
			try {
				Double[] xz = mcposs[i];
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
		Double[][] geopos = GetParameterHelper.getMultiDoublePairParam("geopos", request, MAX_CONVERSIONS_PER_REQUEST, ValueChecks::isValidLatitudeLongitudePair);
		GeographicProjection projection = Projections.BTE_PROJECTION;
		FromGeoResponse resp = new FromGeoResponse();
		resp.total = geopos.length;
		resp.mc_positions = new double[geopos.length][2];
		for(int i=0; i<geopos.length; i++) {
			try {
				Double[] lalo = geopos[i];
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
