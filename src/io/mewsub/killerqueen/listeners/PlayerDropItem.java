package io.mewsub.killerqueen.listeners;

import io.mewsub.killerqueen.KillerQueen;

import io.mewsub.killerqueen.enchantments.KQEnchant;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import org.bukkit.block.Block;

import org.bukkit.block.data.BlockData;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemStack;

import org.bukkit.NamespacedKey;

import org.bukkit.util.Vector;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItem implements Listener {

    @EventHandler
    public void onPlayerDropItem( PlayerDropItemEvent evt ) {
        Player player = evt.getPlayer();
        World world;
        if( KillerQueen.game.hasActiveBomb( player ) ) {

        } else {
            Item item = evt.getItemDrop();
            //if( item.getItemStack().containsEnchantment( new KQEnchant( new NamespacedKey( KillerQueen.plugin, "KQ_ENCHANT" ) ) ) ) {
            KillerQueen.game.chargeBomb( player, item );
            //}
        }
    }

}