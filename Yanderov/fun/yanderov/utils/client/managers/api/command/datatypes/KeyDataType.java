package fun.Yanderov.utils.client.managers.api.command.datatypes;

import fun.Yanderov.utils.client.chat.StringHelper;
import fun.Yanderov.utils.client.managers.api.command.exception.CommandException;
import fun.Yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import net.minecraft.class_3675;
import net.minecraft.class_3675.class_307;

public enum KeyDataType implements IDatatypeFor {
   INSTANCE;

   public Stream tabComplete(IDatatypeContext datatypeContext) throws CommandException {
      Stream<String> keys = getKeys().keySet().stream();
      String context = datatypeContext.getConsumer().getString();
      return (new TabCompleteHelper()).append(keys).filterPrefix(context).sortAlphabetically().stream();
   }

   public Map.Entry get(IDatatypeContext datatypeContext) throws CommandException {
      String key = datatypeContext.getConsumer().getString();
      return (Map.Entry)getKeys().entrySet().stream().filter((s) -> ((String)s.getKey()).equalsIgnoreCase(key)).findFirst().orElse((Object)null);
   }

   private static Map getKeys() {
      Map<String, Integer> keys = new HashMap();
      ObjectIterator var1 = class_307.field_1668.field_1674.int2ObjectEntrySet().iterator();

      while(var1.hasNext()) {
         Int2ObjectMap.Entry<class_3675.class_306> entry = (Int2ObjectMap.Entry)var1.next();
         int keyCode = entry.getIntKey();
         String bindName = StringHelper.getBindName(keyCode).toLowerCase();
         keys.put(bindName, keyCode);
      }

      return keys;
   }

   // $FF: synthetic method
   private static KeyDataType[] $values() {
      return new KeyDataType[]{INSTANCE};
   }
}

