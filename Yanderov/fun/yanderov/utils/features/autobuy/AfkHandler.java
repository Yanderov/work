package fun.Yanderov.utils.features.autobuy;

import fun.Yanderov.utils.math.time.TimerUtil;
import net.minecraft.class_310;
import net.minecraft.class_315;

public class AfkHandler {
   private TimerUtil afkActionTimer = TimerUtil.create();
   private boolean wasInAfk = false;
   private boolean performingAfkAction = false;
   private int afkActionStep = 0;

   public void resetTimers() {
      this.afkActionTimer.resetCounter();
      this.wasInAfk = false;
      this.performingAfkAction = false;
      this.afkActionStep = 0;
   }

   public void handle(class_310 mc) {
      boolean currentlyInAfk = this.isInAfkMode(mc);
      if (currentlyInAfk && !this.wasInAfk) {
         this.performingAfkAction = true;
         this.afkActionStep = 0;
         this.afkActionTimer.resetCounter();
      }

      this.wasInAfk = currentlyInAfk;
      if (this.performingAfkAction && this.afkActionTimer.hasTimeElapsed(100L)) {
         this.processAfkAction(mc);
      }

   }

   private void processAfkAction(class_310 mc) {
      switch (this.afkActionStep) {
         case 0:
            mc.field_1690.field_1894.method_23481(true);
            ++this.afkActionStep;
            this.afkActionTimer.resetCounter();
            break;
         case 1:
            mc.field_1690.field_1894.method_23481(false);
            ++this.afkActionStep;
            this.afkActionTimer.resetCounter();
            break;
         case 2:
            float currentYaw = mc.field_1724.method_36454();
            mc.field_1724.method_36456(currentYaw + 45.0F);
            ++this.afkActionStep;
            this.afkActionTimer.resetCounter();
            break;
         case 3:
            this.performingAfkAction = false;
            this.afkActionStep = 0;
      }

   }

   private boolean isInAfkMode(class_310 mc) {
      return mc.field_1705 == null ? false : mc.field_1705.method_1740().field_2060.values().stream().map((bar) -> bar.method_5414().getString().toLowerCase()).anyMatch((text) -> text.contains("afk"));
   }

   public void resetMovementKeys(class_315 options) {
      if (options != null) {
         options.field_1894.method_23481(false);
         options.field_1881.method_23481(false);
         options.field_1913.method_23481(false);
         options.field_1849.method_23481(false);
      }

   }

   public boolean isPerformingAction() {
      return this.performingAfkAction;
   }
}

