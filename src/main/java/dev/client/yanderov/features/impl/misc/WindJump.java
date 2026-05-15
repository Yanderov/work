package dev.client.yanderov.features.impl.misc;

import dev.client.yanderov.events.keyboard.HotBarScrollEvent;
import dev.client.yanderov.events.keyboard.KeyEvent;
import dev.client.yanderov.events.player.HotBarUpdateEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.events.render.WorldRenderEvent;
import dev.client.yanderov.features.impl.render.ProjectilePrediction;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BindSetting;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.features.aura.utils.MathAngle;
import dev.client.yanderov.utils.features.aura.warp.Turns;
import dev.client.yanderov.utils.features.aura.warp.TurnsConfig;
import dev.client.yanderov.utils.features.aura.warp.TurnsConnection;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import dev.client.yanderov.utils.interactions.inv.InventoryTask;
import dev.client.yanderov.utils.math.script.Script;
import dev.client.yanderov.utils.math.task.TaskPriority;
import dev.client.yanderov.utils.math.time.StopWatch;
import java.util.List;
import net.minecraft.class_1799;
import net.minecraft.class_1802;

public class WindJump extends Module {
   private final Turns rot = new Turns(0.0F, 0.0F);
   private final BindSetting windChargeBind = new BindSetting("Ð—Ð°Ñ€ÑÐ´ Ð²ÐµÑ‚Ñ€Ð°", "Ð‘Ñ€Ð¾ÑÐ¸Ñ‚ÑŒ Ð·Ð°Ñ€ÑÐ´ Ð²ÐµÑ‚Ñ€Ð°");
   private final StopWatch stopWatch = new StopWatch();
   private final Script script = new Script();

   public WindJump() {
      super("WindJump", "Wind Jump", ModuleCategory.MISC);
      this.setup(new Setting[]{this.windChargeBind});
   }

   @EventHandler
   public void onHotBarUpdate(HotBarUpdateEvent e) {
      if (!this.script.isFinished()) {
         e.cancel();
      }

   }

   @EventHandler
   public void onHotBarScroll(HotBarScrollEvent e) {
      if (!this.script.isFinished()) {
         e.cancel();
      }

   }

   @EventHandler
   public void onKey(KeyEvent e) {
      if (e.isKeyReleased(this.windChargeBind.getKey()) && this.stopWatch.finished((double)0.0F)) {
         InventoryTask.swapAndUse(class_1802.field_49098);
      }

   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      if (PlayerInteractionHelper.isKey(this.windChargeBind)) {
         this.rot.setYaw(mc.field_1724.method_36454());
         this.rot.setPitch(90.0F);
         TurnsConnection.INSTANCE.rotateTo(this.rot, TurnsConfig.DEFAULT, TaskPriority.LOW_PRIORITY, this);
         class_1799 stack = class_1802.field_49098.method_7854();
         ProjectilePrediction.getInstance().drawPredictionInHand(e.getStack(), List.of(stack), MathAngle.cameraAngle());
      }

   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (!this.script.isFinished() && this.stopWatch.every((double)250.0F)) {
         this.script.update();
      }

   }
}

