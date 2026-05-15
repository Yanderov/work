package dev.client.yanderov.features.impl.misc;

import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2480;
import net.minecraft.class_2596;
import net.minecraft.class_2846;
import net.minecraft.class_2846.class_2847;

public class FastShulkerBreak extends Module implements QuickImports {
   private final SliderSettings breakCount = (new SliderSettings("ÐšÐ¾Ð»-Ð²Ð¾ Ñ€Ð°Ð·Ñ€ÑƒÑˆÐµÐ½Ð¸Ð¹", "Ð¡ÐºÐ¾Ð»ÑŒÐºÐ¾ Ñ€Ð°Ð· Ð´ÑƒÐ±Ð»Ð¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒ STOP_DESTROY_BLOCK")).setValue(5.0F).range(2.0F, 1000.0F);
   private class_2338 lastBreakPos = null;
   private long lastBreakTime = 0L;

   public FastShulkerBreak() {
      super("Fast Shulker Break", ModuleCategory.MISC);
      this.setup(new Setting[]{this.breakCount});
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (mc != null && mc.field_1687 != null && mc.field_1724 != null) {
         if (e.isSend()) {
            class_2596 var3 = e.getPacket();
            if (var3 instanceof class_2846) {
               class_2846 pkt = (class_2846)var3;
               if (pkt.method_12363() == class_2847.field_12973) {
                  class_2338 pos = pkt.method_12362();
                  class_2350 face = pkt.method_12360();
                  if (pos != null && face != null) {
                     boolean isShulker = mc.field_1687.method_8320(pos).method_26204() instanceof class_2480;
                     long now = System.currentTimeMillis();
                     if (isShulker) {
                        if (this.lastBreakPos == null || !this.lastBreakPos.equals(pos) || now - this.lastBreakTime > 500L) {
                           int reps = Math.min((int)this.breakCount.getValue(), 10000) - 1;
                           if (reps > 0) {
                              for(int i = 0; i < reps; ++i) {
                                 mc.method_1562().method_52787(new class_2846(class_2847.field_12973, pos, face));
                              }

                              this.lastBreakPos = pos;
                              this.lastBreakTime = now;
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public void activate() {
      super.activate();
      this.lastBreakPos = null;
      this.lastBreakTime = 0L;
   }

   public void deactivate() {
      super.deactivate();
      this.lastBreakPos = null;
      this.lastBreakTime = 0L;
   }
}

