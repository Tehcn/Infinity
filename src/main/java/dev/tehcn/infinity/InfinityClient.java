package dev.tehcn.infinity;

import dev.tehcn.infinity.screen.CondenserScreen;
import dev.tehcn.infinity.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class InfinityClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.CONDENSER_SCREEN_HANDLER, CondenserScreen::new);
    }
}
