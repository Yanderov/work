package dev.client.yanderov.features.impl.combat;

import dev.client.yanderov.events.item.UsingItemEvent;
import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.events.player.AttackEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.events.render.WorldRenderEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.geometry.Render3D;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import dev.client.yanderov.utils.math.time.StopWatch;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1819;
import net.minecraft.class_1839;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2678;
import net.minecraft.class_2724;
import net.minecraft.class_2799;
import net.minecraft.class_310;
import net.minecraft.class_2799.class_2800;

public class PulseBlink extends Module {
   private SelectSetting fakeLagMode = (new SelectSetting("FakeLag Mode", "Ð ÐµÐ¶Ð¸Ð¼ Ñ€Ð°Ð±Ð¾Ñ‚Ñ‹ Ñ„ÐµÐ¹ÐºÐ»Ð°Ð³Ð¾Ð²")).value("Dynamic", "Always").selected("Always");
   private SliderSettings enemyStartDistance = (new SliderSettings("Enemy Start Dist", "Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð²ÐºÐ»ÑŽÑ‡ÐµÐ½Ð¸Ñ Ñ„ÐµÐ¹ÐºÐ»Ð°Ð³Ð°")).range(0.0F, 100.0F).setValue(40.0F).visible(() -> this.fakeLagMode.isSelected("Dynamic"));
   private SliderSettings enemyStopDistance = (new SliderSettings("Enemy Stop Dist", "Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð°Ð²Ñ‚Ð¾-Ð±Ð»Ð¸Ð½ÐºÐ° (0 = Ð¸Ð³Ð½Ð¾Ñ€)")).range(0.0F, 8.0F).setValue(0.0F).visible(() -> this.fakeLagMode.isSelected("Dynamic"));
   private SliderSettings minFakeLagDelay = (new SliderSettings("Min FakeLag Delay", "ÐœÐ¸Ð½. Ð´Ð»Ð¸Ñ‚ÐµÐ»ÑŒÐ½Ð¾ÑÑ‚ÑŒ Ñ„Ñ€Ð¸Ð·Ð° (Ð¼Ñ)")).range(0.0F, 3000.0F).setValue(400.0F);
   private SliderSettings maxFakeLagDelay = (new SliderSettings("Max FakeLag Delay", "ÐœÐ°ÐºÑ. Ð´Ð»Ð¸Ñ‚ÐµÐ»ÑŒÐ½Ð¾ÑÑ‚ÑŒ Ñ„Ñ€Ð¸Ð·Ð° (Ð¼Ñ)")).range(0.0F, 3000.0F).setValue(1600.0F);
   private SliderSettings minRecoilNextDelay = (new SliderSettings("Min Recoil Delay", "ÐœÐ¸Ð½. Ð¿Ð°ÑƒÐ·Ð° Ð¼ÐµÐ¶Ð´Ñƒ Ñ„Ñ€Ð¸Ð·Ð°Ð¼Ð¸ (Ð¼Ñ)")).range(0.0F, 1000.0F).setValue(200.0F);
   private SliderSettings maxRecoilNextDelay = (new SliderSettings("Max Recoil Delay", "ÐœÐ°ÐºÑ. Ð¿Ð°ÑƒÐ·Ð° Ð¼ÐµÐ¶Ð´Ñƒ Ñ„Ñ€Ð¸Ð·Ð°Ð¼Ð¸ (Ð¼Ñ)")).range(0.0F, 1000.0F).setValue(600.0F);
   private BooleanSetting randomizationNoise = (new BooleanSetting("Randomization Noise", "Ð¡Ð¼ÐµÑ‰Ð°Ñ‚ÑŒ Ð¼Ð¾Ð´ÐµÐ»ÑŒÐºÑƒ Ð¿Ñ€Ð¸ Ñ„Ñ€Ð¸Ð·Ðµ")).setValue(false);
   private BooleanSetting noiseForward;
   private BooleanSetting noiseRight;
   private BooleanSetting noiseLeft;
   private BooleanSetting noiseBack;
   private BooleanSetting collisionDisable;
   private SliderSettings noiseWorkRadius;
   private BooleanSetting randomizationRotate;
   private SelectSetting headRotateMode;
   private SliderSettings headRotateSpeed;
   private SelectSetting bodyRotateMode;
   private SliderSettings bodyRotateSpeed;
   private BooleanSetting sneakMode;
   private SelectSetting blinkAction;
   private BooleanSetting evTakeDamage;
   private BooleanSetting evJump;
   private BooleanSetting evSneak;
   private BooleanSetting evStopMotion;
   private BooleanSetting evElytra;
   private BooleanSetting evEating;
   private BooleanSetting evShield;
   private BooleanSetting evLava;
   private BooleanSetting evWater;
   private BooleanSetting evBlockClip;
   private BooleanSetting evPlayerHit;
   private BooleanSetting evPotion;
   private BooleanSetting evInteract;
   private SliderSettings dmgDelay;
   private SliderSettings jumpDelay;
   private SliderSettings sneakDelay;
   private SliderSettings stopDelay;
   private SliderSettings elytraDelay;
   private SliderSettings eatDelay;
   private SliderSettings shieldDelay;
   private SliderSettings lavaDelay;
   private SliderSettings waterDelay;
   private SliderSettings clipDelay;
   private SliderSettings hitDelay;
   private SliderSettings potionDelay;
   private SliderSettings interactDelay;
   private BooleanSetting renderEsp;
   private final List buffer;
   private class_238 freezeBox;
   private class_243 freezePos;
   private StopWatch phaseTimer;
   private StopWatch recoilTimer;
   private StopWatch blinkDelayTimer;
   private long currentPhaseDuration;
   private long currentRecoilDuration;
   private long pendingBlinkDelay;
   private boolean inFakeLagPhase;
   private boolean inRecoilPhase;
   private boolean blinkScheduled;
   private float lastHealth;
   private boolean wasOnGround;
   private boolean wasSneaking;
   private boolean wasUsingItem;
   private boolean wasInLava;
   private boolean wasInWater;
   private boolean wasInBlocks;
   private boolean wasElytra;
   private boolean lastAttackPressed;
   private boolean lastUsePressed;
   private boolean wasMovingCached;

   public PulseBlink() {
      super("PulseBlink", "PulseBlink", ModuleCategory.COMBAT);
      BooleanSetting var10001 = (new BooleanSetting("Noise Forward", "Ð¡Ð¼ÐµÑ‰ÐµÐ½Ð¸Ðµ Ð²Ð¿ÐµÑ€Ñ‘Ð´")).setValue(true);
      BooleanSetting var10002 = this.randomizationNoise;
      Objects.requireNonNull(var10002);
      this.noiseForward = var10001.visible(var10002::isValue);
      var10001 = (new BooleanSetting("Noise Right", "Ð¡Ð¼ÐµÑ‰ÐµÐ½Ð¸Ðµ Ð²Ð¿Ñ€Ð°Ð²Ð¾")).setValue(false);
      var10002 = this.randomizationNoise;
      Objects.requireNonNull(var10002);
      this.noiseRight = var10001.visible(var10002::isValue);
      var10001 = (new BooleanSetting("Noise Left", "Ð¡Ð¼ÐµÑ‰ÐµÐ½Ð¸Ðµ Ð²Ð»ÐµÐ²Ð¾")).setValue(false);
      var10002 = this.randomizationNoise;
      Objects.requireNonNull(var10002);
      this.noiseLeft = var10001.visible(var10002::isValue);
      var10001 = (new BooleanSetting("Noise Back", "Ð¡Ð¼ÐµÑ‰ÐµÐ½Ð¸Ðµ Ð½Ð°Ð·Ð°Ð´")).setValue(false);
      var10002 = this.randomizationNoise;
      Objects.requireNonNull(var10002);
      this.noiseBack = var10001.visible(var10002::isValue);
      var10001 = (new BooleanSetting("Noise: Collision Off", "Ð Ð°Ð·Ñ€ÐµÑˆÐ¸Ñ‚ÑŒ ÑÐ¼ÐµÑ‰ÐµÐ½Ð¸Ðµ Ð² Ð±Ð»Ð¾ÐºÐ¸/Ð¶Ð¸Ð´ÐºÐ¾ÑÑ‚Ð¸")).setValue(false);
      var10002 = this.randomizationNoise;
      Objects.requireNonNull(var10002);
      this.collisionDisable = var10001.visible(var10002::isValue);
      SliderSettings var5 = (new SliderSettings("Noise Radius", "Ð Ð°Ð´Ð¸ÑƒÑ ÑÐ¼ÐµÑ‰ÐµÐ½Ð¸Ñ (Ð±Ð»Ð¾ÐºÐ¸)")).range(0.0F, 10.0F).setValue(1.5F);
      var10002 = this.randomizationNoise;
      Objects.requireNonNull(var10002);
      this.noiseWorkRadius = var5.visible(var10002::isValue);
      this.randomizationRotate = (new BooleanSetting("Randomization Rotate", "Ð Ð°Ð½Ð´Ð¾Ð¼Ð¸Ð·Ð°Ñ†Ð¸Ñ Ð³Ð¾Ð»Ð¾Ð²Ñ‹/Ñ‚ÐµÐ»Ð° Ð¿Ñ€Ð¸ Ñ„Ñ€Ð¸Ð·Ðµ")).setValue(false);
      SelectSetting var6 = (new SelectSetting("Head Rotate Mode", "Ð ÐµÐ¶Ð¸Ð¼ Ñ€Ð¾Ñ‚Ð°Ñ†Ð¸Ð¸ Ð³Ð¾Ð»Ð¾Ð²Ñ‹")).value("TopAndDown", "Random", "Off").selected("Off");
      var10002 = this.randomizationRotate;
      Objects.requireNonNull(var10002);
      this.headRotateMode = var6.visible(var10002::isValue);
      this.headRotateSpeed = (new SliderSettings("Head Rot Speed", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ñ€Ð¾Ñ‚Ð°Ñ†Ð¸Ð¸ Ð³Ð¾Ð»Ð¾Ð²Ñ‹")).range(0.0F, 50.0F).setValue(15.0F).visible(() -> this.randomizationRotate.isValue() && !this.headRotateMode.isSelected("Off"));
      var6 = (new SelectSetting("Body Rotate Mode", "Ð ÐµÐ¶Ð¸Ð¼ Ñ€Ð¾Ñ‚Ð°Ñ†Ð¸Ð¸ Ñ‚ÐµÐ»Ð°")).value("Spin", "Off").selected("Off");
      var10002 = this.randomizationRotate;
      Objects.requireNonNull(var10002);
      this.bodyRotateMode = var6.visible(var10002::isValue);
      this.bodyRotateSpeed = (new SliderSettings("Body Rot Speed", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ñ€Ð¾Ñ‚Ð°Ñ†Ð¸Ð¸ Ñ‚ÐµÐ»Ð°")).range(0.0F, 50.0F).setValue(20.0F).visible(() -> this.randomizationRotate.isValue() && this.bodyRotateMode.isSelected("Spin"));
      this.sneakMode = (new BooleanSetting("Sneak Mode", "Ð’Ð¾ Ð²Ñ€ÐµÐ¼Ñ Ñ„Ñ€Ð¸Ð·Ð° Ð¿ÐµÑ€ÑÐ¾Ð½Ð°Ð¶ Ð½Ð° ÑˆÐ¸Ñ„Ñ‚Ðµ")).setValue(false);
      this.blinkAction = (new SelectSetting("Blink Action", "ÐšÐ°Ðº Ð±Ð»Ð¸Ð½ÐºÐ°Ñ‚ÑŒÑÑ Ð¿Ð¾ Ð´ÐµÐ¹ÑÑ‚Ð²Ð¸ÑÐ¼")).value("AllActionBlink", "CustomActionBlink").selected("AllActionBlink");
      this.evTakeDamage = (new BooleanSetting("TakeDamage", "Ð‘Ð»Ð¸Ð½Ðº Ð¿Ñ€Ð¸ Ð¿Ð¾Ð»ÑƒÑ‡ÐµÐ½Ð¸Ð¸ ÑƒÑ€Ð¾Ð½Ð°")).setValue(false).visible(() -> this.blinkAction.isSelected("CustomActionBlink"));
      this.evJump = (new BooleanSetting("Jump", "Ð‘Ð»Ð¸Ð½Ðº Ð¿Ñ€Ð¸ Ð¿Ñ€Ñ‹Ð¶ÐºÐµ")).setValue(false).visible(() -> this.blinkAction.isSelected("CustomActionBlink"));
      this.evSneak = (new BooleanSetting("Sneak", "Ð‘Ð»Ð¸Ð½Ðº Ð¿Ñ€Ð¸ Ð¿Ñ€Ð¸ÑÐµÐ´Ðµ")).setValue(false).visible(() -> this.blinkAction.isSelected("CustomActionBlink"));
      this.evStopMotion = (new BooleanSetting("StopMotion", "Ð‘Ð»Ð¸Ð½Ðº Ð¿Ñ€Ð¸ Ð¾ÑÑ‚Ð°Ð½Ð¾Ð²ÐºÐµ Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ñ")).setValue(false).visible(() -> this.blinkAction.isSelected("CustomActionBlink"));
      this.evElytra = (new BooleanSetting("ElytraGlide", "Ð‘Ð»Ð¸Ð½Ðº Ð¿Ñ€Ð¸ Ð¿Ð¾Ð»Ñ‘Ñ‚Ðµ Ð½Ð° ÑÐ»Ð¸Ñ‚Ñ€Ð°Ñ…")).setValue(false).visible(() -> this.blinkAction.isSelected("CustomActionBlink"));
      this.evEating = (new BooleanSetting("Eating", "Ð‘Ð»Ð¸Ð½Ðº Ð¿Ñ€Ð¸ ÐµÐ´Ðµ")).setValue(false).visible(() -> this.blinkAction.isSelected("CustomActionBlink"));
      this.evShield = (new BooleanSetting("Shield", "Ð‘Ð»Ð¸Ð½Ðº Ð¿Ñ€Ð¸ Ð±Ð»Ð¾ÐºÐµ Ñ‰Ð¸Ñ‚Ð¾Ð¼")).setValue(false).visible(() -> this.blinkAction.isSelected("CustomActionBlink"));
      this.evLava = (new BooleanSetting("Lava", "Ð‘Ð»Ð¸Ð½Ðº Ð² Ð»Ð°Ð²Ðµ")).setValue(false).visible(() -> this.blinkAction.isSelected("CustomActionBlink"));
      this.evWater = (new BooleanSetting("Water", "Ð‘Ð»Ð¸Ð½Ðº Ð² Ð²Ð¾Ð´Ðµ")).setValue(false).visible(() -> this.blinkAction.isSelected("CustomActionBlink"));
      this.evBlockClip = (new BooleanSetting("BlockClip", "Ð‘Ð»Ð¸Ð½Ðº Ð²Ð½ÑƒÑ‚Ñ€Ð¸ Ð±Ð»Ð¾ÐºÐ¾Ð²")).setValue(false).visible(() -> this.blinkAction.isSelected("CustomActionBlink"));
      this.evPlayerHit = (new BooleanSetting("PlayerHit", "Ð‘Ð»Ð¸Ð½Ðº Ð¿Ñ€Ð¸ ÑƒÐ´Ð°Ñ€Ðµ Ð¸Ð³Ñ€Ð¾ÐºÐ°")).setValue(false).visible(() -> this.blinkAction.isSelected("CustomActionBlink"));
      this.evPotion = (new BooleanSetting("Potion", "Ð‘Ð»Ð¸Ð½Ðº Ð¿Ñ€Ð¸ Ð¿Ð¸Ñ‚ÑŒÐµ Ð·ÐµÐ»ÑŒÑ")).setValue(false).visible(() -> this.blinkAction.isSelected("CustomActionBlink"));
      this.evInteract = (new BooleanSetting("Interact", "Ð‘Ð»Ð¸Ð½Ðº Ð¿Ñ€Ð¸ Ð²Ð·Ð°Ð¸Ð¼Ð¾Ð´ÐµÐ¹ÑÑ‚Ð²Ð¸Ð¸")).setValue(false).visible(() -> this.blinkAction.isSelected("CustomActionBlink"));
      this.dmgDelay = (new SliderSettings("Delay: TakeDamage", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð±Ð»Ð¸Ð½ÐºÐ° (Ð¼Ñ)")).range(0.0F, 1000.0F).setValue(0.0F).visible(() -> this.blinkAction.isSelected("CustomActionBlink") && this.evTakeDamage.isValue());
      this.jumpDelay = (new SliderSettings("Delay: Jump", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð±Ð»Ð¸Ð½ÐºÐ° (Ð¼Ñ)")).range(0.0F, 1000.0F).setValue(0.0F).visible(() -> this.blinkAction.isSelected("CustomActionBlink") && this.evJump.isValue());
      this.sneakDelay = (new SliderSettings("Delay: Sneak", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð±Ð»Ð¸Ð½ÐºÐ° (Ð¼Ñ)")).range(0.0F, 1000.0F).setValue(0.0F).visible(() -> this.blinkAction.isSelected("CustomActionBlink") && this.evSneak.isValue());
      this.stopDelay = (new SliderSettings("Delay: StopMotion", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð±Ð»Ð¸Ð½ÐºÐ° (Ð¼Ñ)")).range(0.0F, 1000.0F).setValue(0.0F).visible(() -> this.blinkAction.isSelected("CustomActionBlink") && this.evStopMotion.isValue());
      this.elytraDelay = (new SliderSettings("Delay: Elytra", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð±Ð»Ð¸Ð½ÐºÐ° (Ð¼Ñ)")).range(0.0F, 1000.0F).setValue(0.0F).visible(() -> this.blinkAction.isSelected("CustomActionBlink") && this.evElytra.isValue());
      this.eatDelay = (new SliderSettings("Delay: Eating", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð±Ð»Ð¸Ð½ÐºÐ° (Ð¼Ñ)")).range(0.0F, 1000.0F).setValue(0.0F).visible(() -> this.blinkAction.isSelected("CustomActionBlink") && this.evEating.isValue());
      this.shieldDelay = (new SliderSettings("Delay: Shield", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð±Ð»Ð¸Ð½ÐºÐ° (Ð¼Ñ)")).range(0.0F, 1000.0F).setValue(0.0F).visible(() -> this.blinkAction.isSelected("CustomActionBlink") && this.evShield.isValue());
      this.lavaDelay = (new SliderSettings("Delay: Lava", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð±Ð»Ð¸Ð½ÐºÐ° (Ð¼Ñ)")).range(0.0F, 1000.0F).setValue(0.0F).visible(() -> this.blinkAction.isSelected("CustomActionBlink") && this.evLava.isValue());
      this.waterDelay = (new SliderSettings("Delay: Water", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð±Ð»Ð¸Ð½ÐºÐ° (Ð¼Ñ)")).range(0.0F, 1000.0F).setValue(0.0F).visible(() -> this.blinkAction.isSelected("CustomActionBlink") && this.evWater.isValue());
      this.clipDelay = (new SliderSettings("Delay: BlockClip", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð±Ð»Ð¸Ð½ÐºÐ° (Ð¼Ñ)")).range(0.0F, 1000.0F).setValue(0.0F).visible(() -> this.blinkAction.isSelected("CustomActionBlink") && this.evBlockClip.isValue());
      this.hitDelay = (new SliderSettings("Delay: PlayerHit", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð±Ð»Ð¸Ð½ÐºÐ° (Ð¼Ñ)")).range(0.0F, 1000.0F).setValue(0.0F).visible(() -> this.blinkAction.isSelected("CustomActionBlink") && this.evPlayerHit.isValue());
      this.potionDelay = (new SliderSettings("Delay: Potion", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð±Ð»Ð¸Ð½ÐºÐ° (Ð¼Ñ)")).range(0.0F, 1000.0F).setValue(0.0F).visible(() -> this.blinkAction.isSelected("CustomActionBlink") && this.evPotion.isValue());
      this.interactDelay = (new SliderSettings("Delay: Interact", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð±Ð»Ð¸Ð½ÐºÐ° (Ð¼Ñ)")).range(0.0F, 1000.0F).setValue(0.0F).visible(() -> this.blinkAction.isSelected("CustomActionBlink") && this.evInteract.isValue());
      this.renderEsp = (new BooleanSetting("Render ESP", "ÐŸÐ¾ÐºÐ°Ð·Ñ‹Ð²Ð°Ñ‚ÑŒ Ð¿Ð¾Ð·Ð¸Ñ†Ð¸ÑŽ Ñ„Ñ€Ð¸Ð·Ð°")).setValue(true);
      this.buffer = new CopyOnWriteArrayList();
      this.freezeBox = null;
      this.freezePos = null;
      this.phaseTimer = new StopWatch();
      this.recoilTimer = new StopWatch();
      this.blinkDelayTimer = new StopWatch();
      this.currentPhaseDuration = 0L;
      this.currentRecoilDuration = 0L;
      this.pendingBlinkDelay = -1L;
      this.inFakeLagPhase = false;
      this.inRecoilPhase = false;
      this.blinkScheduled = false;
      this.lastHealth = -1.0F;
      this.wasOnGround = false;
      this.wasSneaking = false;
      this.wasUsingItem = false;
      this.wasInLava = false;
      this.wasInWater = false;
      this.wasInBlocks = false;
      this.wasElytra = false;
      this.lastAttackPressed = false;
      this.lastUsePressed = false;
      this.wasMovingCached = false;
      this.setup(new Setting[]{this.fakeLagMode, this.enemyStartDistance, this.enemyStopDistance, this.minFakeLagDelay, this.maxFakeLagDelay, this.minRecoilNextDelay, this.maxRecoilNextDelay, this.randomizationNoise, this.noiseForward, this.noiseRight, this.noiseLeft, this.noiseBack, this.collisionDisable, this.noiseWorkRadius, this.randomizationRotate, this.headRotateMode, this.headRotateSpeed, this.bodyRotateMode, this.bodyRotateSpeed, this.sneakMode, this.blinkAction, this.evTakeDamage, this.dmgDelay, this.evJump, this.jumpDelay, this.evSneak, this.sneakDelay, this.evStopMotion, this.stopDelay, this.evElytra, this.elytraDelay, this.evEating, this.eatDelay, this.evShield, this.shieldDelay, this.evLava, this.lavaDelay, this.evWater, this.waterDelay, this.evBlockClip, this.clipDelay, this.evPlayerHit, this.hitDelay, this.evPotion, this.potionDelay, this.evInteract, this.interactDelay, this.renderEsp});
   }

   public void activate() {
      this.buffer.clear();
      if (mc.field_1724 != null) {
         this.freezeBox = mc.field_1724.method_5829();
         this.freezePos = mc.field_1724.method_19538();
         this.lastHealth = mc.field_1724.method_6032();
         this.wasOnGround = mc.field_1724.method_24828();
         this.wasSneaking = mc.field_1724.method_5715();
         this.wasUsingItem = mc.field_1724.method_6115();
         this.wasInLava = mc.field_1724.method_5771();
         this.wasInWater = mc.field_1724.method_5799();
         this.wasInBlocks = mc.field_1724.field_5960;
         this.wasElytra = this.playerIsFallFlying();
      }

      this.inFakeLagPhase = false;
      this.inRecoilPhase = false;
      this.blinkScheduled = false;
      this.pendingBlinkDelay = -1L;
      this.phaseTimer.reset();
      this.recoilTimer.reset();
      this.blinkDelayTimer.reset();
   }

   public void deactivate() {
      this.flushPackets();
      this.buffer.clear();
      this.inFakeLagPhase = false;
      this.inRecoilPhase = false;
      this.blinkScheduled = false;
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (!PlayerInteractionHelper.nullCheck()) {
         class_310 mc = class_310.method_1551();
         if (mc.field_1724 != null) {
            if (this.sneakMode.isValue() && this.inFakeLagPhase) {
               mc.field_1724.method_5660(true);
            }

            this.handleBlinkActionEvents(mc);
            if (this.blinkScheduled && this.blinkDelayTimer.finished((double)this.pendingBlinkDelay)) {
               this.forceBlink();
            }

            boolean shouldFakeLagNow = this.shouldFakeLag();
            if (shouldFakeLagNow) {
               if (!this.inFakeLagPhase && !this.inRecoilPhase && !this.blinkScheduled) {
                  this.currentPhaseDuration = this.clampDelay(this.randomInRange(this.minFakeLagDelay.getValue(), this.maxFakeLagDelay.getValue()), 20L);
                  this.currentRecoilDuration = this.clampDelay(this.randomInRange(this.minRecoilNextDelay.getValue(), this.maxRecoilNextDelay.getValue()), 20L);
                  this.phaseTimer.reset();
                  this.recoilTimer.reset();
                  this.inFakeLagPhase = true;
                  this.freezeBox = mc.field_1724.method_5829();
                  this.freezePos = mc.field_1724.method_19538();
               }

               if (this.inFakeLagPhase) {
                  if (this.phaseTimer.finished((double)this.currentPhaseDuration)) {
                     this.forceBlink();
                     if (this.currentRecoilDuration > 0L) {
                        this.inRecoilPhase = true;
                        this.recoilTimer.reset();
                     }
                  }
               } else if (this.inRecoilPhase && this.recoilTimer.finished((double)this.currentRecoilDuration)) {
                  this.inRecoilPhase = false;
               }

            } else {
               if (this.inFakeLagPhase || this.inRecoilPhase) {
                  this.forceBlink();
               }

            }
         }
      }
   }

   private long clampDelay(long val, long minClamp) {
      return Math.max(val, minClamp);
   }

   private boolean shouldFakeLag() {
      if (this.fakeLagMode.isSelected("Always")) {
         return true;
      } else {
         class_1657 target = this.findNearestEnemy();
         if (target == null) {
            return false;
         } else {
            double distSq = mc.field_1724.method_5858(target);
            double start = (double)this.enemyStartDistance.getValue();
            double stop = (double)this.enemyStopDistance.getValue();
            if (stop > (double)0.0F && distSq <= stop * stop) {
               if (this.inFakeLagPhase || !this.buffer.isEmpty()) {
                  this.forceBlink();
               }

               return false;
            } else {
               return distSq <= start * start;
            }
         }
      }
   }

   private class_1657 findNearestEnemy() {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         class_1657 nearest = null;
         double best = Double.MAX_VALUE;

         for(class_1657 p : mc.field_1687.method_18456()) {
            if (p != mc.field_1724 && !p.method_29504()) {
               double d = mc.field_1724.method_5858(p);
               if (d < best) {
                  best = d;
                  nearest = p;
               }
            }
         }

         return nearest;
      } else {
         return null;
      }
   }

   private long randomInRange(float min, float max) {
      if (max < min) {
         float t = min;
         min = max;
         max = t;
      }

      return max == min ? (long)min : (long)((double)min + Math.random() * (double)(max - min));
   }

   private void flushPackets() {
      this.buffer.forEach(PlayerInteractionHelper::sendPacketWithOutEvent);
      this.buffer.clear();
   }

   private void forceBlink() {
      this.flushPackets();
      this.inFakeLagPhase = false;
      this.inRecoilPhase = false;
      this.blinkScheduled = false;
      this.pendingBlinkDelay = -1L;
   }

   private void scheduleBlink(long delayMs) {
      if (delayMs <= 0L) {
         this.forceBlink();
      } else {
         this.blinkScheduled = true;
         this.pendingBlinkDelay = delayMs;
         this.blinkDelayTimer.reset();
      }
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (!PlayerInteractionHelper.nullCheck()) {
         class_2596<?> pkt = e.getPacket();
         Objects.requireNonNull(pkt);
         class_2596 var3 = pkt;
         byte var4 = 0;

         label31:
         while(true) {
            //$FF: var4->value
            //0->net/minecraft/class_2724
            //1->net/minecraft/class_2678
            //2->net/minecraft/class_2799
            // TODO: Fix switch statement for var3
        if (var3 != null) {
            // // case 0:
            // class_2724 ignored = (class_2724)var3;
            // this.setState(false);
            // break label31;
            // // case 1:
            // class_2678 ignored = (class_2678)var3;
            // this.setState(false);
            // break label31;
            // // case 2:
            // class_2799 status = (class_2799)var3;
            // if (status.method_12119() != class_2800.field_12774) {
            // var4 = 3;
            // break;
            // } else {
            // this.setState(false);
            // }
            // // default:
            // break label31;
            }
         }

         if (e.isSend()) {
            if (this.inFakeLagPhase) {
               this.buffer.add(pkt);
               e.cancel();
            }
         }
      }
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      if (this.renderEsp.isValue()) {
         if (this.inFakeLagPhase && this.freezeBox != null) {
            class_238 boxToDraw = this.freezeBox;
            if (this.randomizationNoise.isValue() && this.freezePos != null) {
               class_243 off = this.getNoiseOffset();
               class_238 shifted = boxToDraw.method_997(off);
               if (this.collisionDisable.isValue() || mc.field_1687 == null || mc.field_1687.method_18026(shifted)) {
                  boxToDraw = shifted;
               }
            }

            Render3D.drawBox(boxToDraw, ColorAssist.getClientColor(), 1.0F);
         }
      }
   }

   private class_243 getNoiseOffset() {
      if (this.noiseWorkRadius.getValue() <= 0.0F) {
         return class_243.field_1353;
      } else {
         List<class_243> dirs = new ArrayList();
         if (this.noiseForward.isValue()) {
            dirs.add(this.forward());
         }

         if (this.noiseBack.isValue()) {
            dirs.add(this.forward().method_22882());
         }

         if (this.noiseRight.isValue()) {
            dirs.add(this.right());
         }

         if (this.noiseLeft.isValue()) {
            dirs.add(this.right().method_22882());
         }

         if (dirs.isEmpty()) {
            dirs.add(this.forward());
         }

         class_243 dir = (class_243)dirs.get((int)(Math.random() * (double)dirs.size()));
         double radius = (double)this.noiseWorkRadius.getValue() * Math.random();
         return dir.method_1029().method_1021(radius);
      }
   }

   private class_243 forward() {
      float yaw = mc.field_1724.method_36454() * ((float)Math.PI / 180F);
      return new class_243(-Math.sin((double)yaw), (double)0.0F, Math.cos((double)yaw));
   }

   private class_243 right() {
      float yaw = (mc.field_1724.method_36454() + 90.0F) * ((float)Math.PI / 180F);
      return new class_243(-Math.sin((double)yaw), (double)0.0F, Math.cos((double)yaw));
   }

   private void handleBlinkActionEvents(class_310 mc) {
      if (mc.field_1724 != null) {
         if (this.blinkAction.isSelected("AllActionBlink")) {
            if (this.isNonMovementAction(mc) && this.inFakeLagPhase && !this.blinkScheduled) {
               this.scheduleBlink(0L);
            }
         } else if (this.blinkAction.isSelected("CustomActionBlink")) {
            if (this.evTakeDamage.isValue()) {
               float hp = mc.field_1724.method_6032();
               if (this.lastHealth >= 0.0F && hp < this.lastHealth && this.inFakeLagPhase) {
                  this.scheduleBlink((long)this.dmgDelay.getValue());
               }

               this.lastHealth = hp;
            } else {
               this.lastHealth = mc.field_1724.method_6032();
            }

            boolean onGround = mc.field_1724.method_24828();
            if (this.evJump.isValue() && !onGround && this.wasOnGround && this.inFakeLagPhase) {
               this.scheduleBlink((long)this.jumpDelay.getValue());
            }

            if (this.evStopMotion.isValue()) {
               boolean movingNow = this.isMoving(mc);
               if (!movingNow && this.wasMovingCached && this.inFakeLagPhase) {
                  this.scheduleBlink((long)this.stopDelay.getValue());
               }

               this.wasMovingCached = movingNow;
            } else {
               this.wasMovingCached = this.isMoving(mc);
            }

            boolean sneakingNow = mc.field_1724.method_5715();
            if (this.evSneak.isValue() && sneakingNow && !this.wasSneaking && this.inFakeLagPhase) {
               this.scheduleBlink((long)this.sneakDelay.getValue());
            }

            this.wasSneaking = sneakingNow;
            boolean elyNow = this.playerIsFallFlying();
            if (this.evElytra.isValue() && elyNow && !this.wasElytra && this.inFakeLagPhase) {
               this.scheduleBlink((long)this.elytraDelay.getValue());
            }

            this.wasElytra = elyNow;
            boolean inLava = mc.field_1724.method_5771();
            boolean inWater = mc.field_1724.method_5799();
            boolean inBlocks = mc.field_1724.field_5960;
            if (this.evLava.isValue() && inLava && !this.wasInLava && this.inFakeLagPhase) {
               this.scheduleBlink((long)this.lavaDelay.getValue());
            }

            if (this.evWater.isValue() && inWater && !this.wasInWater && this.inFakeLagPhase) {
               this.scheduleBlink((long)this.waterDelay.getValue());
            }

            if (this.evBlockClip.isValue() && inBlocks && !this.wasInBlocks && this.inFakeLagPhase) {
               this.scheduleBlink((long)this.clipDelay.getValue());
            }

            this.wasInLava = inLava;
            this.wasInWater = inWater;
            this.wasInBlocks = inBlocks;
         }

         this.wasOnGround = mc.field_1724.method_24828();
      }
   }

   @EventHandler
   public void onAttack(AttackEvent e) {
      if (this.isState()) {
         if (this.inFakeLagPhase) {
            if (this.blinkAction.isSelected("CustomActionBlink")) {
               if (this.evPlayerHit.isValue()) {
                  this.scheduleBlink((long)this.hitDelay.getValue());
               }
            }
         }
      }
   }

   @EventHandler
   public void onUsingItem(UsingItemEvent e) {
      if (this.isState()) {
         if (this.inFakeLagPhase) {
            if (this.blinkAction.isSelected("CustomActionBlink")) {
               if (mc.field_1724 != null) {
                  if (e.getType() == -1) {
                     if (this.evInteract.isValue()) {
                        this.scheduleBlink((long)this.interactDelay.getValue());
                     }

                  } else {
                     if (e.getType() == 1 || e.getType() == 0) {
                        class_1799 stack = mc.field_1724.method_6030();
                        if (stack == null) {
                           return;
                        }

                        class_1792 item = stack.method_7909();
                        class_1839 action = stack.method_7976();
                        if (this.evEating.isValue() && action == class_1839.field_8950) {
                           this.scheduleBlink((long)this.eatDelay.getValue());
                        }

                        if (this.evPotion.isValue() && action == class_1839.field_8946) {
                           this.scheduleBlink((long)this.potionDelay.getValue());
                        }

                        if (this.evShield.isValue() && (item instanceof class_1819 || action == class_1839.field_8949)) {
                           this.scheduleBlink((long)this.shieldDelay.getValue());
                        }
                     }

                  }
               }
            }
         }
      }
   }

   private boolean isMoving(class_310 mc) {
      if (mc.field_1724 == null) {
         return false;
      } else {
         class_243 v = mc.field_1724.method_18798();
         return v.field_1352 * v.field_1352 + v.field_1350 * v.field_1350 > 1.0E-4;
      }
   }

   private boolean isNonMovementAction(class_310 mc) {
      if (mc.field_1724 != null && mc.field_1690 != null) {
         if (mc.field_1690.field_1886.method_1434()) {
            return true;
         } else if (mc.field_1690.field_1904.method_1434()) {
            return true;
         } else if (mc.field_1690.field_1871.method_1434()) {
            return true;
         } else if (mc.field_1724.method_6115()) {
            return true;
         } else if (mc.field_1724.method_5715()) {
            return true;
         } else {
            return mc.field_1724.method_5771() || mc.field_1724.method_5799();
         }
      } else {
         return false;
      }
   }

   private boolean playerIsFallFlying() {
      if (mc.field_1724 == null) {
         return false;
      } else {
         try {
            return (Boolean)mc.field_1724.getClass().getMethod("isFallFlying").invoke(mc.field_1724);
         } catch (Throwable var2) {
            return false;
         }
      }
   }
}

