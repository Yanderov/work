package fun.Yanderov.utils.client.managers.api.command.datatypes;

import fun.Yanderov.utils.client.managers.api.command.exception.CommandException;

public interface IDatatypeFor extends IDatatype {
   Object get(IDatatypeContext var1) throws CommandException;
}

