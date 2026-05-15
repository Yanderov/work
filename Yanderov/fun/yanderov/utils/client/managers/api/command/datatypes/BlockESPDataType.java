package fun.Yanderov.utils.client.managers.api.command.datatypes;

import fun.Yanderov.Yanderov;
import fun.Yanderov.utils.client.managers.api.command.exception.CommandException;
import fun.Yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.class_2248;

public enum BlockESPDataType implements IDatatypeFor {
   INSTANCE;

   public Stream tabComplete(IDatatypeContext datatypeContext) throws CommandException {
      Stream<String> blocks = this.getBlocks().stream().map((b) -> b.method_9518().getString().replace(" ", "_"));
      String context = datatypeContext.getConsumer().getString();
      return (new TabCompleteHelper()).append(blocks).filterPrefix(context).sortAlphabetically().stream();
   }

   public class_2248 get(IDatatypeContext datatypeContext) throws CommandException {
      String text = datatypeContext.getConsumer().getString();
      return (class_2248)this.getBlocks().stream().filter((s) -> s.method_9518().getString().replace(" ", "_").equalsIgnoreCase(text)).findFirst().orElse((Object)null);
   }

   private List getBlocks() {
      return Yanderov.getInstance().getBoxESPRepository().blocks.keySet().stream().toList();
   }

   // $FF: synthetic method
   private static BlockESPDataType[] $values() {
      return new BlockESPDataType[]{INSTANCE};
   }
}

