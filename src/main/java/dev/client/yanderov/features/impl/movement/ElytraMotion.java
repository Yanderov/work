package dev.client.yanderov.features.impl.movement;

import antidaunleak.api.annotation.Native;
import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.impl.combat.Aura;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.utils.client.Instance;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.math.time.StopWatch;
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
         // TODO: Fix switch statement for var3
        if (var3 != null) {
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

