package dev.client.modules.impl.combat.elytraaura.math;

import dev.client.WildClient;
import dev.client.modules.impl.combat.ElytraAura;
import dev.client.util.IUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class ElytraAuraResolve implements IUtil {

    public static Vec3d getFinalTargetVector(LivingEntity entity, boolean fromYou) {
        Vec3d point = getPointOnTarget(entity);
        if (!entity.isGliding() || entity.isOnGround() || isStoyak(entity))
            return entity.getPos().add(0, entity.getHeight(), 0);

        // Simplified resolving without complex resolver classes
        if (fromYou)
            point = point.subtract(mc.player.getEyePos());

        return point;
    }

    public static Vec3d getPointOnTarget(LivingEntity entity) {
        ElytraAura elytraAura = (ElytraAura) WildClient.INSTANCE.getModuleManager().getByClass(ElytraAura.class);
        if (elytraAura == null) return entity.getPos().add(0, entity.getHeight() / 2, 0);

        return getPoint(entity, switch (elytraAura.typeAim.getValue()) {
            case "Resolve", "Middle" -> GETPOINT.MIDDLE;
            case "FireWork" -> GETPOINT.FIREWORK;
            default -> GETPOINT.DEFAULT;
        });
    }

    public static Vec3d getPoint(LivingEntity entity, GETPOINT getpoint) {
        return getPoint(mc.player.getEyePos(), entity, getpoint);
    }

    public static Vec3d getPoint(Vec3d pos, LivingEntity entity, GETPOINT getpoint) {
        if (pos == null) return Vec3d.ZERO;
        Vec3d vec3d = null;
        Vec3d resolve = entity.getPos();
        
        switch (getpoint) {
            case DEFAULT ->
                    vec3d = new Vec3d(
                            MathHelper.clamp(pos.x, entity.getBoundingBox().minX + 0.05f, entity.getBoundingBox().maxX - 0.05f),
                            MathHelper.clamp(pos.y, entity.getBoundingBox().minY + 0.05f, entity.getBoundingBox().maxY - 0.05f),
                            MathHelper.clamp(pos.z, entity.getBoundingBox().minZ + 0.05f, entity.getBoundingBox().maxZ - 0.05f)
                    );
            case FIREWORK -> {
                Vec3d interpolatedPos = getPoint(entity, GETPOINT.MIDDLE);

                // Simplified firework logic
                for (Entity e : mc.world.getEntities()) {
                    if (e instanceof FireworkRocketEntity firework) {
                        // In 1.21.4 we don't have a direct shooter check without mixins usually
                        // We can check distance
                        if (firework.getPos().distanceTo(entity.getPos()) < 1.0) {
                             interpolatedPos = firework.getPos();
                             break;
                        }
                    }
                }

                double minX = interpolatedPos.x - entity.getWidth() / 2.0;
                double maxX = interpolatedPos.x + entity.getWidth() / 2.0;
                double minY = interpolatedPos.y;
                double maxY = interpolatedPos.y + entity.getHeight();
                double minZ = interpolatedPos.z - entity.getWidth() / 2.0;
                double maxZ = interpolatedPos.z + entity.getWidth() / 2.0;

                vec3d = new Vec3d(
                        MathHelper.clamp(pos.x, minX + 0.05f, maxX - 0.05f),
                        MathHelper.clamp(pos.y, minY + 0.05f, maxY - 0.05f),
                        MathHelper.clamp(pos.z, minZ + 0.05f, maxZ - 0.05f)
                );
            }

            case MIDDLE -> {
                Vec3d interpolatedPos = resolve.add(0, entity.getHeight() / 2, 0);

                double minX = interpolatedPos.x - entity.getWidth() / 2.0;
                double maxX = interpolatedPos.x + entity.getWidth() / 2.0;
                double minY = interpolatedPos.y;
                double maxY = interpolatedPos.y + entity.getHeight();
                double minZ = interpolatedPos.z - entity.getWidth() / 2.0;
                double maxZ = interpolatedPos.z + entity.getWidth() / 2.0;

                vec3d = new Vec3d(
                        MathHelper.clamp(pos.x, minX + 0.05f, maxX - 0.05f),
                        MathHelper.clamp(pos.y, minY + 0.05f, maxY - 0.05f),
                        MathHelper.clamp(pos.z, minZ + 0.05f, maxZ - 0.05f)
                );
            }
        }

        return vec3d;
    }

    public static boolean isStoyak(LivingEntity entity) {
        if (entity == null) return false;
        // Basic check for staying still
        return entity.getX() == entity.prevX && entity.getY() == entity.prevY && entity.getZ() == entity.prevZ;
    }

    public enum GETPOINT {
        DEFAULT,
        MIDDLE,
        FIREWORK
    }
}
