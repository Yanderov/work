package dev.client.yanderov.utils.client.managers.api.command.datatypes;

import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;

public interface IDatatypeFor extends IDatatype {
   Object get(IDatatypeContext var1) throws CommandException;
}

