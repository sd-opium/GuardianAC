package de.sdopium.guardianac.listener;

import de.sdopium.guardianac.GuardianAC;
import de.sdopium.guardianac.manager.PlayerDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MovementListener implements Listener {
    private final GuardianAC plugin;
    private final PlayerDataManager data = new PlayerDataManager();

    public MovementListener(GuardianAC plugin) { this.plugin = plugin; }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (!plugin.getConfig().getBoolean("general.enabled", true)) return;
        if (e.getTo() == null || e.getFrom().getX() == e.getTo().getX()
                && e.getFrom().getY() == e.getTo().getY()
                && e.getFrom().getZ() == e.getTo().getZ()) return;

        plugin.getChecks().evaluate(e.getPlayer(), data.get(e.getPlayer()));
    }
}
