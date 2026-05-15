package dev.client.modules.impl.combat.elytraaura.utilities;

import dev.client.WildClient;
import dev.client.event.classes.Render3DEvent;
import dev.client.event.classes.SendPacketEvent;
import dev.client.event.classes.TickEvent;
import dev.client.modules.impl.combat.ElytraAura;
import dev.client.modules.impl.combat.elytraaura.math.ElytraAuraResolve;
import dev.client.util.IUtil;
import dev.client.util.math.TimerUtil;
import dev.client.util.player.PacketUtil;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexRendering;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.common.KeepAliveC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.awt.*;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ElytraAuraDefensive implements IUtil {
    private static final TimerUtil blinkTime = new TimerUtil();
    public static boolean blinking = false;
    private static final Queue<Packet<?>> packetQueue = new ConcurrentLinkedQueue<>();
    public static boolean isMotion = false;
    private static Vec3d lastBlinked = Vec3d.ZERO;

    public static void onRender3D(Render3DEvent e) {
        ElytraAura elytraAura = (ElytraAura) WildClient.INSTANCE.getModuleManager().getByClass(ElytraAura.class);
        if (elytraAura == null || !elytraAura.isEnabled()) return;

        e.getMatrixStack().push();
        // Client.RENDERER.toCamera(e.stack); -> WildClient Render3DEvent usually already has stack in world space or camera space
        // Assuming WildClient's Render3DEvent is in world space but needs camera offset
        Vec3d camPos = mc.getBlockEntityRenderDispatcher().camera.getPos();
        e.getMatrixStack().translate(-camPos.x, -camPos.y, -camPos.z);
        
        VertexConsumer consumer = mc.getBufferBuilders().getEntityVertexConsumers().getBuffer(RenderLayer.getLines());
        Vec3d pos = lastBlinked;
        Vec3d pos2 = mc.player.getLerpedPos(mc.getRenderTickCounter().getTickDelta(true));

        VertexRendering.drawBox(e.getMatrixStack(), consumer, new Box(pos.subtract(0.1f), pos.add(0.1f)), 1, 1, 0, 1);
        e.getMatrixStack().pop();
    }

    public static void onTick(TickEvent event) {
        ElytraAura elytraAura = (ElytraAura) WildClient.INSTANCE.getModuleManager().getByClass(ElytraAura.class);
        if (elytraAura == null || !elytraAura.isEnabled()) {
            blinking = false;
            blink();
            return;
        }

        LivingEntity entity = elytraAura.getTarget();
        if (entity != null) {
            isMotion = !entity.isGliding();
        }

        if (!elytraAura.defensive.getValue()) {
            blinking = false;
            blink();
            return;
        }

        if (elytraAura.targetIsLeave(entity) || !isMotion) {
            blinking = false;
            blink();
            return;
        }

        if (entity == null) return;

        if (mc.player.hurtTime > 0
                || mc.player.getPos().distanceTo(new Vec3d(mc.player.prevX, mc.player.prevY, mc.player.prevZ)) < 0.15
                || blinkTime.isReached(600)
                || mc.player.distanceTo(entity) > 5
                || elytraAura.targetIsLeave(entity)) {
            blink();
            blinkTime.reset();
        }

        blinking = isMotion;
    }

    public static void onSendPacket(SendPacketEvent e) {
        ElytraAura elytraAura = (ElytraAura) WildClient.INSTANCE.getModuleManager().getByClass(ElytraAura.class);
        if (elytraAura == null || !elytraAura.isEnabled()) return;

        LivingEntity entity = elytraAura.getTarget();
        if (entity == null) return;

        if (e.getPacket() instanceof KeepAliveC2SPacket) return;

        boolean work = !elytraAura.ifWorkitFakeLagSetting.is("Target isn't elytra flying") 
                || (!entity.isGliding() || entity.isOnGround() || ElytraAuraResolve.isStoyak(entity));

        if (elytraAura.isFakeLag.getValue() && work) {
            packetQueue.add(e.getPacket());
            e.cancel();
            blinking = true;
        }
    }

    public static void blink() {
        for (Packet<?> packet : packetQueue) {
            if (packet instanceof PlayerMoveC2SPacket move) {
                lastBlinked = new Vec3d(move.getX(mc.player.getX()), move.getY(mc.player.getY()), move.getZ(mc.player.getZ()));
            }
            PacketUtil.sendPacket(packet);
        }
        packetQueue.clear();
        blinking = false;
    }

    public static void onDisable() {
        blinking = false;
        blink();
    }
}
