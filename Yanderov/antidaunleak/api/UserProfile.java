package antidaunleak.api;

import antidaunleak.api.annotation.Native;
import java.util.HashMap;
import java.util.Map;

public class UserProfile {
   private static final UserProfile instance = new UserProfile();
   private final Map cache = new HashMap();
   private boolean nativeFailed = false;

   public static UserProfile getInstance() {
      return instance;
   }

   private UserProfile() {
      try {
         this.cache.put("username", this.getUsername());
         this.cache.put("hwid", this.getHwid());
         this.cache.put("role", this.getRole());
         this.cache.put("uid", this.getUid());
         this.cache.put("subTime", this.getSubsTime());
      } catch (UnsatisfiedLinkError var2) {
         this.nativeFailed = true;
         this.cache.put("username", "Yanderov");
         this.cache.put("hwid", "hwid-1231294809786-2348786");
         this.cache.put("role", "Free");
         this.cache.put("uid", "Free");
         this.cache.put("subTime", "2025-24-05");
      }

   }

   @Native(
      type = Native.Type.STANDARD
   )
   private native String getUsername();

   @Native(
      type = Native.Type.STANDARD
   )
   private native String getHwid();

   @Native(
      type = Native.Type.STANDARD
   )
   private native String getRole();

   @Native(
      type = Native.Type.STANDARD
   )
   private native String getUid();

   @Native(
      type = Native.Type.STANDARD
   )
   private native String getSubsTime();

   public String profile(String profile) {
      return (String)this.cache.getOrDefault(profile, "");
   }
}

