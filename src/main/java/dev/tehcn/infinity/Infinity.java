package dev.tehcn.infinity;

import dev.tehcn.infinity.abilities.Abilities;
import dev.tehcn.infinity.armor.ArmorSets;
import dev.tehcn.infinity.armor.InfinityArmorSet;
import dev.tehcn.infinity.block.ModBlocks;
import dev.tehcn.infinity.block.entity.ModBlockEntities;
import dev.tehcn.infinity.item.ModItems;
import dev.tehcn.infinity.recipe.ModRecipes;
import dev.tehcn.infinity.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.kyrptonaught.customportalapi.event.PortalIgniteEvent;
import net.kyrptonaught.customportalapi.portal.PortalIgnitionSource;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Infinity implements ModInitializer {
    public static final String MOD_ID = "infinity";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModBlockEntities.registerBlockEntities();
        ModScreenHandlers.registerAllScreenHandlers();
        ModRecipes.registerRecipes();
        Abilities.init();
        ArmorSets.register();
        CustomPortalBuilder.beginPortal()
            .frameBlock(ModBlocks.INFINITY_BLOCK)
            .lightWithFluid(Fluids.LAVA)
            .destDimID(new Identifier("infinity:darkness"))
            .registerIgniteEvent((PlayerEntity player, World world, BlockPos portalPos, BlockPos framePos, PortalIgnitionSource portalIgnitionSource) -> {
                player.sendMessage(Text.translatable("message.infinity.darkness_awakens").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x0f0f0f))));
            })
            .tintColor(45, 65, 101)
            .registerPortal();
    }
}
