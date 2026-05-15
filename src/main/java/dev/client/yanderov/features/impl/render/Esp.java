package dev.client.yanderov.features.impl.render;

import dev.client.yanderov.common.repository.friend.FriendUtils;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.events.render.DrawEvent;
import dev.client.yanderov.events.render.WorldLoadEvent;
import dev.client.yanderov.events.render.WorldRenderEvent;
import dev.client.yanderov.features.impl.combat.AntiBot;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.MultiSelectSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.Instance;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.client.packet.network.Network;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.font.FontRenderer;
import dev.client.yanderov.utils.display.font.Fonts;
import dev.client.yanderov.utils.display.geometry.Render2D;
import dev.client.yanderov.utils.display.geometry.Render3D;
import dev.client.yanderov.utils.display.shape.ShapeProperties;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import dev.client.yanderov.utils.math.calc.Calculate;
import dev.client.yanderov.utils.math.projection.Projection;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1541;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1921;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_5250;
import net.minecraft.class_9279;
import net.minecraft.class_9288;
import net.minecraft.class_9334;
import org.joml.Vector4d;

public class Esp extends Module {
   private final class_2960 TEXTURE = class_2960.method_60654("textures/features/esp/container.png");
   private final List players = new ArrayList();
   private final Map encMap = new HashMap();
   public final MultiSelectSetting entityType = (new MultiSelectSetting("Ð¢Ð¸Ð¿ ÑÑƒÑ‰Ð½Ð¾ÑÑ‚Ð¸", "Ð¡ÑƒÑ‰Ð½Ð¾ÑÑ‚Ð¸, ÐºÐ¾Ñ‚Ð¾Ñ€Ñ‹Ðµ Ð±ÑƒÐ´ÑƒÑ‚ Ð¾Ñ‚Ð¾Ð±Ñ€Ð°Ð¶Ð°Ñ‚ÑŒÑÑ")).value("Player", "Item", "TNT").selected("Player", "Item");
   private final MultiSelectSetting playerSetting = (new MultiSelectSetting("ÐÐ°ÑÑ‚Ñ€Ð¾Ð¹ÐºÐ¸ Ð¸Ð³Ñ€Ð¾ÐºÐ°", "ÐÐ°ÑÑ‚Ñ€Ð¾Ð¹ÐºÐ¸ Ð´Ð»Ñ Ð¸Ð³Ñ€Ð¾ÐºÐ¾Ð²")).value("Box", "Armor", "NameTags", "Hand Items").selected("Box", "Armor", "NameTags", "Hand Items").visible(() -> this.entityType.isSelected("Player"));
   public final SelectSetting boxType = (new SelectSetting("Ð¢Ð¸Ð¿", "Ð¢Ð¸Ð¿")).value("Corner", "Full", "3D Box", "Skeleton").selected("3D Box").visible(() -> this.playerSetting.isSelected("Box"));
   public final BooleanSetting flatBoxOutline = (new BooleanSetting("ÐšÐ¾Ð½Ñ‚ÑƒÑ€", "ÐšÐ¾Ð½Ñ‚ÑƒÑ€ Ð´Ð»Ñ Ð¿Ð»Ð¾ÑÐºÐ¸Ñ… Ð±Ð¾ÐºÑÐ¾Ð²")).visible(() -> this.playerSetting.isSelected("Box") && (this.boxType.isSelected("Corner") || this.boxType.isSelected("Full")));
   public final SliderSettings boxAlpha = (new SliderSettings("ÐŸÑ€Ð¾Ð·Ñ€Ð°Ñ‡Ð½Ð¾ÑÑ‚ÑŒ", "ÐŸÑ€Ð¾Ð·Ñ€Ð°Ñ‡Ð½Ð¾ÑÑ‚ÑŒ Ð±Ð¾ÐºÑÐ°")).setValue(1.0F).range(0.1F, 1.0F).visible(() -> this.boxType.isSelected("3D Box"));
   public final SliderSettings skeletonWidth = (new SliderSettings("Ð¢Ð¾Ð»Ñ‰Ð¸Ð½Ð° Ð»Ð¸Ð½Ð¸Ð¹", "Ð¢Ð¾Ð»Ñ‰Ð¸Ð½Ð° Ð»Ð¸Ð½Ð¸Ð¹ ÑÐºÐµÐ»ÐµÑ‚Ð°")).setValue(2.5F).range(2.5F, 4.0F).visible(() -> this.boxType.isSelected("Skeleton"));
   private static final float DISTANCE = 128.0F;
   public final BooleanSetting selfRender = (new BooleanSetting("Self Render", "ÐžÑ‚Ð¾Ð±Ñ€Ð°Ð¶Ð°Ñ‚ÑŒ ESP Ð½Ð° ÑÐµÐ±Ðµ")).setValue(false);

   public static Esp getInstance() {
      return (Esp)Instance.get(Esp.class);
   }

   public Esp() {
      super("Esp", "Esp", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.entityType, this.playerSetting, this.boxType, this.flatBoxOutline, this.boxAlpha, this.skeletonWidth, this.selfRender});
   }

   @EventHandler
   public void onWorldLoad(WorldLoadEvent e) {
      this.players.clear();
   }

   @EventHandler
   public void onTick(TickEvent e) {
      this.players.clear();
      if (mc.field_1687 != null) {
         boolean includeSelf = this.selfRender.isValue();
         Stream var10000 = mc.field_1687.method_18456().stream().filter((player) -> includeSelf || player != mc.field_1724).filter((player) -> player.method_5797() == null || !player.method_5797().getString().startsWith("Ghost_"));
         List var10001 = this.players;
         Objects.requireNonNull(var10001);
         var10000.forEach(var10001::add);
      }

   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      if (this.entityType.isSelected("Player")) {
         float tickDelta = mc.method_61966().method_60637(false);

         for(class_1657 player : this.players) {
            if (player != null && (this.selfRender.isValue() || player != mc.field_1724) && (player.method_5797() == null || !player.method_5797().getString().startsWith("Ghost_"))) {
               double interpX = class_3532.method_16436((double)tickDelta, player.field_6014, player.method_23317());
               double interpY = class_3532.method_16436((double)tickDelta, player.field_6036, player.method_23318());
               double interpZ = class_3532.method_16436((double)tickDelta, player.field_5969, player.method_23321());
               class_243 interpCenter = new class_243(interpX, interpY, interpZ);
               float distance = (float)mc.method_1561().field_4686.method_19326().method_1022(interpCenter);
               if (!(distance < 1.0F)) {
                  boolean friend = FriendUtils.isFriend((class_1297)player);
                  int baseColor = friend ? ColorAssist.getFriendColor() : ColorAssist.getClientColor();
                  int alpha = (int)(this.boxAlpha.getValue() * 255.0F);
                  int fillColor = baseColor & 16777215 | alpha << 24;
                  int outlineColor = baseColor | -16777216;
                  if (this.boxType.isSelected("3D Box")) {
                     class_238 interpBox = player.method_18377(player.method_18376()).method_30231(interpX, interpY, interpZ);
                     Render3D.drawBox(interpBox, fillColor, 2.0F, true, true, true);
                     Render3D.drawBox(interpBox, outlineColor, 2.0F, true, true, true);
                  } else if (this.boxType.isSelected("Skeleton") && this.playerSetting.isSelected("Box") && !(distance > 128.0F)) {
                     this.renderSkeleton(player, tickDelta, baseColor);
                  }
               }
            }
         }

      }
   }

   @EventHandler
   public void onDraw(DrawEvent e) {
      class_332 context = e.getDrawContext();
      class_4587 matrix = context.method_51448();
      FontRenderer font = Fonts.getSize(13, Fonts.Type.SEMI);
      FontRenderer bigFont = Fonts.getSize(15, Fonts.Type.SEMI);
      if (this.entityType.isSelected("Player")) {
         for(class_1657 player : this.players) {
            if (player != null && (this.selfRender.isValue() || player != mc.field_1724) && (player.method_5797() == null || !player.method_5797().getString().startsWith("Ghost_"))) {
               Vector4d vec4d = Projection.getVector4D(player);
               float distance = (float)mc.method_1561().field_4686.method_19326().method_1022(player.method_5829().method_1005());
               boolean friend = FriendUtils.isFriend((class_1297)player);
               if (!(distance < 1.0F) && !Projection.cantSee(vec4d)) {
                  if (this.playerSetting.isSelected("Box") && !this.boxType.isSelected("Skeleton")) {
                     this.drawBox(friend, vec4d, player);
                  }

                  if (this.playerSetting.isSelected("Armor")) {
                     this.drawArmor(context, player, vec4d, font);
                  }

                  if (this.playerSetting.isSelected("Hand Items")) {
                     this.drawHands(matrix, player, font, vec4d);
                  }

                  class_5250 text = this.getTextPlayer(player, friend);
                  if (Network.isAresMine()) {
                     float startX = (float)Projection.centerX(vec4d);
                     float startY = (float)vec4d.y;
                     float width = (float)mc.field_1772.method_27525(text);
                     Objects.requireNonNull(mc.field_1772);
                     float height = 9.0F;
                     float posX = startX - width / 2.0F;
                     float posY = startY - 11.0F;
                     int bgColor = friend ? (new Color(0, 170, 0, 160)).getRGB() : ColorAssist.HALF_BLACK;
                     blur.render(ShapeProperties.create(matrix, (double)(posX - 2.0F), (double)(posY - 0.75F), (double)(width + 4.0F), (double)(height + 1.5F)).quality(5.0F).round(4.0F).softness(1.0F).round(height / 4.0F).color(bgColor).build());
                     context.method_51439(mc.field_1772, text, (int)posX, (int)posY + 1, ColorAssist.getColor(255), false);
                  } else {
                     this.drawText(matrix, text, Projection.centerX(vec4d), vec4d.y - (double)2.0F, font, friend);
                  }
               }
            }
         }
      }

      for(class_1297 entity : PlayerInteractionHelper.streamEntities().sorted(Comparator.comparing((ent) -> {
         boolean var10000;
         if (ent instanceof class_1542 item) {
            if (item.method_6983().method_7964().method_10851().toString().equals("empty")) {
               var10000 = true;
               return var10000;
            }
         }

         var10000 = false;
         return var10000;
      })).toList()) {
         if (entity instanceof class_1542 item) {
            if (this.entityType.isSelected("Item")) {
               Vector4d vec4d = Projection.getVector4D(entity);
               class_1799 stack = item.method_6983();
               class_9288 compoundTag = (class_9288)stack.method_57824(class_9334.field_49622);
               List<class_1799> list = compoundTag != null ? compoundTag.method_57489().toList() : List.of();
               if (!Projection.cantSee(vec4d)) {
                  class_2561 text = item.method_6983().method_7964();
                  if (stack.method_7947() > 1) {
                     class_5250 var10000 = text.method_27661();
                     String var10001 = String.valueOf(class_124.field_1070);
                     text = var10000.method_27693(var10001 + " [" + String.valueOf(class_124.field_1061) + stack.method_7947() + String.valueOf(class_124.field_1080) + "x" + String.valueOf(class_124.field_1070) + "]");
                  }

                  if (!list.isEmpty()) {
                     this.drawShulkerBox(context, stack, list, vec4d);
                  } else {
                     this.drawText(matrix, text, Projection.centerX(vec4d), vec4d.y, text.method_10851().toString().equals("empty") ? bigFont : font);
                  }
               }
               continue;
            }
         }

         if (entity instanceof class_1541 tnt) {
            if (this.entityType.isSelected("TNT")) {
               Vector4d vec4d = Projection.getVector4D(entity);
               if (!Projection.cantSee(vec4d)) {
                  this.drawText(matrix, tnt.method_55423(), Projection.centerX(vec4d), vec4d.y, font);
               }
            }
         }
      }

   }

   private void renderSkeleton(class_1657 player, float partialTicks, int color) {
      class_243 pos = Calculate.interpolate(player);
      float width = this.skeletonWidth.getValue();
      float limbSwing = player.field_42108.method_48572(partialTicks);
      float limbSwingAmount = player.field_42108.method_48570(partialTicks);
      float bodyYaw = class_3532.method_17821(partialTicks, player.field_6220, player.field_6283);
      float bodyYawRad = (float)Math.toRadians((double)(-bodyYaw + 90.0F));
      boolean isSwimming = player.method_5681() || player.method_6128();
      float sneakOffset = player.method_5715() ? 0.2F : 0.0F;
      float swimOffset = isSwimming ? 0.6F : 0.0F;
      class_243 head = pos.method_1031((double)0.0F, (double)(1.62F - sneakOffset - swimOffset), (double)0.0F);
      class_243 neck = pos.method_1031((double)0.0F, (double)(1.4F - sneakOffset - swimOffset), (double)0.0F);
      class_243 body = pos.method_1031((double)0.0F, (double)(0.9F - sneakOffset - swimOffset), (double)0.0F);
      class_243 pelvis = pos.method_1031((double)0.0F, (double)(0.6F - sneakOffset - swimOffset), (double)0.0F);
      Render3D.drawLine(head, neck, color, width, false);
      Render3D.drawLine(neck, body, color, width, false);
      Render3D.drawLine(body, pelvis, color, width, false);
      float rightArmSwing = class_3532.method_15362(limbSwing * 0.6662F) * limbSwingAmount * 0.5F;
      float leftArmSwing = class_3532.method_15362(limbSwing * 0.6662F + (float)Math.PI) * limbSwingAmount * 0.5F;
      float rightLegSwing = class_3532.method_15362(limbSwing * 0.6662F + (float)Math.PI) * limbSwingAmount * 0.7F;
      float leftLegSwing = class_3532.method_15362(limbSwing * 0.6662F) * limbSwingAmount * 0.7F;
      class_243 rightShoulder = neck.method_1031(Math.sin((double)bodyYawRad) * 0.3, -0.1, Math.cos((double)bodyYawRad) * 0.3);
      class_243 rightElbow = rightShoulder.method_1031(Math.sin((double)bodyYawRad) * 0.05 + Math.sin((double)bodyYawRad + (Math.PI / 2D)) * (double)rightArmSwing * 0.15, (double)-0.25F - (double)Math.abs(rightArmSwing) * 0.1, Math.cos((double)bodyYawRad) * 0.05 + Math.cos((double)bodyYawRad + (Math.PI / 2D)) * (double)rightArmSwing * 0.15);
      class_243 rightHand = rightElbow.method_1031(Math.sin((double)bodyYawRad + (Math.PI / 2D)) * (double)rightArmSwing * 0.1, (double)-0.25F - (double)Math.abs(rightArmSwing) * 0.05, Math.cos((double)bodyYawRad + (Math.PI / 2D)) * (double)rightArmSwing * 0.1);
      Render3D.drawLine(rightShoulder, rightElbow, color, width, false);
      Render3D.drawLine(rightElbow, rightHand, color, width, false);
      class_243 leftShoulder = neck.method_1031(-Math.sin((double)bodyYawRad) * 0.3, -0.1, -Math.cos((double)bodyYawRad) * 0.3);
      class_243 leftElbow = leftShoulder.method_1031(-Math.sin((double)bodyYawRad) * 0.05 + Math.sin((double)bodyYawRad + (Math.PI / 2D)) * (double)leftArmSwing * 0.15, (double)-0.25F - (double)Math.abs(leftArmSwing) * 0.1, -Math.cos((double)bodyYawRad) * 0.05 + Math.cos((double)bodyYawRad + (Math.PI / 2D)) * (double)leftArmSwing * 0.15);
      class_243 leftHand = leftElbow.method_1031(Math.sin((double)bodyYawRad + (Math.PI / 2D)) * (double)leftArmSwing * 0.1, (double)-0.25F - (double)Math.abs(leftArmSwing) * 0.05, Math.cos((double)bodyYawRad + (Math.PI / 2D)) * (double)leftArmSwing * 0.1);
      Render3D.drawLine(leftShoulder, leftElbow, color, width, false);
      Render3D.drawLine(leftElbow, leftHand, color, width, false);
      class_243 rightHip = pelvis.method_1031(Math.sin((double)bodyYawRad) * 0.15, (double)0.0F, Math.cos((double)bodyYawRad) * 0.15);
      class_243 rightKnee = rightHip.method_1031(Math.sin((double)bodyYawRad + (Math.PI / 2D)) * (double)rightLegSwing * 0.1, -0.35 + (double)Math.max(0.0F, rightLegSwing) * 0.05, Math.cos((double)bodyYawRad + (Math.PI / 2D)) * (double)rightLegSwing * 0.1);
      class_243 rightFoot = rightKnee.method_1031(Math.sin((double)bodyYawRad + (Math.PI / 2D)) * (double)rightLegSwing * 0.08, -0.35 - (double)Math.max(0.0F, -rightLegSwing) * 0.05, Math.cos((double)bodyYawRad + (Math.PI / 2D)) * (double)rightLegSwing * 0.08);
      Render3D.drawLine(rightHip, rightKnee, color, width, false);
      Render3D.drawLine(rightKnee, rightFoot, color, width, false);
      class_243 leftHip = pelvis.method_1031(-Math.sin((double)bodyYawRad) * 0.15, (double)0.0F, -Math.cos((double)bodyYawRad) * 0.15);
      class_243 leftKnee = leftHip.method_1031(Math.sin((double)bodyYawRad + (Math.PI / 2D)) * (double)leftLegSwing * 0.1, -0.35 + (double)Math.max(0.0F, leftLegSwing) * 0.05, Math.cos((double)bodyYawRad + (Math.PI / 2D)) * (double)leftLegSwing * 0.1);
      class_243 leftFoot = leftKnee.method_1031(Math.sin((double)bodyYawRad + (Math.PI / 2D)) * (double)leftLegSwing * 0.08, -0.35 - (double)Math.max(0.0F, -leftLegSwing) * 0.05, Math.cos((double)bodyYawRad + (Math.PI / 2D)) * (double)leftLegSwing * 0.08);
      Render3D.drawLine(leftHip, leftKnee, color, width, false);
      Render3D.drawLine(leftKnee, leftFoot, color, width, false);
      Render3D.drawLine(rightShoulder, leftShoulder, color, width, false);
      Render3D.drawLine(rightHip, leftHip, color, width, false);
   }

   private void drawBox(boolean friend, Vector4d vec, class_1657 player) {
      if (!this.boxType.isSelected("3D Box") && !this.boxType.isSelected("Skeleton")) {
         int client = friend ? ColorAssist.getFriendColor() : ColorAssist.getClientColor();
         int black = ColorAssist.HALF_BLACK;
         float posX = (float)vec.x;
         float posY = (float)vec.y;
         float endPosX = (float)vec.z;
         float endPosY = (float)vec.w;
         float size = (endPosX - posX) / 3.0F;
         if (this.boxType.isSelected("Corner")) {
            Render2D.drawQuad(posX - 0.5F, posY - 0.5F, size, 0.5F, client);
            Render2D.drawQuad(posX - 0.5F, posY, 0.5F, size + 0.5F, client);
            Render2D.drawQuad(posX - 0.5F, endPosY - size - 0.5F, 0.5F, size, client);
            Render2D.drawQuad(posX - 0.5F, endPosY - 0.5F, size, 0.5F, client);
            Render2D.drawQuad(endPosX - size + 1.0F, posY - 0.5F, size, 0.5F, client);
            Render2D.drawQuad(endPosX + 0.5F, posY, 0.5F, size + 0.5F, client);
            Render2D.drawQuad(endPosX + 0.5F, endPosY - size - 0.5F, 0.5F, size, client);
            Render2D.drawQuad(endPosX - size + 1.0F, endPosY - 0.5F, size, 0.5F, client);
            if (this.flatBoxOutline.isValue()) {
               Render2D.drawQuad(posX - 1.0F, posY - 1.0F, size + 1.0F, 1.5F, black);
               Render2D.drawQuad(posX - 1.0F, posY + 0.5F, 1.5F, size + 0.5F, black);
               Render2D.drawQuad(posX - 1.0F, endPosY - size - 1.0F, 1.5F, size, black);
               Render2D.drawQuad(posX - 1.0F, endPosY - 1.0F, size + 1.0F, 1.5F, black);
               Render2D.drawQuad(endPosX - size + 0.5F, posY - 1.0F, size + 1.0F, 1.5F, black);
               Render2D.drawQuad(endPosX, posY + 0.5F, 1.5F, size + 0.5F, black);
               Render2D.drawQuad(endPosX, endPosY - size - 1.0F, 1.5F, size, black);
               Render2D.drawQuad(endPosX - size + 0.5F, endPosY - 1.0F, size + 1.0F, 1.5F, black);
            }
         } else if (this.boxType.isSelected("CornerNew")) {
            int shadow = (new Color(0, 0, 0, 140)).getRGB();
            float expand = 1.5F;
            Render2D.drawQuad(posX - 0.5F - expand, posY - 0.5F - expand, size + expand * 2.0F, 1.0F, shadow);
            Render2D.drawQuad(posX - 0.5F - expand, posY, 1.0F, size + 0.5F + expand, shadow);
            Render2D.drawQuad(posX - 0.5F - expand, endPosY - size - 0.5F - expand, 1.0F, size + expand, shadow);
            Render2D.drawQuad(posX - 0.5F - expand, endPosY - 0.5F - expand, size + expand * 2.0F, 1.0F, shadow);
            Render2D.drawQuad(endPosX - size + 1.0F - expand, posY - 0.5F - expand, size + expand * 2.0F, 1.0F, shadow);
            Render2D.drawQuad(endPosX + 0.5F, posY, 1.0F, size + 0.5F + expand, shadow);
            Render2D.drawQuad(endPosX + 0.5F, endPosY - size - 0.5F - expand, 1.0F, size + expand, shadow);
            Render2D.drawQuad(endPosX - size + 1.0F - expand, endPosY - 0.5F - expand, size + expand * 2.0F, 1.0F, shadow);
            Render2D.drawQuad(posX - 0.5F, posY - 0.5F, size, 0.5F, client);
            Render2D.drawQuad(posX - 0.5F, posY, 0.5F, size + 0.5F, client);
            Render2D.drawQuad(posX - 0.5F, endPosY - size - 0.5F, 0.5F, size, client);
            Render2D.drawQuad(posX - 0.5F, endPosY - 0.5F, size, 0.5F, client);
            Render2D.drawQuad(endPosX - size + 1.0F, posY - 0.5F, size, 0.5F, client);
            Render2D.drawQuad(endPosX + 0.5F, posY, 0.5F, size + 0.5F, client);
            Render2D.drawQuad(endPosX + 0.5F, endPosY - size - 0.5F, 0.5F, size, client);
            Render2D.drawQuad(endPosX - size + 1.0F, endPosY - 0.5F, size, 0.5F, client);
         } else if (this.boxType.isSelected("Full") && this.flatBoxOutline.isValue()) {
            Render2D.drawQuad(posX - 1.0F, posY - 1.0F, endPosX - posX + 2.0F, 1.5F, black);
            Render2D.drawQuad(posX - 1.0F, posY - 1.0F, 1.5F, endPosY - posY + 2.0F, black);
            Render2D.drawQuad(posX - 1.0F, endPosY - 1.0F, endPosX - posX + 2.0F, 1.5F, black);
            Render2D.drawQuad(endPosX - 0.5F, posY - 1.0F, 1.5F, endPosY - posY + 2.0F, black);
         }

      }
   }

   private void drawArmor(class_332 context, class_1657 player, Vector4d vec, FontRenderer font) {
      class_4587 matrix = context.method_51448();
      List<class_1799> items = new ArrayList();
      player.method_56675().forEach((s) -> {
         if (!s.method_7960()) {
            items.add(s);
         }

      });
      float posX = (float)(Projection.centerX(vec) - (double)items.size() * (double)5.5F);
      float posY = (float)(vec.y - 8.666666666666666 - (double)15.0F);
      float padding = 0.5F;
      float offset = -11.0F;
      if (!items.isEmpty()) {
         matrix.method_22903();
         matrix.method_46416(posX, posY, 0.0F);

         for(class_1799 stack : items) {
            offset += 11.0F;
            Render2D.defaultDrawStack(context, stack, offset, 0.0F, false, false, 0.5F);
         }

         matrix.method_22909();
      }

   }

   private void drawHands(class_4587 matrix, class_1657 player, FontRenderer font, Vector4d vec) {
      double posY = vec.w;

      for(class_1799 stack : player.method_5877()) {
         if (!stack.method_7960()) {
            class_5250 text = class_2561.method_43473().method_10852(stack.method_7964());
            if (stack.method_7947() > 1) {
               String var10001 = String.valueOf(class_124.field_1070);
               text.method_27693(var10001 + " [" + String.valueOf(class_124.field_1061) + stack.method_7947() + String.valueOf(class_124.field_1080) + "x" + String.valueOf(class_124.field_1070) + "]");
            }

            posY += (double)(font.getStringHeight((class_2561)text) / 2.0F + 3.0F);
            this.drawText(matrix, text, Projection.centerX(vec), posY, font);
         }
      }

   }

   private void drawShulkerBox(class_332 context, class_1799 itemStack, List stacks, Vector4d vec) {
      class_4587 matrix = context.method_51448();
      int width = 176;
      int height = 67;
      int color = ColorAssist.multBright(ColorAssist.replAlpha(((class_1747)itemStack.method_7909()).method_7711().method_26403().field_16011, 1.0F), 1.0F);
      matrix.method_22903();
      matrix.method_22904(Projection.centerX(vec) - (double)width / (double)4.0F, vec.w + (double)2.0F, (double)-200.0F + Math.cos(vec.x));
      matrix.method_22905(0.5F, 0.5F, 1.0F);
      context.method_25291(class_1921::method_62277, this.TEXTURE, 0, 0, 0.0F, 0.0F, width, height, width, height, color);
      int posX = 7;
      int posY = 6;

      for(class_1799 stack : stacks.stream().toList()) {
         Render2D.defaultDrawStack(context, stack, (float)posX, (float)posY, false, true, 1.0F);
         posX += 18;
         if (posX >= 165) {
            posY += 18;
            posX = 7;
         }
      }

      matrix.method_22909();
   }

   private void drawText(class_4587 matrix, class_2561 text, double startX, double startY, FontRenderer font, boolean friendBg) {
      int paddingX = 2;
      float paddingY = 0.75F;
      float height = (float)font.getFont().getSize() / 1.5F;
      float width = font.getStringWidth(text);
      float posX = (float)(startX - (double)(width / 2.0F));
      float posY = (float)startY - height;
      int bgColor = friendBg ? (new Color(0, 170, 0, 160)).getRGB() : ColorAssist.getRect(0.65F);
      rectangle.render(ShapeProperties.create(matrix, (double)(posX - (float)paddingX), (double)(posY - paddingY), (double)(width + (float)(paddingX * 2)), (double)(height + paddingY * 2.0F)).round(2.0F).outlineColor((new Color(33, 33, 33, 0)).getRGB()).color(bgColor).build());
      font.drawText(matrix, text, (double)posX, (double)(posY + 3.0F));
   }

   private void drawText(class_4587 matrix, class_2561 text, double startX, double startY, FontRenderer font) {
      this.drawText(matrix, text, startX, startY, font, false);
   }

   private class_5250 getTextPlayer(class_1657 player, boolean friend) {
      float health = PlayerInteractionHelper.getHealth(player);
      class_5250 text = class_2561.method_43473();
      if (friend) {
         String var10001 = String.valueOf(class_124.field_1060);
         text.method_27693("[" + var10001 + "F" + String.valueOf(class_124.field_1070) + "] ");
      }

      if (AntiBot.getInstance().isBot(player)) {
         String var5 = String.valueOf(class_124.field_1079);
         text.method_27693("[" + var5 + "BOT" + String.valueOf(class_124.field_1070) + "] ");
      }

      if (this.playerSetting.isSelected("NameTags")) {
         text.method_10852(player.method_5476());
      } else {
         text.method_10852(player.method_5477());
      }

      if (player.method_6079().method_7909().equals(class_1802.field_8575) || player.method_6079().method_7909().equals(class_1802.field_8288)) {
         String var6 = String.valueOf(class_124.field_1070);
         text.method_27693(var6 + this.getSphere(player.method_6079()));
      }

      if (health >= 0.0F && health <= player.method_6063()) {
         String var7 = String.valueOf(class_124.field_1070);
         text.method_27693(var7 + " [" + String.valueOf(class_124.field_1061) + PlayerInteractionHelper.getHealthString(player) + String.valueOf(class_124.field_1070) + "]");
      }

      return text;
   }

   private String getSphere(class_1799 stack) {
      class_9279 component = (class_9279)stack.method_57824(class_9334.field_49628);
      if (Network.isFunTime() && component != null) {
         class_2487 compound = component.method_57461();
         if (compound.method_10550("tslevel") != 0) {
            String var10000 = String.valueOf(class_124.field_1065);
            return " [" + var10000 + compound.method_10558("don-item").replace("sphere-", "").toUpperCase() + String.valueOf(class_124.field_1070) + "]";
         }
      }

      return "";
   }
}

