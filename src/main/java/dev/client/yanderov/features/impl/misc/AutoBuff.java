package dev.client.yanderov.features.impl.misc;

import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.interactions.inv.InventoryTask;
import dev.client.yanderov.utils.math.time.TimerUtil;
import net.minecraft.class_1268;
import net.minecraft.class_1291;
import net.minecraft.class_1294;
import net.minecraft.class_1735;
import net.minecraft.class_6880;

public class AutoBuff extends Module implements QuickImports {
   private final BooleanSetting useStrength = (new BooleanSetting("Strength", "Ð—ÐµÐ»ÑŒÐµ ÑÐ¸Ð»Ñ‹")).setValue(false);
   private final BooleanSetting useSpeed = (new BooleanSetting("Speed", "Ð—ÐµÐ»ÑŒÐµ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚Ð¸")).setValue(false);
   private final BooleanSetting useFireResist = (new BooleanSetting("FireResist", "Ð—ÐµÐ»ÑŒÐµ Ð¾Ð³Ð½ÐµÑÑ‚Ð¾Ð¹ÐºÐ¾ÑÑ‚Ð¸")).setValue(false);
   private final TimerUtil throwTimer = TimerUtil.create();
   private final TimerUtil cycleTimer = TimerUtil.create();
   private Buff pendingBuff = null;
   private boolean headDown = false;
   private float savedPitch;

   public AutoBuff() {
      super("AutoBuff", "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ ÐºÐ¸Ð´Ð°ÐµÑ‚ Ð±Ð°Ñ„-Ð·ÐµÐ»ÑŒÑ", ModuleCategory.MISC);
      this.setup(new Setting[]{this.useStrength, this.useSpeed, this.useFireResist});
   }

   private boolean isBuffEnabled(Buff b) {
      boolean var10000;
      switch (b.ordinal()) {
         case 0 -> var10000 = this.useStrength.isValue();
         case 1 -> var10000 = this.useSpeed.isValue();
         case 2 -> var10000 = this.useFireResist.isValue();
         default -> throw new MatchException((String)null, (Throwable)null);
      }

      return var10000;
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (this.useStrength.isValue() || this.useSpeed.isValue() || this.useFireResist.isValue()) {
            if (this.pendingBuff != null) {
               if (!this.headDown) {
                  this.savedPitch = mc.field_1724.method_36455();
                  mc.field_1724.method_36457(90.0F);
                  this.headDown = true;
               } else {
                  class_1735 slot = InventoryTask.getPotion(this.pendingBuff.effect);
                  if (slot != null) {
                     InventoryTask.swapHand(slot, class_1268.field_5808, false, true);
                     mc.field_1761.method_2919(mc.field_1724, class_1268.field_5808);
                     InventoryTask.swapHand(slot, class_1268.field_5808, false, true);
                     this.throwTimer.resetCounter();
                     this.cycleTimer.resetCounter();
                  }

                  mc.field_1724.method_36457(this.savedPitch);
                  this.pendingBuff = null;
                  this.headDown = false;
               }
            } else {
               if (this.cycleTimer.hasTimeElapsed(1300L) && this.throwTimer.hasTimeElapsed(100L)) {
                  for(Buff b : AutoBuff.Buff.values()) {
                     if (this.isBuffEnabled(b) && !mc.field_1724.method_6059(b.effect) && InventoryTask.getPotion(b.effect) != null) {
                        this.pendingBuff = b;
                        break;
                     }
                  }
               }

            }
         }
      }
   }

   public void activate() {
      super.activate();
      this.cycleTimer.resetCounter();
      this.throwTimer.resetCounter();
      this.pendingBuff = null;
      this.headDown = false;
   }

   public void deactivate() {
      this.pendingBuff = null;
      this.headDown = false;
      super.deactivate();
   }

   private static enum Buff {
      STRENGTH(class_1294.field_5910),
      SPEED(class_1294.field_5904),
      FIRE_RESIST(class_1294.field_5918);

      final class_6880 effect;

      private Buff(class_6880 effect) {
         this.effect = effect;
      }

      // $FF: synthetic method
      private static Buff[] $values() {
         return new Buff[]{STRENGTH, SPEED, FIRE_RESIST};
      }
   }
}

