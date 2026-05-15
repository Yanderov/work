package kotlin.comparisons;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000f\n\u0002\u0010\u000f\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u0017\n\u0002\b\u0003\u001a/\u0010\u0004\u001a\u00028\u0000\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0002\u001a\u00028\u00002\u0006\u0010\u0003\u001a\u00028\u0000H\u0007¢\u0006\u0004\b\u0004\u0010\u0005\u001a7\u0010\u0004\u001a\u00028\u0000\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0002\u001a\u00028\u00002\u0006\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00028\u0000H\u0007¢\u0006\u0004\b\u0004\u0010\u0007\u001a;\u0010\u0004\u001a\u00028\u0000\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0002\u001a\u00028\u00002\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\b\"\u00028\u0000H\u0007¢\u0006\u0004\b\u0004\u0010\n\u001a \u0010\u0004\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\b\u0004\u0010\f\u001a(\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\b\u0004\u0010\r\u001a#\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u000b2\n\u0010\t\u001a\u00020\u000e\"\u00020\u000bH\u0007¢\u0006\u0004\b\u0004\u0010\u000f\u001a \u0010\u0004\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b\u0004\u0010\u0011\u001a(\u0010\u0004\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b\u0004\u0010\u0012\u001a#\u0010\u0004\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00102\n\u0010\t\u001a\u00020\u0013\"\u00020\u0010H\u0007¢\u0006\u0004\b\u0004\u0010\u0014\u001a \u0010\u0004\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u0015H\u0087\b¢\u0006\u0004\b\u0004\u0010\u0016\u001a(\u0010\u0004\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u0015H\u0087\b¢\u0006\u0004\b\u0004\u0010\u0017\u001a#\u0010\u0004\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\n\u0010\t\u001a\u00020\u0018\"\u00020\u0015H\u0007¢\u0006\u0004\b\u0004\u0010\u0019\u001a \u0010\u0004\u001a\u00020\u001a2\u0006\u0010\u0002\u001a\u00020\u001a2\u0006\u0010\u0003\u001a\u00020\u001aH\u0087\b¢\u0006\u0004\b\u0004\u0010\u001b\u001a(\u0010\u0004\u001a\u00020\u001a2\u0006\u0010\u0002\u001a\u00020\u001a2\u0006\u0010\u0003\u001a\u00020\u001a2\u0006\u0010\u0006\u001a\u00020\u001aH\u0087\b¢\u0006\u0004\b\u0004\u0010\u001c\u001a#\u0010\u0004\u001a\u00020\u001a2\u0006\u0010\u0002\u001a\u00020\u001a2\n\u0010\t\u001a\u00020\u001d\"\u00020\u001aH\u0007¢\u0006\u0004\b\u0004\u0010\u001e\u001a \u0010\u0004\u001a\u00020\u001f2\u0006\u0010\u0002\u001a\u00020\u001f2\u0006\u0010\u0003\u001a\u00020\u001fH\u0087\b¢\u0006\u0004\b\u0004\u0010 \u001a(\u0010\u0004\u001a\u00020\u001f2\u0006\u0010\u0002\u001a\u00020\u001f2\u0006\u0010\u0003\u001a\u00020\u001f2\u0006\u0010\u0006\u001a\u00020\u001fH\u0087\b¢\u0006\u0004\b\u0004\u0010!\u001a#\u0010\u0004\u001a\u00020\u001f2\u0006\u0010\u0002\u001a\u00020\u001f2\n\u0010\t\u001a\u00020\"\"\u00020\u001fH\u0007¢\u0006\u0004\b\u0004\u0010#\u001a \u0010\u0004\u001a\u00020$2\u0006\u0010\u0002\u001a\u00020$2\u0006\u0010\u0003\u001a\u00020$H\u0087\b¢\u0006\u0004\b\u0004\u0010%\u001a(\u0010\u0004\u001a\u00020$2\u0006\u0010\u0002\u001a\u00020$2\u0006\u0010\u0003\u001a\u00020$2\u0006\u0010\u0006\u001a\u00020$H\u0087\b¢\u0006\u0004\b\u0004\u0010&\u001a#\u0010\u0004\u001a\u00020$2\u0006\u0010\u0002\u001a\u00020$2\n\u0010\t\u001a\u00020'\"\u00020$H\u0007¢\u0006\u0004\b\u0004\u0010(\u001a/\u0010)\u001a\u00028\u0000\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0002\u001a\u00028\u00002\u0006\u0010\u0003\u001a\u00028\u0000H\u0007¢\u0006\u0004\b)\u0010\u0005\u001a7\u0010)\u001a\u00028\u0000\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0002\u001a\u00028\u00002\u0006\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00028\u0000H\u0007¢\u0006\u0004\b)\u0010\u0007\u001a;\u0010)\u001a\u00028\u0000\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0002\u001a\u00028\u00002\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\b\"\u00028\u0000H\u0007¢\u0006\u0004\b)\u0010\n\u001a \u0010)\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\b)\u0010\f\u001a(\u0010)\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\b)\u0010\r\u001a#\u0010)\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u000b2\n\u0010\t\u001a\u00020\u000e\"\u00020\u000bH\u0007¢\u0006\u0004\b)\u0010\u000f\u001a \u0010)\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b)\u0010\u0011\u001a(\u0010)\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b)\u0010\u0012\u001a#\u0010)\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00102\n\u0010\t\u001a\u00020\u0013\"\u00020\u0010H\u0007¢\u0006\u0004\b)\u0010\u0014\u001a \u0010)\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u0015H\u0087\b¢\u0006\u0004\b)\u0010\u0016\u001a(\u0010)\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u0015H\u0087\b¢\u0006\u0004\b)\u0010\u0017\u001a#\u0010)\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\n\u0010\t\u001a\u00020\u0018\"\u00020\u0015H\u0007¢\u0006\u0004\b)\u0010\u0019\u001a \u0010)\u001a\u00020\u001a2\u0006\u0010\u0002\u001a\u00020\u001a2\u0006\u0010\u0003\u001a\u00020\u001aH\u0087\b¢\u0006\u0004\b)\u0010\u001b\u001a(\u0010)\u001a\u00020\u001a2\u0006\u0010\u0002\u001a\u00020\u001a2\u0006\u0010\u0003\u001a\u00020\u001a2\u0006\u0010\u0006\u001a\u00020\u001aH\u0087\b¢\u0006\u0004\b)\u0010\u001c\u001a#\u0010)\u001a\u00020\u001a2\u0006\u0010\u0002\u001a\u00020\u001a2\n\u0010\t\u001a\u00020\u001d\"\u00020\u001aH\u0007¢\u0006\u0004\b)\u0010\u001e\u001a \u0010)\u001a\u00020\u001f2\u0006\u0010\u0002\u001a\u00020\u001f2\u0006\u0010\u0003\u001a\u00020\u001fH\u0087\b¢\u0006\u0004\b)\u0010 \u001a(\u0010)\u001a\u00020\u001f2\u0006\u0010\u0002\u001a\u00020\u001f2\u0006\u0010\u0003\u001a\u00020\u001f2\u0006\u0010\u0006\u001a\u00020\u001fH\u0087\b¢\u0006\u0004\b)\u0010!\u001a#\u0010)\u001a\u00020\u001f2\u0006\u0010\u0002\u001a\u00020\u001f2\n\u0010\t\u001a\u00020\"\"\u00020\u001fH\u0007¢\u0006\u0004\b)\u0010#\u001a \u0010)\u001a\u00020$2\u0006\u0010\u0002\u001a\u00020$2\u0006\u0010\u0003\u001a\u00020$H\u0087\b¢\u0006\u0004\b)\u0010%\u001a(\u0010)\u001a\u00020$2\u0006\u0010\u0002\u001a\u00020$2\u0006\u0010\u0003\u001a\u00020$2\u0006\u0010\u0006\u001a\u00020$H\u0087\b¢\u0006\u0004\b)\u0010&\u001a#\u0010)\u001a\u00020$2\u0006\u0010\u0002\u001a\u00020$2\n\u0010\t\u001a\u00020'\"\u00020$H\u0007¢\u0006\u0004\b)\u0010(¨\u0006*"},
   d2 = {"", "T", "a", "b", "maxOf", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "c", "(Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "", "other", "(Ljava/lang/Comparable;[Ljava/lang/Comparable;)Ljava/lang/Comparable;", "", "(BB)B", "(BBB)B", "", "(B[B)B", "", "(DD)D", "(DDD)D", "", "(D[D)D", "", "(FF)F", "(FFF)F", "", "(F[F)F", "", "(II)I", "(III)I", "", "(I[I)I", "", "(JJ)J", "(JJJ)J", "", "(J[J)J", "", "(SS)S", "(SSS)S", "", "(S[S)S", "minOf", "kotlin-stdlib"},
   xs = "kotlin/comparisons/ComparisonsKt"
)
class ComparisonsKt___ComparisonsJvmKt extends ComparisonsKt__ComparisonsKt {
   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Comparable maxOf(@NotNull Comparable a, @NotNull Comparable b) {
      Intrinsics.checkNotNullParameter(a, "a");
      Intrinsics.checkNotNullParameter(b, "b");
      return a.compareTo(b) >= 0 ? a : b;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final byte maxOf(byte a, byte b) {
      return (byte)Math.max(a, b);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final short maxOf(short a, short b) {
      return (short)Math.max(a, b);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final int maxOf(int a, int b) {
      return Math.max(a, b);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final long maxOf(long a, long b) {
      return Math.max(a, b);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final float maxOf(float a, float b) {
      return Math.max(a, b);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final double maxOf(double a, double b) {
      return Math.max(a, b);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Comparable maxOf(@NotNull Comparable a, @NotNull Comparable b, @NotNull Comparable c) {
      Intrinsics.checkNotNullParameter(a, "a");
      Intrinsics.checkNotNullParameter(b, "b");
      Intrinsics.checkNotNullParameter(c, "c");
      return ComparisonsKt.maxOf(a, ComparisonsKt.maxOf(b, c));
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final byte maxOf(byte a, byte b, byte c) {
      return (byte)Math.max(a, Math.max(b, c));
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final short maxOf(short a, short b, short c) {
      return (short)Math.max(a, Math.max(b, c));
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final int maxOf(int a, int b, int c) {
      return Math.max(a, Math.max(b, c));
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final long maxOf(long a, long b, long c) {
      return Math.max(a, Math.max(b, c));
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final float maxOf(float a, float b, float c) {
      return Math.max(a, Math.max(b, c));
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final double maxOf(double a, double b, double c) {
      return Math.max(a, Math.max(b, c));
   }

   @SinceKotlin(
      version = "1.4"
   )
   @NotNull
   public static final Comparable maxOf(@NotNull Comparable a, @NotNull Comparable... other) {
      Intrinsics.checkNotNullParameter(a, "a");
      Intrinsics.checkNotNullParameter(other, "other");
      Comparable max = a;
      int var3 = 0;

      for(int var4 = other.length; var3 < var4; ++var3) {
         Comparable e = other[var3];
         max = ComparisonsKt.maxOf(max, e);
      }

      return max;
   }

   @SinceKotlin(
      version = "1.4"
   )
   public static final byte maxOf(byte a, @NotNull byte... other) {
      Intrinsics.checkNotNullParameter(other, "other");
      byte max = a;
      int var3 = 0;

      for(int var4 = other.length; var3 < var4; ++var3) {
         byte e = other[var3];
         max = (byte)Math.max(max, e);
      }

      return max;
   }

   @SinceKotlin(
      version = "1.4"
   )
   public static final short maxOf(short a, @NotNull short... other) {
      Intrinsics.checkNotNullParameter(other, "other");
      short max = a;
      int var3 = 0;

      for(int var4 = other.length; var3 < var4; ++var3) {
         short e = other[var3];
         max = (short)Math.max(max, e);
      }

      return max;
   }

   @SinceKotlin(
      version = "1.4"
   )
   public static final int maxOf(int a, @NotNull int... other) {
      Intrinsics.checkNotNullParameter(other, "other");
      int max = a;
      int var3 = 0;

      for(int var4 = other.length; var3 < var4; ++var3) {
         int e = other[var3];
         max = Math.max(max, e);
      }

      return max;
   }

   @SinceKotlin(
      version = "1.4"
   )
   public static final long maxOf(long a, @NotNull long... other) {
      Intrinsics.checkNotNullParameter(other, "other");
      long max = a;
      int var5 = 0;

      for(int var6 = other.length; var5 < var6; ++var5) {
         long e = other[var5];
         max = Math.max(max, e);
      }

      return max;
   }

   @SinceKotlin(
      version = "1.4"
   )
   public static final float maxOf(float a, @NotNull float... other) {
      Intrinsics.checkNotNullParameter(other, "other");
      float max = a;
      int var3 = 0;

      for(int var4 = other.length; var3 < var4; ++var3) {
         float e = other[var3];
         max = Math.max(max, e);
      }

      return max;
   }

   @SinceKotlin(
      version = "1.4"
   )
   public static final double maxOf(double a, @NotNull double... other) {
      Intrinsics.checkNotNullParameter(other, "other");
      double max = a;
      int var5 = 0;

      for(int var6 = other.length; var5 < var6; ++var5) {
         double e = other[var5];
         max = Math.max(max, e);
      }

      return max;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Comparable minOf(@NotNull Comparable a, @NotNull Comparable b) {
      Intrinsics.checkNotNullParameter(a, "a");
      Intrinsics.checkNotNullParameter(b, "b");
      return a.compareTo(b) <= 0 ? a : b;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final byte minOf(byte a, byte b) {
      return (byte)Math.min(a, b);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final short minOf(short a, short b) {
      return (short)Math.min(a, b);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final int minOf(int a, int b) {
      return Math.min(a, b);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final long minOf(long a, long b) {
      return Math.min(a, b);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final float minOf(float a, float b) {
      return Math.min(a, b);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final double minOf(double a, double b) {
      return Math.min(a, b);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Comparable minOf(@NotNull Comparable a, @NotNull Comparable b, @NotNull Comparable c) {
      Intrinsics.checkNotNullParameter(a, "a");
      Intrinsics.checkNotNullParameter(b, "b");
      Intrinsics.checkNotNullParameter(c, "c");
      return ComparisonsKt.minOf(a, ComparisonsKt.minOf(b, c));
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final byte minOf(byte a, byte b, byte c) {
      return (byte)Math.min(a, Math.min(b, c));
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final short minOf(short a, short b, short c) {
      return (short)Math.min(a, Math.min(b, c));
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final int minOf(int a, int b, int c) {
      return Math.min(a, Math.min(b, c));
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final long minOf(long a, long b, long c) {
      return Math.min(a, Math.min(b, c));
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final float minOf(float a, float b, float c) {
      return Math.min(a, Math.min(b, c));
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final double minOf(double a, double b, double c) {
      return Math.min(a, Math.min(b, c));
   }

   @SinceKotlin(
      version = "1.4"
   )
   @NotNull
   public static final Comparable minOf(@NotNull Comparable a, @NotNull Comparable... other) {
      Intrinsics.checkNotNullParameter(a, "a");
      Intrinsics.checkNotNullParameter(other, "other");
      Comparable min = a;
      int var3 = 0;

      for(int var4 = other.length; var3 < var4; ++var3) {
         Comparable e = other[var3];
         min = ComparisonsKt.minOf(min, e);
      }

      return min;
   }

   @SinceKotlin(
      version = "1.4"
   )
   public static final byte minOf(byte a, @NotNull byte... other) {
      Intrinsics.checkNotNullParameter(other, "other");
      byte min = a;
      int var3 = 0;

      for(int var4 = other.length; var3 < var4; ++var3) {
         byte e = other[var3];
         min = (byte)Math.min(min, e);
      }

      return min;
   }

   @SinceKotlin(
      version = "1.4"
   )
   public static final short minOf(short a, @NotNull short... other) {
      Intrinsics.checkNotNullParameter(other, "other");
      short min = a;
      int var3 = 0;

      for(int var4 = other.length; var3 < var4; ++var3) {
         short e = other[var3];
         min = (short)Math.min(min, e);
      }

      return min;
   }

   @SinceKotlin(
      version = "1.4"
   )
   public static final int minOf(int a, @NotNull int... other) {
      Intrinsics.checkNotNullParameter(other, "other");
      int min = a;
      int var3 = 0;

      for(int var4 = other.length; var3 < var4; ++var3) {
         int e = other[var3];
         min = Math.min(min, e);
      }

      return min;
   }

   @SinceKotlin(
      version = "1.4"
   )
   public static final long minOf(long a, @NotNull long... other) {
      Intrinsics.checkNotNullParameter(other, "other");
      long min = a;
      int var5 = 0;

      for(int var6 = other.length; var5 < var6; ++var5) {
         long e = other[var5];
         min = Math.min(min, e);
      }

      return min;
   }

   @SinceKotlin(
      version = "1.4"
   )
   public static final float minOf(float a, @NotNull float... other) {
      Intrinsics.checkNotNullParameter(other, "other");
      float min = a;
      int var3 = 0;

      for(int var4 = other.length; var3 < var4; ++var3) {
         float e = other[var3];
         min = Math.min(min, e);
      }

      return min;
   }

   @SinceKotlin(
      version = "1.4"
   )
   public static final double minOf(double a, @NotNull double... other) {
      Intrinsics.checkNotNullParameter(other, "other");
      double min = a;
      int var5 = 0;

      for(int var6 = other.length; var5 < var6; ++var5) {
         double e = other[var5];
         min = Math.min(min, e);
      }

      return min;
   }

   public ComparisonsKt___ComparisonsJvmKt() {
   }
}
