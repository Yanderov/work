package dev.client.yanderov.features.impl.misc;

import dev.client.yanderov.display.hud.Notifications;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BindSetting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import dev.client.yanderov.utils.interactions.inv.InventoryFlowManager;
import dev.client.yanderov.utils.interactions.inv.InventoryTask;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2561;
import net.minecraft.class_437;
import net.minecraft.class_490;

public class ArrowsSwap extends Module implements QuickImports {
   private final BindSetting swapKey = (new BindSetting("ÐšÐ½Ð¾Ð¿ÐºÐ° ÑÐ²Ð°Ð¿Ð°", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð´Ð»Ñ Ð·Ð°Ð¿ÑƒÑÐºÐ° ÑÐ²Ð°Ð¿Ð°")).setKey(-1);
   private final BooleanSetting legitMode = (new BooleanSetting("Ð›ÐµÐ³Ð¸Ñ‚", "Ð’Ð¸Ð´Ð¸Ð¼Ñ‹Ð¹ ÑÐ²Ð°Ð¿ Ñ‡ÐµÑ€ÐµÐ· Ð¾Ñ‚ÐºÑ€Ñ‹Ñ‚Ð¸Ðµ Ð¸Ð½Ð²ÐµÐ½Ñ‚Ð°Ñ€Ñ")).setValue(true);
   private final BooleanSetting noJumpSwap = (new BooleanSetting("ÐÐµ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ñ‚ÑŒ Ð² Ð¿Ñ€Ñ‹Ð¶ÐºÐµ", "Ð‘Ð»Ð¾ÐºÐ¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒ ÑÐ²Ð°Ð¿ Ð² Ð¿Ñ€Ñ‹Ð¶ÐºÐµ/Ð¿Ð°Ð´ÐµÐ½Ð¸Ð¸")).setValue(false).visible(() -> this.legitMode.isValue());
   private boolean legitFlow = false;
   private int legitStep = 0;
   private int legitTickWait = 0;

   public ArrowsSwap() {
      super("ArrowsSwap", ModuleCategory.MISC);
      this.setup(new Setting[]{this.swapKey, this.legitMode, this.noJumpSwap});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.isState()) {
         if (e.getType() == 0) {
            if (mc != null && mc.field_1724 != null && mc.field_1687 != null) {
               boolean pressed = PlayerInteractionHelper.isKey(this.swapKey);
               if (pressed && mc.field_1755 == null && !this.legitFlow) {
                  if (this.legitMode.isValue() && this.noJumpSwap.isValue() && (!mc.field_1724.method_24828() || mc.field_1724.field_6017 > 0.0F)) {
                     return;
                  }

                  if (this.legitMode.isValue()) {
                     mc.method_1507(new class_490(mc.field_1724));
                     this.legitFlow = true;
                     this.legitStep = 0;
                     this.legitTickWait = 0;
                  } else {
                     this.performSwapTasked();
                  }
               }

               if (this.legitFlow && this.legitMode.isValue()) {
                  ++this.legitTickWait;
                  if (this.legitStep == 0) {
                     if (mc.field_1755 instanceof class_490 && this.legitTickWait >= 1) {
                        this.legitStep = 1;
                        this.legitTickWait = 0;
                     }
                  } else if (this.legitStep == 1) {
                     this.trySwapInCurrentScreen(false);
                     this.legitStep = 2;
                     this.legitTickWait = 0;
                  } else if (this.legitStep == 2 && this.legitTickWait >= 1) {
                     try {
                        mc.field_1724.method_7346();
                     } catch (Throwable var5) {
                     }

                     try {
                        mc.method_1507((class_437)null);
                     } catch (Throwable var4) {
                     }

                     this.legitFlow = false;
                     this.legitStep = 0;
                     this.legitTickWait = 0;
                  }
               }

            }
         }
      }
   }

   private void performSwapTasked() {
      if (!InventoryFlowManager.shouldSkipExecution()) {
         InventoryFlowManager.addTask(() -> this.trySwapInCurrentScreen(true));
      }
   }

   private void trySwapInCurrentScreen(boolean updateInventory) {
      class_1735[] pair = this.findTwoDistinctArrowSlots();
      if (pair == null) {
         try {
            mc.field_1724.method_7353(class_2561.method_30163("Ð½ÐµÑ‚ ÑÑ‚Ñ€ÐµÐ» Ð´Ð»Ñ ÑÐ²Ð°Ð¿Ð°"), true);
         } catch (Throwable var8) {
         }

      } else {
         class_1735 a = pair[0];
         class_1735 b = pair[1];
         class_1799 beforeA = a.method_7677().method_7972();
         class_1799 beforeB = b.method_7677().method_7972();

         try {
            InventoryTask.clickSlot(a, 0, class_1713.field_7791, false);
            InventoryTask.clickSlot(b, 0, class_1713.field_7791, false);
            InventoryTask.clickSlot(a, 0, class_1713.field_7791, false);
            if (updateInventory) {
               InventoryTask.updateSlots();
            }

            this.notifySwap(beforeA, beforeB);
         } catch (Throwable var9) {
         }

      }
   }

   private void notifySwap(class_1799 from, class_1799 to) {
      try {
         class_2561 msg = class_2561.method_43473().method_10852(this.coloredArrowName(from)).method_10852(class_2561.method_43470(" ÑÐ²Ð°Ð¿Ð½ÑƒÑ‚ Ð½Ð° ")).method_10852(this.coloredArrowName(to));
         Notifications.getInstance().addList(msg, 5000L);
      } catch (Throwable var4) {
      }

   }

   private class_2561 coloredArrowName(class_1799 st) {
      return st.method_7964().method_27661();
   }

   private class_1735[] findTwoDistinctArrowSlots() {
      try {
         class_1735 first = null;

         for(class_1735 s : mc.field_1724.field_7512.field_7761) {
            if (s != null && s.field_7874 >= 9 && s.field_7874 <= 44) {
               class_1799 st = s.method_7677();
               if (st != null && !st.method_7960() && this.isArrow(st)) {
                  if (first == null) {
                     first = s;
                  } else if (s.field_7874 != first.field_7874) {
                     return new class_1735[]{first, s};
                  }
               }
            }
         }
      } catch (Throwable var5) {
      }

      return null;
   }

   private boolean isArrow(class_1799 st) {
      try {
         return st.method_31574(class_1802.field_8107) || st.method_31574(class_1802.field_8236) || st.method_31574(class_1802.field_8087);
      } catch (Throwable var5) {
         try {
            return st.method_7909() == class_1802.field_8107 || st.method_7909() == class_1802.field_8236 || st.method_7909() == class_1802.field_8087;
         } catch (Throwable var4) {
            return false;
         }
      }
   }
}

