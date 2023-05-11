package dev.tehcn.infinity.screen;

import dev.tehcn.infinity.Infinity;
import net.minecraft.screen.ScreenHandlerType;

public class ModScreenHandlers {
    public static ScreenHandlerType<CondenserScreenHandler> CONDENSER_SCREEN_HANDLER;

    public static void registerAllScreenHandlers() {
        Infinity.LOGGER.debug("Registering Mod ScreenHandlers for " + Infinity.MOD_ID);
        CONDENSER_SCREEN_HANDLER = new ScreenHandlerType<>(CondenserScreenHandler::new);
    }
}
