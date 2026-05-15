package dev.client.yanderov.mixins.network.server;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.utils.client.logs.Logger;
import dev.client.yanderov.utils.client.managers.file.exception.FileProcessingException;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({MinecraftServer.class})
public class MinecraftServerMixin {
   @Inject(
      method = {"shutdown"},
      at = {@At("HEAD")}
   )
   public void shutdown(CallbackInfo ci) {
      if (YanderovIntegration.getInstance().isInitialized()) {
         try {
            YanderovIntegration.getInstance().getFileController().saveFiles();
         } catch (FileProcessingException e) {
            Logger.error("Error occurred while saving files: " + e.getMessage());
         }
      }

   }
}

