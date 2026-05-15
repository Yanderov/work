package dev.client.yanderov.utils.client.text;

import antidaunleak.api.UserProfile;
import java.util.Arrays;
import java.util.List;

public class TextAnimation {
   private final List messages;
   private String currentText = "";
   private int currentMessageIndex = 0;
   private int animationTick = 0;
   private boolean isRemoving = false;
   private boolean showUnderscore = true;
   private int underscoreTick = 0;
   private final int delayTicks = 2;
   private final int pauseTicksMax = 60;
   private int pauseTicks = 0;
   private final int underscoreBlinkTicks = 10;

   public TextAnimation() {
      String username = UserProfile.getInstance().profile("username");
      this.messages = Arrays.asList("Glad to see you again, " + username + "! Come on in, everythingâ€™s ready for you", "Welcome back, " + username + "! Ready to dive into the adventure?", "Hey " + username + ", letâ€™s make some epic moments today!", username + ", the game awaits your legendary skills!");
   }

   public void updateText() {
      if (this.pauseTicks > 0) {
         --this.pauseTicks;
         this.updateUnderscore();
      } else {
         if (this.animationTick >= 2) {
            String fullText = (String)this.messages.get(this.currentMessageIndex);
            if (this.isRemoving) {
               if (this.currentText.length() > 0) {
                  this.currentText = this.currentText.substring(0, this.currentText.length() - 1);
               } else {
                  this.isRemoving = false;
                  this.currentMessageIndex = (this.currentMessageIndex + 1) % this.messages.size();
                  this.pauseTicks = 60;
               }
            } else if (this.currentText.length() < fullText.length()) {
               this.currentText = fullText.substring(0, this.currentText.length() + 1);
            } else {
               this.isRemoving = true;
               this.pauseTicks = 60;
            }

            this.animationTick = 0;
         }

         ++this.animationTick;
         this.updateUnderscore();
      }
   }

   private void updateUnderscore() {
      ++this.underscoreTick;
      if (this.underscoreTick >= 10) {
         this.showUnderscore = !this.showUnderscore;
         this.underscoreTick = 0;
      }

   }

   public String getCurrentText() {
      return this.currentText + (this.showUnderscore ? "_" : "");
   }
}

