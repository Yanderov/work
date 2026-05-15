package dev.client.yanderov.utils.client.managers.api.command.datatypes;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import dev.client.yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import dev.client.yanderov.utils.client.managers.file.ClientFile;
import dev.client.yanderov.utils.client.managers.file.impl.ModuleFile;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ConfigDataType implements IDatatypeFor {
   INSTANCE;

   public Stream tabComplete(IDatatypeContext ctx) throws CommandException {
      Stream<String> friends = this.getList().stream().map(ClientFile::getName);
      String context = ctx.getConsumer().getString();
      return (new TabCompleteHelper()).append(friends).filterPrefix(context).sortAlphabetically().stream();
   }

   public ClientFile get(IDatatypeContext datatypeContext) throws CommandException {
      String username = datatypeContext.getConsumer().getString();
      return (ClientFile)this.getList().stream().filter((s) -> s.getName().equalsIgnoreCase(username)).findFirst().orElse((Object)null);
   }

   public List getList() {
      return (List)YanderovIntegration.getInstance().getFileRepository().getClientFiles().stream().filter((clientFile) -> clientFile instanceof ModuleFile).map((clientFile) -> (ModuleFile)clientFile).collect(Collectors.toList());
   }

   // $FF: synthetic method
   private static ConfigDataType[] $values() {
      return new ConfigDataType[]{INSTANCE};
   }
}

