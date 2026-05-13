package dev.client.modules.impl.render;

import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ItemPhysics extends Module {
   public ItemPhysics() {
      super(new ModuleBranding("ItemPhysics", Category.RENDER, "Добавляет предметам на земле физику"));
   }
}

