package Fullbright.modules.movement;

import Fullbright.modules.Mod;
import Fullbright.modules.settings.BooleanSetting;
import Fullbright.modules.settings.ModeSetting;
import Fullbright.modules.settings.NumberSetting;
import org.lwjgl.glfw.GLFW;

public class Flight extends Mod {

    public NumberSetting speed = new NumberSetting("Speed", 1, 10, 1, 0.1);
    public BooleanSetting AntiKick = new BooleanSetting("AntiKick", false);
    public ModeSetting mode = new ModeSetting("Mode", "Packet", "Creative", "Jetpack");
    public Flight() {
        super("Flight", "fly you like a creative?", Category.MOVEMENT);
        this.setKey(GLFW.GLFW_KEY_Z);
        addSettings(speed, AntiKick, mode);
    }

    @Override
    public void onTick() {
        assert mc.player != null;
        if (!mc.player.isSpectator()) {
            mc.player.getAbilities().flying = true;
            mc.player.getAbilities().setFlySpeed(speed.getValueFloat());
            if (mc.player.getAbilities().creativeMode) return;
            mc.player.getAbilities().allowFlying = true;
            mc.player.getAbilities().setFlySpeed(speed.getValueFloat());
        }
        super.onTick();
    }

    @Override
    public void onDisable() {
        assert mc.player != null;
        if (!mc.player.isSpectator()) {
            mc.player.getAbilities().flying = false;
            if (mc.player.getAbilities().creativeMode) return;
            mc.player.getAbilities().allowFlying = false;
        }
        super.onDisable();
    }
}
