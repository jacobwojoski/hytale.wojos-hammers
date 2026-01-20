package org.wojo.wojosHammers;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.Interaction;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import org.wojo.wojosHammers.Interactions.MiningHammerInteractionPrimary;
import org.wojo.wojosHammers.Interactions.MiningHammerInteractionSecondary;

import javax.annotation.Nonnull;

/**
 * This class serves as the entrypoint for your plugin. Use the setup method to register into game registries or add
 * event listeners.
 */
public class WojosMiningHammerPlugin extends JavaPlugin {

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    public WojosMiningHammerPlugin(@Nonnull JavaPluginInit init) {
        super(init);
        LOGGER.atInfo().log("Hello from " + this.getName() + " version " + this.getManifest().getVersion().toString());
    }

    @Override
    protected void setup() {
        LOGGER.atInfo().log("Setting up plugin " + this.getName());
        this.getCommandRegistry().registerCommand(new ExampleCommand(this.getName(), this.getManifest().getVersion().toString()));

        // Register all custom interactions
        this.getCodecRegistry(Interaction.CODEC).register("Mining_Hammer_Interaction_Primary_ID", MiningHammerInteractionPrimary.class, MiningHammerInteractionPrimary.CODEC);
        this.getCodecRegistry(Interaction.CODEC).register("Mining_Hammer_Interaction_Secondary_ID", MiningHammerInteractionSecondary.class, MiningHammerInteractionSecondary.CODEC);
    }
}