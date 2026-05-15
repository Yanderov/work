package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u00008\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0001¢\u0006\u0004\b\u0003\u0010\u0004\u001a-\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0087\bø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u0086\u0001\u0010\u0012\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0005\"\u0004\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u00028\u00010\b2!\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00028\u00000\f2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0001\u0012\u0004\u0012\u00028\u00000\fH\u0087\bø\u0001\u0000\u0082\u0002\u0014\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a2\u0010\u0015\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0005\"\b\b\u0001\u0010\u000b*\u00028\u0000*\b\u0012\u0004\u0012\u00028\u00010\b2\u0006\u0010\u0014\u001a\u00028\u0000H\u0087\b¢\u0006\u0004\b\u0015\u0010\u0016\u001a]\u0010\u0017\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0005\"\b\b\u0001\u0010\u000b*\u00028\u0000*\b\u0012\u0004\u0012\u00028\u00010\b2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0001\u0012\u0004\u0012\u00028\u00000\fH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a \u0010\u0019\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u00028\u00000\bH\u0087\b¢\u0006\u0004\b\u0019\u0010\u001a\u001a_\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u0005\"\u0004\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u00028\u00010\b2!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00028\u00000\fH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0004\b\u001c\u0010\u0018\u001aR\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u0005\"\u0004\b\u0001\u0010\u000b*\b\u0012\u0004\u0012\u00028\u00010\b2!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00028\u00000\fH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0018\u001aY\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u00028\u00000\b2!\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0001\u0012\u0004\u0012\u00020\u001e0\fH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0004\b\u0011\u0010\u0018\u001aY\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u00028\u00000\b2!\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u001e0\fH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0004\b\u0010\u0010\u0018\u001ac\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u0005\"\b\b\u0001\u0010\u000b*\u00028\u0000*\b\u0012\u0004\u0012\u00028\u00010\b2!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0001\u0012\u0004\u0012\u00028\u00000\fH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0004\b \u0010\u0018\u001aV\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u0005\"\b\b\u0001\u0010\u000b*\u00028\u0000*\b\u0012\u0004\u0012\u00028\u00010\b2!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0001\u0012\u0004\u0012\u00028\u00000\fH\u0087\bø\u0001\u0000¢\u0006\u0004\b!\u0010\u0018\u001aB\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\b\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0005*\u00028\u00002\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f¢\u0006\u0002\b\"H\u0087\bø\u0001\u0000¢\u0006\u0004\b\t\u0010\u0018\u001a\u0017\u0010#\u001a\u00020\u001e*\u0006\u0012\u0002\b\u00030\bH\u0001¢\u0006\u0004\b#\u0010$\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006%"},
   d2 = {"", "exception", "", "createFailure", "(Ljava/lang/Throwable;)Ljava/lang/Object;", "R", "Lkotlin/Function0;", "block", "Lkotlin/Result;", "runCatching", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "T", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "value", "onSuccess", "onFailure", "fold", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "defaultValue", "getOrDefault", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getOrElse", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "getOrThrow", "(Ljava/lang/Object;)Ljava/lang/Object;", "transform", "map", "mapCatching", "", "action", "recover", "recoverCatching", "Lkotlin/ExtensionFunctionType;", "throwOnFailure", "(Ljava/lang/Object;)V", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nResult.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Result.kt\nkotlin/ResultKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,340:1\n1#2:341\n*E\n"})
public final class ResultKt {
   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   @NotNull
   public static final Object createFailure(@NotNull Throwable exception) {
      Intrinsics.checkNotNullParameter(exception, "exception");
      return new Result.Failure(exception);
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.3"
   )
   public static final void throwOnFailure(@NotNull Object $this$throwOnFailure) {
      if ($this$throwOnFailure instanceof Result.Failure) {
         throw ((Result.Failure)$this$throwOnFailure).exception;
      }
   }

   @InlineOnly
   @SinceKotlin(
      version = "1.3"
   )
   private static final Object runCatching(Function0 block) {
      Intrinsics.checkNotNullParameter(block, "block");

      Object var1;
      try {
         Result.Companion var4 = Result.Companion;
         var1 = Result.constructor-impl(block.invoke());
      } catch (Throwable e) {
         Result.Companion var10000 = Result.Companion;
         var1 = Result.constructor-impl(createFailure(e));
      }

      return var1;
   }

   @InlineOnly
   @SinceKotlin(
      version = "1.3"
   )
   private static final Object runCatching(Object $this$runCatching, Function1 block) {
      Intrinsics.checkNotNullParameter(block, "block");

      Object var2;
      try {
         Result.Companion var5 = Result.Companion;
         var2 = Result.constructor-impl(block.invoke($this$runCatching));
      } catch (Throwable e) {
         Result.Companion var10000 = Result.Companion;
         var2 = Result.constructor-impl(createFailure(e));
      }

      return var2;
   }

   @InlineOnly
   @SinceKotlin(
      version = "1.3"
   )
   private static final Object getOrThrow(Object $this$getOrThrow) {
      throwOnFailure($this$getOrThrow);
      return $this$getOrThrow;
   }

   @InlineOnly
   @SinceKotlin(
      version = "1.3"
   )
   private static final Object getOrElse(Object $this$getOrElse, Function1 onFailure) {
      Intrinsics.checkNotNullParameter(onFailure, "onFailure");
      Throwable exception = Result.exceptionOrNull-impl($this$getOrElse);
      return exception == null ? $this$getOrElse : onFailure.invoke(exception);
   }

   @InlineOnly
   @SinceKotlin(
      version = "1.3"
   )
   private static final Object getOrDefault(Object $this$getOrDefault, Object defaultValue) {
      return Result.isFailure-impl($this$getOrDefault) ? defaultValue : $this$getOrDefault;
   }

   @InlineOnly
   @SinceKotlin(
      version = "1.3"
   )
   private static final Object fold(Object $this$fold, Function1 onSuccess, Function1 onFailure) {
      Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
      Intrinsics.checkNotNullParameter(onFailure, "onFailure");
      Throwable exception = Result.exceptionOrNull-impl($this$fold);
      return exception == null ? onSuccess.invoke($this$fold) : onFailure.invoke(exception);
   }

   @InlineOnly
   @SinceKotlin(
      version = "1.3"
   )
   private static final Object map(Object $this$map, Function1 transform) {
      Intrinsics.checkNotNullParameter(transform, "transform");
      Object var2;
      if (Result.isSuccess-impl($this$map)) {
         Result.Companion var10000 = Result.Companion;
         var2 = Result.constructor-impl(transform.invoke($this$map));
      } else {
         var2 = Result.constructor-impl($this$map);
      }

      return var2;
   }

   @InlineOnly
   @SinceKotlin(
      version = "1.3"
   )
   private static final Object mapCatching(Object $this$mapCatching, Function1 transform) {
      Intrinsics.checkNotNullParameter(transform, "transform");
      Object var7;
      if (Result.isSuccess-impl($this$mapCatching)) {
         Object $this$mapCatching_u24lambda_u243 = $this$mapCatching;

         Object $this$mapCatching_u24lambda_u243;
         try {
            Result.Companion var6 = Result.Companion;
            int var4 = 0;
            $this$mapCatching_u24lambda_u243 = Result.constructor-impl(transform.invoke($this$mapCatching_u24lambda_u243));
         } catch (Throwable var5) {
            Result.Companion var10000 = Result.Companion;
            $this$mapCatching_u24lambda_u243 = Result.constructor-impl(createFailure(var5));
         }

         var7 = $this$mapCatching_u24lambda_u243;
      } else {
         var7 = Result.constructor-impl($this$mapCatching);
      }

      return var7;
   }

   @InlineOnly
   @SinceKotlin(
      version = "1.3"
   )
   private static final Object recover(Object $this$recover, Function1 transform) {
      Intrinsics.checkNotNullParameter(transform, "transform");
      Throwable exception = Result.exceptionOrNull-impl($this$recover);
      Object var10000;
      if (exception == null) {
         var10000 = $this$recover;
      } else {
         Result.Companion var3 = Result.Companion;
         var10000 = Result.constructor-impl(transform.invoke(exception));
      }

      return var10000;
   }

   @InlineOnly
   @SinceKotlin(
      version = "1.3"
   )
   private static final Object recoverCatching(Object $this$recoverCatching, Function1 transform) {
      Intrinsics.checkNotNullParameter(transform, "transform");
      Throwable exception = Result.exceptionOrNull-impl($this$recoverCatching);
      Object var10000;
      if (exception == null) {
         var10000 = $this$recoverCatching;
      } else {
         Object $this$recoverCatching_u24lambda_u245;
         try {
            Result.Companion var8 = Result.Companion;
            int var5 = 0;
            $this$recoverCatching_u24lambda_u245 = Result.constructor-impl(transform.invoke(exception));
         } catch (Throwable var6) {
            Result.Companion var7 = Result.Companion;
            $this$recoverCatching_u24lambda_u245 = Result.constructor-impl(createFailure(var6));
         }

         var10000 = $this$recoverCatching_u24lambda_u245;
      }

      return var10000;
   }

   @InlineOnly
   @SinceKotlin(
      version = "1.3"
   )
   private static final Object onFailure(Object $this$onFailure, Function1 action) {
      Intrinsics.checkNotNullParameter(action, "action");
      Throwable var10000 = Result.exceptionOrNull-impl($this$onFailure);
      if (var10000 != null) {
         Throwable it = var10000;
         int var4 = 0;
         action.invoke(it);
      }

      return $this$onFailure;
   }

   @InlineOnly
   @SinceKotlin(
      version = "1.3"
   )
   private static final Object onSuccess(Object $this$onSuccess, Function1 action) {
      Intrinsics.checkNotNullParameter(action, "action");
      if (Result.isSuccess-impl($this$onSuccess)) {
         action.invoke($this$onSuccess);
      }

      return $this$onSuccess;
   }
}
