package io.mewsub.killerqueen;

import java.lang.reflect.Method;
import java.lang.reflect.Field;

import io.mewsub.killerqueen.KillerQueen;

import io.mewsub.killerqueen.entities.Bomb;

import java.lang.Math;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.Location;
import org.bukkit.World;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;

import net.minecraft.server.v1_16_R1.EntityLiving;
import net.minecraft.server.v1_16_R1.EntityTNTPrimed;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftTNTPrimed;

import org.bukkit.scheduler.BukkitRunnable;

import org.bukkit.util.BoundingBox;

public class KQGame extends BukkitRunnable {

	private Map<UUID, Bomb> activeBombs;

	public KQGame() {
		this.activeBombs = new HashMap<UUID, Bomb>();
	}

	public Bomb chargeBomb( Player player, Entity ent ) {
		Bomb b = new Bomb( ent );
		this.activeBombs.put( player.getUniqueId(), b );
		return b;
	}

	public void detonateBomb( Player player ) {
		UUID uuid = player.getUniqueId();
		if( this.activeBombs.containsKey( uuid ) ) {
			this.activeBombs.get( uuid ).detonate();
		}
	}

	public boolean hasActiveBomb( Player player ) {
		UUID uuid = player.getUniqueId();
		return this.activeBombs.containsKey( uuid ) && ( this.activeBombs.get( uuid ) != null );
	}

	public Bomb getBomb( Entity ent ) {
		Bomb b;
		for( Map.Entry<UUID, Bomb> e : this.activeBombs.entrySet() ) {
			b = e.getValue();
			if( b.getEntity() == ent ) {
				return b;
			}
		}
		return null;
	}

	public void run() {
		Iterator<Map.Entry<UUID, Bomb>> itr = this.activeBombs.entrySet().iterator();
		Bomb bomb;
		Entity ent;
		Location loc;
		World world;
		List<Entity> hitbox;
		BoundingBox bombBox;
		double maxDist;
		String uuid;
		Player player;
		Map.Entry<UUID, Bomb> etr;
        while( itr.hasNext() ) {
        	etr = itr.next();
			bomb = etr.getValue();
			ent = bomb.getEntity();
			player = KillerQueen.server.getPlayer( etr.getKey() );
			if( bomb.getDetonating() ) {
				loc = ent.getLocation();
				world = ent.getWorld();
				if( ent instanceof FallingBlock || ent instanceof Item ) {
					world.createExplosion( loc, 6, false, false, ent );
				} else {
					Player source = player;
					TNTPrimed tnt = ( TNTPrimed ) world.spawnEntity( loc, EntityType.PRIMED_TNT );
					EntityLiving nmsEntityLiving = ( EntityLiving ) ( ( ( CraftLivingEntity ) source ).getHandle() );
					EntityTNTPrimed nmsTNT = ( EntityTNTPrimed ) ( ( ( CraftTNTPrimed ) tnt ).getHandle() );
					try {
					    Field sourceField = EntityTNTPrimed.class.getDeclaredField( "source" );
					    sourceField.setAccessible( true );
					    sourceField.set( nmsTNT, nmsEntityLiving );
					} catch ( Exception ex ) {
					    ex.printStackTrace();
					}
					world.createExplosion( loc, 6, false, false, tnt );
					tnt.remove();
				}
				itr.remove();
			} else {
				if( !( ent instanceof Item ) ) {
					uuid = bomb.getUUID().toString();
					bombBox = ent.getBoundingBox();
					hitbox = ent.getNearbyEntities( bombBox.getWidthX(), bombBox.getHeight(), bombBox.getWidthZ() );
			        for( Entity hit : hitbox ) {
		                if( hit instanceof LivingEntity ) {
		                	if( !hit.hasMetadata( uuid ) ) {
		                		if( bombBox.overlaps( hit.getBoundingBox() ) ) {
		                			bomb.setEntity( hit );
		                		}
		                	}
		                	break;
		                }
		            }
				}
			}
		}
	}
}