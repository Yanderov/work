package fun.Yanderov.common.repository.friend;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1657;

public final class FriendUtils {
   public static final List friends = new ArrayList();

   public static void addFriend(class_1657 player) {
      addFriend(player.method_5477().getString());
   }

   public static void addFriend(String name) {
      friends.add(new Friend(name));
   }

   public static void removeFriend(class_1657 player) {
      removeFriend(player.method_5477().getString());
   }

   public static void removeFriend(String name) {
      friends.removeIf((friend) -> friend.getName().equalsIgnoreCase(name));
   }

   public static boolean isFriend(class_1297 entity) {
      if (entity instanceof class_1657 player) {
         return isFriend(player.method_5477().getString());
      } else {
         return false;
      }
   }

   public static boolean isFriend(String friend) {
      return friends.stream().anyMatch((isFriend) -> isFriend.getName().equals(friend));
   }

   public static void clear() {
      friends.clear();
   }

   private FriendUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public static List getFriends() {
      return friends;
   }
}

