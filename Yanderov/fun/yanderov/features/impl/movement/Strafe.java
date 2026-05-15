package fun.Yanderov.features.impl.movement;

import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.impl.combat.Aura;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.features.aura.warp.Turns;
import fun.Yanderov.utils.features.aura.warp.TurnsConfig;
import fun.Yanderov.utils.features.aura.warp.TurnsConnection;
import fun.Yanderov.utils.interactions.simulate.Simulations;
import fun.Yanderov.utils.math.task.TaskPriority;
import net.minecraft.class_310;

public class Strafe extends Module {
   private static final class_310 mc = class_310.method_1551();
   public SelectSetting mode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ñ‚Ð¸Ð¿ ÑÑ‚Ñ€ÐµÐ¹Ñ„Ð¾Ð²")).value("Matrix", "Grim").selected("Matrix");
   SliderSettings speed = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð´Ð»Ñ ÑÑ‚Ñ€ÐµÐ¹Ñ„Ð°")).setValue(0.42F).range(0.0F, 1.0F).visible(() -> this.mode.isSelected("Matrix"));
   private float lastYaw;
   private float lastPitch;
   private final Turns rot = new Turns(0.0F, 0.0F);

   public Strafe() {
      super("Strafe", "Strafe", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.mode, this.speed});
   }

   public static Strafe getInstance() {
      return (Strafe)Instance.get(Strafe.class);
   }

   @EventHandler
   public void onTick(TickEvent event) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         boolean moving = Simulations.hasPlayerMovement();
         float yaw = mc.field_1724.method_36454();
         if (this.mode.isSelected("Matrix")) {
            if (moving) {
               yaw = Simulations.moveYaw(mc.field_1724.method_36454());
               double motion = (double)(this.speed.getValue() * 1.5F);
               Simulations.setVelocity(motion);
            } else {
               Simulations.setVelocity((double)0.0F);
            }

            mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, mc.field_1724.method_18798().field_1351, mc.field_1724.method_18798().field_1350);
         } else if (this.mode.isSelected("Grim") && moving) {
            TurnsConfig.freeCorrection = true;
            yaw = Simulations.moveYaw(mc.field_1724.method_36454());
            this.rot.setYaw(yaw);
            this.rot.setPitch(mc.field_1724.method_36455());
            if (Aura.getInstance().getTarget() == null) {
               TurnsConnection.INSTANCE.rotateTo(this.rot, TurnsConfig.DEFAULT, TaskPriority.LOW_PRIORITY, this);
            }
         }

         this.lastYaw = yaw;
         this.lastPitch = 0.0F;
      }
   }

   public void activate() {
      super.activate();
      this.lastYaw = mc.field_1724 != null ? mc.field_1724.method_36454() : 0.0F;
      this.lastPitch = mc.field_1724 != null ? mc.field_1724.method_36455() : 0.0F;
   }
}

