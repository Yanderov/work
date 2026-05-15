package dev.client.yanderov.utils.client.window;

import antidaunleak.api.UserProfile;

public class WindowTitleAnimation {
   private static final WindowTitleAnimation INSTANCE = new WindowTitleAnimation();
   private String currentTitle;
   private int animationTick;
   private boolean isRemoving;
   private boolean isUserPhase;
   private int pauseTicks;
   private final int delayTicks;
   private final int pauseDuration;

   private WindowTitleAnimation() {
      UserProfile var10001 = UserProfile.getInstance();
      this.currentTitle = "<User: " + var10001.profile("username") + ">";
      this.animationTick = 0;
      this.isRemoving = true;
      this.isUserPhase = true;
      this.pauseTicks = 0;
      this.delayTicks = 1;
      this.pauseDuration = 100;
   }

   public static WindowTitleAnimation getInstance() {
      return INSTANCE;
   }

   public void updateTitle() {
      if (this.pauseTicks > 0) {
         --this.pauseTicks;
      } else {
         if (this.animationTick >= 1) {
            String fullText = this.isUserPhase ? "<User: " + UserProfile.getInstance().profile("username") + ">" : "<Uid: " + UserProfile.getInstance().profile("uid") + ">";
            String newTitle;
            if (this.isRemoving) {
               if (this.currentTitle.length() > 1) {
                  newTitle = this.currentTitle.substring(0, this.currentTitle.length() - 1);
               } else {
                  newTitle = "<";
                  this.isRemoving = false;
               }
            } else if (this.currentTitle.length() < fullText.length()) {
               newTitle = fullText.substring(0, this.currentTitle.length() + 1);
            } else {
               newTitle = fullText;
               this.isRemoving = true;
               this.isUserPhase = !this.isUserPhase;
               this.pauseTicks = 100;
            }

            this.currentTitle = newTitle;
            this.animationTick = 0;
         }

         ++this.animationTick;
      }
   }

   public String getCurrentTitle() {
      return "Yanderov 1.21.4 " + this.currentTitle;
   }
}

