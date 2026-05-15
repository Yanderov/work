package kotlin.coroutines.jvm.internal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u00000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001f\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007*\u00020\u0006H\u0002¢\u0006\u0004\b\b\u0010\t\u001a\u0013\u0010\n\u001a\u00020\u0000*\u00020\u0006H\u0002¢\u0006\u0004\b\n\u0010\u000b\u001a\u001b\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f*\u00020\u0006H\u0001¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u0010*\u00020\u0006H\u0001¢\u0006\u0004\b\u0011\u0010\u0012\"\u0014\u0010\u0014\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0016"},
   d2 = {"", "expected", "actual", "", "checkDebugMetadataVersion", "(II)V", "Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "Lkotlin/coroutines/jvm/internal/DebugMetadata;", "getDebugMetadataAnnotation", "(Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;)Lkotlin/coroutines/jvm/internal/DebugMetadata;", "getLabel", "(Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;)I", "", "", "getSpilledVariableFieldMapping", "(Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;)[Ljava/lang/String;", "Ljava/lang/StackTraceElement;", "getStackTraceElement", "(Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;)Ljava/lang/StackTraceElement;", "getStackTraceElementImpl", "COROUTINES_DEBUG_METADATA_VERSION", "I", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nDebugMetadata.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DebugMetadata.kt\nkotlin/coroutines/jvm/internal/DebugMetadataKt\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,134:1\n37#2,2:135\n*S KotlinDebug\n*F\n+ 1 DebugMetadata.kt\nkotlin/coroutines/jvm/internal/DebugMetadataKt\n*L\n131#1:135,2\n*E\n"})
public final class DebugMetadataKt {
   private static final int COROUTINES_DEBUG_METADATA_VERSION = 1;

   @SinceKotlin(
      version = "1.3"
   )
   @JvmName(
      name = "getStackTraceElement"
   )
   @Nullable
   public static final StackTraceElement getStackTraceElement(@NotNull BaseContinuationImpl $this$getStackTraceElementImpl) {
      Intrinsics.checkNotNullParameter($this$getStackTraceElementImpl, "<this>");
      DebugMetadata var10000 = getDebugMetadataAnnotation($this$getStackTraceElementImpl);
      if (var10000 == null) {
         return null;
      } else {
         DebugMetadata debugMetadata = var10000;
         checkDebugMetadataVersion(1, debugMetadata.v());
         int label = getLabel($this$getStackTraceElementImpl);
         int lineNumber = label < 0 ? -1 : debugMetadata.l()[label];
         String moduleName = ModuleNameRetriever.INSTANCE.getModuleName($this$getStackTraceElementImpl);
         String moduleAndClass = moduleName == null ? debugMetadata.c() : moduleName + '/' + debugMetadata.c();
         return new StackTraceElement(moduleAndClass, debugMetadata.m(), debugMetadata.f(), lineNumber);
      }
   }

   private static final DebugMetadata getDebugMetadataAnnotation(BaseContinuationImpl $this$getDebugMetadataAnnotation) {
      return (DebugMetadata)$this$getDebugMetadataAnnotation.getClass().getAnnotation(DebugMetadata.class);
   }

   private static final int getLabel(BaseContinuationImpl $this$getLabel) {
      int field;
      try {
         Field field = $this$getLabel.getClass().getDeclaredField("label");
         field.setAccessible(true);
         Object var3 = field.get($this$getLabel);
         field = ((var3 instanceof Integer ? (Integer)var3 : null) != null ? var3 instanceof Integer ? (Integer)var3 : null : 0) - 1;
      } catch (Exception var4) {
         field = -1;
      }

      return field;
   }

   private static final void checkDebugMetadataVersion(int expected, int actual) {
      if (actual > expected) {
         throw new IllegalStateException(("Debug metadata version mismatch. Expected: " + expected + ", got " + actual + ". Please update the Kotlin standard library.").toString());
      }
   }

   @SinceKotlin(
      version = "1.3"
   )
   @JvmName(
      name = "getSpilledVariableFieldMapping"
   )
   @Nullable
   public static final String[] getSpilledVariableFieldMapping(@NotNull BaseContinuationImpl $this$getSpilledVariableFieldMapping) {
      Intrinsics.checkNotNullParameter($this$getSpilledVariableFieldMapping, "<this>");
      DebugMetadata var10000 = getDebugMetadataAnnotation($this$getSpilledVariableFieldMapping);
      if (var10000 == null) {
         return null;
      } else {
         DebugMetadata debugMetadata = var10000;
         checkDebugMetadataVersion(1, debugMetadata.v());
         ArrayList res = new ArrayList();
         int label = getLabel($this$getSpilledVariableFieldMapping);
         int[] thisCollection$iv = debugMetadata.i();
         int $i$f$toTypedArray = 0;

         for(int var6 = thisCollection$iv.length; $i$f$toTypedArray < var6; ++$i$f$toTypedArray) {
            int labelOfIndex = thisCollection$iv[$i$f$toTypedArray];
            if (labelOfIndex == label) {
               res.add(debugMetadata.s()[$i$f$toTypedArray]);
               res.add(debugMetadata.n()[$i$f$toTypedArray]);
            }
         }

         Collection $this$toTypedArray$iv = (Collection)res;
         $i$f$toTypedArray = 0;
         return (String[])$this$toTypedArray$iv.toArray(new String[0]);
      }
   }
}
