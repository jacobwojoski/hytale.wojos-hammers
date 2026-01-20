package org.wojo.wojosHammers.Interactions;

import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.protocol.InteractionType;
import com.hypixel.hytale.server.core.entity.InteractionContext;
import com.hypixel.hytale.server.core.modules.interaction.interaction.CooldownHandler;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.SimpleInstantInteraction;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class MiningHammerInteractionPrimary extends SimpleInstantInteraction {

    // CODEX: Needed to link interaction with item
    public static final BuilderCodec<MiningHammerInteractionPrimary> CODEC = BuilderCodec.builder(
            MiningHammerInteractionPrimary.class, MiningHammerInteractionPrimary::new, SimpleInstantInteraction.CODEC
    ).build();

    @Override
    protected void firstRun(@NonNullDecl InteractionType interactionType, @NonNullDecl InteractionContext interactionContext, @NonNullDecl CooldownHandler cooldownHandler) {
        // Custom Behavior when item is used
    }

}
