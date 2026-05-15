package dev.client.yanderov.commands.defaults;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.common.repository.rct.RCTRepository;
import dev.client.yanderov.display.hud.Notifications;
import dev.client.yanderov.utils.client.managers.api.command.Command;
import dev.client.yanderov.utils.client.managers.api.command.argument.IArgConsumer;
import dev.client.yanderov.utils.client.managers.api.command.argument.ICommandArgument;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import dev.client.yanderov.utils.client.packet.network.Network;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.class_124;

public class RCTCommand extends Command implements QuickImports {
   private final RCTRepository repository;

   protected RCTCommand(Yanderov main) {
      super("rct");
      this.repository = main.getRCTRepository();
   }

   public void execute(String label, IArgConsumer args) throws CommandException {
      if (!Network.isHolyWorld()) {
         Notifications.getInstance().addList("[RCT] ÐÐµ Ñ€Ð°Ð±Ð¾Ñ‚Ð°ÐµÑ‚ Ð½Ð° ÑÑ‚Ð¾Ð¼ " + String.valueOf(class_124.field_1061) + "ÑÐµÑ€Ð²ÐµÑ€Ðµ", 3000L);
      } else if (Network.isPvp()) {
         Notifications.getInstance().addList("[RCT] Ð’Ñ‹ Ð½Ð°Ñ…Ð¾Ð´Ð¸Ñ‚ÐµÑÑŒ Ð² Ñ€ÐµÐ¶Ð¸Ð¼Ðµ " + String.valueOf(class_124.field_1061) + "Ð¿Ð²Ð¿", 3000L);
      } else {
         if (args.hasAny()) {
            args.requireMin(1);
            int anarchy = (Integer)((ICommandArgument)args.getArgs().getFirst()).getAs(Integer.class);
            this.repository.reconnect(anarchy);
         } else {
            this.repository.reconnect(Network.getAnarchy());
         }

      }
   }

   public Stream tabComplete(String label, IArgConsumer args) throws CommandException {
      return Stream.empty();
   }

   public String getShortDesc() {
      return "ÐŸÐµÑ€ÐµÐ·Ð°Ñ…Ð¾Ð´Ð¸Ñ‚ Ð½Ð° Ð°Ð½Ð°Ñ€Ñ…Ð¸ÑŽ";
   }

   public List getLongDesc() {
      return Arrays.asList("ÐŸÐµÑ€ÐµÐ·Ð°Ñ…Ð¾Ð´Ð¸Ñ‚ Ð½Ð° Ð°Ð½Ð°Ñ€Ñ…Ð¸ÑŽ", "", "Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ðµ:", "> rct <anarchy> - Ð—Ð°Ñ…Ð¾Ð´Ð¸Ñ‚ Ð½Ð° <anarchy>", "> rct - ÐŸÐµÑ€ÐµÐ·Ð°Ñ…Ð¾Ð´Ð¸Ñ‚ Ð½Ð° Ð°Ð½Ð°Ñ€Ñ…Ð¸ÑŽ Ð³Ð´Ðµ Ð²Ñ‹ Ñ‚Ð¾Ð»ÑŒÐºÐ¾ Ñ‡Ñ‚Ð¾ Ð±Ñ‹Ð»Ð¸");
   }
}

