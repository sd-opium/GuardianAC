package de.sdopium.guardianac.manager;

import de.sdopium.guardianac.GuardianAC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PunishmentManager {
    private final GuardianAC plugin;
    public PunishmentManager(GuardianAC plugin) { this.plugin = plugin; }

    public void punish(Player player, String check, double vl) {
        if (!plugin.getConfig().getBoolean("punishments.enabled", false)) return;

        String command = plugin.getConfig().getString("punishments.command", "kick %player% GuardianAC violation")
                .replace("%player%", player.getName())
                .replace("%check%", check)
                .replace("%vl%", String.format("%.1f", vl));

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }
}
