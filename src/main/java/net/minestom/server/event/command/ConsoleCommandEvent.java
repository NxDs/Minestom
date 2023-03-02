package net.minestom.server.event.command;

import net.minestom.server.command.ConsoleSender;
import org.jetbrains.annotations.NotNull;

/**
 * Called every time the console sends a command.
 */
public class ConsoleCommandEvent extends CommandEvent {

    private final ConsoleSender console;

    public ConsoleCommandEvent(@NotNull ConsoleSender console, @NotNull String command) {
        super(console, command);
        this.console = console;
    }

    public @NotNull ConsoleSender getConsole() {
        return console;
    }
}
