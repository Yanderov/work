package dev.client.yanderov.utils.client.managers.api.command.datatypes;

import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import java.util.stream.Stream;

public interface IDatatype extends QuickImports {
   Stream tabComplete(IDatatypeContext var1) throws CommandException;
}

