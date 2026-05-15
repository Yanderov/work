package dev.client.integration;

import dev.client.WildClient;
import dev.client.modules.Category;
import dev.client.modules.IEnableable;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import org.lwjgl.glfw.GLFW;

/**
 * Модуль для открытия ClickGUI из Yanderov
 */
public class YanderovClickGuiModule extends Module implements IEnableable {
    
    public YanderovClickGuiModule() {
        super(new ModuleBranding("YanderovGUI", Category.RENDER, "Открывает ClickGUI из Yanderov"));
        this.setBind(GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    @Override
    public void onEnable() {
        if (WildClient.INSTANCE != null && WildClient.INSTANCE.getYanderovAdapter() != null) {
            WildClient.INSTANCE.getYanderovAdapter().openClickGui();
            System.out.println("[YanderovClickGuiModule] Opening Yanderov ClickGUI");
        } else {
            System.out.println("[YanderovClickGuiModule] Yanderov adapter not available");
        }
    }
}
