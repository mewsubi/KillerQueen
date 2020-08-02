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
import org.bukkit.entity.Projectile;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemStack;

import org.bukkit.NamespacedKey;

import org.bukkit.util.Vector;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import org.bukkit.event.entity.ProjectileLaunchEvent;

import net.minecraft.server.v1_16_R1.PacketPlayOutEntityDestroy;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;

public class ProjectileLaunch implements Listener {

    @EventHandler
    public void onProjectileLaunch( ProjectileLaunchEvent evt ) {
    	Projectile ent = evt.getEntity();
       	if( ent instanceof Arrow ) {
       		if( ent.hasMetadata( "bomb" ) ) {
       			PacketPlayOutEntityDestroy destroyPacket = new PacketPlayOutEntityDestroy( ent.getEntityId() );
	            for( Player p : KillerQueen.server.getOnlinePlayers() ) {
	            	( ( CraftPlayer ) p ).getHandle().playerConnection.sendPacket( destroyPacket );
	            }
       		}
       	}
    }

}

