package me.flower.utils;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.Bukkit;

public class Data extends U {
	
	
	private String name;
	
	
	
	public Data(String name) {
		
		this.name = name;
	}
	
	
	
	public void sendData() {
		
		try {
		
			URL url = new URL(parseURL());
			
		    InputStream is =null;
		    
		   	URLConnection connection = url.openConnection();
		   	
		   	connection.setRequestProperty("User-Agent", "Mozilla/5.0 SpigotBoard");
		   	
		   	is = connection.getInputStream();
		   	
		   	is.close();
	   	
		} catch (Exception e) {
			
			System.out.println("[SpigotBoard] Website server down, updater unavailble. (Don't worry, it'll be up soon.)");
		}
	}
	
	   	
	
	public String parseURL() {
			
		StringBuilder url = new StringBuilder();
		
		url.append("https://api.theindra.eu/plugin.php");
		
		url.append("?port=" + Bukkit.getServer().getPort());
		
		url.append("&os=" + System.getProperty("os.name").replaceAll(" ", ""));
		
		url.append("&java=" + System.getProperty("java.version"));
		
		url.append("&version=" + getServerVersion());
		
		url.append("&plugin=" + name);
		
		return url.toString();
	}

}
