package net.minestom.server.event.command;

import net.minestom.server.command.CommandSender;
import net.minestom.server.event.trait.CancellableEvent;
import org.jetbrains.annotations.NotNull;

public abstract class CommandEvent implements CancellableEvent {

    private final CommandSender sender;
    private String command;

    private boolean cancelled;

    public CommandEvent(@NotNull CommandSender sender, @NotNull String command) {
        this.sender = sender;
        this.command = command;
    }

    /**
     * Gets the command used (command name + arguments).
     *
     * @return the command that the console wants to execute
     */
    @NotNull
    public String getCommand() {
        return command;
    }

    /**
     * Changes the command to execute.
     *
     * @param command the new command
     */
    public void setCommand(@NotNull String command) {
        this.command = command;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public @NotNull CommandSender getSender() {
        return sender;
    }
}
