package fun.Yanderov.commands;

import fun.Yanderov.Yanderov;
import fun.Yanderov.commands.argument.ArgConsumer;
import fun.Yanderov.commands.argument.CommandArguments;
import fun.Yanderov.commands.manager.CommandRepository;
import fun.Yanderov.events.chat.ChatEvent;
import fun.Yanderov.events.chat.TabCompleteEvent;
import fun.Yanderov.utils.client.managers.api.command.IBaritoneChatControl;
import fun.Yanderov.utils.client.managers.api.command.ICommand;
import fun.Yanderov.utils.client.managers.api.command.argument.ICommandArgument;
import fun.Yanderov.utils.client.managers.api.command.exception.CommandNotEnoughArgumentsException;
import fun.Yanderov.utils.client.managers.api.command.exception.CommandNotFoundException;
import fun.Yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import fun.Yanderov.utils.client.managers.api.command.manager.ICommandManager;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.client.managers.event.EventManager;
import fun.Yanderov.utils.display.interfaces.QuickLogger;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.class_3545;

public class CommandDispatcher implements QuickLogger {
   private final ICommandManager manager;
   public static String prefix = ".";

   public CommandDispatcher(EventManager eventManager) {
      this.manager = Yanderov.instance.getCommandRepository();
      eventManager.register(this);
   }

   @EventHandler
   public void onChat(ChatEvent event) {
      String msg = event.getMessage();
      boolean forceRun = msg.startsWith(IBaritoneChatControl.FORCE_COMMAND_PREFIX);
      if (msg.startsWith(prefix) || forceRun) {
         event.cancel();
         String commandStr = msg.substring(forceRun ? IBaritoneChatControl.FORCE_COMMAND_PREFIX.length() : prefix.length());
         if (!this.runCommand(commandStr) && !commandStr.trim().isEmpty()) {
            (new CommandNotFoundException((String)CommandRepository.expand(commandStr).method_15442())).handle((ICommand)null, (List)null);
         }
      }

   }

   public boolean runCommand(String msg) {
      if (msg.isEmpty()) {
         return this.runCommand("help");
      } else {
         class_3545<String, List<ICommandArgument>> pair = CommandRepository.expand(msg);
         String command = (String)pair.method_15442();
         msg.substring(((String)pair.method_15442()).length());
         new ArgConsumer(this.manager, (List)pair.method_15441());
         return this.manager.execute(pair);
      }
   }

   @EventHandler
   public void onTabComplete(TabCompleteEvent event) {
      String eventPrefix = event.prefix;
      if (eventPrefix.startsWith(prefix)) {
         String msg = eventPrefix.substring(prefix.length());
         List<ICommandArgument> args = CommandArguments.from(msg, true);
         Stream<String> stream = this.tabComplete(msg);
         if (args.size() == 1) {
            stream = stream.map((x) -> prefix + x);
         }

         event.completions = (String[])stream.toArray((x$0) -> new String[x$0]);
      }
   }

   public Stream tabComplete(String msg) {
      try {
         List<ICommandArgument> args = CommandArguments.from(msg, true);
         ArgConsumer argc = new ArgConsumer(this.manager, args);
         return argc.hasAtMost(2) && argc.hasExactly(1) ? (new TabCompleteHelper()).addCommands(this.manager).filterPrefix(argc.getString()).stream() : this.manager.tabComplete(msg);
      } catch (CommandNotEnoughArgumentsException var4) {
         return Stream.empty();
      }
   }
}

