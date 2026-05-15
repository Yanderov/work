package fun.Yanderov.utils.features.aura.warp;

import fun.Yanderov.Yanderov;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.PlayerVelocityStrafeEvent;
import fun.Yanderov.events.player.RotationUpdateEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.client.managers.event.EventManager;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.features.aura.utils.MathAngle;
import fun.Yanderov.utils.math.task.TaskPriority;
import fun.Yanderov.utils.math.task.TaskProcessor;
import java.util.Objects;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2708;
import net.minecraft.class_2828;
import net.minecraft.class_3532;

public class TurnsConnection implements QuickImports {
   public static TurnsConnection INSTANCE = new TurnsConnection();
   private TurnsConstructor lastRotationPlan;
   private final TaskProcessor rotationPlanTaskProcessor = new TaskProcessor();
   public Turns currentAngle;
   private Turns previousAngle;
   private Turns serverAngle;
   private Turns fakeAngle;

   public TurnsConnection() {
      this.serverAngle = Turns.DEFAULT;
      Yanderov.getInstance().getEventManager().register(this);
   }

   public void setRotation(Turns value) {
      if (value == null) {
         this.previousAngle = this.currentAngle != null ? this.currentAngle : MathAngle.cameraAngle();
      } else {
         this.previousAngle = this.currentAngle;
      }

      this.currentAngle = value;
   }

   public Turns getRotation() {
      return this.currentAngle != null ? this.currentAngle : MathAngle.cameraAngle();
   }

   public Turns getFakeRotation() {
      if (this.fakeAngle != null) {
         return this.fakeAngle;
      } else {
         return this.currentAngle != null ? this.currentAngle : (this.previousAngle != null ? this.previousAngle : MathAngle.cameraAngle());
      }
   }

   public void setFakeRotation(Turns angle) {
      this.fakeAngle = angle;
   }

   public Turns getPreviousRotation() {
      return this.currentAngle != null && this.previousAngle != null ? this.previousAngle : new Turns(mc.field_1724.field_5982, mc.field_1724.field_6004);
   }

   public Turns getMoveRotation() {
      TurnsConstructor rotationPlan = this.getCurrentRotationPlan();
      return this.currentAngle != null && rotationPlan != null && rotationPlan.isMoveCorrection() ? this.currentAngle : MathAngle.cameraAngle();
   }

   public TurnsConstructor getCurrentRotationPlan() {
      return this.rotationPlanTaskProcessor.fetchActiveTaskValue() != null ? (TurnsConstructor)this.rotationPlanTaskProcessor.fetchActiveTaskValue() : this.lastRotationPlan;
   }

   public void rotateTo(Turns.VecRotation vecRotation, class_1309 entity, int reset, TurnsConfig configurable, TaskPriority taskPriority, Module provider) {
      this.rotateTo(configurable.createRotationPlan(vecRotation.getAngle(), vecRotation.getVec(), entity, reset), taskPriority, provider);
   }

   public void rotateTo(Turns angle, int reset, TurnsConfig configurable, TaskPriority taskPriority, Module provider) {
      this.rotateTo(configurable.createRotationPlan(angle, angle.toVector(), (class_1297)null, reset), taskPriority, provider);
   }

   public void rotateTo(Turns angle, TurnsConfig configurable, TaskPriority taskPriority, Module provider) {
      this.rotateTo(configurable.createRotationPlan(angle, angle.toVector(), (class_1297)null, 1), taskPriority, provider);
   }

   public void rotateTo(TurnsConstructor plan, TaskPriority taskPriority, Module provider) {
      this.rotationPlanTaskProcessor.addTask(new TaskProcessor.Task(1, taskPriority.getPriority(), provider, plan));
   }

   public void update() {
      TurnsConstructor activePlan = this.getCurrentRotationPlan();
      if (activePlan != null) {
         Turns clientAngle = MathAngle.cameraAngle();
         if (this.lastRotationPlan != null) {
            double differenceFromCurrentToPlayer = computeRotationDifference(this.serverAngle, clientAngle);
            if (activePlan.getTicksUntilReset() <= this.rotationPlanTaskProcessor.tickCounter && differenceFromCurrentToPlayer < (double)activePlan.getResetThreshold()) {
               this.setRotation((Turns)null);
               this.lastRotationPlan = null;
               this.rotationPlanTaskProcessor.tickCounter = 0;
               return;
            }
         }

         Turns newAngle = activePlan.nextRotation(this.currentAngle != null ? this.currentAngle : clientAngle, this.rotationPlanTaskProcessor.fetchActiveTaskValue() == null).adjustSensitivity();
         this.setRotation(newAngle);
         this.lastRotationPlan = activePlan;
         this.rotationPlanTaskProcessor.tick(1);
      }
   }

   public static double computeRotationDifference(Turns a, Turns b) {
      return Math.hypot((double)Math.abs(computeAngleDifference(a.getYaw(), b.getYaw())), (double)Math.abs(a.getPitch() - b.getPitch()));
   }

   public static float computeAngleDifference(float a, float b) {
      return class_3532.method_15393(a - b);
   }

   private class_243 fixVelocity(class_243 currVelocity, class_243 movementInput, float speed) {
      if (this.currentAngle != null) {
         float yaw = this.currentAngle.getYaw();
         double d = movementInput.method_1027();
         if (d < 1.0E-7) {
            return class_243.field_1353;
         } else {
            class_243 vec3d = (d > (double)1.0F ? movementInput.method_1029() : movementInput).method_1021((double)speed);
            float f = class_3532.method_15374(yaw * ((float)Math.PI / 180F));
            float g = class_3532.method_15362(yaw * ((float)Math.PI / 180F));
            return new class_243(vec3d.method_10216() * (double)g - vec3d.method_10215() * (double)f, vec3d.method_10214(), vec3d.method_10215() * (double)g + vec3d.method_10216() * (double)f);
         }
      } else {
         return currVelocity;
      }
   }

   public void clear() {
      this.rotationPlanTaskProcessor.activeTasks.clear();
   }

   @EventHandler
   public void onPlayerVelocityStrafe(PlayerVelocityStrafeEvent e) {
      TurnsConstructor currentRotationPlan = this.getCurrentRotationPlan();
      if (currentRotationPlan != null && currentRotationPlan.isMoveCorrection()) {
         e.setVelocity(this.fixVelocity(e.getVelocity(), e.getMovementInput(), e.getSpeed()));
      }

   }

   @EventHandler
   public void onTick(TickEvent e) {
      EventManager.callEvent(new RotationUpdateEvent((byte)0));
      this.update();
      EventManager.callEvent(new RotationUpdateEvent((byte)2));
   }

   @EventHandler
   public void onPacket(PacketEvent event) {
      if (!event.isCancelled()) {
         class_2596 var10000 = event.getPacket();
         Objects.requireNonNull(var10000);
         class_2596 var4 = var10000;
         byte var5 = 0;

         while(true) {
            //$FF: var5->value
            //0->net/minecraft/class_2828
            //1->net/minecraft/class_2708
            switch (var4.typeSwitch<invokedynamic>(var4, var5)) {
               case 0:
                  class_2828 player = (class_2828)var4;
                  if (player.method_36172()) {
                     this.serverAngle = new Turns(player.method_12271(1.0F), player.method_12270(1.0F));
                     return;
                  }

                  var5 = 1;
                  break;
               case 1:
                  class_2708 player = (class_2708)var4;
                  this.serverAngle = new Turns(player.comp_3228().comp_3150(), player.comp_3228().comp_3151());
                  return;
               default:
                  return;
            }
         }
      }
   }

   public TurnsConstructor getLastRotationPlan() {
      return this.lastRotationPlan;
   }

   public TaskProcessor getRotationPlanTaskProcessor() {
      return this.rotationPlanTaskProcessor;
   }

   public Turns getCurrentAngle() {
      return this.currentAngle;
   }

   public Turns getPreviousAngle() {
      return this.previousAngle;
   }

   public Turns getServerAngle() {
      return this.serverAngle;
   }

   public Turns getFakeAngle() {
      return this.fakeAngle;
   }
}

