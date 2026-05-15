package dev.client.yanderov.utils.interactions.inv;

import dev.client.yanderov.display.hud.Notifications;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.features.aura.utils.MathAngle;
import dev.client.yanderov.utils.features.aura.warp.Turns;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import dev.client.yanderov.utils.interactions.item.ItemTask;
import dev.client.yanderov.utils.math.calc.Calculate;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1291;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1713;
import net.minecraft.class_1730;
import net.minecraft.class_1735;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1844;
import net.minecraft.class_2561;
import net.minecraft.class_2813;
import net.minecraft.class_2815;
import net.minecraft.class_4081;
import net.minecraft.class_4174;
import net.minecraft.class_6880;
import net.minecraft.class_746;
import net.minecraft.class_7923;
import net.minecraft.class_9334;

public class InventoryTask implements QuickImports {
   public static void moveItem(class_1735 from, int to) {
      if (from != null) {
         moveItem(from.field_7874, to, false, false);
      }

   }

   public static void moveItem(class_1735 from, int to, boolean task) {
      moveItem(from, to, task, false);
   }

   public static void moveItem(class_1735 from, int to, boolean task, boolean updateInventory) {
      if (from != null) {
         moveItem(from.field_7874, to, task, updateInventory);
      }

   }

   public static void moveItem(int from, int to, boolean task, boolean updateInventory) {
      if (from != to && from != -1) {
         int count = Math.toIntExact(slots().count()) - 10;
         if (from >= count && count == 36) {
            if (task) {
               InventoryFlowManager.addTask(() -> clickSlot(to, from - count, class_1713.field_7791, false));
            } else {
               clickSlot(to, from - count, class_1713.field_7791, false);
            }

         } else {
            if (task) {
               InventoryFlowManager.addTask(() -> moveItem(from, to, updateInventory));
            } else {
               moveItem(from, to, updateInventory);
            }

         }
      }
   }

   public static void moveItem(int from, int to, boolean updateInventory) {
      clickSlot(from, 0, class_1713.field_7791, false);
      clickSlot(to, 0, class_1713.field_7791, false);
      clickSlot(from, 0, class_1713.field_7791, false);
      if (updateInventory) {
         updateSlots();
      }

   }

   public static void swapHand(class_1735 slot, class_1268 hand, boolean task) {
      swapHand(slot, hand, task, false);
   }

   public static void swapHand(class_1735 slot, class_1268 hand, boolean task, boolean updateInventory) {
      if (slot != null && slot.field_7874 != -1 && (!hand.equals(class_1268.field_5810) || slot.field_7871 instanceof class_1661 || slot.field_7871 instanceof class_1730)) {
         int button = hand.equals(class_1268.field_5808) ? mc.field_1724.method_31548().field_7545 : 40;
         if (task) {
            InventoryFlowManager.addTask(() -> swap(slot, button, updateInventory));
         } else {
            swap(slot, button, updateInventory);
         }

      }
   }

   public static void swap(class_1735 slot, int button, boolean updateInventory) {
      clickSlot(slot, button, class_1713.field_7791, false);
      if (updateInventory) {
         updateSlots();
      }

   }

   public static void swapAndUse(class_1735 slot, String text, boolean task) {
      if (slot == null) {
         Notifications.getInstance().addList(String.valueOf(class_124.field_1061) + text + String.valueOf(class_124.field_1070) + " - Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½!", 3000L);
      } else {
         if (task) {
            InventoryFlowManager.addTask(() -> swapAndUse(slot, MathAngle.cameraAngle()));
         } else {
            swapAndUse(slot, MathAngle.cameraAngle());
         }

      }
   }

   public static void swapAndUse(class_1792 item) {
      swapAndUse(item, MathAngle.cameraAngle(), false);
   }

   public static void clickSlot(int id, int button, class_1713 type) {
      if (id != -1 && mc.field_1761 != null && mc.field_1724 != null) {
         mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, id, button, type, mc.field_1724);
      }
   }

   public static void switchTo(int slot) {
      if (mc.field_1724 != null && mc.method_1562() != null) {
         if (mc.field_1724.method_31548().field_7545 != slot) {
            mc.field_1724.method_31548().field_7545 = slot;
         }
      }
   }

   public static void swapAndUse(class_1792 item, String searchName, boolean task) {
      float cooldownProgress = ItemTask.getCooldownProgress(item);
      if (cooldownProgress > 0.0F) {
         String time = Calculate.round((double)cooldownProgress, 0.1) + "Ñ";
         Notifications var6 = Notifications.getInstance();
         String var7 = String.valueOf(class_124.field_1061);
         var6.addList(var7 + item.method_63680().getString() + String.valueOf(class_124.field_1070) + " - Ð² ÐºÐ´ ÐµÑ‰Ðµ " + time, 2000L);
      } else {
         class_1735 slot = getSlot((Predicate)((s) -> s.method_7677().method_7909().equals(item) && getCleanName(s.method_7677().method_7964()).contains(searchName.toLowerCase())));
         if (slot == null) {
            Notifications var10000 = Notifications.getInstance();
            String var10001 = String.valueOf(class_124.field_1061);
            var10000.addList(var10001 + item.method_63680().getString() + String.valueOf(class_124.field_1070) + " - Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½!", 2000L);
         } else {
            if (task) {
               InventoryFlowManager.addTask(() -> swapAndUse(slot, MathAngle.cameraAngle()));
            } else {
               swapAndUse(slot, MathAngle.cameraAngle());
            }

         }
      }
   }

   public static void swapAndUse(class_1792 item, Turns angle, boolean task) {
      float cooldownProgress = ItemTask.getCooldownProgress(item);
      if (cooldownProgress > 0.0F) {
         String time = Calculate.round((double)cooldownProgress, 0.1) + "Ñ";
         Notifications var6 = Notifications.getInstance();
         String var7 = String.valueOf(class_124.field_1061);
         var6.addList(var7 + item.method_63680().getString() + String.valueOf(class_124.field_1070) + " - Ð² ÐºÐ´ ÐµÑ‰Ðµ " + time, 2000L);
      } else {
         class_1735 slot = getSlot(item);
         if (slot == null) {
            Notifications var10000 = Notifications.getInstance();
            String var10001 = String.valueOf(class_124.field_1061);
            var10000.addList(var10001 + item.method_63680().getString() + String.valueOf(class_124.field_1070) + " - Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½!", 2000L);
         } else {
            if (task) {
               InventoryFlowManager.addTask(() -> swapAndUse(slot, angle));
            } else {
               swapAndUse(slot, angle);
            }

         }
      }
   }

   public static void swapAndUse(class_1735 slot, Turns angle) {
      swapHand(slot, class_1268.field_5808, false);
      PlayerInteractionHelper.interactItem(class_1268.field_5808);
      swapHand(slot, class_1268.field_5808, false, true);
   }

   public static void updateSlots() {
      class_1703 screenHandler = mc.field_1724.field_7512;
      class_1799 stack = ((class_1792)class_7923.field_41178.method_10200(Calculate.getRandom(0, 100))).method_7854();
      mc.field_1724.field_3944.method_52787(new class_2813(screenHandler.field_7763, screenHandler.method_37421(), 0, 0, class_1713.field_7793, stack, Int2ObjectMaps.singleton(0, stack)));
   }

   public static void closeScreen(boolean packet) {
      if (packet) {
         mc.field_1724.field_3944.method_52787(new class_2815(mc.field_1724.field_7512.field_7763));
      } else {
         mc.field_1724.method_7346();
      }

   }

   public static void clickSlot(class_1735 slot, int button, class_1713 clickType, boolean silent) {
      if (slot != null) {
         clickSlot(slot.field_7874, button, clickType, silent);
      }

   }

   public static void clickSlot(int slotId, int buttonId, class_1713 clickType, boolean silent) {
      clickSlot(mc.field_1724.field_7512.field_7763, slotId, buttonId, clickType, silent);
   }

   public static void clickSlot(int windowId, int slotId, int buttonId, class_1713 clickType, boolean silent) {
      mc.field_1761.method_2906(windowId, slotId, buttonId, clickType, mc.field_1724);
      if (silent) {
         mc.field_1724.field_7512.method_7593(slotId, buttonId, clickType, mc.field_1724);
      }

   }

   public static class_1735 getSlot(class_1792 item) {
      return getSlot((class_1792)item, (Predicate)((s) -> true));
   }

   public static class_1735 getSlot(class_1792 item, Predicate filter) {
      return getSlot(item, Comparator.comparingInt((s) -> 0), filter);
   }

   public static class_1735 getSlot(Predicate filter) {
      return (class_1735)slots().filter(filter).findFirst().orElse((Object)null);
   }

   public static class_1735 getSlot(Predicate filter, Comparator comparator) {
      return (class_1735)slots().filter(filter).max(comparator).orElse((Object)null);
   }

   public static class_1735 getSlot(class_1792 item, Comparator comparator, Predicate filter) {
      return (class_1735)slots().filter((s) -> s.method_7677().method_7909().equals(item)).filter(filter).max(comparator).orElse((Object)null);
   }

   public static class_1735 getFoodMaxSaturationSlot() {
      return (class_1735)slots().filter((s) -> s.method_7677().method_57824(class_9334.field_50075) != null && !((class_4174)s.method_7677().method_57824(class_9334.field_50075)).comp_2493()).max(Comparator.comparingDouble((s) -> (double)((class_4174)s.method_7677().method_57824(class_9334.field_50075)).comp_2492())).orElse((Object)null);
   }

   public static class_1735 getSlot(List item) {
      return (class_1735)slots().filter((s) -> item.contains(s.method_7677().method_7909())).findFirst().orElse((Object)null);
   }

   public static class_1735 getPotion(class_6880 effect) {
      return (class_1735)slots().filter((s) -> {
         class_1844 component = (class_1844)s.method_7677().method_57824(class_9334.field_49651);
         return component == null ? false : StreamSupport.stream(component.method_57397().spliterator(), false).anyMatch((e) -> e.method_5579().equals(effect));
      }).findFirst().orElse((Object)null);
   }

   public static class_1735 getPotionFromCategory(class_4081 category) {
      return (class_1735)slots().filter((s) -> {
         class_1799 stack = s.method_7677();
         class_1844 component = (class_1844)stack.method_57824(class_9334.field_49651);
         if (stack.method_7909().equals(class_1802.field_8436) && component != null) {
            class_4081 category2 = category.equals(class_4081.field_18271) ? class_4081.field_18272 : class_4081.field_18271;
            long effects = StreamSupport.stream(component.method_57397().spliterator(), false).filter((e) -> ((class_1291)e.method_5579().comp_349()).method_18792().equals(category)).count();
            long effects2 = StreamSupport.stream(component.method_57397().spliterator(), false).filter((e) -> ((class_1291)e.method_5579().comp_349()).method_18792().equals(category2)).count();
            return effects >= effects2;
         } else {
            return false;
         }
      }).findFirst().orElse((Object)null);
   }

   public static int getInventoryCount(class_1792 item) {
      return IntStream.range(0, 45).filter((i) -> ((class_746)Objects.requireNonNull(mc.field_1724)).method_31548().method_5438(i).method_7909().equals(item)).map((i) -> mc.field_1724.method_31548().method_5438(i).method_7947()).sum();
   }

   public static int getHotbarItems(List items) {
      return IntStream.range(0, 9).filter((i) -> items.contains(mc.field_1724.method_31548().method_5438(i).method_7909())).findFirst().orElse(-1);
   }

   public static int getHotbarSlotId(IntPredicate filter) {
      return IntStream.range(0, 9).filter(filter).findFirst().orElse(-1);
   }

   public static int getCount(Predicate filter) {
      return slots().filter(filter).mapToInt((s) -> s.method_7677().method_7947()).sum();
   }

   public static class_1735 mainHandSlot() {
      long count = slots().count();
      int i = count == 46L ? 10 : 9;
      return (class_1735)slots().toList().get(Math.toIntExact(count - (long)i + (long)mc.field_1724.method_31548().field_7545));
   }

   public static boolean isServerScreen() {
      return slots().toList().size() != 46;
   }

   public static Stream slots() {
      return mc.field_1724.field_7512.field_7761.stream();
   }

   public static void selectCompass() {
      class_1735 slot = getSlot(class_1802.field_8251);
      if (slot != null) {
         mc.field_1724.method_31548().field_7545 = slot.field_7874 < 9 ? slot.field_7874 : 0;
         swapHand(slot, class_1268.field_5808, false, true);
      }

   }

   public static String getCleanName(class_2561 text) {
      if (text == null) {
         return "";
      } else {
         String name = text.getString();
         return name == null ? "" : name.replaceAll("Â§[0-9a-fk-or]", "").toLowerCase();
      }
   }
}

