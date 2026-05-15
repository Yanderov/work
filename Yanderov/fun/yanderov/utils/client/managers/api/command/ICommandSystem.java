package fun.Yanderov.utils.client.managers.api.command;

import fun.Yanderov.utils.client.managers.api.command.argparser.IArgParserManager;

public interface ICommandSystem {
   IArgParserManager getParserManager();
}

