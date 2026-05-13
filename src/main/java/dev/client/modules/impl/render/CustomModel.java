package dev.client.modules.impl.render;

import dev.client.WildClient;
import dev.client.event.classes.ReceivePacketEvent;
import dev.client.event.classes.Render2DEvent;
import dev.client.event.classes.RotationEvent;
import dev.client.event.interfaces.IReceivePacketable;
import dev.client.event.interfaces.IRenderable2D;
import dev.client.event.interfaces.IRotateable;
import dev.client.modules.Category;
import dev.client.modules.IDisableable;
import dev.client.modules.IEnableable;
import dev.client.modules.Module;
import dev.client.modules.PlayerModel;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.modules.settings.impl.ModeSetting;
import dev.client.util.IUtil;
import dev.client.util.math.MathUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity.RemovalReason;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.passive.PandaEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import org.joml.Vector2f;

@Environment(EnvType.CLIENT)
public class CustomModel extends Module implements IRenderable2D, IRotateable, IReceivePacketable, IEnableable, IDisableable, IUtil {
   public ModeSetting mode = new ModeSetting() {
      public void onChangeState(String val) {
         CustomModel.this.onDisable();
         CustomModel.this.onEnable();
         super.onChangeState(val);
      }
   }.name("Mode").modes("Skeleton", "Zombie", "Pig", "Allay", "Wargen", "Piglin", "Phantom", "Vex", "Breeze", "Creeper", "Panda").value("Skeleton");
   private final BooleanSetting hands = new BooleanSetting().name("Hands").value(false);
   private Vector2f rotate = new Vector2f(0.0F, 0.0F);
   private Vector2f endRotate = new Vector2f(0.0F, 0.0F);
   private Entity customModel;
   private boolean changeWorld = false;

   public CustomModel() {
      super(new PlayerModel("PlayerModel", Category.RENDER, "Изменяет модельку игрока"));
      this.addSetting(this.mode, this.hands);
   }

   public void onReceivePacket(ReceivePacketEvent receivePacketEvent) {
      if (mc.currentScreen instanceof DownloadingTerrainScreen) {
         this.changeWorld = true;
      }

      if (!(mc.currentScreen instanceof DownloadingTerrainScreen) && this.changeWorld && mc.world != null) {
         this.onDisable();
         this.onEnable();
         this.changeWorld = false;
      }

   }

   public void onRender2D(Render2DEvent event) {
      if (this.customModel == null || mc.player == null) return;

      this.endRotate.x = MathUtil.fast(this.endRotate.x, this.rotate.x, 20.0F);
      this.endRotate.y = MathUtil.fast(this.endRotate.y, this.rotate.y, 20.0F);
      
      float tickDelta = mc.getRenderTickCounter().getTickDelta(false);
      double lerpX = mc.player.prevX + (mc.player.getX() - mc.player.prevX) * tickDelta;
      double lerpY = mc.player.prevY + (mc.player.getY() - mc.player.prevY) * tickDelta;
      double lerpZ = mc.player.prevZ + (mc.player.getZ() - mc.player.prevZ) * tickDelta;
      
      this.customModel.setPos(lerpX, lerpY, lerpZ);
      this.customModel.setBoundingBox(this.calculateBoundingBox(this.customModel, 0.0F));
      this.customModel.setCustomName(Text.of(""));
      
      this.customModel.setYaw(this.endRotate.x);
      this.customModel.setPitch(this.endRotate.y);
      this.customModel.setHeadYaw(this.endRotate.x);
      
      if (this.customModel instanceof LivingEntity livingEntity) {
         livingEntity.bodyYaw = this.endRotate.x;
         livingEntity.prevBodyYaw = this.endRotate.x;
         livingEntity.prevHeadYaw = this.endRotate.x;
         
         livingEntity.handSwingProgress = mc.player.handSwingProgress;
         livingEntity.lastHandSwingProgress = mc.player.lastHandSwingProgress;
         
         livingEntity.limbAnimator.setSpeed(mc.player.limbAnimator.getSpeed());
         
         if (this.hands.getValue()) {
            livingEntity.setStackInHand(Hand.MAIN_HAND, mc.player.getMainHandStack());
            livingEntity.setStackInHand(Hand.OFF_HAND, mc.player.getOffHandStack());
         } else {
            livingEntity.setStackInHand(Hand.MAIN_HAND, Items.AIR.getDefaultStack());
            livingEntity.setStackInHand(Hand.OFF_HAND, Items.AIR.getDefaultStack());
         }

         livingEntity.setPose(mc.player.getPose());
      }
   }

   public Box calculateBoundingBox(Entity entity, float size) {
      if (mc.player == null) return new Box(0,0,0,0,0,0);
      return mc.player.getBoundingBox();
   }

   public void onRotate(RotationEvent rotationEvent) {
      this.rotate.x = rotationEvent.getYaw();
      this.rotate.y = rotationEvent.getPitch();
   }

   public void onDisable() {
      if (this.customModel != null && mc.world != null) {
         mc.world.removeEntity(this.customModel.getId(), RemovalReason.DISCARDED);
         this.customModel = null;
      }
   }

   public void onEnable() {
      if (mc.player == null || mc.world == null) return;
      
      this.rotate = new Vector2f(mc.player.getYaw(), mc.player.getPitch());
      this.endRotate = new Vector2f(mc.player.getYaw(), mc.player.getPitch());
      
      switch (this.mode.getValue()) {
         case "Skeleton" -> this.customModel = new SkeletonEntity(EntityType.SKELETON, mc.world);
         case "Zombie" -> this.customModel = new ZombieEntity(mc.world);
         case "Pig" -> this.customModel = new PigEntity(EntityType.PIG, mc.world);
         case "Allay" -> this.customModel = new AllayEntity(EntityType.ALLAY, mc.world);
         case "Wargen" -> this.customModel = new WardenEntity(EntityType.WARDEN, mc.world);
         case "Piglin" -> this.customModel = new PiglinEntity(EntityType.PIGLIN, mc.world);
         case "Phantom" -> this.customModel = new PhantomEntity(EntityType.PHANTOM, mc.world);
         case "Vex" -> this.customModel = new VexEntity(EntityType.VEX, mc.world);
         case "Breeze" -> this.customModel = new BreezeEntity(EntityType.BREEZE, mc.world);
         case "Creeper" -> this.customModel = new CreeperEntity(EntityType.CREEPER, mc.world);
         case "Panda" -> this.customModel = new PandaEntity(EntityType.PANDA, mc.world);
         default -> this.customModel = new ZombieEntity(mc.world);
      }

      mc.world.addEntity(this.customModel);
   }

   public Entity getCustomModel() {
      return this.customModel;
   }

   public boolean isCustomEntity(Entity entity) {
      return entity != null && entity == this.customModel;
   }
}
