package dev.client.yanderov.common.logger.implement;

import dev.client.yanderov.common.logger.Logger;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_2561;
import net.minecraft.class_5250;

public class MinecraftLogger implements Logger, QuickImports {
   public void log(Object message) {
   }

   public void minecraftLog(class_2561... components) {
      if (mc.field_1724 != null) {
         class_5250 component = class_2561.method_43470("");
         List var10000 = Arrays.asList(components);
         Objects.requireNonNull(component);
         var10000.forEach(component::method_10852);
         mc.field_1705.method_1743().method_1812(component);
      }

   }
}

