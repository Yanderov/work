package dev.client.modules.impl.render;

import com.mojang.blaze3d.platform.GlStateManager.DstFactor;
import com.mojang.blaze3d.platform.GlStateManager.SrcFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.client.WildClient;
import dev.client.event.classes.Render3DEvent;
import dev.client.event.interfaces.IRenderable3D;
import dev.client.modules.Category;
import dev.client.modules.IDisableable;
import dev.client.modules.IEnableable;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.modules.settings.impl.ColorSetting;
import dev.client.modules.settings.impl.MultiBoxSetting;
import dev.client.util.IUtil;
import dev.client.util.render.RenderUtil3D;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientChunkEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gl.ShaderProgramKeys;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.WorldChunk;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

@Environment(EnvType.CLIENT)
public class BlockEsp extends Module implements IRenderable3D, IUtil, IDisableable, IEnableable {
   public final MultiBoxSetting blockOptions = new MultiBoxSetting().name("Blocks").booleanSettings(new BooleanSetting() {
      public void onChangeState(boolean v) {
         BlockEsp.this.rescanAll();
      }
   }.name("DiamondOre").value(true), new BooleanSetting() {
      public void onChangeState(boolean v) {
         BlockEsp.this.rescanAll();
      }
   }.name("AncientDebris").value(true), new BooleanSetting() {
      public void onChangeState(boolean v) {
         BlockEsp.this.rescanAll();
      }
   }.name("Chest").value(true), new BooleanSetting() {
      public void onChangeState(boolean v) {
         BlockEsp.this.rescanAll();
      }
   }.name("EnderChest").value(false), new BooleanSetting() {
      public void onChangeState(boolean v) {
         BlockEsp.this.rescanAll();
      }
   }.name("Spawner").value(false), new BooleanSetting() {
      public void onChangeState(boolean v) {
         BlockEsp.this.rescanAll();
      }
   }.name("Obsidian").value(false));
   private final ColorSetting color = new ColorSetting().name("Color").color(new Color(255, 255, 255, 255));
   private final ConcurrentHashMap<Long, List<BlockPos>> chunkCache = new ConcurrentHashMap();
   private static final double EXPAND = 0.002;

   public BlockEsp() {
      super(new ModuleBranding("BlockEsp", Category.RENDER, "Показывает выбранные блоки"));
      this.addSetting(this.blockOptions, this.color);
      ClientChunkEvents.CHUNK_LOAD.register((ClientChunkEvents.Load)(world, chunk) -> {
         if (this.isEnabled()) {
            this.scanChunk(chunk);
         }
      });
      ClientChunkEvents.CHUNK_UNLOAD.register((ClientChunkEvents.Unload)(world, chunk) -> {
         if (this.isEnabled()) {
            this.chunkCache.remove(chunkKey(chunk.getPos().x, chunk.getPos().z));
         }
      });
   }

   public void onEnable() {
      this.rescanAll();
   }

   public void onDisable() {
      this.chunkCache.clear();
   }

   private void rescanAll() {
      this.chunkCache.clear();
      if (this.isEnabled() && mc.world != null && mc.player != null) {
         int chunkX = mc.player.getBlockPos().getX() >> 4;
         int chunkZ = mc.player.getBlockPos().getZ() >> 4;

         for(int cx = chunkX - 8; cx <= chunkX + 8; ++cx) {
            for(int cz = chunkZ - 8; cz <= chunkZ + 8; ++cz) {
               WorldChunk chunk = mc.world.getChunkManager().getWorldChunk(cx, cz);
               if (chunk != null && !chunk.isEmpty()) {
                  this.scanChunk(chunk);
               }
            }
         }

      }
   }

   public void onRender3D(Render3DEvent event) {
      if (mc.player != null && mc.world != null && !this.chunkCache.isEmpty()) {
         int themeColor = this.color.getColor().getRGB();
         float r = (float)(themeColor >> 16 & 255) / 255.0F;
         float g = (float)(themeColor >> 8 & 255) / 255.0F;
         float b = (float)(themeColor & 255) / 255.0F;
         RenderUtil3D r3d = RenderUtil3D.getInstance();
         Matrix4f mat = r3d.lastWorldSpaceMatrix.getPositionMatrix();
         GL11.glEnable(2881);
         RenderSystem.enableBlend();
         RenderSystem.disableCull();
         RenderSystem.disableDepthTest();
         RenderSystem.blendFunc(SrcFactor.SRC_ALPHA, DstFactor.ONE_MINUS_CONSTANT_ALPHA);
         RenderSystem.lineWidth(1.24F);
         RenderSystem.setShader(ShaderProgramKeys.RENDERTYPE_LINES);
         BufferBuilder lines = Tessellator.getInstance().begin(DrawMode.LINES, VertexFormats.LINES);
         boolean hasLines = false;

         for(List<BlockPos> positions : this.chunkCache.values()) {
            for(BlockPos pos : positions) {
               Box box = (new Box(pos)).expand(0.002);
               if (r3d.canSee(box)) {
                  appendBoxLines(lines, mat, box, r, g, b, 1.0F);
                  hasLines = true;
               }
            }
         }

         if (hasLines) {
            BufferRenderer.drawWithGlobalProgram(lines.end());
         }

         RenderSystem.enableDepthTest();
         RenderSystem.enableCull();
         RenderSystem.disableBlend();
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glDisable(2881);
      }
   }

   private static void appendBoxLines(BufferBuilder buf, Matrix4f mat, Box b, float r, float g, float bl, float a) {
      float x0 = (float)b.minX;
      float y0 = (float)b.minY;
      float z0 = (float)b.minZ;
      float x1 = (float)b.maxX;
      float y1 = (float)b.maxY;
      float z1 = (float)b.maxZ;
      line(buf, mat, x0, y0, z0, x1, y0, z0, r, g, bl, a);
      line(buf, mat, x1, y0, z0, x1, y0, z1, r, g, bl, a);
      line(buf, mat, x1, y0, z1, x0, y0, z1, r, g, bl, a);
      line(buf, mat, x0, y0, z1, x0, y0, z0, r, g, bl, a);
      line(buf, mat, x0, y1, z0, x1, y1, z0, r, g, bl, a);
      line(buf, mat, x1, y1, z0, x1, y1, z1, r, g, bl, a);
      line(buf, mat, x1, y1, z1, x0, y1, z1, r, g, bl, a);
      line(buf, mat, x0, y1, z1, x0, y1, z0, r, g, bl, a);
      line(buf, mat, x0, y0, z0, x0, y1, z0, r, g, bl, a);
      line(buf, mat, x1, y0, z0, x1, y1, z0, r, g, bl, a);
      line(buf, mat, x1, y0, z1, x1, y1, z1, r, g, bl, a);
      line(buf, mat, x0, y0, z1, x0, y1, z1, r, g, bl, a);
   }

   private static void line(BufferBuilder buf, Matrix4f mat, float x0, float y0, float z0, float x1, float y1, float z1, float r, float g, float b, float a) {
      float dx = x1 - x0;
      float dy = y1 - y0;
      float dz = z1 - z0;
      float len = MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
      if (len != 0.0F) {
         float nx = dx / len;
         float ny = dy / len;
         float nz = dz / len;
         buf.vertex(mat, x0, y0, z0).color(r, g, b, a).normal(nx, ny, nz);
         buf.vertex(mat, x1, y1, z1).color(r, g, b, a).normal(nx, ny, nz);
      }
   }

   private void scanChunk(WorldChunk chunk) {
      Set<Block> targets = this.buildTargetSet();
      long key = chunkKey(chunk.getPos().x, chunk.getPos().z);
      if (targets.isEmpty()) {
         this.chunkCache.remove(key);
      } else {
         List<BlockPos> result = new ArrayList<>();
         ChunkSection[] sections = chunk.getSectionArray();
         int baseX = chunk.getPos().getStartX();
         int baseZ = chunk.getPos().getStartZ();

         for(int si = 0; si < sections.length; ++si) {
            ChunkSection section = sections[si];
            if (section != null && !section.isEmpty()) {
               int secMinY = chunk.getBottomY() + si * 16;

               for(int lx = 0; lx < 16; ++lx) {
                  for(int ly = 0; ly < 16; ++ly) {
                     for(int lz = 0; lz < 16; ++lz) {
                        if (targets.contains(section.getBlockState(lx, ly, lz).getBlock())) {
                           result.add(new BlockPos(baseX + lx, secMinY + ly, baseZ + lz));
                        }
                     }
                  }
               }
            }
         }

         if (result.isEmpty()) {
            this.chunkCache.remove(key);
         } else {
            this.chunkCache.put(key, result);
         }

      }
   }

   private static long chunkKey(int cx, int cz) {
      return (long)cx & 4294967295L | ((long)cz & 4294967295L) << 32;
   }

   private Set<Block> buildTargetSet() {
      List<Block> targets = new ArrayList<>(8);
      if (this.blockOptions.getValueByName("DiamondOre")) {
         targets.add(Blocks.DIAMOND_ORE);
         targets.add(Blocks.DEEPSLATE_DIAMOND_ORE);
      }

      if (this.blockOptions.getValueByName("AncientDebris")) {
         targets.add(Blocks.ANCIENT_DEBRIS);
      }

      if (this.blockOptions.getValueByName("Chest")) {
         targets.add(Blocks.CHEST);
         targets.add(Blocks.TRAPPED_CHEST);
      }

      if (this.blockOptions.getValueByName("EnderChest")) {
         targets.add(Blocks.ENDER_CHEST);
      }

      if (this.blockOptions.getValueByName("Spawner")) {
         targets.add(Blocks.SPAWNER);
      }

      if (this.blockOptions.getValueByName("Obsidian")) {
         targets.add(Blocks.OBSIDIAN);
         targets.add(Blocks.CRYING_OBSIDIAN);
      }

      return Set.copyOf(targets);
   }
}

