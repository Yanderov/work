package fun.Yanderov.mixins.network.server;

import fun.Yanderov.Yanderov;
import fun.Yanderov.utils.client.logs.Logger;
import fun.Yanderov.utils.client.managers.file.exception.FileProcessingException;
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
      if (Yanderov.getInstance().isInitialized()) {
         try {
            Yanderov.getInstance().getFileController().saveFiles();
         } catch (FileProcessingException e) {
            Logger.error("Error occurred while saving files: " + e.getMessage());
         }
      }

   }
}

