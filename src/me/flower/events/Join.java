package me.flower.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.flower.SpigotBoard;
import me.flower.utils.U;
import me.flower.utils.Updater;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Join extends U implements Listener {
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		
		
		if(SpigotBoard.instance.getConfig().getBoolean("Settings.update-reminder") == true) {
			
			try {
				
				latestSBversion = Updater.getLatestVersion();
				
			} catch (Exception e) {
				
				System.out.println("[SpigotBoard] Could not connect to website, updater unavailble.");
				
				return;
			}
			
			
			if(player.isOp()) {
				
				if(!SpigotBoard.instance.getDescription().getVersion().equals(latestSBversion)) {
					
					
					if(getServerVersion().equalsIgnoreCase("v1_8_R1") || getServerVersion().equalsIgnoreCase("v1_8_R3")) {
						
						System.out.println("[SpigotBoard] Server version detected (1.8.x). Not compatible with clickable message in chat.");
						
						player.sendMessage("§cSpigot§7Board §8» §7There's a new update available on the spigot page, make sure to update for bugfixes!");
						
						return;
						
					} else {
						
						player.sendMessage("§cSpigot§7Board §8» §7You are currently using §cv"+SpigotBoard.instance.getDescription().getVersion()+".");
						
						player.sendMessage("§cSpigot§7Board §8» §7There's a new update available (§cv"+latestSBversion+"§7).");
						
						
						TextComponent s = new TextComponent("Spigot");
						
						s.setColor(ChatColor.RED);
						
						
						TextComponent b = new TextComponent("Board");
						
						b.setColor(ChatColor.GRAY);
						
						
						TextComponent c = new TextComponent(" » ");
						
						c.setColor(ChatColor.DARK_GRAY);
						
						
						TextComponent m = new TextComponent("Click here for download link.");
						
						m.setColor(ChatColor.RED);
						
						m.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/spigotboard.47497/history"));
						
						m.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Click to open download link.").create()));
						
						
						s.addExtra(b);
						
						s.addExtra(c);
						
						s.addExtra(m);
						
						
						player.spigot().sendMessage(s);
						
						return;
					}
					
				} else {
					
					player.sendMessage("§cSpigot§7Board §8» §7No new updates found.");
					
					return;
				}
				
			}
			
		} else {
			
			return;
		}
		
		
		try {
		
		if(!SpigotBoard.instance.getStats().contains("Stats."+player.getUniqueId())) {
			
			SpigotBoard.instance.getStats().set("Stats."+player.getUniqueId()+".player-name", player.getName());
			SpigotBoard.instance.getStats().set("Stats."+player.getUniqueId()+".kills", 0);
			SpigotBoard.instance.getStats().set("Stats."+player.getUniqueId()+".deaths", 0);
			SpigotBoard.instance.getStats().set("Stats."+player.getUniqueId()+".blocks-broken", 0);
			SpigotBoard.instance.getStats().set("Stats."+player.getUniqueId()+".blocks-placed", 0);
			
		} else {
			
			SpigotBoard.instance.getStats().set("Stats."+player.getUniqueId()+".player-name", player.getName());
		}
		
		SpigotBoard.instance.saveStats();
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
