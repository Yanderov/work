package fun.Yanderov.features.impl.render;

import fun.Yanderov.events.render.WorldRenderEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.display.geometry.Render3D;
import net.minecraft.class_2338;
import net.minecraft.class_239;
import net.minecraft.class_3965;
import net.minecraft.class_239.class_240;

public class BlockOverlay extends Module {
   public static BlockOverlay getInstance() {
      return (BlockOverlay)Instance.get(BlockOverlay.class);
   }

   public BlockOverlay() {
      super("BlockOverlay", "Block Overlay", ModuleCategory.RENDER);
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      class_239 var3 = mc.field_1765;
      if (var3 instanceof class_3965 result) {
         if (result.method_17783().equals(class_240.field_1332)) {
            class_2338 pos = result.method_17777();
            Render3D.drawShapeAlternative(pos, mc.field_1687.method_8320(pos).method_26218(mc.field_1687, pos), ColorAssist.getClientColor(), 2.0F, true, true);
         }
      }

   }
}

