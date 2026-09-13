package de.sdopium.guardianac.checks;

import de.sdopium.guardianac.GuardianAC;
import de.sdopium.guardianac.check.Check;
import de.sdopium.guardianac.model.CheckResult;
import de.sdopium.guardianac.model.PlayerData;
import org.bukkit.entity.Player;

public class NoFallCheck implements Check {
    private final GuardianAC plugin;
    public NoFallCheck(GuardianAC plugin) { this.plugin = plugin; }

    @Override public String name() { return "NoFall"; }

    @Override
    public CheckResult handle(Player player, PlayerData data) {
        if (!plugin.getConfig().getBoolean("checks.nofall.enabled", true)) return CheckResult.pass();
        if (player.isDead() || player.isInsideVehicle() || player.getFallDistance() <= 3.0f) return CheckResult.pass();

        if (player.isOnGround() && player.getFallDistance() > 4.5f) {
            return CheckResult.fail(1.2, String.format("landing after %.1f blocks", player.getFallDistance()));
        }
        return CheckResult.pass();
    }
}
