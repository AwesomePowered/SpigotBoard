package me.flower.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.flower.SpigotBoard;

public class BlockPlace implements Listener {
	
	
	@EventHandler
	public void bB(BlockPlaceEvent event) {
		
		Player player = event.getPlayer();
		
		
		try {
		
			int bp =  SpigotBoard.instance.getStats().getInt("Stats."+player.getUniqueId()+".blocks-placed");
			
			bp++;
			
			SpigotBoard.instance.getStats().set("Stats."+player.getUniqueId()+".blocks-placed", bp);
			
			SpigotBoard.instance.saveStats();
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
