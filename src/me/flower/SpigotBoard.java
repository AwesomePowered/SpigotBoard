package me.flower;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.flower.commands.SBCMD;
import me.flower.events.BlockBreak;
import me.flower.events.BlockPlace;
import me.flower.events.CmdPreprocess;
import me.flower.events.Death;
import me.flower.events.Join;
import me.flower.utils.Data;
import me.flower.utils.ScoreboardManager;
import me.flower.utils.U;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

@SuppressWarnings("rawtypes")
public class SpigotBoard extends JavaPlugin {
	
	
	public static SpigotBoard instance;
	
	private FileConfiguration statsConfig = null;
	private File statsConfigFile = null;
	
	
	
	public void onEnable() {
		
		instance = this;
		
		saveDefaultConfig();
		
		saveDStats();
		
		
		try {
			
			Data data = new Data("SpigotBoard2.0");
			
			data.sendData();
			
		} catch (Exception e) {
			
			System.out.println("[SpigotBoard] Website server down, updater unavailble. (Don't worry, it'll be up soon.)");
		}
		
		
		getServer().getPluginManager().registerEvents(new CmdPreprocess(), instance);
		
		getServer().getPluginManager().registerEvents(new Join(), instance);

		getServer().getPluginManager().registerEvents(new Death(), instance);
		
		getServer().getPluginManager().registerEvents(new BlockBreak(), instance);
		
		getServer().getPluginManager().registerEvents(new BlockPlace(), instance);
		
		
		getServer().getPluginCommand("spigotboard").setExecutor(new SBCMD());
		
		
		if(getServer().getPluginManager().getPlugin("Vault") == null) {
			
			U.Vault = 0;
			
			getLogger().log(Level.SEVERE, "Vault not found, disabling support for group.");
			
		} else {
			
			setupPermissions();
		}
		
		
		if(getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
			
			U.PAPI = 0;
			
			getLogger().log(Level.SEVERE, "PlaceholderAPI not found, disabling support for PlaceholderAPI.");
		}
		
		
		try {
			
			setupEconomy();
			
		} catch (Exception e) {
			
			U.Essentials = 0;
		}
		
		
		Scheduler();
	}
	
	
	
	
	
	public void Scheduler() {
		
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
	    	
		      public void run() {
		    	  
		    	  try{
	    			  
						ScoreboardManager.addScoreboard();
						
					} catch (Exception e) {
						
						e.printStackTrace();
					}
		      }
		    }, 20L, U.isb*20);
	}
	
	
	
	
	
	private boolean setupEconomy() {
	    RegisteredServiceProvider economyProvider = instance.getServer().getServicesManager().getRegistration(Economy.class);
	    if (economyProvider != null) {
	      U.economy = (Economy)economyProvider.getProvider();
	    }

	    return U.economy != null;
	  }
	
	
	
	
	
	private boolean setupPermissions() {
		RegisteredServiceProvider permissionProvider = getServer().getServicesManager().getRegistration(Permission.class);
	    if (permissionProvider != null) {
	      U.permission = (Permission)permissionProvider.getProvider();
	    }
	    return U.permission != null;
	  }
	
	
	
	
	
	
	
	
	public void saveDStats() {
	    if (statsConfigFile == null) {
	        statsConfigFile = new File(getDataFolder(), "stats.yml");
	    }
	    if (!statsConfigFile.exists()) {            
	         instance.saveResource("stats.yml", false);
	     }
	}
	
	
	
	public void createStats(){
	    File location = new File(getDataFolder(), "stats.yml");
	   
	    if (!location.exists()) {
	    	
	      try {
	        location.createNewFile();
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	    }
	  }
	
	
	
	public void reloadStats() throws UnsupportedEncodingException {
	    if (statsConfigFile == null) {
	    statsConfigFile = new File(getDataFolder(), "stats.yml");
	    }
	    statsConfig = YamlConfiguration.loadConfiguration(statsConfigFile);

	    Reader defConfigStream = new InputStreamReader(this.getResource("stats.yml"), "UTF8");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        statsConfig.setDefaults(defConfig);
	    }
	}
	
	
	
	public FileConfiguration getStats() throws UnsupportedEncodingException {
	    if (statsConfig == null) {
	        reloadStats();
	    }
	    return statsConfig;
	}
	
	
	
	public void saveStats() {
	    if (statsConfig == null || statsConfigFile == null) {
	        return;
	    }
	    try {
	        getStats().save(statsConfigFile);
	    } catch (IOException ex) {
	        getLogger().log(Level.SEVERE, "Could not save config to " + statsConfigFile, ex);
	    }
	}
	
	

}
