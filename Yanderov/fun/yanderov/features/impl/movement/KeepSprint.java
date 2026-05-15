package fun.Yanderov.features.impl.movement;

import fun.Yanderov.events.player.KeepSprintEvent;
import fun.Yanderov.events.player.PlayerTravelEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import java.util.Objects;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;

public class KeepSprint extends Module implements QuickImports {
   private final BooleanSetting onAttack = (new BooleanSetting("On Attack", "ÐŸÐ¾Ð´Ð´ÐµÑ€Ð¶Ð¸Ð²Ð°Ñ‚ÑŒ ÑÐ¿Ñ€Ð¸Ð½Ñ‚ Ð¿Ð¾ÑÐ»Ðµ ÑƒÐ´Ð°Ñ€Ð°")).setValue(true);
   private final SliderSettings attackDuration;
   private final SliderSettings strength;
   private final BooleanSetting inWater;
   private final BooleanSetting inLava;
   private final BooleanSetting inWeb;
   private final SliderSettings applyInterval;
   private int keepAttackUntilAge;

   public KeepSprint() {
      super("KeepSprint", ModuleCategory.MOVEMENT);
      SliderSettings var10001 = (new SliderSettings("Ð”Ð»Ð¸Ñ‚ÐµÐ»ÑŒÐ½Ð¾ÑÑ‚ÑŒ", "Ð¡ÐºÐ¾Ð»ÑŒÐºÐ¾ Ñ‚Ð¸ÐºÐ¾Ð² Ð¿Ð¾Ð´Ð´ÐµÑ€Ð¶Ð¸Ð²Ð°Ñ‚ÑŒ Ð¿Ð¾ÑÐ»Ðµ ÑƒÐ´Ð°Ñ€Ð°")).setValue(10.0F).range(0, 40);
      BooleanSetting var10002 = this.onAttack;
      Objects.requireNonNull(var10002);
      this.attackDuration = var10001.visible(var10002::isValue);
      this.strength = (new SliderSettings("Ð¡Ð¸Ð»Ð°", "Ð¡Ð¸Ð»Ð° Ð¾Ñ‚Ð¼ÐµÐ½Ñ‹ Ð·Ð°Ð¼ÐµÐ´Ð»ÐµÐ½Ð¸Ñ, % (0-Ð²Ñ‹ÐºÐ», 100-Ð±ÐµÐ· Ð·Ð°Ð¼ÐµÐ´Ð»ÐµÐ½Ð¸Ñ)")).setValue(100.0F).range(0, 100);
      this.inWater = (new BooleanSetting("Ð’ Ð²Ð¾Ð´Ðµ", "Ð£Ð±Ð¸Ñ€Ð°Ñ‚ÑŒ Ð·Ð°Ð¼ÐµÐ´Ð»ÐµÐ½Ð¸Ðµ Ð² Ð²Ð¾Ð´Ðµ")).setValue(true);
      this.inLava = (new BooleanSetting("Ð’ Ð»Ð°Ð²Ðµ", "Ð£Ð±Ð¸Ñ€Ð°Ñ‚ÑŒ Ð·Ð°Ð¼ÐµÐ´Ð»ÐµÐ½Ð¸Ðµ Ð² Ð»Ð°Ð²Ðµ")).setValue(true);
      this.inWeb = (new BooleanSetting("Ð’ Ð¿Ð°ÑƒÑ‚Ð¸Ð½Ðµ", "Ð£Ð±Ð¸Ñ€Ð°Ñ‚ÑŒ Ð·Ð°Ð¼ÐµÐ´Ð»ÐµÐ½Ð¸Ðµ Ð² Ð¿Ð°ÑƒÑ‚Ð¸Ð½Ðµ")).setValue(true);
      this.applyInterval = (new SliderSettings("Ð˜Ð½Ñ‚ÐµÑ€Ð²Ð°Ð»", "ÐšÐ°Ðº Ñ‡Ð°ÑÑ‚Ð¾ Ð¿Ñ€Ð¸Ð¼ÐµÐ½ÑÑ‚ÑŒ ÐºÐ¾Ð¼Ð¿ÐµÐ½ÑÐ°Ñ†Ð¸ÑŽ (Ñ‚Ð¸ÐºÐ¸)")).setValue(1.0F).range(1, 10);
      this.keepAttackUntilAge = -1;
      this.setup(new Setting[]{this.onAttack, this.attackDuration, this.strength, this.inWater, this.inLava, this.inWeb, this.applyInterval});
   }

   @EventHandler
   public void onKeepSprint(KeepSprintEvent e) {
      if (this.isState() && mc.field_1724 != null) {
         if (this.onAttack.isValue() && this.strength.getValue() > 0.0F) {
            int dur = Math.max(0, (int)this.attackDuration.getValue());
            this.keepAttackUntilAge = mc.field_1724.field_6012 + dur;
         }

      }
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.isState() && mc.field_1724 != null) {
         if (e.getType() == 0) {
            if (!(this.strength.getValue() <= 0.0F)) {
               if (mc.field_1724.field_6012 <= this.keepAttackUntilAge) {
                  try {
                     mc.field_1724.method_5728(true);
                  } catch (Throwable var3) {
                  }
               }

            }
         }
      }
   }

   @EventHandler
   public void onTravel(PlayerTravelEvent e) {
      if (this.isState() && mc.field_1724 != null && mc.field_1687 != null) {
         if (e.isPre()) {
            int interval = Math.max(1, (int)this.applyInterval.getValue());
            if (mc.field_1724.field_6012 % interval == 0) {
               double pct = Math.max((double)0.0F, Math.min((double)100.0F, (double)this.strength.getValue()));
               if (!(pct <= (double)0.0F)) {
                  double k = pct / (double)100.0F;
                  boolean water = mc.field_1724.method_5799();
                  boolean lava = mc.field_1724.method_5771();
                  boolean web = this.isInCobweb();
                  class_243 m = e.getMotion();
                  double fx = (double)1.0F;
                  double fz = (double)1.0F;
                  if (mc.field_1724.field_6012 <= this.keepAttackUntilAge) {
                     double base = 1.3;
                     double f = (double)1.0F + (base - (double)1.0F) * k;
                     fx *= f;
                     fz *= f;
                  }

                  if (this.inWater.isValue() && water) {
                     double base = 1.6;
                     double f = (double)1.0F + (base - (double)1.0F) * k;
                     fx *= f;
                     fz *= f;
                  }

                  if (this.inLava.isValue() && lava) {
                     double base = 1.6;
                     double f = (double)1.0F + (base - (double)1.0F) * k;
                     fx *= f;
                     fz *= f;
                  }

                  if (this.inWeb.isValue() && web) {
                     double base = (double)2.5F;
                     double f = (double)1.0F + (base - (double)1.0F) * k;
                     fx *= f;
                     fz *= f;
                  }

                  if (fx != (double)1.0F || fz != (double)1.0F) {
                     e.setMotion(new class_243(m.field_1352 * fx, m.field_1351, m.field_1350 * fz));
                  }

               }
            }
         }
      }
   }

   private boolean isInCobweb() {
      try {
         class_238 box = mc.field_1724.method_5829();
         int minX = (int)Math.floor(box.field_1323);
         int maxX = (int)Math.floor(box.field_1320);
         int minY = (int)Math.floor(box.field_1322);
         int maxY = (int)Math.floor(box.field_1325);
         int minZ = (int)Math.floor(box.field_1321);
         int maxZ = (int)Math.floor(box.field_1324);

         for(int x = minX; x <= maxX; ++x) {
            for(int y = minY; y <= maxY; ++y) {
               for(int z = minZ; z <= maxZ; ++z) {
                  class_2338 pos = new class_2338(x, y, z);
                  if (mc.field_1687.method_8320(pos).method_27852(class_2246.field_10343)) {
                     return true;
                  }
               }
            }
         }
      } catch (Throwable var12) {
      }

      return false;
   }
}

