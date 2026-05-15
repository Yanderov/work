package fun.Yanderov.features.impl.misc;

import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import fun.Yanderov.common.repository.friend.FriendUtils;
import fun.Yanderov.display.hud.CoolDowns;
import fun.Yanderov.display.hud.Notifications;
import fun.Yanderov.events.container.SetScreenEvent;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.RotationUpdateEvent;
import fun.Yanderov.events.render.DrawEvent;
import fun.Yanderov.events.render.WorldRenderEvent;
import fun.Yanderov.features.impl.render.ProjectilePrediction;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BindSetting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.chat.StringHelper;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.client.packet.network.Network;
import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.display.color.GradientAssist;
import fun.Yanderov.utils.display.font.FontRenderer;
import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.display.geometry.Render2D;
import fun.Yanderov.utils.display.geometry.Render3D;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import fun.Yanderov.utils.features.aura.point.MultiPoint;
import fun.Yanderov.utils.features.aura.rotations.constructor.LinearConstructor;
import fun.Yanderov.utils.features.aura.utils.MathAngle;
import fun.Yanderov.utils.features.aura.warp.Turns;
import fun.Yanderov.utils.features.aura.warp.TurnsConfig;
import fun.Yanderov.utils.features.aura.warp.TurnsConnection;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import fun.Yanderov.utils.interactions.inv.InventoryFlowManager;
import fun.Yanderov.utils.interactions.inv.InventoryTask;
import fun.Yanderov.utils.interactions.simulate.PlayerSimulation;
import fun.Yanderov.utils.math.calc.Calculate;
import fun.Yanderov.utils.math.projection.Projection;
import fun.Yanderov.utils.math.script.Script;
import fun.Yanderov.utils.math.task.TaskPriority;
import fun.Yanderov.utils.math.time.StopWatch;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import net.minecraft.class_10142;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1542;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1792;
import net.minecraft.class_1796;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1887;
import net.minecraft.class_1890;
import net.minecraft.class_1893;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2637;
import net.minecraft.class_2653;
import net.minecraft.class_2656;
import net.minecraft.class_2680;
import net.minecraft.class_2775;
import net.minecraft.class_2824;
import net.minecraft.class_2828;
import net.minecraft.class_286;
import net.minecraft.class_2868;
import net.minecraft.class_287;
import net.minecraft.class_2886;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_3675;
import net.minecraft.class_3944;
import net.minecraft.class_3988;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_476;
import net.minecraft.class_5250;
import net.minecraft.class_6880;
import net.minecraft.class_7439;
import net.minecraft.class_7924;
import net.minecraft.class_9279;
import net.minecraft.class_9288;
import net.minecraft.class_9334;
import net.minecraft.class_293.class_5596;
import org.apache.commons.lang3.StringUtils;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class ServerHelper extends Module {
   private final Map blockStateMap = new HashMap();
   private final List serverEvents = new ArrayList();
   private final List structures = new ArrayList();
   private final List keyBindings = new ArrayList();
   private final MultiPoint pointFinder = new MultiPoint();
   private final StopWatch itemsWatch = new StopWatch();
   private final StopWatch shulkerWatch = new StopWatch();
   private final StopWatch repairWatch = new StopWatch();
   private final Script script = new Script();
   private final Script script2 = new Script();
   private UUID entityUUID;
   private final Map stacks = new HashMap();
   private final SelectSetting mode = (new SelectSetting("Ð¢Ð¸Ð¿ ÑÐµÑ€Ð²ÐµÑ€Ð°", "ÐŸÐ¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ Ð²Ñ‹Ð±Ñ€Ð°Ñ‚ÑŒ Ñ‚Ð¸Ð¿ ÑÐµÑ€Ð²ÐµÑ€Ð°")).value("ReallyWorld", "HolyWorld", "FunTime", "AresMine", "FunSky HVH").selected("FunTime");
   private final BooleanSetting autoLootSetting = (new BooleanSetting("ÐÐ²Ñ‚Ð¾ Ð»ÑƒÑ‚", "ÐšÑ€Ð°Ð¶Ð° Ð»ÑƒÑ‚Ð° Ñ Ð±Ð¾Ñ‚Ð¾Ð² Ð½Ð° Ð¸Ð²ÐµÐ½Ñ‚Ðµ")).setValue(true).visible(() -> this.mode.isSelected("HolyWorld"));
   private final BooleanSetting autoShulkerSetting = (new BooleanSetting("ÐÐ²Ñ‚Ð¾ ÑˆÐ°Ð»ÐºÐµÑ€", "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ ÐºÐ»Ð°Ð´ÐµÑ‚ Ð»ÑƒÑ‚ Ð² ÑˆÐ°Ð»ÐºÐµÑ€")).setValue(true).visible(() -> this.mode.isSelected("HolyWorld"));
   private final BooleanSetting autoRepairSetting = (new BooleanSetting("ÐÐ²Ñ‚Ð¾ Ñ€ÐµÐ¼Ð¾Ð½Ñ‚", "ÐÐ²Ñ‚Ð¾ Ñ€ÐµÐ¼Ð¾Ð½Ñ‚Ð¸Ñ€ÑƒÐµÑ‚ Ð±Ñ€Ð¾Ð½ÑŽ Ð¿ÑƒÐ·Ñ‹Ñ€ÐµÐ¼ Ð¾Ð¿Ñ‹Ñ‚Ð° Ð¿Ñ€Ð¸ Ð½Ð¸Ð·ÐºÐ¾Ð¹ Ð¿Ñ€Ð¾Ñ‡Ð½Ð¾ÑÑ‚Ð¸")).setValue(true).visible(() -> this.mode.isSelected("HolyWorld"));
   private final BooleanSetting consumablesSetting = (new BooleanSetting("Ð¢Ð°Ð¹Ð¼ÐµÑ€ Ñ€Ð°ÑÑ…Ð¾Ð´Ð½Ð¸ÐºÐ¾Ð²", "ÐžÑ‚Ð¾Ð±Ñ€Ð°Ð¶Ð°ÐµÑ‚ Ð²Ñ€ÐµÐ¼Ñ Ð´Ð¾ Ð¾ÐºÐ¾Ð½Ñ‡Ð°Ð½Ð¸Ñ Ñ€Ð°ÑÑ…Ð¾Ð´Ð½Ð¸ÐºÐ¾Ð²")).setValue(true).visible(() -> this.mode.isSelected("FunTime"));
   private final BooleanSetting autoPointSetting = (new BooleanSetting("ÐÐ²Ñ‚Ð¾ Ð¿Ð¾Ð¸Ð½Ñ‚", "ÐžÑ‚Ð¾Ð±Ñ€Ð°Ð¶Ð°ÐµÑ‚ Ð¸Ð½Ñ„Ð¾Ñ€Ð¼Ð°Ñ†Ð¸ÑŽ Ð¾Ð± Ð¸Ð²ÐµÐ½Ñ‚Ðµ")).setValue(true).visible(() -> this.mode.isSelected("FunTime"));
   private final BindSetting verticalClipBind = (new BindSetting("Ð’ÐµÑ€Ñ‚Ð¸ÐºÐ°Ð»ÑŒÐ½Ñ‹Ð¹ ÐºÐ»Ð¸Ð¿", "Ð¢ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚ Ð¿Ð¾ Ð²ÐµÑ€Ñ‚Ð¸ÐºÐ°Ð»Ð¸")).visible(() -> this.mode.isSelected("FunSky HVH"));
   private final SliderSettings verticalClipDistance = (new SliderSettings("Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð²ÐµÑ€Ñ‚. ÐºÐ»Ð¸Ð¿Ð°", "ÐšÐ¾Ð»Ð¸Ñ‡ÐµÑÑ‚Ð²Ð¾ Ð±Ð»Ð¾ÐºÐ¾Ð² Ð´Ð»Ñ Ñ‚ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð° Ð¿Ð¾ Ð²ÐµÑ€Ñ‚Ð¸ÐºÐ°Ð»Ð¸")).setValue(5.0F).range(-70.0F, 70.0F).visible(() -> this.mode.isSelected("FunSky HVH"));
   private final BindSetting horizontalClipBind = (new BindSetting("Ð“Ð¾Ñ€Ð¸Ð·Ð¾Ð½Ñ‚Ð°Ð»ÑŒÐ½Ñ‹Ð¹ ÐºÐ»Ð¸Ð¿", "Ð¢ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚ Ð¿Ð¾ Ð³Ð¾Ñ€Ð¸Ð·Ð¾Ð½Ñ‚Ð°Ð»Ð¸")).visible(() -> this.mode.isSelected("FunSky HVH"));
   private final SliderSettings horizontalClipDistance = (new SliderSettings("Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð³Ð¾Ñ€. ÐºÐ»Ð¸Ð¿Ð°", "ÐšÐ¾Ð»Ð¸Ñ‡ÐµÑÑ‚Ð²Ð¾ Ð±Ð»Ð¾ÐºÐ¾Ð² Ð´Ð»Ñ Ñ‚ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð° Ð¿Ð¾ Ð³Ð¾Ñ€Ð¸Ð·Ð¾Ð½Ñ‚Ð°Ð»Ð¸")).setValue(5.0F).range(-70.0F, 70.0F).visible(() -> this.mode.isSelected("FunSky HVH"));
   private final BindSetting antiTPInfluenceBind = (new BindSetting("AntiTPInfluence", "ÐÐºÑ‚Ð¸Ð²Ð°Ñ†Ð¸Ñ AntiTPInfluence")).visible(() -> this.mode.isSelected("FunSky HVH"));
   private final SliderSettings antiTPInfluenceVerticalClip = (new SliderSettings("Ð’ÐµÑ€Ñ‚Ð¸ÐºÐ°Ð»ÑŒÐ½Ñ‹Ð¹ ÐºÐ»Ð¸Ð¿ AntiTP", "Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð²ÐµÑ€Ñ‚. ÐºÐ»Ð¸Ð¿Ð° Ð´Ð»Ñ AntiTPInfluence")).setValue(-30.0F).range(-60.0F, 0.0F).visible(() -> this.mode.isSelected("FunSky HVH"));
   private final SliderSettings antiTPInfluenceHorizontalClipNegative = (new SliderSettings("Ð“Ð¾Ñ€Ð¸Ð·Ð¾Ð½Ñ‚Ð°Ð»ÑŒÐ½Ñ‹Ð¹ ÐºÐ»Ð¸Ð¿ AntiTP (-)", "ÐœÐ°ÐºÑ. Ð¾Ñ‚Ñ€Ð¸Ñ†Ð°Ñ‚ÐµÐ»ÑŒÐ½Ð°Ñ Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð´Ð»Ñ AntiTPInfluence")).setValue(-30.0F).range(-60.0F, 0.0F).visible(() -> this.mode.isSelected("FunSky HVH"));
   private final SliderSettings antiTPInfluenceHorizontalClipPositive = (new SliderSettings("Ð“Ð¾Ñ€Ð¸Ð·Ð¾Ð½Ñ‚Ð°Ð»ÑŒÐ½Ñ‹Ð¹ ÐºÐ»Ð¸Ð¿ AntiTP (+)", "ÐœÐ°ÐºÑ. Ð¿Ð¾Ð»Ð¾Ð¶Ð¸Ñ‚ÐµÐ»ÑŒÐ½Ð°Ñ Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð´Ð»Ñ AntiTPInfluence")).setValue(30.0F).range(0.0F, 60.0F).visible(() -> this.mode.isSelected("FunSky HVH"));
   private final SliderSettings antiTPInfluenceDelay = (new SliderSettings("Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° AntiTP (Ñ‚Ð¸ÐºÐ¸)", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¼ÐµÐ¶Ð´Ñƒ ÐºÐ»Ð¸Ð¿Ð°Ð¼Ð¸ Ð² Ñ‚Ð¸ÐºÐ°Ñ…")).setValue(10.0F).range(1.0F, 100.0F).visible(() -> this.mode.isSelected("`FunSky` HVH"));
   private final BindSetting bedrockClipBind = (new BindSetting("Bedrock Clip", "Ð¢ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚ Ð² Ð±ÐµÐ´Ñ€Ð¾Ðº")).visible(() -> this.mode.isSelected("FunSky HVH"));
   private final List potionQueue = new ArrayList();
   private final StopWatch potionTimer = new StopWatch();
   private final Map itemConfig = new HashMap();
   private final Map itemStates = new HashMap();
   private final Map lastKeyStates = new HashMap();
   private final Map keyPressedThisTick = new HashMap();
   private int originalSlot = -1;
   private int targetSlot = -1;
   private ActionState actionState;
   private long actionTimer;
   private String pendingItemKey;
   private long stopMovementUntil;
   private boolean keysOverridden;
   private boolean wasForwardPressed;
   private boolean wasBackPressed;
   private boolean wasLeftPressed;
   private boolean wasRightPressed;
   private int originalSourceSlot;
   private boolean antiTPInfluenceActive;
   private class_243 antiTPInfluenceInitialPos;
   private StopWatch antiTPInfluenceTimer;
   private boolean bedrockClipActive;

   public static ServerHelper getInstance() {
      return (ServerHelper)Instance.get(ServerHelper.class);
   }

   public ServerHelper() {
      super("Server Assist", "Server Assist", ModuleCategory.MISC);
      this.actionState = ServerHelper.ActionState.IDLE;
      this.actionTimer = 0L;
      this.pendingItemKey = null;
      this.stopMovementUntil = 0L;
      this.keysOverridden = false;
      this.originalSourceSlot = -1;
      this.antiTPInfluenceActive = false;
      this.antiTPInfluenceInitialPos = null;
      this.antiTPInfluenceTimer = new StopWatch();
      this.bedrockClipActive = false;
      this.initialize();
   }

   public void initialize() {
      this.setup(new Setting[]{this.mode, this.autoLootSetting, this.consumablesSetting, this.autoPointSetting, this.autoShulkerSetting, this.autoRepairSetting, this.verticalClipBind, this.verticalClipDistance, this.horizontalClipBind, this.horizontalClipDistance, this.antiTPInfluenceBind, this.antiTPInfluenceVerticalClip, this.antiTPInfluenceHorizontalClipNegative, this.antiTPInfluenceHorizontalClipPositive, this.antiTPInfluenceDelay, this.bedrockClipBind});
      this.keyBindings.add(new KeyBind(class_1802.field_8450, (new BindSetting("ÐÐ½Ñ‚Ð¸ Ð¿Ð¾Ð»ÐµÑ‚", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð°Ð½Ñ‚Ð¸ Ð¿Ð¾Ð»ÐµÑ‚Ð°")).visible(() -> this.mode.isSelected("ReallyWorld")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8498, (new BindSetting("Ð¡Ð²Ð¸Ñ‚Ð¾Ðº Ð¾Ð¿Ñ‹Ñ‚Ð°", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° ÑÐ²Ð¸Ñ‚ÐºÐ° Ð¾Ð¿Ñ‹Ñ‚Ð°")).visible(() -> this.mode.isSelected("ReallyWorld")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8662, (new BindSetting("Ð’Ð·Ñ€Ñ‹Ð²Ð½Ð°Ñ Ñ‚Ñ€Ð°Ð¿ÐºÐ°", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð²Ð·Ñ€Ñ‹Ð²Ð½Ð¾Ð¹ Ñ‚Ñ€Ð°Ð¿ÐºÐ¸")).visible(() -> this.mode.isSelected("HolyWorld")), 5.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8882, (new BindSetting("ÐžÐ±Ñ‹Ñ‡Ð½Ð°Ñ Ñ‚Ñ€Ð°Ð¿ÐºÐ°", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð¾Ð±Ñ‹Ñ‡Ð½Ð¾Ð¹ Ñ‚Ñ€Ð°Ð¿ÐºÐ¸")).visible(() -> this.mode.isSelected("HolyWorld")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8137, (new BindSetting("Ð¡Ñ‚Ð°Ð½", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° ÑÑ‚Ð°Ð½Ð°")).visible(() -> this.mode.isSelected("HolyWorld")), 30.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8814, (new BindSetting("Ð’Ð·Ñ€Ñ‹Ð²Ð½Ð°Ñ ÑˆÑ‚ÑƒÑ‡ÐºÐ°", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð²Ð·Ñ€Ñ‹Ð²Ð½Ð¾Ð¹ ÑˆÑ‚ÑƒÑ‡ÐºÐ¸")).visible(() -> this.mode.isSelected("HolyWorld")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8543, (new BindSetting("Ð¡Ð½ÐµÐ¶Ð¾Ðº Ð·Ð°Ð¼Ð¾Ñ€Ð¾Ð·ÐºÐ°", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° ÑÐ½ÐµÐ¶ÐºÐ°")).visible(() -> this.mode.isSelected("HolyWorld") || this.mode.isSelected("FunTime") || this.mode.isSelected("FunSky HVH")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8614, (new BindSetting("Ð‘Ð¾Ð¶ÑŒÑ Ð°ÑƒÑ€Ð°", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð±Ð¾Ð¶ÑŒÐµÐ¹ Ð°ÑƒÑ€Ñ‹")).visible(() -> this.mode.isSelected("FunTime") || this.mode.isSelected("FunSky HVH")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_22021, (new BindSetting("Ð¢Ñ€Ð°Ð¿ÐºÐ°", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ñ‚Ñ€Ð°Ð¿ÐºÐ¸")).visible(() -> this.mode.isSelected("FunTime") || this.mode.isSelected("FunSky HVH")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8551, (new BindSetting("ÐŸÐ»Ð°ÑÑ‚", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð¿Ð»Ð°ÑÑ‚Ð°")).visible(() -> this.mode.isSelected("FunTime") || this.mode.isSelected("FunSky HVH")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8479, (new BindSetting("Ð¯Ð²Ð½Ð°Ñ Ð¿Ñ‹Ð»ÑŒ", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° ÑÐ²Ð½Ð¾Ð¹ Ð¿Ñ‹Ð»Ð¸")).visible(() -> this.mode.isSelected("FunTime") || this.mode.isSelected("FunSky HVH")), 10.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8814, (new BindSetting("ÐžÐ³Ð½ÐµÐ½Ð½Ñ‹Ð¹ ÑÐ¼ÐµÑ€Ñ‡", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð¾Ð³Ð½ÐµÐ½Ð½Ð¾Ð³Ð¾ ÑÐ¼ÐµÑ€Ñ‡Ð°")).visible(() -> this.mode.isSelected("FunTime") || this.mode.isSelected("FunSky HVH")), 10.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8449, (new BindSetting("Ð”ÐµÐ·Ð¾Ñ€Ð¸ÐµÐ½Ñ‚Ð°Ñ†Ð¸Ñ", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð´ÐµÐ·Ð¾Ñ€Ð¸ÐµÐ½Ñ‚Ð°Ñ†Ð¸Ð¸")).visible(() -> this.mode.isSelected("FunTime") || this.mode.isSelected("FunSky HVH")), 10.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8693, (new BindSetting("Ð¡Ð²ÐµÑ‚Ð¸Ð»ÑŒÐ½Ð¸Ðº Ð”Ð¶ÐµÐºÐ°", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° ÑÐ²ÐµÑ‚Ð¸Ð»ÑŒÐ½Ð¸ÐºÐ° Ð”Ð¶ÐµÐºÐ°")).visible(() -> this.mode.isSelected("HolyWorld")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8287, (new BindSetting("ÐŸÑƒÐ·Ñ‹Ñ€ÑŒ Ð¾Ð¿Ñ‹Ñ‚Ð°", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð¿ÑƒÐ·Ñ‹Ñ€Ñ Ð¾Ð¿Ñ‹Ñ‚Ð°")).visible(() -> this.mode.isSelected("HolyWorld")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8520, (new BindSetting("Ð ÑŽÐºÐ·Ð°Ðº 1 ÑƒÑ€Ð¾Ð²Ð½Ñ", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ñ€ÑŽÐºÐ·Ð°ÐºÐ° 1 ÑƒÑ€Ð¾Ð²Ð½Ñ")).visible(() -> this.mode.isSelected("HolyWorld")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8350, (new BindSetting("Ð ÑŽÐºÐ·Ð°Ðº 2 ÑƒÑ€Ð¾Ð²Ð½Ñ", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ñ€ÑŽÐºÐ·Ð°ÐºÐ° 2 ÑƒÑ€Ð¾Ð²Ð½Ñ")).visible(() -> this.mode.isSelected("HolyWorld")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8676, (new BindSetting("Ð ÑŽÐºÐ·Ð°Ðº 3 ÑƒÑ€Ð¾Ð²Ð½Ñ", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ñ€ÑŽÐºÐ·Ð°ÐºÐ° 3 ÑƒÑ€Ð¾Ð²Ð½Ñ")).visible(() -> this.mode.isSelected("HolyWorld")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8520, (new BindSetting("Ð ÑŽÐºÐ·Ð°Ðº 4 ÑƒÑ€Ð¾Ð²Ð½Ñ", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ñ€ÑŽÐºÐ·Ð°ÐºÐ° 4 ÑƒÑ€Ð¾Ð²Ð½Ñ")).visible(() -> this.mode.isSelected("HolyWorld")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8436, (new BindSetting("Ð—ÐµÐ»ÑŒÐµ Ð¾Ñ‚Ñ€Ñ‹Ð¶ÐºÐ¸", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð·ÐµÐ»ÑŒÑ Ð¾Ñ‚Ñ€Ñ‹Ð¶ÐºÐ¸")).visible(() -> this.mode.isSelected("FunTime") || this.mode.isSelected("FunSky HVH")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8436, (new BindSetting("Ð—ÐµÐ»ÑŒÐµ ÑÐµÑ€Ð½Ð¾Ð¹ ÐºÐ¸ÑÐ»Ð¾Ñ‚Ñ‹", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð·ÐµÐ»ÑŒÑ ÑÐµÑ€Ð½Ð¾Ð¹ ÐºÐ¸ÑÐ»Ð¾Ñ‚Ñ‹")).visible(() -> this.mode.isSelected("FunTime") || this.mode.isSelected("FunSky HVH")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8436, (new BindSetting("Ð—ÐµÐ»ÑŒÐµ Ð²ÑÐ¿Ñ‹ÑˆÐºÐ¸", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð·ÐµÐ»ÑŒÑ Ð²ÑÐ¿Ñ‹ÑˆÐºÐ¸")).visible(() -> this.mode.isSelected("FunTime") || this.mode.isSelected("FunSky HVH")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8436, (new BindSetting("Ð—ÐµÐ»ÑŒÐµ Ð¼Ð¾Ñ‡Ð¸ Ð¤Ð»ÐµÑˆÐ°", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð·ÐµÐ»ÑŒÑ Ð¼Ð¾Ñ‡Ð¸ Ð¤Ð»ÐµÑˆÐ°")).visible(() -> this.mode.isSelected("FunTime") || this.mode.isSelected("FunSky HVH")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8436, (new BindSetting("Ð—ÐµÐ»ÑŒÐµ Ð¿Ð¾Ð±ÐµÐ´Ð¸Ñ‚ÐµÐ»Ñ", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð·ÐµÐ»ÑŒÑ Ð¿Ð¾Ð±ÐµÐ´Ð¸Ñ‚ÐµÐ»Ñ")).visible(() -> this.mode.isSelected("FunTime") || this.mode.isSelected("FunSky HVH")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8436, (new BindSetting("Ð—ÐµÐ»ÑŒÐµ Ð°Ð³ÐµÐ½Ñ‚Ð°", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð·ÐµÐ»ÑŒÑ Ð°Ð³ÐµÐ½Ñ‚Ð°")).visible(() -> this.mode.isSelected("FunTime") || this.mode.isSelected("FunSky HVH")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8436, (new BindSetting("Ð—ÐµÐ»ÑŒÐµ Ð¼ÐµÐ´Ð¸ÐºÐ°", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð·ÐµÐ»ÑŒÑ Ð¼ÐµÐ´Ð¸ÐºÐ°")).visible(() -> this.mode.isSelected("FunTime") || this.mode.isSelected("FunSky HVH")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8436, (new BindSetting("Ð—ÐµÐ»ÑŒÐµ ÐºÐ¸Ð»Ð»ÐµÑ€Ð°", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð·ÐµÐ»ÑŒÑ ÐºÐ¸Ð»Ð»ÐµÑ€Ð°")).visible(() -> this.mode.isSelected("FunTime") || this.mode.isSelected("FunSky HVH")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8399, (new BindSetting("Ares: ÐÑ€Ð±Ð°Ð»ÐµÑ‚", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð°Ñ€Ð±Ð°Ð»ÐµÑ‚Ð° Ares")).visible(() -> this.mode.isSelected("AresMine")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8407, (new BindSetting("Ares: Ð¡Ð²Ð¸Ñ‚Ð¾Ðº Ð½ÐµÑƒÑÐ·Ð²Ð¸Ð¼Ð¾ÑÑ‚Ð¸", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° ÑÐ²Ð¸Ñ‚ÐºÐ° Ð½ÐµÑƒÑÐ·Ð²Ð¸Ð¼Ð¾ÑÑ‚Ð¸ Ares")).visible(() -> this.mode.isSelected("AresMine")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8407, (new BindSetting("Ares: Ð¡Ð²Ð¸Ñ‚Ð¾Ðº Ð¼ÐµÑ‚ÐµÐ¾Ñ€Ð°", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° ÑÐ²Ð¸Ñ‚ÐºÐ° Ð¼ÐµÑ‚ÐµÐ¾Ñ€Ð° Ares")).visible(() -> this.mode.isSelected("AresMine")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8407, (new BindSetting("Ares: Ð¡Ð²Ð¸Ñ‚Ð¾Ðº Ð·Ð°Ð¼ÐµÐ´Ð»ÐµÐ½Ð¸Ñ", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° ÑÐ²Ð¸Ñ‚ÐºÐ° Ð·Ð°Ð¼ÐµÐ´Ð»ÐµÐ½Ð¸Ñ Ares")).visible(() -> this.mode.isSelected("AresMine")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8407, (new BindSetting("Ares: ÐÐµÐ±ÐµÑÐ½Ð°Ñ Ð½Ð¸Ñ‚ÑŒ", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð½ÐµÐ±ÐµÑÐ½Ð¾Ð¹ Ð½Ð¸Ñ‚Ð¸ Ares")).visible(() -> this.mode.isSelected("AresMine")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8407, (new BindSetting("Ares: Ð‘Ð»Ð°Ð³Ð¾ÑÐ»Ð¾Ð²ÐµÐ½Ð¸Ðµ", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð±Ð»Ð°Ð³Ð¾ÑÐ»Ð¾Ð²ÐµÐ½Ð¸Ñ Ares")).visible(() -> this.mode.isSelected("AresMine")), 0.0F));
      this.keyBindings.add(new KeyBind(class_1802.field_8475, (new BindSetting("Ares: ÐœÐ¾Ð»Ð¾Ñ‚ Ñ‚Ð¾Ñ€Ð°", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð¼Ð¾Ð»Ð¾Ñ‚Ð° Ñ‚Ð¾Ñ€Ð° Ares")).visible(() -> this.mode.isSelected("AresMine")), 0.0F));
      this.keyBindings.forEach((bind) -> this.setup(new Setting[]{bind.setting}));
      this.itemConfig.put("disorientation", new ItemInfo("Ð´ÐµÐ·Ð¾Ñ€Ð¸ÐµÐ½Ñ‚Ð°Ñ†Ð¸Ñ", class_1802.field_8449, "Ð”ÐµÐ·Ð¾Ñ€Ð¸ÐµÐ½Ñ‚Ð°Ñ†Ð¸Ñ"));
      this.itemConfig.put("sugar", new ItemInfo("ÑÐ²Ð½Ð°Ñ", class_1802.field_8479, "Ð¯Ð²Ð½Ð°Ñ Ð¿Ñ‹Ð»ÑŒ"));
      this.itemConfig.put("bojaura", new ItemInfo("Ð±Ð¾Ð¶ÑŒÑ Ð°ÑƒÑ€Ð°", class_1802.field_8614, "Ð‘Ð¾Ð¶ÑŒÑ Ð°ÑƒÑ€Ð°"));
      this.itemConfig.put("snow", new ItemInfo("Ð¡Ð½ÐµÐ¶Ð¾Ðº Ð·Ð°Ð¼Ð¾Ñ€Ð¾Ð·ÐºÐ°", class_1802.field_8543, "Ð¡Ð½ÐµÐ¶Ð¾Ðº Ð·Ð°Ð¼Ð¾Ñ€Ð¾Ð·ÐºÐ°"));
      this.itemConfig.put("plast", new ItemInfo("Ð¿Ð»Ð°ÑÑ‚", class_1802.field_8551, "ÐŸÐ»Ð°ÑÑ‚"));
      this.itemConfig.put("trap", new ItemInfo("Ñ‚Ñ€Ð°Ð¿ÐºÐ°", class_1802.field_22021, "Ð¢Ñ€Ð°Ð¿ÐºÐ°"));
      this.itemConfig.put("fireSwirl", new ItemInfo("Ð¾Ð³Ð½ÐµÐ½Ð½Ñ‹Ð¹ ÑÐ¼ÐµÑ€Ñ‡", class_1802.field_8814, "ÐžÐ³Ð½ÐµÐ½Ð½Ñ‹Ð¹ ÑÐ¼ÐµÑ€Ñ‡"));
      this.itemConfig.put("otriga", new ItemInfo("Ð¾Ñ‚Ñ€Ñ‹Ð¶ÐºÐ¸", class_1802.field_8436, "Ð—ÐµÐ»ÑŒÐµ Ð¾Ñ‚Ñ€Ñ‹Ð¶ÐºÐ¸"));
      this.itemConfig.put("serka", new ItemInfo("ÑÐµÑ€Ð½Ð°Ñ", class_1802.field_8436, "Ð—ÐµÐ»ÑŒÐµ ÑÐµÑ€Ð½Ð¾Ð¹ ÐºÐ¸ÑÐ»Ð¾Ñ‚Ñ‹"));
      this.itemConfig.put("vspihka", new ItemInfo("Ð²ÑÐ¿Ñ‹ÑˆÐºÐ°", class_1802.field_8436, "Ð—ÐµÐ»ÑŒÐµ Ð²ÑÐ¿Ñ‹ÑˆÐºÐ¸"));
      this.itemConfig.put("mochaflesha", new ItemInfo("Ð¼Ð¾Ñ‡Ð° Ñ„Ð»ÐµÑˆÐ°", class_1802.field_8436, "Ð—ÐµÐ»ÑŒÐµ Ð¼Ð¾Ñ‡Ð¸ Ð¤Ð»ÐµÑˆÐ°"));
      this.itemConfig.put("pobedilka", new ItemInfo("Ð¿Ð¾Ð±ÐµÐ´Ð¸Ñ‚ÐµÐ»Ñ", class_1802.field_8436, "Ð—ÐµÐ»ÑŒÐµ Ð¿Ð¾Ð±ÐµÐ´Ð¸Ñ‚ÐµÐ»Ñ"));
      this.itemConfig.put("agent", new ItemInfo("Ð°Ð³ÐµÐ½Ñ‚Ð°", class_1802.field_8436, "Ð—ÐµÐ»ÑŒÐµ Ð°Ð³ÐµÐ½Ñ‚Ð°"));
      this.itemConfig.put("medik", new ItemInfo("Ð¼ÐµÐ´Ð¸ÐºÐ°", class_1802.field_8436, "Ð—ÐµÐ»ÑŒÐµ Ð¼ÐµÐ´Ð¸ÐºÐ°"));
      this.itemConfig.put("killer", new ItemInfo("ÐºÐ¸Ð»Ð»ÐµÑ€Ð°", class_1802.field_8436, "Ð—ÐµÐ»ÑŒÐµ ÐºÐ¸Ð»Ð»ÐµÑ€Ð°"));
      this.itemConfig.put("antiflight", new ItemInfo("Ð°Ð½Ñ‚Ð¸ Ð¿Ð¾Ð»ÐµÑ‚", class_1802.field_8450, "ÐÐ½Ñ‚Ð¸ Ð¿Ð¾Ð»ÐµÑ‚"));
      this.itemConfig.put("expscroll", new ItemInfo("ÑÐ²Ð¸Ñ‚Ð¾Ðº Ð¾Ð¿Ñ‹Ñ‚Ð°", class_1802.field_8498, "Ð¡Ð²Ð¸Ñ‚Ð¾Ðº Ð¾Ð¿Ñ‹Ñ‚Ð°"));
      this.itemConfig.put("dtrap", new ItemInfo("Ð²Ð·Ñ€Ñ‹Ð²Ð½Ð°Ñ Ñ‚Ñ€Ð°Ð¿ÐºÐ°", class_1802.field_8662, "Ð’Ð·Ñ€Ñ‹Ð²Ð½Ð°Ñ Ñ‚Ñ€Ð°Ð¿ÐºÐ°"));
      this.itemConfig.put("trap_holy", new ItemInfo("Ñ‚Ñ€Ð°Ð¿ÐºÐ°", class_1802.field_8882, "ÐžÐ±Ñ‹Ñ‡Ð½Ð°Ñ Ñ‚Ñ€Ð°Ð¿ÐºÐ°"));
      this.itemConfig.put("stan", new ItemInfo("ÑÑ‚Ð°Ð½", class_1802.field_8137, "Ð¡Ñ‚Ð°Ð½"));
      this.itemConfig.put("ditem", new ItemInfo("Ð²Ð·Ñ€Ñ‹Ð²Ð½Ð°Ñ ÑˆÑ‚ÑƒÑ‡ÐºÐ°", class_1802.field_8814, "Ð’Ð·Ñ€Ñ‹Ð²Ð½Ð°Ñ ÑˆÑ‚ÑƒÑ‡ÐºÐ°"));
      this.itemConfig.put("tikva", new ItemInfo("ÑÐ²ÐµÑ‚Ð¸Ð»ÑŒÐ½Ð¸Ðº Ð´Ð¶ÐµÐ¹ÐºÐ°", class_1802.field_8693, "Ð¡Ð²ÐµÑ‚Ð¸Ð»ÑŒÐ½Ð¸Ðº Ð”Ð¶ÐµÐºÐ°"));
      this.itemConfig.put("exp", new ItemInfo("Ð¿ÑƒÐ·Ñ‹Ñ€ÑŒ Ð¾Ð¿Ñ‹Ñ‚Ð°", class_1802.field_8287, "ÐŸÑƒÐ·Ñ‹Ñ€ÑŒ Ð¾Ð¿Ñ‹Ñ‚Ð°"));
      this.itemConfig.put("shulker1", new ItemInfo("Ñ€ÑŽÐºÐ·Ð°Ðº (i ÑƒÑ€Ð¾Ð²ÐµÐ½ÑŒ)", class_1802.field_8520, "Ð ÑŽÐºÐ·Ð°Ðº 1 ÑƒÑ€Ð¾Ð²Ð½Ñ"));
      this.itemConfig.put("shulker2", new ItemInfo("Ñ€ÑŽÐºÐ·Ð°Ðº (ii ÑƒÑ€Ð¾Ð²ÐµÐ½ÑŒ)", class_1802.field_8350, "Ð ÑŽÐºÐ·Ð°Ðº 2 ÑƒÑ€Ð¾Ð²Ð½Ñ"));
      this.itemConfig.put("shulker3", new ItemInfo("Ñ€ÑŽÐºÐ·Ð°Ðº (iii ÑƒÑ€Ð¾Ð²ÐµÐ½ÑŒ)", class_1802.field_8676, "Ð ÑŽÐºÐ·Ð°Ðº 3 ÑƒÑ€Ð¾Ð²Ð½Ñ"));
      this.itemConfig.put("shulker4", new ItemInfo("Ñ€ÑŽÐºÐ·Ð°Ðº (iv ÑƒÑ€Ð¾Ð²ÐµÐ½ÑŒ)", class_1802.field_8520, "Ð ÑŽÐºÐ·Ð°Ðº 4 ÑƒÑ€Ð¾Ð²Ð½Ñ"));
      this.itemConfig.put("ares_crossbow", new ItemInfo("Ð°Ñ€Ð±Ð°Ð»ÐµÑ‚", class_1802.field_8399, "Ares: ÐÑ€Ð±Ð°Ð»ÐµÑ‚"));
      this.itemConfig.put("ares_invuln", new ItemInfo("Ð½ÐµÑƒÑÐ·Ð²", class_1802.field_8407, "Ares: Ð¡Ð²Ð¸Ñ‚Ð¾Ðº Ð½ÐµÑƒÑÐ·Ð²Ð¸Ð¼Ð¾ÑÑ‚Ð¸"));
      this.itemConfig.put("ares_meteor", new ItemInfo("Ð¼ÐµÑ‚ÐµÐ¾Ñ€", class_1802.field_8407, "Ares: Ð¡Ð²Ð¸Ñ‚Ð¾Ðº Ð¼ÐµÑ‚ÐµÐ¾Ñ€Ð°"));
      this.itemConfig.put("ares_slow", new ItemInfo("Ð·Ð°Ð¼ÐµÐ´Ð»", class_1802.field_8407, "Ares: Ð¡Ð²Ð¸Ñ‚Ð¾Ðº Ð·Ð°Ð¼ÐµÐ´Ð»ÐµÐ½Ð¸Ñ"));
      this.itemConfig.put("ares_thread", new ItemInfo("Ð½Ð¸Ñ‚ÑŒ", class_1802.field_8407, "Ares: ÐÐµÐ±ÐµÑÐ½Ð°Ñ Ð½Ð¸Ñ‚ÑŒ"));
      this.itemConfig.put("ares_bless", new ItemInfo("Ð±Ð»Ð°Ð³Ð¾ÑÐ»Ð¾Ð²", class_1802.field_8407, "Ares: Ð‘Ð»Ð°Ð³Ð¾ÑÐ»Ð¾Ð²ÐµÐ½Ð¸Ðµ"));
      this.itemConfig.put("ares_thor", new ItemInfo("Ð¼Ð¾Ð»Ð¾Ñ‚", class_1802.field_8475, "Ares: ÐœÐ¾Ð»Ð¾Ñ‚ Ñ‚Ð¾Ñ€Ð°"));
      this.itemConfig.keySet().forEach((key) -> {
         this.itemStates.put(key, false);
         this.lastKeyStates.put(key, false);
         this.keyPressedThisTick.put(key, false);
      });
   }

   public void activate() {
      this.script2.cleanup();
      this.stacks.clear();
      this.potionQueue.clear();
      this.potionTimer.reset();
      this.itemStates.replaceAll((k, v) -> false);
      this.lastKeyStates.replaceAll((k, v) -> false);
      this.keyPressedThisTick.replaceAll((k, v) -> false);
      this.actionState = ServerHelper.ActionState.IDLE;
      this.originalSlot = -1;
      this.targetSlot = -1;
      this.originalSourceSlot = -1;
      this.pendingItemKey = null;
      this.stopMovementUntil = 0L;
      this.keysOverridden = false;
      this.antiTPInfluenceActive = false;
      this.antiTPInfluenceInitialPos = null;
      this.bedrockClipActive = false;
   }

   public void deactivate() {
      this.itemStates.replaceAll((k, v) -> false);
      this.lastKeyStates.replaceAll((k, v) -> false);
      this.keyPressedThisTick.replaceAll((k, v) -> false);
      this.potionQueue.clear();
      this.potionTimer.reset();
      this.actionState = ServerHelper.ActionState.IDLE;
      this.originalSlot = -1;
      this.targetSlot = -1;
      this.originalSourceSlot = -1;
      this.pendingItemKey = null;
      this.stopMovementUntil = 0L;
      if (this.keysOverridden) {
         mc.field_1690.field_1894.method_23481(false);
         mc.field_1690.field_1881.method_23481(false);
         mc.field_1690.field_1913.method_23481(false);
         mc.field_1690.field_1849.method_23481(false);
      }

      this.keysOverridden = false;
      this.antiTPInfluenceActive = false;
      this.antiTPInfluenceInitialPos = null;
      this.bedrockClipActive = false;
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (!PlayerInteractionHelper.nullCheck()) {
         class_2596 var10000 = e.getPacket();
         Objects.requireNonNull(var10000);
         class_2596 var2 = var10000;
         byte var3 = 0;

         while(true) {
            //$FF: var3->value
            //0->net/minecraft/class_2775
            //1->net/minecraft/class_2653
            //2->net/minecraft/class_2637
            //3->net/minecraft/class_7439
            //4->net/minecraft/class_7439
            //5->net/minecraft/class_3944
            switch (var2.typeSwitch<invokedynamic>(var2, var3)) {
               case 0:
                  class_2775 item = (class_2775)var2;
                  if (this.autoShulkerSetting.isValue() && this.autoShulkerSetting.isVisible() && item.method_11912() == mc.field_1724.method_5628()) {
                     class_1297 var20 = mc.field_1687.method_8469(item.method_11915());
                     if (var20 instanceof class_1542) {
                        class_1542 entity = (class_1542)var20;
                        class_1799 stack = entity.method_6983();
                        if (stack.method_57824(class_9334.field_49622) == null) {
                           this.stacks.put(-Calculate.getRandom(1, 999999999), stack.method_7909());
                           this.shulkerWatch.reset();
                        }

                        return;
                     }
                  }

                  var3 = 1;
                  break;
               case 1:
                  class_2653 slot = (class_2653)var2;
                  if (slot.method_11452() == 0) {
                     class_1792 item = slot.method_11449().method_7909();
                     this.stacks.entrySet().stream().filter((entry) -> (Integer)entry.getKey() < 0 && ((class_1792)entry.getValue()).equals(item)).findFirst().ifPresent((entry) -> {
                        this.stacks.put(slot.method_11450() + 18, item);
                        this.stacks.remove(entry.getKey());
                     });
                  }

                  return;
               case 2:
                  class_2637 chunkDelta = (class_2637)var2;
                  if (this.consumablesSetting.isValue() && this.consumablesSetting.isVisible()) {
                     chunkDelta.method_30621((pos, state) -> this.blockStateMap.put(pos.method_10069(0, 0, 0), state));
                     this.script.addTickStep(0, () -> chunkDelta.method_30621((pos, state) -> {
                           class_243 vec = pos.method_10069(0, 0, 0).method_46558();
                           if (this.blockStateMap.size() > 50 && this.blockStateMap.size() < 600) {
                              if (this.isTrap(pos.method_10086(2))) {
                                 this.addStructure(class_1802.field_22021, vec, (double)(System.currentTimeMillis() + 15000L));
                              } else if (this.isBigTrap(pos.method_10086(3))) {
                                 this.addStructure(class_1802.field_22021, vec, (double)(System.currentTimeMillis() + 30000L));
                              }
                           }

                        }));
                     return;
                  }

                  var3 = 3;
                  break;
               case 3:
                  class_7439 gameMessage = (class_7439)var2;
                  if (this.autoPointSetting.isValue() && this.autoPointSetting.isVisible()) {
                     class_2561 content = gameMessage.comp_763();
                     String contentString = content.toString();
                     String message = content.getString();
                     String name = StringUtils.substringBetween(message, "||| [", "] ");
                     if (name != null) {
                        String position = StringUtils.substringBetween(contentString, "value='/gps ", "'");
                        String lvl = StringUtils.substringBetween(message, "Ð£Ñ€Ð¾Ð²ÐµÐ½ÑŒ Ð»ÑƒÑ‚Ð°: ", "\n â•‘");
                        String owner = StringUtils.substringBetween(message, "ÐŸÑ€Ð¸Ð·Ð²Ð°Ð½ Ð¸Ð³Ñ€Ð¾ÐºÐ¾Ð¼: ", "\n â•‘");
                        if (position != null) {
                           String[] pose = position.split(" ");
                           class_243 center = class_2338.method_49637((double)Integer.parseInt(pose[0]), (double)Integer.parseInt(pose[1]), (double)Integer.parseInt(pose[2])).method_46558();
                           switch (name) {
                              case "ÐœÐ¸ÑÑ‚Ð¸Ñ‡ÐµÑÐºÐ¸Ð¹ ÑÑƒÐ½Ð´ÑƒÐº":
                                 this.addEvent(name, lvl, owner, center, "overworld", 300, 0);
                                 return;
                              case "Ð’ÑƒÐ»ÐºÐ°Ð½":
                                 this.addEvent(name, lvl, owner, center, "overworld", 300, 120);
                                 return;
                              case "ÐœÐµÑ‚ÐµÐ¾Ñ€Ð¸Ñ‚Ð½Ñ‹Ð¹ Ð´Ð¾Ð¶Ð´ÑŒ":
                              case "ÐœÐ°ÑÐº ÑƒÐ±Ð¸Ð¹Ñ†Ð°":
                              case "ÐœÐ¸ÑÑ‚Ð¸Ñ‡ÐµÑÐºÐ¸Ð¹ ÐÐ»Ñ‚Ð°Ñ€ÑŒ":
                                 this.addEvent(name, lvl, owner, center, "overworld", 360, 0);
                                 return;
                              case "Ð—Ð°Ð³Ð°Ð´Ð¾Ñ‡Ð½Ñ‹Ð¹ Ð¼Ð°ÑÐº":
                                 this.addEvent(name, lvl, owner, center, "overworld", 60, 180);
                           }
                        } else {
                           switch (name) {
                              case "Ð¡ÑƒÐ½Ð´ÑƒÐº ÑÐ¼ÐµÑ€Ñ‚Ð¸":
                                 this.addEvent(name, lvl, owner, class_2338.method_49637((double)-155.0F, (double)64.0F, (double)205.0F).method_46558(), "lobby", 300, 0);
                                 return;
                              case "ÐÐ´ÑÐºÐ°Ñ Ñ€ÐµÐ·Ð½Ñ":
                                 this.addEvent(name, lvl, owner, class_2338.method_49637((double)48.0F, (double)87.0F, (double)73.0F).method_46558(), "lobby", 180, 120);
                           }
                        }

                        return;
                     }

                     return;
                  }

                  var3 = 4;
                  break;
               case 4:
                  class_7439 gameMessage = (class_7439)var2;
                  String message = gameMessage.comp_763().getString();
                  if (message.contains("â–¶ ÐŸÐ¾Ð²Ñ‚Ð¾Ñ€Ð½Ð¾ Ð°ÐºÑ‚Ð¸Ð²Ð¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒ ÐŸÑƒÐ·Ñ‹Ñ€ÑŒ Ð¾Ð¿Ñ‹Ñ‚Ð° Ð²Ð¾Ð·Ð¼Ð¾Ð¶Ð½Ð¾ Ñ‡ÐµÑ€ÐµÐ·")) {
                     String subString = StringUtils.substringBetween(message, "Ñ‡ÐµÑ€ÐµÐ· ", " ÑÐµÐºÑƒÐ½Ð´");
                     if (subString != null && !subString.isEmpty()) {
                        int duration = Integer.parseInt(subString) * 20;
                        class_1796 manager = mc.field_1724.method_7357();
                        manager.method_62835(class_1802.field_8287.method_7854(), duration);
                        CoolDowns.getInstance().packet(new PacketEvent(new class_2656(manager.method_62836(class_1802.field_8287.method_7854()), duration), PacketEvent.Type.RECEIVE));
                        return;
                     }
                  }

                  return;
               case 5:
                  class_3944 openScreen = (class_3944)var2;
                  if (!openScreen.method_17594().getString().contains("Ð ÑŽÐºÐ·Ð°Ðº") || this.stacks.isEmpty()) {
                     var3 = 6;
                     break;
                  } else {
                     Script var30 = this.script.cleanup();
                     Script var10002 = this.script2;
                     Objects.requireNonNull(var10002);
                     var30.addTickStep(0, var10002::update);
                     return;
                  }
               default:
                  return;
            }
         }
      }
   }

   @EventHandler
   public void onSetScreen(SetScreenEvent e) {
      class_437 var3 = e.getScreen();
      if (var3 instanceof class_476 screen) {
         if (screen.method_25440().getString().contains("Ð ÑŽÐºÐ·Ð°Ðº") && !this.script2.isFinished()) {
            e.setScreen((class_437)null);
         }
      }

   }

   @EventHandler
   public void onRotationUpdate(RotationUpdateEvent e) {
      if (e.getType() == 0 && mc.field_1755 == null) {
         boolean noMoveOrAction = System.currentTimeMillis() < this.stopMovementUntil || this.actionState != ServerHelper.ActionState.IDLE && this.actionState != ServerHelper.ActionState.SPEEDING_UP;
         if (noMoveOrAction) {
            mc.field_1690.field_1894.method_23481(false);
            mc.field_1690.field_1881.method_23481(false);
            mc.field_1690.field_1913.method_23481(false);
            mc.field_1690.field_1849.method_23481(false);
            if (mc.field_1724.field_3913 != null) {
               mc.field_1724.field_3913.field_3905 = 0.0F;
               mc.field_1724.field_3913.field_3907 = 0.0F;
            }

            if (mc.field_1724.method_5624()) {
               mc.field_1724.method_5728(false);
            }
         }

         if (this.mode.isSelected("FunSky HVH")) {
            this.handleFunSkyHVHInputs();
         }

         for(KeyBind bind : this.keyBindings) {
            String var10000;
            switch (bind.setting.getName()) {
               case "ÐÐ½Ñ‚Ð¸ Ð¿Ð¾Ð»ÐµÑ‚" -> var10000 = "antiflight";
               case "Ð¡Ð²Ð¸Ñ‚Ð¾Ðº Ð¾Ð¿Ñ‹Ñ‚Ð°" -> var10000 = "expscroll";
               case "Ð’Ð·Ñ€Ñ‹Ð²Ð½Ð°Ñ Ñ‚Ñ€Ð°Ð¿ÐºÐ°" -> var10000 = "dtrap";
               case "ÐžÐ±Ñ‹Ñ‡Ð½Ð°Ñ Ñ‚Ñ€Ð°Ð¿ÐºÐ°" -> var10000 = "trap_holy";
               case "Ð¡Ñ‚Ð°Ð½" -> var10000 = "stan";
               case "Ð’Ð·Ñ€Ñ‹Ð²Ð½Ð°Ñ ÑˆÑ‚ÑƒÑ‡ÐºÐ°" -> var10000 = "ditem";
               case "Ð¡Ð½ÐµÐ¶Ð¾Ðº Ð·Ð°Ð¼Ð¾Ñ€Ð¾Ð·ÐºÐ°" -> var10000 = "snow";
               case "Ð‘Ð¾Ð¶ÑŒÑ Ð°ÑƒÑ€Ð°" -> var10000 = "bojaura";
               case "Ð¢Ñ€Ð°Ð¿ÐºÐ°" -> var10000 = "trap";
               case "ÐŸÐ»Ð°ÑÑ‚" -> var10000 = "plast";
               case "Ð¯Ð²Ð½Ð°Ñ Ð¿Ñ‹Ð»ÑŒ" -> var10000 = "sugar";
               case "ÐžÐ³Ð½ÐµÐ½Ð½Ñ‹Ð¹ ÑÐ¼ÐµÑ€Ñ‡" -> var10000 = "fireSwirl";
               case "Ð”ÐµÐ·Ð¾Ñ€Ð¸ÐµÐ½Ñ‚Ð°Ñ†Ð¸Ñ" -> var10000 = "disorientation";
               case "Ð¡Ð²ÐµÑ‚Ð¸Ð»ÑŒÐ½Ð¸Ðº Ð”Ð¶ÐµÐºÐ°" -> var10000 = "tikva";
               case "ÐŸÑƒÐ·Ñ‹Ñ€ÑŒ Ð¾Ð¿Ñ‹Ñ‚Ð°" -> var10000 = "exp";
               case "Ð ÑŽÐºÐ·Ð°Ðº 1 ÑƒÑ€Ð¾Ð²Ð½Ñ" -> var10000 = "shulker1";
               case "Ð ÑŽÐºÐ·Ð°Ðº 2 ÑƒÑ€Ð¾Ð²Ð½Ñ" -> var10000 = "shulker2";
               case "Ð ÑŽÐºÐ·Ð°Ðº 3 ÑƒÑ€Ð¾Ð²Ð½Ñ" -> var10000 = "shulker3";
               case "Ð ÑŽÐºÐ·Ð°Ðº 4 ÑƒÑ€Ð¾Ð²Ð½Ñ" -> var10000 = "shulker4";
               case "Ð—ÐµÐ»ÑŒÐµ Ð¾Ñ‚Ñ€Ñ‹Ð¶ÐºÐ¸" -> var10000 = "otriga";
               case "Ð—ÐµÐ»ÑŒÐµ ÑÐµÑ€Ð½Ð¾Ð¹ ÐºÐ¸ÑÐ»Ð¾Ñ‚Ñ‹" -> var10000 = "serka";
               case "Ð—ÐµÐ»ÑŒÐµ Ð²ÑÐ¿Ñ‹ÑˆÐºÐ¸" -> var10000 = "vspihka";
               case "Ð—ÐµÐ»ÑŒÐµ Ð¼Ð¾Ñ‡Ð¸ Ð¤Ð»ÐµÑˆÐ°" -> var10000 = "mochaflesha";
               case "Ð—ÐµÐ»ÑŒÐµ Ð¿Ð¾Ð±ÐµÐ´Ð¸Ñ‚ÐµÐ»Ñ" -> var10000 = "pobedilka";
               case "Ð—ÐµÐ»ÑŒÐµ Ð°Ð³ÐµÐ½Ñ‚Ð°" -> var10000 = "agent";
               case "Ð—ÐµÐ»ÑŒÐµ Ð¼ÐµÐ´Ð¸ÐºÐ°" -> var10000 = "medik";
               case "Ð—ÐµÐ»ÑŒÐµ ÐºÐ¸Ð»Ð»ÐµÑ€Ð°" -> var10000 = "killer";
               case "Ares: ÐÑ€Ð±Ð°Ð»ÐµÑ‚" -> var10000 = "ares_crossbow";
               case "Ares: Ð¡Ð²Ð¸Ñ‚Ð¾Ðº Ð½ÐµÑƒÑÐ·Ð²Ð¸Ð¼Ð¾ÑÑ‚Ð¸" -> var10000 = "ares_invuln";
               case "Ares: Ð¡Ð²Ð¸Ñ‚Ð¾Ðº Ð¼ÐµÑ‚ÐµÐ¾Ñ€Ð°" -> var10000 = "ares_meteor";
               case "Ares: Ð¡Ð²Ð¸Ñ‚Ð¾Ðº Ð·Ð°Ð¼ÐµÐ´Ð»ÐµÐ½Ð¸Ñ" -> var10000 = "ares_slow";
               case "Ares: ÐÐµÐ±ÐµÑÐ½Ð°Ñ Ð½Ð¸Ñ‚ÑŒ" -> var10000 = "ares_thread";
               case "Ares: Ð‘Ð»Ð°Ð³Ð¾ÑÐ»Ð¾Ð²ÐµÐ½Ð¸Ðµ" -> var10000 = "ares_bless";
               case "Ares: ÐœÐ¾Ð»Ð¾Ñ‚ Ñ‚Ð¾Ñ€Ð°" -> var10000 = "ares_thor";
               default -> var10000 = null;
            }

            String key = var10000;
            if (key != null && bind.setting.isVisible()) {
               boolean currentKey = false;
               if (bind.setting.getKey() != -1) {
                  if (bind.setting.getKey() >= 0 && bind.setting.getKey() <= 7) {
                     currentKey = GLFW.glfwGetMouseButton(mc.method_22683().method_4490(), bind.setting.getKey()) == 1;
                  } else {
                     currentKey = class_3675.method_15987(mc.method_22683().method_4490(), bind.setting.getKey());
                  }
               }

               wasPressedLastTick = (Boolean)this.lastKeyStates.getOrDefault(key, false);
               if (currentKey && !wasPressedLastTick) {
                  ItemInfo info = (ItemInfo)this.itemConfig.get(key);
                  if (info != null) {
                     class_1735 slot = InventoryTask.getSlot((Predicate)((s) -> s.method_7677().method_7909().equals(info.item) && InventoryTask.getCleanName(s.method_7677().method_7964()).contains(info.searchName.toLowerCase())));
                     boolean addStarPrefix = info.displayName.equals("Ð”ÐµÐ·Ð¾Ñ€Ð¸ÐµÐ½Ñ‚Ð°Ñ†Ð¸Ñ") || info.displayName.equals("Ð‘Ð¾Ð¶ÑŒÑ Ð°ÑƒÑ€Ð°") || info.displayName.equals("ÐŸÐ»Ð°ÑÑ‚") || info.displayName.equals("Ð¢Ñ€Ð°Ð¿ÐºÐ°") || info.displayName.equals("ÐžÐ³Ð½ÐµÐ½Ð½Ñ‹Ð¹ ÑÐ¼ÐµÑ€Ñ‡") || info.displayName.equals("Ð¡Ð½ÐµÐ¶Ð¾Ðº Ð·Ð°Ð¼Ð¾Ñ€Ð¾Ð·ÐºÐ°") || info.displayName.equals("Ð¯Ð²Ð½Ð°Ñ Ð¿Ñ‹Ð»ÑŒ") || info.displayName.equals("Ð—ÐµÐ»ÑŒÐµ Ð¾Ñ‚Ñ€Ñ‹Ð¶ÐºÐ¸") || info.displayName.equals("Ð—ÐµÐ»ÑŒÐµ ÑÐµÑ€Ð½Ð¾Ð¹ ÐºÐ¸ÑÐ»Ð¾Ñ‚Ñ‹") || info.displayName.equals("Ð—ÐµÐ»ÑŒÐµ Ð²ÑÐ¿Ñ‹ÑˆÐºÐ¸") || info.displayName.equals("Ð—ÐµÐ»ÑŒÐµ Ð¼Ð¾Ñ‡Ð¸ Ð¤Ð»ÐµÑˆÐ°") || info.displayName.equals("Ð—ÐµÐ»ÑŒÐµ Ð¿Ð¾Ð±ÐµÐ´Ð¸Ñ‚ÐµÐ»Ñ") || info.displayName.equals("Ð—ÐµÐ»ÑŒÐµ Ð°Ð³ÐµÐ½Ñ‚Ð°") || info.displayName.equals("Ð—ÐµÐ»ÑŒÐµ Ð¼ÐµÐ´Ð¸ÐºÐ°") || info.displayName.equals("Ð—ÐµÐ»ÑŒÐµ ÐºÐ¸Ð»Ð»ÐµÑ€Ð°");
                     if (slot != null) {
                        class_1799 stack = slot.method_7677();
                        if (mc.field_1724.method_7357().method_7904(stack)) {
                           CoolDowns.getInstance().list.stream().filter((c) -> c.item().equals(info.item)).findFirst().ifPresent((coolDown) -> {
                              int time = Math.toIntExact(-coolDown.time().elapsedTime() / 1000L);
                              String duration = StringHelper.getDuration(time);
                              class_5250 text = class_2561.method_43473().method_10852(GradientAssist.applyGradientToText(info.displayName, GradientAssist.getGradientColors(info.displayName), addStarPrefix)).method_27693("  Ð±ÑƒÐ´ÐµÑ‚  Ð´Ð¾ÑÑ‚ÑƒÐ¿ÐµÐ½  Ñ‡ÐµÑ€ÐµÐ· ").method_10852(class_2561.method_43470(duration).method_27692(class_124.field_1080));
                              Notifications.getInstance().addList((class_2561)text, 4000L);
                           });
                        } else if (!this.potionQueue.contains(key)) {
                           this.potionQueue.add(key);
                        }
                     } else {
                        class_5250 text = class_2561.method_43473().method_10852(GradientAssist.applyGradientToText(info.displayName, GradientAssist.getGradientColors(info.displayName), addStarPrefix)).method_27693("  Ð½Ðµ  Ð½Ð°Ð¹Ð´ÐµÐ½Ð¾");
                        Notifications.getInstance().addList((class_2561)text, 4000L);
                     }
                  }
               }

               this.lastKeyStates.put(key, currentKey);
               this.keyPressedThisTick.put(key, currentKey);
            }
         }

         if (this.actionState != ServerHelper.ActionState.IDLE) {
            this.processItemAction();
         }

         if (this.actionState == ServerHelper.ActionState.IDLE && !this.potionQueue.isEmpty() && this.potionTimer.finished((double)150.0F)) {
            String potionKey = (String)this.potionQueue.remove(0);
            ItemInfo info = (ItemInfo)this.itemConfig.get(potionKey);
            if (info != null) {
               class_1735 slot = InventoryTask.getSlot((Predicate)((s) -> s.method_7677().method_7909().equals(info.item) && InventoryTask.getCleanName(s.method_7677().method_7964()).contains(info.searchName.toLowerCase())));
               boolean addStarPrefix = info.displayName.equals("Ð”ÐµÐ·Ð¾Ñ€Ð¸ÐµÐ½Ñ‚Ð°Ñ†Ð¸Ñ") || info.displayName.equals("Ð‘Ð¾Ð¶ÑŒÑ Ð°ÑƒÑ€Ð°") || info.displayName.equals("ÐŸÐ»Ð°ÑÑ‚") || info.displayName.equals("Ð¢Ñ€Ð°Ð¿ÐºÐ°") || info.displayName.equals("ÐžÐ³Ð½ÐµÐ½Ð½Ñ‹Ð¹ ÑÐ¼ÐµÑ€Ñ‡") || info.displayName.equals("Ð¡Ð½ÐµÐ¶Ð¾Ðº Ð·Ð°Ð¼Ð¾Ñ€Ð¾Ð·ÐºÐ°") || info.displayName.equals("Ð¯Ð²Ð½Ð°Ñ Ð¿Ñ‹Ð»ÑŒ") || info.displayName.equals("Ð—ÐµÐ»ÑŒÐµ Ð¾Ñ‚Ñ€Ñ‹Ð¶ÐºÐ¸") || info.displayName.equals("Ð—ÐµÐ»ÑŒÐµ ÑÐµÑ€Ð½Ð¾Ð¹ ÐºÐ¸ÑÐ»Ð¾Ñ‚Ñ‹") || info.displayName.equals("Ð—ÐµÐ»ÑŒÐµ Ð²ÑÐ¿Ñ‹ÑˆÐºÐ¸") || info.displayName.equals("Ð—ÐµÐ»ÑŒÐµ Ð¼Ð¾Ñ‡Ð¸ Ð¤Ð»ÐµÑˆÐ°") || info.displayName.equals("Ð—ÐµÐ»ÑŒÐµ Ð¿Ð¾Ð±ÐµÐ´Ð¸Ñ‚ÐµÐ»Ñ") || info.displayName.equals("Ð—ÐµÐ»ÑŒÐµ Ð°Ð³ÐµÐ½Ñ‚Ð°") || info.displayName.equals("Ð—ÐµÐ»ÑŒÐµ Ð¼ÐµÐ´Ð¸ÐºÐ°") || info.displayName.equals("Ð—ÐµÐ»ÑŒÐµ ÐºÐ¸Ð»Ð»ÐµÑ€Ð°");
               if (slot != null) {
                  class_1799 stack = slot.method_7677();
                  if (!mc.field_1724.method_7357().method_7904(stack)) {
                     this.startItemUse(slot, info, addStarPrefix);
                  } else {
                     CoolDowns.getInstance().list.stream().filter((c) -> c.item().equals(info.item)).findFirst().ifPresent((coolDown) -> {
                        int time = Math.toIntExact(-coolDown.time().elapsedTime() / 1000L);
                        String duration = StringHelper.getDuration(time);
                        class_5250 text = class_2561.method_43473().method_10852(GradientAssist.applyGradientToText(info.displayName, GradientAssist.getGradientColors(info.displayName), addStarPrefix)).method_27693("  Ð±ÑƒÐ´ÐµÑ‚  Ð´Ð¾ÑÑ‚ÑƒÐ¿ÐµÐ½  Ñ‡ÐµÑ€ÐµÐ· ").method_10852(class_2561.method_43470(duration).method_27692(class_124.field_1080));
                        Notifications.getInstance().addList((class_2561)text, 4000L);
                     });
                  }
               } else {
                  class_5250 text = class_2561.method_43473().method_10852(GradientAssist.applyGradientToText(info.displayName, GradientAssist.getGradientColors(info.displayName), addStarPrefix)).method_27693("  Ð½Ðµ  Ð½Ð°Ð¹Ð´ÐµÐ½Ð¾");
                  Notifications.getInstance().addList((class_2561)text, 4000L);
               }

               this.potionTimer.reset();
            }
         }

         if (this.autoRepairSetting.isValue() && this.autoRepairSetting.isVisible() && StreamSupport.stream(mc.field_1724.method_5661().spliterator(), false).anyMatch((stackx) -> {
            if ((double)stackx.method_7919() / (double)stackx.method_7936() < 0.94) {
               return false;
            } else {
               class_6880<class_1887> mendingEntry = (class_6880)mc.field_1687.method_30349().method_30530(class_7924.field_41265).method_10223(class_1893.field_9101.method_29177()).orElse((Object)null);
               return mendingEntry != null && class_1890.method_8225(mendingEntry, stackx) > 0;
            }
         })) {
            InventoryTask.slots().filter((slotx) -> {
               class_1799 stack = slotx.method_7677();
               class_9279 component = (class_9279)stack.method_57824(class_9334.field_49628);
               return !mc.field_1724.method_7357().method_7904(stack) && stack.method_7909().equals(class_1802.field_8287) && component != null && component.toString().contains("\"text\":\" - Ð¿Ñ€Ð¸ Ð½Ð°Ð¶Ð°Ñ‚Ð¸Ðµ ÐŸÐšÐœ, Ð¿Ð¾Ð»Ð½Ð¾ÑÑ‚ÑŒÑŽ Ñ€ÐµÐ¼Ð¾Ð½Ñ‚Ð¸Ñ€ÑƒÐµÑ‚\"") && this.repairWatch.every((double)5000.0F);
            }).findFirst().ifPresent((slotx) -> InventoryFlowManager.addTask(() -> InventoryTask.swapAndUse(slotx, MathAngle.cameraAngle())));
         }

         if (!InventoryTask.isServerScreen() && !this.stacks.isEmpty() && this.script2.isFinished() && this.shulkerWatch.finished((double)300.0F)) {
            InventoryTask.slots().filter((s) -> s.method_7677().method_57824(class_9334.field_49622) != null).max(Comparator.comparingDouble((s) -> (double)((class_9288)s.method_7677().method_57825(class_9334.field_49622, (Object)null)).field_49338.stream().filter((item) -> !item.method_7960()).toList().size())).ifPresent((shulker) -> {
               InventoryTask.swapHand(shulker, class_1268.field_5808, false);
               InventoryTask.closeScreen(false);
               PlayerInteractionHelper.interactItem(class_1268.field_5808);
               this.script2.cleanup().addTickStep(0, () -> {
                  List<Integer> integers = new ArrayList();
                  InventoryTask.slots().forEach((slot) -> this.stacks.entrySet().stream().filter((entry) -> slot.field_7871.equals(mc.field_1724.method_31548()) && ((class_1792)entry.getValue()).equals(slot.method_7677().method_7909()) && (Integer)entry.getKey() == slot.field_7874).forEach((entry) -> {
                        InventoryTask.clickSlot(slot, 0, class_1713.field_7794, false);
                        integers.add(slot.field_7874);
                     }));
                  Map var10001 = this.stacks;
                  Objects.requireNonNull(var10001);
                  integers.forEach(var10001::remove);
                  InventoryTask.closeScreen(false);
                  InventoryTask.swapHand(shulker, class_1268.field_5808, false);
                  InventoryTask.closeScreen(false);
                  this.shulkerWatch.reset();
               });
            });
         }

         if (this.autoLootSetting.isValue() && this.autoLootSetting.isVisible()) {
            Stream var21 = PlayerInteractionHelper.streamEntities();
            Objects.requireNonNull(class_3988.class);
            var21 = var21.filter(class_3988.class::isInstance);
            Objects.requireNonNull(class_3988.class);
            var21.map(class_3988.class::cast).filter((m) -> m.method_6084(class_1304.field_6173) || m.method_6084(class_1304.field_6171)).findFirst().ifPresent((merchant) -> {
               class_243 attackVector = (class_243)this.pointFinder.computeVector(merchant, 6.0F, TurnsConnection.INSTANCE.getRotation(), (new LinearConstructor()).randomValue(), true).method_15442();
               Turns angle = MathAngle.calculateAngle(attackVector);
               this.itemsWatch.reset();
               this.entityUUID = merchant.method_5667();
               if (mc.field_1724.method_33571().method_1022(merchant.method_5829().method_1005()) <= (double)6.0F) {
                  mc.field_1724.field_3944.method_52787(class_2824.method_34208(merchant, false, class_1268.field_5808, merchant.method_5829().method_1005()));
                  mc.field_1724.field_3944.method_52787(class_2824.method_34207(merchant, false, class_1268.field_5808));
                  TurnsConnection.INSTANCE.rotateTo(angle, TurnsConfig.DEFAULT, TaskPriority.HIGH_IMPORTANCE_3, this);
               }

            });
         }

         this.script.cleanupIfFinished().update();
         this.blockStateMap.clear();
         this.structures.removeIf((cons) -> cons.time - (double)System.currentTimeMillis() <= (double)0.0F);
         this.serverEvents.removeIf((event) -> event.timeEnd + (double)90000.0F - (double)System.currentTimeMillis() <= (double)0.0F);
      }
   }

   private void startItemUse(class_1735 slot, ItemInfo info, boolean addStarPrefix) {
      this.originalSlot = mc.field_1724.method_31548().field_7545;
      this.originalSourceSlot = slot.field_7874;
      this.targetSlot = slot.field_7874;
      this.pendingItemKey = info.searchName;
      boolean needsSwap = (slot.field_7874 < 0 || slot.field_7874 >= 9) && (slot.field_7874 < 36 || slot.field_7874 >= 45);
      this.wasForwardPressed = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1894.method_1429().method_1444());
      this.wasBackPressed = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1881.method_1429().method_1444());
      this.wasLeftPressed = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1913.method_1429().method_1444());
      this.wasRightPressed = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1849.method_1429().method_1444());
      if (needsSwap) {
         this.actionState = ServerHelper.ActionState.SLOWING_DOWN;
         this.actionTimer = System.currentTimeMillis();
         this.stopMovementUntil = System.currentTimeMillis() + 95L;
         this.keysOverridden = true;
         mc.field_1690.field_1894.method_23481(false);
         mc.field_1690.field_1881.method_23481(false);
         mc.field_1690.field_1913.method_23481(false);
         mc.field_1690.field_1849.method_23481(false);
      } else {
         this.actionState = ServerHelper.ActionState.SWAP_TO_ITEM;
         this.actionTimer = System.currentTimeMillis();
         this.stopMovementUntil = System.currentTimeMillis() + 95L;
         this.keysOverridden = true;
         mc.field_1690.field_1894.method_23481(false);
         mc.field_1690.field_1881.method_23481(false);
         mc.field_1690.field_1913.method_23481(false);
         mc.field_1690.field_1849.method_23481(false);
      }

      class_5250 text = class_2561.method_43473().method_10852(GradientAssist.applyGradientToText(info.displayName, GradientAssist.getGradientColors(info.displayName), addStarPrefix)).method_27693("  Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð°");
      Notifications.getInstance().addList((class_2561)text, 4000L);
   }

   private void processItemAction() {
      long currentTime = System.currentTimeMillis();
      long elapsed = currentTime - this.actionTimer;
      switch (this.actionState.ordinal()) {
         case 1:
            mc.field_1724.field_3913.field_3905 = 0.0F;
            mc.field_1724.field_3913.field_3907 = 0.0F;
            if (mc.field_1724.method_5624()) {
               mc.field_1724.method_5728(false);
            }

            if (elapsed > 1L) {
               this.actionState = ServerHelper.ActionState.WAITING_STOP;
            }
            break;
         case 2:
            mc.field_1724.field_3913.field_3905 = 0.0F;
            mc.field_1724.field_3913.field_3907 = 0.0F;
            double velocityX = Math.abs(mc.field_1724.method_18798().field_1352);
            double velocityZ = Math.abs(mc.field_1724.method_18798().field_1350);
            if (velocityX < 0.001 && velocityZ < 0.001 || elapsed > 75L) {
               this.actionState = ServerHelper.ActionState.SWAP_TO_ITEM;
               this.actionTimer = currentTime;
            }
            break;
         case 3:
            if (elapsed > 25L) {
               if (this.targetSlot >= 0 && this.targetSlot < 9) {
                  mc.field_1724.field_3944.method_52787(new class_2868(this.targetSlot));
                  mc.field_1724.method_31548().field_7545 = this.targetSlot;
               } else if (this.targetSlot >= 36 && this.targetSlot < 45) {
                  int hotbarSlot = this.targetSlot - 36;
                  mc.field_1724.field_3944.method_52787(new class_2868(hotbarSlot));
                  mc.field_1724.method_31548().field_7545 = hotbarSlot;
                  this.targetSlot = hotbarSlot;
               } else {
                  int swapSlot = 8;
                  InventoryTask.clickSlot(this.targetSlot, swapSlot, class_1713.field_7791, false);
                  this.targetSlot = swapSlot;
                  mc.field_1724.field_3944.method_52787(new class_2868(swapSlot));
                  mc.field_1724.method_31548().field_7545 = swapSlot;
               }

               this.actionState = ServerHelper.ActionState.USE_ITEM;
               this.actionTimer = currentTime;
            }
            break;
         case 4:
            if (elapsed > 40L) {
               mc.field_1724.field_3944.method_52787(new class_2886(class_1268.field_5808, 0, mc.field_1724.method_36454(), mc.field_1724.method_36455()));
               mc.field_1724.method_6104(class_1268.field_5808);
               this.actionState = ServerHelper.ActionState.SWAP_BACK;
               this.actionTimer = currentTime;
            }
            break;
         case 5:
            if (elapsed > 25L) {
               boolean wasFromInventory = (this.originalSourceSlot < 0 || this.originalSourceSlot >= 9) && (this.originalSourceSlot < 36 || this.originalSourceSlot >= 45);
               if (wasFromInventory) {
                  if (this.targetSlot >= 0 && this.targetSlot < 9) {
                     InventoryTask.clickSlot(this.originalSourceSlot, this.targetSlot, class_1713.field_7791, false);
                  }
               } else if (this.originalSourceSlot >= 36 && this.originalSourceSlot < 45) {
                  int hotbarSlot = this.originalSourceSlot - 36;
                  if (this.targetSlot != hotbarSlot) {
                     mc.field_1724.field_3944.method_52787(new class_2868(hotbarSlot));
                     mc.field_1724.method_31548().field_7545 = hotbarSlot;
                  }
               } else if (this.originalSourceSlot >= 0 && this.originalSourceSlot < 9 && this.targetSlot != this.originalSourceSlot) {
                  mc.field_1724.field_3944.method_52787(new class_2868(this.originalSourceSlot));
                  mc.field_1724.method_31548().field_7545 = this.originalSourceSlot;
               }

               if (mc.field_1724.method_31548().field_7545 != this.originalSlot) {
                  mc.field_1724.field_3944.method_52787(new class_2868(this.originalSlot));
                  mc.field_1724.method_31548().field_7545 = this.originalSlot;
               }

               this.restoreKeyStates();
               this.actionState = ServerHelper.ActionState.SPEEDING_UP;
               this.actionTimer = currentTime;
            }
            break;
         case 6:
            long speedupElapsed = currentTime - this.actionTimer;
            if (speedupElapsed > 75L) {
               this.actionState = ServerHelper.ActionState.IDLE;
               this.originalSlot = -1;
               this.targetSlot = -1;
               this.originalSourceSlot = -1;
               this.pendingItemKey = null;
            }
      }

   }

   private void restoreKeyStates() {
      if (this.keysOverridden) {
         mc.field_1690.field_1894.method_23481(this.wasForwardPressed);
         mc.field_1690.field_1881.method_23481(this.wasBackPressed);
         mc.field_1690.field_1913.method_23481(this.wasLeftPressed);
         mc.field_1690.field_1849.method_23481(this.wasRightPressed);
         if (mc.field_1724.field_3913 != null) {
            if (this.wasForwardPressed) {
               mc.field_1724.field_3913.field_3905 = 1.0F;
               if (!mc.field_1724.method_5624()) {
                  mc.field_1724.method_5728(true);
               }
            }

            if (this.wasBackPressed) {
               mc.field_1724.field_3913.field_3905 = -1.0F;
            }

            if (this.wasLeftPressed) {
               mc.field_1724.field_3913.field_3907 = 1.0F;
            }

            if (this.wasRightPressed) {
               mc.field_1724.field_3913.field_3907 = -1.0F;
            }
         }

         this.keysOverridden = false;
      }
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      class_4587 matrix = e.getStack();
      this.keyBindings.stream().filter((bind) -> PlayerInteractionHelper.isKey(bind.setting) && InventoryTask.getSlot(bind.item) != null).forEach((bind) -> {
         class_2338 playerPos = mc.field_1724.method_24515();
         class_243 smooth = Calculate.interpolate(class_243.method_24954(class_2338.method_49637(mc.field_1724.field_6014, mc.field_1724.field_6036, mc.field_1724.field_5969)), class_243.method_24954(playerPos)).method_1020(class_243.method_24954(playerPos));
         int[] gradientColors = GradientAssist.getGradientColors(bind.setting.getName());
         int color = gradientColors.length > 1 ? ColorAssist.gradient(10, 0, gradientColors) : gradientColors[0];
         switch (bind.setting.getName()) {
            case "Ð¢Ñ€Ð°Ð¿ÐºÐ°":
            case "ÐžÐ±Ñ‹Ñ‡Ð½Ð°Ñ Ñ‚Ñ€Ð°Ð¿ÐºÐ°":
               this.drawItemCube(playerPos, smooth, 1.99F, color);
               break;
            case "Ð”ÐµÐ·Ð¾Ñ€Ð¸ÐµÐ½Ñ‚Ð°Ñ†Ð¸Ñ":
            case "ÐžÐ³Ð½ÐµÐ½Ð½Ñ‹Ð¹ ÑÐ¼ÐµÑ€Ñ‡":
            case "Ð¯Ð²Ð½Ð°Ñ Ð¿Ñ‹Ð»ÑŒ":
               this.drawItemRadius(matrix, bind.distance, color);
               break;
            case "Ð’Ð·Ñ€Ñ‹Ð²Ð½Ð°Ñ ÑˆÑ‚ÑƒÑ‡ÐºÐ°":
               this.drawItemRadius(matrix, 5.0F, color);
               break;
            case "ÐŸÐ»Ð°ÑÑ‚":
               float yaw = class_3532.method_15393(mc.field_1724.method_36454());
               if (Math.abs(mc.field_1724.method_36455()) > 60.0F) {
                  class_2338 blockPos = playerPos.method_10084().method_10079(mc.field_1724.method_58149(), 3);
                  class_243 pos1 = class_243.method_24954(blockPos.method_10089(3).method_10077(3).method_10074()).method_1019(smooth);
                  class_243 pos2 = class_243.method_24954(blockPos.method_10088(2).method_10076(2).method_10084()).method_1019(smooth);
                  Render3D.drawBox(new class_238(pos1, pos2), color, 3.0F, true, true, true);
               } else if (!(yaw <= -157.5F) && !(yaw >= 157.5F)) {
                  if (yaw <= -112.5F) {
                     this.drawSidePlast(playerPos.method_10089(5).method_10072().method_10074(), smooth, color, -1, true);
                  } else if (yaw <= -67.5F) {
                     class_2338 blockPos = playerPos.method_10089(2).method_10084();
                     class_243 pos1 = class_243.method_24954(blockPos.method_10087(2).method_10077(3)).method_1019(smooth);
                     class_243 pos2 = class_243.method_24954(blockPos.method_10086(3).method_10076(2).method_10089(2)).method_1019(smooth);
                     Render3D.drawBox(new class_238(pos1, pos2), color, 3.0F, true, true, true);
                  } else if (yaw <= -22.5F) {
                     this.drawSidePlast(playerPos.method_10089(5).method_10074(), smooth, color, 1, false);
                  } else if ((double)yaw >= (double)-22.5F && (double)yaw <= (double)22.5F) {
                     class_2338 blockPos = playerPos.method_10077(2).method_10084();
                     class_243 pos1 = class_243.method_24954(blockPos.method_10087(2).method_10089(3)).method_1019(smooth);
                     class_243 pos2 = class_243.method_24954(blockPos.method_10086(3).method_10088(2).method_10077(2)).method_1019(smooth);
                     Render3D.drawBox(new class_238(pos1, pos2), color, 3.0F, true, true, true);
                  } else if (yaw <= 67.5F) {
                     this.drawSidePlast(playerPos.method_10088(4).method_10074(), smooth, color, 1, true);
                  } else if (yaw <= 112.5F) {
                     class_2338 blockPos = playerPos.method_10088(3).method_10084();
                     class_243 pos1 = class_243.method_24954(blockPos.method_10087(2).method_10077(3)).method_1019(smooth);
                     class_243 pos2 = class_243.method_24954(blockPos.method_10086(3).method_10076(2).method_10089(2)).method_1019(smooth);
                     Render3D.drawBox(new class_238(pos1, pos2), color, 3.0F, true, true, true);
                  } else if (yaw <= 157.5F) {
                     this.drawSidePlast(playerPos.method_10088(4).method_10072().method_10074(), smooth, color, -1, false);
                  }
               } else {
                  class_2338 blockPos = playerPos.method_10076(3).method_10084();
                  class_243 pos1 = class_243.method_24954(blockPos.method_10087(2).method_10089(3)).method_1019(smooth);
                  class_243 pos2 = class_243.method_24954(blockPos.method_10086(3).method_10088(2).method_10077(2)).method_1019(smooth);
                  Render3D.drawBox(new class_238(pos1, pos2), color, 3.0F, true, true, true);
               }
               break;
            case "Ð’Ð·Ñ€Ñ‹Ð²Ð½Ð°Ñ Ñ‚Ñ€Ð°Ð¿ÐºÐ°":
               this.drawItemCube(playerPos, smooth, 3.99F, color);
               break;
            case "Ð¡Ñ‚Ð°Ð½":
               this.drawItemCube(playerPos, smooth, 15.01F, color);
               break;
            case "Ð¡Ð½ÐµÐ¶Ð¾Ðº Ð·Ð°Ð¼Ð¾Ñ€Ð¾Ð·ÐºÐ°":
               ProjectilePrediction.getInstance().drawPredictionInHand(matrix, List.of(class_1802.field_8543.method_7854()), MathAngle.cameraAngle());
         }

      });
   }

   @EventHandler
   public void onDraw(DrawEvent e) {
      class_332 context = e.getDrawContext();
      class_4587 matrix = context.method_51448();
      this.structures.forEach((cons) -> {
         double time = (cons.time - (double)System.currentTimeMillis()) / (double)1000.0F;
         class_243 vec3d = Projection.worldSpaceToScreenSpace(cons.vec);
         String text = Calculate.round(time, (double)0.1F) + "Ñ";
         FontRenderer font = Fonts.getSize(14);
         float width = font.getStringWidth(text);
         float posX = (float)(vec3d.field_1352 - (double)(width / 2.0F));
         float posY = (float)vec3d.field_1351;
         float padding = 2.0F;
         if (Projection.canSee(cons.vec) && cons.anarchy == Network.getAnarchy() && Network.getWorldType().equals(cons.world)) {
            blur.render(ShapeProperties.create(matrix, (double)(posX - padding), (double)(posY - padding), (double)(width + padding * 2.0F), (double)10.0F).round(1.5F).color(ColorAssist.HALF_BLACK).build());
            font.drawString(matrix, text, (double)posX, (double)(posY + 1.0F), ColorAssist.getText());
            Render2D.defaultDrawStack(context, cons.item.method_7854(), posX - 14.0F, posY - 2.5F, true, false, 0.5F);
         }

      });
      this.serverEvents.forEach((event) -> {
         class_243 vec3d = Projection.worldSpaceToScreenSpace(event.vec);
         double timeOpen = (event.timeOpen - (double)System.currentTimeMillis()) / (double)1000.0F;
         double timeEnd = (event.timeEnd - (double)System.currentTimeMillis()) / (double)1000.0F;
         double var10000 = mc.method_1561().field_4686.method_19326().method_1022(event.vec);
         String distance = " [" + Calculate.round(var10000, 0.1) + "m]";
         String time = timeOpen > (double)0.0F ? ("Ð”Ð¾ Ð½Ð°Ñ‡Ð°Ð»Ð°: " + Calculate.round(timeOpen, timeOpen < (double)30.0F ? (double)0.1F : (double)1.0F) + "Ñ").replace(".0", "") : (timeEnd > (double)0.0F ? ("Ð”Ð¾ ÐºÐ¾Ð½Ñ†Ð°: " + Calculate.round(timeEnd, timeEnd < (double)30.0F ? (double)0.1F : (double)1.0F) + "Ñ").replace(".0", "") : "ÐšÐ¾Ð½ÐµÑ† Ð¸Ð²ÐµÐ½Ñ‚Ð°!");
         if (Projection.canSee(event.vec) && event.anarchy == Network.getAnarchy() && Network.getWorldType().equals(event.world)) {
            List<String> list = new ArrayList(Collections.singletonList(event.name + distance));
            if (event.owner != null) {
               String var10001 = String.valueOf(class_124.field_1065);
               list.add("ÐŸÑ€Ð¸Ð·Ð²Ð°Ð½: " + var10001 + event.owner);
            }

            list.add(time);
            if (event.lvl != null) {
               list.add(event.lvl);
            }

            this.draw(matrix, Fonts.getSize(14), list, vec3d);
         }

      });
      PlayerInteractionHelper.streamEntities().filter((ent) -> ent.method_5667().equals(this.entityUUID)).forEach((ent) -> {
         class_243 pos = ent.method_24515().method_10074().method_46558();
         class_243 vec = Projection.worldSpaceToScreenSpace(pos);
         String text = !this.itemsWatch.finished((double)200.0F) ? "ÐœÐ¾Ð¶Ð½Ð¾ Ð·Ð°Ð±Ñ€Ð°Ñ‚ÑŒ" : (!this.itemsWatch.finished((double)20000.0F) ? Calculate.round((double)(20.0F - (float)this.itemsWatch.elapsedTime() / 1000.0F), (double)0.1F) + "Ñ" : "Ð¡ÐºÐ¾Ñ€Ð¾");
         FontRenderer font = Fonts.getSize(14);
         float height = 4.0F;
         float width = font.getStringWidth(text);
         float padding = 3.0F;
         double x = vec.method_10216() - (double)(width / 2.0F);
         double y = vec.method_10214() - (double)(height / 2.0F);
         class_124 formatting = mc.field_1724.method_33571().method_1022(ent.method_33571()) < (double)5.0F ? class_124.field_1060 : class_124.field_1061;
         if (Projection.canSee(pos)) {
            blur.render(ShapeProperties.create(matrix, x - (double)padding, y - (double)padding, (double)(width + padding * 2.0F), (double)(height + padding * 2.0F)).round(2.0F).color(ColorAssist.HALF_BLACK).build());
            font.drawString(matrix, String.valueOf(formatting) + text, x, y, ColorAssist.getText());
         }

      });
   }

   private void drawItemCube(class_2338 playerPos, class_243 smooth, float size, int color) {
      class_238 box = (new class_238(playerPos.method_10084())).method_997(smooth).method_1014((double)size);
      boolean inBox = mc.field_1687.method_18456().stream().map((player) -> PlayerSimulation.simulateOtherPlayer(player, 2)).anyMatch((simulated) -> simulated.player != mc.field_1724 && box.method_994(simulated.boundingBox) && !FriendUtils.isFriend((class_1297)simulated.player));
      Render3D.drawBox(box, inBox ? ColorAssist.getFriendColor() : color, 3.0F, true, true, true);
   }

   private void drawItemRadius(class_4587 matrix, float distance, int color) {
      float playerHalfWidth = mc.field_1724.method_17681() / 2.0F;
      int finalColor = this.validDistance(distance) ? ColorAssist.getFriendColor() : color;
      class_243 pos = Calculate.interpolate(mc.field_1724).method_1031((double)playerHalfWidth, 0.02, (double)playerHalfWidth);
      GL11.glEnable(2881);
      RenderSystem.enableBlend();
      RenderSystem.enableDepthTest();
      RenderSystem.depthMask(false);
      RenderSystem.disableCull();
      RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_CONSTANT_ALPHA);
      RenderSystem.setShader(class_10142.field_53876);
      class_287 buffer = class_289.method_1348().method_60827(class_5596.field_27380, class_290.field_1576);
      int i = 0;

      for(int size = 90; i <= size; ++i) {
         class_243 cosSin = Calculate.cosSin(i, size, (double)distance);
         class_243 nextCosSin = Calculate.cosSin(i + 1, size, (double)distance);
         Render3D.vertexLine((class_4587)matrix, buffer, (class_243)pos.method_1019(cosSin), (class_243)pos.method_1031(cosSin.field_1352, cosSin.field_1351 + (double)2.0F, cosSin.field_1350), ColorAssist.multAlpha(finalColor, 0.2F), ColorAssist.multAlpha(finalColor, 0.0F));
         Render3D.drawLine(pos.method_1019(cosSin), pos.method_1019(nextCosSin), finalColor, 2.0F, true);
      }

      i = 0;

      for(int size = 90; i <= size; ++i) {
         class_243 cosSin = Calculate.cosSin(i, size, (double)distance);
         Render3D.vertexLine((class_4587)matrix, buffer, (class_243)pos.method_1019(cosSin), (class_243)pos.method_1031(cosSin.field_1352, cosSin.field_1351 - (double)2.0F, cosSin.field_1350), ColorAssist.multAlpha(finalColor, 0.2F), ColorAssist.multAlpha(finalColor, 0.0F));
      }

      class_286.method_43433(buffer.method_60800());
      RenderSystem.disableDepthTest();
      RenderSystem.depthMask(true);
      RenderSystem.disableBlend();
      GL11.glDisable(2881);
   }

   private void draw(class_4587 matrix, FontRenderer font, List list, class_243 vec3d) {
      float offsetY = 0.0F;

      for(int i = 0; i < list.size(); ++i) {
         String string = (String)list.get(i);
         float width = font.getStringWidth(string);
         float posX = (float)(vec3d.field_1352 - (double)(width / 2.0F));
         blur.render(ShapeProperties.create(matrix, (double)(posX - 2.0F), vec3d.field_1351 - (double)2.0F + (double)offsetY, (double)(width + 4.0F), (double)10.0F).softness(3.0F).round(this.getRound(font, list, i, width)).color(ColorAssist.HALF_BLACK).build());
         font.drawString(matrix, string, (double)posX, vec3d.field_1351 + (double)1.0F + (double)offsetY, ColorAssist.getText());
         offsetY += 10.0F;
      }

   }

   private void drawSidePlast(class_2338 blockPos, class_243 smooth, int color, int i, boolean ff) {
      class_243 vec3d = class_243.method_24954(blockPos).method_1019(smooth);
      float width = 2.0F;
      int quadColor = ColorAssist.multAlpha(color, 0.15F);
      this.drawHorizontalLines(vec3d, color, width, i, ff);
      this.drawHorizontalLines(vec3d, color, width, i, ff);
      this.drawVerticalLines(vec3d, color, width, i, ff);
      this.drawHorizontalQuads(vec3d, quadColor, i, ff);
      this.drawHorizontalQuads(vec3d, quadColor, i, ff);
      this.drawVerticalQuads(vec3d, quadColor, i, ff);
   }

   private void drawHorizontalLines(class_243 vec3d, int color, float width, int i, boolean ff) {
      float x = ff ? (float)i : (float)(-i);
      class_243 var8;
      Render3D.drawLine(vec3d, var8 = vec3d.method_1031((double)x, (double)0.0F, (double)0.0F), color, width, true);

      for(int f = 0; f < 4; ++f) {
         Render3D.drawLine(var8, vec3d = var8.method_1031((double)0.0F, (double)0.0F, (double)i), color, width, true);
         Render3D.drawLine(vec3d, var8 = vec3d.method_1031((double)x, (double)0.0F, (double)0.0F), color, width, true);
      }

      Render3D.drawLine(var8, vec3d = var8.method_1031((double)0.0F, (double)0.0F, (double)i), color, width, true);
      class_243 var11;
      Render3D.drawLine(vec3d, var11 = vec3d.method_1031((double)(x * -2.0F), (double)0.0F, (double)0.0F), color, width, true);

      for(int f = 0; f < 3; ++f) {
         Render3D.drawLine(var11, vec3d = var11.method_1031((double)0.0F, (double)0.0F, (double)(i * -1)), color, width, true);
         Render3D.drawLine(vec3d, var11 = vec3d.method_1031((double)(x * -1.0F), (double)0.0F, (double)0.0F), color, width, true);
      }

      Render3D.drawLine(var11, var11.method_1031((double)0.0F, (double)0.0F, (double)(i * -2)), color, width, true);
   }

   private void drawVerticalLines(class_243 vec3d, int color, float width, int i, boolean ff) {
      float x = ff ? (float)i : (float)(-i);
      class_243 var8;
      Render3D.drawLine(vec3d, var8 = vec3d.method_1031((double)x, (double)0.0F, (double)0.0F), color, width, true);

      for(int f = 0; f < 4; ++f) {
         Render3D.drawLine(var8, vec3d = var8.method_1031((double)0.0F, (double)0.0F, (double)i), color, width, true);
         Render3D.drawLine(vec3d, var8 = vec3d.method_1031((double)x, (double)0.0F, (double)0.0F), color, width, true);
      }

      Render3D.drawLine(var8, vec3d = var8.method_1031((double)0.0F, (double)0.0F, (double)i), color, width, true);
      class_243 var11;
      Render3D.drawLine(vec3d, var11 = vec3d.method_1031((double)(x * -2.0F), (double)0.0F, (double)0.0F), color, width, true);

      for(int f = 0; f < 3; ++f) {
         Render3D.drawLine(var11, vec3d = var11.method_1031((double)0.0F, (double)0.0F, (double)(i * -1)), color, width, true);
         Render3D.drawLine(vec3d, var11 = vec3d.method_1031((double)(x * -1.0F), (double)0.0F, (double)0.0F), color, width, true);
      }

      Render3D.drawLine(var11, var11.method_1031((double)0.0F, (double)0.0F, (double)(i * -2)), color, width, true);
   }

   private void drawHorizontalQuads(class_243 vec3d, int color, int i, boolean ff) {
      vec3d = vec3d.method_1031((double)0.0F, 0.001, (double)0.0F);
      float x = ff ? (float)i : (float)(-i);
      Render3D.drawQuad(vec3d, vec3d.method_1031((double)x, (double)0.0F, (double)0.0F), vec3d.method_1031((double)x, (double)0.0F, (double)(i * 2)), vec3d.method_1031((double)0.0F, (double)0.0F, (double)(i * 2)), color, true);

      for(int f = 0; f < 3; ++f) {
         Render3D.drawQuad(vec3d = vec3d.method_1031((double)x, (double)0.0F, (double)i), vec3d.method_1031((double)x, (double)0.0F, (double)0.0F), vec3d.method_1031((double)x, (double)0.0F, (double)(i * 2)), vec3d.method_1031((double)0.0F, (double)0.0F, (double)(i * 2)), color, true);
      }

      class_243 var8;
      Render3D.drawQuad(var8 = vec3d.method_1031((double)x, (double)0.0F, (double)i), var8.method_1031((double)x, (double)0.0F, (double)0.0F), var8.method_1031((double)x, (double)0.0F, (double)i), var8.method_1031((double)0.0F, (double)0.0F, (double)i), color, true);
   }

   private void drawVerticalQuads(class_243 vec3d, int color, int i, boolean ff) {
      float x = ff ? (float)i : (float)(-i);
      Render3D.drawQuad(vec3d, vec3d.method_1031((double)x, (double)0.0F, (double)0.0F), vec3d.method_1031((double)x, (double)5.0F, (double)0.0F), vec3d.method_1031((double)0.0F, (double)5.0F, (double)0.0F), color, true);

      for(int f = 0; f < 4; ++f) {
         class_243 var7;
         Render3D.drawQuad(var7 = vec3d.method_1031((double)x, (double)0.0F, (double)0.0F), var7.method_1031((double)0.0F, (double)0.0F, (double)i), var7.method_1031((double)0.0F, (double)5.0F, (double)i), var7.method_1031((double)0.0F, (double)5.0F, (double)0.0F), color, true);
         Render3D.drawQuad(vec3d = var7.method_1031((double)0.0F, (double)0.0F, (double)i), vec3d.method_1031((double)x, (double)0.0F, (double)0.0F), vec3d.method_1031((double)x, (double)5.0F, (double)0.0F), vec3d.method_1031((double)0.0F, (double)5.0F, (double)0.0F), color, true);
      }

      class_243 var8;
      Render3D.drawQuad(var8 = vec3d.method_1031((double)x, (double)0.0F, (double)0.0F), var8.method_1031((double)0.0F, (double)0.0F, (double)i), var8.method_1031((double)0.0F, (double)5.0F, (double)i), var8.method_1031((double)0.0F, (double)5.0F, (double)0.0F), color, true);
      Render3D.drawQuad(vec3d = var8.method_1031((double)0.0F, (double)0.0F, (double)i), vec3d.method_1031((double)(x * -2.0F), (double)0.0F, (double)0.0F), vec3d.method_1031((double)(x * -2.0F), (double)5.0F, (double)0.0F), vec3d.method_1031((double)0.0F, (double)5.0F, (double)0.0F), color, true);
      vec3d = vec3d.method_1031((double)(x * -1.0F), (double)0.0F, (double)0.0F);

      for(int f = 0; f < 3; ++f) {
         class_243 var11;
         Render3D.drawQuad(var11 = vec3d.method_1031((double)(x * -1.0F), (double)0.0F, (double)0.0F), var11.method_1031((double)0.0F, (double)0.0F, (double)(i * -1)), var11.method_1031((double)0.0F, (double)5.0F, (double)(i * -1)), var11.method_1031((double)0.0F, (double)5.0F, (double)0.0F), color, true);
         Render3D.drawQuad(vec3d = var11.method_1031((double)0.0F, (double)0.0F, (double)(i * -1)), vec3d.method_1031((double)(x * -1.0F), (double)0.0F, (double)0.0F), vec3d.method_1031((double)(x * -1.0F), (double)5.0F, (double)0.0F), vec3d.method_1031((double)0.0F, (double)5.0F, (double)0.0F), color, true);
      }

      class_243 var12;
      Render3D.drawQuad(var12 = vec3d.method_1031((double)(x * -1.0F), (double)0.0F, (double)0.0F), var12.method_1031((double)0.0F, (double)0.0F, (double)(i * -2)), var12.method_1031((double)0.0F, (double)5.0F, (double)(i * -2)), var12.method_1031((double)0.0F, (double)5.0F, (double)0.0F), color, true);
   }

   private void addEvent(String name, String lvl, String owner, class_243 vec3d, String world, int timeOpen, int timeLoot) {
      if (this.serverEvents.stream().noneMatch((server) -> server.vec.equals(vec3d))) {
         long open = System.currentTimeMillis() + (long)timeOpen * 1000L;
         long loot = open + (long)timeLoot * 1000L;
         this.serverEvents.add(new ServerEvent(name, lvl, owner, vec3d, world, Network.getAnarchy(), (double)open, (double)loot));
      }

   }

   private void addStructure(class_1792 item, class_243 vec, double time) {
      if (this.structures.stream().noneMatch((str) -> str.vec.equals(vec))) {
         this.structures.add(new Structure(item, vec, Network.getWorldType(), Network.getAnarchy(), time));
      }

   }

   private Vector4f getRound(FontRenderer font, List list, int i, float width) {
      if (i == 0) {
         float next = font.getStringWidth((String)list.get(i + 1));
         return next >= width ? new Vector4f(2.0F, 0.0F, 2.0F, 0.0F) : new Vector4f(2.0F);
      } else if (i == list.size() - 1) {
         float prev = font.getStringWidth((String)list.get(i - 1));
         return prev >= width ? new Vector4f(0.0F, 2.0F, 0.0F, 2.0F) : new Vector4f(2.0F);
      } else {
         float prev = font.getStringWidth((String)list.get(i - 1));
         float next = font.getStringWidth((String)list.get(i + 1));
         return prev >= width ? (next >= width ? new Vector4f() : new Vector4f(0.0F, 2.0F, 0.0F, 2.0F)) : new Vector4f(2.0F);
      }
   }

   private boolean validDistance(float dist) {
      return dist == 0.0F || mc.field_1687.method_18456().stream().anyMatch((p) -> p != mc.field_1724 && !FriendUtils.isFriend((class_1297)p) && mc.field_1724.method_5739(p) <= dist);
   }

   private boolean isTrap(class_2338 center) {
      int inconsistencies = 0;

      for(class_2338 pos : PlayerInteractionHelper.getCube(center, 2.0F)) {
         if (pos.method_46558().method_1022(center.method_46558()) < (double)2.0F) {
            class_2680 state = (class_2680)this.blockStateMap.get(pos);
            if (state != null && !state.method_26215()) {
               ++inconsistencies;
            }
         } else if (!pos.equals(center.method_10086(2).method_10095().method_10078()) && !pos.equals(center.method_10086(2).method_10095().method_10067()) && !pos.equals(center.method_10086(2).method_10072().method_10078()) && !pos.equals(center.method_10086(2).method_10072().method_10067())) {
            class_2680 state = (class_2680)this.blockStateMap.get(pos);
            if (state == null || state.method_26215()) {
               ++inconsistencies;
            }
         }

         if (inconsistencies > 1) {
            return false;
         }
      }

      return true;
   }

   private boolean isBigTrap(class_2338 center) {
      int inconsistencies = 0;

      for(class_2338 pos : PlayerInteractionHelper.getCube(center, 3.0F)) {
         if (Math.abs(pos.method_10263() - center.method_10263()) <= 2 && Math.abs(pos.method_10264() - center.method_10264()) <= 2 && Math.abs(pos.method_10260() - center.method_10260()) <= 2) {
            class_2680 state = (class_2680)this.blockStateMap.get(pos);
            if (state != null && !state.method_26215()) {
               ++inconsistencies;
            }
         } else if (!pos.equals(center.method_10086(3))) {
            class_2680 state = (class_2680)this.blockStateMap.get(pos);
            if (state == null || state.method_26215()) {
               ++inconsistencies;
            }
         }

         if (inconsistencies > 1) {
            return false;
         }
      }

      return true;
   }

   public List getKeyBindings() {
      return this.keyBindings;
   }

   public BindSetting getSetting(String name) {
      return (BindSetting)this.keyBindings.stream().filter((bind) -> bind.setting().getName().equals(name)).map(KeyBind::setting).findFirst().orElse((Object)null);
   }

   private void handleFunSkyHVHInputs() {
      if (this.isBindPressed(this.verticalClipBind)) {
         this.teleport(mc.field_1724.method_23317(), mc.field_1724.method_23318() + (double)this.verticalClipDistance.getValue(), mc.field_1724.method_23321());
      }

      if (this.isBindPressed(this.horizontalClipBind)) {
         class_243 lookVec = class_243.method_1030(0.0F, mc.field_1724.method_36454());
         double newX = mc.field_1724.method_23317() + lookVec.field_1352 * (double)this.horizontalClipDistance.getValue();
         double newZ = mc.field_1724.method_23321() + lookVec.field_1350 * (double)this.horizontalClipDistance.getValue();
         this.teleport(newX, mc.field_1724.method_23318(), newZ);
      }

      if (this.isBindPressed(this.antiTPInfluenceBind)) {
         this.antiTPInfluenceActive = !this.antiTPInfluenceActive;
         if (this.antiTPInfluenceActive) {
            this.antiTPInfluenceInitialPos = mc.field_1724.method_19538();
            this.teleport(mc.field_1724.method_23317(), mc.field_1724.method_23318() + (double)this.antiTPInfluenceVerticalClip.getValue(), mc.field_1724.method_23321());
            this.antiTPInfluenceTimer.reset();
         } else {
            if (this.antiTPInfluenceInitialPos != null) {
               this.teleport(this.antiTPInfluenceInitialPos.field_1352, this.antiTPInfluenceInitialPos.field_1351 - (double)1.0F, this.antiTPInfluenceInitialPos.field_1350);
            }

            this.antiTPInfluenceInitialPos = null;
         }
      }

      if (this.antiTPInfluenceActive && this.antiTPInfluenceTimer.finished((double)((long)(this.antiTPInfluenceDelay.getValue() * 50.0F)))) {
         int direction = ThreadLocalRandom.current().nextInt(4);
         double distance = ThreadLocalRandom.current().nextDouble((double)this.antiTPInfluenceHorizontalClipNegative.getValue(), (double)this.antiTPInfluenceHorizontalClipPositive.getValue());
         class_243 var10000;
         switch (direction) {
            case 0 -> var10000 = new class_243(distance, (double)0.0F, (double)0.0F);
            case 1 -> var10000 = new class_243(-distance, (double)0.0F, (double)0.0F);
            case 2 -> var10000 = new class_243((double)0.0F, (double)0.0F, distance);
            default -> var10000 = new class_243((double)0.0F, (double)0.0F, -distance);
         }

         class_243 offset = var10000;
         this.teleport(mc.field_1724.method_23317() + offset.field_1352, mc.field_1724.method_23318(), mc.field_1724.method_23321() + offset.field_1350);
         this.antiTPInfluenceTimer.reset();
      }

      if (this.isBindPressed(this.bedrockClipBind)) {
         this.bedrockClipActive = !this.bedrockClipActive;
         if (this.bedrockClipActive) {
            int bottomY = mc.field_1687.method_31607();
            int topY = mc.field_1724.method_31478();
            int lastBedrockY = Integer.MIN_VALUE;

            for(int y = bottomY; y <= topY; ++y) {
               class_2338 pos = new class_2338(mc.field_1724.method_31477(), y, mc.field_1724.method_31479());
               if (mc.field_1687.method_8320(pos).method_26204() == class_2246.field_9987) {
                  lastBedrockY = y;
               }
            }

            if (lastBedrockY != Integer.MIN_VALUE) {
               this.teleport(mc.field_1724.method_23317(), (double)lastBedrockY + 1.8, mc.field_1724.method_23321());
            }
         }
      }

   }

   private void teleport(double x, double y, double z) {
      class_243 target = new class_243(x, y, z);
      double distance = mc.field_1724.method_19538().method_1022(target);
      int packetCount = (int)Math.min(Math.abs(distance / (double)11.0F) + (double)1.0F, (double)19.0F);

      for(int i = 0; i < packetCount; ++i) {
         mc.method_1562().method_52787(new class_2828.class_5911(false, false));
      }

      mc.method_1562().method_52787(new class_2828.class_2829(x, y, z, false, false));
      mc.field_1724.method_5814(x, y, z);
   }

   private boolean isBindPressed(BindSetting bind) {
      int key = bind.getKey();
      if (key == -1) {
         return false;
      } else {
         boolean isMouseKey = key >= 0 && key <= 7;
         boolean currentlyPressed = isMouseKey ? GLFW.glfwGetMouseButton(mc.method_22683().method_4490(), key) == 1 : class_3675.method_15987(mc.method_22683().method_4490(), key);
         String bindName = bind.getName();
         boolean wasPressed = (Boolean)this.lastKeyStates.getOrDefault(bindName, false);
         this.lastKeyStates.put(bindName, currentlyPressed);
         return currentlyPressed && !wasPressed;
      }
   }

   private static enum ActionState {
      IDLE,
      SLOWING_DOWN,
      WAITING_STOP,
      SWAP_TO_ITEM,
      USE_ITEM,
      SWAP_BACK,
      SPEEDING_UP;

      // $FF: synthetic method
      private static ActionState[] $values() {
         return new ActionState[]{IDLE, SLOWING_DOWN, WAITING_STOP, SWAP_TO_ITEM, USE_ITEM, SWAP_BACK, SPEEDING_UP};
      }
   }

   private static class ItemInfo {
      String searchName;
      class_1792 item;
      String displayName;

      ItemInfo(String searchName, class_1792 item, String displayName) {
         this.searchName = searchName;
         this.item = item;
         this.displayName = displayName;
      }
   }

   public static record KeyBind(class_1792 item, BindSetting setting, float distance) {
   }

   public static record Structure(class_1792 item, class_243 vec, String world, int anarchy, double time) {
   }

   public static record ServerEvent(String name, String lvl, String owner, class_243 vec, String world, int anarchy, double timeOpen, double timeEnd) {
   }
}

