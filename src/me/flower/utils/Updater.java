package me.flower.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import me.flower.SpigotBoard;

public class Updater extends U {
	
	
	public static URL getURL() {
		
		URL url = null;
		
		try {
			
			url = new URL("https://theindra.eu/spigotboard/version");
			
		} catch (Exception e) {
			
			System.out.println("[SpigotBoard] Website server down, updater unavailble. (Don't worry, it'll be up soon.)");
		}
		
		return url;
	}
	
	
	
	
	
	public static String getLatestVersion() {
		
		String version = latestSBversion;
		
		try {
		
			HttpURLConnection request = (HttpURLConnection) getURL().openConnection();
			
			request.setRequestProperty("User-Agent", "Mozilla/5.0 spigotboard");
		
			request.connect();
			
			
			JsonParser jp = new JsonParser();
			
			JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
			
			JsonObject obj = root.getAsJsonObject();
			
			version = obj.get("version").getAsString();
			
			
		} catch (Exception e){
			
			SpigotBoard.instance.getLogger().log(Level.SEVERE, "[SpigotBoard] Failed to catch latest SpigotBoard version from web. (Site may be down)");
		}
		
		return version;
	}

}
