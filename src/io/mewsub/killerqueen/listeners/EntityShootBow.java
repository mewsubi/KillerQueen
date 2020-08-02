package io.mewsub.killerqueen.listeners;

import io.mewsub.killerqueen.KillerQueen;
import io.mewsub.killerqueen.entities.Bomb;

import io.mewsub.killerqueen.enchantments.KQEnchant;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import org.bukkit.block.Block;

import org.bukkit.block.data.BlockData;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemStack;

import org.bukkit.NamespacedKey;

import org.bukkit.util.Vector;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import org.bukkit.event.entity.EntityShootBowEvent;

import net.minecraft.server.v1_16_R1.PacketPlayOutEntityDestroy;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;

public class EntityShootBow implements Listener {

    @EventHandler
    public void onEntityShootBow( EntityShootBowEvent evt ) {
       	if( evt.getEntity() instanceof Player ) {
       		Player player = ( Player ) evt.getEntity();
       		Entity ent = evt.getProjectile();
       		if( KillerQueen.game.hasActiveBomb( player ) ) {

	        } else if( ent instanceof Arrow ) {
	            Bomb b = KillerQueen.game.chargeBomb( player, player );
	            b.setEntity( ent );
	        }
       	}
    }

}