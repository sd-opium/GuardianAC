package de.sdopium.guardianac.manager;

import de.sdopium.guardianac.GuardianAC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AlertManager {
    private final GuardianAC plugin;
    private boolean enabled = true;

    public AlertManager(GuardianAC plugin) { this.plugin = plugin; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public boolean isEnabled() { return enabled; }

    public void send(Player suspect, String check, double vl, String detail) {
        if (!enabled) return;
        String msg = ChatColor.translateAlternateColorCodes('&',
                "&8[&bGuardianAC&8] &f" + suspect.getName() +
                " &7failed &c" + check + " &7(VL &f" + String.format("%.1f", vl) + "&7) &8" + detail);

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("guardianac.alerts")) p.sendMessage(msg);
        }
        plugin.getLogger().info(ChatColor.stripColor(msg));
    }
}
