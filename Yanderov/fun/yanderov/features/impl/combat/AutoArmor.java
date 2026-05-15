package fun.Yanderov.features.impl.combat;

import antidaunleak.api.annotation.Native;
import fun.Yanderov.display.hud.Notifications;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.impl.render.Hud;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.IArmorItem;
import fun.Yanderov.utils.interactions.inv.InventoryFlowManager;
import fun.Yanderov.utils.interactions.inv.InventoryTask;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.class_124;
import net.minecraft.class_1304;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1738;
import net.minecraft.class_1741;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1887;
import net.minecraft.class_1890;
import net.minecraft.class_1893;
import net.minecraft.class_2378;
import net.minecraft.class_2561;
import net.minecraft.class_6880;
import net.minecraft.class_7924;
import net.minecraft.class_1304.class_1305;

public class AutoArmor extends Module {
   public AutoArmor() {
      super("AutoArmor", "Auto Armor", ModuleCategory.PLAYER);
      this.setup(new Setting[0]);
   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   public void onTick(TickEvent e) {
      if (!InventoryTask.isServerScreen()) {
         if (InventoryFlowManager.script.isFinished()) {
            List<Runnable> list = new ArrayList();

            for(class_1304 equipment : class_1304.values()) {
               class_1799 equipStack = mc.field_1724.method_31548().method_7372(equipment.method_5927());
               if (equipment.method_5925() == class_1305.field_6178 && (!equipment.equals(class_1304.field_6174) || !equipStack.method_7909().equals(class_1802.field_8833))) {
                  int armorSlot = 8 - equipment.method_5927();
                  class_1735 slot = InventoryTask.getSlot((Predicate)((s) -> {
                     class_1799 stack = s.method_7677();
                     boolean var10000;
                     if (s.field_7874 != armorSlot && !this.isBroken(stack) && !this.hasCurseOfBinding(stack)) {
                        class_1792 patt0$temp = stack.method_7909();
                        if (patt0$temp instanceof class_1738) {
                           class_1738 armorItem = (class_1738)patt0$temp;
                           if (((IArmorItem)armorItem).zov_pidarok$getType().method_48399().equals(equipment)) {
                              var10000 = true;
                              return var10000;
                           }
                        }
                     }

                     var10000 = false;
                     return var10000;
                  }), (Comparator)Comparator.comparingDouble((s) -> (double)this.calculateArmorValue(s.method_7677(), (IArmorItem)s.method_7677().method_7909())));
                  if (slot != null && this.isBetter(slot.method_7677(), equipStack)) {
                     list.add((Runnable)() -> InventoryTask.moveItem(slot, armorSlot));
                  } else if (this.isBroken(equipStack)) {
                     Hud hud = Hud.getInstance();
                     if (slot != null) {
                        list.add((Runnable)() -> InventoryTask.moveItem(slot, armorSlot));
                        if (hud.state && hud.notificationSettings.isSelected("Auto Armor")) {
                           Notifications var10000 = Notifications.getInstance();
                           String var10001 = String.valueOf(class_124.field_1060);
                           var10000.addList((class_2561)class_2561.method_43470("Ð—Ð°Ð¼ÐµÐ½Ð¸Ð» - " + var10001 + this.equipName(equipment) + String.valueOf(class_124.field_1070) + " Ð½Ð° ").method_10852(equipStack.method_7964()), 3000L);
                        }
                     } else if (InventoryTask.getSlot((class_1792)class_1802.field_8162, (Predicate)((s) -> s.field_7874 >= 9)) != null) {
                        list.add((Runnable)() -> InventoryTask.clickSlot(armorSlot, 0, class_1713.field_7794, false));
                        if (hud.state && hud.notificationSettings.isSelected("Auto Armor")) {
                           Notifications.getInstance().addList((class_2561)class_2561.method_43470("Ð—Ð°ÑÐµÐ¹Ð²Ð¸Ð» - ").method_10852(equipStack.method_7964()), 3000L);
                        }
                     }
                  }
               }
            }

            if (!list.isEmpty()) {
               InventoryFlowManager.addTask(() -> {
                  list.forEach(Runnable::run);
                  InventoryTask.updateSlots();
               });
            }

         }
      }
   }

   private float calculateArmorValue(class_1799 stack, IArmorItem armorItem) {
      class_2378<class_1887> enchantments = mc.field_1687.method_30349().method_30530(class_7924.field_41265);
      class_1741 material = armorItem.zov_pidarok$getMaterial();
      float protection = (float)(Integer)material.comp_2298().getOrDefault(armorItem.zov_pidarok$getType(), 0) + material.comp_2303() + (float)class_1890.method_8225((class_6880)enchantments.method_10223(class_1893.field_9111.method_29177()).orElseThrow(), stack);
      float unbreaking = (float)class_1890.method_8225((class_6880)enchantments.method_10223(class_1893.field_9119.method_29177()).orElseThrow(), stack);
      float mending = (float)class_1890.method_8225((class_6880)enchantments.method_10223(class_1893.field_9101.method_29177()).orElseThrow(), stack);
      return protection + unbreaking * 0.1F + mending * 0.2F;
   }

   private boolean hasCurseOfBinding(class_1799 stack) {
      class_6880<class_1887> curseEntry = (class_6880)mc.field_1687.method_30349().method_30530(class_7924.field_41265).method_10223(class_1893.field_9113.method_29177()).orElse((Object)null);
      if (curseEntry == null) {
         return false;
      } else {
         return class_1890.method_8225(curseEntry, stack) > 0;
      }
   }

   private boolean isBroken(class_1799 stack) {
      return (double)stack.method_7919() / (double)stack.method_7936() > 0.98;
   }

   private boolean isBetter(class_1799 newArmor, class_1799 currentArmor) {
      if (currentArmor.method_7960()) {
         return true;
      } else {
         class_1792 var5 = newArmor.method_7909();
         if (var5 instanceof class_1738) {
            class_1738 newItem = (class_1738)var5;
            var5 = currentArmor.method_7909();
            if (var5 instanceof class_1738) {
               class_1738 currentItem = (class_1738)var5;
               return this.calculateArmorValue(newArmor, (IArmorItem)newItem) > this.calculateArmorValue(currentArmor, (IArmorItem)currentItem);
            }
         }

         return false;
      }
   }

   private String equipName(class_1304 equipmentSlot) {
      String var10000;
      switch (equipmentSlot) {
         case field_6166 -> var10000 = "Ð‘Ð¾Ñ‚Ð¸Ð½ÐºÐ¸";
         case field_6172 -> var10000 = "ÐŸÐ¾Ð½Ð¾Ð¶Ð¸";
         case field_6174 -> var10000 = "ÐÐ°Ð³Ñ€ÑƒÐ´Ð½Ð¸Ðº";
         case field_6169 -> var10000 = "Ð¨Ð»ÐµÐ¼";
         default -> var10000 = "Ð”Ð•Ð—Ð— Ð¡Ð’Ð˜ÐÐ¬Ð¯ Ð¡Ð£ÐšÐÐÐ!";
      }

      return var10000;
   }
}

