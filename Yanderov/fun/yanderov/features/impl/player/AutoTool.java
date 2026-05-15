package fun.Yanderov.features.impl.player;

import antidaunleak.api.annotation.Native;
import fun.Yanderov.events.block.BlockBreakingEvent;
import fun.Yanderov.events.keyboard.HotBarScrollEvent;
import fun.Yanderov.events.player.HotBarUpdateEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.events.render.ItemRendererEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import fun.Yanderov.utils.interactions.inv.InventoryTask;
import fun.Yanderov.utils.math.script.Script;
import fun.Yanderov.utils.math.time.StopWatch;
import java.util.Comparator;
import java.util.Objects;
import net.minecraft.class_1268;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_2680;

public class AutoTool extends Module {
   private final StopWatch swap = new StopWatch();
   private final StopWatch breaking = new StopWatch();
   private final Script script = new Script();
   private final Script swapBackScript = new Script();
   private class_1799 renderStack;
   private class_2338 lastBreakPos;

   public AutoTool() {
      super("AutoTool", "Auto Tool", ModuleCategory.PLAYER);
   }

   @EventHandler
   public void onItemRenderer(ItemRendererEvent e) {
      if (this.renderStack != null && e.getHand().equals(class_1268.field_5808) && Objects.equals(mc.field_1724, e.getPlayer())) {
         e.setStack(this.renderStack);
      }

   }

   @EventHandler
   public void onHotBarUpdate(HotBarUpdateEvent e) {
      if (!this.swapBackScript.isFinished()) {
         e.cancel();
      }

   }

   @EventHandler
   public void onHotBarScroll(HotBarScrollEvent e) {
      if (!this.swapBackScript.isFinished()) {
         e.cancel();
      }

   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginMutation
   )
   public void onBlockBreaking(BlockBreakingEvent e) {
      this.breaking.reset();
      this.lastBreakPos = e.blockPos();
      if (!mc.field_1724.method_7337() && this.swapBackScript.isFinished() && this.swap.finished((double)350.0F)) {
         class_1735 currentBestSlot = this.findBestTool(this.lastBreakPos);
         if (currentBestSlot != null && currentBestSlot != InventoryTask.mainHandSlot()) {
            this.renderStack = mc.field_1724.method_6047();
            InventoryTask.swapHand(currentBestSlot, class_1268.field_5808, true);
            this.swapBackScript.cleanup().addTickStep(0, () -> InventoryTask.swapHand(currentBestSlot, class_1268.field_5808, true, true));
            this.swap.reset();
         }
      }

   }

   @EventHandler
   public void onTick(TickEvent e) {
      this.script.update();
      if (!this.swapBackScript.isFinished() && this.swap.finished((double)350.0F)) {
         class_1735 currentBestSlot = this.findBestTool(this.lastBreakPos);
         if (currentBestSlot != InventoryTask.mainHandSlot() || this.breaking.finished((double)100.0F)) {
            this.script.cleanup().addTickStep(4, () -> this.renderStack = null);
            this.swapBackScript.update();
            this.swap.reset();
         }
      }

   }

   private class_1735 findBestTool(class_2338 blockPos) {
      class_2680 state = mc.field_1687.method_8320(blockPos);
      return PlayerInteractionHelper.isAir(state) ? InventoryTask.mainHandSlot() : (class_1735)InventoryTask.slots().sorted(Comparator.comparing((slot) -> slot.equals(InventoryTask.mainHandSlot()))).filter((s) -> s.method_7677().method_7924(state) != 1.0F).max(Comparator.comparingDouble((s) -> (double)s.method_7677().method_7924(state))).orElse((Object)null);
   }
}

