package kotlin;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a'\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a1\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\u0004\b\u0004\u0010\b\u001a/\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\u00002\u0006\u0010\n\u001a\u00020\t2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\u0004\b\u0004\u0010\u000b¨\u0006\f"},
   d2 = {"T", "Lkotlin/Function0;", "initializer", "Lkotlin/Lazy;", "lazy", "(Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;", "", "lock", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;", "Lkotlin/LazyThreadSafetyMode;", "mode", "(Lkotlin/LazyThreadSafetyMode;Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;", "kotlin-stdlib"},
   xs = "kotlin/LazyKt"
)
class LazyKt__LazyJVMKt {
   @NotNull
   public static final Lazy lazy(@NotNull Function0 initializer) {
      Intrinsics.checkNotNullParameter(initializer, "initializer");
      return new SynchronizedLazyImpl(initializer, (Object)null, 2, (DefaultConstructorMarker)null);
   }

   @NotNull
   public static final Lazy lazy(@NotNull LazyThreadSafetyMode mode, @NotNull Function0 initializer) {
      Intrinsics.checkNotNullParameter(mode, "mode");
      Intrinsics.checkNotNullParameter(initializer, "initializer");
      Lazy var10000;
      switch (LazyKt__LazyJVMKt.WhenMappings.$EnumSwitchMapping$0[mode.ordinal()]) {
         case 1:
            var10000 = new SynchronizedLazyImpl(initializer, (Object)null, 2, (DefaultConstructorMarker)null);
            break;
         case 2:
            var10000 = new SafePublicationLazyImpl(initializer);
            break;
         case 3:
            var10000 = new UnsafeLazyImpl(initializer);
            break;
         default:
            throw new NoWhenBranchMatchedException();
      }

      return var10000;
   }

   @NotNull
   public static final Lazy lazy(@Nullable Object lock, @NotNull Function0 initializer) {
      Intrinsics.checkNotNullParameter(initializer, "initializer");
      return new SynchronizedLazyImpl(initializer, lock);
   }

   public LazyKt__LazyJVMKt() {
   }

   // $FF: synthetic class
   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public class WhenMappings {
      // $FF: synthetic field
      public static final int[] $EnumSwitchMapping$0;

      static {
         int[] var0 = new int[LazyThreadSafetyMode.values().length];

         try {
            var0[LazyThreadSafetyMode.SYNCHRONIZED.ordinal()] = 1;
         } catch (NoSuchFieldError var4) {
         }

         try {
            var0[LazyThreadSafetyMode.PUBLICATION.ordinal()] = 2;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[LazyThreadSafetyMode.NONE.ordinal()] = 3;
         } catch (NoSuchFieldError var2) {
         }

         $EnumSwitchMapping$0 = var0;
      }
   }
}
