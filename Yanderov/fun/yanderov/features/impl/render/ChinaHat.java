package fun.Yanderov.features.impl.render;

import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import fun.Yanderov.events.render.WorldRenderEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.ColorSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.color.ColorAssist;
import net.minecraft.class_10142;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1738;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import net.minecraft.class_293.class_5596;

public class ChinaHat extends Module {
   private final ColorSetting color = (new ColorSetting("Color", "Hat color")).value(ColorAssist.getColor(0, 0, 0, 255));
   private final SliderSettings transparency = (new SliderSettings("Transparency", "Overall hat transparency")).setValue(0.5F).range(0.1F, 1.0F);

   public ChinaHat() {
      super("ChinaHat", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.color, this.transparency});
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent event) {
      if (mc.field_1724 != null && !mc.field_1690.method_31044().method_31034()) {
         class_4587 stack = event.getStack();
         float partialTicks = event.getPartialTicks();
         class_243 playerPos = mc.field_1724.method_30950(partialTicks);
         float yOffset = (float)(playerPos.field_1351 + (double)this.getYOffset(mc.field_1724)) + 1.7F;
         stack.method_22903();
         RenderSystem.enableBlend();
         RenderSystem.blendFuncSeparate(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA, class_4535.ONE, class_4534.ZERO);
         RenderSystem.enableDepthTest();
         RenderSystem.depthFunc(515);
         RenderSystem.depthMask(false);
         RenderSystem.disableCull();
         RenderSystem.setShader(class_10142.field_53876);
         stack.method_22904(playerPos.field_1352, (double)yOffset, playerPos.field_1350);
         stack.method_22907(class_7833.field_40716.rotationDegrees((float)(System.currentTimeMillis() % 36000L) / 100.0F));
         class_287 cone = class_289.method_1348().method_60827(class_5596.field_27381, class_290.field_1576);
         float radiusValue = 0.6F;
         float heightValue = 0.3F;
         int rgb = this.color.getColor();
         int adjustedColor = ColorAssist.multAlpha(rgb, this.transparency.getValue());
         cone.method_22918(stack.method_23760().method_23761(), 0.0F, heightValue, 0.0F).method_39415(ColorAssist.multAlpha(ColorAssist.multBright(rgb, 0.86F), this.transparency.getValue()));
         float steps = 64.0F;
         double angleStep = (Math.PI * 2D) / (double)steps;

         for(int i = 0; (float)i <= steps; ++i) {
            float x = (float)(Math.cos((double)i * angleStep) * (double)radiusValue);
            float z = (float)(Math.sin((double)i * angleStep) * (double)radiusValue);
            cone.method_22918(stack.method_23760().method_23761(), x, 0.0F, z).method_39415(adjustedColor);
         }

         class_286.method_43433(cone.method_60800());
         RenderSystem.depthMask(true);
         RenderSystem.enableCull();
         RenderSystem.defaultBlendFunc();
         RenderSystem.depthFunc(513);
         stack.method_22909();
      }
   }

   private float getYOffset(class_1297 entity) {
      float offset = 0.15F;
      if (entity instanceof class_1309 livingEntity) {
         if (livingEntity.method_6118(class_1304.field_6169).method_7909() instanceof class_1738) {
            offset -= 0.065F;
         }
      }

      return offset;
   }
}

