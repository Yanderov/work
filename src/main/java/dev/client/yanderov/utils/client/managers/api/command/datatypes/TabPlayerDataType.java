package dev.client.yanderov.utils.client.managers.api.command.datatypes;

import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import dev.client.yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.Collection;
import java.util.stream.Stream;
import net.minecraft.class_268;

public enum TabPlayerDataType implements IDatatypeFor {
   INSTANCE;

   public Stream tabComplete(IDatatypeContext ctx) throws CommandException {
      return (new TabCompleteHelper()).append(this.getTeam().stream().map(class_268::method_1204).map(Object::toString).map((s) -> s.replaceAll("[\\[\\]]", ""))).filterPrefix(ctx.getConsumer().getString()).sortAlphabetically().stream();
   }

   public class_268 get(IDatatypeContext datatypeContext) throws CommandException {
      String username = datatypeContext.getConsumer().getString();
      return (class_268)this.getTeam().stream().filter((s) -> s.method_1197().equalsIgnoreCase(username)).findFirst().orElse((Object)null);
   }

   public Collection getTeam() {
      return mc.field_1687.method_8428().method_1159();
   }

   // $FF: synthetic method
   private static TabPlayerDataType[] $values() {
      return new TabPlayerDataType[]{INSTANCE};
   }
}

