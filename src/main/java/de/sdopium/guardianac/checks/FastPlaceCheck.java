package de.sdopium.guardianac.checks;

import de.sdopium.guardianac.GuardianAC;
import de.sdopium.guardianac.check.Check;
import de.sdopium.guardianac.model.CheckResult;
import de.sdopium.guardianac.model.PlayerData;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class FastPlaceCheck implements Check {
    private final GuardianAC plugin;
    public FastPlaceCheck(GuardianAC plugin) { this.plugin = plugin; }

    @Override public String name() { return "FastPlace"; }

    @Override
    public CheckResult handle(Player player, PlayerData data) {
        if (!plugin.getConfig().getBoolean("checks.fastplace.enabled", true)) return CheckResult.pass();
        if (player.getGameMode() != GameMode.SURVIVAL && player.getGameMode() != GameMode.ADVENTURE) return CheckResult.pass();

        long elapsed = System.currentTimeMillis() - data.lastPlaceMillis;
        long min = plugin.getConfig().getLong("checks.fastplace.min-place-interval-ms", 60);

        return data.lastPlaceMillis != 0 && elapsed < min
                ? CheckResult.fail(0.8, "place actions are unusually frequent")
                : CheckResult.pass();
    }
}
