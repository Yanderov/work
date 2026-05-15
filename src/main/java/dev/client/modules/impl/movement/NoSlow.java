package dev.client.modules.impl.movement;

import dev.client.WildClient;
import dev.client.event.classes.TickEvent;
import dev.client.event.classes.UsingItemEvent;
import dev.client.event.interfaces.ITickable;
import dev.client.event.interfaces.IUsingItem;
import dev.client.mixins.other.IClientPlayerInteractionManagerMixin;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.modules.settings.impl.ModeSetting;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.SequencedPacketCreator;
import net.minecraft.item.consume.UseAction;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.util.Hand;

@Environment(EnvType.CLIENT)
public class NoSlow extends Module implements IUsingItem, ITickable, IUtil {
   private ModeSetting mode = new ModeSetting().name("Mode").value("Vanilla").modes("Vanilla", "Grim Old", "GrimAC", "FunTime", "SpookyTime");
   private int ticks = 0;

   public NoSlow() {
      super(new ModuleBranding("NoSlow", Category.MOVEMENT, "Убирает замедление от использования предметов"));
      this.addSetting(this.mode);
   }

   public void onUsing(UsingItemEvent event) {
      if (mc.player == null) return;
      
      Hand first = mc.player.getActiveHand();
      Hand second = first.equals(Hand.MAIN_HAND) ? Hand.OFF_HAND : Hand.MAIN_HAND;
      
      switch (event.getEventType()) {
         case ON:
            switch (this.mode.getValue()) {
               case "Vanilla":
                  event.cancel();
                  break;
               case "Grim Old":
                  if (mc.player.getOffHandStack().getUseAction().equals(UseAction.NONE) || mc.player.getMainHandStack().getUseAction().equals(UseAction.NONE)) {
                     interactItem(first);
                     interactItem(second);
                     event.cancel();
                  }
                  break;
               case "GrimAC":
                  if (mc.player.getActiveHand() == Hand.MAIN_HAND) {
                      mc.player.networkHandler.sendPacket(new PlayerInteractItemC2SPacket(Hand.OFF_HAND, 0, mc.player.getYaw(), mc.player.getPitch()));
                      event.cancel();
                  } else {
                      mc.player.networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(mc.player.getInventory().selectedSlot % 8 + 1));
                      mc.player.networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(mc.player.getInventory().selectedSlot));
                      event.cancel();
                  }
                  break;
               case "SpookyTime":
                  if ((float)this.ticks > 1.0F && mc.player.getItemUseTime() > 1) {
                     event.cancel();
                     this.ticks = 0;
                  }
                  break;
            }
         default:
      }
   }

   public static void sendSequencedPacket(SequencedPacketCreator packetCreator) {
      ((IClientPlayerInteractionManagerMixin)mc.interactionManager).invokeSendSequencedPacket(mc.world, packetCreator);
   }

   public static void interactItem(Hand hand) {
      sendSequencedPacket((i) -> new PlayerInteractItemC2SPacket(hand, i, WildClient.INSTANCE.getRotationManager().getYaw(), WildClient.INSTANCE.getRotationManager().getPitch()));
   }

   public void onTick(TickEvent event) {
      if (mc.player == null) return;
      
      if (!mc.player.isUsingItem()) {
         this.ticks = 0;
      } else {
         ++this.ticks;
      }

      if (this.mode.is("FunTime") && mc.player.isUsingItem() && mc.player.getActiveHand() == Hand.MAIN_HAND) {
          mc.player.networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(mc.player.getInventory().selectedSlot));
      }
   }
}
