package oshi.util;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.regex.Pattern;
import oshi.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class Constants {
   public static final String UNKNOWN = "unknown";
   public static final String SYSFS_SERIAL_PATH = "/sys/devices/virtual/dmi/id/";
   public static final OffsetDateTime UNIX_EPOCH;
   public static final Pattern DIGITS;

   private Constants() {
      throw new AssertionError();
   }

   static {
      UNIX_EPOCH = OffsetDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC);
      DIGITS = Pattern.compile("\\d+");
   }
}
