package io.mewsub.killerqueen.listeners;

import io.mewsub.killerqueen.KillerQueen;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import org.bukkit.block.Block;

import org.bukkit.block.data.BlockData;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemStack;

import org.bukkit.util.Vector;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onPlayerInteract( PlayerInteractEvent evt ) {
        Player player = evt.getPlayer();
        World world;
        switch( evt.getAction() ) {
            case LEFT_CLICK_BLOCK:
                if( KillerQueen.game.hasActiveBomb( player ) ) {

                } else {
                    ItemStack item = evt.getItem();
                    if( item == null || item.getType() == Material.AIR ) {

                        Block block = evt.getClickedBlock();
                        BlockData data = block.getBlockData();
                        block.setType( Material.AIR );

                        FallingBlock fall = block.getLocation().getWorld().spawnFallingBlock( block.getLocation().clone().add( 0.5, 0, 0.5 ), data );

                        if( fall == null ) return;

                        fall.setGravity( false );
                        fall.setGlowing( true );
                        fall.setInvulnerable( true );
                        fall.setHurtEntities( true );

                        KillerQueen.game.chargeBomb( player, fall );
                    }
                }
                break;
        }
    }

}