# GuardianAC

GuardianAC ist ein modularer AntiCheat-Prototyp für Minecraft Paper.

## Checks
- Fly
- Speed
- NoFall
- Impossible movement
- Fast break
- Fast place

## Funktionen
- Violation-System mit Levels
- Warnungen für Staff
- konfigurierbare Schwellenwerte
- Punishment-Engine
- Debug- und Reload-Befehle
- getrennte Checks, Manager und Listener
- YAML-Konfiguration
- Logs im Server-Log

## Befehle
- `/guardian alerts`
- `/guardian debug`
- `/guardian reload`
- `/guardian checks`
- `/guardian violations <spieler>`

## Permissions
- `guardianac.admin`
- `guardianac.alerts`

## Bauen
Java 21 und Maven:
```bash
mvn clean package
```

Die JAR liegt anschließend in `target/`.
