package fun.Yanderov.utils.client.managers.api.command.datatypes;

import fun.Yanderov.utils.client.managers.api.command.exception.CommandException;

public interface IDatatypePost extends IDatatype {
   Object apply(IDatatypeContext var1, Object var2) throws CommandException;
}

