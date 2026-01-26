package org.wojo.wojosHammers;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.Interaction;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import org.wojo.wojosHammers.Commands.HealPlayerCommand;
import org.wojo.wojosHammers.Commands.ServerRulesCommand;
import org.wojo.wojosHammers.Interactions.MiningHammerInteractionPrimary;
import org.wojo.wojosHammers.Interactions.MiningHammerInteractionSecondary;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

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

        this.getCommandRegistry().registerCommand(new ServerRulesCommand());
        this.getCommandRegistry().registerCommand(new HealPlayerCommand());
    }
}


// Setting up the custom animations
// Server/item/items/my_weapon.json:: Interactions:Primary -> Server/Item/Interactions/Weapons/my_weapon/Attacks/Primary/Weapon_XX_Primary.json
//  Weapon_XX_Primary.json:: is a charging interaction
//      can link to a chain file for when spamming click -> primary_chain.json (Does this var link to animation file?) -> Weapon_Sword_Primary_Swing_Left.json -> Swing_left_selector.json -> [Block_Break_adventure | Primary Damage]
//      or Heavy attack (Stamina condition first to check player has stamina to heavy atk)

// resources/Common/Characters/Animations/Items/Dual_Handed/Tool_Mining_Hammer/Attacks/

// Server/Item/Animations/item.json "Animations" { ... list of animations


//  (the item json)              Server/item/items/my_weapon.json ->
//  (The Button hndl json)       Server/Item/Interactions/Weapons/my_weapon/Attacks/Primary/Weapon_XX_Primary.json ->
//  (The Animation json)         Weapon_Sword_Primary_Swing_Left.json ->
//  (The Tgt Selector json)      Swing_left_selector.json ->
//  (The World Interact json)    Hit Block & Damage
