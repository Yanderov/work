package fun.Yanderov.features.impl.movement;

import antidaunleak.api.annotation.Native;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.impl.combat.Aura;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.math.time.StopWatch;
import java.util.Objects;
import java.util.Random;
import net.minecraft.class_243;
import net.minecraft.class_2596;

public class ElytraMotion extends Module {
   private StopWatch timer = new StopWatch();
   private class_243 targetPosition = null;
   private Random random = new Random();
   private double rotationAngle = (double)0.0F;

   public static Fly getInstance() {
      return (Fly)Instance.get(Fly.class);
   }

   public ElytraMotion() {
      super("ElytraMotion", "Elytra Motion", ModuleCategory.MOVEMENT);
      this.setup(new Setting[0]);
   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   public void onTick(TickEvent e) {
      if (this.state && mc.field_1724 != null && mc.field_1687 != null && mc.field_1724.method_6128()) {
         Aura aura = (Aura)Instance.get(Aura.class);
         if (aura.isState() && aura.isState() && aura.getTarget() != null && mc.field_1724.method_5739(aura.getTarget()) < aura.getAttackRange().getValue() - 1.0F) {
            mc.field_1724.method_18800((double)0.0F, 0.02, (double)0.0F);
         }

      }
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      Aura aura = (Aura)Instance.get(Aura.class);
      if (aura.isState() && aura.getTarget() != null && mc.field_1724.method_5739(aura.getTarget()) < aura.getAttackRange().getValue() - 1.0F) {
         class_2596 var10000 = e.getPacket();
         Objects.requireNonNull(var10000);
         class_2596 var3 = var10000;
         byte var4 = 0;
         //$FF: var4->value
         switch (var3.typeSwitch<invokedynamic>(var3, var4)) {
         }
      }

   }

   public void deactivate() {
      super.deactivate();
   }

   public StopWatch getTimer() {
      return this.timer;
   }

   public class_243 getTargetPosition() {
      return this.targetPosition;
   }

   public Random getRandom() {
      return this.random;
   }

   public double getRotationAngle() {
      return this.rotationAngle;
   }
}

