package org.wojo.wojosHammers.Interactions;

import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.*;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.math.vector.Vector3i;
import com.hypixel.hytale.protocol.*;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.asset.type.item.config.ItemTool;
import com.hypixel.hytale.server.core.entity.InteractionContext;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.modules.interaction.BlockHarvestUtils;
import com.hypixel.hytale.server.core.modules.interaction.interaction.CooldownHandler;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.SimpleInstantInteraction;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.client.BreakBlockInteraction;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class MiningHammerInteractionPrimary extends SimpleInstantInteraction {
    // TODO: For Testing only
    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    // CODEX: Needed to link interaction with item
    public static final BuilderCodec<MiningHammerInteractionPrimary> CODEC = BuilderCodec.builder(
            MiningHammerInteractionPrimary.class, MiningHammerInteractionPrimary::new, SimpleInstantInteraction.CODEC
    ).build();

    @Override
    protected void firstRun(@NonNullDecl InteractionType interactionType, @NonNullDecl InteractionContext interactionContext, @NonNullDecl CooldownHandler cooldownHandler) {
        // Custom Behavior when item is used
        CommandBuffer<EntityStore> commandBuffer = interactionContext.getCommandBuffer();
        if (commandBuffer == null) {
            interactionContext.getState().state = InteractionState.Failed;
            LOGGER.atInfo().log("CommandBuffer is null");
            return;
        }
        World world = commandBuffer.getExternalData().getWorld();
        Store<EntityStore> store = commandBuffer.getExternalData().getStore();
        Ref<EntityStore> ref = interactionContext.getEntity();
        BlockPosition target_block = interactionContext.getTargetBlock();

        // Debug Statement
        Player player = commandBuffer.getComponent(ref, Player.getComponentType());
        player.sendMessage(Message.raw("You have used the custom item Primary Interaction"));


        if (target_block != null) {
            com.hypixel.hytale.math.vector.Vector3i target_block_vec = new Vector3i(target_block.x, target_block.y, target_block.z);
            ItemStack item_stack = interactionContext.getHeldItem();
            ItemTool item_tool = null; // How do?
            float damage_scale = (float)0.5; // No Idea what this does
            int set_block_settings = 0; // No Idea what this does
            Ref<ChunkStore> chunk_store_ref = world.getChunkStore().getChunkSectionReference(target_block.x, target_block.y, target_block.z); // How can I get chunk reference from block?
            CommandBuffer<EntityStore> command_buffer_entity_store = commandBuffer;
            ComponentAccessor<ChunkStore> component_accessor_chunk_store = command_buffer_entity_store.getComponent(ref, );

            if (chunk_store_ref != null) {
                BlockHarvestUtils.performBlockDamage(
                        target_block_vec,
                        item_stack,
                        item_tool,
                        damage_scale,
                        set_block_settings,
                        chunk_store_ref,
                        command_buffer_entity_store,
                        component_accessor_chunk_store
                );
            }else{
                player.sendMessage(Message.raw("Chunk Store Ref == null"));
            }
        }else{
            player.sendMessage(Message.raw("No Block hit"));
        }
    }



}
