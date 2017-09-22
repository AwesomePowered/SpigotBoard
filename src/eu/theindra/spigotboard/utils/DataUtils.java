package eu.theindra.spigotboard.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.Bukkit;

public class DataUtils {
	
	private String plugin;
	
	public DataUtils(String name) {
		this.plugin = name;
	}
		
	public void sendData() throws IOException {
		URL url = new URL(createURL());
	    InputStream is =null;
	   	URLConnection connection = url.openConnection();
	   	connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36");
	   	is = connection.getInputStream();
	   	is.close();
    }
	
	public String createURL() throws IOException{
		return "https://api.theindra.eu/plugin.php?ip=" + externalIP() + "&port=" + Bukkit.getServer().getPort() +"&os=" + System.getProperty("os.name").replaceAll(" ", "") + "&java=" + System.getProperty("java.version") + "&version=" + getVersion() + "&plugin=" + plugin + "&country=" + System.getProperty("user.language");
	}
	    
	public String externalIP() throws IOException{
	   	URL ipurl = new URL("http://checkip.amazonaws.com");
	   	BufferedReader in = new BufferedReader(new InputStreamReader(ipurl.openStream()));

	   	return in.readLine();
	}
	    
	public String getVersion() {
		final String packageName = Bukkit.getServer().getClass().getPackage().getName();
	    return packageName.substring(packageName.lastIndexOf('.') + 1);
	}

}
