package dev.client.yanderov.features.impl.render;

import dev.client.yanderov.events.render.WorldRenderEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.ColorSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.chat.ChatMessage;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.geometry.Render3D;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2680;
import net.minecraft.class_2818;
import net.minecraft.class_7923;
import net.minecraft.class_2902.class_2903;

public class BlockESP extends Module {
   ColorSetting color = (new ColorSetting("Ð¦Ð²ÐµÑ‚", "Ð¦Ð²ÐµÑ‚ Ð¿Ð¾Ð´ÑÐ²ÐµÑ‚ÐºÐ¸ Ð±Ð»Ð¾ÐºÐ¾Ð²")).value(ColorAssist.getColor(255, 0, 0, 255));
   SliderSettings range = (new SliderSettings("Ð Ð°Ð´Ð¸ÑƒÑ", "Ð Ð°Ð´Ð¸ÑƒÑ Ð¿Ð¾Ð¸ÑÐºÐ° Ð±Ð»Ð¾ÐºÐ¾Ð²")).range(1, 128).setValue(32.0F);
   BooleanSetting notifyInChat = (new BooleanSetting("Ð£Ð²ÐµÐ´Ð¾Ð¼Ð»ÐµÐ½Ð¸Ñ", "ÐŸÐ¾ÐºÐ°Ð·Ñ‹Ð²Ð°Ñ‚ÑŒ ÐºÐ¾Ð¾Ñ€Ð´Ð¸Ð½Ð°Ñ‚Ñ‹ Ð½Ð°Ð¹Ð´ÐµÐ½Ð½Ñ‹Ñ… Ð±Ð»Ð¾ÐºÐ¾Ð² Ð² Ñ‡Ð°Ñ‚Ðµ")).setValue(false);
   Set blocksToHighlight = new CopyOnWriteArraySet();
   Map renderBlocks = new HashMap();
   Set notifiedBlocks = new CopyOnWriteArraySet();
   long lastScanTime = 0L;
   int checkCounter = 0;

   public BlockESP() {
      super("BlockESP", "Block ESP", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.color, this.range, this.notifyInChat});
   }

   public Set getBlocksToHighlight() {
      return this.blocksToHighlight;
   }

   public void activate() {
      super.activate();
      this.notifiedBlocks.clear();
   }

   public void deactivate() {
      super.deactivate();
      this.renderBlocks.clear();
      this.notifiedBlocks.clear();
   }

   @EventHandler
   public void onRender3D(WorldRenderEvent event) {
      if (this.state && mc.field_1687 != null && mc.field_1724 != null) {
         if (this.blocksToHighlight.isEmpty()) {
            this.renderBlocks.clear();
         } else {
            class_2338 playerPos = mc.field_1724.method_24515();
            long currentTime = System.nanoTime() / 1000000L;
            if (currentTime - this.lastScanTime >= 2000L) {
               this.renderBlocks.clear();
               int chunkRange = 2;
               int yRange = 48;

               for(int x = -chunkRange; x <= chunkRange; ++x) {
                  for(int z = -chunkRange; z <= chunkRange; ++z) {
                     int chunkX = (playerPos.method_10263() >> 4) + x;
                     int chunkZ = (playerPos.method_10260() >> 4) + z;
                     if (mc.field_1687.method_2935().method_12123(chunkX, chunkZ)) {
                        class_2818 chunk = mc.field_1687.method_2935().method_21730(chunkX, chunkZ);
                        if (chunk != null) {
                           int cx = chunk.method_12004().field_9181 << 4;
                           int cz = chunk.method_12004().field_9180 << 4;

                           for(int bx = 0; bx < 16; ++bx) {
                              for(int bz = 0; bz < 16; ++bz) {
                                 int minY = Math.max(mc.field_1687.method_31607(), playerPos.method_10264() - yRange);
                                 int maxY = Math.min(mc.field_1687.method_8624(class_2903.field_13202, cx + bx, cz + bz), playerPos.method_10264() + yRange);

                                 for(int by = minY; by <= maxY; ++by) {
                                    class_2338 pos = new class_2338(cx + bx, by, cz + bz);
                                    double dist = mc.field_1724.method_5649((double)pos.method_10263() + (double)0.5F, (double)pos.method_10264() + (double)0.5F, (double)pos.method_10260() + (double)0.5F);
                                    if (!(dist > (double)(this.range.getValue() * this.range.getValue()))) {
                                       class_2248 block = mc.field_1687.method_8320(pos).method_26204();
                                       String blockName = class_7923.field_41175.method_10221(block).toString();
                                       if (this.blocksToHighlight.contains(blockName)) {
                                          this.renderBlocks.put(pos.method_10062(), mc.field_1687.method_8320(pos));
                                          if (this.notifyInChat.isValue() && !this.notifiedBlocks.contains(pos)) {
                                             this.notifyBlockFound(pos, blockName);
                                             this.notifiedBlocks.add(pos);
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               this.lastScanTime = currentTime;
               this.checkCounter = 0;
            }

            if (this.checkCounter % 5 == 0) {
               int nearChunkRange = 1;

               for(int x = -nearChunkRange; x <= nearChunkRange; ++x) {
                  for(int z = -nearChunkRange; z <= nearChunkRange; ++z) {
                     int chunkX = (playerPos.method_10263() >> 4) + x;
                     int chunkZ = (playerPos.method_10260() >> 4) + z;
                     if (mc.field_1687.method_2935().method_12123(chunkX, chunkZ)) {
                        class_2818 chunk = mc.field_1687.method_2935().method_21730(chunkX, chunkZ);
                        if (chunk != null) {
                           int cx = chunk.method_12004().field_9181 << 4;
                           int cz = chunk.method_12004().field_9180 << 4;

                           for(int bx = 0; bx < 16; ++bx) {
                              for(int bz = 0; bz < 16; ++bz) {
                                 int minY = Math.max(mc.field_1687.method_31607(), playerPos.method_10264() - 24);
                                 int maxY = Math.min(mc.field_1687.method_8624(class_2903.field_13202, cx + bx, cz + bz), playerPos.method_10264() + 24);

                                 for(int by = minY; by <= maxY; ++by) {
                                    class_2338 pos = new class_2338(cx + bx, by, cz + bz);
                                    double dist = mc.field_1724.method_5649((double)pos.method_10263() + (double)0.5F, (double)pos.method_10264() + (double)0.5F, (double)pos.method_10260() + (double)0.5F);
                                    if (!(dist > (double)16.0F)) {
                                       class_2248 block = mc.field_1687.method_8320(pos).method_26204();
                                       String blockName = class_7923.field_41175.method_10221(block).toString();
                                       if (this.blocksToHighlight.contains(blockName) && !this.renderBlocks.containsKey(pos)) {
                                          this.renderBlocks.put(pos.method_10062(), mc.field_1687.method_8320(pos));
                                          if (this.notifyInChat.isValue() && !this.notifiedBlocks.contains(pos)) {
                                             this.notifyBlockFound(pos, blockName);
                                             this.notifiedBlocks.add(pos);
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }

            if (this.checkCounter % 60 == 0) {
               this.renderBlocks.entrySet().removeIf((entry) -> {
                  class_2338 pos = (class_2338)entry.getKey();
                  class_2248 block = mc.field_1687.method_8320(pos).method_26204();
                  String blockName = class_7923.field_41175.method_10221(block).toString();
                  boolean shouldRemove = !this.blocksToHighlight.contains(blockName);
                  if (shouldRemove) {
                     this.notifiedBlocks.remove(pos);
                  }

                  return shouldRemove;
               });
            }

            ++this.checkCounter;
            this.renderBlocks.forEach((posx, state) -> Render3D.drawBox(new class_238(posx), this.color.getColor(), 1.0F));
         }
      } else {
         this.renderBlocks.clear();
      }
   }

   private void notifyBlockFound(class_2338 pos, String blockName) {
      if (mc.field_1724 != null) {
         mc.field_1724.method_7353(ChatMessage.blockesp().method_27693(" -> ÐÐ°Ð¹Ð´ÐµÐ½ Ð±Ð»Ð¾Ðº " + blockName + " Ð½Ð° ÐºÐ¾Ð¾Ñ€Ð´Ð¸Ð½Ð°Ñ‚Ð°Ñ… -> " + pos.method_10263() + ", " + pos.method_10264() + ", " + pos.method_10260()), false);
      }

   }

   private String getBlockName(class_2680 state) {
      return state.method_26204().method_8389().toString().replace("minecraft:", "").replace("_ore", "").replace("_", " ");
   }
}

