package dev.client.yanderov.utils.client.managers.api.command;

import dev.client.yanderov.utils.display.interfaces.QuickImports;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public abstract class Command implements ICommand, QuickImports {
   protected final List names;

   protected Command(String... names) {
      this.names = Stream.of(names).map((string) -> string.toLowerCase(Locale.US)).toList();
   }

   public final List getNames() {
      return this.names;
   }
}

