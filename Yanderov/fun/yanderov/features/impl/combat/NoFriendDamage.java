package fun.Yanderov.features.impl.combat;

import fun.Yanderov.common.repository.friend.FriendUtils;
import fun.Yanderov.events.player.AttackEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.utils.client.managers.event.EventHandler;

public class NoFriendDamage extends Module {
   public NoFriendDamage() {
      super("NoFriendDamage", "No Friend Damage", ModuleCategory.COMBAT);
   }

   @EventHandler
   public void onAttack(AttackEvent e) {
      e.setCancelled(FriendUtils.isFriend(e.getEntity()));
   }
}

