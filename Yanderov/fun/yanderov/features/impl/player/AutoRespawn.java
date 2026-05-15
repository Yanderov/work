package fun.Yanderov.features.impl.player;

import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.DeathScreenEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.client.packet.network.Network;
import java.util.Objects;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_437;
import net.minecraft.class_5892;

public class AutoRespawn extends Module {
   private final SelectSetting modeSetting = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ, Ñ‡Ñ‚Ð¾ Ð±ÑƒÐ´ÐµÑ‚ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ñ‚ÑŒÑÑ")).value("FunTime Back", "Default");

   public AutoRespawn() {
      super("AutoRespawn", "Auto Respawn", ModuleCategory.PLAYER);
      this.setup(new Setting[]{this.modeSetting});
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      class_2596 var10000 = e.getPacket();
      Objects.requireNonNull(var10000);
      class_2596 var2 = var10000;
      byte var3 = 0;

      while(true) {
         //$FF: var3->value
         //0->net/minecraft/class_5892
         switch (var2.typeSwitch<invokedynamic>(var2, var3)) {
            case 0:
               class_5892 message = (class_5892)var2;
               if (!Network.getWorldType().equals("lobby") || !this.modeSetting.isSelected("FunTime Back")) {
                  var3 = 1;
                  break;
               } else {
                  mc.field_1724.field_3944.method_52787(new class_2828.class_2829((double)1448.0F, (double)1337.0F, (double)228.0F, false, false));
                  mc.field_1724.method_7331();
                  mc.field_1724.method_3137();
                  return;
               }
            default:
               return;
         }
      }
   }

   @EventHandler
   public void onDeathScreen(DeathScreenEvent e) {
      if (this.modeSetting.isSelected("Default")) {
         mc.field_1724.method_7331();
         mc.method_1507((class_437)null);
      }

   }
}

