package me.dreamvoid.norespawnanchorbreak;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onCreeperExplode(BlockExplodeEvent e){
        if(e.getBlock().getLocation().getWorld().getEnvironment().equals(World.Environment.NORMAL)){
            e.setCancelled(true);
            e.getBlock().getLocation().getWorld().createExplosion(e.getBlock().getLocation(), 5F, false, false);
        }
    }
}
