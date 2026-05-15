package dev.client.yanderov.utils.client.managers.api.command.datatypes;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.common.repository.way.Way;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import dev.client.yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.List;
import java.util.stream.Stream;

public enum WayDataType implements IDatatypeFor {
   INSTANCE;

   public Stream tabComplete(IDatatypeContext datatypeContext) throws CommandException {
      Stream<String> ways = this.getWay().stream().map(Way::name);
      String context = datatypeContext.getConsumer().getString();
      return (new TabCompleteHelper()).append(ways).filterPrefix(context).sortAlphabetically().stream();
   }

   public Way get(IDatatypeContext datatypeContext) throws CommandException {
      String text = datatypeContext.getConsumer().getString();
      return (Way)this.getWay().stream().filter((s) -> s.name().equalsIgnoreCase(text)).findFirst().orElse((Object)null);
   }

   private List getWay() {
      return YanderovIntegration.getInstance().getWayRepository().wayList;
   }

   // $FF: synthetic method
   private static WayDataType[] $values() {
      return new WayDataType[]{INSTANCE};
   }
}

