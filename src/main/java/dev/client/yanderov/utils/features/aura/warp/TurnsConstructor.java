package dev.client.yanderov.utils.features.aura.warp;

import dev.client.yanderov.features.impl.combat.Aura;
import dev.client.yanderov.features.impl.player.AutoPilot;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.features.aura.rotations.constructor.RotateConstructor;
import dev.client.yanderov.utils.features.aura.utils.MathAngle;
import net.minecraft.class_1297;
import net.minecraft.class_243;

public class TurnsConstructor implements QuickImports {
   private final Turns angle;
   private final class_243 vec3d;
   private final class_1297 entity;
   private final RotateConstructor angleSmooth;
   private final int ticksUntilReset;
   private final float resetThreshold;
   public final boolean moveCorrection;
   public final boolean freeCorrection;
   public final boolean changeLook;

   public Turns nextRotation(Turns fromAngle, boolean isResetting) {
      return isResetting ? this.angleSmooth.limitAngleChange(fromAngle, MathAngle.fromVec2f(mc.field_1724.method_5802())) : this.angleSmooth.limitAngleChange(fromAngle, this.angle, this.vec3d, this.entity);
   }

   public Turns getAngle() {
      return this.angle;
   }

   public class_243 getVec3d() {
      return this.vec3d;
   }

   public class_1297 getEntity() {
      return this.entity;
   }

   public RotateConstructor getAngleSmooth() {
      return this.angleSmooth;
   }

   public int getTicksUntilReset() {
      return this.ticksUntilReset;
   }

   public float getResetThreshold() {
      return this.resetThreshold;
   }

   public boolean isMoveCorrection() {
      return this.moveCorrection;
   }

   public boolean isChangeLook() {
      return this.changeLook;
   }

   public TurnsConstructor(Turns angle, class_243 vec3d, class_1297 entity, RotateConstructor angleSmooth, int ticksUntilReset, float resetThreshold, boolean moveCorrection, boolean freeCorrection) {
      this.changeLook = AutoPilot.getInstance().isState() && AutoPilot.getInstance().target != null ? true : Aura.getInstance().isState() && Aura.getInstance().getTarget() != null && Aura.getInstance().getCorrectionType().isSelected("Change Look");
      this.angle = angle;
      this.vec3d = vec3d;
      this.entity = entity;
      this.angleSmooth = angleSmooth;
      this.ticksUntilReset = ticksUntilReset;
      this.resetThreshold = resetThreshold;
      this.moveCorrection = moveCorrection;
      this.freeCorrection = freeCorrection;
   }

   public boolean isFreeCorrection() {
      return this.freeCorrection;
   }
}

