package eu.theindra.spigotboard.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import eu.theindra.spigotboard.Main;
import eu.theindra.spigotboard.utils.PlaceholderUtils;

public class SBScoreboard implements ScoreboardManager{

	@Override
	public void enableScoreboard() {
        final Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        final Objective obj = board.registerNewObjective("Spigotboard", "dummy");
        
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("title")));

        new BukkitRunnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()) {
                	int count = Main.instance.getConfig().getList("text").size();
                	
                	PlaceholderUtils placeholders = new PlaceholderUtils(p);
                    for(Object text : Main.instance.getConfig().getList("text")){
                    	obj.getScore(ChatColor.translateAlternateColorCodes('&', placeholders.replace(text.toString()))).setScore(count);
                    	count--;
                    }
                    p.setScoreboard(board);
                }
            }
        }.runTaskTimer(Main.instance, 0, 20);
	}

	@Override
	public void disableScoreboard() {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        board.registerNewObjective("Spigotboard", "dummy");

        for(Player p : Bukkit.getOnlinePlayers()) {
            p.setScoreboard(board);
        }
	}

}
