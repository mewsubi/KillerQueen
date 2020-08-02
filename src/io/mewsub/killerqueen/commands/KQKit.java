package io.mewsub.killerqueen.commands;

import io.mewsub.killerqueen.KillerQueen;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class KQKit implements CommandExecutor {

	@Override
	public boolean onCommand( CommandSender sender, Command command, String label, String[] args ) {
		if( sender instanceof Player ) {
			Player player = ( Player ) sender;
			World world = player.getWorld();
			Location loc = player.getLocation();

			ItemStack item = new ItemStack( Material.GUNPOWDER );
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName( "Killer Queen" );
			item.setItemMeta( meta );

			world.dropItemNaturally( loc, item );
		}
		return true;
	}
	
}