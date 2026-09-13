package de.sdopium.guardianac.check;

import de.sdopium.guardianac.model.CheckResult;
import de.sdopium.guardianac.model.PlayerData;
import org.bukkit.entity.Player;

public interface Check {
    String name();
    CheckResult handle(Player player, PlayerData data);
}
