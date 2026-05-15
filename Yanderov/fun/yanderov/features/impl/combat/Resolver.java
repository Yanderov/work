package fun.Yanderov.features.impl.combat;

import fun.Yanderov.events.render.WorldRenderEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.display.geometry.Render3D;
import net.minecraft.class_1309;
import net.minecraft.class_238;
import net.minecraft.class_243;

public class Resolver extends Module {
   private final BooleanSetting drawReal = (new BooleanSetting("ShowRealPos", "ÐŸÐ¾ÐºÐ°Ð·Ñ‹Ð²Ð°Ñ‚ÑŒ Ñ€ÐµÐ°Ð»ÑŒÐ½ÑƒÑŽ Ð¿Ð¾Ð·Ð¸Ñ†Ð¸ÑŽ")).setValue(false);

   public static Resolver getInstance() {
      return (Resolver)Instance.get(Resolver.class);
   }

   public Resolver() {
      super("Resolver", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.drawReal});
   }

   public class_243 resolveAura(class_243 origin, class_1309 target) {
      return this.isState() && target != null ? origin : origin;
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      if (this.isState() && this.drawReal.isValue()) {
         class_1309 target = Aura.getInstance().getCurrentTarget();
         if (target != null) {
            class_243 pos = target.method_19538();
            double half = (double)target.method_17681() / (double)2.0F;
            class_238 box = new class_238(pos.field_1352 - half, pos.field_1351, pos.field_1350 - half, pos.field_1352 + half, pos.field_1351 + (double)target.method_5751(), pos.field_1350 + half);
            int color = ColorAssist.multAlpha(-1, 0.8F);
            Render3D.drawBox(box, color, 1.0F);
         }
      }
   }
}

