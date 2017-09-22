package eu.theindra.spigotboard.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlaceholderUtils {
	
	Player p;
	
	public PlaceholderUtils(Player p){
		this.p = p;
	}
	
	public String[] placeholders = { "%name", "%displayname", "%time", "%online", "%max", "%health", "%ip", "%location" };
	
	public String replace(String text){
		// most shitty way
		String[] values = { p.getName(), p.getDisplayName(), GetTime(), Bukkit.getOnlinePlayers().size() + "", Bukkit.getMaxPlayers() + "", 
				p.getHealth() + "", p.getAddress().getAddress().toString(), Math.round(p.getLocation().getX()) + " " + Math.round(p.getLocation().getY()) + " " + Math.round(p.getLocation().getBlockZ()) };
		
		int count = 0;
		for(String text1 : placeholders){
			text = text.replace(text1, values[count]);
			count++;
		}
		
		return text;
		
	}
	
	public static String GetTime(){
	    DateFormat dateFormat = new SimpleDateFormat("HH:mm");
	    Date date = new Date();
	    return dateFormat.format(date);		
	}
	
}
