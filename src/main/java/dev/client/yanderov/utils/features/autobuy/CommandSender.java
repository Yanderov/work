package dev.client.yanderov.utils.features.autobuy;

import java.time.Instant;
import java.util.BitSet;
import net.minecraft.class_2797;
import net.minecraft.class_310;
import net.minecraft.class_746;
import net.minecraft.class_7469;
import net.minecraft.class_7635;

public class CommandSender {
   private static class_310 mc = class_310.method_1551();

   public static void sendCommand(class_746 player, String command) {
      if (player != null && player.field_3944 != null) {
         player.field_3944.method_52787(new class_2797(command, Instant.now(), 0L, (class_7469)null, new class_7635.class_7636(0, new BitSet())));
      }

   }

   public static void handleServerSwitch(String cmd) {
      if (mc.field_1724 != null && mc.field_1724.field_3944 != null) {
         mc.field_1724.field_3944.method_52787(new class_2797(cmd, Instant.now(), 0L, (class_7469)null, new class_7635.class_7636(0, new BitSet())));
         ServerSwitchHandler.setWaitingForServerLoad(true);
         ServerSwitchHandler.setServerSwitchTime(System.currentTimeMillis());
      }

   }

   public static void openAuction() {
      if (mc.field_1724 != null && mc.field_1724.field_3944 != null) {
         mc.field_1724.field_3944.method_52787(new class_2797("/ah", Instant.now(), 0L, (class_7469)null, new class_7635.class_7636(0, new BitSet())));
      }

   }
}

