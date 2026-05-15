package dev.client.yanderov.features.impl.player;

import dev.client.yanderov.events.player.InputEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.MultiSelectSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import net.minecraft.class_1294;
import net.minecraft.class_1799;
import net.minecraft.class_2828;

public class FastUse extends Module implements QuickImports {
   private final SelectSetting mode = (new SelectSetting("Mode", "Ð ÐµÐ¶Ð¸Ð¼ ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ñ")).value("Immediate", "ItemUseTime").selected("Immediate");
   private final MultiSelectSetting conditions = (new MultiSelectSetting("Conditions", "ÐžÐ³Ñ€Ð°Ð½Ð¸Ñ‡ÐµÐ½Ð¸Ñ")).value("NotInTheAir", "NotDuringMove", "NotDuringRegeneration").selected("NotInTheAir");
   private final BooleanSetting stopInput = (new BooleanSetting("StopInput", "ÐžÑÑ‚Ð°Ð½Ð°Ð²Ð»Ð¸Ð²Ð°Ñ‚ÑŒ Ð²Ð²Ð¾Ð´ Ð¿Ñ€Ð¸ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ð¸")).setValue(false);
   private final SliderSettings delay = (new SliderSettings("Delay", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿ÐµÑ€ÐµÐ´ ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸ÐµÐ¼ (Ñ‚Ð¸ÐºÐ¸)")).setValue(0.0F).range(0.0F, 10.0F).visible(() -> this.mode.isSelected("Immediate"));
   private final SliderSettings speedPackets = (new SliderSettings("Speed", "ÐšÐ¾Ð»Ð¸Ñ‡ÐµÑÑ‚Ð²Ð¾ Ð¿Ð°ÐºÐµÑ‚Ð¾Ð² Ð·Ð° Ñ‚Ð¸Ðº")).setValue(20.0F).range(1.0F, 35.0F).visible(() -> this.mode.isSelected("Immediate"));
   private final SliderSettings consumeTime = (new SliderSettings("ConsumeTime", "ÐœÐ¸Ð½. Ð²Ñ€ÐµÐ¼Ñ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ñ (Ñ‚Ð¸ÐºÐ¸)")).setValue(15.0F).range(0.0F, 20.0F).visible(() -> this.mode.isSelected("ItemUseTime"));
   private final SliderSettings itemSpeedPackets = (new SliderSettings("Packets", "ÐšÐ¾Ð»Ð¸Ñ‡ÐµÑÑ‚Ð²Ð¾ Ð¿Ð°ÐºÐµÑ‚Ð¾Ð² Ð·Ð° Ñ‚Ð¸Ðº")).setValue(20.0F).range(1.0F, 35.0F).visible(() -> this.mode.isSelected("ItemUseTime"));
   private int waitTicks = 0;

   public FastUse() {
      super("FastUse", ModuleCategory.PLAYER);
      this.setup(new Setting[]{this.mode, this.conditions, this.stopInput, this.delay, this.speedPackets, this.consumeTime, this.itemSpeedPackets});
   }

   @EventHandler
   public void onInput(InputEvent e) {
      if (this.stopInput.isValue()) {
         try {
            if (mc.field_1690 != null && mc.field_1690.field_1904.method_1434()) {
               e.setDirectional(false, false, false, false);
               e.setJumping(false);
            }
         } catch (Throwable var3) {
         }

      }
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc.field_1724 != null && mc.field_1687 != null && mc.method_1562() != null) {
            if (!this.accelerateNow()) {
               this.waitTicks = 0;
            } else {
               if (this.mode.isSelected("Immediate")) {
                  if (this.waitTicks == 0) {
                     this.waitTicks = Math.max(0, Math.round(this.delay.getValue())) + 1;
                  }

                  --this.waitTicks;
                  if (this.waitTicks == 0) {
                     this.sendMovePackets(Math.max(1, Math.round(this.speedPackets.getValue())));

                     try {
                        mc.field_1724.method_6075();
                     } catch (Throwable var4) {
                     }
                  }
               } else {
                  try {
                     if (mc.field_1724.method_6048() >= Math.max(0, Math.round(this.consumeTime.getValue()))) {
                        this.sendMovePackets(Math.max(1, Math.round(this.itemSpeedPackets.getValue())));
                        mc.field_1724.method_6075();
                     }
                  } catch (Throwable var3) {
                  }
               }

            }
         }
      }
   }

   private boolean accelerateNow() {
      try {
         if (!mc.field_1724.method_6115()) {
            return false;
         } else {
            class_1799 st = mc.field_1724.method_6030();
            if (st != null && !st.method_7960()) {
               if (!this.isConsumable(st)) {
                  return false;
               } else if (this.conditions.isSelected("NotInTheAir") && !mc.field_1724.method_24828()) {
                  return false;
               } else if (!this.conditions.isSelected("NotDuringMove") || mc.field_1724.field_6250 == 0.0F && mc.field_1724.field_6212 == 0.0F) {
                  return !this.conditions.isSelected("NotDuringRegeneration") || !mc.field_1724.method_6059(class_1294.field_5924);
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }
      } catch (Throwable var2) {
         return false;
      }
   }

   private boolean isConsumable(class_1799 stack) {
      try {
         return stack.method_7909().method_7881(stack, mc.field_1724) > 0;
      } catch (Throwable var3) {
         return false;
      }
   }

   private void sendMovePackets(int count) {
      double x = mc.field_1724.method_23317();
      double y = mc.field_1724.method_23318();
      double z = mc.field_1724.method_23321();
      float yaw = mc.field_1724.method_36454();
      float pitch = mc.field_1724.method_36455();
      boolean onGround = mc.field_1724.method_24828();
      boolean horiz = mc.field_1724.field_5976;

      for(int i = 0; i < count; ++i) {
         try {
            PlayerInteractionHelper.sendPacketWithOutEvent(new class_2828.class_2830(x, y, z, yaw, pitch, onGround, horiz));
         } catch (Throwable var16) {
            try {
               PlayerInteractionHelper.sendPacketWithOutEvent(new class_2828.class_5911(onGround, horiz));
            } catch (Throwable var15) {
            }
         }
      }

   }
}

