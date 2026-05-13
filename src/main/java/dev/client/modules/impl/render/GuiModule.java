package dev.client.modules.impl.render;

import dev.client.WildClient;
import dev.client.modules.Category;
import dev.client.modules.IDisableable;
import dev.client.modules.IEnableable;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

@Environment(EnvType.CLIENT)
public class GuiModule extends Module implements IEnableable, IDisableable {
   public GuiModule() {
      super(new ModuleBranding("Gui", Category.RENDER, "Показывает меню чита"));
      this.setBind(344);
   }

   public void onEnable() {
      if (MinecraftClient.getInstance().currentScreen == null) {
         MinecraftClient.getInstance().setScreen(WildClient.INSTANCE.getGui());
      }

   }

   public void onDisable() {
      MinecraftClient.getInstance().setScreen((Screen)null);
   }
}

