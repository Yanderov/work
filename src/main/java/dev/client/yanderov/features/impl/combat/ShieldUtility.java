package dev.client.yanderov.features.impl.combat;

import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import net.minecraft.class_1268;
import net.minecraft.class_1309;
import net.minecraft.class_1743;
import net.minecraft.class_1799;
import net.minecraft.class_1839;
import net.minecraft.class_2561;
import net.minecraft.class_2868;

public class ShieldUtility extends Module {
   private final BooleanSetting breakShieldGroup = (new BooleanSetting("Break shield", "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸Ð¹ Ð»Ð¾Ð¼ Ñ‰Ð¸Ñ‚Ð°")).setValue(true);
   private final SliderSettings breakShieldClicks = (new SliderSettings("Clicks", "ÐšÐ¾Ð»Ð¸Ñ‡ÐµÑÑ‚Ð²Ð¾ ÐºÐ»Ð¸ÐºÐ¾Ð² Ð¿Ñ€Ð¸ Ð»Ð¾Ð¼Ð°Ð½Ð¸Ð¸ Ñ‰Ð¸Ñ‚Ð°")).setValue(1.0F).range(1.0F, 10.0F);
   private final BooleanSetting autoBlockingGroup = (new BooleanSetting("Auto blocking", "ÐÐ²Ñ‚Ð¾ Ð±Ð»Ð¾Ðº Ñ‰Ð¸Ñ‚Ð¾Ð¼")).setValue(true);
   private final SelectSetting autoBlockingBlockMode = (new SelectSetting("Block mode", "Ð ÐµÐ¶Ð¸Ð¼ Ð¿Ð¾ÑÑ‚Ð°Ð½Ð¾Ð²ÐºÐ¸ Ð±Ð»Ð¾ÐºÐ°")).value("InteractItem").selected("InteractItem");
   private final SelectSetting autoBlockingUnblockMode = (new SelectSetting("Unblock mode", "Ð ÐµÐ¶Ð¸Ð¼ ÑÐ½ÑÑ‚Ð¸Ñ Ð±Ð»Ð¾ÐºÐ°")).value("StopUsingItem", "ChangeSlot", "None").selected("StopUsingItem");

   public ShieldUtility() {
      super("Shield Utility", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.breakShieldGroup, this.breakShieldClicks, this.autoBlockingGroup, this.autoBlockingBlockMode, this.autoBlockingUnblockMode});
   }

   public boolean breakShield() {
      if (this.isState() && this.breakShieldGroup.isValue() && mc.field_1724 != null && mc.field_1687 != null) {
         class_1309 target = Aura.getInstance().getCurrentTarget();
         if (target != null && target.method_6039()) {
            for(int i = 0; i < 8; ++i) {
               class_1799 stack = mc.field_1724.method_31548().method_5438(i);
               if (stack.method_7909() instanceof class_1743) {
                  int currSlot = mc.field_1724.method_31548().field_7545;
                  if (currSlot != i) {
                     mc.field_1724.field_3944.method_52787(new class_2868(i));
                  }

                  int clicks = (int)this.breakShieldClicks.getValue();

                  for(int c = 0; c < clicks; ++c) {
                     mc.field_1761.method_2918(mc.field_1724, target);
                     mc.field_1724.method_6104(class_1268.field_5808);
                  }

                  if (currSlot != i) {
                     mc.field_1724.field_3944.method_52787(new class_2868(currSlot));
                  }

                  mc.field_1724.method_7353(class_2561.method_43470("Broke shield of ").method_10852(target.method_5476()), false);
                  return true;
               }
            }

            return false;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void startBlocking() {
      if (this.isState() && this.autoBlockingGroup.isValue() && mc.field_1724 != null && mc.field_1687 != null && !mc.field_1724.method_6039()) {
         class_1268 hand;
         if (this.canBlock(mc.field_1724.method_6047())) {
            hand = class_1268.field_5808;
         } else {
            if (!this.canBlock(mc.field_1724.method_6079())) {
               return;
            }

            hand = class_1268.field_5810;
         }

         if (this.autoBlockingBlockMode.isSelected("InteractItem")) {
            mc.field_1761.method_2919(mc.field_1724, hand);
         }

      }
   }

   public void stopBlocking(boolean pauses) {
      if (this.isState() && this.autoBlockingGroup.isValue() && mc.field_1724 != null && mc.field_1687 != null && mc.field_1724.method_6039()) {
         switch (this.autoBlockingUnblockMode.getSelected()) {
            case "StopUsingItem":
               mc.field_1761.method_2897(mc.field_1724);
               break;
            case "ChangeSlot":
               int current = mc.field_1724.method_31548().field_7545;
               int next = (current + 1) % 8;
               mc.field_1724.field_3944.method_52787(new class_2868(next));
               mc.field_1724.field_3944.method_52787(new class_2868(current));
               break;
            case "None":
               if (!pauses) {
                  mc.field_1761.method_2897(mc.field_1724);
               }
         }

      }
   }

   private boolean canBlock(class_1799 stack) {
      if (!stack.method_7960() && stack.method_45435(mc.field_1687.method_45162()) && stack.method_7909() != null) {
         return stack.method_7909().method_7853(stack) == class_1839.field_8949;
      } else {
         return false;
      }
   }
}

