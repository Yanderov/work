package fun.Yanderov.commands.defaults;

import fun.Yanderov.utils.client.managers.api.command.Command;
import fun.Yanderov.utils.client.managers.api.command.argument.IArgConsumer;
import fun.Yanderov.utils.client.managers.api.command.exception.CommandException;
import fun.Yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.class_243;
import net.minecraft.class_2828;

public class HClipCommand extends Command {
   public HClipCommand() {
      super("hclip", "hc");
   }

   public void execute(String label, IArgConsumer args) throws CommandException {
      if (mc != null && mc.field_1724 != null && mc.field_1687 != null && mc.method_1562() != null) {
         args.requireExactly(1);
         String distStr = args.getString();

         double blocks;
         try {
            blocks = Double.parseDouble(distStr);
         } catch (NumberFormatException var19) {
            this.logDirect("Â§cÐŸÐ¾Ð¶Ð°Ð»ÑƒÐ¹ÑÑ‚Ð°, Ð²Ð²ÐµÐ´Ð¸Ñ‚Ðµ Ñ‡Ð¸ÑÐ»Ð¾ Ð´Ð»Ñ ÑÑ‚Ð¾Ð¹ ÐºÐ¾Ð¼Ð°Ð½Ð´Ñ‹.");
            return;
         }

         double yawRad = Math.toRadians((double)mc.field_1724.method_36454());
         double dx = -Math.sin(yawRad) * blocks;
         double dz = Math.cos(yawRad) * blocks;
         double x = mc.field_1724.method_23317() + dx;
         double y = mc.field_1724.method_23318();
         double z = mc.field_1724.method_23321() + dz;
         this.teleportLikeClickTp(x, y, z);
         String unit = Math.abs(blocks) >= (double)2.0F ? "Ð±Ð»Ð¾ÐºÐ¾Ð²" : "Ð±Ð»Ð¾Ðº";
         this.logDirect(String.format("Ð¢ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð¸Ñ€Ð¾Ð²Ð°Ð½Ð¾ Ð½Ð° %.2f %s Ð¿Ð¾ Ð³Ð¾Ñ€Ð¸Ð·Ð¾Ð½Ñ‚Ð°Ð»Ð¸", blocks, unit));
      }
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
      return args.hasAny() && !args.hasExactly(1) ? Stream.empty() : (new TabCompleteHelper()).append("1", "2", "-1").filterPrefix(args.hasAny() ? args.getString() : "").stream();
   }

   public String getShortDesc() {
      return "Ð¢ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð¸Ñ€ÑƒÐµÑ‚ Ð²Ð¿ÐµÑ€Ñ‘Ð´/Ð½Ð°Ð·Ð°Ð´ Ð¿Ð¾ Ð³Ð¾Ñ€Ð¸Ð·Ð¾Ð½Ñ‚Ð°Ð»Ð¸";
   }

   public List getLongDesc() {
      return Arrays.asList("ÐŸÐµÑ€ÐµÐ¼ÐµÑ‰Ð°ÐµÑ‚ Ð²Ð°Ñ Ð¿Ð¾ Ð¾ÑÐ¸ XZ Ð½Ð° ÑƒÐºÐ°Ð·Ð°Ð½Ð½Ð¾Ðµ Ñ€Ð°ÑÑÑ‚Ð¾ÑÐ½Ð¸Ðµ Ð² Ð½Ð°Ð¿Ñ€Ð°Ð²Ð»ÐµÐ½Ð¸Ð¸ Ð²Ð·Ð³Ð»ÑÐ´Ð°.", "", "> hclip <distance> - Ð¢ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð°Ñ†Ð¸Ñ Ð½Ð° ÑƒÐºÐ°Ð·Ð°Ð½Ð½Ð¾Ðµ Ñ€Ð°ÑÑÑ‚Ð¾ÑÐ½Ð¸Ðµ", "ÐŸÑ€Ð¸Ð¼ÐµÑ€: .hclip 1");
   }
}

