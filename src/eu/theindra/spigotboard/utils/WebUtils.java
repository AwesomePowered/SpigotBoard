package eu.theindra.spigotboard.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.entity.Player;

public class WebUtils {
	
	public static String GetLatetsRelease(Player p){
		URL url =null;
	    try {
	        url = new URL("https://theindra.eu/SpigotBoardVersion.html");
	    } catch (MalformedURLException e) {
			p.sendMessage("§cError While getting latest version from the web: " + e.getMessage());
	    }
	    InputStream is =null;
	    try {
	    	URLConnection connection = url.openConnection();
	    	connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36");
	    	is = connection.getInputStream();
	    } catch (IOException e) {
			p.sendMessage("§cError While getting latest version from the web: " + e.getMessage());
	    }
	    BufferedReader reader = new BufferedReader( new InputStreamReader( is )  );
	    try {
			p.sendMessage("§f[§9Spigot§6Board§f] §7The latest release is version §3" + reader.readLine());
		} catch (IOException e) {
			p.sendMessage("§cError While getting latest version from the web: " + e.getMessage());
		}
	    try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
			p.sendMessage("§cError While closing the connection: " + e.getMessage());
		}
		return "§cError";
	}
	
	public static String GetLatetsRelease2() throws IOException{
		    URL url =null;
	        url = new URL("https://theindra.eu/SpigotBoardVersion.html");
	        InputStream is =null;
	    	URLConnection connection = url.openConnection();
	    	connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36");
	    	is = connection.getInputStream();
	        BufferedReader reader = new BufferedReader( new InputStreamReader( is )  );
			return reader.readLine();
	}

}
