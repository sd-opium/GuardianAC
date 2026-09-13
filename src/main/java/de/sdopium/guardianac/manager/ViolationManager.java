package de.sdopium.guardianac.manager;

import de.sdopium.guardianac.GuardianAC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ViolationManager {
    private final GuardianAC plugin;
    private final Map<UUID, Double> totals = new HashMap<>();
    private final Map<UUID, Long> lastFlag = new HashMap<>();

    public ViolationManager(GuardianAC plugin) { this.plugin = plugin; }

    public void flag(Player player, String check, double weight, String detail) {
        UUID id = player.getUniqueId();
        decay(id);
        double total = totals.getOrDefault(id, 0.0) + weight;
        totals.put(id, total);
        lastFlag.put(id, System.currentTimeMillis());

        plugin.getAlerts().send(player, check, total, detail);

        double threshold = plugin.getConfig().getDouble("general.max-violations-before-punishment", 12);
        if (total >= threshold) {
            plugin.getPunishments().punish(player, check, total);
            totals.put(id, 0.0);
        }
    }

    public double get(Player player) {
        decay(player.getUniqueId());
        return totals.getOrDefault(player.getUniqueId(), 0.0);
    }

    private void decay(UUID id) {
        Long time = lastFlag.get(id);
        if (time == null) return;
        long seconds = (System.currentTimeMillis() - time) / 1000;
        long decayEvery = plugin.getConfig().getLong("general.decay-seconds", 20);
        if (seconds >= decayEvery) totals.put(id, Math.max(0, totals.getOrDefault(id, 0.0) - 1.0));
    }
}
