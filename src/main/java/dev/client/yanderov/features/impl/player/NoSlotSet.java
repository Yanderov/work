package dev.client.yanderov.features.impl.player;

import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import net.minecraft.class_2735;
import net.minecraft.class_2868;

public class NoSlotSet extends Module implements QuickImports {
   private final BooleanSetting useClientSlot = (new BooleanSetting("UseClientSlot", "ÐžÑ‚Ð¿Ñ€Ð°Ð²Ð»ÑÑ‚ÑŒ Ñ‚ÐµÐºÑƒÑ‰Ð¸Ð¹ ÐºÐ»Ð¸ÐµÐ½Ñ‚ÑÐºÐ¸Ð¹ ÑÐ»Ð¾Ñ‚")).setValue(true);
   private final SliderSettings fixedSlot = (new SliderSettings("FixedSlot", "Ð¡Ð»Ð¾Ñ‚ Ð´Ð»Ñ Ð¾Ñ‚Ð¿Ñ€Ð°Ð²ÐºÐ¸ (0-8)")).setValue(0.0F).range(0.0F, 8.0F).visible(() -> !this.useClientSlot.isValue());

   public NoSlotSet() {
      super("NoSlotSet", ModuleCategory.PLAYER);
      this.setup(new Setting[]{this.useClientSlot, this.fixedSlot});
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (e.getType() == PacketEvent.Type.RECEIVE) {
         if (e.getPacket() instanceof class_2735) {
            if (mc.field_1724 != null && mc.method_1562() != null) {
               e.cancel();
               int slot = 0;

               try {
                  slot = this.useClientSlot.isValue() ? mc.field_1724.method_31548().field_7545 : Math.max(0, Math.min(8, Math.round(this.fixedSlot.getValue())));
               } catch (Throwable var5) {
               }

               try {
                  PlayerInteractionHelper.sendPacketWithOutEvent(new class_2868(slot));
               } catch (Throwable var4) {
               }

            }
         }
      }
   }
}

