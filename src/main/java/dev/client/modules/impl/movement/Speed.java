package dev.client.modules.impl.movement;

import dev.client.event.classes.TickEvent;
import dev.client.event.interfaces.ITickable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.PlayerModel;
import dev.client.modules.settings.impl.FloatSetting;
import dev.client.modules.settings.impl.ModeSetting;
import dev.client.util.IUtil;
import dev.client.util.math.TimerUtil;
import dev.client.util.player.MovementUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class Speed extends Module implements ITickable, IUtil {
   private final ModeSetting mode = new ModeSetting().name("Mode").value("Vanilla").modes("Boat", "Collission", "Vanilla");
   public FloatSetting speed = new FloatSetting().name("Speed").minValue(0.1F).value(0.5F).maxValue(2.0F).incriment(0.01F);
   public FloatSetting dist = new FloatSetting() {
      public boolean isVisible() {
         return Speed.this.mode.is("Collission");
      }
   }.name("Dist").minValue(0.1F).value(0.75F).maxValue(1.1F).incriment(0.01F);
   public FloatSetting boost = new FloatSetting() {
      public boolean isVisible() {
         return Speed.this.mode.is("Collission");
      }
   }.name("Boost").minValue(0.05F).value(0.25F).maxValue(0.5F).incriment(0.01F);
   private final TimerUtil timerUtil = new TimerUtil();

   public Speed() {
      super(new PlayerModel("Speed", Category.MOVEMENT, "Ускоряет перемещение игрока"));
      this.addSetting(this.mode, this.speed, this.dist, this.boost);
   }

   public void onTick(TickEvent event) {
      if (mc.player == null) return;
      
      switch (this.mode.getValue()) {
         case "Vanilla":
            if (MovementUtil.isMove()) {
               float yaw = mc.player.getYaw();
               double speedVal = this.speed.getValue();
               double x = -Math.sin(Math.toRadians(yaw)) * speedVal;
               double z = Math.cos(Math.toRadians(yaw)) * speedVal;
               mc.player.setVelocity(x, mc.player.getVelocity().y, z);
            }
            break;
         case "Boat":
            if (mc.world != null) {
               for(Entity entity : mc.world.getEntities()) {
                  if (entity instanceof BoatEntity boatEntity) {
                     if (mc.player.distanceTo(boatEntity) < 2.0F) {
                        Vec3d velocity = mc.player.getVelocity();
                        mc.player.setVelocity(velocity.x * 2.0D, velocity.y + (mc.player.isOnGround() ? 0.5D : 0.0D), velocity.z * 2.0D);
                     }
                  }
               }
            }
            break;
         case "Collission":
            if (mc.world != null) {
               for(PlayerEntity player : mc.world.getPlayers()) {
                  if (player != mc.player && mc.player.distanceTo(player) < this.dist.getValue() && !mc.player.isOnGround()) {
                     Vec3d velocity = mc.player.getVelocity();
                     if (this.timerUtil.isReached(100L)) {
                        mc.player.setVelocity(velocity.x * (double)this.boost.getValue(), velocity.y, velocity.z * (double)this.boost.getValue());
                        this.timerUtil.reset();
                     }
                  }
               }
            }
            break;
      }
   }
}
