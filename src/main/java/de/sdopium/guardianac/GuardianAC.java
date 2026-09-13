package de.sdopium.guardianac;

import de.sdopium.guardianac.command.GuardianCommand;
import de.sdopium.guardianac.checks.FastBreakCheck;
import de.sdopium.guardianac.checks.FastPlaceCheck;
import de.sdopium.guardianac.checks.FlyCheck;
import de.sdopium.guardianac.checks.MovementCheck;
import de.sdopium.guardianac.checks.NoFallCheck;
import de.sdopium.guardianac.checks.SpeedCheck;
import de.sdopium.guardianac.listener.CombatListener;
import de.sdopium.guardianac.listener.MovementListener;
import de.sdopium.guardianac.listener.PlayerActionListener;
import de.sdopium.guardianac.manager.AlertManager;
import de.sdopium.guardianac.manager.CheckManager;
import de.sdopium.guardianac.manager.PunishmentManager;
import de.sdopium.guardianac.manager.ViolationManager;
import de.sdopium.guardianac.util.Messages;
import org.bukkit.plugin.java.JavaPlugin;

public final class GuardianAC extends JavaPlugin {

    private ViolationManager violations;
    private AlertManager alerts;
    private CheckManager checks;
    private PunishmentManager punishments;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        violations = new ViolationManager(this);
        alerts = new AlertManager(this);
        punishments = new PunishmentManager(this);
        checks = new CheckManager(this);

        checks.register(new FlyCheck(this));
        checks.register(new SpeedCheck(this));
        checks.register(new NoFallCheck(this));
        checks.register(new MovementCheck(this));
        checks.register(new FastBreakCheck(this));
        checks.register(new FastPlaceCheck(this));

        getServer().getPluginManager().registerEvents(new MovementListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerActionListener(this), this);
        getServer().getPluginManager().registerEvents(new CombatListener(this), this);

        new GuardianCommand(this).register();

        getLogger().info("GuardianAC gestartet. Checks: " + checks.names());
    }

    @Override
    public void onDisable() {
        getLogger().info("GuardianAC beendet.");
    }

    public ViolationManager getViolations() { return violations; }
    public AlertManager getAlerts() { return alerts; }
    public CheckManager getChecks() { return checks; }
    public PunishmentManager getPunishments() { return punishments; }
    public String msg(String key) { return Messages.get(this, key); }
}
