package dev.client.modules.impl.movement;

import dev.client.event.classes.Render3DEvent;
import dev.client.event.interfaces.IRenderable3D;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.PlayerModel;
import dev.client.util.IUtil;
import dev.client.util.math.TimerUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class Parkour extends Module implements IRenderable3D, IUtil {
   private final TimerUtil timerUtil = new TimerUtil();

   public Parkour() {
      super(new PlayerModel("Parkour", Category.MOVEMENT, "Прыгает на конце блока"));
   }

   public void onRender3D(Render3DEvent event) {
      if (mc.player.isOnGround() && !mc.options.jumpKey.isPressed() && !mc.world.getBlockCollisions(mc.player, mc.player.getBoundingBox().expand(-0.01D, 0.0D, -0.01D).offset(0.0D, -0.99, 0.0D)).iterator().hasNext() && this.timerUtil.every(150.0D)) {
         mc.player.jump();
      }

   }
}

