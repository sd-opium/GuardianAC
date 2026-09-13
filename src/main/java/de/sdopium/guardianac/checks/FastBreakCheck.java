package de.sdopium.guardianac.checks;

import de.sdopium.guardianac.GuardianAC;
import de.sdopium.guardianac.check.Check;
import de.sdopium.guardianac.model.CheckResult;
import de.sdopium.guardianac.model.PlayerData;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class FastBreakCheck implements Check {
    private final GuardianAC plugin;
    public FastBreakCheck(GuardianAC plugin) { this.plugin = plugin; }

    @Override public String name() { return "FastBreak"; }

    @Override
    public CheckResult handle(Player player, PlayerData data) {
        if (!plugin.getConfig().getBoolean("checks.fastbreak.enabled", true)) return CheckResult.pass();
        if (player.getGameMode() != GameMode.SURVIVAL && player.getGameMode() != GameMode.ADVENTURE) return CheckResult.pass();

        long elapsed = System.currentTimeMillis() - data.lastBreakMillis;
        long min = plugin.getConfig().getLong("checks.fastbreak.min-break-time-ms", 90);

        return data.lastBreakMillis != 0 && elapsed < min
                ? CheckResult.fail(1.0, "block actions are unusually frequent")
                : CheckResult.pass();
    }
}
