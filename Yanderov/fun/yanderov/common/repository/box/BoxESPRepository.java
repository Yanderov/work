package fun.Yanderov.common.repository.box;

import fun.Yanderov.events.block.BlockUpdateEvent;
import fun.Yanderov.events.render.WorldLoadEvent;
import fun.Yanderov.events.render.WorldRenderEvent;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.client.managers.event.EventManager;
import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.display.geometry.Render3D;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.display.interfaces.QuickLogger;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import fun.Yanderov.utils.math.calc.Calculate;
import fun.Yanderov.utils.math.projection.Projection;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3545;

public class BoxESPRepository implements QuickImports, QuickLogger {
   private final Map boxes = new HashMap();
   public final Map entities = new HashMap();
   public final Map blocks = new HashMap();
   public boolean drawFill = true;

   public BoxESPRepository(EventManager eventManager) {
      eventManager.register(this);
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      this.boxes.forEach((pos, pair) -> {
         if (this.drawFill) {
            Render3D.drawShape(pos, (class_265)pair.method_15442(), (Integer)pair.method_15441(), 1.0F);
         } else {
            Render3D.drawShapeAlternative(pos, (class_265)pair.method_15442(), (Integer)pair.method_15441(), 1.0F, false, false);
         }

      });
      PlayerInteractionHelper.streamEntities().filter((ent) -> this.entities.containsKey(ent.method_5864()) && ent != mc.field_1724).forEach((ent) -> {
         int entityColor = (Integer)this.entities.get(ent.method_5864());
         int color = entityColor == 0 ? ColorAssist.getClientColor() : entityColor;
         class_238 box = ent.method_5829().method_997(Calculate.interpolate(ent).method_1020(ent.method_19538()));
         if (Projection.canSee(box)) {
            Render3D.drawBox(box, color, 1.0F);
         }

      });
   }

   @EventHandler
   public void onWorldLoad(WorldLoadEvent e) {
      this.boxes.clear();
   }

   @EventHandler
   public void onBlockUpdate(BlockUpdateEvent e) {
      class_2338 pos = e.pos();
      class_2680 state = e.state();
      class_2248 block = state.method_26204();
      switch (e.type()) {
         case LOAD:
            if (this.blocks.containsKey(block)) {
               this.putBox(pos, state, block);
            }
            break;
         case UPDATE:
            if (this.blocks.containsKey(block) && !this.boxes.containsKey(pos)) {
               this.putBox(pos, state, block);
            }

            if (this.boxes.containsKey(pos) && (state.method_26215() || !((class_265)((class_3545)this.boxes.get(pos)).method_15442()).equals(state.method_26218(mc.field_1687, pos)))) {
               this.boxes.remove(pos);
            }
            break;
         case UNLOAD:
            this.boxes.remove(pos);
      }

   }

   private void putBox(class_2338 pos, class_2680 state, class_2248 block) {
      class_265 shape = state.method_26218(mc.field_1687, pos);
      int blockColor = (Integer)this.blocks.get(block);
      int color = blockColor == 0 ? ColorAssist.replAlpha(state.method_26205(mc.field_1687, pos).field_16011, 1.0F) : blockColor;
      this.boxes.put(pos, new class_3545(shape, color));
   }
}

