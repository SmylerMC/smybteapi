package fr.thesmyler.smybteapi;

import static fr.thesmyler.smybteapi.SmyBteApiUtil.getPropertyOrEnv;
import static fr.thesmyler.smybteapi.SmyBteApiUtil.touchJsonResponse;
import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.ipAddress;
import static spark.Spark.port;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
	public static String INSTANCE_CREDIT;
	public static String INSTANCE_INFO;
	
	public static final Gson GSON = new GsonBuilder().create();
	public static final Gson GSON_PRETTY = new GsonBuilder().setPrettyPrinting().create();
	
	public static final Logger LOGGER = Logger.getLogger("smybteapi");
	
    public static void main(String... args) {
    	setupLogging();
    	setupPreferences();
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
    	String portStr = getPropertyOrEnv("smybteapi.port");
    	if(portStr != null) {
    		try {
    			PORT = Integer.parseInt(portStr);
    		} catch(NumberFormatException e) {
    			LOGGER.severe("Failed to parse port number");
    			throw new RuntimeException("Forcibly shuting down!");
    		}
    	}
    	INTERFACE = getPropertyOrEnv("smybteapi.interface");
    	INSTANCE_NAME = getPropertyOrEnv("smybteapi.instance.name");
    	INSTANCE_CREDIT = getPropertyOrEnv("smybteapi.instance.credit");
    	INSTANCE_INFO = getPropertyOrEnv("smybteapi.instance.info");
    }
    
    public static void setupSpark() {
    	if(INTERFACE != null) {
    		ipAddress(INTERFACE);
    	}
    	if(PORT > 0) {
    		port(PORT);
    	}
        after(SmyBteApi::logRequestResponsePair);
        get("/info", SmyBteApi::info);
    }
    
    private static void logRequestResponsePair(Request request, Response response) {
    	String url = request.url();
    	String client = request.ip();
    	String method = request.requestMethod();
    	int status = response.raw().getStatus();
    	LOGGER.info(String.format("[%s] - [%s] %s %s", client, status, method, url));
    }
    
    public static String info(Request request, Response response) {
    	touchJsonResponse(response);
		Map<String, String> infos = new HashMap<>();
		infos.put("software-version", VERSION);
		infos.put("software-license", SOFTWARE_LICENSE);
		infos.put("software-credit", SOFTWARE_CREDIT);
		infos.put("software-repo", SOFTWARE_REPO);
		if(INSTANCE_NAME != null) {
			infos.put("instance-name", INSTANCE_NAME);
		}
		if(INSTANCE_CREDIT != null) {
			infos.put("instance-credit", INSTANCE_CREDIT);
		}
		if(INSTANCE_INFO != null) {
			infos.put("instance-info", INSTANCE_INFO);
		}
		infos.put("host", request.host());
		infos.put("client", request.ip());
		infos.put("user-agent", request.userAgent());
		return GSON.toJson(infos);
	}
    
}