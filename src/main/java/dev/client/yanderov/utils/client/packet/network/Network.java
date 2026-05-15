package dev.client.yanderov.utils.client.packet.network;

import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import dev.client.yanderov.utils.math.time.StopWatch;
import java.util.Objects;
import net.minecraft.class_2596;
import net.minecraft.class_266;
import net.minecraft.class_268;
import net.minecraft.class_269;
import net.minecraft.class_2761;
import net.minecraft.class_3532;
import net.minecraft.class_8646;
import net.minecraft.class_9011;
import org.apache.commons.lang3.StringUtils;

public final class Network implements QuickImports {
   private static final StopWatch pvpWatch = new StopWatch();
   public static String server = "Vanilla";
   public static float TPS = 20.0F;
   public static long timestamp;
   public static int anarchy;
   public static boolean pvpEnd;

   public static void tick() {
      anarchy = getAnarchyMode();
      server = getServer();
      pvpEnd = inPvpEnd();
      if (inPvp()) {
         pvpWatch.reset();
      }

   }

   public static void packet(PacketEvent e) {
      class_2596 var10000 = e.getPacket();
      Objects.requireNonNull(var10000);
      class_2596 var1 = var10000;
      byte var2 = 0;
      //$FF: var2->value
      //0->net/minecraft/class_2761
      // TODO: Fix switch statement for var1
        if (var1 != null) {
            // // case 0:
            // class_2761 time = (class_2761)var1;
            // long nanoTime = System.nanoTime();
            // float maxTPS = 20.0F;
            // float rawTPS = maxTPS * (1.0E9F / (float)(nanoTime - timestamp));
            // TPS = class_3532.method_15363(rawTPS, 0.0F, maxTPS);
            // timestamp = nanoTime;
            // // default:
      }
   }

   public static String getServer() {
      if (!PlayerInteractionHelper.nullCheck() && mc.method_1562() != null && mc.method_1562().method_45734() != null && mc.method_1562().method_52790() != null) {
         String serverIp = mc.method_1562().method_45734().field_3761.toLowerCase();
         String brand = mc.method_1562().method_52790().toLowerCase();
         if (brand.contains("botfilter")) {
            return "FunTime";
         } else if (brand.contains("Â§6spookyÂ§ccore")) {
            return "SpookyTime";
         } else if (!serverIp.contains("funtime") && !serverIp.contains("skytime") && !serverIp.contains("space-times") && !serverIp.contains("funsky")) {
            if (!brand.contains("holyworld") && !brand.contains("vk.com/idwok")) {
               if (serverIp.contains("reallyworld")) {
                  return "ReallyWorld";
               } else {
                  return serverIp.contains("gulpvp") ? "GulPvP" : "Vanilla";
               }
            } else {
               return "HolyWorld";
            }
         } else {
            return "CopyTime";
         }
      } else {
         return "Vanilla";
      }
   }

   private static int getAnarchyMode() {
      class_269 scoreboard = mc.field_1687.method_8428();
      class_266 objective = scoreboard.method_1189(class_8646.field_45157);
      switch (server) {
         case "FunTime":
            if (objective != null) {
               String[] string = objective.method_1114().getString().split("-");
               if (string.length > 1) {
                  return Integer.parseInt(string[1]);
               }
            }
            break;
         case "HolyWorld":
            for(class_9011 scoreboardEntry : scoreboard.method_1184(objective)) {
               String text = class_268.method_1142(scoreboard.method_1164(scoreboardEntry.comp_2127()), scoreboardEntry.method_55387()).getString();
               if (!text.isEmpty()) {
                  String string = StringUtils.substringBetween(text, "#", " -â—†-");
                  if (string != null && !string.isEmpty()) {
                     return Integer.parseInt(string.replace(" (1.20)", ""));
                  }
               }
            }
      }

      return -1;
   }

   public static boolean isPvp() {
      return !pvpWatch.finished((double)500.0F);
   }

   private static boolean inPvp() {
      return mc.field_1705.method_1740().field_2060.values().stream().map((c) -> c.method_5414().getString().toLowerCase()).anyMatch((s) -> s.contains("pvp") || s.contains("Ð¿Ð²Ð¿"));
   }

   private static boolean inPvpEnd() {
      return mc.field_1705.method_1740().field_2060.values().stream().map((c) -> c.method_5414().getString().toLowerCase()).anyMatch((s) -> (s.contains("pvp") || s.contains("Ð¿Ð²Ð¿")) && (s.contains("0") || s.contains("1")));
   }

   public static String getWorldType() {
      return mc.field_1687.method_27983().method_29177().method_12832();
   }

   public static boolean isCopyTime() {
      return server.equals("CopyTime") || server.equals("SpookyTime") || server.equals("FunTime");
   }

   public static boolean isFunTime() {
      return server.equals("FunTime");
   }

   public static boolean isReallyWorld() {
      return server.equals("ReallyWorld");
   }

   public static boolean isGulPvP() {
      return server.equals("GulPvP");
   }

   public static boolean isHolyWorld() {
      return server.equals("HolyWorld");
   }

   public static boolean isSpookyTime() {
      return server.equals("SpookyTime");
   }

   public static boolean isAresMine() {
      return server.equals("aresmine");
   }

   public static boolean isVanilla() {
      return server.equals("Vanilla");
   }

   private Network() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static int getAnarchy() {
      return anarchy;
   }

   public static boolean isPvpEnd() {
      return pvpEnd;
   }
}

