package de.sdopium.guardianac.checks;

import de.sdopium.guardianac.GuardianAC;
import de.sdopium.guardianac.check.Check;
import de.sdopium.guardianac.model.CheckResult;
import de.sdopium.guardianac.model.PlayerData;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class MovementCheck implements Check {
    private final GuardianAC plugin;
    public MovementCheck(GuardianAC plugin) { this.plugin = plugin; }

    @Override public String name() { return "Movement"; }

    @Override
    public CheckResult handle(Player player, PlayerData data) {
        if (!plugin.getConfig().getBoolean("checks.movement.enabled", true) || !data.initialized) {
            return CheckResult.pass();
        }
        if (player.getGameMode() == GameMode.SPECTATOR || player.isInsideVehicle() || player.isGliding()) {
            return CheckResult.pass();
        }

        double dx = player.getLocation().getX() - data.lastX;
        double dz = player.getLocation().getZ() - data.lastZ;
        double distance = Math.hypot(dx, dz);
        double max = plugin.getConfig().getDouble("checks.movement.max-distance-per-tick", 1.25);

        return distance > max
                ? CheckResult.fail(1.3, String.format("moved %.2f blocks between samples", distance))
                : CheckResult.pass();
    }
}
