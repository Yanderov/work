package fun.Yanderov.features.impl.movement;

import antidaunleak.api.annotation.Native;
import fun.Yanderov.events.player.PlayerTravelEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.impl.combat.Aura;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BindSetting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import fun.Yanderov.utils.interactions.simulate.Simulations;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1531;
import net.minecraft.class_1690;
import net.minecraft.class_243;
import net.minecraft.class_2828;

public class Speed extends Module {
   private final SelectSetting mode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ñ€ÐµÐ¶Ð¸Ð¼ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚Ð¸")).value("Normal", "Grim", "HolyWorld", "FunTime One Block", "Meta", "FunSky HVH", "45Degree").selected("Grim");
   private final SliderSettings speed = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ", "ÐÐ°ÑÑ‚Ñ€Ð¾Ð¹ÐºÐ° ÑÐºÐ¾Ñ€Ð¾ÑÑ‚Ð¸ Ð¿ÐµÑ€ÐµÐ´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ñ")).range(1.0F, 5.0F).setValue(1.5F).visible(() -> this.mode.isSelected("Normal"));
   private final BooleanSetting up = (new BooleanSetting("Ð£ÑÐ¸Ð»ÐµÐ½Ð¸Ðµ", "Ð£Ð²ÐµÐ»Ð¸Ñ‡Ð¸Ð²Ð°ÐµÑ‚ Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸ÑŽ ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ñ Ð´Ð¾ Ñ†ÐµÐ»Ð¸ Ð² Aura")).setValue(true).visible(() -> this.mode.isSelected("Grim"));
   private final SliderSettings strength = (new SliderSettings("Ð¡Ð¸Ð»Ð°", "Ð¤Ð°ÐºÑ‚Ð¾Ñ€ ÑƒÐ¼Ð½Ð¾Ð¶ÐµÐ½Ð¸Ñ Ð´Ð¾ Ñ†ÐµÐ»Ð¸")).range(1.0F, 6.0F).setValue(1.5F).visible(() -> this.mode.isSelected("Grim") && this.up.isValue());
   private final SliderSettings metaSpeed0 = (new SliderSettings("metaSpeed 0", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Meta Ð¿Ñ€Ð¸ Ð¾Ñ‚ÑÑƒÑ‚ÑÑ‚Ð²Ð¸Ð¸ ÑÑ„Ñ„ÐµÐºÑ‚Ð° Speed")).range(0.1F, 10.0F).setValue(1.0F).visible(() -> this.mode.isSelected("Meta"));
   private final SliderSettings metaSpeed1 = (new SliderSettings("metaSpeed 1", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Meta Ð¿Ñ€Ð¸ ÑÑ„Ñ„ÐµÐºÑ‚Ðµ Speed Ñ Ð°Ð¼Ð¿Ð»Ð¸Ñ‚ÑƒÐ´Ð¾Ð¹ 0")).range(0.1F, 10.0F).setValue(1.5F).visible(() -> this.mode.isSelected("Meta"));
   private final SliderSettings metaSpeed2 = (new SliderSettings("metaSpeed 2", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Meta Ð¿Ñ€Ð¸ ÑÑ„Ñ„ÐµÐºÑ‚Ðµ Speed Ñ Ð°Ð¼Ð¿Ð»Ð¸Ñ‚ÑƒÐ´Ð¾Ð¹ 1")).range(0.1F, 10.0F).setValue(2.0F).visible(() -> this.mode.isSelected("Meta"));
   private final SliderSettings metaSpeed3 = (new SliderSettings("metaSpeed 3", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Meta Ð¿Ñ€Ð¸ ÑÑ„Ñ„ÐµÐºÑ‚Ðµ Speed Ñ Ð°Ð¼Ð¿Ð»Ð¸Ñ‚ÑƒÐ´Ð¾Ð¹ 2 Ð¸ Ð²Ñ‹ÑˆÐµ")).range(0.1F, 10.0F).setValue(2.5F).visible(() -> this.mode.isSelected("Meta"));
   private final BooleanSetting metaGroundSpeed = (new BooleanSetting("metaGround", "ÐžÑ‚Ð´ÐµÐ»ÑŒÐ½Ð°Ñ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Meta Ð½Ð° Ð·ÐµÐ¼Ð»Ðµ Ð±ÐµÐ· Ð¿Ñ€Ñ‹Ð¶ÐºÐ¾Ð²")).setValue(false).visible(() -> this.mode.isSelected("Meta"));
   private final SliderSettings metaGroundSpeedValue = (new SliderSettings("metaGroundSpeed", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Meta Ð½Ð° Ð·ÐµÐ¼Ð»Ðµ Ð±ÐµÐ· Ð¿Ñ€Ñ‹Ð¶ÐºÐ¾Ð²")).range(0.1F, 10.0F).setValue(1.0F).visible(() -> this.mode.isSelected("Meta") && this.metaGroundSpeed.isValue());
   private final BooleanSetting metaDamageBoost = (new BooleanSetting("damageBoost", "Ð£Ð²ÐµÐ»Ð¸Ñ‡Ð¸Ð²Ð°ÐµÑ‚ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Meta Ð¿Ñ€Ð¸ Ð¿Ð¾Ð»ÑƒÑ‡ÐµÐ½Ð¸Ð¸ ÑƒÑ€Ð¾Ð½Ð°")).setValue(true).visible(() -> this.mode.isSelected("Meta") || this.mode.isSelected("FunSky HVH"));
   private final SelectSetting boostMethod = (new SelectSetting("boostMethod", "ÐœÐµÑ‚Ð¾Ð´ ÑƒÑÐ¸Ð»ÐµÐ½Ð¸Ñ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚Ð¸")).value("Linear", "Interpolation").selected("Linear").visible(() -> (this.mode.isSelected("Meta") || this.mode.isSelected("FunSky HVH")) && this.metaDamageBoost.isValue());
   private final SliderSettings damageBoostDuration = (new SliderSettings("Ð”Ð»Ð¸Ñ‚ÐµÐ»ÑŒÐ½Ð¾ÑÑ‚ÑŒ ÑƒÑÐ¸Ð»ÐµÐ½Ð¸Ñ", "Ð”Ð»Ð¸Ñ‚ÐµÐ»ÑŒÐ½Ð¾ÑÑ‚ÑŒ Ð±ÑƒÑÑ‚Ð° Ð¾Ñ‚ ÑƒÑ€Ð¾Ð½Ð° (Ð¼Ñ)")).range(0.0F, 1000.0F).setValue(200.0F).visible(() -> (this.mode.isSelected("Meta") || this.mode.isSelected("FunSky HVH")) && this.metaDamageBoost.isValue());
   private final SliderSettings metaDamageMinSpeed = (new SliderSettings("ÐœÐ¸Ð½Ð¸Ð¼Ð°Ð»ÑŒÐ½Ð°Ñ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ñ€Ð¸ Ð¿Ð¾Ð»ÑƒÑ‡ÐµÐ½Ð¸Ð¸ ÑƒÑ€Ð¾Ð½Ð°", "ÐœÐ¸Ð½Ð¸Ð¼Ð°Ð»ÑŒÐ½Ð°Ñ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Meta Ð¿Ñ€Ð¸ Ð¿Ð¾Ð»ÑƒÑ‡ÐµÐ½Ð¸Ð¸ ÑƒÑ€Ð¾Ð½Ð°")).range(0.1F, 10.0F).setValue(1.5F).visible(() -> (this.mode.isSelected("Meta") || this.mode.isSelected("FunSky HVH")) && this.metaDamageBoost.isValue());
   private final SliderSettings metaDamageForwardBoost = (new SliderSettings("Ð£ÑÐ¸Ð»ÐµÐ½Ð¸Ðµ Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ñ Ð²Ð¿ÐµÑ€ÐµÐ´ Ð¿Ñ€Ð¸ Ð¿Ð¾Ð»ÑƒÑ‡ÐµÐ½Ð¸Ð¸ ÑƒÑ€Ð¾Ð½Ð°", "Ð£ÑÐ¸Ð»ÐµÐ½Ð¸Ðµ Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ñ Ð²Ð¿ÐµÑ€ÐµÐ´ Ð¿Ñ€Ð¸ Ð¿Ð¾Ð»ÑƒÑ‡ÐµÐ½Ð¸Ð¸ ÑƒÑ€Ð¾Ð½Ð°")).range(0.0F, 10.0F).setValue(0.5F).visible(() -> (this.mode.isSelected("Meta") || this.mode.isSelected("FunSky HVH")) && this.metaDamageBoost.isValue());
   private final BooleanSetting offNoSpeed = (new BooleanSetting("offNoSpeed", "ÐÐµ ÑƒÑÐ¸Ð»Ð¸Ð²Ð°Ñ‚ÑŒ, ÐµÑÐ»Ð¸ Ð½ÐµÑ‚ ÑÑ„Ñ„ÐµÐºÑ‚Ð° Speed")).setValue(false).visible(() -> (this.mode.isSelected("Meta") || this.mode.isSelected("FunSky HVH")) && this.metaDamageBoost.isValue());
   private final BooleanSetting metaBoost = (new BooleanSetting("Ð£ÑÐ¸Ð»ÐµÐ½Ð¸Ðµ", "Ð£Ð²ÐµÐ»Ð¸Ñ‡Ð¸Ð²Ð°ÐµÑ‚ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ñ€Ð¸ Ð½Ð°Ð¶Ð°Ñ‚Ð¸Ð¸ ÐºÐ»Ð°Ð²Ð¸ÑˆÐ¸")).setValue(true).visible(() -> this.mode.isSelected("Meta") || this.mode.isSelected("FunSky HVH"));
   private final SliderSettings metaBoostMultiplier = (new SliderSettings("ÐšÐ¾ÑÑ„Ñ„Ð¸Ñ†Ð¸ÐµÐ½Ñ‚ ÑƒÑÐ¸Ð»ÐµÐ½Ð¸Ñ", "ÐšÐ¾ÑÑ„Ñ„Ð¸Ñ†Ð¸ÐµÐ½Ñ‚ ÑƒÑÐ¸Ð»ÐµÐ½Ð¸Ñ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚Ð¸ Ð¿Ñ€Ð¸ Ð½Ð°Ð¶Ð°Ñ‚Ð¸Ð¸ ÐºÐ»Ð°Ð²Ð¸ÑˆÐ¸")).range(1.0F, 10.0F).setValue(2.0F).visible(() -> (this.mode.isSelected("Meta") || this.mode.isSelected("FunSky HVH")) && this.metaBoost.isValue());
   private final BindSetting metaBoostKey = (new BindSetting("ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° ÑƒÑÐ¸Ð»ÐµÐ½Ð¸Ñ", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð´Ð»Ñ ÑƒÑÐ¸Ð»ÐµÐ½Ð¸Ñ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚Ð¸")).visible(() -> (this.mode.isSelected("Meta") || this.mode.isSelected("FunSky HVH")) && this.metaBoost.isValue());
   private final BooleanSetting funskyOnGround = (new BooleanSetting("ÐÐ° Ð·ÐµÐ¼Ð»Ðµ", "ÐžÑÐ¾Ð±Ð°Ñ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð´Ð»Ñ Speed 3 Ð½Ð° Ð·ÐµÐ¼Ð»Ðµ")).setValue(false).visible(() -> this.mode.isSelected("FunSky HVH"));
   private final SliderSettings funskyGroundSpeed3 = (new SliderSettings("Ground Speed 3", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ñ€Ð¸ ÑÑ„Ñ„ÐµÐºÑ‚Ðµ Speed 3 Ð½Ð° Ð·ÐµÐ¼Ð»Ðµ")).range(0.1F, 2.0F).setValue(0.56F).visible(() -> this.mode.isSelected("FunSky HVH") && this.funskyOnGround.isValue());
   private final BooleanSetting debuffSpeed = (new BooleanSetting("Debuff Speed", "Ð¤Ð¸ÐºÑÐ¸Ñ€Ð¾Ð²Ð°Ð½Ð½Ð°Ñ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ñ€Ð¸ ÑÑ„Ñ„ÐµÐºÑ‚Ðµ Ð·Ð°Ð¼ÐµÐ´Ð»ÐµÐ½Ð¸Ñ")).setValue(false).visible(() -> this.mode.isSelected("Meta") || this.mode.isSelected("FunSky HVH"));
   private final SliderSettings debuffSpeedValue = (new SliderSettings("Debuff Speed Value", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ñ€Ð¸ Ð·Ð°Ð¼ÐµÐ´Ð»ÐµÐ½Ð¸Ð¸ (Ð»ÑŽÐ±Ð¾Ð¹ ÑƒÑ€Ð¾Ð²ÐµÐ½ÑŒ)")).range(0.1F, 2.0F).setValue(0.3F).visible(() -> (this.mode.isSelected("Meta") || this.mode.isSelected("FunSky HVH")) && this.debuffSpeed.isValue());
   private final BooleanSetting debuffSpeedOff = (new BooleanSetting("Off (vanilla)", "Ð˜Ð³Ð½Ð¾Ñ€Ð¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒ Ð·Ð°Ð´Ð°Ð½Ð½ÑƒÑŽ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ, Ð¾ÑÑ‚Ð°Ð²Ð¸Ñ‚ÑŒ Ð²Ð°Ð½Ð¸Ð»ÑŒ")).setValue(false).visible(() -> (this.mode.isSelected("Meta") || this.mode.isSelected("FunSky HVH")) && this.debuffSpeed.isValue());
   private final SliderSettings holyLowBoost = (new SliderSettings("Holy Low Boost", "Ð£ÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ðµ Ð¿Ñ€Ð¸ Ð¼ÐµÐ´Ð»ÐµÐ½Ð½Ð¾Ð¹ Ñ†ÐµÐ»Ð¸")).range(0.0F, 0.1F).setValue(0.02F).visible(() -> this.mode.isSelected("HolyWorld"));
   private final SliderSettings holyHighBoost = (new SliderSettings("Holy High Boost", "Ð£ÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ðµ Ð¿Ñ€Ð¸ Ð±Ñ‹ÑÑ‚Ñ€Ð¾Ð¹ Ñ†ÐµÐ»Ð¸")).range(0.0F, 0.1F).setValue(0.036F).visible(() -> this.mode.isSelected("HolyWorld"));
   private final SliderSettings holyScanRadius = (new SliderSettings("Holy Scan Radius", "Ð Ð°Ð´Ð¸ÑƒÑ Ð¿Ð¾Ð¸ÑÐºÐ° ÑÑƒÑ‰Ð½Ð¾ÑÑ‚ÐµÐ¹")).range(1.0F, 10.0F).setValue(5.0F).visible(() -> this.mode.isSelected("HolyWorld"));
   private final SliderSettings holyCheckRadius = (new SliderSettings("Holy Check Radius", "Ð Ð°Ð´Ð¸ÑƒÑ Ð¿Ñ€Ð¾Ð²ÐµÑ€ÐºÐ¸ Ð±Ñ‹ÑÑ‚Ñ€Ñ‹Ñ… ÐºÐ¾Ð»Ð»Ð¸Ð·Ð¸Ð¹")).range(1.0F, 10.0F).setValue(4.0F).visible(() -> this.mode.isSelected("HolyWorld"));
   private final BooleanSetting metaCollisionBoost = (new BooleanSetting("Collision Boost", "Ð£ÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ðµ Ð¾Ñ‚ ÑÑ‚Ð¾Ð»ÐºÐ½Ð¾Ð²ÐµÐ½Ð¸Ð¹ ÐºÐ°Ðº Ð² Grim")).setValue(true).visible(() -> this.mode.isSelected("Meta"));
   private final SliderSettings metaCollisionPower = (new SliderSettings("Collision Power", "Ð¡Ð¸Ð»Ð° ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ñ Ð¾Ñ‚ ÑÑ‚Ð¾Ð»ÐºÐ½Ð¾Ð²ÐµÐ½Ð¸Ð¹")).range(0.01F, 0.5F).setValue(0.08F).visible(() -> this.mode.isSelected("Meta") && this.metaCollisionBoost.isValue());
   private float pendingSpeed = 1.0F;
   private float boostedSpeed = 1.0F;
   private double lastYForJump = (double)0.0F;
   private long jumpZeroUntilMs = 0L;
   private long damageBoostEndTime = 0L;
   private final Map previousPositions = new HashMap();

   public Speed() {
      super("Speed", "Speed", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.mode, this.up, this.strength, this.speed, this.metaSpeed0, this.metaSpeed1, this.metaSpeed2, this.metaSpeed3, this.metaGroundSpeed, this.metaGroundSpeedValue, this.metaDamageBoost, this.boostMethod, this.damageBoostDuration, this.metaDamageMinSpeed, this.metaDamageForwardBoost, this.offNoSpeed, this.metaBoost, this.metaBoostMultiplier, this.metaBoostKey, this.funskyOnGround, this.funskyGroundSpeed3, this.debuffSpeed, this.debuffSpeedValue, this.debuffSpeedOff, this.holyLowBoost, this.holyHighBoost, this.holyScanRadius, this.holyCheckRadius, this.metaCollisionBoost, this.metaCollisionPower});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.mode.isSelected("Normal")) {
         Simulations.setVelocity((double)(this.speed.getValue() / 3.0F));
      } else {
         long now = System.currentTimeMillis();
         if (this.mode.isSelected("Meta")) {
            int amp = 0;
            boolean hasSpeed = false;

            try {
               hasSpeed = mc.field_1724.method_6059(class_1294.field_5904);
               if (hasSpeed) {
                  amp = mc.field_1724.method_6112(class_1294.field_5904).method_5578();
               }
            } catch (Throwable var15) {
            }

            float baseSpeed;
            if (!hasSpeed) {
               baseSpeed = this.metaSpeed0.getValue();
            } else if (amp == 0) {
               baseSpeed = this.metaSpeed1.getValue();
            } else if (amp == 1) {
               baseSpeed = this.metaSpeed2.getValue();
            } else {
               baseSpeed = this.metaSpeed3.getValue();
            }

            try {
               if (this.debuffSpeed.isValue() && mc.field_1724.method_6059(class_1294.field_5909) && !this.debuffSpeedOff.isValue()) {
                  baseSpeed = this.debuffSpeedValue.getValue();
               }
            } catch (Throwable var14) {
            }

            boolean boostAllowed = !this.offNoSpeed.isValue() || hasSpeed;
            if (this.metaDamageBoost.isValue() && boostAllowed) {
               try {
                  if (mc.field_1724.field_6235 >= 1) {
                     long dur = (long)this.damageBoostDuration.getValue();
                     if (dur < 0L) {
                        dur = 0L;
                     }

                     this.damageBoostEndTime = now + dur;
                  }
               } catch (Throwable var13) {
               }
            }

            boolean boostActive = this.metaDamageBoost.isValue() && boostAllowed && (mc.field_1724.field_6235 >= 1 || now < this.damageBoostEndTime);
            float targetSpeed = baseSpeed;
            if (boostActive) {
               targetSpeed = Math.max(baseSpeed, this.metaDamageMinSpeed.getValue());
            }

            if (this.boostMethod.isSelected("Linear")) {
               this.boostedSpeed = targetSpeed;
            } else {
               this.boostedSpeed += (targetSpeed - this.boostedSpeed) * 0.25F;
            }

            this.pendingSpeed = this.boostedSpeed;
         }

         if (this.mode.isSelected("FunSky HVH")) {
            int amp = 0;
            boolean hasSpeed = false;

            try {
               hasSpeed = mc.field_1724.method_6059(class_1294.field_5904);
               if (hasSpeed) {
                  amp = mc.field_1724.method_6112(class_1294.field_5904).method_5578();
               }
            } catch (Throwable var12) {
            }

            float baseSpeed;
            if (!hasSpeed) {
               baseSpeed = 0.25F;
            } else if (amp == 0) {
               baseSpeed = 0.25F;
            } else if (amp == 1) {
               baseSpeed = 0.4F;
            } else {
               baseSpeed = 0.56F;
            }

            if (this.funskyOnGround.isValue() && mc.field_1724.method_24828() && amp >= 2) {
               baseSpeed = this.funskyGroundSpeed3.getValue();
            }

            try {
               if (this.debuffSpeed.isValue() && mc.field_1724.method_6059(class_1294.field_5909) && !this.debuffSpeedOff.isValue()) {
                  baseSpeed = this.debuffSpeedValue.getValue();
               }
            } catch (Throwable var11) {
            }

            boolean boostAllowed = !this.offNoSpeed.isValue() || hasSpeed;
            if (this.metaDamageBoost.isValue() && boostAllowed) {
               try {
                  if (mc.field_1724.field_6235 >= 1) {
                     long dur = (long)this.damageBoostDuration.getValue();
                     if (dur < 0L) {
                        dur = 0L;
                     }

                     this.damageBoostEndTime = System.currentTimeMillis() + dur;
                  }
               } catch (Throwable var10) {
               }
            }

            boolean boostActive = this.metaDamageBoost.isValue() && boostAllowed && (mc.field_1724.field_6235 >= 1 || System.currentTimeMillis() < this.damageBoostEndTime);
            float targetSpeed = baseSpeed;
            if (boostActive) {
               targetSpeed = Math.max(baseSpeed, this.metaDamageMinSpeed.getValue());
            }

            if (this.boostMethod.isSelected("Linear")) {
               this.boostedSpeed = targetSpeed;
            } else {
               this.boostedSpeed += (targetSpeed - this.boostedSpeed) * 0.25F;
            }

            this.pendingSpeed = this.boostedSpeed;
         }

      }
   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   public void onMotion(PlayerTravelEvent e) {
      if (this.mode.isSelected("HolyWorld") && e.isPre() && Simulations.hasPlayerMovement() && mc.field_1724 != null && mc.field_1687 != null && this.isMoving()) {
         double scanRadius = (double)this.holyScanRadius.getValue();

         for(class_1297 entity : mc.field_1687.method_18112()) {
            if (entity != mc.field_1724 && (entity instanceof class_1309 || entity instanceof class_1690) && !(entity instanceof class_1531) && mc.field_1724.method_5829().method_1014(scanRadius).method_994(entity.method_5829())) {
               double distanceX = Math.abs(mc.field_1724.method_23317() - entity.method_23317());
               double distanceZ = Math.abs(mc.field_1724.method_23321() - entity.method_23321());
               double activationDistanceX = 2.1;
               double activationDistanceZ = 1.3;
               if (!(distanceX > activationDistanceX) && !(distanceZ > activationDistanceZ)) {
                  double entitySpeed = this.getEntitySpeed(entity);
                  if (entitySpeed < (double)5.0F) {
                     double boostAmount = (double)this.holyLowBoost.getValue();
                     int collisions = 0;

                     for(class_1297 collisionEntity : mc.field_1687.method_18112()) {
                        if (collisionEntity != mc.field_1724 && (collisionEntity instanceof class_1309 || collisionEntity instanceof class_1690) && !(collisionEntity instanceof class_1531) && mc.field_1724.method_5829().method_1014(0.1).method_994(collisionEntity.method_5829())) {
                           ++collisions;
                        }
                     }

                     if (collisions > 0) {
                        double[] motion = Simulations.forward(boostAmount);
                        mc.field_1724.method_5762(motion[0], (double)0.0F, motion[1]);
                     }
                  } else {
                     double boostAmount = (double)this.holyHighBoost.getValue();
                     double checkRadius = (double)this.holyCheckRadius.getValue();
                     int collisions = 0;

                     for(class_1297 collisionEntity : mc.field_1687.method_18112()) {
                        if (collisionEntity != mc.field_1724 && (collisionEntity instanceof class_1309 || collisionEntity instanceof class_1690) && !(collisionEntity instanceof class_1531) && mc.field_1724.method_5829().method_1014(checkRadius).method_994(collisionEntity.method_5829()) && mc.field_1724.method_5858(collisionEntity) <= checkRadius * checkRadius) {
                           ++collisions;
                        }
                     }

                     if (collisions > 0) {
                        double[] motion = Simulations.forward(boostAmount);
                        mc.field_1724.method_5762(motion[0], (double)0.0F, motion[1]);
                     }
                  }
                  break;
               }
            }
         }
      }

      if (this.mode.isSelected("45Degree") && e.isPre() && mc.field_1724 != null && mc.method_1562() != null) {
         boolean moving = mc.field_1690.field_1894.method_1434() || mc.field_1690.field_1881.method_1434() || mc.field_1690.field_1913.method_1434() || mc.field_1690.field_1849.method_1434();
         boolean right = mc.field_1690.field_1849.method_1434() && !mc.field_1690.field_1913.method_1434();
         double curY = mc.field_1724.method_23318();
         if (curY > this.lastYForJump + 0.02 && !mc.field_1724.method_24828()) {
            this.jumpZeroUntilMs = System.currentTimeMillis() + 60L;
         }

         this.lastYForJump = curY;
         float baseYaw = mc.field_1724.method_36454();
         float spoofYaw;
         if (moving && System.currentTimeMillis() >= this.jumpZeroUntilMs) {
            float offset = right ? -45.0F : 45.0F;
            spoofYaw = baseYaw + offset;
         } else {
            spoofYaw = baseYaw;
         }

         try {
            mc.method_1562().method_52787(new class_2828.class_2830(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321(), spoofYaw, mc.field_1724.method_36455(), mc.field_1724.method_24828(), false));
         } catch (Throwable var25) {
         }
      }

      if (this.mode.isSelected("FunTime One Block") && !mc.field_1724.method_5681() && !mc.field_1724.method_6128() && !mc.field_1724.method_5715() && mc.field_1724.method_5829().field_1325 - mc.field_1724.method_5829().field_1322 < (double)1.5F) {
         float motion = mc.field_1724.method_6059(class_1294.field_5904) ? 0.32F : 0.28F;
         Simulations.setVelocity((double)motion);
      }

      if (this.mode.isSelected("Meta") && e.isPre() && Simulations.hasPlayerMovement()) {
         if (this.debuffSpeed.isValue() && this.debuffSpeedOff.isValue()) {
            try {
               if (mc.field_1724.method_6059(class_1294.field_5909)) {
                  return;
               }
            } catch (Throwable var24) {
            }
         }

         float useSpeed = this.pendingSpeed;
         if (this.metaGroundSpeed.isValue() && mc.field_1724.method_24828() && !mc.field_1690.field_1903.method_1434()) {
            useSpeed = this.metaGroundSpeedValue.getValue();
         }

         if (this.metaBoost.isValue()) {
            int code = this.metaBoostKey.getKey();
            if (code != -1 && PlayerInteractionHelper.isKey(PlayerInteractionHelper.getKeyType(code), code)) {
               useSpeed *= this.metaBoostMultiplier.getValue();
            }
         }

         if (this.metaDamageBoost.isValue()) {
            try {
               boolean hasSpeed = mc.field_1724.method_6059(class_1294.field_5904);
               boolean boostAllowed = !this.offNoSpeed.isValue() || hasSpeed;
               if (boostAllowed && mc.field_1724.field_6235 >= 1 && this.metaDamageForwardBoost.getValue() > 0.0F) {
                  double[] add = Simulations.forward((double)this.metaDamageForwardBoost.getValue());
                  mc.field_1724.method_5762(add[0] * 0.001, (double)0.0F, add[1] * 0.001);
               }
            } catch (Throwable var27) {
            }
         }

         if (this.metaCollisionBoost.isValue()) {
            int collisions = 0;
            float box = 0.4F;
            if (Aura.getInstance().isState() && Aura.getInstance().getTarget() != null && Aura.getInstance().getTarget().method_5624() && mc.field_1724.method_5624() && this.up.isValue()) {
               box = this.strength.getValue();
            }

            for(class_1297 ent : mc.field_1687.method_18112()) {
               if (ent != mc.field_1724 && !(ent instanceof class_1531) && (ent instanceof class_1309 || ent instanceof class_1690) && mc.field_1724.method_5829().method_1014((double)box).method_994(ent.method_5829())) {
                  ++collisions;
               }
            }

            if (collisions > 0) {
               useSpeed += this.metaCollisionPower.getValue() * (float)collisions;
            }
         }

         Simulations.setVelocity((double)useSpeed);
      }

      if (this.mode.isSelected("FunSky HVH") && e.isPre() && Simulations.hasPlayerMovement()) {
         if (mc.field_1724.method_5799() && !mc.field_1724.method_5681()) {
            return;
         }

         if (this.debuffSpeed.isValue() && this.debuffSpeedOff.isValue()) {
            try {
               if (mc.field_1724.method_6059(class_1294.field_5909)) {
                  return;
               }
            } catch (Throwable var23) {
            }
         }

         float useSpeed = this.pendingSpeed;
         if (this.metaBoost.isValue()) {
            int code = this.metaBoostKey.getKey();
            if (code != -1 && PlayerInteractionHelper.isKey(PlayerInteractionHelper.getKeyType(code), code)) {
               useSpeed *= this.metaBoostMultiplier.getValue();
            }
         }

         if (this.metaDamageBoost.isValue()) {
            try {
               boolean hasSpeed = mc.field_1724.method_6059(class_1294.field_5904);
               boolean boostAllowed = !this.offNoSpeed.isValue() || hasSpeed;
               if (boostAllowed && mc.field_1724.field_6235 >= 1 && this.metaDamageForwardBoost.getValue() > 0.0F) {
                  double[] add = Simulations.forward((double)this.metaDamageForwardBoost.getValue());
                  mc.field_1724.method_5762(add[0] * 0.001, (double)0.0F, add[1] * 0.001);
               }
            } catch (Throwable var26) {
            }
         }

         Simulations.setVelocity((double)useSpeed);
      }

      if (this.mode.isSelected("Grim") && e.isPre() && Simulations.hasPlayerMovement()) {
         int collisions = 0;
         float box = 0.4F;
         if (Aura.getInstance().isState() && Aura.getInstance().getTarget() != null && Aura.getInstance().getTarget().method_5624() && mc.field_1724.method_5624() && this.up.isValue()) {
            box = this.strength.getValue();
         }

         for(class_1297 ent : mc.field_1687.method_18112()) {
            if (ent != mc.field_1724 && !(ent instanceof class_1531) && (ent instanceof class_1309 || ent instanceof class_1690) && mc.field_1724.method_5829().method_1014((double)box).method_994(ent.method_5829())) {
               ++collisions;
            }
         }

         double[] motion = Simulations.forward(0.08 * (double)collisions);
         mc.field_1724.method_5762(motion[0], (double)0.0F, motion[1]);
      }

   }

   private double getEntitySpeed(class_1297 entity) {
      class_243 currentPos = entity.method_19538();
      class_243 previousPos = (class_243)this.previousPositions.getOrDefault(entity, currentPos);
      double dx = currentPos.field_1352 - previousPos.field_1352;
      double dz = currentPos.field_1350 - previousPos.field_1350;
      double speedValue = Math.sqrt(dx * dx + dz * dz) * (double)20.0F;
      this.previousPositions.put(entity, currentPos);
      return speedValue;
   }

   private boolean isMoving() {
      return mc.field_1690.field_1894.method_1434() || mc.field_1690.field_1881.method_1434() || mc.field_1690.field_1913.method_1434() || mc.field_1690.field_1849.method_1434();
   }

   private boolean hasSprintingTarget() {
      return false;
   }
}

