package dev.client.yanderov.features.impl.player;

import dev.client.yanderov.events.block.BlockBreakingEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2846;
import net.minecraft.class_2846.class_2847;

public class FastBreak extends Module {
   public FastBreak() {
      super("FastBreak", "Fast Break", ModuleCategory.PLAYER);
   }

   @EventHandler
   public void onBlockBreaking(BlockBreakingEvent e) {
      class_2338 blockPos = e.blockPos();
      class_2350 direction = e.direction();
      if ((double)mc.field_1761.field_3715 >= (double)0.5F) {
         mc.field_1724.field_3944.method_52787(new class_2846(class_2847.field_12973, blockPos, direction));
         mc.field_1724.field_3944.method_52787(new class_2846(class_2847.field_12971, blockPos, direction));
      }

   }
}

