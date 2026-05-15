package fun.Yanderov.utils.features.aura.rotations.constructor;

import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.features.aura.warp.Turns;
import net.minecraft.class_1297;
import net.minecraft.class_243;

public abstract class RotateConstructor implements QuickImports {
   private final String name;

   public Turns limitAngleChange(Turns currentAngle, Turns targetAngle) {
      return this.limitAngleChange(currentAngle, targetAngle, (class_243)null, (class_1297)null);
   }

   public Turns limitAngleChange(Turns currentAngle, Turns targetAngle, class_243 vec3d) {
      return this.limitAngleChange(currentAngle, targetAngle, vec3d, (class_1297)null);
   }

   public abstract Turns limitAngleChange(Turns var1, Turns var2, class_243 var3, class_1297 var4);

   public abstract class_243 randomValue();

   public String getName() {
      return this.name;
   }

   public RotateConstructor(String name) {
      this.name = name;
   }
}

