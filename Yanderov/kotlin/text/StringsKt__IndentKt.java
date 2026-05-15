package kotlin.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.internal.IntrinsicConstEvaluation;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000\u001e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u000f\u001a#\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0013\u0010\t\u001a\u00020\u0006*\u00020\u0000H\u0002¢\u0006\u0004\b\u0007\u0010\b\u001a\u001b\u0010\n\u001a\u00020\u0000*\u00020\u00002\b\b\u0002\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\n\u0010\u000b\u001aL\u0010\u0012\u001a\u00020\u0000*\b\u0012\u0004\u0012\u00020\u00000\f2\u0006\u0010\r\u001a\u00020\u00062\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u00022\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u0002H\u0082\b¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u001b\u0010\u0014\u001a\u00020\u0000*\u00020\u00002\b\b\u0002\u0010\u0013\u001a\u00020\u0000¢\u0006\u0004\b\u0014\u0010\u000b\u001a%\u0010\u0016\u001a\u00020\u0000*\u00020\u00002\b\b\u0002\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0015\u001a\u00020\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0013\u0010\u0018\u001a\u00020\u0000*\u00020\u0000H\u0007¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u001d\u0010\u001a\u001a\u00020\u0000*\u00020\u00002\b\b\u0002\u0010\u0015\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u001a\u0010\u000b¨\u0006\u001b"},
   d2 = {"", "indent", "Lkotlin/Function1;", "getIndentFunction$StringsKt__IndentKt", "(Ljava/lang/String;)Lkotlin/jvm/functions/Function1;", "getIndentFunction", "", "indentWidth$StringsKt__IndentKt", "(Ljava/lang/String;)I", "indentWidth", "prependIndent", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "", "resultSizeEstimate", "indentAddFunction", "indentCutFunction", "reindent$StringsKt__IndentKt", "(Ljava/util/List;ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/String;", "reindent", "newIndent", "replaceIndent", "marginPrefix", "replaceIndentByMargin", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "trimIndent", "(Ljava/lang/String;)Ljava/lang/String;", "trimMargin", "kotlin-stdlib"},
   xs = "kotlin/text/StringsKt"
)
@SourceDebugExtension({"SMAP\nIndent.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Indent.kt\nkotlin/text/StringsKt__IndentKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,123:1\n113#1,2:125\n115#1,4:140\n120#1,2:153\n113#1,2:162\n115#1,4:177\n120#1,2:184\n1#2:124\n1#2:150\n1#2:181\n1#2:205\n1577#3,11:127\n1872#3,2:138\n1874#3:151\n1588#3:152\n774#3:155\n865#3,2:156\n1557#3:158\n1628#3,3:159\n1577#3,11:164\n1872#3,2:175\n1874#3:182\n1588#3:183\n1577#3,11:192\n1872#3,2:203\n1874#3:206\n1588#3:207\n158#4,6:144\n158#4,6:186\n*S KotlinDebug\n*F\n+ 1 Indent.kt\nkotlin/text/StringsKt__IndentKt\n*L\n38#1:125,2\n38#1:140,4\n38#1:153,2\n78#1:162,2\n78#1:177,4\n78#1:184,2\n38#1:150\n78#1:181\n114#1:205\n38#1:127,11\n38#1:138,2\n38#1:151\n38#1:152\n74#1:155\n74#1:156,2\n75#1:158\n75#1:159,3\n78#1:164,11\n78#1:175,2\n78#1:182\n78#1:183\n114#1:192,11\n114#1:203,2\n114#1:206\n114#1:207\n39#1:144,6\n101#1:186,6\n*E\n"})
class StringsKt__IndentKt extends StringsKt__AppendableKt {
   @IntrinsicConstEvaluation
   @NotNull
   public static final String trimMargin(@NotNull String $this$trimMargin, @NotNull String marginPrefix) {
      Intrinsics.checkNotNullParameter($this$trimMargin, "<this>");
      Intrinsics.checkNotNullParameter(marginPrefix, "marginPrefix");
      return StringsKt.replaceIndentByMargin($this$trimMargin, "", marginPrefix);
   }

   // $FF: synthetic method
   public static String trimMargin$default(String var0, String var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = "|";
      }

      return StringsKt.trimMargin(var0, var1);
   }

   @NotNull
   public static final String replaceIndentByMargin(@NotNull String $this$replaceIndentByMargin, @NotNull String newIndent, @NotNull String marginPrefix) {
      Intrinsics.checkNotNullParameter($this$replaceIndentByMargin, "<this>");
      Intrinsics.checkNotNullParameter(newIndent, "newIndent");
      Intrinsics.checkNotNullParameter(marginPrefix, "marginPrefix");
      boolean var3 = !StringsKt.isBlank((CharSequence)marginPrefix);
      if (!var3) {
         int var4 = 0;
         String $this$reindent$iv = "marginPrefix must be non-blank string.";
         throw new IllegalArgumentException($this$reindent$iv.toString());
      } else {
         List lines = StringsKt.lines((CharSequence)$this$replaceIndentByMargin);
         int resultSizeEstimate$iv = $this$replaceIndentByMargin.length() + newIndent.length() * lines.size();
         Function1 indentAddFunction$iv = getIndentFunction$StringsKt__IndentKt(newIndent);
         int $i$f$reindent = 0;
         int lastIndex$iv = CollectionsKt.getLastIndex(lines);
         Iterable $this$mapIndexedNotNull$iv$iv = (Iterable)lines;
         int $i$f$mapIndexedNotNull = 0;
         Collection destination$iv$iv$iv = (Collection)(new ArrayList());
         int $i$f$mapIndexedNotNullTo = 0;
         int $i$f$forEachIndexed = 0;
         int index$iv$iv$iv$iv = 0;

         for(Object item$iv$iv$iv$iv : $this$mapIndexedNotNull$iv$iv) {
            int index$iv$iv$iv = index$iv$iv$iv$iv++;
            if (index$iv$iv$iv < 0) {
               CollectionsKt.throwIndexOverflow();
            }

            int var22 = 0;
            String value$iv = (String)item$iv$iv$iv$iv;
            int var25 = 0;
            String var45;
            if ((index$iv$iv$iv == 0 || index$iv$iv$iv == lastIndex$iv) && StringsKt.isBlank((CharSequence)value$iv)) {
               var45 = null;
            } else {
               label86: {
                  int var27 = 0;
                  CharSequence $this$indexOfFirst$iv = (CharSequence)value$iv;
                  int $i$f$indexOfFirst = 0;
                  int index$iv = 0;
                  int var31 = $this$indexOfFirst$iv.length();

                  while(true) {
                     if (index$iv >= var31) {
                        var43 = -1;
                        break;
                     }

                     char it = $this$indexOfFirst$iv.charAt(index$iv);
                     int var33 = 0;
                     if (!CharsKt.isWhitespace(it)) {
                        var43 = index$iv;
                        break;
                     }

                     ++index$iv;
                  }

                  int firstNonWhitespaceIndex = var43;
                  if (firstNonWhitespaceIndex == -1) {
                     var45 = null;
                  } else if (StringsKt.startsWith$default(value$iv, marginPrefix, firstNonWhitespaceIndex, false, 4, (Object)null)) {
                     $i$f$indexOfFirst = firstNonWhitespaceIndex + marginPrefix.length();
                     Intrinsics.checkNotNull(value$iv, "null cannot be cast to non-null type java.lang.String");
                     var45 = value$iv.substring($i$f$indexOfFirst);
                     Intrinsics.checkNotNullExpressionValue(var45, "substring(...)");
                  } else {
                     var45 = null;
                  }

                  if (var45 != null) {
                     String var35 = var45;
                     var45 = (String)indentAddFunction$iv.invoke(var35);
                     if (var45 != null) {
                        break label86;
                     }
                  }

                  var45 = value$iv;
               }
            }

            if (var45 != null) {
               String it$iv$iv$iv = var45;
               int var38 = 0;
               destination$iv$iv$iv.add(it$iv$iv$iv);
            }
         }

         String var39 = ((StringBuilder)CollectionsKt.joinTo$default((Iterable)((List)destination$iv$iv$iv), (Appendable)(new StringBuilder(resultSizeEstimate$iv)), (CharSequence)"\n", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 124, (Object)null)).toString();
         Intrinsics.checkNotNullExpressionValue(var39, "toString(...)");
         return var39;
      }
   }

   // $FF: synthetic method
   public static String replaceIndentByMargin$default(String var0, String var1, String var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = "";
      }

      if ((var3 & 2) != 0) {
         var2 = "|";
      }

      return StringsKt.replaceIndentByMargin(var0, var1, var2);
   }

   @IntrinsicConstEvaluation
   @NotNull
   public static final String trimIndent(@NotNull String $this$trimIndent) {
      Intrinsics.checkNotNullParameter($this$trimIndent, "<this>");
      return StringsKt.replaceIndent($this$trimIndent, "");
   }

   @NotNull
   public static final String replaceIndent(@NotNull String $this$replaceIndent, @NotNull String newIndent) {
      Intrinsics.checkNotNullParameter($this$replaceIndent, "<this>");
      Intrinsics.checkNotNullParameter(newIndent, "newIndent");
      List lines = StringsKt.lines((CharSequence)$this$replaceIndent);
      Iterable $this$filter$iv = (Iterable)lines;
      int $i$f$filter = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList());
      int $i$f$filterTo = 0;

      for(Object element$iv$iv : $this$filter$iv) {
         String p0 = (String)element$iv$iv;
         int var13 = 0;
         if (!StringsKt.isBlank((CharSequence)p0)) {
            destination$iv$iv.add(element$iv$iv);
         }
      }

      $this$filter$iv = (Iterable)((List)destination$iv$iv);
      $i$f$filter = 0;
      destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$filter$iv, 10)));
      $i$f$filterTo = 0;

      for(Object item$iv$iv : $this$filter$iv) {
         String p0 = (String)item$iv$iv;
         int var47 = 0;
         destination$iv$iv.add(indentWidth$StringsKt__IndentKt(p0));
      }

      Integer var10000 = (Integer)CollectionsKt.minOrNull((Iterable)((List)destination$iv$iv));
      int minCommonIndent = var10000 != null ? var10000 : 0;
      int resultSizeEstimate$iv = $this$replaceIndent.length() + newIndent.length() * lines.size();
      Function1 indentAddFunction$iv = getIndentFunction$StringsKt__IndentKt(newIndent);
      int $i$f$reindent = 0;
      int lastIndex$iv = CollectionsKt.getLastIndex(lines);
      Iterable $this$mapIndexedNotNull$iv$iv = (Iterable)lines;
      int $i$f$mapIndexedNotNull = 0;
      Collection destination$iv$iv$iv = (Collection)(new ArrayList());
      int $i$f$mapIndexedNotNullTo = 0;
      int $i$f$forEachIndexed = 0;
      int index$iv$iv$iv$iv = 0;

      for(Object item$iv$iv$iv$iv : $this$mapIndexedNotNull$iv$iv) {
         int index$iv$iv$iv = index$iv$iv$iv$iv++;
         if (index$iv$iv$iv < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         int var22 = 0;
         String value$iv = (String)item$iv$iv$iv$iv;
         int var25 = 0;
         String var51;
         if ((index$iv$iv$iv == 0 || index$iv$iv$iv == lastIndex$iv) && StringsKt.isBlank((CharSequence)value$iv)) {
            var51 = null;
         } else {
            label80: {
               int var27 = 0;
               var51 = StringsKt.drop(value$iv, minCommonIndent);
               if (var51 != null) {
                  String var28 = var51;
                  var51 = (String)indentAddFunction$iv.invoke(var28);
                  if (var51 != null) {
                     break label80;
                  }
               }

               var51 = value$iv;
            }
         }

         if (var51 != null) {
            String it$iv$iv$iv = var51;
            int var31 = 0;
            destination$iv$iv$iv.add(it$iv$iv$iv);
         }
      }

      String var32 = ((StringBuilder)CollectionsKt.joinTo$default((Iterable)((List)destination$iv$iv$iv), (Appendable)(new StringBuilder(resultSizeEstimate$iv)), (CharSequence)"\n", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 124, (Object)null)).toString();
      Intrinsics.checkNotNullExpressionValue(var32, "toString(...)");
      return var32;
   }

   // $FF: synthetic method
   public static String replaceIndent$default(String var0, String var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = "";
      }

      return StringsKt.replaceIndent(var0, var1);
   }

   @NotNull
   public static final String prependIndent(@NotNull String $this$prependIndent, @NotNull final String indent) {
      Intrinsics.checkNotNullParameter($this$prependIndent, "<this>");
      Intrinsics.checkNotNullParameter(indent, "indent");
      return SequencesKt.joinToString$default(SequencesKt.map(StringsKt.lineSequence((CharSequence)$this$prependIndent), new Function1(indent) {
         public final String invoke(String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return StringsKt.isBlank((CharSequence)it) ? (it.length() < indent.length() ? indent : it) : indent + it;
         }
      }), (CharSequence)"\n", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null);
   }

   // $FF: synthetic method
   public static String prependIndent$default(String var0, String var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = "    ";
      }

      return StringsKt.prependIndent(var0, var1);
   }

   private static final int indentWidth$StringsKt__IndentKt(String $this$indentWidth) {
      CharSequence $this$indexOfFirst$iv = (CharSequence)$this$indentWidth;
      int $i$f$indexOfFirst = 0;
      int index$iv = 0;
      int var4 = $this$indexOfFirst$iv.length();

      int var10000;
      while(true) {
         if (index$iv >= var4) {
            var10000 = -1;
            break;
         }

         char it = $this$indexOfFirst$iv.charAt(index$iv);
         int var6 = 0;
         if (!CharsKt.isWhitespace(it)) {
            var10000 = index$iv;
            break;
         }

         ++index$iv;
      }

      int it = var10000;
      index$iv = 0;
      return it == -1 ? $this$indentWidth.length() : it;
   }

   private static final Function1 getIndentFunction$StringsKt__IndentKt(final String indent) {
      return ((CharSequence)indent).length() == 0 ? (Function1)null.INSTANCE : (Function1)(new Function1(indent) {
         public final String invoke(String line) {
            Intrinsics.checkNotNullParameter(line, "line");
            return indent + line;
         }
      });
   }

   private static final String reindent$StringsKt__IndentKt(List $this$reindent, int resultSizeEstimate, Function1 indentAddFunction, Function1 indentCutFunction) {
      int $i$f$reindent = 0;
      int lastIndex = CollectionsKt.getLastIndex($this$reindent);
      Iterable $this$mapIndexedNotNull$iv = (Iterable)$this$reindent;
      int $i$f$mapIndexedNotNull = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList());
      int $i$f$mapIndexedNotNullTo = 0;
      int $i$f$forEachIndexed = 0;
      int index$iv$iv$iv = 0;

      for(Object item$iv$iv$iv : $this$mapIndexedNotNull$iv) {
         int index$iv$iv = index$iv$iv$iv++;
         if (index$iv$iv < 0) {
            if (!PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
               throw new ArithmeticException("Index overflow has happened.");
            }

            CollectionsKt.throwIndexOverflow();
         }

         int var20 = 0;
         String value = (String)item$iv$iv$iv;
         int var23 = 0;
         String var29;
         if ((index$iv$iv == 0 || index$iv$iv == lastIndex) && StringsKt.isBlank((CharSequence)value)) {
            var29 = null;
         } else {
            label48: {
               var29 = (String)indentCutFunction.invoke(value);
               if (var29 != null) {
                  String var24 = var29;
                  var29 = (String)indentAddFunction.invoke(var24);
                  if (var29 != null) {
                     break label48;
                  }
               }

               var29 = value;
            }
         }

         if (var29 != null) {
            String it$iv$iv = var29;
            int var27 = 0;
            destination$iv$iv.add(it$iv$iv);
         }
      }

      String var6 = ((StringBuilder)CollectionsKt.joinTo$default((Iterable)((List)destination$iv$iv), (Appendable)(new StringBuilder(resultSizeEstimate)), (CharSequence)"\n", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 124, (Object)null)).toString();
      Intrinsics.checkNotNullExpressionValue(var6, "toString(...)");
      return var6;
   }

   public StringsKt__IndentKt() {
   }
}
