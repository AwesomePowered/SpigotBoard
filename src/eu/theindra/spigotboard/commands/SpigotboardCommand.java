package eu.theindra.spigotboard.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.theindra.spigotboard.Main;
import eu.theindra.spigotboard.utils.WebUtils;

public class SpigotboardCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		Player p = (Player) arg0;
		
		if(arg2.equalsIgnoreCase("spigotboard")){	
			if(arg3.length == 0){
				p.sendMessage("§7§m----------------------");
				p.sendMessage("§9Spigot§6Board §eCommands:");
				p.sendMessage("§7§m----------------------");
				p.sendMessage("§f- §7/spigotboard reload");
				p.sendMessage("§f- §7/spigotboard version");
		    	p.sendMessage("§f- §7/spigotboard author"); 
		    	p.sendMessage("§7§m---"); 
		    	p.sendMessage("§f- §7/spigotboard view");
		    	p.sendMessage("§7§m----------------------");
			}else if(arg3[0].equalsIgnoreCase("reload")){
			  	if (p.hasPermission("Spigotboard.reload")) {
				     Main.instance.reloadConfig();
				     p.sendMessage(Main.instance.prefix + "§7The §aConfig §7has been reloaded succesfully.");
				} else {
				     p.sendMessage(Main.instance.prefix + "§cYour not allowed to reload the config!");
				}
			}else if(arg3[0].equalsIgnoreCase("version")){
				p.sendMessage(Main.instance.prefix + "§7Your current version is §a" + Main.instance.getDescription().getVersion());
				p.sendMessage(Main.instance.prefix + "§7Checking latest release please wait...");
				WebUtils.GetLatetsRelease(p);
			}else if(arg3[0].equalsIgnoreCase("author")){
			    p.sendMessage(Main.instance.prefix + "§7Why do u care about the developers?");
			    p.sendMessage(Main.instance.prefix + "§7Developed by §9TheIndra");
			    p.sendMessage(Main.instance.prefix + "§7Tested by §9SquapingDeath");
			}else if(arg3[0].equalsIgnoreCase("view")){
				p.sendMessage("§7Title: §8" + Main.instance.getConfig().getString("title"));
				List < String > Lines = Main.instance.getConfig().getStringList("text");
				int Line = 1;
				for (String s: Lines) {
					p.sendMessage("§7" + Line++ + ". §8" + s);
				}
			}else{
			  p.sendMessage(Main.instance.prefix + "§cSubcommand not found, Type '/spigotboard' for a list of command");
			}
		}
		return false;
	}

}
