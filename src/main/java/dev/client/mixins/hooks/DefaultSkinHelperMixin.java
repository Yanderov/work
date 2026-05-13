package dev.client.mixins.hooks;

import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.client.util.SkinTextures;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(DefaultSkinHelper.class)
public class DefaultSkinHelperMixin {
    private static final Identifier TYAN_SKIN = Identifier.of("wild", "tyan.png");
    private static final SkinTextures TYAN_TEXTURES = new SkinTextures(TYAN_SKIN, null, null, null, SkinTextures.Model.WIDE, true);

    @Inject(method = "getSkinTextures(Ljava/util/UUID;)Lnet/minecraft/client/util/SkinTextures;", at = @At("HEAD"), cancellable = true)
    private static void onGetSkinTextures(UUID uuid, CallbackInfoReturnable<SkinTextures> cir) {
        cir.setReturnValue(TYAN_TEXTURES);
    }
}
