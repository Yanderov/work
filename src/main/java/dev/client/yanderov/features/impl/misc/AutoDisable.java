package dev.client.yanderov.features.impl.misc;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.events.player.EntityDeathEvent;
import dev.client.yanderov.features.impl.combat.Aura;
import dev.client.yanderov.features.impl.movement.Fly;
import dev.client.yanderov.features.impl.movement.NoClip;
import dev.client.yanderov.features.impl.movement.Speed;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.MultiSelectSetting;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_2561;
import net.minecraft.class_2708;

public class AutoDisable extends Module implements QuickImports {
   private final MultiSelectSetting on = (new MultiSelectSetting("On", "Ð¡Ð¾Ð±Ñ‹Ñ‚Ð¸Ñ Ð´Ð»Ñ Ð°Ð²Ñ‚Ð¾-Ð²Ñ‹ÐºÐ»ÑŽÑ‡ÐµÐ½Ð¸Ñ")).value("Flag", "Death").selected();

   public AutoDisable() {
      super("AutoDisable", ModuleCategory.MISC);
      this.setup(new Setting[]{this.on});
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (e.getPacket() instanceof class_2708) {
         if (this.on.isSelected("Flag")) {
            this.disableSelected("flag");
         }
      }
   }

   @EventHandler
   public void onDeath(EntityDeathEvent e) {
      if (this.on.isSelected("Death")) {
         try {
            class_1297 ent = e.getEntity();
            if (mc.field_1724 != null && ent == mc.field_1724) {
               this.disableSelected("your death");
            }
         } catch (Throwable var3) {
         }

      }
   }

   private void disableSelected(String reason) {
      List<Module> toDisable = new ArrayList();
      toDisable.add(this.get(Fly.class));
      toDisable.add(this.get(Speed.class));
      toDisable.add(this.get(NoClip.class));
      toDisable.add(this.get(Aura.class));
      int disabled = 0;

      for(Module m : toDisable) {
         if (m != null && m.isState()) {
            try {
               m.setState(false);
               ++disabled;
            } catch (Throwable var7) {
            }
         }
      }

      if (disabled > 0) {
         this.notifyUser("Disabled modules due to " + reason);
      }

   }

   private Module get(Class cls) {
      try {
         return (Module)YanderovIntegration.getInstance().getModuleRepository().modules().stream().filter((m) -> m.getClass() == cls).findFirst().orElse((Object)null);
      } catch (Throwable var3) {
         return null;
      }
   }

   private void notifyUser(String s) {
      try {
         if (mc.field_1724 != null) {
            mc.field_1724.method_7353(class_2561.method_30163(s), false);
         }
      } catch (Throwable var3) {
      }

   }
}

