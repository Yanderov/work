package fun.Yanderov.features.impl.movement;

import antidaunleak.api.annotation.Native;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import fun.Yanderov.utils.interactions.simulate.Simulations;
import net.minecraft.class_2246;

public class NoWeb extends Module {
   public final SelectSetting webMode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ñ€ÐµÐ¶Ð¸Ð¼ Ð¾Ð±Ñ…Ð¾Ð´Ð°")).value("Grim");

   public static NoWeb getInstance() {
      return (NoWeb)Instance.get(NoWeb.class);
   }

   public NoWeb() {
      super("NoWeb", "No Web", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.webMode});
   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   public void onTick(TickEvent e) {
      if (PlayerInteractionHelper.isPlayerInBlock(class_2246.field_10343)) {
         double[] speed = Simulations.calculateDirection(0.35);
         mc.field_1724.method_5762(speed[0], (double)0.0F, speed[1]);
         mc.field_1724.field_18276.field_1351 = mc.field_1690.field_1903.method_1434() ? (double)0.65F : (mc.field_1690.field_1832.method_1434() ? (double)-0.65F : (double)0.0F);
      }

   }
}

