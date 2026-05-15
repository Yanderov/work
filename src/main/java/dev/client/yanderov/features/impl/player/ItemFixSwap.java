package dev.client.yanderov.features.impl.player;

import dev.client.yanderov.events.keyboard.HotBarScrollEvent;
import dev.client.yanderov.events.player.HotBarUpdateEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.events.render.ItemRendererEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.interactions.inv.InventoryTask;
import java.util.Objects;
import net.minecraft.class_1268;
import net.minecraft.class_1799;
import net.minecraft.class_2868;

public class ItemFixSwap extends Module {
   private boolean lockActive = false;
   private int lockSlot = -1;
   private int deferredSlot = -1;
   private int lastSentSlot = -1;
   private class_1799 renderStack = null;
   private boolean pendingWorldApply = false;
   private Object lastWorldRef = null;
   private int lastServerSlot = -1;

   public ItemFixSwap() {
      super("ItemFixSwap", "ItemFixSwap", ModuleCategory.PLAYER);
   }

   @EventHandler
   public void onItemRenderer(ItemRendererEvent e) {
      if (this.lockActive && e.getHand() == class_1268.field_5808 && mc.field_1724 != null && Objects.equals(mc.field_1724, e.getPlayer()) && this.renderStack != null) {
         e.setStack(this.renderStack);
      }

   }

   @EventHandler
   public void onHotBarUpdate(HotBarUpdateEvent e) {
      e.cancel();
      if (mc.field_1724 != null && !this.lockActive && this.lastServerSlot != -1) {
         mc.field_1724.method_31548().field_7545 = this.lastServerSlot;
      }

   }

   @EventHandler
   public void onHotBarScroll(HotBarScrollEvent e) {
      if (this.shouldLock()) {
         e.cancel();
      }

   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.lastWorldRef != mc.field_1687) {
         this.lastWorldRef = mc.field_1687;
         if (mc.field_1687 != null) {
            this.pendingWorldApply = true;
            this.lastSentSlot = -1;
            this.lastServerSlot = mc.field_1724 != null ? mc.field_1724.method_31548().field_7545 : 0;
         }
      }

      if (mc.field_1687 != null && mc.field_1724 != null) {
         if (this.pendingWorldApply) {
            if (this.lastServerSlot == -1) {
               this.lastServerSlot = mc.field_1724.method_31548().field_7545;
            }

            if (this.deferredSlot != -1) {
               this.setSelectedSlot(this.deferredSlot, true);
            } else {
               this.ensureSelectedSynced(true);
            }

            this.pendingWorldApply = false;
         }

         boolean usingMain = this.isUsingMainhand();
         int currentSelected = mc.field_1724.method_31548().field_7545;
         if (usingMain) {
            if (!this.lockActive) {
               this.lockActive = true;
               this.lockSlot = this.clamp(currentSelected);
               this.renderStack = mc.field_1724.method_6047().method_7972();
               this.sendSelected(this.lockSlot, true);
            } else if (currentSelected != this.lockSlot) {
               this.deferredSlot = this.clamp(currentSelected);
               mc.field_1724.method_31548().field_7545 = this.lockSlot;
            }
         } else if (this.lockActive) {
            this.lockActive = false;
            if (this.deferredSlot != -1 && this.deferredSlot != this.lockSlot) {
               this.setSelectedSlot(this.deferredSlot, true);
               InventoryTask.updateSlots();
            } else {
               mc.field_1724.method_31548().field_7545 = this.lastServerSlot;
            }

            this.lockSlot = -1;
            this.deferredSlot = -1;
            this.renderStack = null;
         }

      }
   }

   private boolean shouldLock() {
      return this.lockActive || this.isUsingMainhand();
   }

   private boolean isUsingMainhand() {
      return mc.field_1724 != null && mc.field_1724.method_6115() && mc.field_1724.method_6058() == class_1268.field_5808;
   }

   private void ensureSelectedSynced(boolean force) {
      if (mc.field_1724 != null) {
         int s = this.clamp(mc.field_1724.method_31548().field_7545);
         if (force || this.lastSentSlot != s) {
            this.sendSelected(s, true);
         }

      }
   }

   private void setSelectedSlot(int idx, boolean force) {
      if (mc.field_1724 != null) {
         idx = this.clamp(idx);
         mc.field_1724.method_31548().field_7545 = idx;
         this.sendSelected(idx, true);
      }
   }

   private void sendSelected(int idx, boolean force) {
      if (mc.field_1724 != null && mc.field_1724.field_3944 != null) {
         if (force || this.lastSentSlot != idx) {
            mc.field_1724.field_3944.method_52787(new class_2868(idx));
            this.lastSentSlot = idx;
            this.lastServerSlot = idx;
         }
      }
   }

   private int clamp(int idx) {
      if (idx < 0) {
         return 0;
      } else {
         return idx > 8 ? 8 : idx;
      }
   }
}

