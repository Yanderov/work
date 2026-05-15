package dev.client.yanderov.display.hud;

import dev.client.yanderov.utils.client.managers.api.draggable.AbstractDraggable;
import dev.client.yanderov.utils.client.packet.network.Network;
import dev.client.yanderov.utils.display.font.FontRenderer;
import dev.client.yanderov.utils.display.font.Fonts;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import dev.client.yanderov.utils.interactions.simulate.Simulations;
import dev.client.yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import java.util.Objects;
import net.minecraft.class_2338;
import net.minecraft.class_332;
import net.minecraft.class_746;

public class PlayerInfo extends AbstractDraggable {
   public PlayerInfo() {
      super("Player Info", 0, 0, 60, 0, false);
   }

   public void drawDraggable(class_332 context) {
      int offset = PlayerInteractionHelper.isChat(mc.field_1755) ? -28 : -15;
      this.setY(window.method_4502() + offset);
      class_2338 blockPos = ((class_746)Objects.requireNonNull(mc.field_1724)).method_24515();
      FontRenderer font = Fonts.getSize(14, Fonts.Type.DEFAULT);
      double var10000 = Simulations.getSpeedSqrt(mc.field_1724) * (double)20.0F;
      String bps = "Bps: " + Calculate.round(var10000, (double)0.25F);
      var10000 = (double)Network.TPS;
      String tps = "Tps: " + Calculate.round(var10000, (double)0.1F);
      int var11 = blockPos.method_10263();
      String xyz = "Xyz: " + var11 + ", " + blockPos.method_10264() + ", " + blockPos.method_10260();
      String text = xyz + " â€¢ " + tps + " â€¢ " + bps;
      float width = font.getStringWidth(text) + 10.0F;
      this.setWidth((int)width);
      font.drawGradientString(context.method_51448(), text, (double)(this.getX() + 3), (double)((float)this.getY() + 6.5F), (new Color(225, 225, 255, 255)).getRGB(), (new Color(255, 255, 255, 255)).getRGB());
   }
}

