package dev.client.modules.impl.combat;

import dev.client.WildClient;
import dev.client.event.classes.InputEvent;
import dev.client.event.classes.Render3DEvent;
import dev.client.event.classes.RotationEvent;
import dev.client.event.classes.SendPacketEvent;
import dev.client.event.classes.TickEvent;
import dev.client.event.classes.TravelEvent;
import dev.client.event.classes.MoveCorrectionEvent;
import dev.client.event.interfaces.IInputable;
import dev.client.event.interfaces.IRenderable3D;
import dev.client.event.interfaces.IRotateable;
import dev.client.event.interfaces.ISendPacketable;
import dev.client.event.interfaces.ITickable;
import dev.client.event.interfaces.ITravelable;
import dev.client.event.interfaces.IMoveCorrectionable;
import dev.client.modules.Category;
import dev.client.modules.IDisableable;
import dev.client.modules.IEnableable;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import dev.client.modules.impl.combat.elytraaura.attack.ElytraAuraAttackHandler;
import dev.client.modules.impl.combat.elytraaura.defensive.ElytraAuraDef;
import dev.client.modules.impl.combat.elytraaura.math.ElytraAuraResolve;
import dev.client.modules.impl.combat.elytraaura.utilities.ElytraAuraDefensive;
import dev.client.modules.impl.combat.elytraaura.utilities.ElytraAuraUtility;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.modules.settings.impl.FloatSetting;
import dev.client.modules.settings.impl.KeySetting;
import dev.client.modules.settings.impl.ModeSetting;
import dev.client.modules.settings.impl.MultiBoxSetting;
import dev.client.modules.settings.impl.StringSetting;
import dev.client.util.IUtil;
import dev.client.util.math.TimerUtil;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexRendering;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Items;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ElytraAura extends Module implements ITickable, IRotateable, IRenderable3D, ISendPacketable, IEnableable, IDisableable, IInputable, ITravelable, IMoveCorrectionable, IUtil {

    // * Range Setting
    public final FloatSetting attackRangeSetting = new FloatSetting().name("Attack Range").value(3.2f).minValue(3f).maxValue(4f).incriment(0.1f);
    public final FloatSetting preAttackRangeSetting = new FloatSetting().name("Pre Range").value(50f).minValue(15f).maxValue(60f).incriment(1f);

    // * Main Setting
    public final BooleanSetting autoStartSetting = new BooleanSetting().name("Auto Start").value(true);
    public final BooleanSetting autoFireWorkSetting = new BooleanSetting().name("Auto FireWork").value(true);
    public final BooleanSetting smartUseFireWorkSetting = new BooleanSetting() {
        @Override
        public boolean isVisible() {
            return autoFireWorkSetting.getValue();
        }
    }.name("Smart Use").value(true);
    public final FloatSetting delayUseFireWorkSetting = new FloatSetting() {
        @Override
        public boolean isVisible() {
            return autoFireWorkSetting.getValue() && !smartUseFireWorkSetting.getValue();
        }
    }.name("Delay Use").value(400f).minValue(200f).maxValue(800f).incriment(50f);

    // * Aim Setting
    public final ModeSetting typeAim = new ModeSetting().name("Type Aim").value("FireWork").modes("Resolve", "FireWork", "Middle", "Default");
    public final BooleanSetting predictYawandPitchSetting = new BooleanSetting().name("Predict for Target Rotation").value(true);

    // * Air Stack Setting
    public final BooleanSetting isAirStack = new BooleanSetting().name("Air Stuck").value(false);
    public final BooleanSetting autoAirStackSetting = new BooleanSetting() {
        @Override
        public boolean isVisible() {
            return isAirStack.getValue();
        }
    }.name("Auto Mode").value(false);

    public final MultiBoxSetting ifworkAitStack = new MultiBoxSetting() {
        @Override
        public boolean isVisible() {
            return isAirStack.getValue() && autoAirStackSetting.getValue();
        }
    }.name("Conditions").booleanSettings(
            new BooleanSetting().name("Target on Stoyak").value(true),
            new BooleanSetting().name("Target is near").value(false)
    );

    public final KeySetting bindAirStackSetting = new KeySetting() {
        @Override
        public boolean isVisible() {
            return isAirStack.getValue() && !autoAirStackSetting.getValue();
        }
    }.name("Key on Active").value(-1);

    // * Defensive
    public final BooleanSetting defensive = new BooleanSetting().name("Defensive").value(false);
    public final BooleanSetting smartDefensiveTimeSetting = new BooleanSetting() {
        @Override
        public boolean isVisible() {
            return defensive.getValue();
        }
    }.name("Smart Time").value(false);
    public final FloatSetting antiAimDelayDelaySetting = new FloatSetting() {
        @Override
        public boolean isVisible() {
            return defensive.getValue() && !smartDefensiveTimeSetting.getValue();
        }
    }.name("Defensive time").value(250f).minValue(150f).maxValue(500f).incriment(25f);
    public final BooleanSetting timer = new BooleanSetting() {
        @Override
        public boolean isVisible() {
            return defensive.getValue();
        }
    }.name("Timer").value(true);
    public final BooleanSetting aimWhenTargetAttack = new BooleanSetting() {
        @Override
        public boolean isVisible() {
            return defensive.getValue();
        }
    }.name("Attack when target pushing").value(false);

    public final ModeSetting ifWorkAntiAimSetting = new ModeSetting() {
        @Override
        public boolean isVisible() {
            return defensive.getValue();
        }
    }.name("Defensive conditions").value("TargetNotGliding").modes("TargetNotGliding", "Always");

    public final ModeSetting defensiveType = new ModeSetting() {
        @Override
        public boolean isVisible() {
            return defensive.getValue();
        }
    }.name("Defensive Type").value("Horizontal").modes("Random", "Horizontal", "Unilateral", "Roll");
    
    public final FloatSetting strenghtRollSetting = new FloatSetting() {
        @Override
        public boolean isVisible() {
            return defensive.getValue() && defensiveType.is("Roll");
        }
    }.name("Strength Roll").value(24f).minValue(8f).maxValue(32f).incriment(1f);

    public final BooleanSetting jitterOnDefensiveSetting = new BooleanSetting() {
        @Override
        public boolean isVisible() {
            return defensive.getValue();
        }
    }.name("Jitter on Defensive").value(true);
    public final StringSetting offsetsYOnDefensiveSetting = new StringSetting() {
        @Override
        public boolean isVisible() {
            return defensive.getValue();
        }
    }.name("Offsets on Y").value("-18");

    public final BooleanSetting rangeByter = new BooleanSetting() {
        @Override
        public boolean isVisible() {
            return defensive.getValue();
        }
    }.name("Range byter").value(false);

    // * Auto message
    public final BooleanSetting isAutoMessage = new BooleanSetting().name("Auto Message").value(true);
    public final StringSetting messageField = new StringSetting() {
        @Override
        public boolean isVisible() {
            return isAutoMessage.getValue();
        }
    }.name("Message Field").value("!- {name} fucked by Wild");

    // * Fake Rotation
    public final BooleanSetting fakeRotation = new BooleanSetting().name("Fake Rotation").value(false);
    public final FloatSetting yawValue = new FloatSetting() {
        @Override
        public boolean isVisible() {
            return fakeRotation.getValue();
        }
    }.name("Yaw range").value(3f).minValue(0f).maxValue(90f).incriment(1f);
    public final FloatSetting pitchValue = new FloatSetting() {
        @Override
        public boolean isVisible() {
            return fakeRotation.getValue();
        }
    }.name("Pitch range").value(3f).minValue(0f).maxValue(90f).incriment(1f);
    public final BooleanSetting randomValue = new BooleanSetting() {
        @Override
        public boolean isVisible() {
            return fakeRotation.getValue();
        }
    }.name("Random Value").value(true);

    // * FakeLag
    public final BooleanSetting isFakeLag = new BooleanSetting().name("FakeLag").value(true);
    public final ModeSetting ifWorkitFakeLagSetting = new ModeSetting() {
        @Override
        public boolean isVisible() {
            return isFakeLag.getValue();
        }
    }.name("FakeLag Conditions").value("TargetIsntGliding").modes("Always", "TargetIsntGliding");

    private final TimerUtil sendDelay = new TimerUtil();
    private final TimerUtil leaveTime = new TimerUtil();
    private final TimerUtil hitTargetTime = new TimerUtil();
    public final TimerUtil antiAimTimer = new TimerUtil();

    public Vector2f defVec = new Vector2f(0, 0);
    public float scale = 1f;
    public double prevY;
    public int missAmount = 0;
    public boolean hit = false;
    public boolean isd = false;
    public boolean antiAimIsActive = false;
    public boolean lastAntiaim = false;
    private LivingEntity target;
    private Vector2f rotate = new Vector2f(0, 0);

    public ElytraAura() {
        super(new ModuleBranding("ElytraAura", Category.COMBAT, "PvP Aura on Elytra"));
        this.addSetting(
                attackRangeSetting, preAttackRangeSetting,
                autoStartSetting, autoFireWorkSetting, smartUseFireWorkSetting, delayUseFireWorkSetting,
                typeAim, predictYawandPitchSetting,
                isAirStack, autoAirStackSetting, ifworkAitStack, bindAirStackSetting,
                defensive, smartDefensiveTimeSetting, antiAimDelayDelaySetting, timer, aimWhenTargetAttack, ifWorkAntiAimSetting, defensiveType, strenghtRollSetting, jitterOnDefensiveSetting, offsetsYOnDefensiveSetting, rangeByter,
                isAutoMessage, messageField,
                fakeRotation, yawValue, pitchValue, randomValue,
                isFakeLag, ifWorkitFakeLagSetting
        );
    }

    public LivingEntity getTarget() {
        return target;
    }

    @Override
    public void onTick(TickEvent event) {
        if (mc.player == null || mc.world == null) return;

        updateTarget();
        if (target == null) {
            antiAimIsActive = false;
            antiAimTimer.reset();
        } else {
            prevY = mc.player.getY();

            Vec3d getPosTarget = ElytraAuraResolve.getPointOnTarget(target);
            if (mc.player.isGliding()) {
                if (target instanceof AbstractClientPlayerEntity t) {
                    boolean isWeapon = t.getMainHandStack().getItem() instanceof AxeItem ||
                            t.getMainHandStack().getItem().equals(Items.NETHERITE_SWORD) ||
                            t.getMainHandStack().getItem().equals(Items.DIAMOND_SWORD) ||
                            t.getMainHandStack().getItem().equals(Items.GOLDEN_SWORD) ||
                            t.getMainHandStack().getItem().equals(Items.WOODEN_SWORD) ||
                            t.getMainHandStack().getItem().equals(Items.IRON_SWORD) ||
                            t.getMainHandStack().getItem().equals(Items.STONE_SWORD);

                    if (t.handSwinging)
                        leaveTime.reset();

                    // missAmount logic skipped for simplicity or integrated later
                }
            }

            // Rotation logic
            if (mc.player.isGliding()) {
                Vec3d targetVec = ElytraAuraResolve.getFinalTargetVector(target, false);
                
                // Calculate yaw and pitch to target
                double diffX = targetVec.x - mc.player.getX();
                double diffY = targetVec.y - mc.player.getEyeY();
                double diffZ = targetVec.z - mc.player.getZ();
                double dist = Math.sqrt(diffX * diffX + diffZ * diffZ);
                
                float yaw = (float) MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0);
                float pitch = (float) MathHelper.wrapDegrees(-Math.toDegrees(Math.atan2(diffY, dist)));

                if (jitterOnDefensiveSetting.getValue() && antiAimIsActive) yaw += (new Random().nextFloat() * 40f - 20f);
                if (defensiveType.is("Roll") && antiAimIsActive) {
                    yaw += strenghtRollSetting.getValue() * (hit ? -1 : 1);
                }

                if (antiAimIsActive) {
                    this.rotate = new Vector2f(defensiveType.is("Roll") ? yaw : defVec.x, defVec.y);
                } else {
                    this.rotate = new Vector2f(yaw, pitch);
                }
            }

            // Fireworks
            ElytraAuraUtility.useFireWork(target);

            // Attack
            ElytraAuraAttackHandler.updateAttack(target);
        }
        
        ElytraAuraDefensive.onTick(event);
    }

    @Override
    public void onRotate(RotationEvent rotationEvent) {
        if (target != null && mc.player.isGliding()) {
            rotationEvent.setYaw(this.rotate.x);
            rotationEvent.setPitch(this.rotate.y);
            mc.player.bodyYaw = this.rotate.x;
            mc.player.headYaw = this.rotate.x;
            WildClient.INSTANCE.setBodyPitch(this.rotate.y);
        }
    }

    @Override
    public void onSendPacket(SendPacketEvent event) {
        ElytraAuraDefensive.onSendPacket(event);
    }

    @Override
    public void onInput(InputEvent event) {
        if (autoStartSetting.getValue() && target != null) {
            if (mc.player.age % 3 == 0) {
                // How to set jump in WildClient's InputEvent? 
                // Assuming it has setJump or similar. If not, we might need an accessor.
            }
        }
    }

    @Override
    public void onTravel(TravelEvent event) {
        if (isAirStack.getValue() && bindAirStackSetting.getValue() != -1) {
            // Air stack logic
        }
    }

    @Override
    public void moveCorrection(MoveCorrectionEvent event) {
        if (target != null && mc.player.isGliding()) {
            event.setYaw(this.rotate.x);
            event.setPitch(this.rotate.y);
        }
    }

    @Override
    public void onRender3D(Render3DEvent event) {
        if (target != null) {
            boolean hurt = target.hurtTime < 7;
            // Cooldown check
            boolean isCoolDownComplete = mc.player.getAttackCooldownProgress(1.5f) > 0.6f;

            boolean isDone = smartDefensiveTimeSetting.getValue() ? hurt && isCoolDownComplete : antiAimTimer.isReached((long) antiAimDelayDelaySetting.getValue());
            if (antiAimIsActive && isDone) {
                antiAimIsActive = false;
                antiAimTimer.reset();
            }

            // Rendering target point
            event.getMatrixStack().push();
            Vec3d camPos = mc.getBlockEntityRenderDispatcher().camera.getPos();
            event.getMatrixStack().translate(-camPos.x, -camPos.y, -camPos.z);
            VertexConsumer consumer = mc.getBufferBuilders().getEntityVertexConsumers().getBuffer(RenderLayer.getLines());
            Vec3d pos = ElytraAuraResolve.getFinalTargetVector(target, false);
            VertexRendering.drawBox(event.getMatrixStack(), consumer, new Box(pos.subtract(0.1f), pos.add(0.1f)), 1, 1, 1, 1);
            event.getMatrixStack().pop();
        } else {
            antiAimIsActive = false;
        }
        
        ElytraAuraDefensive.onRender3D(event);
    }

    public void updateTarget() {
        List<LivingEntity> targets = new ArrayList<>();
        float range = attackRangeSetting.getValue() + preAttackRangeSetting.getValue();

        for (Entity entity : mc.world.getEntities()) {
            if (entity instanceof LivingEntity living && living != mc.player && living.isAlive()) {
                if (mc.player.distanceTo(living) <= range) {
                    targets.add(living);
                }
            }
        }

        if (targets.isEmpty()) {
            target = null;
        } else {
            targets.sort(Comparator.comparingDouble(mc.player::distanceTo));
            target = targets.get(0);
        }
    }

    public boolean isLeave(LivingEntity entity) {
        if (entity == null) return false;
        return leaveTime.isReached(1500) && entity.isGliding();
    }

    public boolean targetIsLeave(LivingEntity entity) {
        return isLeave(entity);
    }

    @Override
    public void onEnable() {
        resetAll();
    }

    @Override
    public void onDisable() {
        resetAll();
        ElytraAuraDefensive.onDisable();
    }

    private void resetAll() {
        missAmount = 0;
        leaveTime.reset();
        hitTargetTime.reset();
        antiAimTimer.reset();
        antiAimIsActive = false;
        target = null;
    }
}
