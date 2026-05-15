package dev.client.yanderov.features.impl.combat;

import dev.client.yanderov.common.repository.friend.FriendUtils;
import dev.client.yanderov.events.player.AttackEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.utils.client.managers.event.EventHandler;

public class NoFriendDamage extends Module {
   public NoFriendDamage() {
      super("NoFriendDamage", "No Friend Damage", ModuleCategory.COMBAT);
   }

   @EventHandler
   public void onAttack(AttackEvent e) {
      e.setCancelled(FriendUtils.isFriend(e.getEntity()));
   }
}

