package dev.client.yanderov.features.impl.combat;

import antidaunleak.api.annotation.Native;
import dev.client.yanderov.Yanderov;
import dev.client.yanderov.display.hud.Notifications;
import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.events.player.MotionEvent;
import dev.client.yanderov.events.player.RotationUpdateEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.events.render.DrawEvent;
import dev.client.yanderov.events.render.WorldRenderEvent;
import dev.client.yanderov.features.impl.movement.ElytraTarget;
import dev.client.yanderov.features.impl.movement.FakeLag;
import dev.client.yanderov.features.impl.movement.TargetStrafe;
import dev.client.yanderov.features.impl.render.Hud;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.MultiSelectSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.Instance;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.geometry.Render3D;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.display.shape.ShapeProperties;
import dev.client.yanderov.utils.features.aura.miror.MirorAiManager;
import dev.client.yanderov.utils.features.aura.point.MultiPoint;
import dev.client.yanderov.utils.features.aura.rotations.constructor.LinearConstructor;
import dev.client.yanderov.utils.features.aura.rotations.constructor.RotateConstructor;
import dev.client.yanderov.utils.features.aura.rotations.impl.AdvancedAngle;
import dev.client.yanderov.utils.features.aura.rotations.impl.FTAngle;
import dev.client.yanderov.utils.features.aura.rotations.impl.FakeAngle;
import dev.client.yanderov.utils.features.aura.rotations.impl.FunSkyHvHAngle;
import dev.client.yanderov.utils.features.aura.rotations.impl.HAngle;
import dev.client.yanderov.utils.features.aura.rotations.impl.HWAngle;
import dev.client.yanderov.utils.features.aura.rotations.impl.LGAngle;
import dev.client.yanderov.utils.features.aura.rotations.impl.MatrixAngle;
import dev.client.yanderov.utils.features.aura.rotations.impl.RWAngle;
import dev.client.yanderov.utils.features.aura.rotations.impl.SPAngle;
import dev.client.yanderov.utils.features.aura.rotations.impl.SnapAngle;
import dev.client.yanderov.utils.features.aura.striking.StrikeManager;
import dev.client.yanderov.utils.features.aura.striking.StrikerConstructor;
import dev.client.yanderov.utils.features.aura.target.TargetFinder;
import dev.client.yanderov.utils.features.aura.utils.MathAngle;
import dev.client.yanderov.utils.features.aura.warp.Turns;
import dev.client.yanderov.utils.features.aura.warp.TurnsConfig;
import dev.client.yanderov.utils.features.aura.warp.TurnsConnection;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import dev.client.yanderov.utils.math.calc.Calculate;
import dev.client.yanderov.utils.math.task.TaskPriority;
import dev.client.yanderov.utils.math.time.StopWatch;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2663;
import net.minecraft.class_2678;
import net.minecraft.class_2724;
import net.minecraft.class_2799;
import net.minecraft.class_3532;
import net.minecraft.class_3545;
import net.minecraft.class_3966;
import net.minecraft.class_4587;
import net.minecraft.class_746;
import net.minecraft.class_2799.class_2800;

public class Aura extends Module {
   private static final float RANGE_MARGIN = 0.253F;
   private static final Map LEGIT_SPRINT_MAP = Map.of("FunTime", 1, "Matrix", 1, "Snap", 1, "SpookyTime", 2, "HolyWorld", 2);
   private TargetFinder targetSelector = new TargetFinder();
   private MultiPoint pointFinder = new MultiPoint();
   private class_1309 target;
   private class_1309 lastTarget;
   private long shiftTapEndTime = 0L;
   public static boolean fakeRotate;
   public static float legitSprintNeed;
   private FovCircleRenderer fovCircleRenderer;
   private SelectSetting aimMode = (new SelectSetting("ÐÐ°Ð²Ð¾Ð´ÐºÐ°", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ñ‚Ð¸Ð¿ Ð½Ð°Ð²Ð¾Ð´ÐºÐ¸")).value("FunTime", "Legit Snap", "ReallyWorld", "HolyWorld", "SpookyTime", "SlothAi", "FunSky HVH", "Advanced", "minarai (test)").selected("FunTime");
   private MultiSelectSetting targetType = (new MultiSelectSetting("Ð¢Ð¸Ð¿ Ñ‚Ð°Ñ€Ð³ÐµÑ‚Ð°", "Ð¤Ð¸Ð»ÑŒÑ‚Ñ€ÑƒÐµÑ‚ Ð²ÐµÑÑŒ ÑÐ¿Ð¸ÑÐ¾Ðº Ñ†ÐµÐ»ÐµÐ¹ Ð¿Ð¾ Ñ‚Ð¸Ð¿Ñƒ")).value("Players", "Mobs", "Animals", "Friends", "Armor Stand").selected("Players", "Mobs", "Animals").visible(() -> !this.aimMode.isSelected("Advanced"));
   private final SelectSetting mirorAiModel = (new SelectSetting("ÐœÐ¾Ð´ÐµÐ»ÑŒ", "ÐœÐ¾Ð´ÐµÐ»ÑŒ minarai (test)")).visible(() -> this.aimMode.isSelected("minarai (test)"));
   private SliderSettings attackRange = (new SliderSettings("Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ ÑƒÐ´Ð°Ñ€Ð°", "Ð”Ð°Ð»ÑŒÐ½Ð¾ÑÑ‚ÑŒ Ð°Ñ‚Ð°ÐºÐ¸ Ð´Ð¾ Ñ†ÐµÐ»Ð¸")).setValue(3.0F).range(1.0F, 6.0F);
   private SliderSettings lookRange = (new SliderSettings("Ð”Ð¾Ð¿Ð¾Ð»Ð½Ð¸Ñ‚ÐµÐ»ÑŒÐ½Ð°Ñ Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð¿Ð¾Ð¸ÑÐºÐ°", "Ð”Ð¸Ð°Ð¿Ð°Ð·Ð¾Ð½ Ð¿Ð¾Ð¸ÑÐºÐ° Ð´Ð¾ Ñ†ÐµÐ»Ð¸")).setValue(1.5F).range(0.0F, 2.0F);
   private MultiSelectSetting attackSetting = (new MultiSelectSetting("ÐÐ°ÑÑ‚Ñ€Ð¾Ð¹ÐºÐ¸", "ÐŸÐ¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ Ð½Ð°ÑÑ‚Ñ€Ð¾Ð¸Ñ‚ÑŒ Ñ€Ð°Ð±Ð¾Ñ‚Ñƒ Ñ„ÑƒÐ½ÐºÑ†Ð¸Ð¸")).value("Only Critical", "Break Shield", "UnPress Shield", "No Attack When Eat", "Ignore The Walls", "Fake Lag", "Hit Chance").selected("Only Critical", "Break Shield");
   private SliderSettings hitChance = (new SliderSettings("Ð¨Ð°Ð½Ñ ÑƒÐ´Ð°Ñ€Ð° Ð² %", "Ð¨Ð°Ð½Ñ ÑƒÐ´Ð°Ñ€Ð° Ð¿Ð¾ Ñ†ÐµÐ»Ð¸")).setValue(100.0F).range(1.0F, 100.0F).visible(() -> this.attackSetting.isSelected("Hit Chance"));
   private MultiSelectSetting hitInfoMode = (new MultiSelectSetting("Ð¡Ð¾Ð¾Ð±Ñ‰Ð°Ñ‚ÑŒ ÐµÑÐ»Ð¸", "Ð£Ð²ÐµÐ´Ð¾Ð¼Ð»ÐµÐ½Ð¸Ñ Ð¾ Ð¿Ñ€Ð¾Ð¼Ð°Ñ…Ð°Ñ…")).value("ÐÐµ Ð¿Ñ€Ð¾ÑˆÐµÐ» ÑƒÐ´Ð°Ñ€", "ÐÐµ Ð¿Ñ€Ð¾ÑˆÐµÐ» ÐºÑ€Ð¸Ñ‚").selected("ÐÐµ Ð¿Ñ€Ð¾ÑˆÐµÐ» ÑƒÐ´Ð°Ñ€", "ÐÐµ Ð¿Ñ€Ð¾ÑˆÐµÐ» ÐºÑ€Ð¸Ñ‚");
   private BooleanSetting shieldBreaker = (new BooleanSetting("Ð›Ð¾Ð¼Ð°Ñ‚ÑŒ Ñ‰Ð¸Ñ‚", "Ð£Ð¼Ð½Ñ‹Ð¹ Ð»Ð¾Ð¼ Ñ‰Ð¸Ñ‚Ð°")).setValue(true).visible(() -> this.attackSetting.isSelected("Break Shield"));
   private BooleanSetting shieldSprintBreaker = (new BooleanSetting("Ð›Ð¾Ð¼Ð°Ñ‚ÑŒ Ñ‰Ð¸Ñ‚ Ñ ÑÐ¿Ñ€Ð¸Ð½Ñ‚Ð¾Ð¼", "Ð”Ñ€Ð¾Ð¿ ÑÐ¿Ñ€Ð¸Ð½Ñ‚Ð° Ð¿Ñ€Ð¸ Ð»Ð¾Ð¼Ð°Ð½Ð¸Ð¸ Ñ‰Ð¸Ñ‚Ð°")).setValue(true).visible(() -> this.shieldBreaker.isValue());
   private SelectSetting clickMode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼ ÐºÐ»Ð¸ÐºÐ¾Ð²", "Ð¢Ð¸Ð¿ Ð°Ñ‚Ð°ÐºÐ¸")).value("1.9", "1.8").selected("1.9");
   private SliderSettings clickCps = (new SliderSettings("CPS", "ÐšÐ»Ð¸ÐºÐ¸ Ð² ÑÐµÐºÑƒÐ½Ð´Ñƒ Ð² Ñ€ÐµÐ¶Ð¸Ð¼Ðµ 1.8")).setValue(12.0F).range(1.0F, 20.0F).visible(() -> this.clickMode.isSelected("1.8"));
   private SelectSetting correctionType = (new SelectSetting("ÐšÐ¾Ñ€Ñ€ÐµÐºÑ†Ð¸Ð¸ Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ñ", "Ð’Ñ‹Ð±Ð¾Ñ€ ÐºÐ¾Ñ€Ñ€ÐµÐºÑ†Ð¸Ð¸ Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ñ Ð¸Ð³Ñ€Ð¾ÐºÐ°")).value("Free", "Focused", "Not visible").selected("Free");
   private SelectSetting sprintReset = (new SelectSetting("Ð¡Ð±Ñ€Ð¾Ñ ÑÐ¿Ñ€Ð¸Ð½Ñ‚Ð°", "Ð’Ñ‹Ð±Ð¾Ñ€ ÑÐ±Ñ€Ð¾ÑÐ° ÑÐ¿Ñ€Ð¸Ð½Ñ‚Ð° Ð¿ÐµÑ€ÐµÐ´ ÑƒÐ´Ð°Ñ€Ð¾Ð¼")).value("Legit", "Packet").selected("Legit");
   private BooleanSetting smartCrits = (new BooleanSetting("Ð£Ð´Ð°Ñ€Ñ‹ Ð½Ð° Ð·ÐµÐ¼Ð»Ðµ", "ÐšÑ€Ð¸Ñ‚Ñ‹ Ñ‚Ð¾Ð»ÑŒÐºÐ¾ Ð¿Ñ€Ð¸ Ð½Ð°Ð¶Ð°Ñ‚Ð¸Ð¸ Ð¿Ñ€Ð¾Ð±ÐµÐ»Ð°")).setValue(true).visible(() -> this.attackSetting.isSelected("Only Critical"));
   private BooleanSetting flySync = (new BooleanSetting("Ð¡Ð¸Ð½Ñ…Ñ€Ð¾Ð½Ð¸Ð·Ð°Ñ†Ð¸Ñ Ñ Ñ„Ð»Ð°ÐµÐ¼", "Ð‘Ð¸Ñ‚ÑŒ Ð² Ð¿Ð¾Ð»Ñ‘Ñ‚Ðµ Ð¸ Ð½Ð° Ñ‚Ñ€Ð°Ð½ÑÐ¿Ð¾Ñ€Ñ‚Ðµ")).setValue(false);
   private BooleanSetting highPingLagRangeSync = (new BooleanSetting("ÑÐ¸Ð½Ñ…Ñ€Ð¾Ð½Ð¸Ð·Ð°Ñ†Ð¸Ñ Ñ LagRange", "Ð–Ð´Ð°Ñ‚ÑŒ LagRange Ð¿ÐµÑ€ÐµÐ´ ÑƒÐ´Ð°Ñ€Ð¾Ð¼")).setValue(false);
   private SliderSettings lagRangeAttackDelayMs = (new SliderSettings("Ð´Ð»Ð¸Ñ‚ÐµÐ»ÑŒÐ½Ð¾ÑÑ‚ÑŒ Ð·Ð°Ð²Ð¸ÑÐ°Ð½Ð¸Ñ (ms)", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿Ð¾ÑÐ»Ðµ LagRange (ms)")).setValue(0.0F).range(0, 2000).visible(() -> this.highPingLagRangeSync.isValue());
   private final SelectSetting advancedProfile = (new SelectSetting("Ð¢Ð¸Ð¿ Ñ€Ð¾Ñ‚Ð°Ñ†Ð¸Ð¸", "ÐŸÑ€Ð¾Ñ„Ð¸Ð»ÑŒ Advanced")).value("Lite", "Hard").selected("Hard").visible(() -> this.aimMode.isSelected("Advanced"));
   private final SelectSetting advancedRotationType = (new SelectSetting("Advanced Rot Type", "Ð¢Ð¸Ð¿ Ñ€Ð¾Ñ‚Ð°Ñ†Ð¸Ð¸ Advanced")).value("Snap", "Focus").selected("Focus").visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SliderSettings advancedFov = (new SliderSettings("FOV", "ÐŸÐ¾Ð»Ðµ Ð·Ñ€ÐµÐ½Ð¸Ñ Advanced")).setValue(360.0F).range(0.0F, 360.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final BooleanSetting drawFov = (new BooleanSetting("drawFOV", "Ð Ð¸ÑÐ¾Ð²Ð°Ñ‚ÑŒ Ð¾ÐºÑ€ÑƒÐ¶Ð½Ð¾ÑÑ‚ÑŒ ÐºÐ°Ðº Ð² Legit Snap")).setValue(false).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SliderSettings horizontalSpeed = (new SliderSettings("horSpeed", "Ð“Ð¾Ñ€Ð¸Ð·Ð¾Ð½Ñ‚Ð°Ð»ÑŒÐ½Ð°Ñ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ")).setValue(4.0F).range(0.1F, 360.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SliderSettings verticalSpeed = (new SliderSettings("verSpeed", "Ð’ÐµÑ€Ñ‚Ð¸ÐºÐ°Ð»ÑŒÐ½Ð°Ñ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ")).setValue(3.0F).range(0.1F, 360.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SliderSettings horizontalAcceleration = (new SliderSettings("horAccel", "Ð“Ð¾Ñ€Ð¸Ð·Ð¾Ð½Ñ‚Ð°Ð»ÑŒÐ½Ð¾Ðµ ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ðµ")).setValue(2.0F).range(0.0F, 30.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SliderSettings verticalAcceleration = (new SliderSettings("verAccel", "Ð’ÐµÑ€Ñ‚Ð¸ÐºÐ°Ð»ÑŒÐ½Ð°Ñ ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ðµ")).setValue(1.5F).range(0.0F, 30.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SliderSettings gaussianFrequency = (new SliderSettings("Gaussian %", "Ð§Ð°ÑÑ‚Ð¾Ñ‚Ð° Ñ€Ð°Ð½Ð´Ð¾Ð¼Ð¸Ð·Ð°Ñ†Ð¸Ð¸, %")).setValue(35.0F).range(0.0F, 100.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SliderSettings gaussianSpeed = (new SliderSettings("gaussianSpeed", "Ð¡Ð¸Ð»Ð° Ð³Ð°ÑƒÑÑ. ÑˆÑƒÐ¼Ð°")).setValue(1.2F).range(0.0F, 10.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SelectSetting gaussianBodyPart = (new SelectSetting("gaussianPart", "Ð§Ð°ÑÑ‚ÑŒ Ñ‚ÐµÐ»Ð° Ð´Ð»Ñ ÑÐ¼ÐµÑ‰ÐµÐ½Ð¸Ñ")).value("Head", "Body", "Legs").selected("Body").visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SliderSettings horizontalRandomize = (new SliderSettings("horRandomize", "Ð“Ð¾Ñ€. Ñ€Ð°Ð½Ð´Ð¾Ð¼Ð¸Ð·Ð°Ñ†Ð¸Ñ Ñ…Ð¸Ñ‚Ð±Ð¾ÐºÑÐ°, %")).setValue(2.0F).range(0.0F, 30.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SliderSettings verticalRandomize = (new SliderSettings("verRandomize", "Ð’ÐµÑ€Ñ‚. Ñ€Ð°Ð½Ð´Ð¾Ð¼Ð¸Ð·Ð°Ñ†Ð¸Ñ Ñ…Ð¸Ñ‚Ð±Ð¾ÐºÑÐ°, %")).setValue(2.0F).range(0.0F, 30.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SliderSettings yawRandom = (new SliderSettings("yawRandom", "Ð¡Ð»ÑƒÑ‡Ð°Ð¹Ð½Ñ‹Ð¹ Ð¿Ð¾Ð²Ð¾Ñ€Ð¾Ñ‚ Ð¿Ð¾ Yaw")).setValue(0.0F).range(0.0F, 25.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SliderSettings pitchRandom = (new SliderSettings("pitchRandom", "Ð¡Ð»ÑƒÑ‡Ð°Ð¹Ð½Ñ‹Ð¹ Ð¿Ð¾Ð²Ð¾Ñ€Ð¾Ñ‚ Ð¿Ð¾ Pitch")).setValue(0.0F).range(0.0F, 25.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SliderSettings randomJitterFrequency = (new SliderSettings("randJitterFreq", "Ð§Ð°ÑÑ‚Ð¾Ñ‚Ð° ÑÑ€Ð°Ð±Ð°Ñ‚Ñ‹Ð²Ð°Ð½Ð¸Ñ Ñ€Ð°Ð½Ð´Ð¾Ð¼Ð°, %")).setValue(60.0F).range(0.0F, 100.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final BooleanSetting clampSteps = (new BooleanSetting("clampSteps", "ÐžÐ³Ñ€Ð°Ð½Ð¸Ñ‡Ð¸Ð²Ð°Ñ‚ÑŒ ÑˆÐ°Ð³ Ð¿Ð¾Ð²Ð¾Ñ€Ð¾Ñ‚Ð°")).setValue(true).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SliderSettings maxYawStep = (new SliderSettings("maxYawStep", "ÐœÐ°ÐºÑ. ÑˆÐ°Ð³ Ð¿Ð¾ Yaw")).setValue(8.0F).range(0.1F, 45.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.clampSteps.isValue());
   private final SliderSettings maxPitchStep = (new SliderSettings("maxPitchStep", "ÐœÐ°ÐºÑ. ÑˆÐ°Ð³ Ð¿Ð¾ Pitch")).setValue(6.0F).range(0.1F, 45.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.clampSteps.isValue());
   private final BooleanSetting heuristics = (new BooleanSetting("Heuristics", "ÐÐ½Ð°Ð»Ð¸Ð· Ð¿Ñ€Ð¾Ñ‚Ð¸Ð²Ð½Ð¸ÐºÐ° Ð´Ð»Ñ ÑƒÐ»ÑƒÑ‡ÑˆÐµÐ½Ð¸Ñ KA")).setValue(false).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SliderSettings fakelagAnalyzerDistance = (new SliderSettings("fakeLagDist", "Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð°Ð½Ð°Ð»Ð¸Ð·Ð° Ñ‚ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð¾Ð²")).setValue(2.0F).range(0.5F, 6.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.heuristics.isValue());
   private final BooleanSetting nearestBestHitVec = (new BooleanSetting("nearestBestHitVec", "Ð’Ñ‹Ð±Ð¾Ñ€ Ð»ÑƒÑ‡ÑˆÐµÐ¹ Ñ‚Ð¾Ñ‡ÐºÐ¸ ÑƒÐ´Ð°Ñ€Ð°")).setValue(true).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final BooleanSetting mouseFovVec = (new BooleanSetting("mouseFOVVec", "ÐŸÑ€Ð¸Ñ†ÐµÐ» Ð² Ñ‚Ð¾Ñ‡ÐºÑƒ ÐºÑƒÑ€ÑÐ¾Ñ€Ð° Ð¿Ð¾ Ñ…Ð¸Ñ‚Ð±Ð¾ÐºÑÑƒ")).setValue(true).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final BooleanSetting pointSelection = (new BooleanSetting("pointSelection", "ÐÐ°ÑÑ‚Ñ€Ð¾Ð¹ÐºÐ° Ð¾Ð±Ð»Ð°ÑÑ‚Ð¸ Ð½Ð°Ð²ÐµÐ´ÐµÐ½Ð¸Ñ")).setValue(false).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final BooleanSetting pointSelVertical = (new BooleanSetting("PSVertical", "Ð’ÐµÑ€Ñ‚Ð¸ÐºÐ°Ð»ÑŒÐ½Ð°Ñ Ð¾Ð±Ð»Ð°ÑÑ‚ÑŒ")).setValue(true).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.pointSelection.isValue());
   private final BooleanSetting pointSelHorizontal = (new BooleanSetting("PSHorizontal", "Ð“Ð¾Ñ€Ð¸Ð·Ð¾Ð½Ñ‚Ð°Ð»ÑŒÐ½Ð°Ñ Ð¾Ð±Ð»Ð°ÑÑ‚ÑŒ")).setValue(true).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.pointSelection.isValue());
   private final SliderSettings pointSelVSpeed = (new SliderSettings("PSVerSpeed", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ ÑÐ¼ÐµÐ½Ñ‹ Ð²ÐµÑ€Ñ‚Ð¸ÐºÐ°Ð»ÑŒÐ½Ð¾")).setValue(2.0F).range(0.0F, 180.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.pointSelection.isValue());
   private final SliderSettings pointSelHSpeed = (new SliderSettings("PSHorSpeed", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ ÑÐ¼ÐµÐ½Ñ‹ Ð³Ð¾Ñ€Ð¸Ð·Ð¾Ð½Ñ‚Ð°Ð»ÑŒÐ½Ð¾")).setValue(2.0F).range(0.0F, 180.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.pointSelection.isValue());
   private final SliderSettings pointSelFreq = (new SliderSettings("PSFreq", "Ð§Ð°ÑÑ‚Ð¾Ñ‚Ð° ÑÐ¼ÐµÐ½Ñ‹ Ð¿Ð¾Ð·Ð¸Ñ†Ð¸Ð¸")).setValue(1.0F).range(0.0F, 10.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.pointSelection.isValue());
   private final BooleanSetting thornsArmorAnalyzed = (new BooleanSetting("thornsAnalyzed", "Ð˜Ð·Ð±ÐµÐ³Ð°Ñ‚ÑŒ ÑƒÐ´Ð°Ñ€Ð¾Ð² Ð¿Ð¾ Ñ‡Ð°ÑÑ‚ÑÐ¼ Ñ Thorns")).setValue(true).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final BooleanSetting stopRotationInsideTarget = (new BooleanSetting("stopRotInside", "Ð¡Ñ‚Ð¾Ð¿ Ñ€Ð°Ð½Ð´Ð¾Ð¼Ð¸Ð·Ð°Ñ†Ð¸ÑŽ Ð² 1x2 Ð¿Ñ€Ð¸ ÐºÐ¾Ð½Ñ‚Ð°ÐºÑ‚Ðµ")).setValue(true).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final BooleanSetting lockView = (new BooleanSetting("lockView", "ÐšÐ»Ð¸ÐµÐ½Ñ‚ Ð²Ð¸Ð´Ð¸Ñ‚ Ñ‚Ðµ Ð¶Ðµ Ð¿Ð¾Ð²Ð¾Ñ€Ð¾Ñ‚Ñ‹")).setValue(false).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final BooleanSetting snapOnAttack = (new BooleanSetting("snapOnAttack", "Ð¡Ð½Ð°Ð¿ Ð¿Ñ€Ð¸ Ð°Ñ‚Ð°ÐºÐµ (Ð»Ð¾Ð¼Ð°Ñ‚ÑŒ Ñ€Ð°Ð½Ð´Ð¾Ð¼)")).setValue(true).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SelectSetting targetSort = (new SelectSetting("Target Sort", "Ð ÐµÐ¶Ð¸Ð¼ ÑÐ¾Ñ€Ñ‚Ð¸Ñ€Ð¾Ð²ÐºÐ¸ Ñ†ÐµÐ»ÐµÐ¹")).value("health", "armorEndurance", "distance", "FOV", "armorWorse", "armorBetter").selected("distance").visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final MultiSelectSetting targetEntity = (new MultiSelectSetting("Target Entity", "Ð’Ñ‹Ð±Ð¾Ñ€ ÑÑƒÑ‰Ð½Ð¾ÑÑ‚ÐµÐ¹ Ð´Ð»Ñ Ñ‚Ð°Ñ€Ð³ÐµÑ‚Ð°")).value("player", "invisible", "naked", "mob", "villager", "animals", "dead", "armorStand").selected("player").visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SelectSetting targetSortMode = (new SelectSetting("Target Mode", "Single or Switch")).value("single", "switch").selected("single").visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SliderSettings targetLockDistance = (new SliderSettings("Lock Distance", "Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ lock (single)")).setValue(10.0F).range(1.0F, 20.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.targetSortMode.isSelected("single"));
   private final SliderSettings targetLockTime = (new SliderSettings("Lock Time", "Ð’Ñ€ÐµÐ¼Ñ lock (ÑÐµÐº, single)")).setValue(10.0F).range(1.0F, 30.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.targetSortMode.isSelected("single"));
   private final BooleanSetting smartLock = (new BooleanSetting("Smart Lock", "Ð’Ð¾Ð·Ð²Ñ€Ð°Ñ‚ Ðº Ð¿Ñ€Ð¾ÑˆÐ»Ð¾Ð¼Ñƒ Ñ‚Ð°Ñ€Ð³ÐµÑ‚Ñƒ Ð¿Ñ€Ð¸ Ð¿Ñ€Ð¸Ð±Ð»Ð¸Ð¶ÐµÐ½Ð¸Ð¸")).setValue(false).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.targetSortMode.isSelected("single"));
   private final SliderSettings smartLockDistance = (new SliderSettings("Smart Lock Dist", "Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ ÑÑ€Ð°Ð±Ð°Ñ‚Ñ‹Ð²Ð°Ð½Ð¸Ñ smartLock")).setValue(6.0F).range(1.0F, 20.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.targetSortMode.isSelected("single") && this.smartLock.isValue());
   private final BooleanSetting onlyLowHealth = (new BooleanSetting("Only Low HP", "SmartLock Ñ‚Ð¾Ð»ÑŒÐºÐ¾ ÐµÑÐ»Ð¸ HP Ð½Ð¸Ð¶Ðµ")).setValue(false).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.targetSortMode.isSelected("single") && this.smartLock.isValue());
   private final BooleanSetting accelerationEnabled = (new BooleanSetting("accelerationEnabled", "Ð£ÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ðµ Ñ€Ð¾Ñ‚Ð°Ñ†Ð¸Ð¸")).setValue(false).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SelectSetting accelerationMode = (new SelectSetting("accelerationMode", "Acceleration mode")).value("Beginning", "Culmination").selected("Beginning").visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.accelerationEnabled.isValue());
   private final BooleanSetting decelerationEnabled = (new BooleanSetting("decelerationEnabled", "Ð—Ð°Ð¼ÐµÐ´Ð»ÐµÐ½Ð¸Ðµ Ñ€Ð¾Ñ‚Ð°Ñ†Ð¸Ð¸")).setValue(false).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SelectSetting decelerationMode = (new SelectSetting("decelerationMode", "Deceleration mode")).value("Ending", "Culmination").selected("Ending").visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.decelerationEnabled.isValue());
   private final SliderSettings smoothnessFactor = (new SliderSettings("smoothnessFactor", "Ð¤Ð°ÐºÑ‚Ð¾Ñ€ Ð¿Ð»Ð°Ð²Ð½Ð¾ÑÑ‚Ð¸")).setValue(0.6F).range(0.0F, 1.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SliderSettings responseTimeMs = (new SliderSettings("responseTimeMs", "Ð’Ñ€ÐµÐ¼Ñ Ð¾Ñ‚ÐºÐ»Ð¸ÐºÐ° (Ð¼Ñ)")).setValue(150.0F).range(0.0F, 1000.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SliderSettings maxAngleChangePerTick = (new SliderSettings("maxAngleChangePerTick", "ÐœÐ°ÐºÑ. Ð¸Ð·Ð¼ÐµÐ½ÐµÐ½Ð¸Ðµ ÑƒÐ³Ð»Ð°/Ñ‚Ð¸Ðº")).setValue(12.0F).range(0.1F, 90.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final BooleanSetting clampAngles = (new BooleanSetting("clampAngles", "ÐžÐ³Ñ€Ð°Ð½Ð¸Ñ‡Ð¸Ð²Ð°Ñ‚ÑŒ ÑƒÐ³Ð»Ñ‹")).setValue(true).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final BooleanSetting humanizeAngles = (new BooleanSetting("humanizeAngles", "Ð§ÐµÐ»Ð¾Ð²ÐµÑ‡ÐµÑÐºÐ°Ñ Ð½ÐµÑ‚Ð¾Ñ‡Ð½Ð¾ÑÑ‚ÑŒ")).setValue(true).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final BooleanSetting predictionEnabled = (new BooleanSetting("predictionEnabled", "Ð’ÐºÐ»ÑŽÑ‡Ð¸Ñ‚ÑŒ Ð¿Ñ€ÐµÐ´ÑÐºÐ°Ð·Ð°Ð½Ð¸Ðµ")).setValue(false).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SelectSetting predictionType = (new SelectSetting("predictionType", "Prediction type")).value("Linear", "Quadratic", "Adaptive").selected("Linear").visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.predictionEnabled.isValue());
   private final SliderSettings predictionTimeSec = (new SliderSettings("predictionTimeSec", "Ð’Ñ€ÐµÐ¼Ñ Ð¿Ñ€ÐµÐ´ÑÐºÐ°Ð·Ð°Ð½Ð¸Ñ (ÑÐµÐº)")).setValue(0.15F).range(0.0F, 0.8F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.predictionEnabled.isValue());
   private final BooleanSetting velocityPrediction = (new BooleanSetting("velocityPrediction", "ÐŸÑ€ÐµÐ´ÑÐºÐ°Ð·Ð°Ð½Ð¸Ðµ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚Ð¸")).setValue(true).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.predictionEnabled.isValue());
   private final BooleanSetting gravityPrediction = (new BooleanSetting("gravityPrediction", "Ð£Ñ‡Ñ‘Ñ‚ Ð³Ñ€Ð°Ð²Ð¸Ñ‚Ð°Ñ†Ð¸Ð¸")).setValue(false).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.predictionEnabled.isValue());
   private final BooleanSetting bouncePrediction = (new BooleanSetting("bouncePrediction", "ÐŸÑ€ÐµÐ´ÑÐºÐ°Ð·Ð°Ð½Ð¸Ðµ Ð¾Ñ‚ÑÐºÐ¾ÐºÐ¾Ð²")).setValue(false).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.predictionEnabled.isValue());
   private final BooleanSetting terrainAwareness = (new BooleanSetting("terrainAwareness", "Ð£Ñ‡Ñ‘Ñ‚ Ñ€ÐµÐ»ÑŒÐµÑ„Ð°")).setValue(false).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.predictionEnabled.isValue());
   private final SliderSettings predictionSmoothing = (new SliderSettings("predictionSmoothing", "Ð¡Ð³Ð»Ð°Ð¶Ð¸Ð²Ð°Ð½Ð¸Ðµ Ð¿Ñ€ÐµÐ´ÑÐºÐ°Ð·Ð°Ð½Ð¸Ñ")).setValue(0.5F).range(0.0F, 1.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.predictionEnabled.isValue());
   private final SliderSettings predictionUpdateRate = (new SliderSettings("predictionUpdateRate", "Ð§Ð°ÑÑ‚Ð¾Ñ‚Ð° Ð¾Ð±Ð½Ð¾Ð²Ð»ÐµÐ½Ð¸Ñ Ð¿Ñ€ÐµÐ´ÑÐºÐ°Ð·Ð°Ð½Ð¸Ñ")).setValue(5.0F).range(1.0F, 30.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.predictionEnabled.isValue());
   private final SliderSettings historicalDataSize = (new SliderSettings("historicalDataSize", "Ð Ð°Ð·Ð¼ÐµÑ€ Ð¸ÑÑ‚Ð¾Ñ€Ð¸Ð¸")).setValue(12.0F).range(3.0F, 60.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.predictionEnabled.isValue());
   private final SliderSettings confidenceThreshold = (new SliderSettings("confidenceThreshold", "ÐŸÐ¾Ñ€Ð¾Ð³ ÑƒÐ²ÐµÑ€ÐµÐ½Ð½Ð¾ÑÑ‚Ð¸")).setValue(0.4F).range(0.0F, 1.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.predictionEnabled.isValue());
   private final SelectSetting fallbackMode = (new SelectSetting("fallbackMode", "Fallback mode")).value("None", "Linear", "Center").selected("Linear").visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.predictionEnabled.isValue());
   private final BooleanSetting learningMode = (new BooleanSetting("learningMode", "Ð ÐµÐ¶Ð¸Ð¼ Ð¾Ð±ÑƒÑ‡ÐµÐ½Ð¸Ñ")).setValue(false).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final BooleanSetting patternRecognition = (new BooleanSetting("patternRecognition", "Ð Ð°ÑÐ¿Ð¾Ð·Ð½Ð°Ð²Ð°Ð½Ð¸Ðµ Ð¿Ð°Ñ‚Ñ‚ÐµÑ€Ð½Ð¾Ð²")).setValue(false).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.learningMode.isValue());
   private final BooleanSetting counterMeasures = (new BooleanSetting("counterMeasures", "ÐšÐ¾Ð½Ñ‚Ñ€Ð¼ÐµÑ€Ñ‹")).setValue(false).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.learningMode.isValue());
   private final BooleanSetting fovOptimization = (new BooleanSetting("fovOptimization", "FOV Optimization")).setValue(false).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final BooleanSetting obstacleAvoidance = (new BooleanSetting("obstacleAvoidance", "Obstacle Avoidance")).setValue(false).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final BooleanSetting targetTracking = (new BooleanSetting("targetTracking", "Target Tracking")).setValue(false).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final BooleanSetting predictDodges = (new BooleanSetting("predictDodges", "Predict Dodges")).setValue(false).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SelectSetting calculationMethod = (new SelectSetting("calculationMethod", "Calculation Method")).value("Raycast", "BoundingBox", "Center", "ClosestPoint").selected("Raycast").visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SelectSetting raycastMode = (new SelectSetting("raycastMode", "Raycast rules")).value("Off", "Entity", "Ignore").selected("Entity").visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.calculationMethod.isSelected("Raycast"));
   private final SliderSettings hitboxExpansion = (new SliderSettings("hitboxExpansion", "Hitbox Expansion")).setValue(0.0F).range(0.0F, 1.5F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SelectSetting hitboxMode = (new SelectSetting("hitboxMode", "Hitbox Mode")).value("Static", "Dynamic").selected("Static").visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SliderSettings humanError = (new SliderSettings("humanError", "Human Error")).setValue(0.4F).range(0.0F, 2.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard") && this.humanizeAngles.isValue());
   private final BooleanSetting mouseAcceleration = (new BooleanSetting("mouseAcceleration", "Mouse Acceleration")).setValue(false).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SliderSettings naturalMissRate = (new SliderSettings("naturalMissRate", "Natural Miss Rate, %")).setValue(0.0F).range(0.0F, 30.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SliderSettings fatigueSimulation = (new SliderSettings("fatigueSimulation", "Fatigue Simulation")).setValue(0.0F).range(0.0F, 1.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SliderSettings reactionTime = (new SliderSettings("reactionTime", "Reaction Time (ms)")).setValue(0.0F).range(0.0F, 400.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Hard"));
   private final SelectSetting liteInterpolationMode = (new SelectSetting("Ð˜Ð½Ñ‚ÐµÑ€Ð¿Ð¾Ð»ÑÑ†Ð¸Ñ", "Ð˜Ð½Ñ‚ÐµÑ€Ð¿Ð¾Ð»ÑÑ†Ð¸Ñ Lite")).value("Ð›Ð¸Ð½ÐµÐ¹Ð½Ð°Ñ", "ÐšÐ²Ð°Ð´Ñ€Ð°Ñ‚Ð¸Ñ‡Ð½Ð°Ñ", "ÐšÑƒÐ±Ð¸Ñ‡ÐµÑÐºÐ°Ñ", "Ð¡Ð¸Ð½ÑƒÑÐ¾Ð¸Ð´Ð°Ð»ÑŒÐ½Ð°Ñ", "Ð‘ÐµÐ· Ð¸Ð½Ñ‚ÐµÑ€Ð¿Ð¾Ð»ÑÑ†Ð¸Ð¸").selected("Ð›Ð¸Ð½ÐµÐ¹Ð½Ð°Ñ").visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Lite"));
   private final SelectSetting liteNoiseMode = (new SelectSetting("Ð¨ÑƒÐ¼", "Ð¨ÑƒÐ¼ Lite")).value("off", "Ð Ð°Ð²Ð½Ð¾Ð¼ÐµÑ€Ð½Ñ‹Ð¹", "Ð“Ð°ÑƒÑÑÐ¾Ð²ÑÐºÐ¸Ð¹", "ÐŸÐµÑ€Ð»Ð¸Ð½", "Ð¡Ð»ÑƒÑ‡Ð°Ð¹Ð½Ñ‹Ð¹ ÑˆÐ°Ð³", "Ð¡Ð»ÑƒÑ‡Ð°Ð¹Ð½Ñ‹Ð¹ Ð²Ñ‹Ð±Ð¾Ñ€").selected("off").visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Lite"));
   private final SliderSettings liteSmoothnessH = (new SliderSettings("ÐŸÐ»Ð°Ð²Ð½Ð¾ÑÑ‚ÑŒ XZ", "ÐŸÐ»Ð°Ð²Ð½Ð¾ÑÑ‚ÑŒ XZ Lite")).setValue(1.0F).range(0.0F, 40.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Lite"));
   private final SliderSettings liteSmoothnessV = (new SliderSettings("ÐŸÐ»Ð°Ð²Ð½Ð¾ÑÑ‚ÑŒ Y", "ÐŸÐ»Ð°Ð²Ð½Ð¾ÑÑ‚ÑŒ Y Lite")).setValue(1.0F).range(0.0F, 40.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Lite"));
   private final SliderSettings liteInitialAimSpeed = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ ÐÐ°Ð²Ð¾Ð´ÐºÐ¸", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ ÐÐ°Ð²Ð¾Ð´ÐºÐ¸ Lite")).setValue(180.0F).range(30.0F, 360.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Lite"));
   private final SliderSettings liteTargetTrackSpeed = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¢Ð°Ñ€Ð³ÐµÑ‚Ð°", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¢Ð°Ñ€Ð³ÐµÑ‚Ð° Lite")).setValue(120.0F).range(30.0F, 360.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Lite"));
   private final SliderSettings liteShakeIntensityYaw = (new SliderSettings("Ð˜Ð½Ñ‚ÐµÐ½ÑÐ¸Ð²Ð½Ð¾ÑÑ‚ÑŒ Ñ‚Ñ€ÑÑÐºÐ¸ Yaw", "Ð¢Ñ€ÑÑÐºÐ° Yaw Lite")).setValue(2.5F).range(0.0F, 40.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Lite"));
   private final SliderSettings liteShakeIntensityPitch = (new SliderSettings("Ð˜Ð½Ñ‚ÐµÐ½ÑÐ¸Ð²Ð½Ð¾ÑÑ‚ÑŒ Ñ‚Ñ€ÑÑÐºÐ¸ Pitch", "Ð¢Ñ€ÑÑÐºÐ° Pitch Lite")).setValue(2.5F).range(0.0F, 40.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Lite"));
   private final SliderSettings liteShakeSpeedYaw = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ñ‚Ñ€ÑÑÐºÐ¸ Yaw", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ñ‚Ñ€ÑÑÐºÐ¸ Yaw Lite")).setValue(7.0F).range(1.0F, 40.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Lite"));
   private final SliderSettings liteShakeSpeedPitch = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ñ‚Ñ€ÑÑÐºÐ¸ Pitch", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ñ‚Ñ€ÑÑÐºÐ¸ Pitch Lite")).setValue(7.0F).range(1.0F, 40.0F).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Lite"));
   private final BooleanSetting liteTakePlayerPitch = (new BooleanSetting("Ð‘Ñ€Ð°Ñ‚ÑŒ Ð¿Ð¸Ñ‚Ñ‡ Ð¾Ñ‚ Ð¸Ð³Ñ€Ð¾ÐºÐ°", "ÐŸÐ¸Ñ‚Ñ‡ Ð¸Ð³Ñ€Ð¾ÐºÐ° Ð´Ð»Ñ Lite")).setValue(false).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Lite"));
   private final BooleanSetting liteMultiPoints = (new BooleanSetting("ÐœÑƒÐ»ÑŒÑ‚Ð¸Ð¿Ð¾Ð¸Ð½Ñ‚Ñ‹", "ÐœÑƒÐ»ÑŒÑ‚Ð¸Ð¿Ð¾Ð¸Ð½Ñ‚Ñ‹ Lite")).setValue(true).visible(() -> this.aimMode.isSelected("Advanced") && this.advancedProfile.isSelected("Lite"));
   private class_243 lastAimPoint = null;
   private boolean maceNoCooldownOnce = false;
   private class_1799 lastMainHand;
   private final List packets;
   private class_238 box;
   public static int tickStop = -1;
   private final StopWatch cpsTimer;
   private final StopWatch lagRangeAttackTimer;
   private final StopWatch shieldBreakTimer;
   private int previousAxeSlot;
   private boolean breakingShield;
   private boolean raycastBlockedThisTick;
   public static boolean shouldRotate;
   private final Map fakelagTrace;
   private final Map lastTelTicks;
   private long lastPointSwitchMs;
   public boolean elytraStateForward;
   private boolean wasForwardPressed;

   public static Aura getInstance() {
      return (Aura)Instance.get(Aura.class);
   }

   public class_1309 getCurrentTarget() {
      return this.target;
   }

   public Aura() {
      super("Aura", ModuleCategory.COMBAT);
      this.lastMainHand = class_1799.field_8037;
      this.packets = new CopyOnWriteArrayList();
      this.cpsTimer = new StopWatch();
      this.lagRangeAttackTimer = new StopWatch();
      this.shieldBreakTimer = new StopWatch();
      this.previousAxeSlot = -1;
      this.breakingShield = false;
      this.raycastBlockedThisTick = false;
      this.fakelagTrace = new ConcurrentHashMap();
      this.lastTelTicks = new ConcurrentHashMap();
      this.lastPointSwitchMs = 0L;
      this.elytraStateForward = false;
      this.wasForwardPressed = false;
      List<String> options = new ArrayList();
      options.add("None");
      options.addAll(MirorAiManager.getInstance().getModelNames());
      this.mirorAiModel.value((String[])options.toArray(new String[0]));
      this.mirorAiModel.selected("None");
      this.setup(new Setting[]{this.aimMode, this.correctionType, this.sprintReset, this.targetType, this.mirorAiModel, this.attackRange, this.lookRange, this.hitChance, this.hitInfoMode, this.attackSetting, this.shieldBreaker, this.shieldSprintBreaker, this.highPingLagRangeSync, this.lagRangeAttackDelayMs, this.clickMode, this.clickCps, this.smartCrits, this.flySync, this.advancedProfile, this.advancedRotationType, this.advancedFov, this.drawFov, this.horizontalSpeed, this.verticalSpeed, this.horizontalAcceleration, this.verticalAcceleration, this.gaussianFrequency, this.gaussianSpeed, this.gaussianBodyPart, this.horizontalRandomize, this.verticalRandomize, this.yawRandom, this.pitchRandom, this.randomJitterFrequency, this.clampSteps, this.maxYawStep, this.maxPitchStep, this.heuristics, this.fakelagAnalyzerDistance, this.nearestBestHitVec, this.mouseFovVec, this.pointSelection, this.pointSelVertical, this.pointSelHorizontal, this.pointSelVSpeed, this.pointSelHSpeed, this.pointSelFreq, this.thornsArmorAnalyzed, this.stopRotationInsideTarget, this.lockView, this.snapOnAttack, this.liteInterpolationMode, this.liteNoiseMode, this.liteSmoothnessH, this.liteSmoothnessV, this.liteInitialAimSpeed, this.liteTargetTrackSpeed, this.liteShakeIntensityYaw, this.liteShakeIntensityPitch, this.liteShakeSpeedYaw, this.liteShakeSpeedPitch, this.liteTakePlayerPitch, this.liteMultiPoints, this.targetSort, this.targetEntity, this.targetSortMode, this.targetLockDistance, this.targetLockTime, this.smartLock, this.smartLockDistance, this.onlyLowHealth, this.accelerationEnabled, this.accelerationMode, this.decelerationEnabled, this.decelerationMode, this.smoothnessFactor, this.responseTimeMs, this.maxAngleChangePerTick, this.clampAngles, this.humanizeAngles, this.predictionEnabled, this.predictionType, this.predictionTimeSec, this.velocityPrediction, this.gravityPrediction, this.bouncePrediction, this.terrainAwareness, this.predictionSmoothing, this.predictionUpdateRate, this.historicalDataSize, this.confidenceThreshold, this.fallbackMode, this.learningMode, this.patternRecognition, this.counterMeasures, this.fovOptimization, this.obstacleAvoidance, this.targetTracking, this.predictDodges, this.calculationMethod, this.raycastMode, this.hitboxExpansion, this.hitboxMode, this.humanError, this.mouseAcceleration, this.naturalMissRate, this.fatigueSimulation, this.reactionTime});
      this.fovCircleRenderer = new FovCircleRenderer();
      Yanderov.instance.getEventManager().register(this.fovCircleRenderer);
   }

   public void deactivate() {
      this.targetSelector.releaseTarget();
      this.target = null;
      this.packets.forEach(PlayerInteractionHelper::sendPacketWithOutEvent);
      this.packets.clear();
      this.breakingShield = false;
      this.previousAxeSlot = -1;
      super.deactivate();
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      class_2596 var3 = e.getPacket();
      if (var3 instanceof class_2663 status) {
         if (status.method_11470() == 30) {
            class_1297 entity = status.method_11469(mc.field_1687);
            if (entity != null && entity.equals(this.target) && Hud.getInstance().notificationSettings.isSelected("Break Shield")) {
               Notifications.getInstance().addList((class_2561)class_2561.method_43470("Ð¡Ð»Ð¾Ð¼Ð°Ð»Ð¸ Ñ‰Ð¸Ñ‚ Ð¸Ð³Ñ€Ð¾ÐºÑƒ - ").method_10852(entity.method_5476()), 5000L);
            }
         }
      }

      if (this.attackSetting.isSelected("Fake Lag") && this.target != null) {
         if (!PlayerInteractionHelper.nullCheck()) {
            class_2596 var10000 = e.getPacket();
            Objects.requireNonNull(var10000);
            class_2596 var7 = var10000;
            byte var9 = 0;

            while(true) {
               //$FF: var9->value
               //0->net/minecraft/class_2724
               //1->net/minecraft/class_2678
               //2->net/minecraft/class_2799
               // TODO: Fix switch statement for var7
        if (var7 != null) {
            // // case 0:
            // class_2724 respawn = (class_2724)var7;
            // this.setState(false);
            // return;
            // // case 1:
            // class_2678 join = (class_2678)var7;
            // this.setState(false);
            // return;
            // // case 2:
            // class_2799 status = (class_2799)var7;
            // if (status.method_12119().equals(class_2800.field_12774)) {
            // this.setState(false);
            // return;
            // }

            // var9 = 3;
            // break;
            // // default:
            // if (e.isSend() && tickStop < 0) {
            // this.packets.add(e.getPacket());
            // e.cancel();
            // }

            // return;
               }
            }
         }
      }
   }

   @EventHandler
   public void tick(TickEvent e) {
      if (!PlayerInteractionHelper.nullCheck()) {
         if (mc.field_1724 != null) {
            class_1799 current = mc.field_1724.method_6047();
            if (!this.lastMainHand.method_7960() && !this.isMace(this.lastMainHand) && this.isMace(current)) {
               this.maceNoCooldownOnce = true;
            }

            this.lastMainHand = current.method_7972();
         }

         if (this.target != null) {
            --tickStop;
            if (tickStop >= 0 && !this.packets.isEmpty() && this.attackSetting.isSelected("Fake Lag")) {
               this.box = mc.field_1724.method_5829();
               this.packets.forEach(PlayerInteractionHelper::sendPacketWithOutEvent);
               this.packets.clear();
            }

            if (mc.field_1724.method_5739(this.target) > this.attackRange.getValue() && this.attackSetting.isSelected("Fake Lag")) {
               this.packets.forEach(PlayerInteractionHelper::sendPacketWithOutEvent);
               this.packets.clear();
            }

         }
      }
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      if (this.box != null && this.attackSetting.isSelected("Fake Lag") && this.target != null) {
         Render3D.drawBox(this.box, ColorAssist.getClientColor(), 1.0F);
      }

   }

   @EventHandler
   public void onRotationUpdate(RotationUpdateEvent e) {
      TPInfluence tpInfluence = TPInfluence.getInstance();
      if (!tpInfluence.isState() || tpInfluence.isInTeleportCycle()) {
         if (!tpInfluence.isState() || !tpInfluence.isReturningHome() || !tpInfluence.isSilentRotations()) {
            try {
               if (this.aimMode.isSelected("FunTime") && Yanderov.instance.getFtCheckClient() != null) {
                  Yanderov.instance.getFtCheckClient().checkAndWarnFunTime();
               }
            } catch (Exception var8) {
            }

            boolean mirorAiNoModel = this.aimMode.isSelected("MirorAi") && (this.mirorAiModel.getSelected().equalsIgnoreCase("None") || MirorAiManager.getInstance().getModel(this.mirorAiModel.getSelected()) == null);
            switch (e.getType()) {
               case 0:
                  if (mirorAiNoModel) {
                     this.target = null;
                     return;
                  }

                  this.target = this.updateTarget();
                  if (this.target != null) {
                     if (this.aimMode.isSelected("Advanced") && this.reactionTime.getValue() > 0.0F && ThreadLocalRandom.current().nextDouble() < Math.min((double)1.0F, (double)this.reactionTime.getValue() / (double)400.0F)) {
                        this.lastTarget = this.target;
                        return;
                     }

                     this.rotateToTarget(this.getConfig());
                     this.lastTarget = this.target;
                  }
                  break;
               case 2:
                  if (mirorAiNoModel) {
                     return;
                  }

                  if (this.target != null) {
                     if (this.aimMode.isSelected("Advanced") && this.calculationMethod.isSelected("Raycast")) {
                        if (this.raycastMode.isSelected("Entity") && this.raycastBlockedThisTick) {
                           this.raycastBlockedThisTick = false;
                           return;
                        }

                        this.raycastBlockedThisTick = false;
                     }

                     if (this.aimMode.isSelected("Advanced")) {
                        double miss = (double)this.naturalMissRate.getValue() / (double)100.0F;
                        if (miss > (double)0.0F && ThreadLocalRandom.current().nextDouble() < miss) {
                           return;
                        }
                     }

                     StrikerConstructor.AttackPerpetratorConfigurable config = this.getConfig();
                     if (this.attackSetting.isSelected("Break Shield") && this.shieldBreaker.isValue()) {
                        class_1309 var6 = this.target;
                        if (var6 instanceof class_1657) {
                           class_1657 playerTarget = (class_1657)var6;
                           if (this.tryShieldBreak(playerTarget, config)) {
                              return;
                           }
                        }
                     }

                     if (this.highPingLagRangeSync.isValue()) {
                        FakeLag lagRange = (FakeLag)Instance.get(FakeLag.class);
                        if (lagRange != null && lagRange.isState()) {
                           long delay = (long)this.lagRangeAttackDelayMs.getValue();
                           if (!this.lagRangeAttackTimer.finished((double)delay)) {
                              return;
                           }
                        } else {
                           this.lagRangeAttackTimer.reset();
                        }
                     }

                     if (tpInfluence != null && tpInfluence.isState() && tpInfluence.isInTeleportCycle()) {
                        Criticals crits = Criticals.getInstance();
                        if (crits != null && crits.isState()) {
                           crits.doCritical();
                        }
                     }

                     if (MirorAiManager.getInstance().isTraining() && this.target != null && this.lastAimPoint != null) {
                        class_243 idealPoint = this.target.method_5829().method_1005();
                        MirorAiManager.getInstance().recordSample(this.target, this.lastAimPoint, idealPoint);
                        int count = MirorAiManager.getInstance().getCurrentSamplesCount();
                        if (mc.field_1724 != null) {
                           mc.field_1724.method_7353(class_2561.method_43470("ÑeÐ¼Ð¿Ð»Ð¾Ð²: " + count), false);
                        }
                     }

                     if (this.clickMode.isSelected("1.8")) {
                        float cps = this.clickCps.getValue();
                        if (cps <= 0.0F) {
                           return;
                        }

                        long delay = (long)(1000.0F / cps);
                        if (this.cpsTimer.finished((double)delay)) {
                           this.cpsTimer.reset();
                           Yanderov.instance.getAttackPerpetrator().performAttack(config);
                        }
                     } else {
                        Yanderov.instance.getAttackPerpetrator().performAttack(config);
                     }
                  }
            }

         }
      }
   }

   private class_1309 updateTarget() {
      List<String> targetSettings = this.targetType.getSelected();
      if (this.aimMode.isSelected("Advanced")) {
         targetSettings = List.of("Players");
      }

      TargetFinder.EntityFilter filter = new TargetFinder.EntityFilter(targetSettings);
      float range = this.attackRange.getValue() + 0.253F + (mc.field_1724.method_6128() && ElytraTarget.getInstance().isState() ? ElytraTarget.getInstance().elytraFindRange.getValue() : this.lookRange.getValue());
      TPInfluence tp = TPInfluence.getInstance();
      if (tp != null && tp.isState()) {
         if (tp.isReturningHome()) {
            return null;
         }

         if (tp.getTarget() != null && !tp.getTarget().method_31481() && tp.getTarget().method_5805()) {
            boolean inRange;
            if (tp.isClientEsp() && tp.getLastTpPos() != null) {
               inRange = tp.getLastTpPos().method_1025(tp.getTarget().method_19538()) <= (double)(range * range);
            } else {
               inRange = mc.field_1724.method_5858(tp.getTarget()) <= (double)(range * range);
            }

            if (inRange) {
               return tp.getTarget();
            }
         }
      }

      float dynamicFov = 360.0F;
      if (this.aimMode.isSelected("Legit Snap") && this.fovCircleRenderer != null) {
         dynamicFov = this.fovCircleRenderer.getCachedDynamicFov();
      } else if (this.aimMode.isSelected("Advanced")) {
         dynamicFov = this.advancedFov.getValue();
      }

      if (!this.aimMode.isSelected("Advanced")) {
         this.targetSelector.searchTargets(mc.field_1687.method_18112(), range, dynamicFov, this.attackSetting.isSelected("Ignore The Walls"));
         TargetFinder var10000 = this.targetSelector;
         Objects.requireNonNull(filter);
         var10000.validateTarget(filter::isValid);
         return this.targetSelector.getCurrentTarget();
      } else {
         List<class_1309> candidates = this.collectCandidates(range, dynamicFov);
         candidates.removeIf((entity) -> !filter.isValid(entity));
         if (!this.targetEntity.getSelected().isEmpty()) {
            this.applyEntityFilter(candidates);
         }

         this.sortCandidates(candidates, this.targetSort.getSelected());
         class_1309 chosen = this.pickByTargetMode(candidates);
         return chosen;
      }
   }

   @Native(
      type = Native.Type.VMProtectBeginMutation
   )
   private void rotateToTarget(StrikerConstructor.AttackPerpetratorConfigurable config) {
      StrikeManager attackHandler = Yanderov.instance.getAttackPerpetrator().getAttackHandler();
      TurnsConnection controller = TurnsConnection.INSTANCE;
      Turns targetAngle = config.getAngle();
      if (this.aimMode.isSelected("Advanced") || this.aimMode.isSelected("minarai")) {
         targetAngle = this.limitAngleChangeAdvanced(controller.getRotation(), targetAngle);
      }

      Turns.VecRotation rotation = new Turns.VecRotation(targetAngle, targetAngle.toVector());
      TurnsConfig rotationConfig = this.getRotationConfig();
      boolean elytraMode = mc.field_1724.method_6128() && this.attackSetting.isSelected("Elytra possibilities");
      if (fakeRotate && this.target != null) {
         FakeAngle fake = new FakeAngle();
         Turns fakeRot = fake.limitAngleChange(controller.getRotation(), rotation.getAngle(), rotation.getVec(), this.target);
         controller.setFakeRotation(fakeRot);
      }

      fakeRotate = false;
      if ((this.aimMode.isSelected("Advanced") || this.aimMode.isSelected("minarai")) && this.lockView.isValue() && this.target != null) {
         controller.setFakeRotation(rotation.getAngle());
      }

      switch (this.aimMode.getSelected()) {
         case "FunTime":
            if (attackHandler.canAttack(config, 5)) {
               controller.clear();
               controller.rotateTo(rotation, this.target, 40, rotationConfig, TaskPriority.HIGH_IMPORTANCE_1, this);
            }
            break;
         case "HolyWorld":
            if (attackHandler.canAttack(config, 10) || !attackHandler.getAttackTimer().finished((double)150.0F)) {
               controller.rotateTo(rotation, this.target, 10, rotationConfig, TaskPriority.HIGH_IMPORTANCE_1, this);
            }
            break;
         case "Legit Snap":
            if (attackHandler.canAttack(config, 1) || !attackHandler.getAttackTimer().finished((double)40.0F)) {
               controller.rotateTo(rotation, this.target, 1, rotationConfig, TaskPriority.HIGH_IMPORTANCE_1, this);
            }
            break;
         case "ReallyWorld":
         case "SpookyTime":
         case "SlothAi":
            controller.rotateTo(rotation, this.target, 1, rotationConfig, TaskPriority.HIGH_IMPORTANCE_1, this);
            break;
         case "Advanced":
            controller.rotateTo(rotation, this.target, 1, rotationConfig, TaskPriority.HIGH_IMPORTANCE_1, this);
            break;
         case "minarai":
            controller.rotateTo(rotation, this.target, 1, rotationConfig, TaskPriority.HIGH_IMPORTANCE_1, this);
      }

      if (shouldRotate && !this.aimMode.isSelected("Trigger Bot")) {
         controller.rotateTo(rotation, this.target, 1, rotationConfig, TaskPriority.HIGH_IMPORTANCE_1, this);
      }

      if (elytraMode && !this.aimMode.isSelected("Trigger Bot")) {
         controller.rotateTo(rotation, this.target, 1, rotationConfig, TaskPriority.HIGH_IMPORTANCE_1, this);
      }

   }

   public StrikerConstructor.AttackPerpetratorConfigurable getConfig() {
      float baseRange = this.attackRange.getValue() + 0.253F;
      class_3545<class_243, class_238> pointData = this.pointFinder.computeVector(this.target, baseRange, TurnsConnection.INSTANCE.getRotation(), this.getSmoothMode().randomValue(), this.attackSetting.isSelected("Ignore The Walls"));
      class_243 computedPoint = (class_243)pointData.method_15442();
      class_238 hitbox = (class_238)pointData.method_15441();
      if (this.aimMode.isSelected("Advanced") && this.mouseFovVec.isValue() && this.target != null) {
         class_239 hr = mc.field_1765;
         if (hr instanceof class_3966) {
            class_3966 ehr = (class_3966)hr;
            if (ehr.method_17782() == this.target) {
               computedPoint = ehr.method_17782().method_19538().method_1031((double)0.0F, (double)this.target.method_17682() * (double)0.5F, (double)0.0F);
            }
         }
      }

      if (this.aimMode.isSelected("Advanced") && this.nearestBestHitVec.isValue() && this.target != null) {
         computedPoint = this.pickBestVisiblePoint(this.target, computedPoint, baseRange);
      }

      if (this.aimMode.isSelected("Advanced") && this.heuristics.isValue() && this.target != null) {
         computedPoint = this.applyFakelagBias(this.target, computedPoint);
      }

      if (this.aimMode.isSelected("Advanced") && this.thornsArmorAnalyzed.isValue() && this.target != null && this.hasPartialThorns(this.target)) {
         computedPoint = computedPoint.method_1031((double)0.0F, (double)(-this.target.method_17682()) * (double)0.25F, (double)0.0F);
      }

      if (this.aimMode.isSelected("Advanced") && this.pointSelection.isValue()) {
         long now = System.currentTimeMillis();
         if (now - this.lastPointSwitchMs > (long)Math.max(50.0F, this.pointSelFreq.getValue() * 100.0F)) {
            double vOff = this.pointSelVertical.isValue() ? Math.sin((double)now / (double)300.0F * (double)this.pointSelVSpeed.getValue()) * (double)this.target.method_17682() * (double)0.25F : (double)0.0F;
            double hOff = this.pointSelHorizontal.isValue() ? Math.cos((double)now / (double)280.0F * (double)this.pointSelHSpeed.getValue()) * (double)this.target.method_17681() * (double)0.25F : (double)0.0F;
            computedPoint = computedPoint.method_1031(hOff, vOff, (double)0.0F);
            this.lastPointSwitchMs = now;
         }
      }

      computedPoint = Resolver.getInstance().resolveAura(computedPoint, this.target);
      if (this.target != null) {
         class_243 lagOffset = this.computeLagTrackOffset(this.target);
         if (lagOffset != null) {
            computedPoint = computedPoint.method_1019(lagOffset);
            hitbox = hitbox.method_989(lagOffset.field_1352, lagOffset.field_1351, lagOffset.field_1350);
         }
      }

      if (this.aimMode.isSelected("Advanced") && this.predictionEnabled.isValue() && this.target != null) {
         computedPoint = this.applyPrediction(computedPoint, this.target);
      }

      if (this.aimMode.isSelected("Advanced") && (this.fovOptimization.isValue() || this.obstacleAvoidance.isValue() || this.targetTracking.isValue() || this.predictDodges.isValue())) {
         computedPoint = this.applyHeuristicAdjustments(computedPoint, this.target);
      }

      if (this.aimMode.isSelected("Advanced")) {
         class_3545<class_243, class_238> cm = this.applyCalculationMethod(this.calculationMethod.getSelected(), computedPoint, this.target, hitbox);
         computedPoint = (class_243)cm.method_15442();
         hitbox = (class_238)cm.method_15441();
      }

      if (this.aimMode.isSelected("Advanced")) {
         hitbox = this.expandHitbox(hitbox);
      }

      if (this.aimMode.isSelected("Advanced") && this.calculationMethod.isSelected("Raycast")) {
         if (this.raycastMode.isSelected("Entity")) {
            if (this.isBlockedByEntity(computedPoint, this.target)) {
               this.raycastBlockedThisTick = true;
            }
         } else if (this.raycastMode.isSelected("Ignore")) {
            this.raycastBlockedThisTick = false;
         } else {
            this.raycastBlockedThisTick = false;
         }
      } else {
         this.raycastBlockedThisTick = false;
      }

      if (this.aimMode.isSelected("Advanced") && this.humanizeAngles.isValue() && this.humanError.getValue() > 0.0F) {
         computedPoint = this.applyHumanError(computedPoint, this.humanError.getValue());
      }

      if (mc.field_1724.method_6128() && this.target != null && this.target.method_6128()) {
         class_243 targetVelocity = this.target.method_18798();
         double targetSpeed = targetVelocity.method_37267();
         float leadTicks = 0.0F;
         if (ElytraTarget.shouldElytraTarget) {
            leadTicks = ElytraTarget.getInstance().elytraForward.getValue();
         }

         if (targetSpeed > 0.35) {
            class_243 predictedPos = this.target.method_19538().method_1019(targetVelocity.method_1021((double)leadTicks));
            computedPoint = predictedPos.method_1031((double)0.0F, (double)(this.target.method_17682() / 2.0F), (double)0.0F);
            hitbox = new class_238(predictedPos.field_1352 - (double)(this.target.method_17681() / 2.0F), predictedPos.field_1351, predictedPos.field_1350 - (double)(this.target.method_17681() / 2.0F), predictedPos.field_1352 + (double)(this.target.method_17681() / 2.0F), predictedPos.field_1351 + (double)this.target.method_17682(), predictedPos.field_1350 + (double)(this.target.method_17681() / 2.0F));
         }
      }

      if (this.aimMode.isSelected("minarai")) {
         String modelName = this.mirorAiModel.getSelected();
         MirorAiManager.MirorModel m = MirorAiManager.getInstance().getModel(modelName);
         if (m != null && m.samples != null && !m.samples.isEmpty()) {
            int idx = ThreadLocalRandom.current().nextInt(m.samples.size());
            MirorAiManager.Sample s = (MirorAiManager.Sample)m.samples.get(idx);
            computedPoint = computedPoint.method_1031(s.dx, s.dy, s.dz);
         }
      }

      this.lastAimPoint = computedPoint;
      Turns angle = MathAngle.fromVec3d(computedPoint.method_1020(((class_746)Objects.requireNonNull(mc.field_1724)).method_33571()));
      return new StrikerConstructor.AttackPerpetratorConfigurable(this.target, angle, baseRange, this.attackSetting.getSelected(), this.aimMode, hitbox);
   }

   @Native(
      type = Native.Type.VMProtectBeginMutation
   )
   public TurnsConfig getRotationConfig() {
      boolean visibleCorrection = !this.correctionType.isSelected("Not visible");
      boolean freeCorrection = !this.aimMode.isSelected("Legit") && this.correctionType.isSelected("Free");
      if (TargetStrafe.getInstance().isState() && TargetStrafe.getInstance().mode.isSelected("Grim") && this.target != null) {
         freeCorrection = false;
      }

      return new TurnsConfig(this.getSmoothMode(), visibleCorrection, freeCorrection);
   }

   @EventHandler
   public void onmotion(MotionEvent event) {
   }

   public RotateConstructor getSmoothMode() {
      if (mc.field_1724.method_6128() && this.attackSetting.isSelected("Elytra possibilities") && !this.aimMode.isSelected("Trigger Bot")) {
         return new LinearConstructor();
      } else {
         Object var10000;
         switch (this.aimMode.getSelected()) {
            case "FunTime" -> var10000 = new FTAngle();
            case "HolyWorld" -> var10000 = new HWAngle();
            case "HvH" -> var10000 = new HAngle();
            case "SlothAi" -> var10000 = new LGAngle();
            case "FunSky HVH" -> var10000 = new FunSkyHvHAngle();
            case "SpookyTime" -> var10000 = new SPAngle();
            case "ReallyWorld" -> var10000 = new RWAngle();
            case "Snap" -> var10000 = new SnapAngle();
            case "Legit Snap" -> var10000 = new SnapAngle();
            case "Matrix" -> var10000 = new MatrixAngle();
            case "Advanced" -> var10000 = new AdvancedAngle();
            case "MirorAi" -> var10000 = new AdvancedAngle();
            default -> var10000 = new LinearConstructor();
         }

         return (RotateConstructor)var10000;
      }
   }

   private List collectCandidates(float range, float fovDeg) {
      List<class_1309> list = new ArrayList();
      if (mc.field_1687 != null && mc.field_1724 != null) {
         for(class_1297 e : mc.field_1687.method_18112()) {
            if (e instanceof class_1309) {
               class_1309 le = (class_1309)e;
               if (le != mc.field_1724 && !le.method_31481() && !le.method_7325() && !(mc.field_1724.method_5739(le) > range)) {
                  if (fovDeg < 360.0F) {
                     Turns p2t = MathAngle.fromVec3d(le.method_19538().method_1031((double)0.0F, (double)le.method_17682() * (double)0.5F, (double)0.0F).method_1020(mc.field_1724.method_33571()));
                     float yawDelta = Math.abs(class_3532.method_15393(p2t.getYaw() - mc.field_1724.method_36454()));
                     if (yawDelta > fovDeg / 2.0F) {
                        continue;
                     }
                  }

                  list.add(le);
               }
            }
         }

         return list;
      } else {
         return list;
      }
   }

   private void applyEntityFilter(List list) {
      list.removeIf((le) -> {
         boolean player = le.method_5864().method_5882().contains("player");
         boolean invisible = le.method_5767();
         boolean naked = this.isNaked(le);
         boolean mob = le.method_5864().method_5882().contains("zombie") || le.method_5864().method_5882().contains("skeleton") || le.method_5864().method_5882().contains("creeper") || le.method_5864().method_5882().contains("spider");
         boolean villager = le.method_5864().method_5882().contains("villager");
         boolean animals = le.method_5864().method_5882().contains("cow") || le.method_5864().method_5882().contains("pig") || le.method_5864().method_5882().contains("chicken") || le.method_5864().method_5882().contains("sheep");
         boolean dead = le.method_29504();
         boolean armorStand = le.method_5864().method_5882().contains("armor_stand");
         boolean ok = false;
         if (this.targetEntity.isSelected("player") && player) {
            ok = true;
         }

         if (this.targetEntity.isSelected("invisible") && invisible) {
            ok = true;
         }

         if (this.targetEntity.isSelected("naked") && naked) {
            ok = true;
         }

         if (this.targetEntity.isSelected("mob") && mob) {
            ok = true;
         }

         if (this.targetEntity.isSelected("villager") && villager) {
            ok = true;
         }

         if (this.targetEntity.isSelected("animals") && animals) {
            ok = true;
         }

         if (this.targetEntity.isSelected("dead") && dead) {
            ok = true;
         }

         if (this.targetEntity.isSelected("armorStand") && armorStand) {
            ok = true;
         }

         return !ok;
      });
   }

   private void sortCandidates(List list, String mode) {
      switch (mode) {
         case "health" -> list.sort(Comparator.comparingDouble(class_1309::method_6032));
         case "armorEndurance" -> list.sort(Comparator.comparingDouble(this::totalArmorDurability));
         case "distance" -> list.sort(Comparator.comparingDouble((le) -> (double)mc.field_1724.method_5739(le)));
         case "FOV" -> list.sort(Comparator.comparingDouble(this::fovDeltaToCrosshair));
         case "armorWorse" -> list.sort(Comparator.comparingDouble(this::armorValue));
         case "armorBetter" -> list.sort(Comparator.comparingDouble(this::armorValue).reversed());
      }

   }

   private class_1309 pickByTargetMode(List list) {
      if (list.isEmpty()) {
         return null;
      } else if (this.aimMode.isSelected("Advanced") && this.targetSortMode.isSelected("single")) {
         long now = System.currentTimeMillis();
         if (this.target != null && !this.target.method_31481()) {
            boolean distOk = mc.field_1724.method_5739(this.target) <= this.targetLockDistance.getValue();
            boolean timeOk = this.lastTarget == this.target && now - this.shiftTapEndTime <= (long)(this.targetLockTime.getValue() * 1000.0F);
            if (distOk && timeOk) {
               return this.target;
            }
         }

         if (this.smartLock.isValue() && this.lastTarget != null && !this.lastTarget.method_31481()) {
            boolean close = mc.field_1724.method_5739(this.lastTarget) <= this.smartLockDistance.getValue();
            boolean hpOk = !this.onlyLowHealth.isValue() || this.target != null && this.lastTarget.method_6032() < this.target.method_6032();
            if (close && hpOk) {
               return this.lastTarget;
            }
         }

         this.shiftTapEndTime = System.currentTimeMillis();
         return (class_1309)list.get(0);
      } else {
         return (class_1309)list.get(0);
      }
   }

   private boolean isNaked(class_1309 le) {
      for(class_1304 slot : new class_1304[]{class_1304.field_6169, class_1304.field_6174, class_1304.field_6172, class_1304.field_6166}) {
         class_1799 st = le.method_6118(slot);
         if (!st.method_7960()) {
            return false;
         }
      }

      return true;
   }

   private double totalArmorDurability(class_1309 le) {
      double sum = (double)0.0F;

      for(class_1304 slot : new class_1304[]{class_1304.field_6169, class_1304.field_6174, class_1304.field_6172, class_1304.field_6166}) {
         class_1799 st = le.method_6118(slot);
         if (!st.method_7960()) {
            sum += (double)(st.method_7936() - st.method_7919());
         }
      }

      return sum;
   }

   private double armorValue(class_1309 le) {
      double sum = (double)0.0F;

      for(class_1304 slot : new class_1304[]{class_1304.field_6169, class_1304.field_6174, class_1304.field_6172, class_1304.field_6166}) {
         class_1799 st = le.method_6118(slot);
         sum += st.method_7909().method_7876().contains("netherite") ? (double)4.0F : (st.method_7909().method_7876().contains("diamond") ? (double)3.0F : (st.method_7909().method_7876().contains("iron") ? (double)2.0F : (st.method_7909().method_7876().contains("leather") ? (double)1.0F : (double)0.0F)));
      }

      return sum;
   }

   private double fovDeltaToCrosshair(class_1309 le) {
      Turns p2t = MathAngle.fromVec3d(le.method_19538().method_1031((double)0.0F, (double)le.method_17682() * (double)0.5F, (double)0.0F).method_1020(mc.field_1724.method_33571()));
      return (double)Math.abs(class_3532.method_15393(p2t.getYaw() - mc.field_1724.method_36454()));
   }

   private boolean hasPartialThorns(class_1309 le) {
      boolean any = false;
      boolean all = true;

      for(class_1304 slot : new class_1304[]{class_1304.field_6169, class_1304.field_6174, class_1304.field_6172, class_1304.field_6166}) {
         class_1799 st = le.method_6118(slot);
         boolean th = false;
         if (!st.method_7960() && st.method_7942()) {
            String enchStr = st.method_58657().toString().toLowerCase();
            th = enchStr.contains("thorns");
         }

         any |= th;
         all &= th;
      }

      return any && !all;
   }

   private boolean hasFullThorns(class_1309 le) {
      for(class_1304 slot : new class_1304[]{class_1304.field_6169, class_1304.field_6174, class_1304.field_6172, class_1304.field_6166}) {
         class_1799 st = le.method_6118(slot);
         if (st.method_7960() || !st.method_7942()) {
            return false;
         }

         String enchStr = st.method_58657().toString().toLowerCase();
         if (!enchStr.contains("thorns")) {
            return false;
         }
      }

      return true;
   }

   private class_243 applyFakelagBias(class_1309 target, class_243 originalPoint) {
      List<class_243> trace = (List)this.fakelagTrace.computeIfAbsent(target.method_5667(), (k) -> new ArrayList());
      long now = System.currentTimeMillis();
      Long lastTel = (Long)this.lastTelTicks.get(target.method_5667());
      if (lastTel != null && now - lastTel < 300L && !trace.isEmpty()) {
         class_243 lastPos = (class_243)trace.get(trace.size() - 1);
         return lastPos.method_1031((double)0.0F, (double)target.method_17682() * (double)0.5F, (double)0.0F);
      } else {
         return originalPoint;
      }
   }

   private class_243 applyPrediction(class_243 point, class_1309 target) {
      class_243 vel = target.method_18798();
      double t = (double)this.predictionTimeSec.getValue();
      class_243 predicted = target.method_19538().method_1019(vel.method_1021(t));
      if (this.velocityPrediction.isValue()) {
         point = predicted.method_1031((double)0.0F, (double)target.method_17682() * (double)0.5F, (double)0.0F);
      }

      return point;
   }

   private class_243 applyHeuristicAdjustments(class_243 point, class_1309 target) {
      return point;
   }

   private class_3545 applyCalculationMethod(String mode, class_243 point, class_1309 target, class_238 baseBox) {
      return new class_3545(point, baseBox);
   }

   private class_238 expandHitbox(class_238 box) {
      float exp = this.hitboxExpansion.getValue();
      return exp <= 0.0F ? box : box.method_1014((double)exp);
   }

   private boolean isBlockedByEntity(class_243 point, class_1309 target) {
      return false;
   }

   private class_243 applyHumanError(class_243 point, float amount) {
      double dx = (ThreadLocalRandom.current().nextDouble() - (double)0.5F) * (double)amount * 0.1;
      double dy = (ThreadLocalRandom.current().nextDouble() - (double)0.5F) * (double)amount * 0.1;
      double dz = (ThreadLocalRandom.current().nextDouble() - (double)0.5F) * (double)amount * 0.1;
      return point.method_1031(dx, dy, dz);
   }

   private boolean tryShieldBreak(class_1657 targetPlayer, StrikerConstructor.AttackPerpetratorConfigurable config) {
      return false;
   }

   private Turns limitAngleChangeAdvanced(Turns current, Turns target) {
      return target;
   }

   private class_243 pickBestVisiblePoint(class_1309 entity, class_243 fallback, float range) {
      return fallback;
   }

   private boolean isMace(class_1799 stack) {
      if (stack != null && !stack.method_7960()) {
         String key = stack.method_7909().method_7876().toLowerCase();
         return key.contains("mace");
      } else {
         return false;
      }
   }

   private boolean isThrowableInHand(class_1799 stack) {
      if (stack != null && !stack.method_7960()) {
         String key = stack.method_7909().method_7876().toLowerCase();
         return key.contains("snowball") || key.contains("egg") || key.contains("pearl") || key.contains("ender_pearl") || key.contains("trident") || key.contains("bow") || key.contains("crossbow") || key.contains("arrow");
      } else {
         return false;
      }
   }

   private class_243 computeLagTrackOffset(class_1309 target) {
      LagTrack lag = (LagTrack)Instance.get(LagTrack.class);
      if (lag != null && lag.isState()) {
         try {
            Field methodField = LagTrack.class.getDeclaredField("method");
            methodField.setAccessible(true);
            SelectSetting method = (SelectSetting)methodField.get(lag);
            if (method != null && method.isSelected("Predict")) {
               Field distanceField = LagTrack.class.getDeclaredField("distance");
               distanceField.setAccessible(true);
               SliderSettings distance = (SliderSettings)distanceField.get(lag);
               Field onlyForwardField = LagTrack.class.getDeclaredField("onlyForward");
               onlyForwardField.setAccessible(true);
               BooleanSetting onlyForward = (BooleanSetting)onlyForwardField.get(lag);
               Field offsetSideField = LagTrack.class.getDeclaredField("offsetSide");
               offsetSideField.setAccessible(true);
               SelectSetting offsetSide = (SelectSetting)offsetSideField.get(lag);
               double dist = distance != null ? (double)distance.getValue() : (double)0.0F;
               if (dist <= (double)0.0F) {
                  return null;
               } else if (mc.field_1724 == null) {
                  return null;
               } else if (this.isThrowableInHand(mc.field_1724.method_6047())) {
                  return null;
               } else {
                  float yaw = target.method_36454();
                  float yawRad = yaw * ((float)Math.PI / 180F);
                  class_243 look = new class_243((double)(-class_3532.method_15374(yawRad)), (double)0.0F, (double)class_3532.method_15362(yawRad));
                  class_243 targetPos = target.method_19538();
                  class_243 playerPos = mc.field_1724.method_19538();
                  class_243 toPlayer = playerPos.method_1020(targetPos).method_1029();
                  double dot = look.method_1029().method_1026(toPlayer);
                  boolean backToMe = dot < (double)0.0F;
                  if (onlyForward != null && onlyForward.isValue() && !backToMe) {
                     return null;
                  } else {
                     class_243 dir;
                     if (offsetSide != null && offsetSide.isSelected("Ð¡Ð¿Ð¸Ð½Ð°")) {
                        dir = look.method_22882();
                     } else if (offsetSide != null && offsetSide.isSelected("Ð’Ð·Ð³Ð»ÑÐ´")) {
                        dir = look;
                     } else if (backToMe) {
                        dir = look.method_22882();
                     } else {
                        dir = look;
                     }

                     return dir.method_1027() == (double)0.0F ? null : dir.method_1029().method_1021(dist);
                  }
               }
            } else {
               return null;
            }
         } catch (Throwable var23) {
            return null;
         }
      } else {
         return null;
      }
   }

   public void setTargetSelector(TargetFinder targetSelector) {
      this.targetSelector = targetSelector;
   }

   public void setPointFinder(MultiPoint pointFinder) {
      this.pointFinder = pointFinder;
   }

   public void setTarget(class_1309 target) {
      this.target = target;
   }

   public void setLastTarget(class_1309 lastTarget) {
      this.lastTarget = lastTarget;
   }

   public void setShiftTapEndTime(long shiftTapEndTime) {
      this.shiftTapEndTime = shiftTapEndTime;
   }

   public void setFovCircleRenderer(FovCircleRenderer fovCircleRenderer) {
      this.fovCircleRenderer = fovCircleRenderer;
   }

   public void setAimMode(SelectSetting aimMode) {
      this.aimMode = aimMode;
   }

   public void setTargetType(MultiSelectSetting targetType) {
      this.targetType = targetType;
   }

   public void setAttackRange(SliderSettings attackRange) {
      this.attackRange = attackRange;
   }

   public void setLookRange(SliderSettings lookRange) {
      this.lookRange = lookRange;
   }

   public void setAttackSetting(MultiSelectSetting attackSetting) {
      this.attackSetting = attackSetting;
   }

   public void setHitChance(SliderSettings hitChance) {
      this.hitChance = hitChance;
   }

   public void setHitInfoMode(MultiSelectSetting hitInfoMode) {
      this.hitInfoMode = hitInfoMode;
   }

   public void setShieldBreaker(BooleanSetting shieldBreaker) {
      this.shieldBreaker = shieldBreaker;
   }

   public void setShieldSprintBreaker(BooleanSetting shieldSprintBreaker) {
      this.shieldSprintBreaker = shieldSprintBreaker;
   }

   public void setClickMode(SelectSetting clickMode) {
      this.clickMode = clickMode;
   }

   public void setClickCps(SliderSettings clickCps) {
      this.clickCps = clickCps;
   }

   public void setCorrectionType(SelectSetting correctionType) {
      this.correctionType = correctionType;
   }

   public void setSprintReset(SelectSetting sprintReset) {
      this.sprintReset = sprintReset;
   }

   public void setSmartCrits(BooleanSetting smartCrits) {
      this.smartCrits = smartCrits;
   }

   public void setFlySync(BooleanSetting flySync) {
      this.flySync = flySync;
   }

   public void setHighPingLagRangeSync(BooleanSetting highPingLagRangeSync) {
      this.highPingLagRangeSync = highPingLagRangeSync;
   }

   public void setLagRangeAttackDelayMs(SliderSettings lagRangeAttackDelayMs) {
      this.lagRangeAttackDelayMs = lagRangeAttackDelayMs;
   }

   public void setLastAimPoint(class_243 lastAimPoint) {
      this.lastAimPoint = lastAimPoint;
   }

   public void setMaceNoCooldownOnce(boolean maceNoCooldownOnce) {
      this.maceNoCooldownOnce = maceNoCooldownOnce;
   }

   public void setLastMainHand(class_1799 lastMainHand) {
      this.lastMainHand = lastMainHand;
   }

   public void setBox(class_238 box) {
      this.box = box;
   }

   public void setPreviousAxeSlot(int previousAxeSlot) {
      this.previousAxeSlot = previousAxeSlot;
   }

   public void setBreakingShield(boolean breakingShield) {
      this.breakingShield = breakingShield;
   }

   public void setRaycastBlockedThisTick(boolean raycastBlockedThisTick) {
      this.raycastBlockedThisTick = raycastBlockedThisTick;
   }

   public void setLastPointSwitchMs(long lastPointSwitchMs) {
      this.lastPointSwitchMs = lastPointSwitchMs;
   }

   public void setElytraStateForward(boolean elytraStateForward) {
      this.elytraStateForward = elytraStateForward;
   }

   public void setWasForwardPressed(boolean wasForwardPressed) {
      this.wasForwardPressed = wasForwardPressed;
   }

   public TargetFinder getTargetSelector() {
      return this.targetSelector;
   }

   public MultiPoint getPointFinder() {
      return this.pointFinder;
   }

   public class_1309 getTarget() {
      return this.target;
   }

   public class_1309 getLastTarget() {
      return this.lastTarget;
   }

   public long getShiftTapEndTime() {
      return this.shiftTapEndTime;
   }

   public FovCircleRenderer getFovCircleRenderer() {
      return this.fovCircleRenderer;
   }

   public SelectSetting getAimMode() {
      return this.aimMode;
   }

   public MultiSelectSetting getTargetType() {
      return this.targetType;
   }

   public SelectSetting getMirorAiModel() {
      return this.mirorAiModel;
   }

   public SliderSettings getAttackRange() {
      return this.attackRange;
   }

   public SliderSettings getLookRange() {
      return this.lookRange;
   }

   public MultiSelectSetting getAttackSetting() {
      return this.attackSetting;
   }

   public SliderSettings getHitChance() {
      return this.hitChance;
   }

   public MultiSelectSetting getHitInfoMode() {
      return this.hitInfoMode;
   }

   public BooleanSetting getShieldBreaker() {
      return this.shieldBreaker;
   }

   public BooleanSetting getShieldSprintBreaker() {
      return this.shieldSprintBreaker;
   }

   public SelectSetting getClickMode() {
      return this.clickMode;
   }

   public SliderSettings getClickCps() {
      return this.clickCps;
   }

   public SelectSetting getCorrectionType() {
      return this.correctionType;
   }

   public SelectSetting getSprintReset() {
      return this.sprintReset;
   }

   public BooleanSetting getSmartCrits() {
      return this.smartCrits;
   }

   public BooleanSetting getFlySync() {
      return this.flySync;
   }

   public BooleanSetting getHighPingLagRangeSync() {
      return this.highPingLagRangeSync;
   }

   public SliderSettings getLagRangeAttackDelayMs() {
      return this.lagRangeAttackDelayMs;
   }

   public SelectSetting getAdvancedProfile() {
      return this.advancedProfile;
   }

   public SelectSetting getAdvancedRotationType() {
      return this.advancedRotationType;
   }

   public SliderSettings getAdvancedFov() {
      return this.advancedFov;
   }

   public BooleanSetting getDrawFov() {
      return this.drawFov;
   }

   public SliderSettings getHorizontalSpeed() {
      return this.horizontalSpeed;
   }

   public SliderSettings getVerticalSpeed() {
      return this.verticalSpeed;
   }

   public SliderSettings getHorizontalAcceleration() {
      return this.horizontalAcceleration;
   }

   public SliderSettings getVerticalAcceleration() {
      return this.verticalAcceleration;
   }

   public SliderSettings getGaussianFrequency() {
      return this.gaussianFrequency;
   }

   public SliderSettings getGaussianSpeed() {
      return this.gaussianSpeed;
   }

   public SelectSetting getGaussianBodyPart() {
      return this.gaussianBodyPart;
   }

   public SliderSettings getHorizontalRandomize() {
      return this.horizontalRandomize;
   }

   public SliderSettings getVerticalRandomize() {
      return this.verticalRandomize;
   }

   public SliderSettings getYawRandom() {
      return this.yawRandom;
   }

   public SliderSettings getPitchRandom() {
      return this.pitchRandom;
   }

   public SliderSettings getRandomJitterFrequency() {
      return this.randomJitterFrequency;
   }

   public BooleanSetting getClampSteps() {
      return this.clampSteps;
   }

   public SliderSettings getMaxYawStep() {
      return this.maxYawStep;
   }

   public SliderSettings getMaxPitchStep() {
      return this.maxPitchStep;
   }

   public BooleanSetting getHeuristics() {
      return this.heuristics;
   }

   public SliderSettings getFakelagAnalyzerDistance() {
      return this.fakelagAnalyzerDistance;
   }

   public BooleanSetting getNearestBestHitVec() {
      return this.nearestBestHitVec;
   }

   public BooleanSetting getMouseFovVec() {
      return this.mouseFovVec;
   }

   public BooleanSetting getPointSelection() {
      return this.pointSelection;
   }

   public BooleanSetting getPointSelVertical() {
      return this.pointSelVertical;
   }

   public BooleanSetting getPointSelHorizontal() {
      return this.pointSelHorizontal;
   }

   public SliderSettings getPointSelVSpeed() {
      return this.pointSelVSpeed;
   }

   public SliderSettings getPointSelHSpeed() {
      return this.pointSelHSpeed;
   }

   public SliderSettings getPointSelFreq() {
      return this.pointSelFreq;
   }

   public BooleanSetting getThornsArmorAnalyzed() {
      return this.thornsArmorAnalyzed;
   }

   public BooleanSetting getStopRotationInsideTarget() {
      return this.stopRotationInsideTarget;
   }

   public BooleanSetting getLockView() {
      return this.lockView;
   }

   public BooleanSetting getSnapOnAttack() {
      return this.snapOnAttack;
   }

   public SelectSetting getTargetSort() {
      return this.targetSort;
   }

   public MultiSelectSetting getTargetEntity() {
      return this.targetEntity;
   }

   public SelectSetting getTargetSortMode() {
      return this.targetSortMode;
   }

   public SliderSettings getTargetLockDistance() {
      return this.targetLockDistance;
   }

   public SliderSettings getTargetLockTime() {
      return this.targetLockTime;
   }

   public BooleanSetting getSmartLock() {
      return this.smartLock;
   }

   public SliderSettings getSmartLockDistance() {
      return this.smartLockDistance;
   }

   public BooleanSetting getOnlyLowHealth() {
      return this.onlyLowHealth;
   }

   public BooleanSetting getAccelerationEnabled() {
      return this.accelerationEnabled;
   }

   public SelectSetting getAccelerationMode() {
      return this.accelerationMode;
   }

   public BooleanSetting getDecelerationEnabled() {
      return this.decelerationEnabled;
   }

   public SelectSetting getDecelerationMode() {
      return this.decelerationMode;
   }

   public SliderSettings getSmoothnessFactor() {
      return this.smoothnessFactor;
   }

   public SliderSettings getResponseTimeMs() {
      return this.responseTimeMs;
   }

   public SliderSettings getMaxAngleChangePerTick() {
      return this.maxAngleChangePerTick;
   }

   public BooleanSetting getClampAngles() {
      return this.clampAngles;
   }

   public BooleanSetting getHumanizeAngles() {
      return this.humanizeAngles;
   }

   public BooleanSetting getPredictionEnabled() {
      return this.predictionEnabled;
   }

   public SelectSetting getPredictionType() {
      return this.predictionType;
   }

   public SliderSettings getPredictionTimeSec() {
      return this.predictionTimeSec;
   }

   public BooleanSetting getVelocityPrediction() {
      return this.velocityPrediction;
   }

   public BooleanSetting getGravityPrediction() {
      return this.gravityPrediction;
   }

   public BooleanSetting getBouncePrediction() {
      return this.bouncePrediction;
   }

   public BooleanSetting getTerrainAwareness() {
      return this.terrainAwareness;
   }

   public SliderSettings getPredictionSmoothing() {
      return this.predictionSmoothing;
   }

   public SliderSettings getPredictionUpdateRate() {
      return this.predictionUpdateRate;
   }

   public SliderSettings getHistoricalDataSize() {
      return this.historicalDataSize;
   }

   public SliderSettings getConfidenceThreshold() {
      return this.confidenceThreshold;
   }

   public SelectSetting getFallbackMode() {
      return this.fallbackMode;
   }

   public BooleanSetting getLearningMode() {
      return this.learningMode;
   }

   public BooleanSetting getPatternRecognition() {
      return this.patternRecognition;
   }

   public BooleanSetting getCounterMeasures() {
      return this.counterMeasures;
   }

   public BooleanSetting getFovOptimization() {
      return this.fovOptimization;
   }

   public BooleanSetting getObstacleAvoidance() {
      return this.obstacleAvoidance;
   }

   public BooleanSetting getTargetTracking() {
      return this.targetTracking;
   }

   public BooleanSetting getPredictDodges() {
      return this.predictDodges;
   }

   public SelectSetting getCalculationMethod() {
      return this.calculationMethod;
   }

   public SelectSetting getRaycastMode() {
      return this.raycastMode;
   }

   public SliderSettings getHitboxExpansion() {
      return this.hitboxExpansion;
   }

   public SelectSetting getHitboxMode() {
      return this.hitboxMode;
   }

   public SliderSettings getHumanError() {
      return this.humanError;
   }

   public BooleanSetting getMouseAcceleration() {
      return this.mouseAcceleration;
   }

   public SliderSettings getNaturalMissRate() {
      return this.naturalMissRate;
   }

   public SliderSettings getFatigueSimulation() {
      return this.fatigueSimulation;
   }

   public SliderSettings getReactionTime() {
      return this.reactionTime;
   }

   public SelectSetting getLiteInterpolationMode() {
      return this.liteInterpolationMode;
   }

   public SelectSetting getLiteNoiseMode() {
      return this.liteNoiseMode;
   }

   public SliderSettings getLiteSmoothnessH() {
      return this.liteSmoothnessH;
   }

   public SliderSettings getLiteSmoothnessV() {
      return this.liteSmoothnessV;
   }

   public SliderSettings getLiteInitialAimSpeed() {
      return this.liteInitialAimSpeed;
   }

   public SliderSettings getLiteTargetTrackSpeed() {
      return this.liteTargetTrackSpeed;
   }

   public SliderSettings getLiteShakeIntensityYaw() {
      return this.liteShakeIntensityYaw;
   }

   public SliderSettings getLiteShakeIntensityPitch() {
      return this.liteShakeIntensityPitch;
   }

   public SliderSettings getLiteShakeSpeedYaw() {
      return this.liteShakeSpeedYaw;
   }

   public SliderSettings getLiteShakeSpeedPitch() {
      return this.liteShakeSpeedPitch;
   }

   public BooleanSetting getLiteTakePlayerPitch() {
      return this.liteTakePlayerPitch;
   }

   public BooleanSetting getLiteMultiPoints() {
      return this.liteMultiPoints;
   }

   public class_243 getLastAimPoint() {
      return this.lastAimPoint;
   }

   public boolean isMaceNoCooldownOnce() {
      return this.maceNoCooldownOnce;
   }

   public class_1799 getLastMainHand() {
      return this.lastMainHand;
   }

   public List getPackets() {
      return this.packets;
   }

   public class_238 getBox() {
      return this.box;
   }

   public StopWatch getCpsTimer() {
      return this.cpsTimer;
   }

   public StopWatch getLagRangeAttackTimer() {
      return this.lagRangeAttackTimer;
   }

   public StopWatch getShieldBreakTimer() {
      return this.shieldBreakTimer;
   }

   public int getPreviousAxeSlot() {
      return this.previousAxeSlot;
   }

   public boolean isBreakingShield() {
      return this.breakingShield;
   }

   public boolean isRaycastBlockedThisTick() {
      return this.raycastBlockedThisTick;
   }

   public Map getFakelagTrace() {
      return this.fakelagTrace;
   }

   public Map getLastTelTicks() {
      return this.lastTelTicks;
   }

   public long getLastPointSwitchMs() {
      return this.lastPointSwitchMs;
   }

   public boolean isElytraStateForward() {
      return this.elytraStateForward;
   }

   public boolean isWasForwardPressed() {
      return this.wasForwardPressed;
   }

   public static float getLegitSprintNeed() {
      return legitSprintNeed;
   }

   public class FovCircleRenderer implements QuickImports {
      private float currentScale = 1.0F;
      private float targetScale = 1.0F;
      private float cachedDynamicFov = 33.0F;
      private float lastFinalRadius = 0.0F;

      @EventHandler
      public void drawEvent(DrawEvent e) {
         if (mc.field_1724 != null && Aura.this.isState()) {
            boolean showLegit = Aura.this.aimMode.isSelected("Legit Snap");
            boolean showAdvanced = Aura.this.aimMode.isSelected("Advanced") && Aura.this.drawFov.isValue();
            if (showLegit || showAdvanced) {
               if (mc.field_1690.method_31044().method_31034()) {
                  class_4587 matrix = e.getDrawContext().method_51448();
                  float middleW = (float)mc.method_22683().method_4486() / 2.0F;
                  float middleH = (float)mc.method_22683().method_4502() / 2.0F;
                  double baseFov = (double)(Integer)mc.field_1690.method_41808().method_41753();
                  baseFov = class_3532.method_15350(baseFov, (double)30.0F, (double)110.0F);
                  this.targetScale = mc.field_1724.method_5624() ? 0.9F : 1.0F;
                  this.currentScale = Calculate.interpolateSmooth((double)2.5F, this.currentScale, this.targetScale);
                  float finalRadius;
                  if (showLegit) {
                     float baseRadius = (float)class_3532.method_16436((baseFov - (double)30.0F) / (double)80.0F, (double)106.5F, (double)65.0F);
                     float fovScale = (float)((double)450.0F / baseFov);
                     float dynamicRadius = baseRadius * fovScale;
                     finalRadius = dynamicRadius * this.currentScale;
                  } else {
                     float desiredFov = class_3532.method_15363(Aura.this.advancedFov.getValue(), 0.0F, 360.0F);
                     float screenWidth = (float)mc.method_22683().method_4486();
                     double baseFovRad = Math.toRadians(baseFov);
                     double pixelsPerRadian = (double)screenWidth / baseFovRad;
                     double circleFovRad = Math.toRadians((double)desiredFov) / (double)2.0F;
                     double circleRadiusInPixels = circleFovRad * pixelsPerRadian;
                     finalRadius = (float)((double)2.0F * circleRadiusInPixels) * this.currentScale;
                  }

                  this.lastFinalRadius = finalRadius;
                  float baseThickness = (float)class_3532.method_16436((baseFov - (double)30.0F) / (double)80.0F, 0.003, 0.015);
                  arc.render(ShapeProperties.create(matrix, (double)(middleW - finalRadius / 2.0F), (double)(middleH - finalRadius / 2.0F), (double)finalRadius, (double)finalRadius).round(0.3F).thickness(baseThickness).end(360.0F).color(ColorAssist.getColor(255, 255, 255, 255)).build());
                  if (showLegit) {
                     this.cachedDynamicFov = this.calculateDynamicFov();
                  } else {
                     this.cachedDynamicFov = class_3532.method_15363(Aura.this.advancedFov.getValue(), 0.0F, 360.0F);
                  }
               }

            }
         }
      }

      private float calculateDynamicFov() {
         if (mc.field_1724 != null && mc.method_22683() != null) {
            double fov = (double)(Integer)mc.field_1690.method_41808().method_41753();
            fov = class_3532.method_15350(fov, (double)30.0F, (double)110.0F);
            float screenWidth = (float)mc.method_22683().method_4486();
            float circleRadiusInPixels = this.lastFinalRadius / 2.0F;
            double horizontalFovRadians = Math.toRadians(fov);
            double pixelsPerRadian = (double)screenWidth / horizontalFovRadians;
            double circleFovRadians = (double)circleRadiusInPixels / pixelsPerRadian;
            float circleFovDegrees = (float)Math.toDegrees(circleFovRadians);
            circleFovDegrees *= 2.0F;
            return class_3532.method_15363(circleFovDegrees, 36.0F, 360.0F);
         } else {
            return 33.0F;
         }
      }

      public float getCachedDynamicFov() {
         return this.cachedDynamicFov;
      }
   }
}

