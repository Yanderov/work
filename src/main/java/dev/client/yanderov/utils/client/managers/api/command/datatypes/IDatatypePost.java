package dev.client.yanderov.utils.client.managers.api.command.datatypes;

import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;

public interface IDatatypePost extends IDatatype {
   Object apply(IDatatypeContext var1, Object var2) throws CommandException;
}

