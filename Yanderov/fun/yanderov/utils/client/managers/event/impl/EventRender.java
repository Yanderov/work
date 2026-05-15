package fun.Yanderov.utils.client.managers.event.impl;

import net.minecraft.class_10017;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_9779;

public class EventRender extends EventLayer {
   public static class AfterHand extends EventRender {
      class_4587 stack;
      class_9779 tickCounter;

      public class_4587 getStack() {
         return this.stack;
      }

      public class_9779 getTickCounter() {
         return this.tickCounter;
      }

      public AfterHand(class_4587 stack, class_9779 tickCounter) {
         this.stack = stack;
         this.tickCounter = tickCounter;
      }
   }

   public static class BeforeHud extends EventRender {
      class_332 context;
      class_9779 tickCounter;

      public class_332 getContext() {
         return this.context;
      }

      public class_9779 getTickCounter() {
         return this.tickCounter;
      }

      public BeforeHud(class_332 context, class_9779 tickCounter) {
         this.context = context;
         this.tickCounter = tickCounter;
      }
   }

   public static class AfterHud extends EventRender {
      class_332 context;
      class_9779 tickCounter;

      public class_332 getContext() {
         return this.context;
      }

      public class_9779 getTickCounter() {
         return this.tickCounter;
      }

      public AfterHud(class_332 context, class_9779 tickCounter) {
         this.context = context;
         this.tickCounter = tickCounter;
      }
   }

   public static class RenderLabelsEvent extends EventRender {
      class_10017 state;

      public class_10017 getState() {
         return this.state;
      }

      public RenderLabelsEvent(class_10017 state) {
         this.state = state;
      }
   }
}

