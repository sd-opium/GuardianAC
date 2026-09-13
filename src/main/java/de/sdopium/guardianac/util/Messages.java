package de.sdopium.guardianac.util;

import de.sdopium.guardianac.GuardianAC;
import org.bukkit.ChatColor;

public final class Messages {
    private Messages() {}

    public static String get(GuardianAC plugin, String key) {
        String value = plugin.getConfig().getString(key, key);
        return ChatColor.translateAlternateColorCodes('&', value);
    }
}
