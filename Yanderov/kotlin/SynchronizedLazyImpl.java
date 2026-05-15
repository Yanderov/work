package kotlin;

import java.io.Serializable;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0002\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\u00060\u0003j\u0002`\u0004B!\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00058\b@\bX\u0088\u000e¢\u0006\u0006\n\u0004\b\u0006\u0010\u0015R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u0014R\u0014\u0010\u0017\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0012¨\u0006\u0018"},
   d2 = {"Lkotlin/SynchronizedLazyImpl;", "T", "Lkotlin/Lazy;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "Lkotlin/Function0;", "initializer", "", "lock", "<init>", "(Lkotlin/jvm/functions/Function0;Ljava/lang/Object;)V", "", "isInitialized", "()Z", "", "toString", "()Ljava/lang/String;", "writeReplace", "()Ljava/lang/Object;", "_value", "Ljava/lang/Object;", "Lkotlin/jvm/functions/Function0;", "getValue", "value", "kotlin-stdlib"}
)
final class SynchronizedLazyImpl implements Lazy, Serializable {
   @Nullable
   private Function0 initializer;
   @Nullable
   private volatile Object _value;
   @NotNull
   private final Object lock;

   public SynchronizedLazyImpl(@NotNull Function0 initializer, @Nullable Object lock) {
      Intrinsics.checkNotNullParameter(initializer, "initializer");
      super();
      this.initializer = initializer;
      this._value = UNINITIALIZED_VALUE.INSTANCE;
      Object var10001 = lock;
      if (lock == null) {
         var10001 = this;
      }

      this.lock = var10001;
   }

   // $FF: synthetic method
   public SynchronizedLazyImpl(Function0 var1, Object var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 2) != 0) {
         var2 = null;
      }

      this(var1, var2);
   }

   public Object getValue() {
      // $FF: Couldn't be decompiled
   }

   public boolean isInitialized() {
      return this._value != UNINITIALIZED_VALUE.INSTANCE;
   }

   @NotNull
   public String toString() {
      return this.isInitialized() ? String.valueOf(this.getValue()) : "Lazy value not initialized yet.";
   }

   private final Object writeReplace() {
      return new InitializedLazyImpl(this.getValue());
   }
}
