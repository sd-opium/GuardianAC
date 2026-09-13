package de.sdopium.guardianac.command;

import de.sdopium.guardianac.GuardianAC;
import de.sdopium.guardianac.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuardianCommand {
    private final GuardianAC plugin;
    public GuardianCommand(GuardianAC plugin) { this.plugin = plugin; }

    public void register() {
        plugin.getCommand("guardian").setExecutor((sender, cmd, label, args) -> execute(sender, args));
    }

    private boolean execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("guardianac.admin")) {
            sender.sendMessage(Messages.get(plugin, "messages.no-permission"));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§bGuardianAC §7- §f/guardian alerts|debug|reload|checks|violations");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "alerts" -> {
                boolean value = !plugin.getAlerts().isEnabled();
                plugin.getAlerts().setEnabled(value);
                sender.sendMessage("§aAlerts: " + value);
            }
            case "debug" -> sender.sendMessage("§7Checks: §f" + plugin.getChecks().names());
            case "reload" -> {
                plugin.reloadConfig();
                sender.sendMessage("§aGuardianAC neu geladen.");
            }
            case "checks" -> sender.sendMessage("§bAktive Checks: §f" + plugin.getChecks().names());
            case "violations" -> {
                if (args.length < 2) {
                    sender.sendMessage("§e/guardian violations <spieler>");
                    break;
                }
                Player p = Bukkit.getPlayerExact(args[1]);
                if (p == null) sender.sendMessage("§cSpieler nicht online.");
                else sender.sendMessage("§b" + p.getName() + " §7VL: §f" + plugin.getViolations().get(p));
            }
            default -> sender.sendMessage("§e/guardian alerts|debug|reload|checks|violations");
        }
        return true;
    }
}
