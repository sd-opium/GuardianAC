package de.sdopium.guardianac.listener;

import de.sdopium.guardianac.GuardianAC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.entity.Player;

public class PlayerActionListener implements Listener {
    private final GuardianAC plugin;
    public PlayerActionListener(GuardianAC plugin) { this.plugin = plugin; }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        // Timestamp only; analysis happens on subsequent movement/action samples.
        p.setMetadata("guardian_last_break", new org.bukkit.metadata.FixedMetadataValue(plugin, System.currentTimeMillis()));
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        p.setMetadata("guardian_last_place", new org.bukkit.metadata.FixedMetadataValue(plugin, System.currentTimeMillis()));
    }
}
