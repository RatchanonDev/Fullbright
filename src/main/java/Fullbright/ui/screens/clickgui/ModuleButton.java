package Fullbright.ui.screens.clickgui;

import Fullbright.modules.Mod;
import Fullbright.modules.settings.BooleanSetting;
import Fullbright.modules.settings.ModeSetting;
import Fullbright.modules.settings.NumberSetting;
import Fullbright.modules.settings.Setting;
import Fullbright.ui.screens.clickgui.setting.CheckBox;
import Fullbright.ui.screens.clickgui.setting.Component;
import Fullbright.ui.screens.clickgui.setting.ModeBox;
import Fullbright.ui.screens.clickgui.setting.Slider;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleButton {

    public Mod module;
    public Frame parent;
    public int offset;
    public List<Component> components;
    public boolean extended;

    public ModuleButton(Mod module, Frame parent, int offset) {
        this.module = module;
        this.parent = parent;
        this.offset = offset;
        this.components = new ArrayList<>();
        this.extended = false;

        int setOffset = parent.height;
        for (Setting setting : module.getSettings()) {
            if (setting instanceof BooleanSetting) {
                components.add(new CheckBox(setting, this, setOffset));
            } else if (setting instanceof ModeSetting) {
                components.add(new ModeBox(setting, this, setOffset));
            } else if (setting instanceof NumberSetting) {
                components.add(new Slider(setting, this, setOffset));
            }
            setOffset += parent.height;
        }
    }

    public void render(MatrixStack matricies, int mouseX, int mouseY, float delta) {
        DrawableHelper.fill(matricies, parent.x, parent.y + offset, parent.x + parent.width, parent.y + offset + parent.height, new Color(0, 0, 0, 60).getRGB());

        if (isHovered(mouseX, mouseY)) DrawableHelper.fill(matricies, parent.x, parent.y + offset, parent.x + parent.width, parent.y + offset + parent.height, new Color(0, 0, 0, 60).getRGB());
        int textOffset = ((parent.height / 2) - parent.mc.textRenderer.fontHeight / 2);
        parent.mc.textRenderer.drawWithShadow(matricies, (module.isEnabled() ? "+" : "") + " " + module.getName(), parent.x + textOffset, parent.y + offset + textOffset, module.isEnabled() ? 0xFDE74C : -1);

        if (extended) {
            for (Component component : components) {
                component.render(matricies, mouseX, mouseY, delta);
            }
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY)) {
            if (button == 0) {
                module.toggle();
            } else if (button == 1) {
                extended = !extended;
                parent.updateButtons();
            }
        }

        for (Component component : components) {
            component.mouseClicked(mouseX, mouseY, button);
        }

    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        for (Component component : components) {
            component.mouseRelease(mouseX, mouseY, button);
        }
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX > parent.x && mouseX < parent.x + parent.width && mouseY > parent.y + offset && mouseY < parent.y + offset + parent.height;
    }
}
