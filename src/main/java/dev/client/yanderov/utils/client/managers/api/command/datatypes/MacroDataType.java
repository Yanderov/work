package dev.client.yanderov.utils.client.managers.api.command.datatypes;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.common.repository.macro.Macro;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import dev.client.yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.List;
import java.util.stream.Stream;

public enum MacroDataType implements IDatatypeFor {
   INSTANCE;

   public Stream tabComplete(IDatatypeContext datatypeContext) throws CommandException {
      Stream<String> macros = this.getMacro().stream().map(Macro::name);
      String context = datatypeContext.getConsumer().getString();
      return (new TabCompleteHelper()).append(macros).filterPrefix(context).sortAlphabetically().stream();
   }

   public Macro get(IDatatypeContext datatypeContext) throws CommandException {
      String username = datatypeContext.getConsumer().getString();
      return (Macro)this.getMacro().stream().filter((s) -> s.name().equalsIgnoreCase(username)).findFirst().orElse((Object)null);
   }

   private List getMacro() {
      return YanderovIntegration.getInstance().getMacroRepository().macroList;
   }

   // $FF: synthetic method
   private static MacroDataType[] $values() {
      return new MacroDataType[]{INSTANCE};
   }
}

