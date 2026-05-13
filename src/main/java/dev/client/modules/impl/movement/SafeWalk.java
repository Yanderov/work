package dev.client.modules.impl.movement;

import dev.client.event.classes.TickEvent;
import dev.client.event.interfaces.ITickable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.math.BlockPos;

@Environment(EnvType.CLIENT)
public class SafeWalk extends Module implements ITickable, IUtil {
   public SafeWalk() {
      super(new ModuleBranding("SafeWalk", Category.MOVEMENT, "Приседает на краю блоков, спасая от падения"));
   }

   public void onTick(TickEvent event) {
      BlockPos blockPos = new BlockPos((int)mc.player.getX(), (int)(mc.player.getY() - 1.0D), (int)mc.player.getZ());
      if (mc.player.fallDistance <= 4.0F) {
         boolean noBlockBelow = mc.world.getBlockState(blockPos).isAir();
         mc.options.sneakKey.setPressed(noBlockBelow);
      } else {
         mc.options.sneakKey.setPressed(false);
      }

   }
}

