package dev.client.modules.impl.combat;

import dev.client.WildClient;
import dev.client.event.classes.RotationEvent;
import dev.client.event.classes.TickEvent;
import dev.client.event.interfaces.IRotateable;
import dev.client.event.interfaces.ITickable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.modules.settings.impl.FloatSetting;
import dev.client.modules.settings.impl.MultiBoxSetting;
import dev.client.util.IUtil;
import dev.client.util.math.TimerUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Environment(EnvType.CLIENT)
public class LegitAura extends Module implements ITickable, IRotateable, IUtil {
    public final MultiBoxSetting settings = new MultiBoxSetting().name("Settings").booleanSettings(
            new BooleanSetting().name("OnlyCrit").value(true),
            new BooleanSetting().name("SmartCrits").value(false),
            new BooleanSetting().name("NoEatAttack").value(true),
            new BooleanSetting().name("ThroughWalls").value(false)
    );
    private final FloatSetting distanceSetting = new FloatSetting().name("Distance").value(3.0F).minValue(2.5F).maxValue(6.0F).incriment(0.1F);
    private final FloatSetting rotateDistance = new FloatSetting().name("Rotate Distance").value(0.0F).minValue(0.0F).maxValue(3.0F).incriment(0.1F);
    private final FloatSetting speed = new FloatSetting().name("Speed").value(0.2F).minValue(0.1F).maxValue(3.0F).incriment(0.1F);
    private final FloatSetting elytrarotate = new FloatSetting().name("Elytra Rotate").value(12.5F).minValue(0.0F).maxValue(64.0F).incriment(0.5F);
    private final FloatSetting elytradist = new FloatSetting().name("Elytra Distance").value(0.7F).minValue(0.0F).maxValue(0.7F).incriment(0.05F);
    private final FloatSetting speedelytra = new FloatSetting().name("Elytra Speed").value(0.8F).minValue(0.1F).maxValue(5.0F).incriment(0.1F);
    private final MultiBoxSetting targets = new MultiBoxSetting().name("Targets").booleanSettings(
            new BooleanSetting().name("Players").value(true),
            new BooleanSetting().name("Nakeds").value(true),
            new BooleanSetting().name("Mobs").value(false),
            new BooleanSetting().name("Friends").value(false),
            new BooleanSetting().name("Invisibles").value(true),
            new BooleanSetting().name("Naked Invisibles").value(true)
    );

    private LivingEntity currentTarget;
    private Vector2f rotate = new Vector2f(0.0F, 0.0F);
    private final TimerUtil attackTimer = new TimerUtil();

    public LegitAura() {
        super(new ModuleBranding("LegitAura", Category.COMBAT, "Легитная киллаура с плавными наводками"));
        this.addSetting(this.targets, this.distanceSetting, this.rotateDistance, this.speed, this.settings, this.elytrarotate, this.elytradist, this.speedelytra);
    }

    @Override
    public void onTick(TickEvent event) {
        if (mc.player == null || mc.world == null) return;

        if (this.currentTarget == null || !this.isValidTarget(this.currentTarget)) {
            this.currentTarget = this.findTarget();
        }

        if (this.currentTarget != null) {
            double distance = mc.player.distanceTo(this.currentTarget);
            float elytraBonus = mc.player.isGliding() ? this.elytrarotate.getValue() : 0.0F;
            double maxDist = this.distanceSetting.getValue() + this.rotateDistance.getValue() + elytraBonus;

            if (distance <= maxDist) {
                if (!this.settings.getValueByName("ThroughWalls") && !mc.player.canSee(this.currentTarget)) {
                    this.currentTarget = null;
                    return;
                }

                float[] targetRotations = this.calculateRotations(this.currentTarget);
                float yawSpeed = (mc.player.isGliding() ? this.speedelytra.getValue() : this.speed.getValue()) / 300.0F;
                float pitchSpeed = (mc.player.isGliding() ? this.speedelytra.getValue() : this.speed.getValue()) / 650.0F;

                float yaw = this.smoothRotation(this.rotate.x, targetRotations[0], yawSpeed * 100.0F);
                float pitch = this.smoothRotation(this.rotate.y, targetRotations[1], pitchSpeed * 100.0F);
                this.rotate = new Vector2f(yaw, pitch);

                if (this.canAttack()) {
                    this.attack();
                }
            } else {
                this.currentTarget = null;
            }
        } else {
            this.rotate = new Vector2f(mc.player.getYaw(), mc.player.getPitch());
        }
    }

    private boolean canAttack() {
        if (this.settings.getValueByName("NoEatAttack") && mc.player.isUsingItem()) return false;
        
        boolean ready = mc.player.getAttackCooldownProgress(0.5F) >= 0.92F;
        if (!ready) return false;

        if (this.settings.getValueByName("OnlyCrit")) {
            boolean falling = !mc.player.isOnGround() && mc.player.fallDistance > 0.0F;
            if (this.settings.getValueByName("SmartCrits")) {
                if (mc.options.jumpKey.isPressed()) {
                    return falling;
                }
            } else {
                return falling;
            }
        }
        
        return true;
    }

    private void attack() {
        if (this.currentTarget == null) return;
        
        // Raytrace check for legit feel
        if (!this.rayTrace()) return;

        if (this.attackTimer.isReached(500L)) {
            mc.interactionManager.attackEntity(mc.player, this.currentTarget);
            mc.player.swingHand(Hand.MAIN_HAND);
            this.attackTimer.reset();
        }
    }

    private boolean rayTrace() {
        if (this.currentTarget == null) return false;
        
        double reach = this.distanceSetting.getValue();
        Vec3d eyePos = mc.player.getEyePos();
        Vec3d lookVec = this.getRotationVec(this.rotate.y, this.rotate.x);
        Vec3d endPos = eyePos.add(lookVec.multiply(reach));
        
        HitResult result = this.currentTarget.getBoundingBox().rayCast(eyePos, endPos).orElse(null);
        return result != null;
    }

    private Vec3d getRotationVec(float pitch, float yaw) {
        float f = pitch * 0.017453292F;
        float g = -yaw * 0.017453292F;
        float h = MathHelper.cos(g);
        float i = MathHelper.sin(g);
        float j = MathHelper.cos(f);
        float k = MathHelper.sin(f);
        return new Vec3d((double)(i * j), (double)(-k), (double)(h * j));
    }

    private LivingEntity findTarget() {
        List<LivingEntity> targets = new ArrayList<>();
        float elytraBonus = mc.player.isGliding() ? this.elytrarotate.getValue() : 0.0F;
        double maxDist = this.distanceSetting.getValue() + this.rotateDistance.getValue() + elytraBonus;

        for (Entity entity : mc.world.getEntities()) {
            if (entity instanceof LivingEntity living) {
                if (this.isValidTarget(living) && mc.player.distanceTo(living) <= maxDist) {
                    targets.add(living);
                }
            }
        }

        if (targets.isEmpty()) return null;
        targets.sort(Comparator.comparingDouble(e -> mc.player.distanceTo(e)));
        return targets.get(0);
    }

    private boolean isValidTarget(LivingEntity entity) {
        if (entity == null || entity == mc.player || !entity.isAlive() || entity instanceof ArmorStandEntity) return false;
        
        if (entity instanceof PlayerEntity player) {
            if (!this.targets.getValueByName("Friends") && WildClient.INSTANCE.getFriendManager().isFriend(player.getName().getString())) return false;
            if (!this.targets.getValueByName("Players")) return false;
            
            boolean isNaked = player.getArmor() == 0;
            if (isNaked && !this.targets.getValueByName("Nakeds")) return false;
            
            if (player.isInvisible()) {
                if (isNaked && !this.targets.getValueByName("Naked Invisibles")) return false;
                if (!this.targets.getValueByName("Invisibles")) return false;
            }
        } else if (entity instanceof Monster) {
            if (!this.targets.getValueByName("Mobs")) return false;
        } else if (entity instanceof AnimalEntity) {
            if (!this.targets.getValueByName("Mobs")) return false; // Grouping animals with mobs for simplicity or add more settings
        } else {
            return false;
        }

        return true;
    }

    private float smoothRotation(float current, float target, float speed) {
        float delta = MathHelper.wrapDegrees(target - current);
        float dynamicSpeed = speed * (1.0F + Math.abs(delta) / 180.0F);
        dynamicSpeed = Math.min(dynamicSpeed, 30.0F);
        float adjustment = delta * dynamicSpeed * 0.05F;
        return current + MathHelper.clamp(adjustment, -dynamicSpeed, dynamicSpeed);
    }

    private float[] calculateRotations(Entity entity) {
        Vec3d eyePos = mc.player.getEyePos();
        double entityX = entity.getX();
        double entityZ = entity.getZ();
        double entityY;

        if (mc.player.isGliding()) {
            entityY = entity.getY() + (double)entity.getHeight() / 2.0D;
        } else {
            double halfWidth = (double)entity.getWidth() / 2.0D;
            double closestX = MathHelper.clamp(eyePos.x, entityX - halfWidth, entityX + halfWidth);
            double closestZ = MathHelper.clamp(eyePos.z, entityZ - halfWidth, entityZ + halfWidth);
            double closestY = MathHelper.clamp(eyePos.y, entity.getY(), entity.getY() + (double)entity.getHeight());
            
            double deltaX = closestX - eyePos.x;
            double deltaY = closestY - eyePos.y;
            double deltaZ = closestZ - eyePos.z;
            double distHorizontal = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
            
            float yaw = (float)(Math.atan2(deltaZ, deltaX) * 57.29577951308232D) - 90.0F;
            float pitch = (float)(-(Math.atan2(deltaY, distHorizontal) * 57.29577951308232D));
            
            // Add some jitter for "legit" feel
            yaw += (ThreadLocalRandom.current().nextFloat() - 0.5F) * 1.8F;
            pitch += (ThreadLocalRandom.current().nextFloat() - 0.5F) * 1.4F;
            
            return new float[]{yaw, pitch};
        }

        double deltaX = entityX - eyePos.x;
        double deltaY = entityY - eyePos.y;
        double deltaZ = entityZ - eyePos.z;
        double distHorizontal = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        
        float yaw = (float)(Math.atan2(deltaZ, deltaX) * 57.29577951308232D) - 90.0F;
        float pitch = (float)(-(Math.atan2(deltaY, distHorizontal) * 57.29577951308232D));
        
        return new float[]{yaw, pitch};
    }

    @Override
    public void onRotate(RotationEvent rotationEvent) {
        if (this.currentTarget != null) {
            rotationEvent.setYaw(this.rotate.x);
            rotationEvent.setPitch(this.rotate.y);
            mc.player.headYaw = this.rotate.x;
            mc.player.bodyYaw = this.rotate.x;
        }
    }

    @Override
    public void onDisable() {
        this.currentTarget = null;
        super.onDisable();
    }
}
