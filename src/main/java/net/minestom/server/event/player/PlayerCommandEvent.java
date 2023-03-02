package net.minestom.server.event.player;

import net.minestom.server.entity.Player;
import net.minestom.server.event.command.CommandEvent;
import net.minestom.server.event.trait.PlayerInstanceEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Called every time a player send a message starting by '/'.
 */
public class PlayerCommandEvent extends CommandEvent implements PlayerInstanceEvent {

    private final Player player;

    public PlayerCommandEvent(@NotNull Player player, @NotNull String command) {
        super(player, command);
        this.player = player;
    }

    @Override
    public @NotNull Player getPlayer() {
        return player;
    }
}
