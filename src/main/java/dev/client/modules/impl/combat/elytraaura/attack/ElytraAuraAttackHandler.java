package dev.client.modules.impl.combat.elytraaura.attack;

import dev.client.WildClient;
import dev.client.modules.impl.combat.ElytraAura;
import dev.client.modules.impl.combat.elytraaura.math.ElytraAuraResolve;
import dev.client.util.IUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;

public class ElytraAuraAttackHandler implements IUtil {

    public static void updateAttack(LivingEntity entity) {
        if (mc.player == null || mc.world == null || entity == null) return;
        
        ElytraAura elytraAura = (ElytraAura) WildClient.INSTANCE.getModuleManager().getByClass(ElytraAura.class);
        if (elytraAura == null || !elytraAura.isEnabled()) return;

        if ((mc.player.getPos().distanceTo(ElytraAuraResolve.getPointOnTarget(entity)) <= attackDistance(entity)) 
                && mc.player.getAttackCooldownProgress(1.0f) >= 0.95f) {
            mc.interactionManager.attackEntity(mc.player, entity);
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }

    public static float attackDistance(LivingEntity entity) {
        ElytraAura elytraAura = (ElytraAura) WildClient.INSTANCE.getModuleManager().getByClass(ElytraAura.class);
        if (elytraAura == null) return 3.2f;
        
        return entity.isGliding() ? Math.max(elytraAura.isLeave(entity) ? 4.5f : 3.2f, elytraAura.attackRangeSetting.getValue()) : 2.8f;
    }
}
