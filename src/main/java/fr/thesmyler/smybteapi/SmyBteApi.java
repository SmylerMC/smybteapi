package fr.thesmyler.smybteapi;

import static fr.thesmyler.smybteapi.SmyBteApiUtil.getPropertyOrEnv;
import static fr.thesmyler.smybteapi.SmyBteApiUtil.touchJsonResponse;
import static spark.Spark.get;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
	
	public static String VERSION = null;
	public static final String SOFTWARE_LICENSE = "MIT License";
	public static final String SOFTWARE_CREDIT = "SmylerMC <smyler@mail.com>";
	public static final String SOFTWARE_REPO = "https://github.com/SmylerMC/smyler-bte-api";
	
	public static String INSTANCE_NAME = null;
	public static String INSTANCE_CREDIT = null;
	public static String INSTANCE_INFO = null;
	
	public static final Gson GSON = new GsonBuilder().create();
	public static final Gson GSON_PRETTY = new GsonBuilder().setPrettyPrinting().create();
	
    public static void main(String... args) {
    	setup();
        get("/info", SmyBteApi::info);
    }
    
    public static void setup() {
    	try(InputStream is = SmyBteApi.class.getResourceAsStream("version.properties"); 
    			BufferedInputStream bs = new BufferedInputStream(is);
    			Scanner sc = new Scanner(bs)){
    		VERSION = sc.nextLine();
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	INSTANCE_NAME = getPropertyOrEnv("smybteapi.instance.name");
    	INSTANCE_CREDIT = getPropertyOrEnv("smybteapi.instance.credit");
    	INSTANCE_INFO = getPropertyOrEnv("smybteapi.instance.info");
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