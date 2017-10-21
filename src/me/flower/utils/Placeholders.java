package me.flower.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.bukkit.entity.Player;

import me.flower.SpigotBoard;

public class Placeholders {
	
	
	public static String getRAM() {
		
		Runtime r = Runtime.getRuntime();
		
		int mb = 1048576;
		
		return (r.totalMemory() - r.freeMemory()) / mb + " MB";
	}
	
	
	public static String getMaxRAM() {
		
		int mb = 1048576;
		
	    return Runtime.getRuntime().maxMemory() / mb + "MB";
	}
	
	
	public static String getTime() {
	    
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of(SpigotBoard.instance.getConfig().getString("Settings.timezone")));
		
		String time = DateTimeFormatter.ofPattern("hh:mm a z").format(zdt);

		return time;
	}
	
	
	public static String getLocation(Player player) {
		
		int x = player.getLocation().getBlockX();
		
		int y = player.getLocation().getBlockY();
		
		int z = player.getLocation().getBlockZ();
		
		
		return "X: "+x+" Y: "+y+" Z: "+z;
	}
	
	
	public static boolean isFlying(Player player) {
		
		if(player.isFlying()) {
			
			return true;
		}
		
		return false;
	}
	
	
	public static int getKills(UUID uuid) {
		
		int kills = 0;
		
		try {
		
		kills = SpigotBoard.instance.getStats().getInt("Stats."+uuid+".kills");
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return kills;
	}
	
	
	public static int getDeaths(UUID uuid) {
		
		int deaths = 0;
		
		try {
		
		deaths = SpigotBoard.instance.getStats().getInt("Stats."+uuid+".deaths");
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return deaths;
	}
	
	
	public static int getBB(UUID uuid) {
		
		int bb = 0;
		
		try {
		
		bb = SpigotBoard.instance.getStats().getInt("Stats."+uuid+".blocks-broken");
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return bb;
	}
	
	
	public static int getBP(UUID uuid) {
		
		int bp = 0;
		
		try {
		
		bp = SpigotBoard.instance.getStats().getInt("Stats."+uuid+".blocks-placed");
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return bp;
	}
}
