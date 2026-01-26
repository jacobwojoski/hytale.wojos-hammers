package org.wojo.wojosHammers.Commands;

import com.google.protobuf.FloatValue;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.DefaultArg;
import com.hypixel.hytale.server.core.command.system.arguments.system.FlagArg;
import com.hypixel.hytale.server.core.command.system.arguments.system.OptionalArg;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractTargetPlayerCommand;
import com.hypixel.hytale.server.core.modules.entitystats.EntityStatMap;
import com.hypixel.hytale.server.core.modules.entitystats.EntityStatValue;
import com.hypixel.hytale.server.core.modules.entitystats.asset.DefaultEntityStatTypes;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

// Example usage: /healplayer --health 50 --message "Feels Good" --debug
public class HealPlayerCommand extends AbstractTargetPlayerCommand {
    private final DefaultArg<Float> healthArg;
    private final OptionalArg<String> messageArg;
    private final FlagArg debugArg;

    public HealPlayerCommand() {
        super("healplayer", "Healing a player for an <input> ammount of HP (default: 100)");

        // Abstract targetPlayerCommand passes the player that ran the command by default you can use `--player <value>` to specify someone else

        // args <Command String> <Description> <Default Value> <Desc of default value>
        this.healthArg = this.withDefaultArg("health", "Amount to heal player", ArgTypes.FLOAT, (float)100, "Desc of Default: 100");

        // Or you could do the following making the health value required instead of with a default. You would need to change the decleration to RequiredArg<Float>
        // this.healthArg = this.withRequiredArg("player", "Player Ref", ArgTypes.PLAYER_REF);

        this.messageArg = this.withOptionalArg("message", "Message to print while healing", ArgTypes.STRING);

        this.debugArg = this.withFlagArg("debug", "Add debug logs");
    }

    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NullableDecl Ref<EntityStore> ref, @NonNullDecl Ref<EntityStore> ref1, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world, @NonNullDecl Store<EntityStore> store) {

        if (this.debugArg.get(commandContext) == true) {
            commandContext.sendMessage(Message.raw("We are debugging"));
        }

        EntityStatMap stats = store.getComponent(ref, EntityStatMap.getComponentType());
        int healthIdx = DefaultEntityStatTypes.getHealth();
        EntityStatValue health = stats.get(healthIdx);

        float missing = health.getMax() - health.get();

        if (this.debugArg.get(commandContext) == true) {
            commandContext.sendMessage(Message.raw("Missing:  " + missing + " health"));
            commandContext.sendMessage(Message.raw("Adding:  " + healthArg.get(commandContext) + " health to "));
            commandContext.sendMessage(Message.raw(messageArg.get(commandContext)));
            commandContext.sendMessage(Message.raw("Input Value: " + healthArg.get(commandContext) + " Default"));
            commandContext.sendMessage(Message.raw("Default Health Value: "+healthArg.getDefaultValue()));
        }

        stats.addStatValue(healthIdx, healthArg.get(commandContext));
    }
}
