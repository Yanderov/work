package dev.client.yanderov.utils.client.managers.api.command.datatypes;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import dev.client.yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.List;
import java.util.stream.Stream;

public enum ModuleDataType implements IDatatypeFor {
   INSTANCE;

   public Stream tabComplete(IDatatypeContext datatypeContext) throws CommandException {
      Stream<String> source = this.getModules().stream().map(Module::getName);
      String context = datatypeContext.getConsumer().getString();
      return (new TabCompleteHelper()).append(source).filterPrefix(context).sortAlphabetically().stream();
   }

   public Module get(IDatatypeContext datatypeContext) throws CommandException {
      String name = datatypeContext.getConsumer().getString();
      return (Module)this.getModules().stream().filter((s) -> s.getName().equalsIgnoreCase(name)).findFirst().orElse((Object)null);
   }

   private List getModules() {
      return YanderovIntegration.getInstance().getModuleRepository().modules();
   }

   // $FF: synthetic method
   private static ModuleDataType[] $values() {
      return new ModuleDataType[]{INSTANCE};
   }
}

