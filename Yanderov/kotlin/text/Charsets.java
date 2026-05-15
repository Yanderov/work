package kotlin.text;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u0006R\u0011\u0010\r\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u000f\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0011\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\u0010\u0010\fR\u0014\u0010\u0012\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0006R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0006R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0006R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0006¨\u0006\u0016"},
   d2 = {"Lkotlin/text/Charsets;", "", "<init>", "()V", "Ljava/nio/charset/Charset;", "ISO_8859_1", "Ljava/nio/charset/Charset;", "US_ASCII", "UTF_16", "UTF_16BE", "UTF_16LE", "UTF32", "()Ljava/nio/charset/Charset;", "UTF_32", "UTF32_BE", "UTF_32BE", "UTF32_LE", "UTF_32LE", "UTF_8", "utf_32", "utf_32be", "utf_32le", "kotlin-stdlib"}
)
public final class Charsets {
   @NotNull
   public static final Charsets INSTANCE = new Charsets();
   @JvmField
   @NotNull
   public static final Charset UTF_8;
   @JvmField
   @NotNull
   public static final Charset UTF_16;
   @JvmField
   @NotNull
   public static final Charset UTF_16BE;
   @JvmField
   @NotNull
   public static final Charset UTF_16LE;
   @JvmField
   @NotNull
   public static final Charset US_ASCII;
   @JvmField
   @NotNull
   public static final Charset ISO_8859_1;
   @Nullable
   private static volatile Charset utf_32;
   @Nullable
   private static volatile Charset utf_32le;
   @Nullable
   private static volatile Charset utf_32be;

   private Charsets() {
   }

   @JvmName(
      name = "UTF32"
   )
   @NotNull
   public final Charset UTF32() {
      Charset var10000 = utf_32;
      if (var10000 == null) {
         Charsets $this$_get_UTF_32__u24lambda_u240 = this;
         int var3 = 0;
         var10000 = Charset.forName("UTF-32");
         Intrinsics.checkNotNullExpressionValue(var10000, "forName(...)");
         Charset charset = var10000;
         utf_32 = charset;
         var10000 = charset;
      }

      return var10000;
   }

   @JvmName(
      name = "UTF32_LE"
   )
   @NotNull
   public final Charset UTF32_LE() {
      Charset var10000 = utf_32le;
      if (var10000 == null) {
         Charsets $this$_get_UTF_32LE__u24lambda_u241 = this;
         int var3 = 0;
         var10000 = Charset.forName("UTF-32LE");
         Intrinsics.checkNotNullExpressionValue(var10000, "forName(...)");
         Charset charset = var10000;
         utf_32le = charset;
         var10000 = charset;
      }

      return var10000;
   }

   @JvmName(
      name = "UTF32_BE"
   )
   @NotNull
   public final Charset UTF32_BE() {
      Charset var10000 = utf_32be;
      if (var10000 == null) {
         Charsets $this$_get_UTF_32BE__u24lambda_u242 = this;
         int var3 = 0;
         var10000 = Charset.forName("UTF-32BE");
         Intrinsics.checkNotNullExpressionValue(var10000, "forName(...)");
         Charset charset = var10000;
         utf_32be = charset;
         var10000 = charset;
      }

      return var10000;
   }

   static {
      Charset var10000 = Charset.forName("UTF-8");
      Intrinsics.checkNotNullExpressionValue(var10000, "forName(...)");
      UTF_8 = var10000;
      var10000 = Charset.forName("UTF-16");
      Intrinsics.checkNotNullExpressionValue(var10000, "forName(...)");
      UTF_16 = var10000;
      var10000 = Charset.forName("UTF-16BE");
      Intrinsics.checkNotNullExpressionValue(var10000, "forName(...)");
      UTF_16BE = var10000;
      var10000 = Charset.forName("UTF-16LE");
      Intrinsics.checkNotNullExpressionValue(var10000, "forName(...)");
      UTF_16LE = var10000;
      var10000 = Charset.forName("US-ASCII");
      Intrinsics.checkNotNullExpressionValue(var10000, "forName(...)");
      US_ASCII = var10000;
      var10000 = Charset.forName("ISO-8859-1");
      Intrinsics.checkNotNullExpressionValue(var10000, "forName(...)");
      ISO_8859_1 = var10000;
   }
}
