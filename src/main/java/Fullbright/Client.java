package Fullbright;

import Fullbright.modules.Mod;
import Fullbright.modules.ModuleManager;
import Fullbright.ui.screens.clickgui.ClickGUI;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import org.lwjgl.glfw.GLFW;

public class Client implements ModInitializer {

    public static final Client INSTANCE = new Client();
    public Logger logger = LogManager.getLogger(Client.class);

    private MinecraftClient mc = MinecraftClient.getInstance();

    @Override
    public void onInitialize() {
        logger.info("Fullbright is here to give you maximum blindness!");
    }

    public void onKeyPress(int key, int action) {
        (( F))
        if (mc.currentScreen != null) return;
        if (action == GLFW.GLFW_PRESS) {
            for (Mod module : ModuleManager.INSTANCE.getModules()) {
                if (key == module.getKey()) module.toggle();
            }

            if (key == GLFW.GLFW_KEY_INSERT) {
                if (!(mc.currentScreen instanceof ClickGUI)) {
                    mc.setScreen(ClickGUI.INSTANCE);
                } else {
                    mc.setScreen(null);
                }
            }
        }
    }

    public void onTick() {
        if (mc.player != null) {
            for (Mod module : ModuleManager.INSTANCE.getEnabledModules()) {
                module.onTick();
            }
        }
    }
}