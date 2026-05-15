package kotlin.text;

import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0004\u0017\u0018\u0016\u0019B!\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001a"},
   d2 = {"Lkotlin/text/HexFormat;", "", "", "upperCase", "Lkotlin/text/HexFormat$BytesHexFormat;", "bytes", "Lkotlin/text/HexFormat$NumberHexFormat;", "number", "<init>", "(ZLkotlin/text/HexFormat$BytesHexFormat;Lkotlin/text/HexFormat$NumberHexFormat;)V", "", "toString", "()Ljava/lang/String;", "Lkotlin/text/HexFormat$BytesHexFormat;", "getBytes", "()Lkotlin/text/HexFormat$BytesHexFormat;", "Lkotlin/text/HexFormat$NumberHexFormat;", "getNumber", "()Lkotlin/text/HexFormat$NumberHexFormat;", "Z", "getUpperCase", "()Z", "Companion", "Builder", "BytesHexFormat", "NumberHexFormat", "kotlin-stdlib"}
)
@ExperimentalStdlibApi
@SinceKotlin(
   version = "1.9"
)
public final class HexFormat {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private final boolean upperCase;
   @NotNull
   private final BytesHexFormat bytes;
   @NotNull
   private final NumberHexFormat number;
   @NotNull
   private static final HexFormat Default;
   @NotNull
   private static final HexFormat UpperCase;

   public HexFormat(boolean upperCase, @NotNull BytesHexFormat bytes, @NotNull NumberHexFormat number) {
      Intrinsics.checkNotNullParameter(bytes, "bytes");
      Intrinsics.checkNotNullParameter(number, "number");
      super();
      this.upperCase = upperCase;
      this.bytes = bytes;
      this.number = number;
   }

   public final boolean getUpperCase() {
      return this.upperCase;
   }

   @NotNull
   public final BytesHexFormat getBytes() {
      return this.bytes;
   }

   @NotNull
   public final NumberHexFormat getNumber() {
      return this.number;
   }

   @NotNull
   public String toString() {
      StringBuilder $this$toString_u24lambda_u240 = new StringBuilder();
      int var3 = 0;
      StringBuilder var10000 = $this$toString_u24lambda_u240.append("HexFormat(");
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      Intrinsics.checkNotNullExpressionValue(var10000.append('\n'), "append(...)");
      var10000 = $this$toString_u24lambda_u240.append("    upperCase = ").append(this.upperCase);
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      StringBuilder var4 = var10000;
      var10000 = var4.append(",");
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      Intrinsics.checkNotNullExpressionValue(var10000.append('\n'), "append(...)");
      var10000 = $this$toString_u24lambda_u240.append("    bytes = BytesHexFormat(");
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      Intrinsics.checkNotNullExpressionValue(var10000.append('\n'), "append(...)");
      Intrinsics.checkNotNullExpressionValue(this.bytes.appendOptionsTo$kotlin_stdlib($this$toString_u24lambda_u240, "        ").append('\n'), "append(...)");
      var10000 = $this$toString_u24lambda_u240.append("    ),");
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      Intrinsics.checkNotNullExpressionValue(var10000.append('\n'), "append(...)");
      var10000 = $this$toString_u24lambda_u240.append("    number = NumberHexFormat(");
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      Intrinsics.checkNotNullExpressionValue(var10000.append('\n'), "append(...)");
      Intrinsics.checkNotNullExpressionValue(this.number.appendOptionsTo$kotlin_stdlib($this$toString_u24lambda_u240, "        ").append('\n'), "append(...)");
      var10000 = $this$toString_u24lambda_u240.append("    )");
      Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
      Intrinsics.checkNotNullExpressionValue(var10000.append('\n'), "append(...)");
      $this$toString_u24lambda_u240.append(")");
      String var11 = $this$toString_u24lambda_u240.toString();
      Intrinsics.checkNotNullExpressionValue(var11, "toString(...)");
      return var11;
   }

   static {
      Default = new HexFormat(false, HexFormat.BytesHexFormat.Companion.getDefault$kotlin_stdlib(), HexFormat.NumberHexFormat.Companion.getDefault$kotlin_stdlib());
      UpperCase = new HexFormat(true, HexFormat.BytesHexFormat.Companion.getDefault$kotlin_stdlib(), HexFormat.NumberHexFormat.Companion.getDefault$kotlin_stdlib());
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u0000 '2\u00020\u0001:\u0002('B9\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0004\b\n\u0010\u000bJ'\u0010\u0012\u001a\u00060\fj\u0002`\r2\n\u0010\u000e\u001a\u00060\fj\u0002`\r2\u0006\u0010\u000f\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\b\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\b\u0010\u0015\u001a\u0004\b\u0016\u0010\u0014R\u0017\u0010\u0007\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0015\u001a\u0004\b\u0017\u0010\u0014R\u0017\u0010\t\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\t\u0010\u0015\u001a\u0004\b\u0018\u0010\u0014R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0019\u001a\u0004\b\u001c\u0010\u001bR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0015\u001a\u0004\b\u001d\u0010\u0014R\u001a\u0010\u001f\u001a\u00020\u001e8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\u001e8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b#\u0010 \u001a\u0004\b$\u0010\"R\u001a\u0010%\u001a\u00020\u001e8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b%\u0010 \u001a\u0004\b&\u0010\"¨\u0006)"},
      d2 = {"Lkotlin/text/HexFormat$BytesHexFormat;", "", "", "bytesPerLine", "bytesPerGroup", "", "groupSeparator", "byteSeparator", "bytePrefix", "byteSuffix", "<init>", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "sb", "indent", "appendOptionsTo$kotlin_stdlib", "(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;", "appendOptionsTo", "toString", "()Ljava/lang/String;", "Ljava/lang/String;", "getBytePrefix", "getByteSeparator", "getByteSuffix", "I", "getBytesPerGroup", "()I", "getBytesPerLine", "getGroupSeparator", "", "ignoreCase", "Z", "getIgnoreCase$kotlin_stdlib", "()Z", "noLineAndGroupSeparator", "getNoLineAndGroupSeparator$kotlin_stdlib", "shortByteSeparatorNoPrefixAndSuffix", "getShortByteSeparatorNoPrefixAndSuffix$kotlin_stdlib", "Companion", "Builder", "kotlin-stdlib"}
   )
   public static final class BytesHexFormat {
      @NotNull
      public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
      private final int bytesPerLine;
      private final int bytesPerGroup;
      @NotNull
      private final String groupSeparator;
      @NotNull
      private final String byteSeparator;
      @NotNull
      private final String bytePrefix;
      @NotNull
      private final String byteSuffix;
      private final boolean noLineAndGroupSeparator;
      private final boolean shortByteSeparatorNoPrefixAndSuffix;
      private final boolean ignoreCase;
      @NotNull
      private static final BytesHexFormat Default = new BytesHexFormat(Integer.MAX_VALUE, Integer.MAX_VALUE, "  ", "", "", "");

      public BytesHexFormat(int bytesPerLine, int bytesPerGroup, @NotNull String groupSeparator, @NotNull String byteSeparator, @NotNull String bytePrefix, @NotNull String byteSuffix) {
         Intrinsics.checkNotNullParameter(groupSeparator, "groupSeparator");
         Intrinsics.checkNotNullParameter(byteSeparator, "byteSeparator");
         Intrinsics.checkNotNullParameter(bytePrefix, "bytePrefix");
         Intrinsics.checkNotNullParameter(byteSuffix, "byteSuffix");
         super();
         this.bytesPerLine = bytesPerLine;
         this.bytesPerGroup = bytesPerGroup;
         this.groupSeparator = groupSeparator;
         this.byteSeparator = byteSeparator;
         this.bytePrefix = bytePrefix;
         this.byteSuffix = byteSuffix;
         this.noLineAndGroupSeparator = this.bytesPerLine == Integer.MAX_VALUE && this.bytesPerGroup == Integer.MAX_VALUE;
         this.shortByteSeparatorNoPrefixAndSuffix = ((CharSequence)this.bytePrefix).length() == 0 && ((CharSequence)this.byteSuffix).length() == 0 && this.byteSeparator.length() <= 1;
         this.ignoreCase = HexFormatKt.access$isCaseSensitive(this.groupSeparator) || HexFormatKt.access$isCaseSensitive(this.byteSeparator) || HexFormatKt.access$isCaseSensitive(this.bytePrefix) || HexFormatKt.access$isCaseSensitive(this.byteSuffix);
      }

      public final int getBytesPerLine() {
         return this.bytesPerLine;
      }

      public final int getBytesPerGroup() {
         return this.bytesPerGroup;
      }

      @NotNull
      public final String getGroupSeparator() {
         return this.groupSeparator;
      }

      @NotNull
      public final String getByteSeparator() {
         return this.byteSeparator;
      }

      @NotNull
      public final String getBytePrefix() {
         return this.bytePrefix;
      }

      @NotNull
      public final String getByteSuffix() {
         return this.byteSuffix;
      }

      public final boolean getNoLineAndGroupSeparator$kotlin_stdlib() {
         return this.noLineAndGroupSeparator;
      }

      public final boolean getShortByteSeparatorNoPrefixAndSuffix$kotlin_stdlib() {
         return this.shortByteSeparatorNoPrefixAndSuffix;
      }

      public final boolean getIgnoreCase$kotlin_stdlib() {
         return this.ignoreCase;
      }

      @NotNull
      public String toString() {
         StringBuilder $this$toString_u24lambda_u240 = new StringBuilder();
         int var3 = 0;
         StringBuilder var10000 = $this$toString_u24lambda_u240.append("BytesHexFormat(");
         Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
         Intrinsics.checkNotNullExpressionValue(var10000.append('\n'), "append(...)");
         Intrinsics.checkNotNullExpressionValue(this.appendOptionsTo$kotlin_stdlib($this$toString_u24lambda_u240, "    ").append('\n'), "append(...)");
         $this$toString_u24lambda_u240.append(")");
         String var4 = $this$toString_u24lambda_u240.toString();
         Intrinsics.checkNotNullExpressionValue(var4, "toString(...)");
         return var4;
      }

      @NotNull
      public final StringBuilder appendOptionsTo$kotlin_stdlib(@NotNull StringBuilder sb, @NotNull String indent) {
         Intrinsics.checkNotNullParameter(sb, "sb");
         Intrinsics.checkNotNullParameter(indent, "indent");
         StringBuilder var10000 = sb.append(indent).append("bytesPerLine = ").append(this.bytesPerLine);
         Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
         StringBuilder var3 = var10000;
         var10000 = var3.append(",");
         Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
         Intrinsics.checkNotNullExpressionValue(var10000.append('\n'), "append(...)");
         var10000 = sb.append(indent).append("bytesPerGroup = ").append(this.bytesPerGroup);
         Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
         var3 = var10000;
         var10000 = var3.append(",");
         Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
         Intrinsics.checkNotNullExpressionValue(var10000.append('\n'), "append(...)");
         var10000 = sb.append(indent).append("groupSeparator = \"").append(this.groupSeparator);
         Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
         var3 = var10000;
         var10000 = var3.append("\",");
         Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
         Intrinsics.checkNotNullExpressionValue(var10000.append('\n'), "append(...)");
         var10000 = sb.append(indent).append("byteSeparator = \"").append(this.byteSeparator);
         Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
         var3 = var10000;
         var10000 = var3.append("\",");
         Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
         Intrinsics.checkNotNullExpressionValue(var10000.append('\n'), "append(...)");
         var10000 = sb.append(indent).append("bytePrefix = \"").append(this.bytePrefix);
         Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
         var3 = var10000;
         var10000 = var3.append("\",");
         Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
         Intrinsics.checkNotNullExpressionValue(var10000.append('\n'), "append(...)");
         sb.append(indent).append("byteSuffix = \"").append(this.byteSuffix).append("\"");
         return sb;
      }

      @Metadata(
         mv = {1, 9, 0},
         k = 1,
         xi = 48,
         d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\r\u0018\u00002\u00020\u0001B\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0007\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0005\u0010\u0006R*\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR*\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR*\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u000b\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR*\u0010\u0017\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\u00168\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR*\u0010\u001d\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\u00168\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u0018\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001cR\"\u0010 \u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b \u0010\u000b\u001a\u0004\b!\u0010\r\"\u0004\b\"\u0010\u000f¨\u0006#"},
         d2 = {"Lkotlin/text/HexFormat$BytesHexFormat$Builder;", "", "<init>", "()V", "Lkotlin/text/HexFormat$BytesHexFormat;", "build$kotlin_stdlib", "()Lkotlin/text/HexFormat$BytesHexFormat;", "build", "", "value", "bytePrefix", "Ljava/lang/String;", "getBytePrefix", "()Ljava/lang/String;", "setBytePrefix", "(Ljava/lang/String;)V", "byteSeparator", "getByteSeparator", "setByteSeparator", "byteSuffix", "getByteSuffix", "setByteSuffix", "", "bytesPerGroup", "I", "getBytesPerGroup", "()I", "setBytesPerGroup", "(I)V", "bytesPerLine", "getBytesPerLine", "setBytesPerLine", "groupSeparator", "getGroupSeparator", "setGroupSeparator", "kotlin-stdlib"}
      )
      public static final class Builder {
         private int bytesPerLine;
         private int bytesPerGroup;
         @NotNull
         private String groupSeparator;
         @NotNull
         private String byteSeparator;
         @NotNull
         private String bytePrefix;
         @NotNull
         private String byteSuffix;

         public Builder() {
            this.bytesPerLine = HexFormat.BytesHexFormat.Companion.getDefault$kotlin_stdlib().getBytesPerLine();
            this.bytesPerGroup = HexFormat.BytesHexFormat.Companion.getDefault$kotlin_stdlib().getBytesPerGroup();
            this.groupSeparator = HexFormat.BytesHexFormat.Companion.getDefault$kotlin_stdlib().getGroupSeparator();
            this.byteSeparator = HexFormat.BytesHexFormat.Companion.getDefault$kotlin_stdlib().getByteSeparator();
            this.bytePrefix = HexFormat.BytesHexFormat.Companion.getDefault$kotlin_stdlib().getBytePrefix();
            this.byteSuffix = HexFormat.BytesHexFormat.Companion.getDefault$kotlin_stdlib().getByteSuffix();
         }

         public final int getBytesPerLine() {
            return this.bytesPerLine;
         }

         public final void setBytesPerLine(int value) {
            if (value <= 0) {
               throw new IllegalArgumentException("Non-positive values are prohibited for bytesPerLine, but was " + value);
            } else {
               this.bytesPerLine = value;
            }
         }

         public final int getBytesPerGroup() {
            return this.bytesPerGroup;
         }

         public final void setBytesPerGroup(int value) {
            if (value <= 0) {
               throw new IllegalArgumentException("Non-positive values are prohibited for bytesPerGroup, but was " + value);
            } else {
               this.bytesPerGroup = value;
            }
         }

         @NotNull
         public final String getGroupSeparator() {
            return this.groupSeparator;
         }

         public final void setGroupSeparator(@NotNull String <set-?>) {
            Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
            this.groupSeparator = <set-?>;
         }

         @NotNull
         public final String getByteSeparator() {
            return this.byteSeparator;
         }

         public final void setByteSeparator(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            if (!StringsKt.contains$default((CharSequence)value, '\n', false, 2, (Object)null) && !StringsKt.contains$default((CharSequence)value, '\r', false, 2, (Object)null)) {
               this.byteSeparator = value;
            } else {
               throw new IllegalArgumentException("LF and CR characters are prohibited in byteSeparator, but was " + value);
            }
         }

         @NotNull
         public final String getBytePrefix() {
            return this.bytePrefix;
         }

         public final void setBytePrefix(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            if (!StringsKt.contains$default((CharSequence)value, '\n', false, 2, (Object)null) && !StringsKt.contains$default((CharSequence)value, '\r', false, 2, (Object)null)) {
               this.bytePrefix = value;
            } else {
               throw new IllegalArgumentException("LF and CR characters are prohibited in bytePrefix, but was " + value);
            }
         }

         @NotNull
         public final String getByteSuffix() {
            return this.byteSuffix;
         }

         public final void setByteSuffix(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            if (!StringsKt.contains$default((CharSequence)value, '\n', false, 2, (Object)null) && !StringsKt.contains$default((CharSequence)value, '\r', false, 2, (Object)null)) {
               this.byteSuffix = value;
            } else {
               throw new IllegalArgumentException("LF and CR characters are prohibited in byteSuffix, but was " + value);
            }
         }

         @NotNull
         public final BytesHexFormat build$kotlin_stdlib() {
            return new BytesHexFormat(this.bytesPerLine, this.bytesPerGroup, this.groupSeparator, this.byteSeparator, this.bytePrefix, this.byteSuffix);
         }
      }

      @Metadata(
         mv = {1, 9, 0},
         k = 1,
         xi = 48,
         d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"},
         d2 = {"Lkotlin/text/HexFormat$BytesHexFormat$Companion;", "", "<init>", "()V", "Lkotlin/text/HexFormat$BytesHexFormat;", "Default", "Lkotlin/text/HexFormat$BytesHexFormat;", "getDefault$kotlin_stdlib", "()Lkotlin/text/HexFormat$BytesHexFormat;", "kotlin-stdlib"}
      )
      public static final class Companion {
         private Companion() {
         }

         @NotNull
         public final BytesHexFormat getDefault$kotlin_stdlib() {
            return HexFormat.BytesHexFormat.Default;
         }

         // $FF: synthetic method
         public Companion(DefaultConstructorMarker $constructor_marker) {
            this();
         }
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001d\u001cB!\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ'\u0010\u000f\u001a\u00060\tj\u0002`\n2\n\u0010\u000b\u001a\u00060\tj\u0002`\n2\u0006\u0010\f\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u00058\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u00058\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\u0011R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0013\u001a\u0004\b\u001a\u0010\u0015R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u001b\u0010\u0011¨\u0006\u001e"},
      d2 = {"Lkotlin/text/HexFormat$NumberHexFormat;", "", "", "prefix", "suffix", "", "removeLeadingZeros", "<init>", "(Ljava/lang/String;Ljava/lang/String;Z)V", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "sb", "indent", "appendOptionsTo$kotlin_stdlib", "(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;", "appendOptionsTo", "toString", "()Ljava/lang/String;", "ignoreCase", "Z", "getIgnoreCase$kotlin_stdlib", "()Z", "isDigitsOnly", "isDigitsOnly$kotlin_stdlib", "Ljava/lang/String;", "getPrefix", "getRemoveLeadingZeros", "getSuffix", "Companion", "Builder", "kotlin-stdlib"}
   )
   public static final class NumberHexFormat {
      @NotNull
      public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
      @NotNull
      private final String prefix;
      @NotNull
      private final String suffix;
      private final boolean removeLeadingZeros;
      private final boolean isDigitsOnly;
      private final boolean ignoreCase;
      @NotNull
      private static final NumberHexFormat Default = new NumberHexFormat("", "", false);

      public NumberHexFormat(@NotNull String prefix, @NotNull String suffix, boolean removeLeadingZeros) {
         Intrinsics.checkNotNullParameter(prefix, "prefix");
         Intrinsics.checkNotNullParameter(suffix, "suffix");
         super();
         this.prefix = prefix;
         this.suffix = suffix;
         this.removeLeadingZeros = removeLeadingZeros;
         this.isDigitsOnly = ((CharSequence)this.prefix).length() == 0 && ((CharSequence)this.suffix).length() == 0;
         this.ignoreCase = HexFormatKt.access$isCaseSensitive(this.prefix) || HexFormatKt.access$isCaseSensitive(this.suffix);
      }

      @NotNull
      public final String getPrefix() {
         return this.prefix;
      }

      @NotNull
      public final String getSuffix() {
         return this.suffix;
      }

      public final boolean getRemoveLeadingZeros() {
         return this.removeLeadingZeros;
      }

      public final boolean isDigitsOnly$kotlin_stdlib() {
         return this.isDigitsOnly;
      }

      public final boolean getIgnoreCase$kotlin_stdlib() {
         return this.ignoreCase;
      }

      @NotNull
      public String toString() {
         StringBuilder $this$toString_u24lambda_u240 = new StringBuilder();
         int var3 = 0;
         StringBuilder var10000 = $this$toString_u24lambda_u240.append("NumberHexFormat(");
         Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
         Intrinsics.checkNotNullExpressionValue(var10000.append('\n'), "append(...)");
         Intrinsics.checkNotNullExpressionValue(this.appendOptionsTo$kotlin_stdlib($this$toString_u24lambda_u240, "    ").append('\n'), "append(...)");
         $this$toString_u24lambda_u240.append(")");
         String var4 = $this$toString_u24lambda_u240.toString();
         Intrinsics.checkNotNullExpressionValue(var4, "toString(...)");
         return var4;
      }

      @NotNull
      public final StringBuilder appendOptionsTo$kotlin_stdlib(@NotNull StringBuilder sb, @NotNull String indent) {
         Intrinsics.checkNotNullParameter(sb, "sb");
         Intrinsics.checkNotNullParameter(indent, "indent");
         StringBuilder var10000 = sb.append(indent).append("prefix = \"").append(this.prefix);
         Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
         StringBuilder var3 = var10000;
         var10000 = var3.append("\",");
         Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
         Intrinsics.checkNotNullExpressionValue(var10000.append('\n'), "append(...)");
         var10000 = sb.append(indent).append("suffix = \"").append(this.suffix);
         Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
         var3 = var10000;
         var10000 = var3.append("\",");
         Intrinsics.checkNotNullExpressionValue(var10000, "append(...)");
         Intrinsics.checkNotNullExpressionValue(var10000.append('\n'), "append(...)");
         sb.append(indent).append("removeLeadingZeros = ").append(this.removeLeadingZeros);
         return sb;
      }

      @Metadata(
         mv = {1, 9, 0},
         k = 1,
         xi = 48,
         d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u00002\u00020\u0001B\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0007\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0005\u0010\u0006R*\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0011\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R*\u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u000b\u001a\u0004\b\u0018\u0010\r\"\u0004\b\u0019\u0010\u000f¨\u0006\u001a"},
         d2 = {"Lkotlin/text/HexFormat$NumberHexFormat$Builder;", "", "<init>", "()V", "Lkotlin/text/HexFormat$NumberHexFormat;", "build$kotlin_stdlib", "()Lkotlin/text/HexFormat$NumberHexFormat;", "build", "", "value", "prefix", "Ljava/lang/String;", "getPrefix", "()Ljava/lang/String;", "setPrefix", "(Ljava/lang/String;)V", "", "removeLeadingZeros", "Z", "getRemoveLeadingZeros", "()Z", "setRemoveLeadingZeros", "(Z)V", "suffix", "getSuffix", "setSuffix", "kotlin-stdlib"}
      )
      public static final class Builder {
         @NotNull
         private String prefix;
         @NotNull
         private String suffix;
         private boolean removeLeadingZeros;

         public Builder() {
            this.prefix = HexFormat.NumberHexFormat.Companion.getDefault$kotlin_stdlib().getPrefix();
            this.suffix = HexFormat.NumberHexFormat.Companion.getDefault$kotlin_stdlib().getSuffix();
            this.removeLeadingZeros = HexFormat.NumberHexFormat.Companion.getDefault$kotlin_stdlib().getRemoveLeadingZeros();
         }

         @NotNull
         public final String getPrefix() {
            return this.prefix;
         }

         public final void setPrefix(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            if (!StringsKt.contains$default((CharSequence)value, '\n', false, 2, (Object)null) && !StringsKt.contains$default((CharSequence)value, '\r', false, 2, (Object)null)) {
               this.prefix = value;
            } else {
               throw new IllegalArgumentException("LF and CR characters are prohibited in prefix, but was " + value);
            }
         }

         @NotNull
         public final String getSuffix() {
            return this.suffix;
         }

         public final void setSuffix(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            if (!StringsKt.contains$default((CharSequence)value, '\n', false, 2, (Object)null) && !StringsKt.contains$default((CharSequence)value, '\r', false, 2, (Object)null)) {
               this.suffix = value;
            } else {
               throw new IllegalArgumentException("LF and CR characters are prohibited in suffix, but was " + value);
            }
         }

         public final boolean getRemoveLeadingZeros() {
            return this.removeLeadingZeros;
         }

         public final void setRemoveLeadingZeros(boolean <set-?>) {
            this.removeLeadingZeros = <set-?>;
         }

         @NotNull
         public final NumberHexFormat build$kotlin_stdlib() {
            return new NumberHexFormat(this.prefix, this.suffix, this.removeLeadingZeros);
         }
      }

      @Metadata(
         mv = {1, 9, 0},
         k = 1,
         xi = 48,
         d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"},
         d2 = {"Lkotlin/text/HexFormat$NumberHexFormat$Companion;", "", "<init>", "()V", "Lkotlin/text/HexFormat$NumberHexFormat;", "Default", "Lkotlin/text/HexFormat$NumberHexFormat;", "getDefault$kotlin_stdlib", "()Lkotlin/text/HexFormat$NumberHexFormat;", "kotlin-stdlib"}
      )
      public static final class Companion {
         private Companion() {
         }

         @NotNull
         public final NumberHexFormat getDefault$kotlin_stdlib() {
            return HexFormat.NumberHexFormat.Default;
         }

         // $FF: synthetic method
         public Companion(DefaultConstructorMarker $constructor_marker) {
            this();
         }
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0001¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\u0005\u0010\u0006J,\u0010\f\u001a\u00020\t2\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\nH\u0087\bø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ,\u0010\u000f\u001a\u00020\t2\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\nH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\rR\u0018\u0010\u0010\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0011\u0010\f\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000f\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\"\u0010\u0019\u001a\u00020\u00188\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001f"},
      d2 = {"Lkotlin/text/HexFormat$Builder;", "", "<init>", "()V", "Lkotlin/text/HexFormat;", "build", "()Lkotlin/text/HexFormat;", "Lkotlin/Function1;", "Lkotlin/text/HexFormat$BytesHexFormat$Builder;", "", "Lkotlin/ExtensionFunctionType;", "builderAction", "bytes", "(Lkotlin/jvm/functions/Function1;)V", "Lkotlin/text/HexFormat$NumberHexFormat$Builder;", "number", "_bytes", "Lkotlin/text/HexFormat$BytesHexFormat$Builder;", "_number", "Lkotlin/text/HexFormat$NumberHexFormat$Builder;", "getBytes", "()Lkotlin/text/HexFormat$BytesHexFormat$Builder;", "getNumber", "()Lkotlin/text/HexFormat$NumberHexFormat$Builder;", "", "upperCase", "Z", "getUpperCase", "()Z", "setUpperCase", "(Z)V", "kotlin-stdlib"}
   )
   public static final class Builder {
      private boolean upperCase;
      @Nullable
      private BytesHexFormat.Builder _bytes;
      @Nullable
      private NumberHexFormat.Builder _number;

      @PublishedApi
      public Builder() {
         this.upperCase = HexFormat.Companion.getDefault().getUpperCase();
      }

      public final boolean getUpperCase() {
         return this.upperCase;
      }

      public final void setUpperCase(boolean <set-?>) {
         this.upperCase = <set-?>;
      }

      @NotNull
      public final BytesHexFormat.Builder getBytes() {
         if (this._bytes == null) {
            this._bytes = new BytesHexFormat.Builder();
         }

         BytesHexFormat.Builder var10000 = this._bytes;
         Intrinsics.checkNotNull(var10000);
         return var10000;
      }

      @NotNull
      public final NumberHexFormat.Builder getNumber() {
         if (this._number == null) {
            this._number = new NumberHexFormat.Builder();
         }

         NumberHexFormat.Builder var10000 = this._number;
         Intrinsics.checkNotNull(var10000);
         return var10000;
      }

      @InlineOnly
      private final void bytes(Function1 builderAction) {
         Intrinsics.checkNotNullParameter(builderAction, "builderAction");
         builderAction.invoke(this.getBytes());
      }

      @InlineOnly
      private final void number(Function1 builderAction) {
         Intrinsics.checkNotNullParameter(builderAction, "builderAction");
         builderAction.invoke(this.getNumber());
      }

      @PublishedApi
      @NotNull
      public final HexFormat build() {
         BytesHexFormat var1;
         HexFormat var10000;
         boolean var10002;
         label19: {
            var10000 = new HexFormat;
            var10002 = this.upperCase;
            BytesHexFormat.Builder var10003 = this._bytes;
            if (var10003 != null) {
               var1 = var10003.build$kotlin_stdlib();
               if (var1 != null) {
                  break label19;
               }
            }

            var1 = HexFormat.BytesHexFormat.Companion.getDefault$kotlin_stdlib();
         }

         NumberHexFormat var2;
         label14: {
            NumberHexFormat.Builder var10004 = this._number;
            if (var10004 != null) {
               var2 = var10004.build$kotlin_stdlib();
               if (var2 != null) {
                  break label14;
               }
            }

            var2 = HexFormat.NumberHexFormat.Companion.getDefault$kotlin_stdlib();
         }

         var10000.<init>(var10002, var1, var2);
         return var10000;
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\b¨\u0006\u000b"},
      d2 = {"Lkotlin/text/HexFormat$Companion;", "", "<init>", "()V", "Lkotlin/text/HexFormat;", "Default", "Lkotlin/text/HexFormat;", "getDefault", "()Lkotlin/text/HexFormat;", "UpperCase", "getUpperCase", "kotlin-stdlib"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final HexFormat getDefault() {
         return HexFormat.Default;
      }

      @NotNull
      public final HexFormat getUpperCase() {
         return HexFormat.UpperCase;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
