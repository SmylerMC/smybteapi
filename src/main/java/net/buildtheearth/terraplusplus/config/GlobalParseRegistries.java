package net.buildtheearth.terraplusplus.config;

import net.buildtheearth.terraplusplus.projection.EqualEarthProjection;
import net.buildtheearth.terraplusplus.projection.EquirectangularProjection;
import net.buildtheearth.terraplusplus.projection.GeographicProjection;
import net.buildtheearth.terraplusplus.projection.SinusoidalProjection;
import net.buildtheearth.terraplusplus.projection.dymaxion.BTEDymaxionProjection;
import net.buildtheearth.terraplusplus.projection.dymaxion.ConformalDynmaxionProjection;
import net.buildtheearth.terraplusplus.projection.dymaxion.DymaxionProjection;
import net.buildtheearth.terraplusplus.projection.mercator.CenteredMercatorProjection;
import net.buildtheearth.terraplusplus.projection.mercator.TransverseMercatorProjection;
import net.buildtheearth.terraplusplus.projection.mercator.WebMercatorProjection;
import net.buildtheearth.terraplusplus.projection.transform.FlipHorizontalProjectionTransform;
import net.buildtheearth.terraplusplus.projection.transform.FlipVerticalProjectionTransform;
import net.buildtheearth.terraplusplus.projection.transform.OffsetProjectionTransform;
import net.buildtheearth.terraplusplus.projection.transform.ScaleProjectionTransform;
import net.buildtheearth.terraplusplus.projection.transform.SwapAxesProjectionTransform;
import net.buildtheearth.terraplusplus.util.HackBiMap;

/**
 * Identifies implementation classes by their type names.
 * <p>
 * Warning: modifying any of the fields in this class outside of initialization time may cause unexpected behavior.
 *
 * @author DaPorkchop_
 */
public final class GlobalParseRegistries {
	public static final HackBiMap<String, Class<? extends GeographicProjection>> PROJECTIONS = new HackBiMap<String, Class<? extends GeographicProjection>>();

	static {
		//normal projections
		PROJECTIONS.put("centered_mercator", CenteredMercatorProjection.class);
		PROJECTIONS.put("web_mercator", WebMercatorProjection.class);
		PROJECTIONS.put("transverse_mercator", TransverseMercatorProjection.class);
		PROJECTIONS.put("equirectangular", EquirectangularProjection.class);
		PROJECTIONS.put("sinusoidal", SinusoidalProjection.class);
		PROJECTIONS.put("equal_earth", EqualEarthProjection.class);
		PROJECTIONS.put("bte_conformal_dymaxion", BTEDymaxionProjection.class);
		PROJECTIONS.put("dymaxion", DymaxionProjection.class);
		PROJECTIONS.put("conformal_dymaxion", ConformalDynmaxionProjection.class);
		//transformations
		PROJECTIONS.put("flip_horizontal", FlipHorizontalProjectionTransform.class);
		PROJECTIONS.put("flip_vertical", FlipVerticalProjectionTransform.class);
		PROJECTIONS.put("offset", OffsetProjectionTransform.class);
		PROJECTIONS.put("scale", ScaleProjectionTransform.class);
		PROJECTIONS.put("swap_axes", SwapAxesProjectionTransform.class);
	}
}
