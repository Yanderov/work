package dev.client.yanderov.utils.client.managers.api.command.helpers;

import dev.client.yanderov.utils.client.managers.api.command.manager.ICommandManager;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import net.minecraft.class_2960;

public class TabCompleteHelper {
   private Stream stream;

   public TabCompleteHelper(String[] base) {
      this.stream = Stream.of(base);
   }

   public TabCompleteHelper(List base) {
      this.stream = base.stream();
   }

   public TabCompleteHelper() {
      this.stream = Stream.empty();
   }

   public TabCompleteHelper append(Stream source) {
      this.stream = Stream.concat(this.stream, source);
      return this;
   }

   public TabCompleteHelper append(String... source) {
      return this.append(Stream.of(source));
   }

   public TabCompleteHelper append(Class num) {
      return this.append(Stream.of((Enum[])num.getEnumConstants()).map(Enum::name).map(String::toLowerCase));
   }

   public TabCompleteHelper prepend(Stream source) {
      this.stream = Stream.concat(source, this.stream);
      return this;
   }

   public TabCompleteHelper prepend(String... source) {
      return this.prepend(Stream.of(source));
   }

   public TabCompleteHelper prepend(Class num) {
      return this.prepend(Stream.of((Enum[])num.getEnumConstants()).map(Enum::name).map(String::toLowerCase));
   }

   public TabCompleteHelper map(Function transform) {
      this.stream = this.stream.map(transform);
      return this;
   }

   public TabCompleteHelper filter(Predicate filter) {
      this.stream = this.stream.filter(filter);
      return this;
   }

   public TabCompleteHelper sort(Comparator comparator) {
      this.stream = this.stream.sorted(comparator);
      return this;
   }

   public TabCompleteHelper sortAlphabetically() {
      return this.sort(String.CASE_INSENSITIVE_ORDER);
   }

   public TabCompleteHelper filterPrefix(String prefix) {
      return this.filter((x) -> x.toLowerCase(Locale.US).startsWith(prefix.toLowerCase(Locale.US)));
   }

   public TabCompleteHelper filterPrefixNamespaced(String prefix) {
      return this.filterPrefix(class_2960.method_60654(prefix).toString());
   }

   public String[] build() {
      return (String[])this.stream.toArray((x$0) -> new String[x$0]);
   }

   public Stream stream() {
      return this.stream;
   }

   public TabCompleteHelper addCommands(ICommandManager manager) {
      return this.append(manager.getRegistry().descendingStream().flatMap((command) -> command.getNames().stream()).distinct());
   }
}

