package fun.Yanderov.utils.client.managers.api.command.datatypes;

import fun.Yanderov.Yanderov;
import fun.Yanderov.utils.client.managers.api.command.exception.CommandException;
import fun.Yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.Optional;
import java.util.stream.Stream;
import net.minecraft.class_1299;

public enum EntityESPDataType implements IDatatypeFor {
   INSTANCE;

   public Stream tabComplete(IDatatypeContext ctx) throws CommandException {
      Stream<String> ways = this.getEntities().map((s) -> s.method_5897().getString().replace(" ", "_"));
      String context = ctx.getConsumer().getString();
      return (new TabCompleteHelper()).append(ways).filterPrefix(context).sortAlphabetically().stream();
   }

   public class_1299 get(IDatatypeContext datatypeContext) throws CommandException {
      return (class_1299)this.findEntity(datatypeContext.getConsumer().getString()).orElse((Object)null);
   }

   public Optional findEntity(String text) {
      return this.getEntities().filter((s) -> s.method_5897().getString().replace(" ", "_").equalsIgnoreCase(text)).findFirst();
   }

   private Stream getEntities() {
      return Yanderov.getInstance().getBoxESPRepository().entities.keySet().stream();
   }

   // $FF: synthetic method
   private static EntityESPDataType[] $values() {
      return new EntityESPDataType[]{INSTANCE};
   }
}

