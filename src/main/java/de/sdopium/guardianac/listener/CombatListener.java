package de.sdopium.guardianac.listener;

import de.sdopium.guardianac.GuardianAC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CombatListener implements Listener {
    private final GuardianAC plugin;
    public CombatListener(GuardianAC plugin) { this.plugin = plugin; }

    @EventHandler
    public void onCombat(EntityDamageByEntityEvent e) {
        // Reserved extension point for future combat checks such as reach/aim analysis.
        if (e.getDamager() instanceof org.bukkit.entity.Player player) {
            if (player.hasPermission("guardianac.admin") && plugin.getAlerts().isEnabled()) {
                // No flag is generated here; this listener intentionally avoids false positives.
            }
        }
    }
}
