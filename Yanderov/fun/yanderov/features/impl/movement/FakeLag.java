package fun.Yanderov.features.impl.movement;

import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.events.render.WorldRenderEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.ColorSetting;
import fun.Yanderov.features.module.setting.implement.MultiSelectSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.geometry.Render3D;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import java.util.ArrayDeque;
import java.util.Deque;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1839;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2663;
import net.minecraft.class_2678;
import net.minecraft.class_2724;
import net.minecraft.class_2743;
import net.minecraft.class_2799;
import net.minecraft.class_2824;
import net.minecraft.class_3532;
import net.minecraft.class_408;
import net.minecraft.class_465;
import net.minecraft.class_2799.class_2800;

public class FakeLag extends Module {
   private final SliderSettings delayMs = (new SliderSettings("Delay", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿Ð°ÐºÐµÑ‚Ð¾Ð² (ms)")).setValue(400.0F).range(0, 2000);
   private final BooleanSetting fastMode = (new BooleanSetting("FastMode", "Fast-Ñ€ÐµÐ¶Ð¸Ð¼ (Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·ÑƒÐµÑ‚ Delay, Ð¸Ð³Ð½Ð¾Ñ€Ð¸Ñ€ÑƒÑ Ð¿Ð¸Ð½Ð³-ÑÐ»Ð°Ð¹Ð´ÐµÑ€Ñ‹)")).setValue(false);
   private final BooleanSetting fastFlushAll = (new BooleanSetting("FastFlushAll", "Ð’ Fast-Ñ€ÐµÐ¶Ð¸Ð¼Ðµ ÑÐ»Ð°Ñ‚ÑŒ Ð²ÑÑŽ Ð¾Ñ‡ÐµÑ€ÐµÐ´ÑŒ Ð·Ð° Ñ‚Ð¸Ðº")).setValue(true).visible(() -> this.fastMode.isValue());
   private final SliderSettings nextLagDelayMs = (new SliderSettings("NextLagDelay", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° ÑÑ‚Ð°Ñ€Ñ‚Ð° Ð¿Ð¾ÑÐ»Ðµ Ð±Ð»Ð¸Ð½ÐºÐ° (ms)")).setValue(0.0F).range(0, 3000);
   private final SliderSettings maxPacketsPerTick = (new SliderSettings("MaxPackets/Tick", "ÐœÐ°ÐºÑÐ¸Ð¼ÑƒÐ¼ Ð¿Ð°ÐºÐµÑ‚Ð¾Ð² Ð·Ð° Ñ‚Ð¸Ðº (Normal)")).setValue(1.0F).range(1, 100);
   private final BooleanSetting flushOnDisable = (new BooleanSetting("FlushOnDisable", "Ð¡Ð»Ð¸Ð²Ð°Ñ‚ÑŒ Ð¾Ñ‡ÐµÑ€ÐµÐ´ÑŒ Ð¿Ñ€Ð¸ Ð²Ñ‹ÐºÐ»ÑŽÑ‡ÐµÐ½Ð¸Ð¸ Ð¼Ð¾Ð´ÑƒÐ»Ñ")).setValue(true);
   private final BooleanSetting autoBlinkOnStop = (new BooleanSetting("Auto Blink On Stop", "Ð¡Ð»Ð¸Ñ‚ÑŒ Ð¾Ñ‡ÐµÑ€ÐµÐ´ÑŒ Ð¿Ñ€Ð¸ Ð¾ÑÑ‚Ð°Ð½Ð¾Ð²ÐºÐµ")).setValue(true);
   private final BooleanSetting pulseMode = (new BooleanSetting("Pulse Mode", "Ð ÐµÐ¶Ð¸Ð¼ Ð¿ÑƒÐ»ÑŒÑ-Ð»Ð°Ð³Ð¸ (ÐºÐ°Ðº PulseBlink)")).setValue(false);
   private final SliderSettings pulseDurationMs = (new SliderSettings("Pulse Duration", "Ð”Ð»Ð¸Ñ‚ÐµÐ»ÑŒÐ½Ð¾ÑÑ‚ÑŒ Ð¿ÑƒÐ»ÑŒÑÐ° (ms)")).setValue(500.0F).range(50, 3000);
   private final SliderSettings pulseCooldownMs = (new SliderSettings("Pulse Cooldown", "ÐšÑƒÐ»Ð´Ð°ÑƒÐ½ Ð¼ÐµÐ¶Ð´Ñƒ Ð¿ÑƒÐ»ÑŒÑÐ°Ð¼Ð¸ (ms)")).setValue(1500.0F).range(0, 5000);
   private final SliderSettings maxPulsePackets = (new SliderSettings("Pulse MaxPackets", "Ð›Ð¸Ð¼Ð¸Ñ‚ Ð¿Ð°ÐºÐµÑ‚Ð¾Ð² Ð·Ð° Ð¿ÑƒÐ»ÑŒÑ")).setValue(80.0F).range(10, 300);
   private final BooleanSetting playerActivateRange = (new BooleanSetting("PlayerActivateRange", "Ð Ð°Ð±Ð¾Ñ‚Ð°Ñ‚ÑŒ Ñ‚Ð¾Ð»ÑŒÐºÐ¾ Ñ€ÑÐ´Ð¾Ð¼ Ñ Ð¸Ð³Ñ€Ð¾ÐºÐ°Ð¼Ð¸")).setValue(false);
   private final SliderSettings playerActivateRadius = (new SliderSettings("PlayerRange", "Ð Ð°Ð´Ð¸ÑƒÑ Ð¿Ð¾Ð¸ÑÐºÐ° Ð¸Ð³Ñ€Ð¾ÐºÐ¾Ð²")).setValue(20.0F).range(0.0F, 30.0F).visible(() -> this.playerActivateRange.isValue());
   private final SliderSettings blinkPlayerRadius = (new SliderSettings("BlinkPlayerRadius", "Ð‘Ð»Ð¸Ð½Ðº Ð¿Ñ€Ð¸ Ð¿Ñ€Ð¸Ð±Ð»Ð¸Ð¶ÐµÐ½Ð¸Ð¸ Ðº Ð¸Ð³Ñ€Ð¾ÐºÑƒ")).setValue(0.0F).range(0.0F, 10.0F);
   private final MultiSelectSetting blinkActions = (new MultiSelectSetting("BlinkActions", "Ð£ÑÐ»Ð¾Ð²Ð¸Ñ Ð±Ð»Ð¸Ð½ÐºÐ°")).value("Vehicle", "Eat", "PvpAction", "Velocity", "Elytra", "Inventory", "Chat", "Sneak", "Water", "StopMotion", "Potion", "AnyAction");
   private final SelectSetting renderEsp = (new SelectSetting("RenderEsp", "ÐžÑ‚Ð¾Ð±Ñ€Ð°Ð¶ÐµÐ½Ð¸Ðµ ESP")).value("Off", "Soul", "Chams", "Box").selected("Soul");
   private final SelectSetting espSmoothMode = (new SelectSetting("EspSmooth", "Ð¡Ð³Ð»Ð°Ð¶Ð¸Ð²Ð°Ð½Ð¸Ðµ/blur ESP")).value("Off", "Low", "Medium", "High").selected("Medium").visible(() -> !this.renderEsp.isSelected("Off"));
   private final BooleanSetting hideEspInFirstPerson = (new BooleanSetting("HideEspInFirstPerson", "Ð¡ÐºÑ€Ñ‹Ð²Ð°Ñ‚ÑŒ ESP Ð² 1 Ð»Ð¸Ñ†Ðµ")).setValue(false).visible(() -> !this.renderEsp.isSelected("Off"));
   private final ColorSetting chamsColor = (new ColorSetting("ChamsColor", "Ð¦Ð²ÐµÑ‚ ESP")).value(-2130706433).visible(() -> !this.renderEsp.isSelected("Off"));
   private final BooleanSetting visualSpinMethod = (new BooleanSetting("VisualSpinMethod", "ÐšÑ€ÑƒÑ‚Ð¸Ñ‚ÑŒ Ð²Ð¸Ð·ÑƒÐ°Ð»ÑŒÐ½ÑƒÑŽ Ð¼Ð¾Ð´ÐµÐ»ÑŒÐºÑƒ (Soul/Chams)")).setValue(false).visible(() -> this.renderEsp.isSelected("Soul") || this.renderEsp.isSelected("Chams"));
   private final SliderSettings spinSpeed = (new SliderSettings("SpinSpeed", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð²Ñ€Ð°Ñ‰ÐµÐ½Ð¸Ñ ESP")).setValue(1.0F).range(0.0F, 10.0F).visible(() -> this.visualSpinMethod.isValue() && (this.renderEsp.isSelected("Soul") || this.renderEsp.isSelected("Chams")));
   private final BooleanSetting lowSpeedStart = (new BooleanSetting("LowSpeedStart", "ÐŸÐ»Ð°Ð²Ð½Ð¾Ðµ ÑÐ¼ÐµÑ‰ÐµÐ½Ð¸Ðµ ÑÐµÑ€Ð²ÐµÑ€Ð½Ð¾Ð¹ Ð¿Ð¾Ð·Ð¸Ñ†Ð¸Ð¸")).setValue(false);
   private final SliderSettings lowSpeedFactor = (new SliderSettings("LowSpeedStartFactor", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ ÑÐ¼ÐµÑ‰ÐµÐ½Ð¸Ñ (0 - Ð¼ÐµÐ´Ð»ÐµÐ½Ð½Ð¾, 1 - Ð±Ñ‹ÑÑ‚Ñ€Ð¾)")).setValue(1.0F).range(0.0F, 1.0F).visible(() -> this.lowSpeedStart.isValue());
   private final SliderSettings minIncomingPing = (new SliderSettings("MinIncomingPing", "ÐœÐ¸Ð½Ð¸Ð¼Ð°Ð»ÑŒÐ½Ñ‹Ð¹ Ð²Ñ…Ð¾Ð´ÑÑ‰Ð¸Ð¹ Ð¿Ð¸Ð½Ð³ (ms)")).setValue(150.0F).range(0, 2000);
   private final SliderSettings maxIncomingPing = (new SliderSettings("MaxIncomingPing", "ÐœÐ°ÐºÑÐ¸Ð¼Ð°Ð»ÑŒÐ½Ñ‹Ð¹ Ð²Ñ…Ð¾Ð´ÑÑ‰Ð¸Ð¹ Ð¿Ð¸Ð½Ð³ (ms)")).setValue(200.0F).range(0, 4000);
   private final SliderSettings minIncomingPingRecalcDelay = (new SliderSettings("MinIncomingPingRecalculateDelay", "ÐœÐ¸Ð½. Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿ÐµÑ€ÐµÑÑ‡Ñ‘Ñ‚Ð° Ð²Ñ…Ð¾Ð´. Ð¿Ð¸Ð½Ð³Ð° (ms)")).setValue(1000.0F).range(0, 10000);
   private final SliderSettings maxIncomingPingRecalcDelay = (new SliderSettings("MaxIncomingPingRecalculateDelay", "ÐœÐ°ÐºÑ. Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿ÐµÑ€ÐµÑÑ‡Ñ‘Ñ‚Ð° Ð²Ñ…Ð¾Ð´. Ð¿Ð¸Ð½Ð³Ð° (ms)")).setValue(3000.0F).range(0, 20000);
   private final SelectSetting incomingPingAccelerationMode = (new SelectSetting("IncomingPingAccelerationMode", "Ð ÐµÐ¶Ð¸Ð¼ Ð¸Ð·Ð¼ÐµÐ½ÐµÐ½Ð¸Ñ Ð²Ñ…Ð¾Ð´ÑÑ‰ÐµÐ³Ð¾ Ð¿Ð¸Ð½Ð³Ð°")).value("Instant", "Smooth").selected("Instant");
   private final SliderSettings minIncomingPingDecelerationTime = (new SliderSettings("MinIncomingPingDecelerationTime", "ÐœÐ¸Ð½. Ð²Ñ€ÐµÐ¼Ñ ÑÐ½Ð¸Ð¶ÐµÐ½Ð¸Ñ Ð²Ñ…Ð¾Ð´. Ð¿Ð¸Ð½Ð³Ð° (ms)")).setValue(250.0F).range(0, 5000).visible(() -> this.incomingPingAccelerationMode.isSelected("Instant"));
   private final SliderSettings maxIncomingPingDecelerationTime = (new SliderSettings("MaxIncomingPingDecelerationTime", "ÐœÐ°ÐºÑ. Ð²Ñ€ÐµÐ¼Ñ ÑÐ½Ð¸Ð¶ÐµÐ½Ð¸Ñ Ð²Ñ…Ð¾Ð´. Ð¿Ð¸Ð½Ð³Ð° (ms)")).setValue(750.0F).range(0, 10000).visible(() -> this.incomingPingAccelerationMode.isSelected("Instant"));
   private final SliderSettings minIncomingPingAccelerationPerSecond = (new SliderSettings("MinIncomingPingAccelerationPerSecond", "ÐœÐ¸Ð½. ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ðµ Ð²Ñ…Ð¾Ð´. Ð¿Ð¸Ð½Ð³Ð° (ms/s)")).setValue(50.0F).range(0, 2000).visible(() -> this.incomingPingAccelerationMode.isSelected("Smooth"));
   private final SliderSettings maxIncomingPingAccelerationPerSecond = (new SliderSettings("MaxIncomingPingAccelerationPerSecond", "ÐœÐ°ÐºÑ. ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ðµ Ð²Ñ…Ð¾Ð´. Ð¿Ð¸Ð½Ð³Ð° (ms/s)")).setValue(150.0F).range(0, 4000).visible(() -> this.incomingPingAccelerationMode.isSelected("Smooth"));
   private final SliderSettings minIncomingPingAccelerationApplyDelay = (new SliderSettings("MinIncomingPingAccelerationApplyDelay", "ÐœÐ¸Ð½. Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿Ñ€Ð¸Ð¼ÐµÐ½ÐµÐ½Ð¸Ñ ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ñ Ð²Ñ…Ð¾Ð´. Ð¿Ð¸Ð½Ð³Ð° (ms)")).setValue(250.0F).range(0, 5000).visible(() -> this.incomingPingAccelerationMode.isSelected("Smooth"));
   private final SliderSettings maxIncomingPingAccelerationApplyDelay = (new SliderSettings("MaxIncomingPingAccelerationApplyDelay", "ÐœÐ°ÐºÑ. Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿Ñ€Ð¸Ð¼ÐµÐ½ÐµÐ½Ð¸Ñ ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ñ Ð²Ñ…Ð¾Ð´. Ð¿Ð¸Ð½Ð³Ð° (ms)")).setValue(750.0F).range(0, 10000).visible(() -> this.incomingPingAccelerationMode.isSelected("Smooth"));
   private final SliderSettings minIncomingPingAccelerationRecalcDelay = (new SliderSettings("MinIncomingPingAccelerationRecalculateDelay", "ÐœÐ¸Ð½. Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿ÐµÑ€ÐµÑÑ‡Ñ‘Ñ‚Ð° ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ñ Ð²Ñ…Ð¾Ð´. Ð¿Ð¸Ð½Ð³Ð° (ms)")).setValue(1000.0F).range(0, 10000).visible(() -> this.incomingPingAccelerationMode.isSelected("Smooth"));
   private final SliderSettings maxIncomingPingAccelerationRecalcDelay = (new SliderSettings("MaxIncomingPingAccelerationRecalculateDelay", "ÐœÐ°ÐºÑ. Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿ÐµÑ€ÐµÑÑ‡Ñ‘Ñ‚Ð° ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ñ Ð²Ñ…Ð¾Ð´. Ð¿Ð¸Ð½Ð³Ð° (ms)")).setValue(3000.0F).range(0, 20000).visible(() -> this.incomingPingAccelerationMode.isSelected("Smooth"));
   private final SliderSettings minIncomingPingDecelerationTime2 = (new SliderSettings("MinIncomingPingDecelerationTimeSmooth", "ÐœÐ¸Ð½. Ð²Ñ€ÐµÐ¼Ñ ÑÐ½Ð¸Ð¶ÐµÐ½Ð¸Ñ Ð²Ñ…Ð¾Ð´. Ð¿Ð¸Ð½Ð³Ð° (Smooth) (ms)")).setValue(250.0F).range(0, 5000).visible(() -> this.incomingPingAccelerationMode.isSelected("Smooth"));
   private final SliderSettings maxIncomingPingDecelerationTime2 = (new SliderSettings("MaxIncomingPingDecelerationTimeSmooth", "ÐœÐ°ÐºÑ. Ð²Ñ€ÐµÐ¼Ñ ÑÐ½Ð¸Ð¶ÐµÐ½Ð¸Ñ Ð²Ñ…Ð¾Ð´. Ð¿Ð¸Ð½Ð³Ð° (Smooth) (ms)")).setValue(750.0F).range(0, 10000).visible(() -> this.incomingPingAccelerationMode.isSelected("Smooth"));
   private final SliderSettings minOutgoingPing = (new SliderSettings("MinOutgoingPing", "ÐœÐ¸Ð½Ð¸Ð¼Ð°Ð»ÑŒÐ½Ñ‹Ð¹ Ð¸ÑÑ…Ð¾Ð´ÑÑ‰Ð¸Ð¹ Ð¿Ð¸Ð½Ð³ (ms)")).setValue(150.0F).range(0, 2000);
   private final SliderSettings maxOutgoingPing = (new SliderSettings("MaxOutgoingPing", "ÐœÐ°ÐºÑÐ¸Ð¼Ð°Ð»ÑŒÐ½Ñ‹Ð¹ Ð¸ÑÑ…Ð¾Ð´ÑÑ‰Ð¸Ð¹ Ð¿Ð¸Ð½Ð³ (ms)")).setValue(200.0F).range(0, 4000);
   private final SliderSettings minOutgoingPingRecalcDelay = (new SliderSettings("MinOutgoingPingRecalculateDelay", "ÐœÐ¸Ð½. Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿ÐµÑ€ÐµÑÑ‡Ñ‘Ñ‚Ð° Ð¸ÑÑ…. Ð¿Ð¸Ð½Ð³Ð° (ms)")).setValue(1000.0F).range(0, 10000);
   private final SliderSettings maxOutgoingPingRecalcDelay = (new SliderSettings("MaxOutgoingPingRecalculateDelay", "ÐœÐ°ÐºÑ. Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿ÐµÑ€ÐµÑÑ‡Ñ‘Ñ‚Ð° Ð¸ÑÑ…. Ð¿Ð¸Ð½Ð³Ð° (ms)")).setValue(3000.0F).range(0, 20000);
   private final SelectSetting outgoingPingAccelerationMode = (new SelectSetting("OutgoingPingAccelerationMode", "Ð ÐµÐ¶Ð¸Ð¼ Ð¸Ð·Ð¼ÐµÐ½ÐµÐ½Ð¸Ñ Ð¸ÑÑ…Ð¾Ð´ÑÑ‰ÐµÐ³Ð¾ Ð¿Ð¸Ð½Ð³Ð°")).value("Instant", "Smooth").selected("Instant");
   private final SliderSettings minOutgoingPingDecelerationTime = (new SliderSettings("MinOutgoingPingDecelerationTime", "ÐœÐ¸Ð½. Ð²Ñ€ÐµÐ¼Ñ ÑÐ½Ð¸Ð¶ÐµÐ½Ð¸Ñ Ð¸ÑÑ…. Ð¿Ð¸Ð½Ð³Ð° (ms)")).setValue(250.0F).range(0, 5000).visible(() -> this.outgoingPingAccelerationMode.isSelected("Instant"));
   private final SliderSettings maxOutgoingPingDecelerationTime = (new SliderSettings("MaxOutgoingPingDecelerationTime", "ÐœÐ°ÐºÑ. Ð²Ñ€ÐµÐ¼Ñ ÑÐ½Ð¸Ð¶ÐµÐ½Ð¸Ñ Ð¸ÑÑ…. Ð¿Ð¸Ð½Ð³Ð° (ms)")).setValue(750.0F).range(0, 10000).visible(() -> this.outgoingPingAccelerationMode.isSelected("Instant"));
   private final SliderSettings minOutgoingPingAccelerationPerSecond = (new SliderSettings("MinOutgoingPingAccelerationPerSecond", "ÐœÐ¸Ð½. ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ðµ Ð¸ÑÑ…. Ð¿Ð¸Ð½Ð³Ð° (ms/s)")).setValue(50.0F).range(0, 2000).visible(() -> this.outgoingPingAccelerationMode.isSelected("Smooth"));
   private final SliderSettings maxOutgoingPingAccelerationPerSecond = (new SliderSettings("MaxOutgoingPingAccelerationPerSecond", "ÐœÐ°ÐºÑ. ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ðµ Ð¸ÑÑ…. Ð¿Ð¸Ð½Ð³Ð° (ms/s)")).setValue(150.0F).range(0, 4000).visible(() -> this.outgoingPingAccelerationMode.isSelected("Smooth"));
   private final SliderSettings minOutgoingPingAccelerationApplyDelay = (new SliderSettings("MinOutgoingPingAccelerationApplyDelay", "ÐœÐ¸Ð½. Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿Ñ€Ð¸Ð¼ÐµÐ½ÐµÐ½Ð¸Ñ ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ñ Ð¸ÑÑ…. Ð¿Ð¸Ð½Ð³Ð° (ms)")).setValue(250.0F).range(0, 5000).visible(() -> this.outgoingPingAccelerationMode.isSelected("Smooth"));
   private final SliderSettings maxOutgoingPingAccelerationApplyDelay = (new SliderSettings("MaxOutgoingPingAccelerationApplyDelay", "ÐœÐ°ÐºÑ. Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿Ñ€Ð¸Ð¼ÐµÐ½ÐµÐ½Ð¸Ñ ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ñ Ð¸ÑÑ…. Ð¿Ð¸Ð½Ð³Ð° (ms)")).setValue(750.0F).range(0, 10000).visible(() -> this.outgoingPingAccelerationMode.isSelected("Smooth"));
   private final SliderSettings minOutgoingPingAccelerationRecalcDelay = (new SliderSettings("MinOutgoingPingAccelerationRecalculateDelay", "ÐœÐ¸Ð½. Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿ÐµÑ€ÐµÑÑ‡Ñ‘Ñ‚Ð° ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ñ Ð¸ÑÑ…. Ð¿Ð¸Ð½Ð³Ð° (ms)")).setValue(1000.0F).range(0, 10000).visible(() -> this.outgoingPingAccelerationMode.isSelected("Smooth"));
   private final SliderSettings maxOutgoingPingAccelerationRecalcDelay = (new SliderSettings("MaxOutgoingPingAccelerationRecalculateDelay", "ÐœÐ°ÐºÑ. Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿ÐµÑ€ÐµÑÑ‡Ñ‘Ñ‚Ð° ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ñ Ð¸ÑÑ…. Ð¿Ð¸Ð½Ð³Ð° (ms)")).setValue(3000.0F).range(0, 20000).visible(() -> this.outgoingPingAccelerationMode.isSelected("Smooth"));
   private final SliderSettings minOutgoingPingDecelerationTime2 = (new SliderSettings("MinOutgoingPingDecelerationTimeSmooth", "ÐœÐ¸Ð½. Ð²Ñ€ÐµÐ¼Ñ ÑÐ½Ð¸Ð¶ÐµÐ½Ð¸Ñ Ð¸ÑÑ…. Ð¿Ð¸Ð½Ð³Ð° (Smooth) (ms)")).setValue(250.0F).range(0, 5000).visible(() -> this.outgoingPingAccelerationMode.isSelected("Smooth"));
   private final SliderSettings maxOutgoingPingDecelerationTime2 = (new SliderSettings("MaxOutgoingPingDecelerationTimeSmooth", "ÐœÐ°ÐºÑ. Ð²Ñ€ÐµÐ¼Ñ ÑÐ½Ð¸Ð¶ÐµÐ½Ð¸Ñ Ð¸ÑÑ…. Ð¿Ð¸Ð½Ð³Ð° (Smooth) (ms)")).setValue(750.0F).range(0, 10000).visible(() -> this.outgoingPingAccelerationMode.isSelected("Smooth"));
   private final BooleanSetting outgoingComboMode = (new BooleanSetting("OutgoingComboMode", "Combo Ñ€ÐµÐ¶Ð¸Ð¼ Ð¸ÑÑ…Ð¾Ð´ÑÑ‰ÐµÐ³Ð¾ Ð¿Ð¸Ð½Ð³Ð°")).setValue(false);
   private final SliderSettings minOutgoingPingStartDelay = (new SliderSettings("MinOutgoingPingStartDelay", "ÐœÐ¸Ð½. ÑÑ‚Ð°Ñ€Ñ‚Ð¾Ð²Ð°Ñ Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿Ð¸Ð½Ð³Ð° (ms)")).setValue(0.0F).range(0, 5000).visible(() -> this.outgoingComboMode.isValue());
   private final SliderSettings maxOutgoingPingStartDelay = (new SliderSettings("MaxOutgoingPingStartDelay", "ÐœÐ°ÐºÑ. ÑÑ‚Ð°Ñ€Ñ‚Ð¾Ð²Ð°Ñ Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿Ð¸Ð½Ð³Ð° (ms)")).setValue(0.0F).range(0, 10000).visible(() -> this.outgoingComboMode.isValue());
   private final SliderSettings resetPingAtDistanceToTarget = (new SliderSettings("ResetPingAtDistanceToTarget", "Ð¡Ð±Ñ€Ð¾Ñ Ð¿Ð¸Ð½Ð³Ð° Ð¿Ð¾ Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ð¸ Ð´Ð¾ Ñ†ÐµÐ»Ð¸")).setValue(0.0F).range(0.0F, 20.0F).visible(() -> this.outgoingComboMode.isValue());
   private final Deque queuedPackets = new ArrayDeque();
   private final Deque positionHistory = new ArrayDeque();
   private double currentIncomingPingMs = (double)0.0F;
   private double targetIncomingPingMs = (double)0.0F;
   private long nextIncomingPingRecalcAt = 0L;
   private double currentOutgoingPingMs = (double)0.0F;
   private double targetOutgoingPingMs = (double)0.0F;
   private long nextOutgoingPingRecalcAt = 0L;
   private boolean pulsing = false;
   private long pulseStartTime = 0L;
   private long lastPulseEndTime = 0L;
   private long lagResumeAtMs = 0L;
   private class_243 renderPos = null;
   private float renderYaw = 0.0F;
   private boolean hasRenderState = false;

   public static FakeLag getInstance() {
      return (FakeLag)Instance.get(FakeLag.class);
   }

   public FakeLag() {
      super("LagRange", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.delayMs, this.fastMode, this.fastFlushAll, this.nextLagDelayMs, this.maxPacketsPerTick, this.flushOnDisable, this.autoBlinkOnStop, this.pulseMode, this.pulseDurationMs, this.pulseCooldownMs, this.maxPulsePackets, this.playerActivateRange, this.playerActivateRadius, this.blinkPlayerRadius, this.blinkActions, this.minIncomingPing, this.maxIncomingPing, this.minIncomingPingRecalcDelay, this.maxIncomingPingRecalcDelay, this.incomingPingAccelerationMode, this.minIncomingPingDecelerationTime, this.maxIncomingPingDecelerationTime, this.minIncomingPingAccelerationPerSecond, this.maxIncomingPingAccelerationPerSecond, this.minIncomingPingAccelerationApplyDelay, this.maxIncomingPingAccelerationApplyDelay, this.minIncomingPingAccelerationRecalcDelay, this.maxIncomingPingAccelerationRecalcDelay, this.minIncomingPingDecelerationTime2, this.maxIncomingPingDecelerationTime2, this.minOutgoingPing, this.maxOutgoingPing, this.minOutgoingPingRecalcDelay, this.maxOutgoingPingRecalcDelay, this.outgoingPingAccelerationMode, this.minOutgoingPingDecelerationTime, this.maxOutgoingPingDecelerationTime, this.minOutgoingPingAccelerationPerSecond, this.maxOutgoingPingAccelerationPerSecond, this.minOutgoingPingAccelerationApplyDelay, this.maxOutgoingPingAccelerationApplyDelay, this.minOutgoingPingAccelerationRecalcDelay, this.maxOutgoingPingAccelerationRecalcDelay, this.minOutgoingPingDecelerationTime2, this.maxOutgoingPingDecelerationTime2, this.outgoingComboMode, this.minOutgoingPingStartDelay, this.maxOutgoingPingStartDelay, this.resetPingAtDistanceToTarget, this.renderEsp, this.espSmoothMode, this.hideEspInFirstPerson, this.chamsColor, this.visualSpinMethod, this.spinSpeed, this.lowSpeedStart, this.lowSpeedFactor});
   }

   private boolean isDelayActive() {
      return this.delayMs.getValue() > 0.0F;
   }

   private boolean isBlinkingInternal() {
      return System.currentTimeMillis() < this.lagResumeAtMs;
   }

   private void startBlink() {
      long now = System.currentTimeMillis();
      if (this.flushOnDisable.isValue()) {
         this.flushAll();
      } else {
         this.queuedPackets.clear();
      }

      this.pulsing = false;
      this.lagResumeAtMs = now + (long)this.nextLagDelayMs.getValue();
      this.positionHistory.clear();
      if (mc.field_1724 != null) {
         this.positionHistory.addLast(new PositionSample(now, mc.field_1724.method_19538(), mc.field_1724.method_36454(), mc.field_1724.method_36455()));
      }

      this.renderPos = null;
      this.hasRenderState = false;
   }

   public void activate() {
      this.queuedPackets.clear();
      this.positionHistory.clear();
      this.pulsing = false;
      this.pulseStartTime = 0L;
      this.lastPulseEndTime = 0L;
      this.lagResumeAtMs = 0L;
      this.renderPos = null;
      this.renderYaw = 0.0F;
      this.hasRenderState = false;
      if (mc.field_1724 != null) {
         long now = System.currentTimeMillis();
         this.positionHistory.addLast(new PositionSample(now, mc.field_1724.method_19538(), mc.field_1724.method_36454(), mc.field_1724.method_36455()));
      }

   }

   public void deactivate() {
      if (this.flushOnDisable.isValue()) {
         this.flushAll();
      } else {
         this.queuedPackets.clear();
      }

      this.positionHistory.clear();
      this.pulsing = false;
      this.lagResumeAtMs = 0L;
      this.renderPos = null;
      this.renderYaw = 0.0F;
      this.hasRenderState = false;
   }

   private void flushAll() {
      if (!this.queuedPackets.isEmpty()) {
         while(!this.queuedPackets.isEmpty()) {
            QueuedPacket qp = (QueuedPacket)this.queuedPackets.pollFirst();
            if (qp != null && qp.packet() != null) {
               PlayerInteractionHelper.sendPacketWithOutEvent(qp.packet());
            }
         }

      }
   }

   private void flushExpiredNormal() {
      if (this.isDelayActive()) {
         if (!this.queuedPackets.isEmpty()) {
            long now = System.currentTimeMillis();
            long delay = this.getCurrentDelayMs();
            int limit;
            if (this.fastMode.isValue() && this.fastFlushAll.isValue()) {
               limit = Integer.MAX_VALUE;
            } else {
               limit = (int)this.maxPacketsPerTick.getValue();
            }

            for(int sent = 0; !this.queuedPackets.isEmpty() && sent < limit; ++sent) {
               QueuedPacket first = (QueuedPacket)this.queuedPackets.peekFirst();
               if (first == null || now - first.timestamp() < delay) {
                  break;
               }

               this.queuedPackets.pollFirst();
               PlayerInteractionHelper.sendPacketWithOutEvent(first.packet());
            }

         }
      }
   }

   private long getCurrentDelayMs() {
      if (this.fastMode.isValue()) {
         double v = (double)this.delayMs.getValue();
         if (v < (double)0.0F) {
            v = (double)0.0F;
         }

         return (long)v;
      } else {
         double min = (double)this.minOutgoingPing.getValue();
         double max = (double)this.maxOutgoingPing.getValue();
         if (max < min) {
            double t = min;
            min = max;
            max = t;
         }

         double mid = (min + max) * (double)0.5F;
         if (mid < (double)0.0F) {
            mid = (double)0.0F;
         }

         return (long)mid;
      }
   }

   private void updateHistory() {
      if (mc.field_1724 != null) {
         long now = System.currentTimeMillis();
         this.positionHistory.addLast(new PositionSample(now, mc.field_1724.method_19538(), mc.field_1724.method_36454(), mc.field_1724.method_36455()));
         long keepTime = Math.max(this.getCurrentDelayMs(), (long)this.pulseDurationMs.getValue()) + 2000L;

         while(!this.positionHistory.isEmpty()) {
            PositionSample first = (PositionSample)this.positionHistory.peekFirst();
            if (first == null || now - first.timestamp() <= keepTime) {
               break;
            }

            this.positionHistory.pollFirst();
         }

      }
   }

   private PositionSample getServerSample() {
      if (this.positionHistory.isEmpty()) {
         return null;
      } else {
         long now = System.currentTimeMillis();
         long targetTime = now - this.getCurrentDelayMs();
         PositionSample closest = null;
         long bestDiff = Long.MAX_VALUE;

         for(PositionSample sample : this.positionHistory) {
            long diff = Math.abs(sample.timestamp() - targetTime);
            if (diff < bestDiff) {
               bestDiff = diff;
               closest = sample;
            }
         }

         return closest;
      }
   }

   private boolean isPlayerStopped() {
      if (mc.field_1724 == null) {
         return false;
      } else {
         class_243 vel = mc.field_1724.method_18798();
         double speedSq = vel.field_1352 * vel.field_1352 + vel.field_1350 * vel.field_1350;
         return speedSq < 1.0E-4;
      }
   }

   private boolean canStartPulse(long now) {
      long cd = (long)this.pulseCooldownMs.getValue();
      return !this.pulsing && now - this.lastPulseEndTime >= cd;
   }

   private boolean isPulseExpired(long now) {
      long duration = (long)this.pulseDurationMs.getValue();
      return this.pulsing && now - this.pulseStartTime >= duration;
   }

   private boolean hasNearbyPlayer(double radius) {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         if (radius <= (double)0.0F) {
            return true;
         } else {
            double r2 = radius * radius;

            for(class_1657 p : mc.field_1687.method_18456()) {
               if (p != mc.field_1724 && !p.method_29504() && !p.method_31481()) {
                  double d2 = p.method_5858(mc.field_1724);
                  if (d2 <= r2) {
                     return true;
                  }
               }
            }

            return false;
         }
      } else {
         return false;
      }
   }

   private double nearestPlayerDistance() {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         double best = Double.MAX_VALUE;

         for(class_1657 p : mc.field_1687.method_18456()) {
            if (p != mc.field_1724 && !p.method_29504() && !p.method_31481()) {
               double d = (double)p.method_5739(mc.field_1724);
               if (d < best) {
                  best = d;
               }
            }
         }

         return best;
      } else {
         return Double.MAX_VALUE;
      }
   }

   private boolean isRidingVehicle() {
      return mc.field_1724 != null && mc.field_1724.method_5765();
   }

   private boolean isUsingFood() {
      if (mc.field_1724 == null) {
         return false;
      } else if (!mc.field_1724.method_6115()) {
         return false;
      } else {
         class_1799 stack = mc.field_1724.method_6030();
         if (stack != null && !stack.method_7960()) {
            class_1839 action = stack.method_7976();
            return action == class_1839.field_8950 || action == class_1839.field_8946;
         } else {
            return false;
         }
      }
   }

   private boolean isPvpUseAction() {
      if (mc.field_1724 == null) {
         return false;
      } else if (!mc.field_1724.method_6115()) {
         return false;
      } else {
         class_1799 stack = mc.field_1724.method_6030();
         if (stack != null && !stack.method_7960()) {
            class_1839 action = stack.method_7976();
            return action == class_1839.field_8949 || action == class_1839.field_8953 || action == class_1839.field_8947;
         } else {
            return false;
         }
      }
   }

   private boolean hasElytraEquipped() {
      if (mc.field_1724 == null) {
         return false;
      } else {
         class_1799 chest = mc.field_1724.method_6118(class_1304.field_6174);
         return chest != null && chest.method_31574(class_1802.field_8833);
      }
   }

   private boolean isInInventory() {
      return mc.field_1755 instanceof class_465;
   }

   private boolean isInChat() {
      return mc.field_1755 instanceof class_408;
   }

   private boolean isSneaking() {
      return mc.field_1724 != null && mc.field_1724.method_5715();
   }

   private boolean isInWater() {
      return mc.field_1724 != null && mc.field_1724.method_5799();
   }

   private boolean isUsingPotion() {
      if (mc.field_1724 == null) {
         return false;
      } else if (!mc.field_1724.method_6115()) {
         return false;
      } else {
         class_1799 stack = mc.field_1724.method_6030();
         if (stack != null && !stack.method_7960()) {
            return stack.method_31574(class_1802.field_8574) || stack.method_31574(class_1802.field_8436) || stack.method_31574(class_1802.field_8150);
         } else {
            return false;
         }
      }
   }

   private void handleBlinkConditionsTick() {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         double radius = (double)this.blinkPlayerRadius.getValue();
         if (radius > (double)0.0F) {
            double dist = this.nearestPlayerDistance();
            if (dist <= radius && dist != Double.MAX_VALUE) {
               this.startBlink();
            }
         }

         if (this.blinkActions.isSelected("Vehicle") && this.isRidingVehicle()) {
            this.startBlink();
         }

         if (this.blinkActions.isSelected("Elytra") && this.hasElytraEquipped()) {
            this.startBlink();
         }

         if (this.blinkActions.isSelected("Inventory") && this.isInInventory()) {
            this.startBlink();
         }

         if (this.blinkActions.isSelected("Chat") && this.isInChat()) {
            this.startBlink();
         }

         if (this.blinkActions.isSelected("Eat") && this.isUsingFood()) {
            this.startBlink();
         }

         if (this.blinkActions.isSelected("PvpAction") && this.isPvpUseAction()) {
            this.startBlink();
         }

         if (this.blinkActions.isSelected("Sneak") && this.isSneaking()) {
            this.startBlink();
         }

         if (this.blinkActions.isSelected("Water") && this.isInWater()) {
            this.startBlink();
         }

         if (this.blinkActions.isSelected("StopMotion") && this.isPlayerStopped()) {
            this.startBlink();
         }

         if (this.blinkActions.isSelected("Potion") && this.isUsingPotion()) {
            this.startBlink();
         }

         if (this.blinkActions.isSelected("AnyAction")) {
            boolean any = this.isUsingFood() || this.isPvpUseAction() || this.isUsingPotion() || this.isInInventory() || this.isInChat() || this.isSneaking() || this.isInWater() || this.isRidingVehicle() || this.hasElytraEquipped();
            if (any) {
               this.startBlink();
            }
         }

      }
   }

   private boolean isLaggingAllowed() {
      if (!this.isDelayActive() && !this.pulseMode.isValue()) {
         return false;
      } else if (this.isBlinkingInternal()) {
         return false;
      } else {
         return !this.playerActivateRange.isValue() || this.hasNearbyPlayer((double)this.playerActivateRadius.getValue());
      }
   }

   public boolean isLagActiveForAura() {
      return this.isLaggingAllowed() && !this.queuedPackets.isEmpty();
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.isState()) {
         if (!PlayerInteractionHelper.nullCheck()) {
            this.handleBlinkConditionsTick();
            if (!this.isLaggingAllowed()) {
               this.flushAll();
               this.updateHistory();
            } else {
               this.updateHistory();
               long now = System.currentTimeMillis();
               if (this.pulseMode.isValue()) {
                  if (this.pulsing) {
                     boolean tooLong = this.isPulseExpired(now);
                     boolean tooManyPackets = this.queuedPackets.size() >= (int)this.maxPulsePackets.getValue();
                     if (tooLong || tooManyPackets) {
                        this.flushAll();
                        this.pulsing = false;
                        this.lastPulseEndTime = now;
                     }
                  }
               } else {
                  this.flushExpiredNormal();
                  if (this.isDelayActive() && this.autoBlinkOnStop.isValue() && this.isPlayerStopped()) {
                     this.startBlink();
                  }
               }

            }
         }
      }
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (this.isState()) {
         if (!PlayerInteractionHelper.nullCheck()) {
            class_2596<?> packet = e.getPacket();
            if (e.isSend()) {
               if (this.isDelayActive() || this.pulseMode.isValue()) {
                  if (packet instanceof class_2799) {
                     class_2799 status = (class_2799)packet;
                     if (status.method_12119().equals(class_2800.field_12774)) {
                        return;
                     }
                  }

                  long now = System.currentTimeMillis();
                  if (packet instanceof class_2824) {
                     class_2824 interact = (class_2824)packet;
                     if (this.blinkActions.isSelected("PvpAction")) {
                        try {
                           Object it = this.getInteractTypeCompat(interact);
                           if (it != null && String.valueOf(it).toUpperCase().contains("ATTACK")) {
                              this.startBlink();
                           }
                        } catch (Throwable var7) {
                        }
                     }
                  }

                  if (this.isLaggingAllowed()) {
                     if (this.pulseMode.isValue()) {
                        if (!this.pulsing) {
                           if (!this.canStartPulse(now)) {
                              return;
                           }

                           this.pulsing = true;
                           this.pulseStartTime = now;
                        }

                        this.queuedPackets.addLast(new QueuedPacket(now, packet));
                        e.cancel();
                     } else {
                        if (!this.isDelayActive()) {
                           return;
                        }

                        this.queuedPackets.addLast(new QueuedPacket(now, packet));
                        e.cancel();
                     }

                  }
               }
            } else {
               if (packet instanceof class_2724 || packet instanceof class_2678) {
                  this.setState(false);
               }

               if (packet instanceof class_2743) {
                  class_2743 vel = (class_2743)packet;
                  if (this.blinkActions.isSelected("Velocity") && mc.field_1724 != null && vel.method_11818() == mc.field_1724.method_5628()) {
                     this.startBlink();
                  }
               }

               if (packet instanceof class_2663) {
                  class_2663 s2c = (class_2663)packet;
                  if (this.blinkActions.isSelected("Velocity")) {
                     class_1297 ent = s2c.method_11469(mc.field_1687);
                     if (ent != null && ent.equals(mc.field_1724)) {
                        byte st = s2c.method_11470();
                        if (st == 2 || st == 33) {
                           this.startBlink();
                        }
                     }
                  }
               }

            }
         }
      }
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      if (this.isState()) {
         if (mc.field_1687 != null && mc.field_1724 != null) {
            if (!this.renderEsp.isSelected("Off")) {
               if (!this.hideEspInFirstPerson.isValue() || !mc.field_1690.method_31044().method_31034()) {
                  boolean lagActive = !this.queuedPackets.isEmpty() && this.isLaggingAllowed();
                  if (lagActive) {
                     if (!this.isDelayActive()) {
                        if (this.renderEsp.isSelected("Soul")) {
                           float yaw = mc.field_1724.method_36454();
                           if (this.visualSpinMethod.isValue()) {
                              float speed = this.spinSpeed.getValue();
                              if (speed > 0.0F) {
                                 long now = System.currentTimeMillis();
                                 float spin = (float)(now % 3600L) / 10.0F * speed;
                                 yaw += spin;
                              }
                           }

                           Render3D.drawEntity(mc.field_1724, mc.field_1724.method_19538(), yaw, 200, e.getStack(), e.getPartialTicks());
                        } else {
                           int color = this.chamsColor.getColor();
                           if (this.renderEsp.isSelected("Chams")) {
                              int rgba = color;
                              float yaw = mc.field_1724.method_36454();
                              if (this.visualSpinMethod.isValue()) {
                                 float speed = this.spinSpeed.getValue();
                                 if (speed > 0.0F) {
                                    long now = System.currentTimeMillis();
                                    float spin = (float)(now % 3600L) / 10.0F * speed;
                                    yaw += spin;
                                 }
                              }

                              class_1799 head = mc.field_1724.method_6118(class_1304.field_6169);
                              class_1799 chest = mc.field_1724.method_6118(class_1304.field_6174);
                              class_1799 legs = mc.field_1724.method_6118(class_1304.field_6172);
                              class_1799 feet = mc.field_1724.method_6118(class_1304.field_6166);
                              mc.field_1724.method_5673(class_1304.field_6169, class_1799.field_8037);
                              mc.field_1724.method_5673(class_1304.field_6174, class_1799.field_8037);
                              mc.field_1724.method_5673(class_1304.field_6172, class_1799.field_8037);
                              mc.field_1724.method_5673(class_1304.field_6166, class_1799.field_8037);

                              try {
                                 Render3D.drawEntityChams(mc.field_1724, mc.field_1724.method_19538(), yaw, rgba, e.getStack(), e.getPartialTicks());
                              } finally {
                                 mc.field_1724.method_5673(class_1304.field_6169, head);
                                 mc.field_1724.method_5673(class_1304.field_6174, chest);
                                 mc.field_1724.method_5673(class_1304.field_6172, legs);
                                 mc.field_1724.method_5673(class_1304.field_6166, feet);
                              }

                           } else if (this.renderEsp.isSelected("Box")) {
                              Render3D.drawBox(mc.field_1724.method_5829(), color, 1.5F);
                           }
                        }
                     } else {
                        PositionSample sample = this.getServerSample();
                        if (sample != null) {
                           class_243 targetPos = sample.pos();
                           float targetYaw = sample.yaw();
                           class_243 localPos = mc.field_1724.method_19538();
                           if (targetPos.method_1025(localPos) < 0.04) {
                              this.renderPos = null;
                              this.hasRenderState = false;
                           } else {
                              long now = System.currentTimeMillis();
                              if (this.hasRenderState && this.renderPos != null) {
                                 double posFactor;
                                 if (this.lowSpeedStart.isValue()) {
                                    posFactor = (double)class_3532.method_15363(this.lowSpeedFactor.getValue(), 0.0F, 1.0F);
                                 } else {
                                    posFactor = (double)1.0F;
                                 }

                                 this.renderPos = this.renderPos.method_1019(targetPos.method_1020(this.renderPos).method_1021(posFactor));
                                 float yawDiff = this.wrapDegrees(targetYaw - this.renderYaw);
                                 this.renderYaw += yawDiff * (float)posFactor;
                              } else {
                                 this.renderPos = targetPos;
                                 this.renderYaw = targetYaw;
                                 this.hasRenderState = true;
                              }

                              if (this.renderEsp.isSelected("Soul")) {
                                 float finalYaw = this.renderYaw;
                                 if (this.visualSpinMethod.isValue()) {
                                    float speed = this.spinSpeed.getValue();
                                    if (speed > 0.0F) {
                                       float spin = (float)(now % 3600L) / 10.0F * speed;
                                       finalYaw += spin;
                                    }
                                 }

                                 Render3D.drawEntity(mc.field_1724, this.renderPos, finalYaw, 200, e.getStack(), e.getPartialTicks());
                              } else {
                                 int color = this.chamsColor.getColor();
                                 if (this.renderEsp.isSelected("Chams")) {
                                    int rgba = color;
                                    float finalYaw = this.renderYaw;
                                    if (this.visualSpinMethod.isValue()) {
                                       float speed = this.spinSpeed.getValue();
                                       if (speed > 0.0F) {
                                          float spin = (float)(now % 3600L) / 10.0F * speed;
                                          finalYaw += spin;
                                       }
                                    }

                                    class_1799 head = mc.field_1724.method_6118(class_1304.field_6169);
                                    class_1799 chest = mc.field_1724.method_6118(class_1304.field_6174);
                                    class_1799 legs = mc.field_1724.method_6118(class_1304.field_6172);
                                    class_1799 feet = mc.field_1724.method_6118(class_1304.field_6166);
                                    mc.field_1724.method_5673(class_1304.field_6169, class_1799.field_8037);
                                    mc.field_1724.method_5673(class_1304.field_6174, class_1799.field_8037);
                                    mc.field_1724.method_5673(class_1304.field_6172, class_1799.field_8037);
                                    mc.field_1724.method_5673(class_1304.field_6166, class_1799.field_8037);

                                    try {
                                       Render3D.drawEntityChams(mc.field_1724, this.renderPos, finalYaw, rgba, e.getStack(), e.getPartialTicks());
                                    } finally {
                                       mc.field_1724.method_5673(class_1304.field_6169, head);
                                       mc.field_1724.method_5673(class_1304.field_6174, chest);
                                       mc.field_1724.method_5673(class_1304.field_6172, legs);
                                       mc.field_1724.method_5673(class_1304.field_6166, feet);
                                    }

                                 } else {
                                    if (this.renderEsp.isSelected("Box")) {
                                       class_243 currentPos = mc.field_1724.method_19538();
                                       class_243 offset = this.renderPos.method_1020(currentPos);
                                       class_238 bb = mc.field_1724.method_5829().method_997(offset);
                                       Render3D.drawBox(bb, color, 1.5F);
                                    }

                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private float wrapDegrees(float value) {
      value %= 360.0F;
      if (value >= 180.0F) {
         value -= 360.0F;
      }

      if (value < -180.0F) {
         value += 360.0F;
      }

      return value;
   }

   private Object getInteractTypeCompat(class_2824 pkt) {
      try {
         return class_2824.class.getMethod("getType").invoke(pkt);
      } catch (Throwable var5) {
         try {
            return class_2824.class.getMethod("getInteractionType").invoke(pkt);
         } catch (Throwable var4) {
            try {
               return class_2824.class.getMethod("getAction").invoke(pkt);
            } catch (Throwable var3) {
               return null;
            }
         }
      }
   }

   private static record QueuedPacket(long timestamp, class_2596 packet) {
   }

   private static record PositionSample(long timestamp, class_243 pos, float yaw, float pitch) {
   }
}

