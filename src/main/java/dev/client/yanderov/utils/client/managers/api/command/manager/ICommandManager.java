package dev.client.yanderov.utils.client.managers.api.command.manager;

import dev.client.yanderov.utils.client.managers.api.command.ICommand;
import dev.client.yanderov.utils.client.managers.api.command.registry.Registry;
import java.util.stream.Stream;
import net.minecraft.class_3545;

public interface ICommandManager {
   Registry getRegistry();

   ICommand getCommand(String var1);

   boolean execute(String var1);

   boolean execute(class_3545 var1);

   Stream tabComplete(class_3545 var1);

   Stream tabComplete(String var1);
}

