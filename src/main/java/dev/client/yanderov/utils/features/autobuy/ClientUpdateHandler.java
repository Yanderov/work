package dev.client.yanderov.utils.features.autobuy;

import net.minecraft.class_1707;
import net.minecraft.class_1713;
import net.minecraft.class_310;
import net.minecraft.class_437;
import net.minecraft.class_476;

public class ClientUpdateHandler {
   private static class_310 mc = class_310.method_1551();

   public static void handleUpdate() {
      class_437 var1 = mc.field_1755;
      if (var1 instanceof class_476 screen) {
         int syncId = ((class_1707)screen.method_17577()).field_7763;
         mc.field_1761.method_2906(syncId, 49, 0, class_1713.field_7794, mc.field_1724);
      }

   }
}

