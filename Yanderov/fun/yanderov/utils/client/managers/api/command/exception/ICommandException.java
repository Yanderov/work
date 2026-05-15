package fun.Yanderov.utils.client.managers.api.command.exception;

import fun.Yanderov.utils.client.managers.api.command.ICommand;
import fun.Yanderov.utils.client.managers.api.command.argument.ICommandArgument;
import fun.Yanderov.utils.display.interfaces.QuickLogger;
import java.util.List;
import net.minecraft.class_124;

public interface ICommandException extends QuickLogger {
   String getMessage();

   default void handle(ICommand command, List args) {
      this.logDirect(this.getMessage(), class_124.field_1061);
   }
}

