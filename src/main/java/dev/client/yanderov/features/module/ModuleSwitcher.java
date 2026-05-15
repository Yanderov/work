package dev.client.yanderov.features.module;

import dev.client.yanderov.common.logger.implement.ConsoleLogger;
import dev.client.yanderov.events.keyboard.KeyEvent;
import dev.client.yanderov.features.module.exception.ModuleException;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.client.managers.event.EventManager;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.display.interfaces.QuickLogger;
import java.util.List;
import net.minecraft.class_124;

public class ModuleSwitcher implements QuickLogger, QuickImports {
   private final List modules;

   public ModuleSwitcher(List modules, EventManager eventManager) {
      this.modules = modules;
      eventManager.register(this);
   }

   @EventHandler
   public void onKey(KeyEvent event) {
      for(Module module : this.modules) {
         if (event.key() == module.getKey() && mc.field_1755 == null) {
            try {
               this.handleModuleState(module, event.action());
            } catch (Exception e) {
               this.handleException(module.getName(), e);
            }
         }
      }

   }

   private void handleModuleState(Module module, int action) {
      if (module.getType() == 1 && action == 1) {
         module.switchState();
      }

   }

   private void handleException(String moduleName, Exception e) {
      ConsoleLogger consoleLogger = new ConsoleLogger();
      if (e instanceof ModuleException) {
         this.logDirect("[" + moduleName + "] " + String.valueOf(class_124.field_1061) + e.getMessage());
      } else {
         consoleLogger.log("Error in module " + moduleName + ": " + e.getMessage());
      }

   }
}

