package de.sdopium.guardianac.checks;

import de.sdopium.guardianac.GuardianAC;
import de.sdopium.guardianac.check.Check;
import de.sdopium.guardianac.model.CheckResult;
import de.sdopium.guardianac.model.PlayerData;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class FlyCheck implements Check {
    private final GuardianAC plugin;
    public FlyCheck(GuardianAC plugin) { this.plugin = plugin; }

    @Override public String name() { return "Fly"; }

    @Override
    public CheckResult handle(Player player, PlayerData data) {
        if (!plugin.getConfig().getBoolean("checks.fly.enabled", true)) return CheckResult.pass();
        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR || player.isFlying()) {
            data.airTicks = 0;
            return CheckResult.pass();
        }

        if (!player.isOnGround() && !player.isGliding() && !player.isInsideVehicle() && !player.getAllowFlight()) {
            data.airTicks++;
        } else {
            data.airTicks = 0;
        }

        int threshold = plugin.getConfig().getInt("checks.fly.max-air-ticks", 12);
        return data.airTicks > threshold
                ? CheckResult.fail(1.5, "sustained airborne movement")
                : CheckResult.pass();
    }
}
