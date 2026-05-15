package dev.client.yanderov.mixins.player.entity;

import dev.client.yanderov.events.block.BlockUpdateEvent;
import dev.client.yanderov.events.chat.ChatEvent;
import dev.client.yanderov.utils.client.managers.event.EventManager;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2666;
import net.minecraft.class_2672;
import net.minecraft.class_2680;
import net.minecraft.class_2818;
import net.minecraft.class_2826;
import net.minecraft.class_634;
import net.minecraft.class_7439;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_634.class})
public class ClientPlayNetworkHandlerMixin implements QuickImports {
   @Inject(
      method = {"onChunkData"},
      at = {@At("RETURN")}
   )
   private void onChunkDataHook(class_2672 packet, CallbackInfo ci) {
      this.scanChunk(mc.field_1687.method_8497(packet.method_11523(), packet.method_11524()), BlockUpdateEvent.Type.LOAD);
   }

   @Inject(
      method = {"onUnloadChunk"},
      at = {@At("HEAD")}
   )
   private void onUnloadChunkHook(class_2666 packet, CallbackInfo ci) {
      this.scanChunk(mc.field_1687.method_8497(packet.comp_1726().field_9181, packet.comp_1726().field_9180), BlockUpdateEvent.Type.UNLOAD);
   }

   @Inject(
      method = {"sendChatMessage(Ljava/lang/String;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void sendChatMessage(String string, CallbackInfo ci) {
      ChatEvent event = new ChatEvent(string);
      EventManager.callEvent(event);
      if (event.isCancelled()) {
         ci.cancel();
      }

   }

   @Inject(
      method = {"onGameMessage"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onGameMessage(class_7439 packet, CallbackInfo ci) {
      String message = packet.comp_763().getString();
      ChatEvent event = new ChatEvent(message);
      EventManager.callEvent(event);
      if (event.isCancelled()) {
         ci.cancel();
      }

   }

   @Unique
   private void scanChunk(class_2818 worldChunk, BlockUpdateEvent.Type type) {
      int startX = worldChunk.method_12004().method_8326();
      int startZ = worldChunk.method_12004().method_8328();
      List<CompletableFuture<Void>> sectionFutures = new ArrayList();

      for(int sectionIndex = 0; sectionIndex <= worldChunk.method_12040(); ++sectionIndex) {
         sectionFutures.add(CompletableFuture.runAsync(() -> {
            class_2826 section = worldChunk.method_38259(sectionIndex);

            for(int sectionY = 0; sectionY < 16; ++sectionY) {
               int y = sectionIndex + (worldChunk.method_31607() >> 4) << 4 | sectionY;

               for(int x = 0; x < 16; ++x) {
                  for(int z = 0; z < 16; ++z) {
                     class_2680 blockState = type.equals(BlockUpdateEvent.Type.LOAD) ? section.method_12254(x, sectionY, z) : class_2246.field_10124.method_9564();
                     class_2338 pos = new class_2338(startX | x, y, startZ | z);
                     EventManager.callEvent(new BlockUpdateEvent(blockState, pos, type));
                  }
               }
            }

         }));
      }

      CompletableFuture.allOf((CompletableFuture[])sectionFutures.toArray(new CompletableFuture[0])).join();
   }
}

