package dev.client.yanderov.utils.client.managers.api.command.datatypes;

import dev.client.yanderov.common.repository.staff.StaffRepository;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import dev.client.yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.stream.Stream;

public enum StaffDataType implements IDatatypePost {
   INSTANCE;

   public String apply(IDatatypeContext context, Void original) throws CommandException {
      return context.getConsumer().getString();
   }

   public Stream tabComplete(IDatatypeContext context) throws CommandException {
      TabCompleteHelper helper = new TabCompleteHelper();
      StaffRepository.getStaff().forEach((staff) -> helper.append(staff.getName()));
      String prefix = context.getConsumer().hasAny() ? context.getConsumer().peekString() : "";
      return helper.filterPrefix(prefix).stream();
   }

   // $FF: synthetic method
   private static StaffDataType[] $values() {
      return new StaffDataType[]{INSTANCE};
   }
}

