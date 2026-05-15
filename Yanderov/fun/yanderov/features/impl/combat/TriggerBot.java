package fun.Yanderov.features.impl.combat;

import antidaunleak.api.annotation.Native;
import fun.Yanderov.Yanderov;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.RotationUpdateEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.events.render.WorldRenderEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.MultiSelectSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.features.aura.point.MultiPoint;
import fun.Yanderov.utils.features.aura.rotations.constructor.LinearConstructor;
import fun.Yanderov.utils.features.aura.rotations.constructor.RotateConstructor;
import fun.Yanderov.utils.features.aura.striking.StrikerConstructor;
import fun.Yanderov.utils.features.aura.target.TargetFinder;
import fun.Yanderov.utils.features.aura.utils.MathAngle;
import fun.Yanderov.utils.features.aura.warp.Turns;
import fun.Yanderov.utils.features.aura.warp.TurnsConnection;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import java.util.Objects;
import net.minecraft.class_1309;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3545;
import net.minecraft.class_746;

public class TriggerBot extends Module {
   private static final float RANGE_MARGIN = 0.253F;
   private final TargetFinder targetSelector = new TargetFinder();
   private final MultiPoint pointFinder = new MultiPoint();
   public class_1309 target;
   public SliderSettings attackRange = (new SliderSettings("Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ ÑƒÐ´Ð°Ñ€Ð°", "Ð”Ð°Ð»ÑŒÐ½Ð¾ÑÑ‚ÑŒ Ð°Ñ‚Ð°ÐºÐ¸ Ð´Ð¾ Ñ†ÐµÐ»Ð¸")).setValue(3.0F).range(1.0F, 6.0F);
   MultiSelectSetting targetType = (new MultiSelectSetting("Ð¢Ð¸Ð¿ Ñ‚Ð°Ñ€Ð³ÐµÑ‚Ð°", "Ð¤Ð¸Ð»ÑŒÑ‚Ñ€ÑƒÐµÑ‚ ÑÐ¿Ð¸ÑÐ¾Ðº Ñ†ÐµÐ»ÐµÐ¹ Ð¿Ð¾ Ñ‚Ð¸Ð¿Ñƒ")).value("Players", "Mobs", "Animals", "Friends", "Armor Stand").selected("Players", "Mobs", "Animals");
   public MultiSelectSetting attackSetting = (new MultiSelectSetting("ÐÐ°ÑÑ‚Ñ€Ð¾Ð¹ÐºÐ¸", "ÐŸÐ°Ñ€Ð°Ð¼ÐµÑ‚Ñ€Ñ‹ Ð°Ñ‚Ð°ÐºÐ¸")).value("Only Critical", "Break Shield", "UnPress Shield", "No Attack When Eat", "Ignore The Walls", "Hit Chance").selected("Only Critical", "Break Shield");
   public SliderSettings hitChance = (new SliderSettings("Ð¨Ð°Ð½Ñ ÑƒÐ´Ð°Ñ€Ð° Ð² %", "Ð¨Ð°Ð½Ñ ÑƒÐ´Ð°Ñ€Ð° Ð¿Ð¾ Ñ†ÐµÐ»Ð¸")).setValue(100.0F).range(1.0F, 100.0F).visible(() -> this.attackSetting.isSelected("Hit Chance"));
   public SelectSetting sprintReset = (new SelectSetting("Ð¡Ð±Ñ€Ð¾Ñ ÑÐ¿Ñ€Ð¸Ð½Ñ‚Ð°", "Ð’Ñ‹Ð±Ð¾Ñ€ ÑÐ±Ñ€Ð¾ÑÐ° ÑÐ¿Ñ€Ð¸Ð½Ñ‚Ð° Ð¿ÐµÑ€ÐµÐ´ ÑƒÐ´Ð°Ñ€Ð¾Ð¼")).value("Legit", "Packet").selected("Legit");
   public BooleanSetting smartCrits = (new BooleanSetting("Ð£Ð´Ð°Ñ€Ñ‹ Ð½Ð° Ð·ÐµÐ¼Ð»Ðµ", "ÐšÑ€Ð¸Ñ‚Ñ‹ Ñ‚Ð¾Ð»ÑŒÐºÐ¾ Ð¿Ñ€Ð¸ Ð½Ð°Ð¶Ð°Ñ‚Ð¸Ð¸ Ð¿Ñ€Ð¾Ð±ÐµÐ»Ð°")).setValue(true).visible(() -> this.attackSetting.isSelected("Only Critical"));

   public TriggerBot() {
      super("TriggerBot", "Trigger Bot", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.attackRange, this.targetType, this.attackSetting, this.hitChance, this.sprintReset, this.smartCrits});
   }

   public static TriggerBot getInstance() {
      return (TriggerBot)Instance.get(TriggerBot.class);
   }

   private class_1309 updateTarget() {
      TargetFinder.EntityFilter filter = new TargetFinder.EntityFilter(this.targetType.getSelected());
      float range = this.attackRange.getValue() + 0.253F;
      this.targetSelector.searchTargets(mc.field_1687.method_18112(), range, 360.0F, this.attackSetting.isSelected("Ignore The Walls"));
      TargetFinder var10000 = this.targetSelector;
      Objects.requireNonNull(filter);
      var10000.validateTarget(filter::isValid);
      return this.targetSelector.getCurrentTarget();
   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginMutation
   )
   public void onRotationUpdate(RotationUpdateEvent e) {
      if (!PlayerInteractionHelper.nullCheck()) {
         switch (e.getType()) {
            case 0:
               this.target = this.updateTarget();
               break;
            case 2:
               if (this.target != null) {
                  Yanderov.getInstance().getAttackPerpetrator().performAttack(this.getConfig());
               }
         }

      }
   }

   public StrikerConstructor.AttackPerpetratorConfigurable getConfig() {
      float baseRange = this.attackRange.getValue() + 0.253F;
      class_3545<class_243, class_238> pointData = this.pointFinder.computeVector(this.target, baseRange, TurnsConnection.INSTANCE.getRotation(), this.getSmoothMode().randomValue(), this.attackSetting.isSelected("Ignore The Walls"));
      class_243 computedPoint = (class_243)pointData.method_15442();
      class_238 hitbox = (class_238)pointData.method_15441();
      Turns angle = MathAngle.fromVec3d(computedPoint.method_1020(((class_746)Objects.requireNonNull(mc.field_1724)).method_33571()));
      return new StrikerConstructor.AttackPerpetratorConfigurable(this.target, angle, baseRange, this.attackSetting.getSelected(), (SelectSetting)null, hitbox);
   }

   public RotateConstructor getSmoothMode() {
      return new LinearConstructor();
   }

   @EventHandler
   public void tick(TickEvent e) {
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
   }
}

