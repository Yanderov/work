package dev.client.yanderov.features.impl.movement;

import antidaunleak.api.annotation.Native;
import dev.client.yanderov.events.item.UsingItemEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.utils.client.Instance;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import dev.client.yanderov.utils.math.script.Script;
import dev.client.yanderov.utils.math.time.StopWatch;
import net.minecraft.class_1268;
import net.minecraft.class_1839;

public class NoSlow extends Module {
   private final StopWatch notifWatch = new StopWatch();
   private final Script script = new Script();
   private boolean finish;
   public final SelectSetting itemMode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚Ð°", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ñ€ÐµÐ¶Ð¸Ð¼ Ð¾Ð±Ñ…Ð¾Ð´Ð°")).value("Grim Old", "Grim Tick");
   private int ticks = 0;

   public static NoSlow getInstance() {
      return (NoSlow)Instance.get(NoSlow.class);
   }

   public NoSlow() {
      super("NoSlow", "No Slow", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.itemMode});
   }

   @EventHandler
   public void onUpdate(TickEvent event) {
      if (mc.field_1724.method_6058() != class_1268.field_5808 && mc.field_1724.method_6058() != class_1268.field_5810) {
         this.ticks = 0;
      } else {
         ++this.ticks;
      }

   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   public void onUsingItem(UsingItemEvent e) {
      class_1268 first = mc.field_1724.method_6058();
      class_1268 second = first.equals(class_1268.field_5808) ? class_1268.field_5810 : class_1268.field_5808;
      switch (e.getType()) {
         case 1:
            switch (this.itemMode.getSelected()) {
               case "Grim Old":
                  if (mc.field_1724.method_6079().method_7976().equals(class_1839.field_8952) || mc.field_1724.method_6047().method_7976().equals(class_1839.field_8952)) {
                     PlayerInteractionHelper.interactItem(first);
                     PlayerInteractionHelper.interactItem(second);
                     e.cancel();
                  }

                  return;
               case "Grim Tick":
                  if ((float)this.ticks > 1.0F && mc.field_1724.method_6048() > 1) {
                     e.cancel();
                     this.ticks = 0;
                  }

                  return;
               default:
                  return;
            }
         case 2:
            while(!this.script.isFinished()) {
               this.script.update();
            }
      }

   }
}

