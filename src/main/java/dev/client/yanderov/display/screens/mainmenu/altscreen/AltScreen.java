package dev.client.yanderov.display.screens.mainmenu.altscreen;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.UserApiService;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.client.yanderov.Yanderov;
import dev.client.yanderov.common.animation.Animation;
import dev.client.yanderov.common.animation.Direction;
import dev.client.yanderov.common.animation.implement.InOutBack;
import dev.client.yanderov.display.screens.mainmenu.altscreen.impl.Account;
import dev.client.yanderov.display.screens.mainmenu.altscreen.impl.AccountRepository;
import dev.client.yanderov.mixins.client.IMinecraftClient;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.font.FontRenderer;
import dev.client.yanderov.utils.display.font.Fonts;
import dev.client.yanderov.utils.display.geometry.Render2D;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.display.scissor.ScissorAssist;
import dev.client.yanderov.utils.display.shape.ShapeProperties;
import java.awt.Color;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_320;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_5520;
import net.minecraft.class_7569;
import net.minecraft.class_7574;
import net.minecraft.class_7853;
import net.minecraft.class_320.class_321;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;

public class AltScreen implements QuickImports {
   private final AccountRepository accountRepository = YanderovIntegration.getInstance().getAccountRepository();
   private String currentAccount = "";
   private boolean typing = false;
   private String typedText = "";
   private int cursorPos = 0;
   private int selStart = -1;
   private int selEnd = -1;
   private long lastClick = 0L;
   private float textXOffset = 0.0F;
   private boolean dragging = false;
   private static final int MIN_LENGTH = 3;
   private static final int MAX_LENGTH = 16;
   private final Random rand = new Random();
   private float scroll = 0.0F;
   private float smoothedScroll = 0.0F;
   private float panelX;
   private float panelY;
   private final float panelWidth = 160.0F;
   private final float panelHeight = 210.0F;
   private final Map accountAnimations = new HashMap();
   private final Map accountYPositions = new HashMap();
   private final Map accountRemoveAnimations = new HashMap();
   private Animation emptyMessageAnimation = (new InOutBack()).setValue((double)1.0F).setMs(300);
   private boolean wasEmpty = true;
   private long lastActionTime = 0L;
   private static final long ACTION_DELAY = 250L;

   public AltScreen(float x, float y) {
      this.panelX = x;
      this.panelY = y;
      this.currentAccount = this.accountRepository.currentAccount != null ? this.accountRepository.currentAccount : "";
      this.initializeAccountAnimations();
      this.emptyMessageAnimation.setDirection(Direction.FORWARDS);
      this.emptyMessageAnimation.reset();
      this.wasEmpty = this.accountRepository.accountList.isEmpty();
   }

   private void initializeAccountAnimations() {
      for(Account account : this.accountRepository.accountList) {
         if (!this.accountAnimations.containsKey(account.uuid)) {
            Animation anim = (new InOutBack()).setValue((double)1.0F).setMs(300);
            anim.setDirection(Direction.FORWARDS);
            anim.reset();
            this.accountAnimations.put(account.uuid, anim);
         }
      }

   }

   public void updatePosition(float x, float y) {
      float deltaY = y - this.panelY;
      this.panelX = x;
      this.panelY = y;

      for(String key : this.accountYPositions.keySet()) {
         this.accountYPositions.put(key, (Float)this.accountYPositions.get(key) + deltaY);
      }

   }

   public void tick() {
      for(Account account : this.accountRepository.accountList) {
         float target = account.starred ? 1.0F : 0.0F;
         account.starAnim += (target - account.starAnim) * 0.2F;
         if (!this.accountAnimations.containsKey(account.uuid)) {
            Animation anim = (new InOutBack()).setValue((double)1.0F).setMs(300);
            anim.setDirection(Direction.FORWARDS);
            anim.reset();
            this.accountAnimations.put(account.uuid, anim);
         }
      }

      this.accountAnimations.keySet().removeIf((uuid) -> {
         boolean exists = this.accountRepository.accountList.stream().anyMatch((acc) -> acc.uuid.equals(uuid));
         if (!exists) {
            this.accountYPositions.remove(uuid);
         }

         return !exists;
      });
      boolean isEmpty = this.accountRepository.accountList.isEmpty();
      if (isEmpty != this.wasEmpty) {
         this.wasEmpty = isEmpty;
         if (isEmpty) {
            this.emptyMessageAnimation.setDirection(Direction.FORWARDS);
         } else {
            this.emptyMessageAnimation.setDirection(Direction.BACKWARDS);
         }

         this.emptyMessageAnimation.reset();
      }

   }

   public void render(class_332 context, Color buttonColor, Color outlineColor, Color gradientColor, Color textColor, Color bgColor) {
      rectangle.render(ShapeProperties.create(context.method_51448(), (double)(this.panelX + 80.0F - 22.0F), (double)(this.panelY - 13.5F), (double)43.0F, (double)12.0F).thickness(2.0F).round(3.0F).outlineColor(outlineColor.getRGB()).color(buttonColor.getRGB(), buttonColor.getRGB(), gradientColor.getRGB(), gradientColor.getRGB()).build());
      Fonts.getSize(16, Fonts.Type.DEFAULT).drawCenteredString(context.method_51448(), "Accounts", (double)(this.panelX + 80.0F), (double)(this.panelY - 10.0F), textColor.getRGB());
      rectangle.render(ShapeProperties.create(context.method_51448(), (double)this.panelX, (double)this.panelY, (double)160.0F, (double)210.0F).thickness(2.0F).round(10.0F).outlineColor(outlineColor.getRGB()).color(buttonColor.getRGB(), buttonColor.getRGB(), gradientColor.getRGB(), gradientColor.getRGB()).build());
      this.renderTextField(context, buttonColor, outlineColor, gradientColor, textColor);
      this.renderAccountList(context, buttonColor, outlineColor, gradientColor, textColor);
      String displayAccount = this.currentAccount.isEmpty() ? "Not selected" : this.currentAccount;
      String currentText = "Current account Â» " + displayAccount;
      float currentWidth = Fonts.getSize(15, Fonts.Type.SEMI).getStringWidth(currentText) + 20.0F;
      float currentX = this.panelX + 80.0F - currentWidth / 2.0F;
      rectangle.render(ShapeProperties.create(context.method_51448(), (double)currentX, (double)(this.panelY + 210.0F + 2.0F), (double)currentWidth, (double)12.0F).thickness(2.0F).round(3.0F).outlineColor(outlineColor.getRGB()).color(buttonColor.getRGB(), buttonColor.getRGB(), gradientColor.getRGB(), gradientColor.getRGB()).build());
      Fonts.getSize(15, Fonts.Type.SEMI).drawCenteredString(context.method_51448(), currentText, (double)(this.panelX + 80.0F), (double)(this.panelY + 210.0F + 6.0F), textColor.getRGB());
   }

   private void renderTextField(class_332 context, Color buttonColor, Color outlineColor, Color gradientColor, Color textColor) {
      rectangle.render(ShapeProperties.create(context.method_51448(), (double)(this.panelX + 5.0F), (double)(this.panelY + 185.0F), (double)149.0F, (double)20.0F).thickness(2.0F).round(6.0F).outlineColor(outlineColor.getRGB()).color(buttonColor.getRGB(), buttonColor.getRGB(), gradientColor.getRGB(), gradientColor.getRGB()).build());
      rectangle.render(ShapeProperties.create(context.method_51448(), (double)(this.panelX + 5.0F), (double)(this.panelY + 185.0F), (double)149.0F, (double)1.0F).thickness(2.0F).round(5.0F).outlineColor(outlineColor.getRGB()).color((new Color(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), 5)).getRGB(), (new Color(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), textColor.getAlpha())).getRGB(), (new Color(gradientColor.getRed(), gradientColor.getGreen(), gradientColor.getBlue(), textColor.getAlpha())).getRGB(), (new Color(gradientColor.getRed(), gradientColor.getGreen(), gradientColor.getBlue(), 5)).getRGB()).build());
      rectangle.render(ShapeProperties.create(context.method_51448(), (double)(this.panelX + 160.0F - 25.0F), (double)(this.panelY + 187.5F), (double)15.0F, (double)15.0F).thickness(2.0F).round(4.0F).outlineColor(outlineColor.getRGB()).color(buttonColor.getRGB(), buttonColor.getRGB(), gradientColor.getRGB(), gradientColor.getRGB()).build());
      Fonts.getSize(24, Fonts.Type.ICONS).drawString(context.method_51448(), "R", (double)(this.panelX + 160.0F - 24.5F), (double)(this.panelY + 192.0F), textColor.getRGB());
      float textFieldX = this.panelX + 5.0F;
      float textFieldY = this.panelY + 177.0F;
      float textFieldWidth = 149.0F;
      float textFieldHeight = 20.0F;
      FontRenderer font = Fonts.getSize(16, Fonts.Type.DEFAULT);
      long currentTime = System.currentTimeMillis();
      boolean blink = currentTime % 1000L < 500L;
      context.method_44379((int)(textFieldX + 3.0F), (int)textFieldY, (int)(textFieldX + textFieldWidth - 3.0F), (int)(textFieldY + textFieldHeight) + 5);
      if (this.typing && this.hasSelection()) {
         int start = Math.min(this.selStart, this.selEnd);
         int end = Math.max(this.selStart, this.selEnd);
         float selXStart = textFieldX + 5.0F - this.textXOffset + font.getStringWidth(this.typedText.substring(0, start));
         float selWidth = font.getStringWidth(this.typedText.substring(start, end));
         Color selColor = new Color(85, 133, 232, textColor.getAlpha());
         rectangle.render(ShapeProperties.create(context.method_51448(), (double)selXStart, (double)(textFieldY + 13.5F), (double)selWidth, (double)(textFieldHeight - 10.0F)).color(selColor.getRGB()).build());
      }

      if (this.typedText.isEmpty() && !this.typing) {
         font.drawString(context.method_51448(), "Enter nickname", (double)(textFieldX + 5.0F), (double)(textFieldY + 16.0F), textColor.getRGB());
      } else {
         font.drawString(context.method_51448(), this.typedText, (double)(textFieldX + 5.0F - this.textXOffset), (double)(textFieldY + 16.0F), textColor.getRGB());
      }

      if (this.typing && blink && !this.hasSelection()) {
         float cursorX = textFieldX + 5.0F - this.textXOffset + font.getStringWidth(this.typedText.substring(0, this.cursorPos));
         rectangle.render(ShapeProperties.create(context.method_51448(), (double)(cursorX + 1.0F), (double)(textFieldY + 15.0F), (double)0.5F, (double)(textFieldHeight - 13.0F)).color(textColor.getRGB()).build());
      }

      context.method_44380();
   }

   private void renderAccountList(class_332 context, Color buttonColor, Color outlineColor, Color gradientColor, Color textColor) {
      float accountSpacing = 25.0F;
      class_4587 matrix = context.method_51448();
      Matrix4f positionMatrix = matrix.method_23760().method_23761();
      ScissorAssist scissorManager = YanderovIntegration.getInstance().getScissorManager();
      float listY = this.panelY + 5.0F;
      float listHeight = 179.0F;
      scissorManager.push(positionMatrix, this.panelX, listY, 160.0F, listHeight);
      this.smoothedScroll = class_3532.method_16439(0.1F, this.smoothedScroll, this.scroll);
      if (this.accountRepository.accountList.isEmpty()) {
         Fonts.getSize(16, Fonts.Type.DEFAULT).drawCenteredString(context.method_51448(), "Empty    ", (double)(this.panelX + 80.0F - 5.0F), (double)(this.panelY + 105.0F - 10.0F), textColor.getRGB());
         Fonts.getSize(36, Fonts.Type.ICONS).drawCenteredString(context.method_51448(), "   W", (double)(this.panelX + 80.0F + 7.0F), (double)(this.panelY + 105.0F - 16.0F), textColor.getRGB());
      } else {
         for(int i = 0; i < this.accountRepository.accountList.size(); ++i) {
            Account account = (Account)this.accountRepository.accountList.get(i);
            float targetY = this.panelY + 10.0F + (float)i * accountSpacing - this.smoothedScroll;
            String key = account.uuid;
            this.accountYPositions.putIfAbsent(key, targetY);
            float currentY = (Float)this.accountYPositions.get(key);
            currentY = class_3532.method_16439(0.15F, currentY, targetY);
            this.accountYPositions.put(key, currentY);
            Animation anim = (Animation)this.accountAnimations.get(account.uuid);
            Animation removeAnim = (Animation)this.accountRemoveAnimations.get(account.uuid);
            if (anim != null) {
               float animProgress = anim.getOutput().floatValue();
               if (removeAnim != null) {
                  animProgress *= removeAnim.getOutput().floatValue();
               }

               float scale = 0.5F + animProgress * 0.5F;
               int alpha = (int)((float)textColor.getAlpha() * animProgress);
               if (currentY + 20.0F >= listY && currentY <= listY + listHeight) {
                  matrix.method_22903();
                  float centerX = this.panelX + 80.0F;
                  float centerY = currentY + 10.0F;
                  matrix.method_46416(centerX, centerY, 0.0F);
                  matrix.method_22905(scale, scale, 1.0F);
                  matrix.method_46416(-centerX, -centerY, 0.0F);
                  int clampedAlpha = Math.max(0, Math.min(255, alpha));
                  Color animButtonColor = new Color(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), clampedAlpha);
                  Color animGradientColor = new Color(gradientColor.getRed(), gradientColor.getGreen(), gradientColor.getBlue(), clampedAlpha);
                  Color animOutlineColor = new Color(outlineColor.getRed(), outlineColor.getGreen(), outlineColor.getBlue(), clampedAlpha);
                  Color animTextColor = new Color(textColor.getRed(), textColor.getGreen(), textColor.getBlue(), clampedAlpha);
                  rectangle.render(ShapeProperties.create(context.method_51448(), (double)(this.panelX + 5.0F), (double)currentY, (double)149.0F, (double)20.0F).thickness(2.0F).round(5.0F).outlineColor(animOutlineColor.getRGB()).color(animButtonColor.getRGB(), animButtonColor.getRGB(), animGradientColor.getRGB(), animGradientColor.getRGB()).build());
                  rectangle.render(ShapeProperties.create(context.method_51448(), (double)(this.panelX + 5.0F), (double)currentY, (double)149.0F, (double)1.0F).thickness(2.0F).round(5.0F).outlineColor(animOutlineColor.getRGB()).color((new Color(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), Math.max(0, Math.min(255, 5 * clampedAlpha / 255)))).getRGB(), (new Color(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), clampedAlpha)).getRGB(), (new Color(gradientColor.getRed(), gradientColor.getGreen(), gradientColor.getBlue(), clampedAlpha)).getRGB(), (new Color(gradientColor.getRed(), gradientColor.getGreen(), gradientColor.getBlue(), Math.max(0, Math.min(255, 5 * clampedAlpha / 255)))).getRGB()).build());
                  Color starColor = this.interpolateColor(animTextColor, new Color(255, 255, 0, clampedAlpha), account.starAnim);
                  Color faceOutline = new Color(64, 64, 64, clampedAlpha);
                  rectangle.render(ShapeProperties.create(context.method_51448(), (double)(this.panelX + 9.5F), (double)currentY + (double)2.5F, (double)16.0F, (double)16.0F).thickness(4.0F).round(8.0F).outlineColor(faceOutline.getRGB()).color(animButtonColor.getRGB(), animButtonColor.getRGB(), animGradientColor.getRGB(), animGradientColor.getRGB()).build());
                  Fonts.getSize(25, Fonts.Type.ICONS).drawString(context.method_51448(), "â˜…", (double)(this.panelX + 160.0F - 23.5F), (double)(currentY + 4.5F), starColor.getRGB());
                  this.drawAccountFace(context, account, this.panelX + 10.0F, currentY + 3.0F, alpha);
                  Fonts.getSize(15, Fonts.Type.SEMI).drawString(context.method_51448(), account.name, (double)(this.panelX + 28.0F), (double)(currentY + 8.5F), animTextColor.getRGB());
                  matrix.method_22909();
               }
            }
         }
      }

      scissorManager.pop();
      if (this.accountRepository.accountList.size() > 7) {
         this.renderScrollbar(context, listY, listHeight, accountSpacing, textColor.getAlpha());
      }

   }

   private void renderScrollbar(class_332 context, float listY, float listHeight, float accountSpacing, int alpha) {
      float contentHeight = (float)this.accountRepository.accountList.size() * accountSpacing;
      float maxScroll = Math.max(0.0F, contentHeight - listHeight);
      this.scroll = class_3532.method_15363(this.scroll, 0.0F, maxScroll);
      float scrollbarWidth = 2.0F;
      float scrollbarX = this.panelX + 160.0F - scrollbarWidth - 2.5F;
      float scrollbarY = listY + 1.0F;
      float scrollbarHeight = listHeight - 1.0F;
      Color bgScrollColor = new Color(30, 30, 30, (int)((double)100.0F * ((double)alpha / (double)255.0F)));
      rectangle.render(ShapeProperties.create(context.method_51448(), (double)scrollbarX, (double)scrollbarY, (double)scrollbarWidth, (double)scrollbarHeight).round(1.0F).color(bgScrollColor.getRGB()).build());
      float handleHeight = Math.max(20.0F, listHeight * (listHeight / (contentHeight + listHeight)));
      float scrollRatio = this.smoothedScroll / maxScroll;
      float handleY = scrollbarY + (scrollbarHeight - handleHeight) * scrollRatio;
      Color handleColor = new Color(100, 100, 100, (int)((double)150.0F * ((double)alpha / (double)255.0F)));
      rectangle.render(ShapeProperties.create(context.method_51448(), (double)scrollbarX, (double)handleY, (double)scrollbarWidth, (double)handleHeight).round(1.0F).color(handleColor.getRGB()).build());
   }

   private void drawAccountFace(class_332 context, Account account, float x, float y, int alpha) {
      GameProfile profile = new GameProfile(UUID.fromString(account.uuid), account.name);
      class_2960 skinTexture = class_310.method_1551().method_1582().method_52862(profile).comp_1626();
      if (skinTexture == null) {
         skinTexture = class_2960.method_60655("minecraft", "textures/entity/steve.png");
      }

      class_4587 matrices = context.method_51448();
      matrices.method_22903();
      RenderSystem.enableBlend();
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, (float)alpha / 255.0F);
      Render2D.drawTexture(context, skinTexture, x, y, 15.0F, 7.0F, 8, 8, 64, ColorAssist.getRect(1.0F), -1);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.disableBlend();
      matrices.method_22909();
   }

   private Color interpolateColor(Color start, Color end, float t) {
      int r = (int)((float)start.getRed() + (float)(end.getRed() - start.getRed()) * t);
      int g = (int)((float)start.getGreen() + (float)(end.getGreen() - start.getGreen()) * t);
      int b = (int)((float)start.getBlue() + (float)(end.getBlue() - start.getBlue()) * t);
      int a = (int)((float)start.getAlpha() + (float)(end.getAlpha() - start.getAlpha()) * t);
      return new Color(r, g, b, a);
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      float textFieldX = this.panelX + 5.0F;
      float textFieldY = this.panelY + 177.0F;
      if (button == 0 && this.isInBounds(mouseX, mouseY, textFieldX, textFieldY + 8.0F, 130.0F, 20.0F)) {
         this.handleTextFieldClick(mouseX);
         return true;
      } else {
         this.typing = false;
         this.clearSelection();
         if (button == 0 && this.isInBounds(mouseX, mouseY, this.panelX + 160.0F - 25.0F, this.panelY + 187.5F, 15.0F, 15.0F)) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - this.lastActionTime >= 250L) {
               this.lastActionTime = currentTime;
               this.addRandomAccount();
            }

            return true;
         } else {
            return this.handleAccountListClick(mouseX, mouseY, button);
         }
      }
   }

   private void handleTextFieldClick(double mouseX) {
      long currentTime = System.currentTimeMillis();
      if (currentTime - this.lastClick < 250L) {
         this.selStart = 0;
         this.selEnd = this.typedText.length();
      } else {
         this.typing = true;
         this.cursorPos = this.getCursorIndexAt(mouseX);
         this.selStart = this.cursorPos;
         this.selEnd = this.cursorPos;
         this.lastClick = currentTime;
      }

      this.dragging = true;
   }

   private void addRandomAccount() {
      String username = this.generateRandomUsername();
      if (!this.accountExists(username)) {
         String offlineUuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + username).getBytes(StandardCharsets.UTF_8)).toString();
         Account newAccount = new Account(username, false, false, (String)null, offlineUuid, "0");
         this.accountRepository.accountList.add(newAccount);
         this.accountRepository.accountList.sort((a1, a2) -> Boolean.compare(a2.starred, a1.starred));
         Animation anim = (new InOutBack()).setValue((double)1.0F).setMs(300);
         anim.setDirection(Direction.FORWARDS);
         anim.reset();
         this.accountAnimations.put(offlineUuid, anim);
         this.typedText = "";
         this.cursorPos = 0;
         this.clearSelection();
         this.saveAccounts();
      }
   }

   private boolean accountExists(String username) {
      return this.accountRepository.accountList.stream().anyMatch((account) -> account.name.equalsIgnoreCase(username));
   }

   private String generateRandomUsername() {
      char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};
      char[] consonants = new char[]{'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'};
      StringBuilder username = new StringBuilder();
      int length = 6 + this.rand.nextInt(5);
      boolean startWithVowel = this.rand.nextBoolean();

      for(int i = 0; i < length; ++i) {
         if (i % 2 == 0) {
            username.append(startWithVowel ? vowels[this.rand.nextInt(vowels.length)] : consonants[this.rand.nextInt(consonants.length)]);
         } else {
            username.append(startWithVowel ? consonants[this.rand.nextInt(consonants.length)] : vowels[this.rand.nextInt(vowels.length)]);
         }
      }

      if (this.rand.nextInt(100) < 30) {
         username.append(this.rand.nextInt(100));
      }

      String var10000 = username.substring(0, 1).toUpperCase();
      String result = var10000 + username.substring(1);
      if (this.accountRepository.accountList.stream().anyMatch((account) -> account.name.equals(result))) {
         result = result + System.currentTimeMillis() % 1000L;
      }

      return result;
   }

   private boolean handleAccountListClick(double mouseX, double mouseY, int button) {
      float accountSpacing = 25.0F;
      float listY = this.panelY + 5.0F;
      float listHeight = 179.0F;

      for(int i = 0; i < this.accountRepository.accountList.size(); ++i) {
         Account account = (Account)this.accountRepository.accountList.get(i);
         float accY = (Float)this.accountYPositions.getOrDefault(account.uuid, this.panelY + 10.0F + (float)i * accountSpacing - this.smoothedScroll);
         if (!(accY + 20.0F < listY) && !(accY > listY + listHeight)) {
            if (button == 0 && this.isInBounds(mouseX, mouseY, this.panelX + 160.0F - 25.0F, accY + 6.5F, 15.0F, 15.0F)) {
               account.starred = !account.starred;
               this.accountRepository.accountList.sort((a1, a2) -> Boolean.compare(a2.starred, a1.starred));
               this.saveAccounts();
               return true;
            }

            if (button == 0 && this.isInBounds(mouseX, mouseY, this.panelX + 5.0F, accY, 149.0F, 20.0F)) {
               this.currentAccount = account.name;
               this.accountRepository.currentAccount = account.name;
               this.setSession(account);
               this.saveAccounts();
               return true;
            }

            if (button == 1 && this.isInBounds(mouseX, mouseY, this.panelX + 5.0F, accY, 149.0F, 20.0F)) {
               long currentTime = System.currentTimeMillis();
               if (currentTime - this.lastActionTime >= 250L) {
                  this.lastActionTime = currentTime;
                  Account accountToRemove = (Account)this.accountRepository.accountList.get(i);
                  if (accountToRemove.name.equals(this.currentAccount)) {
                     this.accountRepository.currentAccount = "";
                     this.currentAccount = "";
                  }

                  Animation removeAnim = (new InOutBack()).setValue((double)1.0F).setMs(250);
                  removeAnim.setDirection(Direction.BACKWARDS);
                  removeAnim.reset();
                  this.accountRemoveAnimations.put(accountToRemove.uuid, removeAnim);
                  (new Thread(() -> {
                     try {
                        Thread.sleep(250L);
                        this.accountRepository.accountList.remove(accountToRemove);
                        this.accountYPositions.remove(accountToRemove.uuid);
                        this.accountAnimations.remove(accountToRemove.uuid);
                        this.accountRemoveAnimations.remove(accountToRemove.uuid);
                        this.saveAccounts();
                     } catch (InterruptedException e) {
                        e.printStackTrace();
                     }

                  })).start();
               }

               return true;
            }
         }
      }

      return false;
   }

   private void setSession(Account account) {
      class_320 newSession = new class_320(account.name, UUID.fromString(account.uuid), "0", Optional.empty(), Optional.empty(), class_321.field_1988);
      IMinecraftClient mca = (IMinecraftClient)class_310.method_1551();
      mca.setSessionT(newSession);
      class_310.method_1551().method_53462().getProperties().clear();
      UserApiService apiService = UserApiService.OFFLINE;
      mca.setUserApiService(apiService);
      mca.setSocialInteractionsManagerT(new class_5520(class_310.method_1551(), apiService));
      mca.setProfileKeys(class_7853.method_46532(apiService, newSession, class_310.method_1551().field_1697.toPath()));
      mca.setAbuseReportContextT(class_7574.method_44599(class_7569.method_44586(), apiService));
      this.accountRepository.currentAccount = account.name;
      this.currentAccount = account.name;
   }

   private void saveAccounts() {
      try {
         YanderovIntegration.getInstance().getFileController().saveFiles();
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public boolean mouseScrolled(double mouseX, double mouseY, double vertical) {
      if (this.accountRepository.accountList.size() > 7) {
         float listY = this.panelY + 5.0F;
         float listHeight = 179.0F;
         if (this.isInBounds(mouseX, mouseY, this.panelX, listY, 160.0F, listHeight)) {
            float contentHeight = (float)this.accountRepository.accountList.size() * 25.0F;
            float maxScroll = Math.max(0.0F, contentHeight - listHeight);
            this.scroll = (float)((double)this.scroll - vertical * (double)20.0F);
            this.scroll = class_3532.method_15363(this.scroll, 0.0F, maxScroll);
            return true;
         }
      }

      return false;
   }

   public boolean mouseDragged(double mouseX, double mouseY, int button) {
      if (this.dragging && button == 0) {
         this.cursorPos = this.getCursorIndexAt(mouseX);
         this.selEnd = this.cursorPos;
         return true;
      } else {
         return false;
      }
   }

   public boolean mouseReleased() {
      this.dragging = false;
      return false;
   }

   public boolean charTyped(char chr) {
      if (this.typing && this.typedText.length() < 16) {
         this.deleteSelectedText();
         this.typedText = this.typedText.substring(0, this.cursorPos) + chr + this.typedText.substring(this.cursorPos);
         ++this.cursorPos;
         this.clearSelection();
         this.updateTextXOffset();
         return true;
      } else {
         return false;
      }
   }

   public boolean keyPressed(int keyCode) {
      if (this.typing) {
         if (keyCode == 341 || keyCode == 345) {
            return false;
         }

         if (class_310.method_1551().field_1755 != null) {
            class_437 var10000 = class_310.method_1551().field_1755;
            if (class_437.method_25441()) {
               switch (keyCode) {
                  case 65:
                     this.selStart = 0;
                     this.selEnd = this.typedText.length();
                     return true;
                  case 67:
                     if (this.hasSelection()) {
                        GLFW.glfwSetClipboardString(class_310.method_1551().method_22683().method_4490(), this.getSelectedText());
                     }

                     return true;
                  case 86:
                     String clipboard = GLFW.glfwGetClipboardString(class_310.method_1551().method_22683().method_4490());
                     if (clipboard != null) {
                        this.deleteSelectedText();
                        this.typedText = this.typedText.substring(0, this.cursorPos) + clipboard + this.typedText.substring(this.cursorPos);
                        this.cursorPos += clipboard.length();
                        this.clearSelection();
                        this.updateTextXOffset();
                     }

                     return true;
               }
            }
         }

         switch (keyCode) {
            case 257:
               if (this.typedText.length() >= 3 && this.typedText.length() <= 16) {
                  long currentTime = System.currentTimeMillis();
                  if (currentTime - this.lastActionTime >= 250L) {
                     if (this.accountExists(this.typedText)) {
                        this.typedText = "";
                        this.cursorPos = 0;
                        this.typing = false;
                        this.clearSelection();
                        return true;
                     }

                     this.lastActionTime = currentTime;
                     String offlineUuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + this.typedText).getBytes(StandardCharsets.UTF_8)).toString();
                     Account newAccount = new Account(this.typedText, false, false, (String)null, offlineUuid, "0");
                     this.accountRepository.accountList.add(newAccount);
                     this.accountRepository.accountList.sort((a1, a2) -> Boolean.compare(a2.starred, a1.starred));
                     Animation anim = (new InOutBack()).setValue((double)1.0F).setMs(300);
                     anim.setDirection(Direction.FORWARDS);
                     anim.reset();
                     this.accountAnimations.put(offlineUuid, anim);
                     this.typedText = "";
                     this.cursorPos = 0;
                     this.typing = false;
                     this.clearSelection();
                     this.saveAccounts();
                  }
               }

               return true;
            case 258:
            case 260:
            case 261:
            default:
               break;
            case 259:
               if (this.hasSelection()) {
                  this.deleteSelectedText();
               } else if (this.cursorPos > 0) {
                  String var10001 = this.typedText.substring(0, this.cursorPos - 1);
                  this.typedText = var10001 + this.typedText.substring(this.cursorPos);
                  --this.cursorPos;
               }

               this.updateTextXOffset();
               return true;
            case 262:
               if (this.cursorPos < this.typedText.length()) {
                  ++this.cursorPos;
               }

               this.updateSelectionAfterMove();
               this.updateTextXOffset();
               return true;
            case 263:
               if (this.cursorPos > 0) {
                  --this.cursorPos;
               }

               this.updateSelectionAfterMove();
               this.updateTextXOffset();
               return true;
         }
      }

      return false;
   }

   public void reset() {
      this.typing = false;
      this.clearSelection();
   }

   private boolean isInBounds(double mx, double my, float x, float y, float w, float h) {
      return mx >= (double)x && mx <= (double)(x + w) && my >= (double)y && my <= (double)(y + h);
   }

   private boolean hasSelection() {
      return this.selStart != this.selEnd;
   }

   private String getSelectedText() {
      int start = Math.min(this.selStart, this.selEnd);
      int end = Math.max(this.selStart, this.selEnd);
      return this.typedText.substring(start, end);
   }

   private void deleteSelectedText() {
      if (this.hasSelection()) {
         int start = Math.min(this.selStart, this.selEnd);
         int end = Math.max(this.selStart, this.selEnd);
         String var10001 = this.typedText.substring(0, start);
         this.typedText = var10001 + this.typedText.substring(end);
         this.cursorPos = start;
         this.clearSelection();
      }

   }

   private void clearSelection() {
      this.selStart = this.cursorPos;
      this.selEnd = this.cursorPos;
   }

   private void updateSelectionAfterMove() {
      if (class_310.method_1551().field_1755 != null) {
         class_437 var10000 = class_310.method_1551().field_1755;
         if (class_437.method_25442()) {
            this.selEnd = this.cursorPos;
            return;
         }
      }

      this.clearSelection();
   }

   private int getCursorIndexAt(double mouseX) {
      float textFieldX = this.panelX + 5.0F;
      FontRenderer font = Fonts.getSize(16, Fonts.Type.DEFAULT);
      float relX = (float)mouseX - textFieldX - 3.0F + this.textXOffset;

      int pos;
      for(pos = 0; pos < this.typedText.length() && !(font.getStringWidth(this.typedText.substring(0, pos + 1)) > relX); ++pos) {
      }

      return pos;
   }

   private void updateTextXOffset() {
      float textFieldWidth = 149.0F;
      FontRenderer font = Fonts.getSize(16, Fonts.Type.DEFAULT);
      float cursorX = font.getStringWidth(this.typedText.substring(0, this.cursorPos));
      if (cursorX < this.textXOffset) {
         this.textXOffset = cursorX;
      } else if (cursorX > this.textXOffset + textFieldWidth - 10.0F) {
         this.textXOffset = cursorX - (textFieldWidth - 10.0F);
      }

   }
}

