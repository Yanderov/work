package dev.client.yanderov.commands;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.commands.argument.ArgConsumer;
import dev.client.yanderov.commands.argument.CommandArguments;
import dev.client.yanderov.commands.manager.CommandRepository;
import dev.client.yanderov.events.chat.ChatEvent;
import dev.client.yanderov.events.chat.TabCompleteEvent;
import dev.client.yanderov.utils.client.managers.api.command.IBaritoneChatControl;
import dev.client.yanderov.utils.client.managers.api.command.ICommand;
import dev.client.yanderov.utils.client.managers.api.command.argument.ICommandArgument;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandNotEnoughArgumentsException;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandNotFoundException;
import dev.client.yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import dev.client.yanderov.utils.client.managers.api.command.manager.ICommandManager;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.client.managers.event.EventManager;
import dev.client.yanderov.utils.display.interfaces.QuickLogger;
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

