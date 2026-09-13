package de.sdopium.guardianac.checks;

import de.sdopium.guardianac.GuardianAC;
import de.sdopium.guardianac.check.Check;
import de.sdopium.guardianac.model.CheckResult;
import de.sdopium.guardianac.model.PlayerData;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class SpeedCheck implements Check {
    private final GuardianAC plugin;
    public SpeedCheck(GuardianAC plugin) { this.plugin = plugin; }

    @Override public String name() { return "Speed"; }

    @Override
    public CheckResult handle(Player player, PlayerData data) {
        if (!plugin.getConfig().getBoolean("checks.speed.enabled", true)) return CheckResult.pass();
        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR ||
                player.isFlying() || player.isInsideVehicle()) return CheckResult.pass();

        Vector v = player.getVelocity();
        double horizontal = Math.hypot(v.getX(), v.getZ());
        double allowed = plugin.getConfig().getDouble("checks.speed.max-horizontal-speed", 0.75);

        if (player.hasPotionEffect(PotionEffectType.SPEED)) {
            allowed += 0.12 * (player.getPotionEffect(PotionEffectType.SPEED).getAmplifier() + 1);
        }

        return horizontal > allowed
                ? CheckResult.fail(1.0, String.format("horizontal velocity %.2f > %.2f", horizontal, allowed))
                : CheckResult.pass();
    }
}
