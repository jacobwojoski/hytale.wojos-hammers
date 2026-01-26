package org.wojo.wojosHammers.Commands;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

// Command that can be run that lists the server rules in chat to the player
public class ServerRulesCommand extends AbstractAsyncCommand {

    // Constructor
    public ServerRulesCommand() {
        // Pass any needed info to AbstractAsyncCommand
        // super(<The command>, <Command description>)
        super("rules", "Lists the servers rules");
    }

    // Run the command
    // arg1: conetext - info about who ran the command. Server console?, Some player?
    @Override
    protected CompletableFuture<Void> executeAsync(@Nonnull CommandContext context) {
        context.sendMessage(Message.raw("The only rule is there are no rules."));
        return CompletableFuture.completedFuture(null);
    }
}