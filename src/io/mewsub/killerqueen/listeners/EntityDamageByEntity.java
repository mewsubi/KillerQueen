package io.mewsub.killerqueen.listeners;

import io.mewsub.killerqueen.KillerQueen;

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

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener {

    @EventHandler
    public void onEntityDamageByEntity( EntityDamageByEntityEvent evt ) {
        Entity e1 = evt.getDamager();
        Entity e2 = evt.getEntity();
    	switch( evt.getCause() ) {
    		case ENTITY_EXPLOSION:
    			if( e1 instanceof FallingBlock || e1 instanceof Item ) {
					evt.setDamage( 0.0 );
    			}
    			break;
            case PROJECTILE:
                if( e1 instanceof Player ) {
                    evt.setDamage( 0.0 );
                    return;
                }
                if( e1 instanceof Arrow ) {
                    Arrow a = ( Arrow ) e1;
                    if( a.getShooter() instanceof Player ) {
                        evt.setDamage( 0.0 );
                    }
                }
                break;
    		default:
    			if( e1 instanceof Player ) {
    				evt.setDamage( 0.0 );
    			}
    			break;
    	}

    }

}