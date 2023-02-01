package Fullbright.modules.movement;

import Fullbright.modules.Mod;
import org.lwjgl.glfw.GLFW;

public class Sprint extends Mod {
    public Sprint() {
        super("Sprint", "make you always run like a super runner", Category.MOVEMENT);
        this.setKey(GLFW.GLFW_KEY_K);
    }

    @Override
    public void onTick() {
        if (mc.player.forwardSpeed > 0) mc.player.setSprinting(true);
        super.onTick();
    }

    @Override
    public void onDisable() {
        mc.player.setSprinting(false);
        super.onDisable();
    }
}
