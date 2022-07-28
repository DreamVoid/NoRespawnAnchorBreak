package org.mineblock.creeperfirework;

import org.bukkit.*;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
    public void onCreeperExplode(EntityExplodeEvent e){
        if(e.getEntityType().equals(EntityType.CREEPER)){
            e.setCancelled(true);
            Creeper c = (Creeper)e.getEntity();
            Location loc = c.getLocation();
            loc = loc.add(new Vector(0, 1, 0));
            Firework fw = (Firework)c.getWorld().spawnEntity(loc, EntityType.FIREWORK);
            FireworkMeta fwm = fw.getFireworkMeta();
            fwm.setPower(0);
            fwm.addEffect(FireworkEffect.builder().withColor(Color.RED, Color.YELLOW).flicker(true).build());
            fw.setFireworkMeta(fwm);
            loc.getWorld().playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, SoundCategory.HOSTILE, 2, 1);
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, fw::detonate, 1);
        }
    }
}
