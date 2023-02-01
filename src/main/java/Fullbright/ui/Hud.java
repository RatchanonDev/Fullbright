package Fullbright.ui;

import Fullbright.modules.Mod;
import Fullbright.modules.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

import java.util.Comparator;
import java.util.List;

public class Hud {

    private static MinecraftClient mc = MinecraftClient.getInstance();

    public static void render(MatrixStack matrices, float tickDelta) {
        mc.textRenderer.drawWithShadow(matrices, "Fullbright", 5, 5, 0xC3423F);
        renderArrayList(matrices);
    }

    public static void renderArrayList(MatrixStack matrices) {
        int index = 0;
        int sWidth = mc.getWindow().getScaledWidth();
        int shHeight = mc.getWindow().getScaledHeight();

        List<Mod> enabled = ModuleManager.INSTANCE.getEnabledModules();

        enabled.sort(Comparator.comparingInt(m -> (int) mc.textRenderer.getWidth(((Mod)m).getDisplayName())).reversed());

        for (Mod mod : ModuleManager.INSTANCE.getEnabledModules()) {
            mc.textRenderer.drawWithShadow(matrices, mod.getDisplayName(), (sWidth - 4) - mc.textRenderer.getWidth(mod.getDisplayName()), 5 + (index * mc.textRenderer.fontHeight), 0xFDE74C);
            index++;
        }
    }
}
