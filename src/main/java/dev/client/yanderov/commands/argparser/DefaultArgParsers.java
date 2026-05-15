package dev.client.yanderov.commands.argparser;

import dev.client.yanderov.utils.client.managers.api.command.argparser.IArgParser;
import dev.client.yanderov.utils.client.managers.api.command.argument.ICommandArgument;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DefaultArgParsers {
   public static final List ALL;

   static {
      ALL = Arrays.asList(DefaultArgParsers.IntArgumentParser.INSTANCE, DefaultArgParsers.LongArgumentParser.INSTANCE, DefaultArgParsers.FloatArgumentParser.INSTANCE, DefaultArgParsers.DoubleArgumentParser.INSTANCE, DefaultArgParsers.BooleanArgumentParser.INSTANCE);
   }

   public static enum IntArgumentParser implements IArgParser.Stateless {
      INSTANCE;

      public Class getTarget() {
         return Integer.class;
      }

      public Integer parseArg(ICommandArgument arg) throws RuntimeException {
         return Integer.parseInt(arg.getValue());
      }

      // $FF: synthetic method
      private static IntArgumentParser[] $values() {
         return new IntArgumentParser[]{INSTANCE};
      }
   }

   public static enum LongArgumentParser implements IArgParser.Stateless {
      INSTANCE;

      public Class getTarget() {
         return Long.class;
      }

      public Long parseArg(ICommandArgument arg) throws RuntimeException {
         return Long.parseLong(arg.getValue());
      }

      // $FF: synthetic method
      private static LongArgumentParser[] $values() {
         return new LongArgumentParser[]{INSTANCE};
      }
   }

   public static enum FloatArgumentParser implements IArgParser.Stateless {
      INSTANCE;

      public Class getTarget() {
         return Float.class;
      }

      public Float parseArg(ICommandArgument arg) throws RuntimeException {
         String value = arg.getValue();
         if (!value.matches("^([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)|)$")) {
            throw new IllegalArgumentException("failed float format check");
         } else {
            return Float.parseFloat(value);
         }
      }

      // $FF: synthetic method
      private static FloatArgumentParser[] $values() {
         return new FloatArgumentParser[]{INSTANCE};
      }
   }

   public static enum DoubleArgumentParser implements IArgParser.Stateless {
      INSTANCE;

      public Class getTarget() {
         return Double.class;
      }

      public Double parseArg(ICommandArgument arg) throws RuntimeException {
         String value = arg.getValue();
         if (!value.matches("^([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)|)$")) {
            throw new IllegalArgumentException("failed double format check");
         } else {
            return Double.parseDouble(value);
         }
      }

      // $FF: synthetic method
      private static DoubleArgumentParser[] $values() {
         return new DoubleArgumentParser[]{INSTANCE};
      }
   }

   public static class BooleanArgumentParser implements IArgParser.Stateless {
      public static final BooleanArgumentParser INSTANCE = new BooleanArgumentParser();
      public static final List TRUTHY_VALUES = Arrays.asList("1", "true", "yes", "t", "y", "on", "enable");
      public static final List FALSY_VALUES = Arrays.asList("0", "false", "no", "f", "n", "off", "disable");

      public Class getTarget() {
         return Boolean.class;
      }

      public Boolean parseArg(ICommandArgument arg) throws RuntimeException {
         String value = arg.getValue();
         if (TRUTHY_VALUES.contains(value.toLowerCase(Locale.US))) {
            return true;
         } else if (FALSY_VALUES.contains(value.toLowerCase(Locale.US))) {
            return false;
         } else {
            throw new IllegalArgumentException("invalid boolean");
         }
      }
   }
}

