package dev.client.yanderov.features.impl.player;

import dev.client.yanderov.events.block.PushEvent;
import dev.client.yanderov.events.player.PlayerCollisionEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.MultiSelectSetting;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import net.minecraft.class_1690;
import net.minecraft.class_2246;
import net.minecraft.class_2248;

public class NoPush extends Module {
   private final MultiSelectSetting ignoreSetting = (new MultiSelectSetting("Ð˜Ð³Ð½Ð¾Ñ€Ð¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒ", "Ð Ð°Ð·Ñ€ÐµÑˆÐ°ÐµÑ‚ Ð²Ñ‹Ð±Ñ€Ð°Ð½Ð½Ñ‹Ðµ Ð²Ð°Ð¼Ð¸ Ð´ÐµÐ¹ÑÑ‚Ð²Ð¸Ñ")).value("Water", "Block", "Entity Collision", "Powder Snow", "Berry");

   public NoPush() {
      super("NoPush", "No Push", ModuleCategory.PLAYER);
      this.setup(new Setting[]{this.ignoreSetting});
   }

   @EventHandler
   public void onPush(PushEvent e) {
      switch (e.getType()) {
         case COLLISION -> e.setCancelled(this.ignoreSetting.isSelected("Entity Collision"));
         case WATER -> e.setCancelled(this.ignoreSetting.isSelected("Water"));
         case BLOCK -> e.setCancelled(this.ignoreSetting.isSelected("Block"));
      }

   }

   @EventHandler
   public void onPlayerCollision(PlayerCollisionEvent e) {
      if (mc.field_1724 != null && mc.field_1724.method_5854() instanceof class_1690) {
         e.setCancelled(true);
      } else {
         class_2248 block = e.getBlock();
         if (block.equals(class_2246.field_27879)) {
            e.setCancelled(this.ignoreSetting.isSelected("Powder Snow"));
         } else if (block.equals(class_2246.field_16999)) {
            e.setCancelled(this.ignoreSetting.isSelected("Berry"));
         }

      }
   }
}

