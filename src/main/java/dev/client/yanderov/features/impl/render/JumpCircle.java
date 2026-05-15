package dev.client.yanderov.features.impl.render;

import dev.client.yanderov.events.player.JumpEvent;
import dev.client.yanderov.events.render.WorldRenderEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.ColorSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.geometry.Render3D;
import dev.client.yanderov.utils.math.Counter;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import org.joml.Vector4i;

public class JumpCircle extends Module {
   private final List circles = new ArrayList();
   private final class_2960 circleTexture = class_2960.method_60654("textures/circle2.png");
   private final SliderSettings maxSize = (new SliderSettings("Max Size", "ÐœÐ°ÐºÑÐ¸Ð¼Ð°Ð»ÑŒÐ½Ñ‹Ð¹ Ñ€Ð°Ð·Ð¼ÐµÑ€ ÐºÑ€ÑƒÐ³Ð°")).setValue(2.5F).range(1.0F, 3.0F);
   private final SliderSettings speed = (new SliderSettings("Speed", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð°Ð½Ð¸Ð¼Ð°Ñ†Ð¸Ð¸")).setValue(1000.0F).range(500.0F, 5000.0F);
   private final ColorSetting color = (new ColorSetting("Ð¦Ð²ÐµÑ‚", "Ð¦Ð²ÐµÑ‚ ÐºÑ€ÑƒÐ³Ð°")).value(ColorAssist.getColor(225, 225, 255, 255));

   public JumpCircle() {
      super("JumpCircle", "Jump Circle", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.maxSize, this.speed, this.color});
   }

   @EventHandler
   public void onJump(JumpEvent event) {
      if (mc.field_1724 != null && event.getPlayer() == mc.field_1724) {
         class_243 pos = new class_243(mc.field_1724.method_23317(), Math.floor(mc.field_1724.method_23318()) + 0.001, mc.field_1724.method_23321());
         this.circles.add(new Circle(pos, new Counter()));
      }
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      this.circles.removeIf((c) -> c.timer.passedMs((long)this.speed.getValue()));
      this.renderCircles();
   }

   private void renderCircles() {
      if (!this.circles.isEmpty()) {
         for(Circle circle : this.circles) {
            this.renderSingleCircle(circle);
         }

      }
   }

   private void renderSingleCircle(Circle circle) {
      float lifeTime = (float)circle.timer.getPassedTimeMs();
      float maxTime = this.speed.getValue();
      float progress = Math.min(lifeTime / maxTime, 1.0F);
      if (!(progress >= 1.0F)) {
         float scale = progress * this.maxSize.getValue();
         float alpha = 1.0F - progress;
         alpha = Math.max(0.0F, Math.min(1.0F, alpha));
         int finalColor = ColorAssist.multAlpha(this.color.getColor(), alpha);
         class_4184 camera = mc.method_1561().field_4686;
         class_243 cameraPos = camera.method_19326();
         class_243 circlePos = circle.pos();
         class_4587 matrixStack = new class_4587();
         matrixStack.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
         matrixStack.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0F));
         matrixStack.method_22904(circlePos.field_1352 - cameraPos.field_1352, circlePos.field_1351 - cameraPos.field_1351, circlePos.field_1350 - cameraPos.field_1350);
         matrixStack.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
         matrixStack.method_22907(class_7833.field_40714.rotationDegrees(90.0F));
         class_4587.class_4665 entry = matrixStack.method_23760();
         Vector4i colors = new Vector4i(finalColor, finalColor, finalColor, finalColor);
         Render3D.drawTexture(entry, this.circleTexture, -scale / 2.0F, -scale / 2.0F, scale, scale, colors, true);
      }
   }

   public static record Circle(class_243 pos, Counter timer) {
   }
}

