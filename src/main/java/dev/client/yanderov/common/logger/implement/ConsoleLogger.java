package dev.client.yanderov.common.logger.implement;

import dev.client.yanderov.common.logger.Logger;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import org.apache.logging.log4j.LogManager;

public class ConsoleLogger implements Logger {
   private final org.apache.logging.log4j.Logger logger = LogManager.getLogger("avalora");

   public void log(Object message) {
      this.logger.info("[AV{}AL{}ORA] {}", class_124.field_1078, class_124.field_1061, message);
   }

   public void minecraftLog(class_2561... components) {
   }
}

