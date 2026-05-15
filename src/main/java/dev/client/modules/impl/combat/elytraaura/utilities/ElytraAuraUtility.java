package dev.client.modules.impl.combat.elytraaura.utilities;

import dev.client.WildClient;
import dev.client.modules.impl.combat.ElytraAura;
import dev.client.modules.impl.combat.elytraaura.math.ElytraAuraResolve;
import dev.client.util.IUtil;
import dev.client.util.math.TimerUtil;
import dev.client.util.player.inventory.InventoryUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.math.Vec2f;

import java.util.ArrayList;
import java.util.List;

public class ElytraAuraUtility implements IUtil {
    private static final TimerUtil fireworkTime = new TimerUtil();
    public static boolean hut = false;

    public static Vec2f[] parsePitchOffsets(String pitchOffsetsString) {
        if (pitchOffsetsString == null || pitchOffsetsString.trim().isEmpty()) {
            return new Vec2f[]{new Vec2f(0, 0)};
        }

        String[] parts = pitchOffsetsString.split(",");
        List<Vec2f> offsets = new ArrayList<>();

        for (String part : parts) {
            try {
                float pitchValue = Float.parseFloat(part.trim());
                offsets.add(new Vec2f(0, pitchValue));
            } catch (NumberFormatException e) {
            }
        }

        if (offsets.isEmpty()) {
            return new Vec2f[]{new Vec2f(0, 0)};
        }

        return offsets.toArray(new Vec2f[0]);
    }

    public static boolean useFireWork(LivingEntity entity) {
        ElytraAura elytraAura = (ElytraAura) WildClient.INSTANCE.getModuleManager().getByClass(ElytraAura.class);
        if (elytraAura == null || !elytraAura.isEnabled()) return false;

        if (elytraAura.autoFireWorkSetting.getValue()) {
            if (elytraAura.autoAirStackSetting.getValue() && elytraAura.ifworkAitStack.getValueByName("Target on Stoyak")) {
                boolean iso = mc.player.getPos().distanceTo(entity.getPos()) > 5.4 && ElytraAuraResolve.isStoyak(entity) && !entity.isOnGround() && !elytraAura.isLeave(entity);
                if (iso)
                    return false;
            }

            // client$lastAttackedTicks check replaced with a simpler one or skipped
            
            if (elytraAura.defensive.getValue()) { // used getValue as a proxy for toggleable group enabled state if applicable
                if (elytraAura.lastAntiaim && !elytraAura.antiAimIsActive) {
                    elytraAura.lastAntiaim = false;
                    useItemOnHotbar(Items.FIREWORK_ROCKET);
                    return true;
                }
            }
            
            if (elytraAura.smartUseFireWorkSetting.getValue()) {
                int delay = 400;
                if (mc.player.getPos().distanceTo(entity.getPos()) < 4) delay = 270;
                if (elytraAura.targetIsLeave(entity)) delay = 400;
                if (entity.isOnGround() || !entity.isGliding() || ElytraAuraResolve.isStoyak(entity)) delay = 450;
                
                if (!elytraAura.antiAimIsActive && hut) {
                    if (fireworkTime.isReached(10)) {
                        useItemOnHotbar(Items.FIREWORK_ROCKET);
                        fireworkTime.reset();
                        hut = false;
                        return true;
                    }
                }
                
                if (fireworkTime.isReached(delay) && mc.player.isGliding()) {
                    useItemOnHotbar(Items.FIREWORK_ROCKET);
                    fireworkTime.reset();
                    return true;
                }
            } else {
                if (fireworkTime.isReached((long) elytraAura.delayUseFireWorkSetting.getValue()) && mc.player.isGliding()) {
                    useItemOnHotbar(Items.FIREWORK_ROCKET);
                    fireworkTime.reset();
                    return true;
                }
            }
        }
        return false;
    }

    public static void useItemOnHotbar(Item item) {
        int slot = getItemOnHotbar(item);

        if (slot == -1) {
            int fireworkSlot = findItemInInventory(item);
            if (fireworkSlot != -1) {
                int freeHotbarSlot = findFreeHotbarSlot();
                if (freeHotbarSlot != -1) {
                    swapSlots(fireworkSlot, freeHotbarSlot);
                    slot = freeHotbarSlot;
                }
            }
        }

        if (slot != -1 && !mc.player.getItemCooldownManager().isCoolingDown(item.getDefaultStack())) {
            InventoryUtil.sendFireWork(mc.player.getYaw(), mc.player.getPitch());
        }
    }

    private static int findItemInInventory(Item item) {
        for (int i = 9; i < 36; i++) {
            if (mc.player.getInventory().getStack(i).getItem() == item) {
                return i;
            }
        }
        return -1;
    }

    private static int findFreeHotbarSlot() {
        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    private static void swapSlots(int fromSlot, int toSlot) {
        mc.interactionManager.clickSlot(
                mc.player.currentScreenHandler.syncId,
                fromSlot < 9 ? fromSlot + 36 : fromSlot,
                toSlot,
                SlotActionType.SWAP,
                mc.player
        );
    }

    public static int getItemOnHotbar(Item item) {
        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).getItem() == item) {
                return i;
            }
        }
        return -1;
    }
}
