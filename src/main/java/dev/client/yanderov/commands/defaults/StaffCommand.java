package dev.client.yanderov.commands.defaults;

import dev.client.yanderov.common.repository.staff.StaffRepository;
import dev.client.yanderov.utils.client.managers.api.command.Command;
import dev.client.yanderov.utils.client.managers.api.command.IBaritoneChatControl;
import dev.client.yanderov.utils.client.managers.api.command.argument.IArgConsumer;
import dev.client.yanderov.utils.client.managers.api.command.datatypes.StaffDataType;
import dev.client.yanderov.utils.client.managers.api.command.datatypes.TabPlayerDataType;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import dev.client.yanderov.utils.client.managers.api.command.helpers.Paginator;
import dev.client.yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_5250;

public class StaffCommand extends Command {
   public StaffCommand() {
      super("staff");
   }

   public void execute(String label, IArgConsumer args) throws CommandException {
      switch (args.hasAny() ? args.getString().toLowerCase(Locale.US) : "list") {
         case "add":
            args.requireMin(1);
            String name = args.getString();
            if (!StaffRepository.isStaff(name)) {
               StaffRepository.addStaff(name);
               this.save();
               String var8 = String.valueOf(class_124.field_1060);
               this.logDirect("Ð’Ñ‹ ÑƒÑÐ¿ÐµÑˆÐ½Ð¾ Ð´Ð¾Ð±Ð°Ð²Ð¸Ð»Ð¸ " + var8 + name + String.valueOf(class_124.field_1080) + " Ð² ÑÐ¿Ð¸ÑÐ¾Ðº Ð¿ÐµÑ€ÑÐ¾Ð½Ð°Ð»Ð°!");
            } else {
               this.logDirect(String.valueOf(class_124.field_1061) + name + " ÑƒÐ¶Ðµ ÐµÑÑ‚ÑŒ Ð² ÑÐ¿Ð¸ÑÐºÐµ Ð¿ÐµÑ€ÑÐ¾Ð½Ð°Ð»Ð°!", class_124.field_1061);
            }
            break;
         case "remove":
            args.requireMin(1);
            String name = args.getString();
            if (StaffRepository.isStaff(name)) {
               StaffRepository.removeStaff(name);
               this.save();
               String var10001 = String.valueOf(class_124.field_1061);
               this.logDirect("Ð’Ñ‹ ÑƒÑÐ¿ÐµÑˆÐ½Ð¾ ÑƒÐ´Ð°Ð»Ð¸Ð»Ð¸ " + var10001 + name + String.valueOf(class_124.field_1080) + " Ð¸Ð· ÑÐ¿Ð¸ÑÐºÐ° Ð¿ÐµÑ€ÑÐ¾Ð½Ð°Ð»Ð°!");
            } else {
               this.logDirect(String.valueOf(class_124.field_1061) + name + " Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½ Ð² ÑÐ¿Ð¸ÑÐºÐµ Ð¿ÐµÑ€ÑÐ¾Ð½Ð°Ð»Ð°", class_124.field_1061);
            }
            break;
         case "list":
            args.requireMax(1);
            Paginator.paginate(args, (Paginator)(new Paginator(StaffRepository.getStaff())), () -> this.logDirect("Ð¡Ð¿Ð¸ÑÐ¾Ðº Ð¿ÐµÑ€ÑÐ¾Ð½Ð°Ð»Ð°:"), (staff) -> {
               class_5250 namesComponent = class_2561.method_43470(staff.getName());
               namesComponent.method_10862(namesComponent.method_10866().method_10977(class_124.field_1068));
               return namesComponent;
            }, IBaritoneChatControl.FORCE_COMMAND_PREFIX + label);
            break;
         case "clear":
            args.requireMax(0);
            StaffRepository.clear();
            this.save();
            this.logDirect("Ð¡Ð¿Ð¸ÑÐ¾Ðº Ð¿ÐµÑ€ÑÐ¾Ð½Ð°Ð»Ð° Ð¾Ñ‡Ð¸Ñ‰ÐµÐ½.");
            break;
         default:
            this.logDirect("ÐÐµÐ¸Ð·Ð²ÐµÑÑ‚Ð½Ð°Ñ Ð¿Ð¾Ð´ÐºÐ¾Ð¼Ð°Ð½Ð´Ð°. Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·ÑƒÐ¹Ñ‚Ðµ: add, remove, list, clear");
      }

   }

   private void save() {
      try {
         Method saveMethod = StaffRepository.class.getDeclaredMethod("save");
         saveMethod.setAccessible(true);
         saveMethod.invoke((Object)null);
      } catch (ReflectiveOperationException e) {
         throw new RuntimeException("ÐžÑˆÐ¸Ð±ÐºÐ° Ð¿Ñ€Ð¸ ÑÐ¾Ñ…Ñ€Ð°Ð½ÐµÐ½Ð¸Ð¸ ÑÐ¿Ð¸ÑÐºÐ° Ð¿ÐµÑ€ÑÐ¾Ð½Ð°Ð»Ð° Ñ‡ÐµÑ€ÐµÐ· Ñ€ÐµÑ„Ð»ÐµÐºÑÐ¸ÑŽ", e);
      }
   }

   public Stream tabComplete(String label, IArgConsumer args) throws CommandException {
      if (args.hasExactlyOne()) {
         return (new TabCompleteHelper()).sortAlphabetically().prepend("add", "remove", "list", "clear").filterPrefix(args.getString()).stream();
      } else {
         if (args.hasAtMost(2)) {
            String arg = args.peekString(0).toLowerCase(Locale.US);
            if (arg.equals("add")) {
               return args.tabCompleteDatatype(TabPlayerDataType.INSTANCE);
            }

            if (arg.equals("remove")) {
               return args.tabCompleteDatatype(StaffDataType.INSTANCE);
            }
         }

         return Stream.empty();
      }
   }

   public String getShortDesc() {
      return "Ð£Ð¿Ñ€Ð°Ð²Ð»ÐµÐ½Ð¸Ðµ ÑÐ¿Ð¸ÑÐºÐ¾Ð¼ Ð¿ÐµÑ€ÑÐ¾Ð½Ð°Ð»Ð°.";
   }

   public List getLongDesc() {
      return Arrays.asList("Ð­Ñ‚Ð° ÐºÐ¾Ð¼Ð°Ð½Ð´Ð° Ð¿Ð¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ ÑƒÐ¿Ñ€Ð°Ð²Ð»ÑÑ‚ÑŒ ÑÐ¿Ð¸ÑÐºÐ¾Ð¼ Ð¿ÐµÑ€ÑÐ¾Ð½Ð°Ð»Ð°.", "", "Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ðµ:", "> staff add <Ð½Ð¸Ðº> - Ð”Ð¾Ð±Ð°Ð²Ð»ÑÐµÑ‚ Ð¸Ð³Ñ€Ð¾ÐºÐ° Ð² ÑÐ¿Ð¸ÑÐ¾Ðº Ð¿ÐµÑ€ÑÐ¾Ð½Ð°Ð»Ð°.", "> staff remove <Ð½Ð¸Ðº> - Ð£Ð´Ð°Ð»ÑÐµÑ‚ Ð¸Ð³Ñ€Ð¾ÐºÐ° Ð¸Ð· ÑÐ¿Ð¸ÑÐºÐ° Ð¿ÐµÑ€ÑÐ¾Ð½Ð°Ð»Ð°.", "> staff list - ÐŸÐ¾ÐºÐ°Ð·Ñ‹Ð²Ð°ÐµÑ‚ ÑÐ¿Ð¸ÑÐ¾Ðº Ð¿ÐµÑ€ÑÐ¾Ð½Ð°Ð»Ð°.", "> staff clear - ÐžÑ‡Ð¸Ñ‰Ð°ÐµÑ‚ ÑÐ¿Ð¸ÑÐ¾Ðº Ð¿ÐµÑ€ÑÐ¾Ð½Ð°Ð»Ð°.");
   }
}

