package dev.client.yanderov.commands.defaults;

import dev.client.yanderov.utils.client.managers.api.command.Command;
import dev.client.yanderov.utils.client.managers.api.command.argument.IArgConsumer;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import dev.client.yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2828;

public class VClipCommand extends Command {
   public VClipCommand() {
      super("vclip", "vc");
   }

   public void execute(String label, IArgConsumer args) throws CommandException {
      if (mc != null && mc.field_1724 != null && mc.field_1687 != null && mc.method_1562() != null) {
         args.requireMin(1);
         String first = args.getString();
         class_2338 playerPos = class_2338.method_49638(mc.field_1724.method_19538());
         double yOffset;
         switch (first.toLowerCase()) {
            case "up":
               yOffset = this.findOffset(playerPos, true);
               break;
            case "down":
               yOffset = this.findOffset(playerPos, false);
               break;
            default:
               try {
                  yOffset = Double.parseDouble(first);
               } catch (NumberFormatException var10) {
                  this.logDirect("Â§c" + first + " Â§7Ð½Ðµ ÑÐ²Ð»ÑÐµÑ‚ÑÑ Ñ‡Ð¸ÑÐ»Ð¾Ð¼!");
                  return;
               }
         }

         if (yOffset == (double)0.0F) {
            this.logDirect("Â§cÐÐµ ÑƒÐ´Ð°Ð»Ð¾ÑÑŒ Ð²Ñ‹Ð¿Ð¾Ð»Ð½Ð¸Ñ‚ÑŒ Ñ‚ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð°Ñ†Ð¸ÑŽ.");
         } else {
            class_243 cur = mc.field_1724.method_19538();
            class_243 target = new class_243(cur.field_1352, cur.field_1351 + yOffset, cur.field_1350);
            this.teleportLikeClickTp(target.field_1352, target.field_1351, target.field_1350);
            String unit = Math.abs(yOffset) >= (double)2.0F ? "Ð±Ð»Ð¾ÐºÐ¾Ð²" : "Ð±Ð»Ð¾Ðº";
            this.logDirect(String.format("Ð¢ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð¸Ñ€Ð¾Ð²Ð°Ð½Ð¾ Ð½Ð° %.2f %s Ð¿Ð¾ Ð²ÐµÑ€Ñ‚Ð¸ÐºÐ°Ð»Ð¸", yOffset, unit));
         }
      }
   }

   private double findOffset(class_2338 playerPos, boolean toUp) {
      int start = toUp ? 3 : -1;
      int end = toUp ? 255 : -255;
      int step = toUp ? 1 : -1;

      for(int i = start; i != end; i += step) {
         class_2338 p = playerPos.method_10086(i);
         if (mc.field_1687.method_8320(p).method_26215()) {
            return (double)(i + (toUp ? 1 : -1));
         }

         if (!toUp && mc.field_1687.method_8320(p).method_27852(class_2246.field_9987)) {
            this.logDirect("Â§cÐ¢ÑƒÑ‚ Ð½ÐµÐ»ÑŒÐ·Ñ Ñ‚ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒÑÑ Ð¿Ð¾Ð´ Ð·ÐµÐ¼Ð»ÑŽ.");
            return (double)0.0F;
         }
      }

      return (double)0.0F;
   }

   private void teleportLikeClickTp(double x, double y, double z) {
      class_243 target = new class_243(x, y, z);
      double distance = mc.field_1724.method_19538().method_1022(target);
      int packetCount = (int)Math.min(Math.abs(distance / (double)11.0F) + (double)1.0F, (double)19.0F);

      for(int i = 0; i < packetCount; ++i) {
         mc.method_1562().method_52787(new class_2828.class_5911(false, false));
      }

      mc.method_1562().method_52787(new class_2828.class_2829(x, y, z, false, false));
      mc.field_1724.method_5814(x, y, z);
   }

   public Stream tabComplete(String label, IArgConsumer args) throws CommandException {
      return args.hasAny() && !args.hasExactly(1) ? Stream.empty() : (new TabCompleteHelper()).append("up", "down", "10", "-10").filterPrefix(args.hasAny() ? args.getString() : "").stream();
   }

   public String getShortDesc() {
      return "Ð¢ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð¸Ñ€ÑƒÐµÑ‚ Ð²Ð²ÐµÑ€Ñ…/Ð²Ð½Ð¸Ð· Ð¿Ð¾ Ð²ÐµÑ€Ñ‚Ð¸ÐºÐ°Ð»Ð¸";
   }

   public List getLongDesc() {
      return Arrays.asList("ÐŸÐµÑ€ÐµÐ¼ÐµÑ‰Ð°ÐµÑ‚ Ð²Ð°Ñ Ð¿Ð¾ Ð¾ÑÐ¸ Y Ð½Ð° ÑƒÐºÐ°Ð·Ð°Ð½Ð½Ð¾Ðµ Ñ€Ð°ÑÑÑ‚Ð¾ÑÐ½Ð¸Ðµ Ð¸Ð»Ð¸ Ð´Ð¾ Ð±Ð»Ð¸Ð¶Ð°Ð¹ÑˆÐµÐ³Ð¾ ÑÐ²Ð¾Ð±Ð¾Ð´Ð½Ð¾Ð³Ð¾ Ð¿Ñ€Ð¾ÑÑ‚Ñ€Ð°Ð½ÑÑ‚Ð²Ð°.", "", "> vclip up - Ð¢ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð°Ñ†Ð¸Ñ Ð²Ð²ÐµÑ€Ñ… Ð´Ð¾ Ð±Ð»Ð¸Ð¶Ð°Ð¹ÑˆÐµÐ³Ð¾ ÑÐ²Ð¾Ð±Ð¾Ð´Ð½Ð¾Ð³Ð¾ Ð¿Ñ€Ð¾ÑÑ‚Ñ€Ð°Ð½ÑÑ‚Ð²Ð°", "> vclip down - Ð¢ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð°Ñ†Ð¸Ñ Ð²Ð½Ð¸Ð· Ð´Ð¾ Ð±Ð»Ð¸Ð¶Ð°Ð¹ÑˆÐµÐ³Ð¾ ÑÐ²Ð¾Ð±Ð¾Ð´Ð½Ð¾Ð³Ð¾ Ð¿Ñ€Ð¾ÑÑ‚Ñ€Ð°Ð½ÑÑ‚Ð²Ð°", "> vclip <distance> - Ð¢ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð°Ñ†Ð¸Ñ Ð½Ð° ÑƒÐºÐ°Ð·Ð°Ð½Ð½Ð¾Ðµ Ñ€Ð°ÑÑÑ‚Ð¾ÑÐ½Ð¸Ðµ");
   }
}

