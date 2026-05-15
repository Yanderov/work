package fun.Yanderov.features.impl.player;

import antidaunleak.api.annotation.Native;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.features.aura.warp.Turns;
import fun.Yanderov.utils.features.aura.warp.TurnsConfig;
import fun.Yanderov.utils.features.aura.warp.TurnsConnection;
import fun.Yanderov.utils.math.task.TaskPriority;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.class_1542;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2828;
import net.minecraft.class_310;
import net.minecraft.class_3532;

public class AutoPilot extends Module {
   private static final class_310 mc = class_310.method_1551();
   public class_1542 target;
   private float lastYaw;
   private float lastPitch;
   private float targetYaw;
   private float targetPitch;
   Turns rot = new Turns(0.0F, 0.0F);

   public AutoPilot() {
      super("AutoPilot", "Auto Pilot", ModuleCategory.MISC);
   }

   public static AutoPilot getInstance() {
      return (AutoPilot)Instance.get(AutoPilot.class);
   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   public void onTick(TickEvent event) {
      if (mc.field_1724 != null && mc.field_1687 != null && mc.method_1562() != null) {
         this.target = this.findTarget();
         if (this.target != null) {
            double dx = this.target.method_19538().method_10216() - mc.field_1724.method_19538().method_10216();
            double dy = this.target.method_19538().method_10214() - (mc.field_1724.method_19538().method_10214() + (double)mc.field_1724.method_18381(mc.field_1724.method_18376()));
            double dz = this.target.method_19538().method_10215() - mc.field_1724.method_19538().method_10215();
            this.targetYaw = (float)(Math.atan2(dz, dx) * (double)180.0F / Math.PI - (double)90.0F);
            this.targetPitch = (float)(-Math.atan2(dy, Math.sqrt(dx * dx + dz * dz)) * (double)180.0F / Math.PI);
            float maxRotation = 1024.0F;
            float yawDiff = class_3532.method_15393(this.targetYaw - this.lastYaw);
            float yawStep = class_3532.method_15363(yawDiff, -maxRotation, maxRotation);
            this.lastYaw += yawStep;
            float pitchDiff = class_3532.method_15393(this.targetPitch - this.lastPitch);
            float pitchStep = class_3532.method_15363(pitchDiff, -maxRotation, maxRotation);
            this.lastPitch += pitchStep;
            mc.field_1724.method_36456(this.lastYaw);
            mc.field_1724.method_36457(this.lastPitch);
            this.rot.setYaw(this.lastYaw);
            this.rot.setPitch(this.lastPitch);
            TurnsConnection.INSTANCE.rotateTo(this.rot, TurnsConfig.DEFAULT, TaskPriority.HIGH_IMPORTANCE_1, this);
         } else {
            this.lastYaw = mc.field_1724.method_36454();
            this.lastPitch = mc.field_1724.method_36455();
         }

      } else {
         this.target = null;
      }
   }

   private class_1542 findTarget() {
      List<class_1542> items = (List)mc.field_1687.method_8390(class_1542.class, mc.field_1724.method_5829().method_1014((double)50.0F), (e) -> e.method_5805() && this.isValidItem(e)).stream().sorted(Comparator.comparingDouble((e) -> mc.field_1724.method_5858(e))).collect(Collectors.toList());
      return items.isEmpty() ? null : (class_1542)items.get(0);
   }

   private boolean isValidItem(class_1542 item) {
      class_1799 stack = item.method_6983();
      return stack.method_7909() == class_1802.field_8849 || stack.method_7909() == class_1802.field_8575 || stack.method_7909() == class_1802.field_8367 || stack.method_7909().toString().contains("_spawn_egg");
   }

   public void deactivate() {
      this.target = null;
      if (mc.field_1724 != null) {
         mc.method_1562().method_52787(new class_2828.class_2830(mc.field_1724.method_19538().method_10216(), mc.field_1724.method_19538().method_10214(), mc.field_1724.method_19538().method_10215(), mc.field_1724.method_36454(), mc.field_1724.method_36455(), mc.field_1724.method_24828(), false));
      }

   }
}

