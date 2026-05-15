package fun.Yanderov.display.hud;

import com.mojang.authlib.GameProfile;
import fun.Yanderov.common.animation.Animation;
import fun.Yanderov.common.animation.Direction;
import fun.Yanderov.common.animation.implement.Decelerate;
import fun.Yanderov.common.repository.staff.StaffRepository;
import fun.Yanderov.features.impl.render.Hud;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.api.draggable.AbstractDraggable;
import fun.Yanderov.utils.client.packet.network.Network;
import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.display.font.FontRenderer;
import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.display.geometry.Render2D;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import fun.Yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import net.minecraft.class_10042;
import net.minecraft.class_124;
import net.minecraft.class_1309;
import net.minecraft.class_2561;
import net.minecraft.class_268;
import net.minecraft.class_269;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_5250;
import net.minecraft.class_640;
import net.minecraft.class_746;
import net.minecraft.class_897;
import net.minecraft.class_922;

public class StaffList extends AbstractDraggable {
   public final Map list = new HashMap();
   private final Set notifiedPlayers = new HashSet();
   private final Pattern namePattern = Pattern.compile("^\\w{3,16}$");
   private long lastColorChange = 0L;
   private int currentColorIndex = 0;
   private static final Map CHAR_TO_NAME = new HashMap();
   private static final Map PREFIX_COLORS = new HashMap();

   public static StaffList getInstance() {
      return (StaffList)Instance.getDraggable(StaffList.class);
   }

   public StaffList() {
      super("Staff List", 115, 40, 80, 23, true);
   }

   public boolean visible() {
      return !this.list.isEmpty() || PlayerInteractionHelper.isChat(mc.field_1755);
   }

   public void tick() {
      if (mc.field_1687 != null && mc.field_1724 != null && mc.method_1562() != null) {
         Collection<class_640> playerList = mc.method_1562().method_2880();
         class_269 scoreboard = mc.field_1687.method_8428();
         Set<String> addedNames = new HashSet();
         if (this.list.isEmpty() && PlayerInteractionHelper.isChat(mc.field_1755)) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - this.lastColorChange >= 1000L) {
               this.currentColorIndex = (this.currentColorIndex + 1) % PREFIX_COLORS.size();
               this.lastColorChange = System.currentTimeMillis();
            }

         } else {
            for(class_640 entry : playerList) {
               String name = entry.method_2966().getName();
               if (!addedNames.contains(name) && !this.list.containsKey(entry)) {
                  if (entry.method_2971() != null) {
                     entry.method_2971().getString();
                  }
               }
            }

            for(StaffRepository.Staff staff : StaffRepository.getStaff()) {
               String staffName = staff.getName();
               if (!addedNames.contains(staffName) && !this.list.keySet().stream().anyMatch((e) -> e.method_2966().getName().equals(staffName))) {
                  playerList.stream().filter((p) -> p.method_2966().getName().equalsIgnoreCase(staffName)).findFirst().ifPresent((entryx) -> {
                     this.list.put(entryx, (new Decelerate()).setMs(150).setValue((double)1.0F));
                     addedNames.add(staffName);
                  });
               }
            }

            List<class_268> teams = new ArrayList(scoreboard.method_1159());
            teams.sort(Comparator.comparing(class_268::method_1197));
            Collection<class_640> online = mc.method_1562().method_2880();

            for(class_268 team : teams) {
               Collection<String> members = team.method_1204();
               if (members.size() == 1) {
                  String name = (String)members.iterator().next();
                  if (this.namePattern.matcher(name).matches() && !addedNames.contains(name)) {
                     boolean present = online.stream().anyMatch((e) -> e.method_2966() != null && name.equals(e.method_2966().getName()));
                     if (!present && !this.list.keySet().stream().anyMatch((e) -> e.method_2966().getName().equals(name))) {
                        String teamPrefix = team.method_1144().getString();
                        String prefix = (String)CHAR_TO_NAME.entrySet().stream().filter((e) -> teamPrefix.contains((CharSequence)e.getKey())).map(Map.Entry::getValue).findFirst().orElse("");
                        class_5250 displayName = class_2561.method_43473();
                        if (Network.isReallyWorld()) {
                           displayName.method_10852(class_2561.method_43470(name).method_27692(class_124.field_1080)).method_10852(class_2561.method_43470(" [").method_27692(class_124.field_1080)).method_10852(class_2561.method_43470(prefix.isEmpty() ? "V" : prefix).method_27692(class_124.field_1070)).method_10852(class_2561.method_43470("]").method_27692(class_124.field_1080));
                        } else {
                           displayName.method_10852(class_2561.method_43470("[").method_27692(class_124.field_1080)).method_10852(class_2561.method_43470(prefix.isEmpty() ? "V" : prefix).method_27692(class_124.field_1070)).method_10852(class_2561.method_43470("] ").method_27692(class_124.field_1080)).method_10852(class_2561.method_43470(name).method_27692(class_124.field_1080));
                        }

                        GameProfile fakeProfile = new GameProfile(UUID.randomUUID(), name);
                        class_640 fake = new class_640(fakeProfile, mc.method_1542());
                        fake.method_2962(displayName);
                        fake.method_62153(Integer.MIN_VALUE);
                        this.list.put(fake, (new Decelerate()).setMs(150).setValue((double)1.0F));
                        addedNames.add(name);
                        if (Hud.getInstance().notificationSettings.isSelected("Staff Join") && !this.notifiedPlayers.contains(name)) {
                           Notifications.getInstance().addList((class_2561)class_2561.method_43470(name + " - Ð—Ð°ÑˆÐµÐ» Ð½Ð° ÑÐµÑ€Ð²ÐµÑ€!"), 5000L);
                           this.notifiedPlayers.add(name);
                        }
                     }
                  }
               }
            }

            this.list.entrySet().removeIf((entryx) -> {
               String name = ((class_640)entryx.getKey()).method_2966().getName();
               boolean isFromRepo = StaffRepository.isStaff(name);
               boolean inPlayerList = playerList.stream().anyMatch((p) -> p.method_2966().getName().equals(name));
               Stream var10000 = scoreboard.method_1159().stream().flatMap((t) -> t.method_1204().stream());
               Objects.requireNonNull(name);
               boolean inTeam = var10000.anyMatch(name::equals);
               boolean shouldRemove = false;
               if (isFromRepo) {
                  if (!inPlayerList) {
                     shouldRemove = true;
                  }
               } else if (inPlayerList || !inTeam) {
                  shouldRemove = true;
               }

               if (shouldRemove) {
                  ((Animation)entryx.getValue()).setDirection(Direction.BACKWARDS);
               }

               if (((Animation)entryx.getValue()).isFinished(Direction.BACKWARDS)) {
                  this.notifiedPlayers.remove(name);
                  if (!inPlayerList && Hud.getInstance().notificationSettings.isSelected("Staff Leave")) {
                     Notifications.getInstance().addList((class_2561)class_2561.method_43470(name + " - Ð’Ñ‹ÑˆÐµÐ» Ñ ÑÐµÑ€Ð²ÐµÑ€Ð°!"), 5000L);
                  }

                  return true;
               } else {
                  return false;
               }
            });
         }
      } else {
         this.list.clear();
      }
   }

   public void drawDraggable(class_332 context) {
      class_4587 matrix = context.method_51448();
      FontRenderer font = Fonts.getSize(13, Fonts.Type.DEFAULT);
      FontRenderer fontPlayer = Fonts.getSize(13, Fonts.Type.DEFAULT);
      FontRenderer icon = Fonts.getSize(19, Fonts.Type.ICONS);
      FontRenderer items = Fonts.getSize(12, Fonts.Type.DEFAULT);
      long activeStaff = this.list.entrySet().stream().filter((e) -> !((Animation)e.getValue()).isFinished(Direction.BACKWARDS)).count();
      String staffCountText = String.valueOf(activeStaff);
      float textWidth = items.getStringWidth(staffCountText);
      float boxWidth = textWidth + 6.0F;
      blur.render(ShapeProperties.create(matrix, (double)this.getX(), (double)this.getY(), (double)this.getWidth(), (double)15.5F).round(1.5F, 0.0F, 1.5F, 0.0F).quality(12.0F).color((new Color(0, 0, 0, 150)).getRGB()).build());
      rectangle.render(ShapeProperties.create(matrix, (double)this.getX(), (double)this.getY(), (double)this.getWidth(), (double)15.5F).round(1.5F, 0.0F, 1.5F, 0.0F).thickness(0.1F).outlineColor((new Color(33, 33, 33, 255)).getRGB()).color((new Color(18, 19, 20, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(18, 19, 20, 75)).getRGB()).build());
      items.drawString(matrix, "Active:", (double)((float)(this.getX() + this.getWidth()) - boxWidth - 22.0F), (double)(this.getY() + 7), ColorAssist.getText());
      items.drawString(matrix, staffCountText, (double)((float)(this.getX() + this.getWidth()) - boxWidth - 3.0F), (double)(this.getY() + 7), (new Color(225, 225, 255, 255)).getRGB());
      rectangle.render(ShapeProperties.create(matrix, (double)(this.getX() + 18), (double)(this.getY() + 5), (double)0.5F, (double)6.0F).color(ColorAssist.getText(0.5F)).round(0.0F).build());
      blur.render(ShapeProperties.create(matrix, (double)this.getX(), (double)((float)this.getY() + 16.5F), (double)this.getWidth(), (double)(this.getHeight() - 17)).round(0.0F, 1.5F, 0.0F, 1.5F).quality(12.0F).color((new Color(0, 0, 0, 150)).getRGB()).build());
      rectangle.render(ShapeProperties.create(matrix, (double)this.getX(), (double)((float)this.getY() + 16.5F), (double)this.getWidth(), (double)(this.getHeight() - 17)).round(0.0F, 1.5F, 0.0F, 1.5F).thickness(0.1F).outlineColor((new Color(33, 33, 33, 255)).getRGB()).color((new Color(18, 19, 20, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(18, 19, 20, 75)).getRGB()).build());
      icon.drawString(matrix, "E", (double)((float)this.getX() + 5.0F), (double)((float)this.getY() + 6.0F), (new Color(225, 225, 255, 255)).getRGB());
      font.drawString(matrix, this.getName(), (double)(this.getX() + 22), (double)((float)this.getY() + 6.5F), ColorAssist.getText());
      float centerX = (float)this.getX() + (float)this.getWidth() / 2.0F;
      int offset = 23;
      int maxWidth = 80;
      Collection<class_640> playerList = ((class_746)Objects.requireNonNull(mc.field_1724)).field_3944.method_2880();

      for(Map.Entry staff : this.list.entrySet()) {
         class_640 player = (class_640)staff.getKey();
         if (player != null) {
            String name = player.method_2966().getName();
            float centerY = (float)(this.getY() + offset);
            float animation = ((Animation)staff.getValue()).getOutput().floatValue();
            boolean isVisible = playerList.stream().anyMatch((p) -> p.method_2966().getName().equals(name));
            class_640 renderEntry = isVisible ? (class_640)playerList.stream().filter((p) -> p.method_2966().getName().equals(name)).findFirst().orElse(player) : player;
            String displayName = renderEntry.method_2971() != null ? renderEntry.method_2971().getString() : name;
            String prefix = (String)CHAR_TO_NAME.entrySet().stream().filter((e) -> displayName.contains((CharSequence)e.getKey())).map(Map.Entry::getValue).findFirst().orElse("Vanish");
            int prefixColor = (Integer)PREFIX_COLORS.getOrDefault(prefix, (new Color(255, 0, 0, 255)).getRGB());
            class_2960 skinTexture = renderEntry.method_52810().comp_1626();
            int textColor = ColorAssist.getText();
            int textAlpha = 255;
            int colorWithAlpha = ColorAssist.rgba(textColor >> 16 & 255, textColor >> 8 & 255, textColor & 255, textAlpha);
            float prefixWidth = fontPlayer.getStringWidth(prefix);
            float prefixBoxWidth = prefixWidth + 6.0F;
            Calculate.scale(matrix, centerX, centerY, 1.0F, animation, () -> {
               Render2D.drawTexture(context, skinTexture, (float)this.getX() + 4.5F, centerY - 1.5F, 8.0F, 3.5F, 8, 8, 64, ColorAssist.getRect(1.0F));
               rectangle.render(ShapeProperties.create(matrix, (double)((float)this.getX() + 15.0F), (double)(centerY - 1.0F), (double)0.5F, (double)7.0F).color(ColorAssist.getOutline(1.0F, 0.5F)).build());
               fontPlayer.drawString(matrix, name, (double)(this.getX() + 19), (double)(centerY + 1.0F), colorWithAlpha);
               fontPlayer.drawString(matrix, prefix, (double)((float)(this.getX() + this.getWidth()) - prefixWidth - 8.0F), (double)(centerY + 1.0F), prefixColor);
            });
            float width = fontPlayer.getStringWidth(name) + 25.0F + 10.0F;
            maxWidth = (int)Math.max(width, (float)maxWidth);
            offset += (int)(11.0F * animation);
         }
      }

      if (this.list.isEmpty() && PlayerInteractionHelper.isChat(mc.field_1755)) {
         float centerY = (float)(this.getY() + offset);
         String name = "Example Staff";
         String prefix = "Vanish";
         int textColor = ColorAssist.getText();
         int textAlpha = 255;
         int colorWithAlpha = ColorAssist.rgba(textColor >> 16 & 255, textColor >> 8 & 255, textColor & 255, textAlpha);
         int prefixColor = (Integer)PREFIX_COLORS.getOrDefault("Vanish", (new Color(225, 225, 255, 255)).getRGB());
         float prefixWidth = fontPlayer.getStringWidth("Vanish");
         float prefixBoxWidth = prefixWidth + 6.0F;
         Calculate.scale(matrix, centerX, centerY, 1.0F, 1.0F, () -> {
            class_897<? super class_1309, ?> baseRenderer = mc.method_1561().method_3953(mc.field_1724);
            if (baseRenderer instanceof class_922 renderer) {
               class_10042 state = (class_10042)renderer.method_62425(mc.field_1724, tickCounter.method_60637(false));
               class_2960 textureLocation = renderer.method_3885(state);
               Render2D.drawTexture(context, textureLocation, (float)this.getX() + 4.5F, centerY - 1.5F, 8.0F, 3.0F, 8, 8, 64, ColorAssist.getRect(1.0F), ColorAssist.multRed(-1, 1.0F));
            }

            rectangle.render(ShapeProperties.create(matrix, (double)((float)this.getX() + 15.0F), (double)(centerY - 1.0F), (double)0.5F, (double)7.0F).color(ColorAssist.getOutline(1.0F, 0.5F)).build());
            fontPlayer.drawString(matrix, name, (double)(this.getX() + 19), (double)(centerY + 1.0F), colorWithAlpha);
            fontPlayer.drawString(matrix, "Vanish", (double)((float)(this.getX() + this.getWidth()) - prefixWidth - 8.0F), (double)(centerY + 1.0F), prefixColor);
         });
         int width = (int)fontPlayer.getStringWidth(name) + 25 + 10;
         maxWidth = Math.max(width, maxWidth);
         offset += 11;
      }

      this.setWidth(maxWidth + 20);
      this.setHeight(offset);
   }

   static {
      CHAR_TO_NAME.put("ê”€", "player");
      CHAR_TO_NAME.put("ê”„", "hero");
      CHAR_TO_NAME.put("ê”ˆ", "titan");
      CHAR_TO_NAME.put("ê”’", "avenger");
      CHAR_TO_NAME.put("ê”–", "overlord");
      CHAR_TO_NAME.put("ê” ", "magister");
      CHAR_TO_NAME.put("ê”¤", "imperator");
      CHAR_TO_NAME.put("ê”¨", "dragon");
      CHAR_TO_NAME.put("ê”²", "bull");
      CHAR_TO_NAME.put("ê•’", "rabbit");
      CHAR_TO_NAME.put("ê”¶", "tiger");
      CHAR_TO_NAME.put("ê•„", "dracula");
      CHAR_TO_NAME.put("ê•–", "bunny");
      CHAR_TO_NAME.put("ê•€", "hydra");
      CHAR_TO_NAME.put("ê•ˆ", "cobra");
      CHAR_TO_NAME.put("ê”", "media");
      CHAR_TO_NAME.put("ê”…", "yt");
      CHAR_TO_NAME.put("ê• ", "d.helper");
      CHAR_TO_NAME.put("ê”‰", "helper");
      CHAR_TO_NAME.put("ê”“", "ml.moder");
      CHAR_TO_NAME.put("ê”—", "moder");
      CHAR_TO_NAME.put("ê”¡", "moder+");
      CHAR_TO_NAME.put("ê”¥", "st.moder");
      CHAR_TO_NAME.put("ê”©", "gl.moder");
      CHAR_TO_NAME.put("ê”³", "ml.admin");
      CHAR_TO_NAME.put("ê”·", "admin");
      PREFIX_COLORS.put("media", (new Color(255, 0, 0, 255)).getRGB());
      PREFIX_COLORS.put("yt", (new Color(255, 0, 0, 255)).getRGB());
      PREFIX_COLORS.put("d.helper", (new Color(255, 255, 0, 255)).getRGB());
      PREFIX_COLORS.put("helper", (new Color(255, 255, 0, 255)).getRGB());
      PREFIX_COLORS.put("ml.moder", (new Color(0, 255, 255, 255)).getRGB());
      PREFIX_COLORS.put("moder", (new Color(0, 0, 255, 255)).getRGB());
      PREFIX_COLORS.put("moder+", (new Color(0, 0, 255, 255)).getRGB());
      PREFIX_COLORS.put("st.moder", (new Color(128, 0, 128, 255)).getRGB());
      PREFIX_COLORS.put("gl.moder", (new Color(128, 0, 128, 255)).getRGB());
      PREFIX_COLORS.put("ml.admin", (new Color(0, 255, 255, 255)).getRGB());
      PREFIX_COLORS.put("admin", (new Color(255, 0, 0, 255)).getRGB());
      PREFIX_COLORS.put("Vanish", (new Color(255, 0, 0, 255)).getRGB());
   }
}

