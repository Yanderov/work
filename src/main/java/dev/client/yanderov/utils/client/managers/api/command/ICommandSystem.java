package dev.client.yanderov.utils.client.managers.api.command;

import dev.client.yanderov.utils.client.managers.api.command.argparser.IArgParserManager;

public interface ICommandSystem {
   IArgParserManager getParserManager();
}

