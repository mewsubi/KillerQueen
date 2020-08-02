package io.mewsub.killerqueen;

import io.mewsub.killerqueen.commands.KQKit;

import io.mewsub.killerqueen.enchantments.KQEnchant;

import io.mewsub.killerqueen.listeners.EntityDamageByEntity;
import io.mewsub.killerqueen.listeners.EntityPickupItem;
import io.mewsub.killerqueen.listeners.EntityShootBow;
import io.mewsub.killerqueen.listeners.ProjectileLaunch;
import io.mewsub.killerqueen.listeners.PlayerDropItem;
import io.mewsub.killerqueen.listeners.PlayerInteract;
import io.mewsub.killerqueen.listeners.PlayerInteractEntity;
import io.mewsub.killerqueen.listeners.PlayerSwapHandItems;

import java.lang.reflect.Field;

import org.bukkit.NamespacedKey;

import org.bukkit.enchantments.Enchantment;

import org.bukkit.scheduler.BukkitRunnable;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Server;

public class KillerQueen extends JavaPlugin {

    public static Plugin plugin;
    public static Server server;

    public static KQGame game;

    @Override
    public void onEnable() {
        KillerQueen.plugin = ( Plugin ) this;
        KillerQueen.server = this.getServer();
        KillerQueen.server.getPluginManager().registerEvents( new EntityDamageByEntity(), this );
        KillerQueen.server.getPluginManager().registerEvents( new EntityPickupItem(), this );
        KillerQueen.server.getPluginManager().registerEvents( new EntityShootBow(), this );
        KillerQueen.server.getPluginManager().registerEvents( new ProjectileLaunch(), this );
        KillerQueen.server.getPluginManager().registerEvents( new PlayerDropItem(), this );
        KillerQueen.server.getPluginManager().registerEvents( new PlayerInteract(), this );
        KillerQueen.server.getPluginManager().registerEvents( new PlayerInteractEntity(), this );
        KillerQueen.server.getPluginManager().registerEvents( new PlayerSwapHandItems(), this );

        this.getCommand( "killerqueen" ).setExecutor( new KQKit() );

        NamespacedKey key = new NamespacedKey( this, "KQ_ENCHANT" );

        try {
            Field f = Enchantment.class.getDeclaredField( "acceptingNew" );
            f.setAccessible( true );
            f.set( null, true );
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        try {
            KQEnchant kqEnchant = new KQEnchant( key );
            Enchantment.registerEnchantment( kqEnchant );
        } catch ( IllegalArgumentException e ){
        } catch( Exception e ){
            e.printStackTrace();
        }

        KillerQueen.game = new KQGame();
        KillerQueen.game.runTaskTimer( KillerQueen.plugin, 0L, 1L );
    }

    @Override
    public void onDisable() {

    }

}
