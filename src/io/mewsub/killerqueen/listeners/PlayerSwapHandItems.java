package io.mewsub.killerqueen.listeners;

import io.mewsub.killerqueen.KillerQueen;

import io.mewsub.killerqueen.enchantments.KQEnchant;

import org.bukkit.NamespacedKey;

import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;

import org.bukkit.Material;

import org.bukkit.inventory.ItemStack;

import org.bukkit.inventory.meta.ItemMeta;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerSwapHandItems implements Listener {
    
    @EventHandler
    public void onPlayerSwapHandItemsEvent( PlayerSwapHandItemsEvent evt ) {
        Player player = evt.getPlayer();
        ItemStack item = evt.getMainHandItem();
        if( item == null || item.getType() == Material.AIR ) return;
        if( item.getItemMeta().getDisplayName().equals( "Killer Queen" ) ) {
        	if( KillerQueen.game.hasActiveBomb( player ) ) {
        		KillerQueen.game.detonateBomb( player );
        	} else {
        		ItemStack bomb = evt.getOffHandItem();
	        	if( bomb == null || bomb.getType() == Material.AIR ) {
	        		evt.setCancelled( true );
	        		return;
	        	} else {
	        		ItemMeta meta = bomb.getItemMeta();
	        		if( meta == null ) return;
	        		meta.addEnchant( new KQEnchant( new NamespacedKey( KillerQueen.plugin, "KQ_ENCHANT" ) ), 1, true );
	        		bomb.setItemMeta( meta );
	        	}
        	}
        	evt.setCancelled( true );
        }
    }

}