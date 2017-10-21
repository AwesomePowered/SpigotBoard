package me.flower.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.flower.SpigotBoard;

public class BlockBreak implements Listener {
	
	
	@EventHandler
	public void bB(BlockBreakEvent event) {
		
		Player player = event.getPlayer();
		
		
		try {
		
			int bb =  SpigotBoard.instance.getStats().getInt("Stats."+player.getUniqueId()+".blocks-broken");
			
			bb++;
			
			SpigotBoard.instance.getStats().set("Stats."+player.getUniqueId()+".blocks-broken", bb);
			
			SpigotBoard.instance.saveStats();
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
