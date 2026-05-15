package dev.client.yanderov.utils.client.managers.api.command.datatypes;

import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import dev.client.yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_7923;

public enum BlockDataType implements IDatatypeFor {
   INSTANCE;

   public Stream tabComplete(IDatatypeContext ctx) throws CommandException {
      Stream<String> ways = this.streamBlocks().map((s) -> s.method_9518().getString().replace(" ", "_"));
      String context = ctx.getConsumer().getString();
      return (new TabCompleteHelper()).append(ways).filterPrefix(context).sortAlphabetically().stream();
   }

   public class_2248 get(IDatatypeContext datatypeContext) throws CommandException {
      return (class_2248)this.findBlock(datatypeContext.getConsumer().getString()).orElse((Object)null);
   }

   public Optional findBlock(String text) {
      return this.streamBlocks().filter((s) -> s.method_9518().getString().replace(" ", "_").equalsIgnoreCase(text)).findFirst();
   }

   public Stream streamBlocks() {
      return class_7923.field_41175.method_10220().filter(this::blackList);
   }

   public boolean blackList(class_2248 block) {
      return !List.of(class_2246.field_10124, class_2246.field_10543, class_2246.field_10243, class_2246.field_10382).contains(block);
   }

   // $FF: synthetic method
   private static BlockDataType[] $values() {
      return new BlockDataType[]{INSTANCE};
   }
}

