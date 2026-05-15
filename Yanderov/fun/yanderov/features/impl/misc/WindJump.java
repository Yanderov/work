package fun.Yanderov.features.impl.misc;

import fun.Yanderov.events.keyboard.HotBarScrollEvent;
import fun.Yanderov.events.keyboard.KeyEvent;
import fun.Yanderov.events.player.HotBarUpdateEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.events.render.WorldRenderEvent;
import fun.Yanderov.features.impl.render.ProjectilePrediction;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BindSetting;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.features.aura.utils.MathAngle;
import fun.Yanderov.utils.features.aura.warp.Turns;
import fun.Yanderov.utils.features.aura.warp.TurnsConfig;
import fun.Yanderov.utils.features.aura.warp.TurnsConnection;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import fun.Yanderov.utils.interactions.inv.InventoryTask;
import fun.Yanderov.utils.math.script.Script;
import fun.Yanderov.utils.math.task.TaskPriority;
import fun.Yanderov.utils.math.time.StopWatch;
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

