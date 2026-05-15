package fun.Yanderov.features.impl.player;

import fun.Yanderov.events.player.RotationUpdateEvent;
import fun.Yanderov.events.render.WorldRenderEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.display.geometry.Render3D;
import fun.Yanderov.utils.features.aura.utils.MathAngle;
import fun.Yanderov.utils.features.aura.warp.TurnsConfig;
import fun.Yanderov.utils.features.aura.warp.TurnsConnection;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import fun.Yanderov.utils.math.task.TaskPriority;
import java.util.Comparator;
import java.util.Objects;
import net.minecraft.class_1268;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_638;

public class Nuker extends Module {
   public class_2338 pos;
   private class_265 shape;
   private final BooleanSetting rotateSetting = (new BooleanSetting("ÐŸÐ¾Ð²Ð¾Ñ€Ð¾Ñ‚", "Ð›Ð¾Ð¼Ð°Ñ‚ÑŒ Ð±Ð»Ð¾ÐºÐ¸ Ð½Ð¸Ð¶Ðµ Ð¸Ð³Ñ€Ð¾ÐºÐ°")).setValue(true);
   private final BooleanSetting downSetting = (new BooleanSetting("Ð’Ð½Ð¸Ð·", "Ð›Ð¾Ð¼Ð°Ñ‚ÑŒ Ð±Ð»Ð¾ÐºÐ¸ Ð½Ð¸Ð¶Ðµ Ð¸Ð³Ñ€Ð¾ÐºÐ°")).setValue(true);
   private final SliderSettings radiusSetting = (new SliderSettings("Ð Ð°Ð´Ð¸ÑƒÑ", "Ð›Ð¾Ð¼Ð°ÐµÑ‚ Ð±Ð»Ð¾ÐºÐ¸ Ð² Ñ€Ð°Ð´Ð¸ÑƒÑÐµ Ð²Ð¾ÐºÑ€ÑƒÐ³ Ð²Ð°Ñ")).setValue(3.0F).range(1, 6);

   public Nuker() {
      super("Nuker", ModuleCategory.PLAYER);
      this.setup(new Setting[]{this.rotateSetting, this.downSetting, this.radiusSetting});
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      if (this.pos != null && this.shape != null && !this.shape.method_1110()) {
         Render3D.drawShape(this.pos, this.shape, ColorAssist.getClientColor(), 2.0F);
      }

   }

   @EventHandler
   public void onRotationUpdate(RotationUpdateEvent e) {
      if (e.getType() == 0) {
         this.pos = (class_2338)PlayerInteractionHelper.getCube(mc.field_1724.method_24515(), (float)this.radiusSetting.getInt(), (float)this.radiusSetting.getInt(), this.downSetting.isValue()).stream().filter(this::validBlock).min(Comparator.comparingDouble(this::blockPriority)).orElse((Object)null);
         if (this.pos != null) {
            if (this.rotateSetting.isValue()) {
               TurnsConnection.INSTANCE.rotateTo(MathAngle.calculateAngle(this.pos.method_46558()), TurnsConfig.DEFAULT, TaskPriority.HIGH_IMPORTANCE_1, this);
            }

            this.shape = mc.field_1687.method_8320(this.pos).method_26218(mc.field_1687, this.pos);
            mc.field_1761.method_2902(this.pos, class_2350.field_11036);
            mc.field_1724.method_6104(class_1268.field_5808);
         }
      }

   }

   private double blockPriority(class_2338 pos) {
      double var10000;
      switch (mc.field_1687.method_8320(pos).method_26204().method_63499().replace("block.minecraft.", "")) {
         case "ancient_debris" -> var10000 = (double)0.0F;
         case "diamond_ore" -> var10000 = (double)1.0F;
         case "emerald_ore" -> var10000 = (double)2.0F;
         case "gold_ore" -> var10000 = (double)3.0F;
         case "iron_ore" -> var10000 = (double)4.0F;
         case "lapis_ore" -> var10000 = (double)5.0F;
         case "redstone_ore" -> var10000 = (double)6.0F;
         default -> var10000 = mc.field_1724.method_5707(pos.method_46558());
      }

      return var10000;
   }

   private boolean validBlock(class_2338 pos) {
      class_2680 state = ((class_638)Objects.requireNonNull(mc.field_1687)).method_8320(pos);
      return !PlayerInteractionHelper.isAir(state) && state.method_26204() != class_2246.field_10382 && state.method_26204() != class_2246.field_10164 && state.method_26204() != class_2246.field_9987 && state.method_26204() != class_2246.field_10499;
   }

   public class_2338 getPos() {
      return this.pos;
   }

   public class_265 getShape() {
      return this.shape;
   }

   public BooleanSetting getRotateSetting() {
      return this.rotateSetting;
   }

   public BooleanSetting getDownSetting() {
      return this.downSetting;
   }

   public SliderSettings getRadiusSetting() {
      return this.radiusSetting;
   }
}

