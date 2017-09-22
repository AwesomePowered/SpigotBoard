package eu.theindra.spigotboard;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import eu.theindra.spigotboard.commands.SpigotboardCommand;
import eu.theindra.spigotboard.scoreboard.SBScoreboard;
import eu.theindra.spigotboard.scoreboard.ScoreboardManager;
import eu.theindra.spigotboard.utils.DataUtils;

public class Main extends JavaPlugin {
	
	// einstein one day said always comment your code :)

	public static Main instance;
	public boolean[] extensions = { false, false }; // vault, placeholderapi
	
	public String prefix = "§f[§9Spigot§eboard§f] §7"; //lazyness
	
	public void onEnable(){
		instance = this;
		
		
		DataUtils data = new DataUtils("SpigotBoard2.0");
		try {
			data.sendData();
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage("§cCouldn't send data to theindra api");
		}
		
		getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "§9Spigot§eboard version " + this.getDescription().getVersion() + " " + "Has been enabled!");
		
		// vault en placeholder api aan?
		if (getServer().getPluginManager().getPlugin("Vault") != null) {
			extensions[0] = true;
		}
		
		if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
			extensions[1] = true;
		}
		
		// bukkit vertellen welke commands we hebben :D
		getCommand("spigotboard").setExecutor(new SpigotboardCommand());
		
		// laad config
		loadConfiguration();
		
		// enable scoreboard
		new SBScoreboard().enableScoreboard();
	}
	
	public void onDisable(){
		new SBScoreboard().disableScoreboard();
	}
	
	 public void loadConfiguration() {
		  getConfig().options().copyDefaults(true);
		  saveDefaultConfig();
	}
	
}
