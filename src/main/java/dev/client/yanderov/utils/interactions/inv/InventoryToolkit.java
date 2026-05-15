package dev.client.yanderov.utils.interactions.inv;

import dev.client.yanderov.utils.display.interfaces.QuickImports;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_1713;
import net.minecraft.class_1743;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2596;
import net.minecraft.class_2868;
import net.minecraft.class_7204;

public final class InventoryToolkit implements QuickImports {
   private static int cachedSlot = -1;

   public static int getItemSlot(class_1792 input) {
      for(class_1799 stack : mc.field_1724.method_31548().field_7548) {
         if (stack.method_7909() == input && stack.method_7919() < 430) {
            return -2;
         }
      }

      int slot = -1;

      for(int i = 0; i < 36; ++i) {
         class_1799 stack = mc.field_1724.method_31548().method_5438(i);
         if (stack.method_7909() == input && stack.method_7919() < 430) {
            slot = i;
            break;
         }
      }

      if (slot < 9 && slot != -1) {
         slot += 36;
      }

      return slot;
   }

   public static void moveTo(int syncId, class_1799 stack, int slot) {
      if (!Objects.isNull(stack)) {
         mc.field_1761.method_2906(syncId, mc.field_1724.method_31548().method_7395(stack), 0, class_1713.field_7790, mc.field_1724);
         mc.field_1761.method_2906(syncId, slot, 0, class_1713.field_7790, mc.field_1724);
      }
   }

   public static InventoryResult findInInventory(Searcher searcher) {
      if (mc.field_1724 != null) {
         for(class_1799 stack : mc.field_1724.method_31548().field_7548) {
            if (searcher.isValid(stack) && stack.method_7919() < 430) {
               return new InventoryResult(-2, true, stack);
            }
         }

         for(int i = 36; i >= 0; --i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (searcher.isValid(stack) && stack.method_7919() < 430) {
               if (i < 9) {
                  i += 36;
               }

               return new InventoryResult(i, true, stack);
            }
         }
      }

      return InventoryResult.notFound();
   }

   public static InventoryResult findItemInInventory(List items) {
      return findInInventory((stack) -> items.contains(stack.method_7909()));
   }

   public static InventoryResult findItemInInventory(class_1792... items) {
      return findItemInInventory(Arrays.asList(items));
   }

   public static int getAxe() {
      for(int i = 0; i < 9; ++i) {
         class_1799 stack = mc.field_1724.method_31548().method_5438(i);
         if (stack.method_7909() instanceof class_1743) {
            return i;
         }
      }

      return -1;
   }

   public static InventoryResult findItemInHotBar(class_1792 item) {
      if (mc.field_1724 == null) {
         return InventoryResult.notFound();
      } else {
         for(int i = 0; i < 9; ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7909() == item && stack.method_7919() < 430) {
               return new InventoryResult(i, true, stack);
            }
         }

         return InventoryResult.notFound();
      }
   }

   public static InventoryResult findItemInInventory(class_1792 item) {
      if (mc.field_1724 == null) {
         return InventoryResult.notFound();
      } else {
         for(class_1799 stack : mc.field_1724.method_31548().field_7548) {
            if (stack.method_7909() == item && stack.method_7919() < 430) {
               return new InventoryResult(-2, true, stack);
            }
         }

         for(int i = 36; i >= 0; --i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7909() == item && stack.method_7919() < 430) {
               if (i < 9) {
                  i += 36;
               }

               return new InventoryResult(i, true, stack);
            }
         }

         return InventoryResult.notFound();
      }
   }

   public static InventoryResult findInHotBar(Searcher searcher) {
      if (mc.field_1724 != null) {
         for(int i = 0; i < 9; ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (searcher.isValid(stack) && stack.method_7919() < 430) {
               return new InventoryResult(i, true, stack);
            }
         }
      }

      return InventoryResult.notFound();
   }

   public static InventoryResult findItemInHotBar(List items) {
      return findInHotBar((stack) -> items.contains(stack.method_7909()));
   }

   public static InventoryResult findItemInHotBar(class_1792... items) {
      return findItemInHotBar(Arrays.asList(items));
   }

   public static void saveSlot() {
      cachedSlot = mc.field_1724.method_31548().field_7545;
   }

   public static void returnSlot() {
      if (cachedSlot != -1) {
         switchTo(cachedSlot);
      }

      cachedSlot = -1;
   }

   public static void switchTo(int slot) {
      if (mc.field_1724 != null && mc.method_1562() != null) {
         if (mc.field_1724.method_31548().field_7545 != slot) {
            mc.field_1724.method_31548().field_7545 = slot;
         }
      }
   }

   public static void switchToSilent(int slot) {
      if (mc.field_1724 != null && mc.method_1562() != null) {
         mc.method_1562().method_52787(new class_2868(slot));
      }
   }

   public static void sendSequencedPacket(class_7204 packetCreator) {
      if (mc.method_1562() != null && mc.field_1687 != null) {
         mc.method_1562().method_52787(packetCreator.predict(0));
      }
   }

   public static void sendPacket(class_2596 packet) {
      if (mc.method_1562() != null) {
         mc.method_1562().method_52787(packet);
      }
   }

   public static void clickSlot(int id) {
      if (id != -1 && mc.field_1761 != null && mc.field_1724 != null) {
         mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, id, 0, class_1713.field_7790, mc.field_1724);
      }
   }

   public static void clickSlot(int id, class_1713 type) {
      if (id != -1 && mc.field_1761 != null && mc.field_1724 != null) {
         mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, id, 0, type, mc.field_1724);
      }
   }

   public static void clickSlot(int id, int button, class_1713 type) {
      if (id != -1 && mc.field_1761 != null && mc.field_1724 != null) {
         mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, id, button, type, mc.field_1724);
      }
   }

   public static class_1799 byItem(class_1792 item) {
      for(int i = 0; i < mc.field_1724.method_31548().method_5439(); ++i) {
         class_1799 itemStack = mc.field_1724.method_31548().method_5438(i);
         if (itemStack.method_7909().equals(item) && itemStack.method_7919() < 430) {
            return itemStack;
         }
      }

      return null;
   }

   public static boolean quickMoveFromTo(int from, int to) {
      if (from != -1 && to != -1 && mc.field_1761 != null && mc.field_1724 != null) {
         mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, from, 0, class_1713.field_7790, mc.field_1724);
         mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, to, 0, class_1713.field_7790, mc.field_1724);
         mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, from, 0, class_1713.field_7790, mc.field_1724);
         return true;
      } else {
         return false;
      }
   }

   public static int getSlotWithStack(class_1799 stack) {
      if (stack != null && !stack.method_7960()) {
         for(int i = 0; i < mc.field_1724.method_31548().method_5439(); ++i) {
            class_1799 invStack = mc.field_1724.method_31548().method_5438(i);
            if (class_1799.method_7973(invStack, stack)) {
               return i;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   public interface Searcher {
      boolean isValid(class_1799 var1);
   }
}

