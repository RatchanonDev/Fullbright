package Fullbright.ui.screens.clickgui.setting;

import Fullbright.modules.settings.Setting;
import Fullbright.ui.screens.clickgui.ModuleButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class Component {

    public Setting setting;
    public ModuleButton parent;
    public int offset;

    protected MinecraftClient mc = MinecraftClient.getInstance();

    public Component(Setting setting, ModuleButton parent, int offset) {
        this.setting = setting;
        this.parent = parent;
        this.offset = offset;
    }

    public void render(MatrixStack matricies, int mouseX, int mouseY, float delta) {

    }

    public void mouseClicked(double mouseX, double mouseY, int button) {

    }

    public void mouseRelease(double mouseX, double mouseY, int button) {

    }

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX > parent.parent.x && mouseX < parent.parent.x + parent.parent.width && mouseY > parent.parent.y + parent.offset + offset && mouseY < parent.parent.y + parent.offset + offset + parent.parent.height;
    }
}
