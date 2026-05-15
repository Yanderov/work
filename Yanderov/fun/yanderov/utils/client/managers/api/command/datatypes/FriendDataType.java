package fun.Yanderov.utils.client.managers.api.command.datatypes;

import fun.Yanderov.common.repository.friend.Friend;
import fun.Yanderov.common.repository.friend.FriendUtils;
import fun.Yanderov.utils.client.managers.api.command.exception.CommandException;
import fun.Yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.util.List;
import java.util.stream.Stream;

public enum FriendDataType implements IDatatypeFor {
   INSTANCE;

   public Stream tabComplete(IDatatypeContext datatypeContext) throws CommandException {
      Stream<String> friends = this.getFriends().stream().map(Friend::getName);
      String context = datatypeContext.getConsumer().getString();
      return (new TabCompleteHelper()).append(friends).filterPrefix(context).sortAlphabetically().stream();
   }

   public Friend get(IDatatypeContext datatypeContext) throws CommandException {
      String username = datatypeContext.getConsumer().getString();
      return (Friend)this.getFriends().stream().filter((s) -> s.getName().equalsIgnoreCase(username)).findFirst().orElse((Object)null);
   }

   private List getFriends() {
      return FriendUtils.getFriends();
   }

   // $FF: synthetic method
   private static FriendDataType[] $values() {
      return new FriendDataType[]{INSTANCE};
   }
}

