package fun.Yanderov.features.impl.render;

import fun.Yanderov.events.render.DrawEvent;
import fun.Yanderov.events.render.WorldRenderEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.display.font.FontRenderer;
import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.display.geometry.Render2D;
import fun.Yanderov.utils.display.geometry.Render3D;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import fun.Yanderov.utils.features.aura.utils.RaycastAngle;
import fun.Yanderov.utils.features.aura.warp.Turns;
import fun.Yanderov.utils.features.aura.warp.TurnsConnection;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import fun.Yanderov.utils.math.calc.Calculate;
import fun.Yanderov.utils.math.projection.Projection;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1542;
import net.minecraft.class_1665;
import net.minecraft.class_1667;
import net.minecraft.class_1676;
import net.minecraft.class_1680;
import net.minecraft.class_1681;
import net.minecraft.class_1683;
import net.minecraft.class_1684;
import net.minecraft.class_1685;
import net.minecraft.class_1686;
import net.minecraft.class_1753;
import net.minecraft.class_1764;
import net.minecraft.class_1771;
import net.minecraft.class_1776;
import net.minecraft.class_1779;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1823;
import net.minecraft.class_1828;
import net.minecraft.class_1835;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_332;
import net.minecraft.class_3486;
import net.minecraft.class_3532;
import net.minecraft.class_3857;
import net.minecraft.class_3965;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import net.minecraft.class_9278;
import net.minecraft.class_9334;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_3960;
import org.joml.Quaternionf;

public class ProjectilePrediction extends Module {
   private final List points = new ArrayList();

   public static ProjectilePrediction getInstance() {
      return (ProjectilePrediction)Instance.get(ProjectilePrediction.class);
   }

   public ProjectilePrediction() {
      super("ProjectilePrediction", "Projectile Prediction", ModuleCategory.RENDER);
   }

   @EventHandler
   public void onDraw(DrawEvent e) {
      class_332 context = e.getDrawContext();

      for(Point point : this.points) {
         class_243 vec3d = Projection.worldSpaceToScreenSpace(point.pos);
         int ticks = point.ticks;
         if (Projection.canSee(point.pos)) {
            FontRenderer font = Fonts.getSize(13);
            double time = (double)(ticks * 50) / (double)1000.0F;
            Object[] var10001 = new Object[]{time};
            String text = String.format("%.1f", var10001) + " ÑÐµÐº";
            float textWidth = font.getStringWidth(text);
            float posX = (float)(vec3d.method_10216() + (double)(textWidth / 2.0F) - (double)6.0F);
            float posY = (float)(vec3d.method_10214() + (double)4.0F);
            float padding = 3.0F;
            float iconSize = 8.0F;
            blur.render(ShapeProperties.create(context.method_51448(), (double)(posX - textWidth + iconSize + padding), (double)(posY - padding), (double)(padding + textWidth + padding), (double)10.0F).round(1.5F).color(ColorAssist.HALF_BLACK).build());
            font.drawString(context.method_51448(), text, (double)(posX - textWidth + 8.0F + padding * 2.0F), (double)(posY + 0.5F), -1);
            Render2D.defaultDrawStack(context, point.stack, posX - textWidth - padding + 2.0F, posY - padding, true, false, 0.5F);
         }
      }

   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         this.points.clear();
         this.drawPredictionInHand(e.getStack(), mc.field_1724.method_5877(), TurnsConnection.INSTANCE.getRotation());
         this.getProjectiles().forEach((entity) -> {
            class_243 motion = entity.method_18798();
            class_243 pos = entity.method_19538();

            for(int i = 0; i < 300; ++i) {
               class_243 prevPos = pos;
               pos = pos.method_1019(motion);
               motion = this.calculateMotion(entity, prevPos, motion);
               class_239 result = RaycastAngle.raycast(prevPos, pos, class_3960.field_17558, entity);
               if (!result.method_17783().equals(class_240.field_1333)) {
                  pos = result.method_17784();
               }

               Render3D.drawLine(prevPos, pos, ColorAssist.multAlpha(ColorAssist.fade(i), class_3532.method_15363((float)i / 25.0F, 0.0F, 1.0F)), 2.0F, false);
               if (!result.method_17783().equals(class_240.field_1333) || pos.field_1351 < (double)-128.0F) {
                  this.BreakingBad(entity, pos, i);
                  break;
               }
            }

         });
      }
   }

   public void drawPredictionInHand(class_4587 matrix, Iterable stacks, Turns angle) {
      class_1792 activeItem = mc.field_1724.method_6030().method_7909();
      Iterator var5 = stacks.iterator();
      if (var5.hasNext()) {
         class_1799 stack = (class_1799)var5.next();
         class_1792 var10000 = stack.method_7909();
         Objects.requireNonNull(var10000);
         class_1792 var8 = var10000;
         byte var9 = 0;

         label55:
         while(true) {
            //$FF: var9->value
            //0->net/minecraft/class_1779
            //1->net/minecraft/class_1828
            //2->net/minecraft/class_1835
            //3->net/minecraft/class_1823
            //4->net/minecraft/class_1771
            //5->net/minecraft/class_1776
            //6->net/minecraft/class_1753
            //7->net/minecraft/class_1764
            switch (var8.typeSwitch<invokedynamic>(var8, var9)) {
               case 0:
                  class_1779 item = (class_1779)var8;
                  var26 = this.checkTrajectory(new class_1683(mc.field_1687, mc.field_1724, stack), 0.8, angle);
                  break label55;
               case 1:
                  class_1828 item = (class_1828)var8;
                  var26 = this.checkTrajectory(new class_1686(mc.field_1687, mc.field_1724, stack), 0.55, angle);
                  break label55;
               case 2:
                  class_1835 item = (class_1835)var8;
                  if (item.equals(activeItem) && mc.field_1724.method_6048() >= 10) {
                     var26 = this.checkTrajectory(new class_1685(mc.field_1687, mc.field_1724, stack), (double)2.5F, angle);
                     break label55;
                  }

                  var9 = 3;
                  break;
               case 3:
                  class_1823 item = (class_1823)var8;
                  var26 = this.checkTrajectory(new class_1680(mc.field_1687, mc.field_1724, stack), (double)1.5F, angle);
                  break label55;
               case 4:
                  class_1771 item = (class_1771)var8;
                  var26 = this.checkTrajectory(new class_1681(mc.field_1687, mc.field_1724, stack), (double)1.5F, angle);
                  break label55;
               case 5:
                  class_1776 item = (class_1776)var8;
                  var26 = this.checkTrajectory(new class_1684(mc.field_1687, mc.field_1724, stack), (double)1.5F, angle);
                  break label55;
               case 6:
                  class_1753 item = (class_1753)var8;
                  if (item.equals(activeItem) && mc.field_1724.method_6115()) {
                     var26 = this.checkTrajectory(new class_1667(mc.field_1687, mc.field_1724, stack, stack), (double)(3.0F * class_3532.method_15363(((float)mc.field_1724.method_6048() + tickCounter.method_60637(false)) / 20.0F, 0.0F, 1.0F)), angle);
                     break label55;
                  }

                  var9 = 7;
                  break;
               case 7:
                  class_1764 item = (class_1764)var8;
                  if (!class_1764.method_7781(stack)) {
                     var9 = 8;
                     break;
                  }

                  class_9278 component = (class_9278)stack.method_57824(class_9334.field_49649);
                  List<class_239> list = new ArrayList();
                  if (component != null) {
                     float velocity = ((class_1799)component.method_57437().getFirst()).method_31574(class_1802.field_8639) ? 100.0F : 3.0F;
                     list.add(this.checkTrajectory(angle.toVector(), new class_1667(mc.field_1687, mc.field_1724, stack, stack), (double)velocity));
                     if (component.method_57437().size() > 2) {
                        float pitchAbs = angle.getPitch() / 90.0F;
                        float delta = pitchAbs * pitchAbs * pitchAbs * pitchAbs * pitchAbs;
                        float yaw = (float)class_3532.method_48781(Math.abs(delta), 10, 90);
                        float pitch = (float)class_3532.method_48781(delta, 0, 10);
                        list.add(this.checkTrajectory(angle.addYaw(-yaw).addPitch(-pitch).toVector(), new class_1667(mc.field_1687, mc.field_1724, stack, stack), (double)velocity));
                        list.add(this.checkTrajectory(angle.addYaw(yaw * 2.0F).toVector(), new class_1667(mc.field_1687, mc.field_1724, stack, stack), (double)velocity));
                     }
                  }

                  var26 = list;
                  break label55;
               default:
                  var26 = null;
                  break label55;
            }
         }

         List<class_239> results = (List<class_239>)var26;
         if (results != null) {
            List var25 = results.stream().filter(Objects::nonNull).toList();
            if (!var25.isEmpty()) {
               this.renderProjectileResults(matrix, var25);
            }
         }

      }
   }

   public void renderProjectileResults(class_4587 matrix, List results) {
      for(class_239 result : results) {
         class_2350 direction = this.getDirection(result);
         int color = result.method_17783().equals(class_240.field_1331) ? Color.RED.getRGB() : ColorAssist.getClientColor();
         double width = 0.3;
         Quaternionf var10000;
         switch (direction) {
            case field_11039:
            case field_11034:
               var10000 = class_7833.field_40718.rotationDegrees(90.0F);
               break;
            case field_11035:
            case field_11043:
               var10000 = class_7833.field_40714.rotationDegrees(90.0F);
               break;
            default:
               var10000 = new Quaternionf();
         }

         Quaternionf quaternionf = var10000;
         matrix.method_22903();
         matrix.method_61958(result.method_17784());
         matrix.method_22907(quaternionf);
         class_4587.class_4665 entry = matrix.method_23760().method_56822();
         int i = 0;

         for(int size = 90; i <= size; ++i) {
            Render3D.drawLine(entry, Calculate.cosSin(i, size, width), Calculate.cosSin(i + 1, size, width), color, color, 1.0F, false);
         }

         Render3D.drawLine(entry, new class_243((double)0.0F, (double)0.0F, -width), new class_243((double)0.0F, (double)0.0F, width), color, color, 1.0F, false);
         Render3D.drawLine(entry, new class_243(-width, (double)0.0F, (double)0.0F), new class_243(width, (double)0.0F, (double)0.0F), color, color, 1.0F, false);
         matrix.method_22909();
      }

   }

   public List getProjectiles() {
      return PlayerInteractionHelper.streamEntities().filter((e) -> (e instanceof class_1665 || e instanceof class_3857) && !this.visible(e)).toList();
   }

   public List checkTrajectory(class_1676 entity, double velocity, Turns angle) {
      return new ArrayList(Collections.singleton(this.checkTrajectory(angle.toVector(), entity, velocity)));
   }

   public class_239 checkTrajectory(class_243 lookVec, class_1676 entity, double velocity) {
      float sqrt = class_3532.method_15355(lookVec.method_46409().lengthSquared());
      Objects.requireNonNull(entity);
      class_1676 var7 = entity;
      byte var8 = 0;

      class_243 var10000;
      label15:
      while(true) {
         //$FF: var8->value
         //0->net/minecraft/class_1667
         switch (var7.typeSwitch<invokedynamic>(var7, var8)) {
            case 0:
               class_1667 arrow = (class_1667)var7;
               if (!arrow.method_54759().method_7909().equals(class_1802.field_8399)) {
                  var8 = 1;
                  break;
               }

               var10000 = class_243.field_1353;
               break label15;
            default:
               var10000 = mc.field_1724.method_18798();
               break label15;
         }
      }

      class_243 motion = var10000;
      return this.traceTrajectory(mc.field_1724.method_33571().method_1019(Calculate.interpolate(mc.field_1724).method_1020(mc.field_1724.method_19538())), lookVec.method_1021(velocity / (double)sqrt).method_1019(motion), entity);
   }

   public class_239 calcTrajectory(class_1676 e) {
      return this.traceTrajectory(e.method_19538(), e.method_18798(), e);
   }

   public class_239 traceTrajectory(class_243 pos, class_243 motion, class_1676 entity) {
      int i = 0;

      while(true) {
         if (i < 300) {
            class_243 prevPos = pos;
            pos = pos.method_1019(motion);
            motion = this.calculateMotion(entity, prevPos, motion);
            class_239 result = RaycastAngle.raycast(prevPos, pos, class_3960.field_17558, entity);
            if (!result.method_17783().equals(class_240.field_1333)) {
               return result;
            }

            if (PlayerInteractionHelper.streamEntities().filter((ent) -> {
               boolean var10000;
               if (ent instanceof class_1309 living) {
                  if (living != entity.method_24921() && living.method_5805()) {
                     var10000 = true;
                     return var10000;
                  }
               }

               var10000 = false;
               return var10000;
            }).anyMatch((ent) -> ent.method_5829().method_1014(0.3).method_993(prevPos, pos))) {
               return new class_239(pos) {
                  public class_239.class_240 method_17783() {
                     return class_240.field_1331;
                  }
               };
            }

            if (!(pos.field_1351 < (double)-128.0F)) {
               ++i;
               continue;
            }
         }

         return null;
      }
   }

   public class_243 calculateMotion(class_1297 entity, class_243 prevPos, class_243 motion) {
      boolean isInWater = mc.field_1687.method_8316(class_2338.method_49638(prevPos)).method_15767(class_3486.field_15517);
      Objects.requireNonNull(entity);
      class_1297 var7 = entity;
      byte var8 = 0;

      double var10000;
      label19:
      while(true) {
         //$FF: var8->value
         //0->net/minecraft/class_1685
         //1->net/minecraft/class_1665
         switch (var7.typeSwitch<invokedynamic>(var7, var8)) {
            case 0:
               class_1685 trident = (class_1685)var7;
               var10000 = 0.99;
               break label19;
            case 1:
               class_1665 persistent = (class_1665)var7;
               if (!isInWater) {
                  var8 = 2;
                  break;
               }

               var10000 = 0.6;
               break label19;
            default:
               var10000 = isInWater ? 0.8 : 0.99;
               break label19;
         }
      }

      double multiply = var10000;
      return motion.method_1021(multiply).method_1031((double)0.0F, -entity.method_56989(), (double)0.0F);
   }

   private void BreakingBad(class_1297 entity, class_243 pos, int ticks) {
      Objects.requireNonNull(entity);
      byte var5 = 0;
      //$FF: var5->value
      //0->net/minecraft/class_3857
      //1->net/minecraft/class_1665
      switch (entity.typeSwitch<invokedynamic>(entity, var5)) {
         case 0:
            class_3857 thrown = (class_3857)entity;
            this.points.add(new Point(thrown.method_7495(), pos, ticks));
            break;
         case 1:
            class_1665 persistent = (class_1665)entity;
            this.points.add(new Point(persistent.method_54759(), pos, ticks));
      }

   }

   private class_2350 getDirection(class_239 result) {
      if (result instanceof class_3965 blockHitResult) {
         return blockHitResult.method_17780();
      } else {
         return class_2350.method_58251(result.method_17784().method_1020(mc.field_1724.method_33571()).method_1029());
      }
   }

   private boolean visible(class_1297 entity) {
      boolean posChange = entity.method_23317() == entity.field_6014 && entity.method_23318() == entity.field_6036 && entity.method_23321() == entity.field_5969;
      boolean itemEntityCheck = entity instanceof class_1542 && (entity.method_24828() || PlayerInteractionHelper.isBoxInBlock(entity.method_5829().method_1014((double)2.0F), class_2246.field_10382));
      return posChange || itemEntityCheck;
   }

   private static record Point(class_1799 stack, class_243 pos, int ticks) {
   }
}

