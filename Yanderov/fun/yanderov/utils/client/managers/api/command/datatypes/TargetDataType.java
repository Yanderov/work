package fun.Yanderov.utils.client.managers.api.command.datatypes;

import fun.Yanderov.common.repository.target.TargetRepository;
import fun.Yanderov.utils.client.managers.api.command.exception.CommandException;
import fun.Yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.stream.Stream;

public enum TargetDataType implements IDatatypeFor {
   INSTANCE;

   public Stream tabComplete(IDatatypeContext datatypeContext) throws CommandException {
      Stream<String> targets = TargetRepository.getInstance().getTargets().stream();
      String context = datatypeContext.getConsumer().peekString();
      return (new TabCompleteHelper()).append(targets).filterPrefix(context).sortAlphabetically().stream();
   }

   public String get(IDatatypeContext datatypeContext) throws CommandException {
      String name = datatypeContext.getConsumer().getString();
      return (String)TargetRepository.getInstance().getTargets().stream().filter((s) -> s.equalsIgnoreCase(name)).findFirst().orElse((Object)null);
   }

   // $FF: synthetic method
   private static TargetDataType[] $values() {
      return new TargetDataType[]{INSTANCE};
   }
}

