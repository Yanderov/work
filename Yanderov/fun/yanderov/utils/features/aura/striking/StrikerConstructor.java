package fun.Yanderov.utils.features.aura.striking;

import fun.Yanderov.events.item.UsingItemEvent;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.features.aura.warp.Turns;
import java.util.List;
import net.minecraft.class_1309;
import net.minecraft.class_238;

public class StrikerConstructor implements QuickImports {
   StrikeManager attackHandler = new StrikeManager();

   public void tick() {
      this.attackHandler.tick();
   }

   public void onPacket(PacketEvent e) {
      this.attackHandler.onPacket(e);
   }

   public void onUsingItem(UsingItemEvent e) {
      this.attackHandler.onUsingItem(e);
   }

   public void performAttack(AttackPerpetratorConfigurable configurable) {
      this.attackHandler.handleAttack(configurable);
   }

   public StrikeManager getAttackHandler() {
      return this.attackHandler;
   }

   public static class AttackPerpetratorConfigurable {
      private final class_1309 target;
      private final Turns angle;
      private final float maximumRange;
      private final boolean onlyCritical;
      private final boolean shouldUnPressShield;
      private final boolean eatAndAttack;
      private final class_238 box;
      private final SelectSetting aimMode;

      public AttackPerpetratorConfigurable(class_1309 target, Turns angle, float maximumRange, List options, SelectSetting aimMode, class_238 box) {
         this.target = target;
         this.angle = angle;
         this.maximumRange = maximumRange;
         this.onlyCritical = options.contains("Only Critical");
         this.shouldUnPressShield = options.contains("UnPress Shield");
         this.eatAndAttack = options.contains("No Attack When Eat");
         this.box = box;
         this.aimMode = aimMode;
      }

      public class_1309 getTarget() {
         return this.target;
      }

      public Turns getAngle() {
         return this.angle;
      }

      public float getMaximumRange() {
         return this.maximumRange;
      }

      public boolean isOnlyCritical() {
         return this.onlyCritical;
      }

      public boolean isShouldUnPressShield() {
         return this.shouldUnPressShield;
      }

      public boolean isEatAndAttack() {
         return this.eatAndAttack;
      }

      public class_238 getBox() {
         return this.box;
      }

      public SelectSetting getAimMode() {
         return this.aimMode;
      }
   }
}

