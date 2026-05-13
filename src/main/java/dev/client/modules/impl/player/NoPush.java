package dev.client.modules.impl.player;

import dev.client.event.classes.ReceivePacketEvent;
import dev.client.event.interfaces.IReceivePacketable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.modules.settings.impl.MultiBoxSetting;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;

@Environment(EnvType.CLIENT)
public class NoPush extends Module implements IReceivePacketable, IUtil {
   public final MultiBoxSetting options = new MultiBoxSetting().name("Options").booleanSettings(new BooleanSetting().name("Water").value(false), new BooleanSetting().name("Players").value(false), new BooleanSetting().name("Blocks").value(false), new BooleanSetting().name("FishingHook").value(false));

   public NoPush() {
      super(new ModuleBranding("NoPush", Category.PLAYER, "Убирает отталкивание игрока от выбранных объектов"));
      this.addSetting(this.options);
   }

   public void onReceivePacket(ReceivePacketEvent receivePacketEvent) {
      Packet rawPacket = receivePacketEvent.getPacket();
      if (rawPacket instanceof EntityStatusS2CPacket pac) {
         if (pac.getStatus() == 31) {
            Entity entity = pac.getEntity(mc.world);
            if (entity instanceof FishingBobberEntity) {
               FishingBobberEntity hook = (FishingBobberEntity)entity;
               if (this.options.getValueByName("FishingHook") && hook.getHookedEntity() == mc.player) {
                  receivePacketEvent.cancel();
               }
            }
         }
      }

   }
}

