package dev.client.util.aura.esp;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

@Environment(EnvType.CLIENT)
public abstract class EspMode {
   protected String colorMode = "White";

   public abstract void render(Entity entity, MatrixStack matrices);

   public void setColorMode(String colorMode) {
      this.colorMode = colorMode;
   }
}
