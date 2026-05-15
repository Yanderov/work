package fun.Yanderov.features.impl.render;

import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import fun.Yanderov.common.animation.Animation;
import fun.Yanderov.common.animation.Direction;
import fun.Yanderov.common.animation.implement.Decelerate;
import fun.Yanderov.common.repository.friend.FriendUtils;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.events.render.DrawEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.math.calc.Calculate;
import java.util.List;
import net.minecraft.class_10142;
import net.minecraft.class_1297;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_5498;
import net.minecraft.class_742;
import net.minecraft.class_7833;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

public class Arrows extends Module {
   private final class_2960 iconId = class_2960.method_60654("textures/features/arrows/arrow.png");
   private final Animation radiusAnim = (new Decelerate()).setMs(150).setValue((double)6.0F);
   private final SliderSettings radiusSetting = (new SliderSettings("Ð Ð°Ð´Ð¸ÑƒÑ", "Ð Ð°Ð´Ð¸ÑƒÑ ÑÑ‚Ñ€ÐµÐ»Ð¾Ðº")).setValue(50.0F).range(30, 100);
   private final SliderSettings sizeSetting = (new SliderSettings("Ð Ð°Ð·Ð¼ÐµÑ€", "Ð Ð°Ð·Ð¼ÐµÑ€ ÑÑ‚Ñ€ÐµÐ»Ð¾Ðº")).setValue(10.0F).range(8, 20);

   public Arrows() {
      super("Arrows", "Arrows", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.radiusSetting, this.sizeSetting});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      this.radiusAnim.setDirection(mc.field_1724.method_5624() ? Direction.FORWARDS : Direction.BACKWARDS);
   }

   @EventHandler
   public void onDraw(DrawEvent e) {
      class_4587 matrix = e.getDrawContext().method_51448();
      List<class_742> players = mc.field_1687.method_18456().stream().filter((p) -> p != mc.field_1724 && p.method_5805() && p.method_6032() > 0.0F).filter((p) -> !this.isGhostPlayer(p)).toList();
      float middleW = (float)mc.method_22683().method_4486() / 2.0F;
      float middleH = (float)mc.method_22683().method_4502() / 2.0F;
      float posY = middleH - this.radiusSetting.getValue() - this.radiusAnim.getOutput().floatValue();
      float size = this.sizeSetting.getValue();
      if (!mc.field_1690.field_1842 && mc.field_1690.method_31044().equals(class_5498.field_26664) && !players.isEmpty()) {
         RenderSystem.enableBlend();
         RenderSystem.disableCull();
         RenderSystem.disableDepthTest();
         RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_CONSTANT_ALPHA);
         RenderSystem.setShaderTexture(0, class_2960.method_60654("textures/features/arrows/arrow.png"));
         RenderSystem.setShader(class_10142.field_53880);
         class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
         players.forEach((player) -> {
            int color = FriendUtils.isFriend((class_1297)player) ? ColorAssist.getFriendColor() : ColorAssist.getClientColor();
            float yaw = getRotations(player) - mc.field_1724.method_36454();
            matrix.method_22903();
            matrix.method_46416(middleW, middleH, 0.0F);
            matrix.method_22907(class_7833.field_40718.rotationDegrees(yaw));
            matrix.method_46416(-middleW, -middleH, 0.0F);
            Matrix4f matrix4f = matrix.method_23760().method_23761();
            buffer.method_22918(matrix4f, middleW - size / 2.0F, posY + size, 0.0F).method_22913(0.0F, 1.0F).method_39415(ColorAssist.multAlpha(ColorAssist.multDark(color, 0.4F), 0.5F));
            buffer.method_22918(matrix4f, middleW + size / 2.0F, posY + size, 0.0F).method_22913(1.0F, 1.0F).method_39415(ColorAssist.multAlpha(ColorAssist.multDark(color, 0.4F), 0.5F));
            buffer.method_22918(matrix4f, middleW + size / 2.0F, posY, 0.0F).method_22913(1.0F, 0.0F).method_39415(color);
            buffer.method_22918(matrix4f, middleW - size / 2.0F, posY, 0.0F).method_22913(0.0F, 0.0F).method_39415(color);
            matrix.method_46416(middleW, middleH, 0.0F);
            matrix.method_22907(class_7833.field_40718.rotationDegrees(-yaw));
            matrix.method_46416(-middleW, -middleH, 0.0F);
            matrix.method_22909();
         });
         class_286.method_43433(buffer.method_60800());
         RenderSystem.enableDepthTest();
         RenderSystem.enableCull();
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableBlend();
      }

   }

   private boolean isGhostPlayer(class_742 player) {
      if (player.method_5797() != null) {
         String name = player.method_5797().getString();
         return name != null && name.startsWith("Ghost_");
      } else {
         return player.getClass().getSimpleName().equals("OtherClientPlayerEntity") && player.method_36455() == -30.0F;
      }
   }

   public static float getRotations(class_1297 entity) {
      double x = Calculate.interpolate(entity.method_23317(), entity.method_23317()) - Calculate.interpolate(mc.field_1724.method_23317(), mc.field_1724.method_23317());
      double z = Calculate.interpolate(entity.method_23321(), entity.method_23321()) - Calculate.interpolate(mc.field_1724.method_23321(), mc.field_1724.method_23321());
      return (float)(-(Math.atan2(x, z) * (180D / Math.PI)));
   }
}

