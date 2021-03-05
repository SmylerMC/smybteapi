package fr.thesmyler.smybteapi;

import static fr.thesmyler.smybteapi.SmyBteApiUtil.getPropertyOrEnv;
import static fr.thesmyler.smybteapi.SmyBteApiUtil.touchJsonResponse;
import static spark.Spark.afterAfter;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.internalServerError;
import static spark.Spark.ipAddress;
import static spark.Spark.notFound;
import static spark.Spark.port;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import fr.thesmyler.smybteapi.exception.ApiSpecificationException;
import fr.thesmyler.smybteapi.exception.ErrorHandler;
import fr.thesmyler.smybteapi.projection.ProjectionRoutes;
import fr.thesmyler.smybteapi.projection.Projections;
import spark.Request;
import spark.Response;

/**
 * Main class for this API
 * 
 * @author SmylerMC
 *
 */
public class SmyBteApi {
	
	public static String VERSION;
	public static final String SOFTWARE_LICENSE = "MIT License";
	public static final String SOFTWARE_CREDIT = "SmylerMC <smyler@mail.com>";
	public static final String SOFTWARE_REPO = "https://github.com/SmylerMC/smyler-bte-api";
	
	public static String INTERFACE;
	public static int PORT = -1;
	
	public static String INSTANCE_NAME;
	public static String INSTANCE_ADMIN;
	public static String INSTANCE_INFO;
	
	public static final Gson GSON = new GsonBuilder().create();
	public static final Gson GSON_PRETTY = new GsonBuilder().setPrettyPrinting().create();
    public static final JsonMapper JSON_MAPPER = JsonMapper.builder()
            .configure(JsonReadFeature.ALLOW_JAVA_COMMENTS, true)
            .configure(JsonReadFeature.ALLOW_LEADING_ZEROS_FOR_NUMBERS, true)
            .configure(JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS, true)
            .configure(JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS, true)
            .configure(JsonReadFeature.ALLOW_TRAILING_COMMA, true)
            .build();
	
	public static final Logger LOGGER = Logger.getLogger("smybteapi");
	
    public static void main(String... args) {
    	setupLogging();
    	setupPreferences();
    	Projections.setup();
    	setupSpark();
    }
    
    public static void setupLogging() {
    	try(InputStream is = SmyBteApi.class.getResourceAsStream("logging.properties")) {
    		LogManager.getLogManager().readConfiguration(is);
    	} catch (IOException e) {
			LOGGER.severe("Failed to set logging format!");
			e.printStackTrace();
		}
    	LOGGER.info("Starting Smyler BTE API...");
    	try(InputStream is = SmyBteApi.class.getResourceAsStream("version.properties"); 
    			BufferedInputStream bs = new BufferedInputStream(is);
    			Scanner sc = new Scanner(bs)){
    		VERSION = sc.nextLine();
    		LOGGER.info("Version: " + VERSION);
    	} catch (IOException e) {
    		LOGGER.severe("Failed to read version information!");
			e.printStackTrace();
		}
    }
    
    public static void setupPreferences() {
    	INTERFACE = getPropertyOrEnv("smybteapi.interface");
    	if(INTERFACE != null) LOGGER.info("Will use interface: " + INTERFACE);
    	String portStr = getPropertyOrEnv("smybteapi.port");
    	if(portStr != null) {
    		try {
    			PORT = Integer.parseInt(portStr);
    			LOGGER.info("Will use port: " + PORT);
    		} catch(NumberFormatException e) {
    			LOGGER.severe("Failed to parse port number");
    			throw new RuntimeException("Forcibly shutting down!");
    		}
    	}
    	INSTANCE_NAME = getPropertyOrEnv("smybteapi.instance.name");
    	if(INSTANCE_NAME != null) LOGGER.info("Instance name: " + INSTANCE_NAME);
    	INSTANCE_ADMIN = getPropertyOrEnv("smybteapi.instance.admin");
    	if(INSTANCE_ADMIN != null) LOGGER.info("Instance administrator: " + INSTANCE_ADMIN);
    	INSTANCE_INFO = getPropertyOrEnv("smybteapi.instance.info");
    	if(INSTANCE_INFO != null) LOGGER.info("Additional instance information: " + INSTANCE_INFO);
    }
    
    public static void setupSpark() {
    	if(INTERFACE != null) {
    		ipAddress(INTERFACE);
    	}
    	if(PORT > 0) {
    		port(PORT);
    	}
        exception(ApiSpecificationException.class, ErrorHandler::handleUserGeneratedException);
        internalServerError(ErrorHandler::handleServerError);
        notFound(ErrorHandler::handleNotFound);
        get("/info", SmyBteApi::info);
        get("/projection/toGeo", ProjectionRoutes::toGeo);
        get("/projection/fromGeo", ProjectionRoutes::fromGeo);
        afterAfter(SmyBteApi::logRequestResponsePair);
    }
    
    private static void logRequestResponsePair(Request request, Response response) {
    	String url = request.pathInfo() + "?" + request.queryString();
    	String client = request.ip();
    	String method = request.requestMethod();
    	int status = response.status();
    	LOGGER.info(String.format("[%s - %s] - [%s] %s %s", client, request.userAgent(), status, method, url));
    }
    
    public static String info(Request request, Response response) {
    	touchJsonResponse(response);
    	JsonObject json = new JsonObject();
    	json.addProperty("software-version", VERSION);
    	json.addProperty("software-license", SOFTWARE_LICENSE);
    	json.addProperty("software-credit", SOFTWARE_CREDIT);
    	json.addProperty("software-repo", SOFTWARE_REPO);
		if(INSTANCE_NAME != null) {
			json.addProperty("instance-name", INSTANCE_NAME);
		}
		if(INSTANCE_ADMIN != null) {
			json.addProperty("instance-administrator", INSTANCE_ADMIN);
		}
		if(INSTANCE_INFO != null) {
			json.addProperty("instance-info", INSTANCE_INFO);
		}
		json.addProperty("host", request.host());
		json.addProperty("client", request.ip());
		json.addProperty("user-agent", request.userAgent());
		return GSON.toJson(json);
	}
    
}