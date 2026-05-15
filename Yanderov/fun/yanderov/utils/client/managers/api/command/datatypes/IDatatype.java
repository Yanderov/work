package fun.Yanderov.utils.client.managers.api.command.datatypes;

import fun.Yanderov.utils.client.managers.api.command.exception.CommandException;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import java.util.stream.Stream;

public interface IDatatype extends QuickImports {
   Stream tabComplete(IDatatypeContext var1) throws CommandException;
}

