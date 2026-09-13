package de.sdopium.guardianac.manager;

import de.sdopium.guardianac.GuardianAC;
import de.sdopium.guardianac.check.Check;
import de.sdopium.guardianac.model.CheckResult;
import de.sdopium.guardianac.model.PlayerData;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CheckManager {
    private final GuardianAC plugin;
    private final Map<String, Check> checks = new LinkedHashMap<>();

    public CheckManager(GuardianAC plugin) { this.plugin = plugin; }

    public void register(Check check) { checks.put(check.name().toLowerCase(), check); }

    public void evaluate(Player player, PlayerData data) {
        for (Check check : checks.values()) {
            CheckResult result = check.handle(player, data);
            if (result.failed()) {
                plugin.getViolations().flag(player, check.name(), result.weight(), result.detail());
            }
        }

        data.lastX = player.getLocation().getX();
        data.lastY = player.getLocation().getY();
        data.lastZ = player.getLocation().getZ();
        data.initialized = true;
    }

    public String names() {
        return checks.keySet().stream().collect(Collectors.joining(", "));
    }
}
