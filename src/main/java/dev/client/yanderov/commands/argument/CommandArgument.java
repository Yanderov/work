package dev.client.yanderov.commands.argument;

import dev.client.yanderov.commands.argparser.ArgParserManager;
import dev.client.yanderov.utils.client.managers.api.command.argument.ICommandArgument;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandInvalidTypeException;
import java.util.stream.Stream;

class CommandArgument implements ICommandArgument {
   private final int index;
   private final String value;
   private final String rawRest;

   CommandArgument(int index, String value, String rawRest) {
      this.index = index;
      this.value = value;
      this.rawRest = rawRest;
   }

   public int getIndex() {
      return this.index;
   }

   public String getValue() {
      return this.value;
   }

   public String getRawRest() {
      return this.rawRest;
   }

   public Enum getEnum(Class enumClass) throws CommandInvalidTypeException {
      return (Enum)Stream.of((Enum[])enumClass.getEnumConstants()).filter((e) -> e.name().equalsIgnoreCase(this.value)).findFirst().orElseThrow(() -> new CommandInvalidTypeException(this, enumClass.getSimpleName()));
   }

   public Object getAs(Class type) throws CommandInvalidTypeException {
      return ArgParserManager.INSTANCE.parseStateless(type, this);
   }

   public boolean is(Class type) {
      try {
         this.getAs(type);
         return true;
      } catch (Throwable var3) {
         return false;
      }
   }

   public Object getAs(Class type, Class stateType, Object state) throws CommandInvalidTypeException {
      return ArgParserManager.INSTANCE.parseStated(type, stateType, this, state);
   }

   public boolean is(Class type, Class stateType, Object state) {
      try {
         this.getAs(type, stateType, state);
         return true;
      } catch (Throwable var5) {
         return false;
      }
   }
}

