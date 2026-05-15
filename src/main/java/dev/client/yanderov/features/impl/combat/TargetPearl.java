package dev.client.yanderov.features.impl.combat;

import antidaunleak.api.annotation.Native;
import dev.client.yanderov.common.repository.friend.FriendUtils;
import dev.client.yanderov.events.player.EntitySpawnEvent;
import dev.client.yanderov.events.player.PostMotionEvent;
import dev.client.yanderov.events.player.RotationUpdateEvent;
import dev.client.yanderov.features.impl.render.ProjectilePrediction;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BindSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.features.aura.rotations.impl.SnapAngle;
import dev.client.yanderov.utils.features.aura.utils.MathAngle;
import dev.client.yanderov.utils.features.aura.warp.Turns;
import dev.client.yanderov.utils.features.aura.warp.TurnsConfig;
import dev.client.yanderov.utils.features.aura.warp.TurnsConnection;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import dev.client.yanderov.utils.interactions.inv.InventoryFlowManager;
import dev.client.yanderov.utils.interactions.inv.InventoryTask;
import dev.client.yanderov.utils.interactions.simulate.PlayerSimulation;
import dev.client.yanderov.utils.math.script.Script;
import dev.client.yanderov.utils.math.task.TaskPriority;
import dev.client.yanderov.utils.math.time.StopWatch;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1684;
import net.minecraft.class_1735;
import net.minecraft.class_1802;
import net.minecraft.class_239;
import net.minecraft.class_243;

public class TargetPearl extends Module {
   private final StopWatch stopWatch = new StopWatch();
   private final Script script = new Script();
   private final SelectSetting modeSetting = (new SelectSetting("Mode", "When will target pearl work")).value("Bind", "Always").selected("Always");
   private final SelectSetting targetSetting = (new SelectSetting("Targets", "Targets for which pearls will be thrown")).value("Aura Target", "All").selected("Aura Target");
   private final BindSetting throwSetting = (new BindSetting("Throw", "Throw Key")).visible(() -> this.modeSetting.isSelected("Bind"));
   private final SliderSettings distanceSetting = (new SliderSettings("Distance", "Target Pearl Trigger Distance")).setValue(10.0F).range(5, 15);

   public TargetPearl() {
      super("TargetPearl", "Target Pearl", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.modeSetting, this.targetSetting, this.throwSetting, this.distanceSetting});
   }

   @EventHandler
   public void onEntitySpawn(EntitySpawnEvent e) {
      class_1297 var3 = e.getEntity();
      if (var3 instanceof class_1684 pearl) {
         Optional var10000 = mc.field_1687.method_18456().stream().filter((p) -> p.method_5739(pearl) <= 3.0F).min(Comparator.comparingDouble((p) -> (double)p.method_5739(pearl)));
         Objects.requireNonNull(pearl);
         var10000.ifPresent(pearl::method_7432);
      }

   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginMutation
   )
   public void onRotationUpdate(RotationUpdateEvent e) {
      if (e.getType() == 0) {
         class_1309 target = Aura.getInstance().getLastTarget();
         class_1735 slot = InventoryTask.getSlot(class_1802.field_8634);
         if (slot == null || !this.stopWatch.finished((double)1000.0F)) {
            return;
         }

         if (this.modeSetting.isSelected("Bind") && !PlayerInteractionHelper.isKey(this.throwSetting)) {
            return;
         }

         Stream var10000 = PlayerInteractionHelper.streamEntities();
         Objects.requireNonNull(class_1684.class);
         var10000 = var10000.filter(class_1684.class::isInstance);
         Objects.requireNonNull(class_1684.class);
         if (var10000.map(class_1684.class::cast).anyMatch((pearl) -> Objects.equals(pearl.method_24921(), mc.field_1724))) {
            this.stopWatch.reset();
            return;
         }

         ProjectilePrediction prediction = ProjectilePrediction.getInstance();
         var10000 = PlayerInteractionHelper.streamEntities();
         Objects.requireNonNull(class_1684.class);
         var10000 = var10000.filter(class_1684.class::isInstance);
         Objects.requireNonNull(class_1684.class);
         var10000.map(class_1684.class::cast).filter((pearl) -> !FriendUtils.isFriend(pearl.method_24921()) && (this.targetSetting.isSelected("All") || target != null && target.equals(pearl.method_24921()))).min(Comparator.comparingDouble((pearl) -> TurnsConnection.computeRotationDifference(MathAngle.cameraAngle(), MathAngle.calculateAngle(prediction.calcTrajectory(pearl).method_17784())))).ifPresent((pearl) -> {
            class_239 targetResult = prediction.calcTrajectory(pearl);
            if (targetResult != null && !(mc.field_1724.method_19538().method_1022(targetResult.method_17784()) <= (double)this.distanceSetting.getInt())) {
               class_243 eyePos = mc.field_1724.method_33571().method_1019(mc.field_1724.method_19538().method_1020(PlayerSimulation.simulateLocalPlayer(1).pos));
               float yaw = MathAngle.fromVec3d(targetResult.method_17784().method_1020(eyePos)).getYaw();
               IntStream.range(-89, 89).mapToObj((pitch) -> new Turns(yaw, (float)pitch)).filter((angle) -> {
                  class_239 playerResult = prediction.checkTrajectory(angle.toVector(), new class_1684(mc.field_1687, mc.field_1724, slot.method_7677()), (double)1.5F);
                  return playerResult != null && playerResult.method_17784().method_1022(targetResult.method_17784()) <= (double)3.0F;
               }).max(Comparator.comparingDouble(Turns::getPitch)).ifPresent((angle) -> {
                  TurnsConnection.INSTANCE.rotateTo(new Turns.VecRotation(angle, angle.toVector()), mc.field_1724, 1, new TurnsConfig(new SnapAngle(), true, true), TaskPriority.HIGH_IMPORTANCE_3, this);
                  InventoryFlowManager.unPressMoveKeys();
                  this.script.cleanup().addTickStep(0, () -> {
                     InventoryTask.swapAndUse(class_1802.field_8634, angle, false);
                     InventoryFlowManager.enableMoveKeys();
                  });
                  pearl.method_7432((class_1297)null);
                  this.stopWatch.reset();
               });
            }
         });
      }

   }

   @EventHandler
   public void onPostMotion(PostMotionEvent e) {
      this.script.update();
   }
}

