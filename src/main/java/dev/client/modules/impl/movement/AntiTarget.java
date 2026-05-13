package dev.client.modules.impl.movement;

import dev.client.WildClient;
import dev.client.event.classes.TickEvent;
import dev.client.event.interfaces.ITickable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import dev.client.modules.settings.impl.FloatSetting;
import dev.client.modules.settings.impl.ModeSetting;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class AntiTarget extends Module implements ITickable, IUtil {
    private final ModeSetting mode = new ModeSetting().name("Mode").value("Normal").modes("Normal", "Fast");
    private final FloatSetting pitch = new FloatSetting() {
        @Override
        public boolean isVisible() {
            return AntiTarget.this.mode.is("Normal");
        }
    }.name("Pitch").value(35.0F).minValue(30.0F).maxValue(50.0F).incriment(1.0F);

    public AntiTarget() {
        super(new ModuleBranding("AntiTarget", Category.MOVEMENT, "Не даёт вас затаргетить на элитрах"));
        this.addSetting(this.mode, this.pitch);
    }

    @Override
    public void onTick(TickEvent event) {
        if (mc.player == null) return;
        
        // Skip if aura has a target
        var aura = WildClient.INSTANCE.getModuleManager().getByClass(dev.client.modules.impl.combat.Aura.class);
        if (aura != null && aura.isEnabled() && aura.getTarget() != null) {
            return;
        }

        if (mc.player.isGliding()) { // check for elytra flying using isGliding
            if (this.mode.is("Normal")) {
                float targetPitch = -this.pitch.getValue();
                mc.player.setPitch(targetPitch);
                WildClient.INSTANCE.setBodyPitch(targetPitch);
            } else {
                mc.player.setPitch(-42.5f);
                mc.player.setYaw(45.0f);
                WildClient.INSTANCE.setBodyPitch(-42.5f);
            }
        }
    }
}
